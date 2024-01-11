package com.openspace.HospitalMgnt.Company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.openspace.HospitalMgnt.PackageMaster.PackageMasterRepository;
import com.openspace.HospitalMgnt.common.Registration.RegistrationDto;
import com.openspace.Model.Config.FileConfig;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.Dto.StripeInvoiceDto;
import com.openspace.Model.Dto.StripeInvoiceItem;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.CompanyStatus;
import com.openspace.Model.PatientMgnt.Repositories.CityRepository;
import com.openspace.Model.PatientMgnt.Repositories.CompanyRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripePlanRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripeTierRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Stripe.StripePlan;
import com.openspace.Model.Stripe.StripeTier;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.PatientManagement.s3.AccessS3Bucket;
import com.openspace.PatientManagement.s3.S3Bucket;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FileConfig fileConfig;

	@Autowired
	private PackageMasterRepository packageMasterRepository;

	@Autowired
	private StripePlanRepository stripePlanRepository;

	@Autowired
	private StripeTierRepository stripeTierRepository;

	private Path pathLocation = Paths.get("upload-dir");

	@Override
	public void saveCompany(Company company) {
		Company dbCompany = companyRepository.findByCompanyName(company.getCompanyName());
		if (dbCompany != null) {
			throw new RuntimeException(ErrorMessageHandler.companyNameAlreadyExists);
		}
		companyRepository.save(company);
	}

	@Override
	public List<Company> getAllCompanies() {
		return (List<Company>) companyRepository.findAll();
	}

	@Override
	public void updateCompany(Company company) {
		Company dbCompany = companyRepository.findOne(company.getId());
		if (dbCompany == null) {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		Company dbCompany2 = companyRepository.findByCompanyName(company.getCompanyName());
		if (dbCompany2 != null) {
			throw new RuntimeException(ErrorMessageHandler.companyNameAlreadyExists);
		}
		dbCompany.setCompanyName(company.getCompanyName());
		companyRepository.save(dbCompany);
	}

	@Override
	public void deleteCompany(Long id) {
		Company dbCompany = companyRepository.findOne(id);
		if (dbCompany == null) {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		companyRepository.delete(dbCompany);
	}

	@Override
	public RegistrationDto getCompanyUser(String username) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		RegistrationDto registrationDto = new RegistrationDto();
		if (dbUserAccount.getCompany() != null) {
			registrationDto.setCompanyName(dbUserAccount.getCompany().getCompanyName());
			if (dbUserAccount.getCompany().getAddress1() != null) {
				registrationDto.setAddress1(dbUserAccount.getCompany().getAddress1().getAddress1());
				registrationDto.setAddress2(dbUserAccount.getCompany().getAddress1().getAddress2());
				registrationDto.setCity(dbUserAccount.getCompany().getAddress1().getCity());
				registrationDto.setCountry(dbUserAccount.getCompany().getAddress1().getCountry());
				registrationDto.setState(dbUserAccount.getCompany().getAddress1().getState());
				registrationDto.setZipcode(dbUserAccount.getCompany().getAddress1().getZipcode());
			}
		}
		Person dbPerson = personRepository.findByEmail(dbUserAccount.getUsername());
		if (dbPerson != null) {
			registrationDto.setFirstName(dbPerson.getFirstName());
			registrationDto.setLastName(dbPerson.getLastName());
			registrationDto.setMoiblenumber(dbPerson.getMobileNumber());
		}
		registrationDto.setEmail(dbUserAccount.getUsername());
		registrationDto.setPassword(dbUserAccount.getPassword());
		registrationDto.setId(dbUserAccount.getId());
		return registrationDto;
	}

	@Override
	public void updateCompanyUser(RegistrationDto registrationDto) throws IOException {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(registrationDto.getEmail());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Address1 address = new Address1();
		address.setCountry(registrationDto.getCountry());
		address.setState(registrationDto.getState());
		address.setCity(registrationDto.getCity());
		address.setAddress1(registrationDto.getAddress1());
		address.setAddress2(registrationDto.getAddress2());
		address.setZipcode(registrationDto.getZipcode());

		if (registrationDto.getCompanyName() != null) {

			Company dbCompany = companyRepository.findByCompanyName(registrationDto.getCompanyName());
			if (dbCompany == null) {
				Company company = companyRepository.findOne(dbUserAccount.getCompany().getId());
				company.setCompanyName(registrationDto.getCompanyName());
				company.setCompanyStatus(CompanyStatus.APPROVED);
				company.setAddress1(address);
				companyRepository.save(company);
				dbUserAccount.setCompany(company);
				userAccountRepository.save(dbUserAccount);
			} else if(dbCompany.getId().equals(dbUserAccount.getCompany().getId())) {
				// throw new RuntimeException("Company Already Exists");
				dbCompany.setAddress1(address);
				dbCompany.setCompanyStatus(CompanyStatus.APPROVED);
				dbCompany.setCompanyName(registrationDto.getCompanyName());
				companyRepository.save(dbCompany);
				dbUserAccount.setCompany(dbCompany);
				userAccountRepository.save(dbUserAccount);
			}else{
				throw new RuntimeException("Company Is Already Used By Another Super Admin");
			}
			Person dbPerson = personRepository.findByUserAccount_Id(dbUserAccount.getId());
			if (dbPerson == null) {
				throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
			}
			dbPerson.setFirstName(registrationDto.getFirstName());
			dbPerson.setLastName(registrationDto.getLastName());
			dbPerson.setEmail(registrationDto.getEmail());
			dbPerson.setMobileNumber(registrationDto.getMoiblenumber());
			// dbPerson.setProfilePic(registrationDto.getProfilePic());
			dbPerson.setUserAccount(dbUserAccount);

			S3Bucket s3Bucket = new S3Bucket();
			AWSCredentials credentials = new BasicAWSCredentials(AccessS3Bucket.keyName, AccessS3Bucket.securityKey);
			AmazonS3 s3client = new AmazonS3Client(credentials);
			if (registrationDto.getProfilePic() != null) {
				String locationPath1 = fileConfig.getLocationpath().replace("/", File.separator);
				System.out.println(locationPath1 + registrationDto.getProfilePic());
				String locationPath = AccessS3Bucket.bucketName.replace("/", File.separator);

				File companyfolder = new File(locationPath, dbUserAccount.getUniqueId());
				String companyFolder1 = dbUserAccount.getUniqueId();
				boolean findCompanyFolder = s3Bucket.isValidFile(companyFolder1);
				if (findCompanyFolder == false) {
					s3Bucket.createFolder(companyFolder1);
					String yearfolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear();
					boolean findyearfolder = s3Bucket.isValidFile(yearfolder);
					if (findyearfolder == false) {
						s3Bucket.createFolder(yearfolder);
						String enterprisefolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Enterprise";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String imageFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Enterprise" + "/" + "Images";
							boolean findImagefolder = s3Bucket.isValidFile(imageFolder);
							if (findImagefolder == false) {
								s3Bucket.createFolder(imageFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							}

						} else {
							String imageFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Enterprise" + "/" + "Images";
							boolean findImagefolder = s3Bucket.isValidFile(imageFolder);
							if (findImagefolder == false) {
								s3Bucket.createFolder(imageFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							}
						}
					} else {
						String enterprisefolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Enterprise";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String imageFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Enterprise" + "/" + "Images";
							boolean findImagefolder = s3Bucket.isValidFile(imageFolder);
							if (findImagefolder == false) {
								s3Bucket.createFolder(imageFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							}
						}
					}
				} else {
					String yearfolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear();
					boolean findyearfolder = s3Bucket.isValidFile(yearfolder);
					if (findyearfolder == false) {
						s3Bucket.createFolder(yearfolder);
						String enterprisefolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Enterprise";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String imageFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Enterprise" + "/" + "Images";
							boolean findImagefolder = s3Bucket.isValidFile(imageFolder);
							if (findImagefolder == false) {
								s3Bucket.createFolder(imageFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							}
						}

					}else{
						String enterprisefolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Enterprise";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String imageFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Enterprise" + "/" + "Images";
							boolean findImagefolder = s3Bucket.isValidFile(imageFolder);
							if (findImagefolder == false) {
								s3Bucket.createFolder(imageFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							}
						}else{
							String imageFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Enterprise" + "/" + "Images";
							boolean findImagefolder = s3Bucket.isValidFile(imageFolder);
							if (findImagefolder == false) {
								s3Bucket.createFolder(imageFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										imageFolder + "/" + registrationDto.getProfilePic(),
										new File(locationPath1 + registrationDto.getProfilePic()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbPerson.setProfilePic(imageFolder + "/" + registrationDto.getProfilePic());
								personRepository.save(dbPerson);
							}
						}
					}
				}
			} 
		} else {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
	}

	@Override
	public Page<Company> getAllCompaniesPage(int page, int size) {
		// TODO Auto-generated method stub
		return (Page<Company>) companyRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id"));
	}

	@Override
	public StripeInvoiceDto getStripeInvoice(String adminUsername) {
		StripeInvoiceDto stripeInvoiceDto = new StripeInvoiceDto();
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}

		List<Person> persons = personRepository
				.findByUserAccount_Company_IdAndActive(dbUserAccount.getCompany().getId(), true);
		stripeInvoiceDto.setUsers((long) persons.size() + 6);
		// PackageNameMaster
		// packageNameMaster=packageMasterRepository.findByPackageName("Basic");
		stripeInvoiceDto.setPackageNameMaster(dbUserAccount.getStripePackage().getPackageName());
		StripePlan stripePlan = stripePlanRepository.findByStripePackage_Id(dbUserAccount.getStripePackage().getId());

		List<StripeTier> stripeTiers = stripeTierRepository.findByStripePlan_Id(stripePlan.getId());

		List<StripeInvoiceItem> stripeInvoiceItems = new ArrayList<>();
		double amount = 0;
		boolean flag = false;
		for (StripeTier stripeTier : stripeTiers) {
			if (stripeTier.getUpTo() >= persons.size() + 6) {
				if (flag == false) {
					flag = true;
					amount = stripeTier.getAmount();
				}
			}
		}
		StripeInvoiceItem stripeInvoiceItem = new StripeInvoiceItem();
		stripeInvoiceItem.setDescription(dbUserAccount.getStripePackage().getPackageName());
		stripeInvoiceItem.setUsers(persons.size() + 6L);
		stripeInvoiceItem.setUnitCost(amount);
		stripeInvoiceItem.setPrice(stripeInvoiceItem.getUsers() * stripeInvoiceItem.getUnitCost());
		stripeInvoiceItems.add(stripeInvoiceItem);

		stripeInvoiceDto.setStripeInvoiceItems(stripeInvoiceItems);
		double totalAmount = 0;
		for (StripeInvoiceItem item : stripeInvoiceItems) {
			totalAmount = totalAmount + item.getPrice();
		}
		stripeInvoiceDto.setTotalAmount(totalAmount);
		return stripeInvoiceDto;

	}

	@Override
	public Boolean isCompanyExists(String company) {
		boolean result = false;
		Company dbCompany = companyRepository.findByCompanyName(company);
		if (dbCompany != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	///// SCRAP
	/*
	 * @Override public StripeInvoiceDto getStripeInvoice(String adminUsername){
	 * StripeInvoiceDto stripeInvoiceDto=new StripeInvoiceDto(); UserAccount
	 * dbUserAccount = userAccountRepository.findByUsername(adminUsername); if
	 * (dbUserAccount == null) { throw new RuntimeException(
	 * "User Does not Exist"); } List<Person> persons=
	 * personRepository.findByUserAccount_Company_IdAndActive(dbUserAccount.
	 * getCompany().getId(),true); stripeInvoiceDto.setUsers((long)
	 * persons.size()+23); PackageNameMaster
	 * packageNameMaster=packageMasterRepository.findByPackageName("Basic");
	 * stripeInvoiceDto.setPackageNameMaster(packageNameMaster);
	 * 
	 * List<StripeInvoiceItem> stripeInvoiceItems=new ArrayList<>();
	 * StripeInvoiceItem stripeInvoiceItem=new StripeInvoiceItem();
	 * stripeInvoiceItem.setDescription("First 10"); Long qty=0L;
	 * if(persons.size()+23<10){ qty=(long) persons.size()+23; }else{ qty=10L; }
	 * stripeInvoiceItem.setUsers(qty); stripeInvoiceItem.setUnitCost(39.99);
	 * stripeInvoiceItem.setPrice(qty*stripeInvoiceItem.getUnitCost());
	 * stripeInvoiceItems.add(stripeInvoiceItem);
	 * 
	 * if(persons.size()+23>10){ StripeInvoiceItem stripeInvoiceItem1=new
	 * StripeInvoiceItem(); stripeInvoiceItem1.setDescription("Next 11 to 20");
	 * Long qty1=0L; if(persons.size()+23<20){ qty1=(long) persons.size()+23-10;
	 * }else{ qty1=10L; } stripeInvoiceItem1.setUsers(qty1);
	 * stripeInvoiceItem1.setUnitCost(29.99);
	 * stripeInvoiceItem1.setPrice(qty1*stripeInvoiceItem1.getUnitCost());
	 * stripeInvoiceItems.add(stripeInvoiceItem1); }
	 * 
	 * if(persons.size()+23>20){ StripeInvoiceItem stripeInvoiceItem1=new
	 * StripeInvoiceItem(); stripeInvoiceItem1.setDescription("After 20 "); Long
	 * qty1=(long) persons.size()+23-20; stripeInvoiceItem1.setUsers(qty1);
	 * System.out.println("qty1::"+qty1); stripeInvoiceItem1.setUnitCost(19.99);
	 * stripeInvoiceItem1.setPrice(qty1*stripeInvoiceItem1.getUnitCost());
	 * //stripeInvoiceItem1.setPrice(new
	 * DecimalFormat("##.##").format(qty1*stripeInvoiceItem1.getUnitCost()));
	 * //stripeInvoiceItem1.setPrice(Double.parseDouble(new
	 * DecimalFormat("##.##").format(qty1*stripeInvoiceItem1.getUnitCost())));
	 * stripeInvoiceItems.add(stripeInvoiceItem1); }
	 * stripeInvoiceDto.setStripeInvoiceItems(stripeInvoiceItems); double
	 * totalAmount=0; for(StripeInvoiceItem item:stripeInvoiceItems){
	 * totalAmount=totalAmount+item.getPrice(); }
	 * stripeInvoiceDto.setTotalAmount(totalAmount); return stripeInvoiceDto;
	 * 
	 * }
	 */

	/*
	 * @Override public StripeInvoiceDto getStripeInvoice(String adminUsername)
	 * { StripeInvoiceDto stripeInvoiceDto = new StripeInvoiceDto(); UserAccount
	 * dbUserAccount = userAccountRepository.findByUsername(adminUsername); if
	 * (dbUserAccount == null) { throw new
	 * RuntimeException("User Does not Exist"); }
	 * 
	 * List<Person> persons = personRepository
	 * .findByUserAccount_Company_IdAndActive(dbUserAccount.getCompany().getId()
	 * , true); stripeInvoiceDto.setUsers((long) persons.size() + 23); //
	 * PackageNameMaster //
	 * packageNameMaster=packageMasterRepository.findByPackageName("Basic");
	 * stripeInvoiceDto.setPackageNameMaster(dbUserAccount.getStripePackage().
	 * getPackageName()); StripePlan stripePlan =
	 * stripePlanRepository.findByStripePackage_Id(dbUserAccount.
	 * getStripePackage().getId());
	 * 
	 * List<StripeTier> stripeTiers =
	 * stripeTierRepository.findByStripePlan_Id(stripePlan.getId());
	 * 
	 * List<StripeInvoiceItem> stripeInvoiceItems = new ArrayList<>(); boolean
	 * flag = false; Long num = 0L; for (StripeTier stripeTier : stripeTiers) {
	 * StripeInvoiceItem stripeInvoiceItem = new StripeInvoiceItem(); Long qty =
	 * 0L; if (flag == false) { flag = true;
	 * stripeInvoiceItem.setDescription("First " + stripeTier.getUpTo()); num =
	 * stripeTier.getUpTo();
	 * 
	 * if (persons.size() + 26 < stripeTier.getUpTo()) { qty = (long)
	 * persons.size() + 26; } else { qty = stripeTier.getUpTo(); } } else { Long
	 * num1 = num + 1; stripeInvoiceItem.setDescription("Next " + num1 + " to "
	 * + stripeTier.getUpTo());
	 * 
	 * if (persons.size() + 26 < stripeTier.getUpTo()) { qty = (long)
	 * persons.size() + 26 - num; } else { qty = num; } num =
	 * stripeTier.getUpTo(); }
	 * 
	 * stripeInvoiceItem.setUsers(qty);
	 * stripeInvoiceItem.setUnitCost(stripeTier.getAmount());
	 * stripeInvoiceItem.setPrice(qty * stripeInvoiceItem.getUnitCost());
	 * stripeInvoiceItems.add(stripeInvoiceItem); }
	 * 
	 * 
	 * StripeInvoiceItem stripeInvoiceItem=new StripeInvoiceItem();
	 * stripeInvoiceItem.setDescription("First 10"); Long qty=0L;
	 * if(persons.size()+23<10){ qty=(long) persons.size()+23; }else{ qty=10L; }
	 * stripeInvoiceItem.setUsers(qty); stripeInvoiceItem.setUnitCost(39.99);
	 * stripeInvoiceItem.setPrice(qty*stripeInvoiceItem.getUnitCost());
	 * stripeInvoiceItems.add(stripeInvoiceItem);
	 * 
	 * if(persons.size()+23>10){ StripeInvoiceItem stripeInvoiceItem1=new
	 * StripeInvoiceItem(); stripeInvoiceItem1.setDescription( "Next 11 to 20");
	 * Long qty1=0L; if(persons.size()+23<20){ qty1=(long) persons.size()+23-10;
	 * }else{ qty1=10L; } stripeInvoiceItem1.setUsers(qty1);
	 * stripeInvoiceItem1.setUnitCost(29.99);
	 * stripeInvoiceItem1.setPrice(qty1*stripeInvoiceItem1.getUnitCost());
	 * stripeInvoiceItems.add(stripeInvoiceItem1); }
	 * 
	 * 
	 * 
	 * if(persons.size()+23>20){ StripeInvoiceItem stripeInvoiceItem1=new
	 * StripeInvoiceItem(); stripeInvoiceItem1.setDescription("After 20 "); Long
	 * qty1=(long) persons.size()+23-20; stripeInvoiceItem1.setUsers(qty1);
	 * System.out.println("qty1::"+qty1); stripeInvoiceItem1.setUnitCost(19.99);
	 * stripeInvoiceItem1.setPrice(qty1*stripeInvoiceItem1.getUnitCost());
	 * //stripeInvoiceItem1.setPrice(new
	 * DecimalFormat("##.##").format(qty1*stripeInvoiceItem1.getUnitCost())) ;
	 * //stripeInvoiceItem1.setPrice(Double.parseDouble(new
	 * DecimalFormat("##.##").format(qty1*stripeInvoiceItem1.getUnitCost())) );
	 * stripeInvoiceItems.add(stripeInvoiceItem1); }
	 * 
	 * 
	 * stripeInvoiceDto.setStripeInvoiceItems(stripeInvoiceItems); double
	 * totalAmount = 0; for (StripeInvoiceItem item : stripeInvoiceItems) {
	 * totalAmount = totalAmount + item.getPrice(); }
	 * stripeInvoiceDto.setTotalAmount(totalAmount); return stripeInvoiceDto;
	 * 
	 * }
	 */
}
