package com.openspace.HospitalMgnt.common.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.openspace.HospitalMgnt.common.Base64Encoding;
import com.openspace.HospitalMgnt.common.OTPGeneration;
import com.openspace.Model.Config.FileConfig;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.DoctorManagement.ActiveStatus;
import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.Schedule;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.Site;
import com.openspace.Model.PatientMgnt.Repositories.AppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.CityRepository;
import com.openspace.Model.PatientMgnt.Repositories.PaymentRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.RoleRepository;
import com.openspace.Model.PatientMgnt.Repositories.ScheduleRepository;
import com.openspace.Model.PatientMgnt.Repositories.SiteRepository;
import com.openspace.Model.PatientMgnt.Repositories.SubAppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.UserManagement.Role;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.PatientManagement.s3.AccessS3Bucket;
import com.openspace.PatientManagement.s3.S3Bucket;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private FileConfig fileConfig;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private SubAppointmentRepository subAppointmentRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private TherapistRepository therapistRepository;

	private Path pathLocation = Paths.get("upload-dir");

	@Override
	public List<Person> getAllPersons(String adminUsername) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
		System.out.println("Role Name "+dbUserAccount.getRole().getRoleName());
		if (dbUserAccount.getCompany() != null && dbUserAccount.getRole().getRoleName().equals("Super Admin")) {
			dbUseraccountList = userAccountRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		}
		if (dbUserAccount.getCompany() == null && dbUserAccount.getRole().getRoleName().equals("Individual")) {
			dbUseraccountList = userAccountRepository.findByUniqueId(dbUserAccount.getUniqueId());
		}
		
		if (dbUserAccount.getSite() != null && dbUserAccount.getRole().getRoleName().equals("Facility Manager")) {
			dbUseraccountList = userAccountRepository.findBySite_Id(dbUserAccount.getSite().getId());
		}
		
		
		List<Person> persons = new ArrayList<Person>();
		for (UserAccount userAccount : dbUseraccountList) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);

		}
		return persons;
		/*
		 * Person person = personRepository.findByEmail(adminUsername); Company
		 * company = person.getUserAccount().getCompany();
		 * System.out.println("companyName:" + company.getCompanyName());
		 * 
		 * return (List<Person>) userAccountRepository.findByCompany(company);
		 */
	}

	@Override
	public Page<Person> getAllPersons(String adminUsername, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Page<Person> persons = null;
		if(dbUserAccount.getCompany()!=null){
		persons = personRepository.findByUserAccount_Company_Id(dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		}else{
			persons = personRepository.findByUserAccount_Id(dbUserAccount.getId(), new PageRequest(page, size, Sort.Direction.DESC, "id"));
		}
		return persons;
	}

	@Override
	public Page<Person> getAllPersonsSearch(String adminUsername, String search, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		if (search.equals("null")) {
			return (Page<Person>) personRepository.findByUserAccount_Company_Id(dbUserAccount.getCompany().getId(),
					new PageRequest(page, size, Sort.Direction.DESC, "id"));
		} else {
			List<Person> personList = new ArrayList<Person>();
			personList = personRepository.findByPersonSearch(search, dbUserAccount.getCompany().getId());
			return new PageImpl<Person>(personList, new PageRequest(page, size, Sort.Direction.DESC, "id"),
					personList.size());
		}
	}

	@Override
	public void addPerson(Person person) {
		personRepository.save(person);
	}

	// not yet completed
	@SuppressWarnings("unused")
	@Override
	public void updatePerson(Doctor doctor, String adminUsername) throws FileNotFoundException, IOException {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(doctor.getEmail());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.adminTherapistDoesNotExists);
		}
		Person dbPerson = personRepository.findByEmail(dbUserAccount.getUsername());
		if (dbPerson == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		Doctor dbdoctor = therapistRepository.findOne(dbPerson.getId());
		Company dbCompany = dbUserAccount.getCompany();
		if (dbCompany == null) {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}

		UserAccount dbadminUsername = userAccountRepository.findByUsername(adminUsername);
		if (dbadminUsername == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		// Therpist
		if (doctor.getUserAccount().getRole().getRoleName().equals("Therapist")) {
			UserAccount user = userAccountRepository.findByUsername(doctor.getEmail());
			user.setUsername(doctor.getEmail());
			user.setActive(doctor.isActive());

			/*
			 * if(doctor.isActive()==false){ List<Appointment>
			 * appointments=appointmentRepository.findByDoctor_IdAndActive(
			 * doctor.getId(), true); if(appointments.size()>0){ throw new
			 * RuntimeException("Can't inActive! Therapist has Appointments!Please assign those appointments to another Therapist!"
			 * ); }
			 * 
			 * }
			 */
			if (doctor.isActive() == false) {
				// List<SubAppointment>
				// subAppointments=subAppointmentRepository.findByDoctor_IdAndActiveAndStatus(doctor.getId(),
				// true,SubAppointmentStatus.AWAITING);
				List<SubAppointment> subAppointments = subAppointmentRepository
						.findByDoctor_IdAndActiveAndStatusAndAppointmentStartedDateGreaterThanEqual(doctor.getId(),
								true, SubAppointmentStatus.AWAITING, LocalDate.now());
				if (subAppointments.size() > 0) {
					throw new RuntimeException(ErrorMessageHandler.pleaseAssignAppointmentsToAnotherTherapistBeforeInactivating);
				}

			}
			/*
			 * UserAccount adminUser =
			 * userAccountRepository.findByUsername(personDto.getAdminUserName()
			 * ); user.setCompany(adminUser.getCompany());
			 */
			userAccountRepository.save(user);

			Address1 address = dbPerson.getAddress1();
			address.setCountry(doctor.getAddress1().getCountry());
			address.setState(doctor.getAddress1().getState());
			address.setCity(doctor.getAddress1().getCity());
			address.setAddress1(doctor.getAddress1().getAddress1());
			address.setAddress2(doctor.getAddress1().getAddress2());
			address.setZipcode(doctor.getAddress1().getZipcode());

			dbPerson.setUserAccount(user);
			dbPerson.setFirstName(doctor.getFirstName());
			dbPerson.setLastName(doctor.getLastName());
			dbPerson.setAddress1(address);
			dbPerson.setEmail(doctor.getEmail());
			dbPerson.setMobileNumber(doctor.getMobileNumber());
			dbPerson.setGender(doctor.getGender());
			dbPerson.setActive(doctor.isActive());
			dbPerson.setCreatedDate(LocalDate.now());
			dbPerson.setModifiedDate(LocalDate.now());
			
			dbPerson.setAadharNumber(doctor.getAadharNumber());
			dbdoctor.setAadharNumber(doctor.getAadharNumber());
			dbdoctor.setFirstName(doctor.getFirstName());
			dbdoctor.setLastName(doctor.getLastName());
			dbdoctor.setAddress1(address);
			dbdoctor.setEmail(doctor.getEmail());
			dbdoctor.setMobileNumber(doctor.getMobileNumber());
			dbdoctor.setGender(doctor.getGender());
			dbdoctor.setActive(doctor.isActive());
			dbdoctor.setModifiedByUser(dbadminUsername.getUsername());
			dbdoctor.setModifiedDate(LocalDate.now());
			dbdoctor.setUserAccount(user);
			dbdoctor.setRciNumber(doctor.getRciNumber());
			dbdoctor.setDesignation(doctor.getDesignation());
			dbdoctor.setSpecialization(doctor.getSpecialization());
			dbdoctor.setDepartment(doctor.getDepartment());
			dbdoctor.setShortAutoBiography(doctor.getShortAutoBiography());
			

			System.out.println("Image path    "+doctor.getSignaturePath());
			

			S3Bucket s3Bucket = new S3Bucket();
			AWSCredentials credentials = new BasicAWSCredentials(AccessS3Bucket.keyName, AccessS3Bucket.securityKey);
			AmazonS3 s3client = new AmazonS3Client(credentials);
			if (doctor.getSignaturePath() != null && doctor.getDirectoryPath().equals("true")) {
				String locationPath1 = fileConfig.getLocationpath().replace("/", File.separator);
				String locationPath = AccessS3Bucket.bucketName.replace("/", File.separator);

				String companyFolder1 = dbadminUsername.getUniqueId();
				boolean findCompanyFolder = s3Bucket.isValidFile(companyFolder1);
				if (findCompanyFolder == false) {
					s3Bucket.createFolder(companyFolder1);
					String yearfolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear();
					boolean findyearfolder = s3Bucket.isValidFile(yearfolder);
					if (findyearfolder == false) {
						s3Bucket.createFolder(yearfolder);
						String enterprisefolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Therapist";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}

						} else {
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}
						}
					} else {
						String enterprisefolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Therapist";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}

						} else {
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}
						}
					}
				} else {
					String yearfolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear();
					boolean findyearfolder = s3Bucket.isValidFile(yearfolder);
					if (findyearfolder == false) {
						s3Bucket.createFolder(yearfolder);
						String enterprisefolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Therapist";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}

						} else {
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}
						}
					} else {
						String enterprisefolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear()
								+ "Therapist";
						boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
						if (findEnterprisefolder == false) {
							s3Bucket.createFolder(enterprisefolder);
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}

						} else {
							String therapistFolder = dbadminUsername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
									+ "Therapist" + "/" + dbPerson.getFirstName()+dbPerson.getLastName() ;
							boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
							if (findtherapistFolder == false) {
								s3Bucket.createFolder(therapistFolder);
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							} else {
								s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
										therapistFolder + "/" + doctor.getSignaturePath(),
										new File(locationPath1 + doctor.getSignaturePath()))
												.withCannedAcl(CannedAccessControlList.PublicRead));
								dbdoctor.setSignaturePath(therapistFolder + "/" + doctor.getSignaturePath());
							}
						}
					}
				}
			} 
			personRepository.save(dbPerson);
			therapistRepository.save(dbdoctor);
		}

		else {
			// Facility Manager
			UserAccount userAccount = userAccountRepository.findByUsername(doctor.getEmail());
			if (userAccount == null) {
				throw new RuntimeException(ErrorMessageHandler.siteAdminDoesNotExists);
			}
			Person siteperson = personRepository.findByEmail(userAccount.getUsername());
			if (siteperson == null) {
				throw new RuntimeException(ErrorMessageHandler.siteAdminDoesNotExists);
			}
			if (doctor.getUserAccount().getRole().getRoleName().equals("Facility Manager")) {
				UserAccount user = userAccountRepository.findOne(doctor.getUserAccount().getId());
				user.setUsername(doctor.getEmail());
				Site site = siteRepository.findOne(doctor.getUserAccount().getSite().getId());
				user.setSite(site);
				user.setActive(doctor.isActive());

				siteperson.setFirstName(doctor.getFirstName());
				siteperson.setLastName(doctor.getLastName());
				siteperson.setAadharNumber(doctor.getAadharNumber());
				siteperson.setEmail(doctor.getEmail());
				siteperson.setMobileNumber(doctor.getMobileNumber());
				siteperson.setGender(doctor.getGender());
				siteperson.setActive(doctor.isActive());
				siteperson.setModifiedByUser(dbadminUsername.getUsername());
				siteperson.setModifiedDate(LocalDate.now());
				userAccountRepository.save(user);
				siteperson.setUserAccount(user);
				personRepository.save(siteperson);

			} else {
				// Super Admin
				UserAccount superAdminuserAccount = userAccountRepository.findByUsername(doctor.getEmail());
				if (superAdminuserAccount == null) {
					throw new RuntimeException(ErrorMessageHandler.superAdminDoesNotExists);
				}
				Person superAdmin = personRepository.findByEmail(userAccount.getUsername());
				if (superAdmin == null) {
					throw new RuntimeException(ErrorMessageHandler.superAdminDoesNotExists);
				}

				if (doctor.getUserAccount().getRole().getRoleName().equals("Super Admin")) {
					UserAccount user = userAccountRepository.findOne(superAdminuserAccount.getId());
					user.setUsername(doctor.getEmail());
					user.setActive(doctor.isActive());

					superAdmin.setFirstName(doctor.getFirstName());
					superAdmin.setLastName(doctor.getLastName());

					superAdmin.setEmail(doctor.getEmail());
					superAdmin.setMobileNumber(doctor.getMobileNumber());
					superAdmin.setGender(doctor.getGender());
					superAdmin.setActive(doctor.isActive());
					superAdmin.setModifiedDate(LocalDate.now());
					superAdmin.setAadharNumber(doctor.getAadharNumber());
					userAccountRepository.save(user);
					superAdmin.setUserAccount(user);
					personRepository.save(superAdmin);

				} else {
					// Admin User
					UserAccount adminuserAccount = userAccountRepository.findByUsername(doctor.getEmail());
					if (dbUserAccount == null) {
						throw new RuntimeException(ErrorMessageHandler.superAdminDoesNotExists);
					}
					Person admin = personRepository.findByEmail(userAccount.getUsername());
					if (dbPerson == null) {
						throw new RuntimeException(ErrorMessageHandler.superAdminDoesNotExists);
					}

					UserAccount user = userAccountRepository.findOne(doctor.getUserAccount().getId());
					user.setUsername(doctor.getEmail());
					user.setActive(doctor.isActive());

					admin.setFirstName(doctor.getFirstName());
					admin.setLastName(doctor.getLastName());

					admin.setEmail(doctor.getEmail());
					admin.setMobileNumber(doctor.getMobileNumber());
					admin.setGender(doctor.getGender());
					admin.setActive(doctor.isActive());
					admin.setAadharNumber(doctor.getAadharNumber());
					admin.setModifiedByUser(dbadminUsername.getUsername());
					admin.setModifiedDate(LocalDate.now());
					userAccountRepository.save(user);
					admin.setUserAccount(user);
					personRepository.save(admin);
				}
			}

		}

	}

	@Override
	public void deletePerson(Long id, String adminUsername) {
		Person dbPerson = personRepository.findOne(id);
		if (dbPerson == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		UserAccount dbadminUsername = userAccountRepository.findByUsername(adminUsername);
		if (dbadminUsername == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		/*
		 * UserAccount user =
		 * userAccountRepository.findOne(dbPerson.getUserAccount().getId());
		 * if(user.getRole().getRoleName().equals("Therapist")){
		 * List<Appointment>
		 * appointments=appointmentRepository.findByDoctor_IdAndActive(id,
		 * true); if(appointments.size()>0){ throw new
		 * RuntimeException("Therapist has Appointments!Please assign those appointments to another Therapist!"
		 * ); }
		 */
		UserAccount user = userAccountRepository.findOne(dbPerson.getUserAccount().getId());
		if (user.getRole().getRoleName().equals("Therapist")) {
			// List<Appointment>
			// appointments=appointmentRepository.findByDoctor_IdAndActive(id,
			// true);
			List<SubAppointment> subAppointments = subAppointmentRepository
					.findByDoctor_IdAndActiveAndStatusAndAppointmentStartedDateGreaterThanEqual(id, true,
							SubAppointmentStatus.AWAITING, LocalDate.now());
			if (subAppointments.size() > 0) {
				throw new RuntimeException(ErrorMessageHandler.pleaseAssignAppointmentsToAnotherTherapistBeforeInactivating);
			}
		}
		user.setActive(false);
		userAccountRepository.save(user);
		dbPerson.setActive(false);
		dbPerson.setModifiedDate(LocalDate.now());
		dbPerson.setModifiedByUser(dbadminUsername.getUsername());
		personRepository.save(dbPerson);

	}

	@Override
	public void addPerson_user(PersonDTO personDto) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		UserAccount dbusername = userAccountRepository.findByUsername(personDto.getUsername());
		Person dbPerson = personRepository.findByEmail(personDto.getUsername());
		
		String password =OTPGeneration.generateOTP();
		personDto.setPassword(password);
		
		dbPerson = personRepository.findByMobileNumber(personDto.getMobileNumber());
		if (dbusername != null || dbPerson != null) {
			throw new RuntimeException(ErrorMessageHandler.userAlreadyExists);
		} else {
			UserAccount adminUser = userAccountRepository.findByUsername(personDto.getAdminUserName());
			// Therpist
			if (personDto.getRole().getRoleName().equals("Therapist")) {
				UserAccount user = new UserAccount();
				user.setUsername(personDto.getUsername());
				user.setRole(personDto.getRole());
				user.setPassword(Base64Encoding.encodePassword(personDto.getPassword()));
				user.setActive(true);

				if (adminUser.getSite() != null) {
					user.setSite(adminUser.getSite());
					user.setUniqueId(adminUser.getUniqueId());
					user.setCompany(adminUser.getCompany());

					Address1 address = new Address1();
					address.setCountry(personDto.getCountry());
					address.setState(personDto.getState());
					address.setCity(personDto.getCity());
					address.setAddress1(personDto.getAddress1());
					address.setAddress2(personDto.getAddress2());
					address.setZipcode(personDto.getZipcode());

					Doctor doctor = new Doctor();
					doctor.setSite(adminUser.getSite());
					doctor.setCompany(adminUser.getCompany());
					doctor.setRciNumber(personDto.getRciNumber());
					doctor.setCreatedDate(LocalDate.now());
					doctor.setModifiedDate(LocalDate.now());
					doctor.setCreatedByUser(adminUser.getUsername());
					doctor.setModifiedByUser(adminUser.getUsername());
					doctor.setDesignation(personDto.getDesignation());
					doctor.setSpecialization(personDto.getSpecialization());
					doctor.setDepartment(personDto.getDepartment());
					doctor.setShortAutoBiography(personDto.getShortAutoBiography());
					doctor.setUserAccount(user);
					doctor.setFirstName(personDto.getFirstName());
					doctor.setLastName(personDto.getLastName());
					doctor.setAddress1(address);
					doctor.setEmail(personDto.getUsername());
					doctor= folderStructureCreation(personDto,doctor);
					doctor.setMobileNumber(personDto.getMobileNumber());
					doctor.setGender(personDto.getGender());
					doctor.setActive(true);
					doctor.setCreatedDate(LocalDate.now());
					doctor.setModifiedDate(LocalDate.now());
					doctor.setAadharNumber(personDto.getAadharNumber());

					userAccountRepository.save(user);
					doctor.setUserAccount(user);
					therapistRepository.save(doctor);
					Mail mail = new Mail();
					
					/*mail.postMail(personDto.getUsername(), "Registration Notification",
							"Your Account Registered Successfully By <b>" + adminUser.getCompany().getCompanyName()
									+ "</b><br><br>Your credentials are-  <br> <br> <b>USERNAME</b>:"
									+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()
									+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088'>Go to Our Company Website</a>");
				*/
					mail.postMail(personDto.getUsername(), "Registration Notification",
							"A new user has been created. Below are the details of the user and role"
									+ "</b> <br> <br> <b>Username</b>:"
									+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()+ "<br><b>Role</b>:" + personDto.getRole().getRoleName()
									+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088'>Go to Our Company Website</a>"+
									"<br><br><br><br>Cheers, <br>ezClinic Team.");
				} else {
					user.setCompany(adminUser.getCompany());
					user.setUniqueId(adminUser.getUniqueId());
					/*
					 * Address address = new Address();
					 * address.setCountry(personDto.getCountryName());
					 * address.setState(personDto.getStateName());
					 */
					Address1 address = new Address1();
					address.setCountry(personDto.getCountry());
					address.setState(personDto.getState());
					address.setCity(personDto.getCity());
					address.setAddress1(personDto.getAddress1());
					address.setAddress2(personDto.getAddress2());
					address.setZipcode(personDto.getZipcode());

					Doctor doctor = new Doctor();
					doctor.setCompany(adminUser.getCompany());
					doctor.setRciNumber(personDto.getRciNumber());
					doctor.setCreatedDate(LocalDate.now());
					doctor.setModifiedDate(LocalDate.now());
					doctor.setCreatedByUser(adminUser.getUsername());
					doctor.setModifiedByUser(adminUser.getUsername());
					doctor.setDesignation(personDto.getDesignation());
					doctor.setSpecialization(personDto.getSpecialization());
					doctor.setDepartment(personDto.getDepartment());
					doctor.setShortAutoBiography(personDto.getShortAutoBiography());
					doctor.setFirstName(personDto.getFirstName());
					doctor.setLastName(personDto.getLastName());
					doctor.setAddress1(address);
					doctor.setEmail(personDto.getUsername());
					doctor.setAadharNumber(personDto.getAadharNumber());
					
					doctor= folderStructureCreation(personDto,doctor);

					doctor.setMobileNumber(personDto.getMobileNumber());
					doctor.setGender(personDto.getGender());
					doctor.setActive(true);
					doctor.setCreatedDate(LocalDate.now());
					doctor.setModifiedDate(LocalDate.now());
					userAccountRepository.save(user);
					doctor.setUserAccount(user);

					therapistRepository.save(doctor);
					Mail mail = new Mail();
					/*mail.postMail(personDto.getUsername(), "Registration Notification",
							"Your Account Registered Successfully By <b>" + adminUser.getCompany().getCompanyName()
									+ "</b><br><br>Your crediantials are . <br> <br> <b>USERNAME</b>:"
									+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()
									+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088/'>Go to Our Company Website</a>");
				*/
					mail.postMail(personDto.getUsername(), "Registration Notification",
							"A new user has been created. Below are the details of the user and role"
									+ "</b> <br> <br> <b>Username</b>:"
									+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()+ "<br><b>Role</b>:" + personDto.getRole().getRoleName()
									+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088'>Go to Our Company Website</a>"+
									"<br><br><br><br>Cheers, <br>ezClinic Team.");	
				}

			}

			else {
				// Facility Manager
				if (personDto.getRole().getRoleName().equals("Facility Manager")) {
					UserAccount user = new UserAccount();
					user.setUsername(personDto.getUsername());
					user.setSite(personDto.getSite());
					user.setUniqueId(adminUser.getUniqueId());
					user.setPassword(Base64Encoding.encodePassword(personDto.getPassword()));
					user.setRole(personDto.getRole());
					user.setCompany(adminUser.getCompany());
					user.setActive(true);

					Person person1 = new Person();

					person1.setFirstName(personDto.getFirstName());
					person1.setLastName(personDto.getLastName());

					person1.setEmail(personDto.getUsername());

					person1.setMobileNumber(personDto.getMobileNumber());
					person1.setGender(personDto.getGender());
					person1.setCreatedDate(LocalDate.now());
					person1.setModifiedDate(LocalDate.now());
					person1.setCreatedByUser(adminUser.getUsername());
					person1.setModifiedByUser(adminUser.getUsername());
					person1.setActive(true);
					person1.setCreatedDate(LocalDate.now());
					person1.setModifiedDate(LocalDate.now());
					person1.setAadharNumber(personDto.getAadharNumber());
					userAccountRepository.save(user);
					person1.setUserAccount(user);
					personRepository.save(person1);

					Mail mail = new Mail();
					/*mail.postMail(personDto.getUsername(), "Registration Notification",
							"Your Account Registered Successfully By <b>" + adminUser.getCompany().getCompanyName()
									+ "</b><br><br>Your crediantials are . <br> <br> <b>USERNAME</b>:"
									+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()
									+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088/'>Go to Our Company Website</a>");
				*/
					mail.postMail(personDto.getUsername(), "Registration Notification",
							"A new user has been created. Below are the details of the user and role"
									+ "</b> <br> <br> <b>Username</b>:"
									+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()+ "<br><b>Role</b>:" + personDto.getRole().getRoleName()
									+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088'>Go to Our Company Website</a>"+
									"<br><br><br><br>Cheers, <br>ezClinic Team.");	
				} else {
					// Admin User
					UserAccount user = new UserAccount();
					user.setUsername(personDto.getUsername());
					user.setUniqueId(adminUser.getUniqueId());
					user.setPassword(Base64Encoding.encodePassword(personDto.getPassword()));
					user.setRole(personDto.getRole());
					// superior site adding to admin user
					if (adminUser.getSite() != null) {
						user.setSite(adminUser.getSite());
						user.setCompany(adminUser.getCompany());
						user.setActive(true);

						Person person1 = new Person();
						person1.setFirstName(personDto.getFirstName());
						person1.setLastName(personDto.getLastName());

						person1.setEmail(personDto.getUsername());
						person1.setCreatedDate(LocalDate.now());
						person1.setModifiedDate(LocalDate.now());
						person1.setCreatedByUser(adminUser.getUsername());
						person1.setModifiedByUser(adminUser.getUsername());
						person1.setMobileNumber(personDto.getMobileNumber());
						person1.setGender(personDto.getGender());
						person1.setActive(true);
						person1.setCreatedDate(LocalDate.now());
						person1.setModifiedDate(LocalDate.now());
						person1.setAadharNumber(personDto.getAadharNumber());
						userAccountRepository.save(user);
						person1.setUserAccount(user);
						personRepository.save(person1);

						Mail mail = new Mail();
						/*mail.postMail(personDto.getUsername(), "Registration Notification",
								"Your Account Registered Successfully By <b>" + adminUser.getCompany().getCompanyName()
										+ "</b><br><br>Your crediantials are . <br> <br> <b>USERNAME</b>:"
										+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()
										+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088/'>Go to Our Company Website</a>");
										*/
						
						mail.postMail(personDto.getUsername(), "Registration Notification",
								"A new user has been created. Below are the details of the user and role"
										+ "</b> <br> <br> <b>Username</b>:"
										+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()+ "<br><b>Role</b>:" + personDto.getRole().getRoleName()
										+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088'>Go to Our Company Website</a>"+
										"<br><br><br><br>Cheers, <br>ezClinic Team.");

					} else {
						user.setCompany(adminUser.getCompany());
						user.setActive(true);
						user.setUniqueId(adminUser.getUniqueId());
						Person person1 = new Person();
						person1.setFirstName(personDto.getFirstName());
						person1.setLastName(personDto.getLastName());

						person1.setEmail(personDto.getUsername());
						person1.setCreatedDate(LocalDate.now());
						person1.setModifiedDate(LocalDate.now());
						person1.setCreatedByUser(adminUser.getUsername());
						person1.setModifiedByUser(adminUser.getUsername());
						person1.setMobileNumber(personDto.getMobileNumber());
						person1.setGender(personDto.getGender());
						person1.setActive(true);
						person1.setCreatedDate(LocalDate.now());
						person1.setModifiedDate(LocalDate.now());
						person1.setAadharNumber(personDto.getAadharNumber());
						userAccountRepository.save(user);
						person1.setUserAccount(user);
						personRepository.save(person1);

						Mail mail = new Mail();
						/*mail.postMail(personDto.getUsername(), "Registration Notification",
								"Your Account Registered Successfully By <b>" + adminUser.getCompany().getCompanyName()
										+ "</b><br><br>Your crediantials are . <br> <br> <b>USERNAME</b>:"
										+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()
										+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088/'>Go to Our Company Website</a>");*/
						
						mail.postMail(personDto.getUsername(), "Registration Notification",
								"A new user has been created. Below are the details of the user and role"
										+ "</b> <br> <br> <b>Username</b>:"
										+ personDto.getUsername() + "<br><b>Password</b>:" + personDto.getPassword()+ "<br><b>Role</b>:" + personDto.getRole().getRoleName()
										+ "<br><br> <a href='http://ec2-54-183-128-252.us-west-1.compute.amazonaws.com:8088'>Go to Our Company Website</a>"+
										"<br><br><br><br>Cheers, <br>ezClinic Team.");
					}

				} // Admin User
			} // Facility Manager

		} // Therpist

	}

	@Override
	public int getAllRegistrations(String adminUsername) {
		int i = 0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		
		
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		System.out.println("Role Name "+dbUserAccount.getRole().getRoleName());
		if (dbUserAccount.getCompany() == null) {
			return 1;
		}
		List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
		if (dbUserAccount.getCompany() != null) {
			dbUseraccountList = userAccountRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		}
		
		if (dbUserAccount.getSite() != null && dbUserAccount.getRole().getRoleName().equals("Facility Manager")) {
			dbUseraccountList = userAccountRepository.findBySite_Id(dbUserAccount.getSite().getId());
		}
		List<Person> persons = new ArrayList<Person>();
		for (UserAccount userAccount : dbUseraccountList) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);

		}
		i = persons.size();
		return i;// TODO Auto-generated method stub

	}

	@Override
	public int getAllActiveAdmins(String adminUsername) {
		int i = 0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Role role = roleRepository.findByRoleName("Admin");
		if (role == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		Role role1 = roleRepository.findByRoleName(ErrorMessageHandler.superAdmin);
		if (role1 == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		if (dbUserAccount.getCompany() == null) {
			return i;
		}
		List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
		dbUseraccountList = userAccountRepository
				.findByCompany_IdAndRole_IdAndActive(dbUserAccount.getCompany().getId(), role.getId(), true);
		List<UserAccount> useraccounts = userAccountRepository
				.findByCompany_IdAndRole_IdAndActive(dbUserAccount.getCompany().getId(), role1.getId(), true);

		List<Person> persons = new ArrayList<Person>();
		for (UserAccount userAccount : useraccounts) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);
		}
		for (UserAccount userAccount : dbUseraccountList) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);

		}
		i = persons.size();
		return i;
	}

	@Override
	public int getAllInActiveAdmins(String adminUsername) {
		int i = 0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Role role = roleRepository.findByRoleName("Admin");
		if (role == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		Role role1 = roleRepository.findByRoleName("Super Admin");
		if (role == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		if (dbUserAccount.getCompany() == null) {
			return i;
		}
		List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
		dbUseraccountList = userAccountRepository
				.findByCompany_IdAndRole_IdAndActive(dbUserAccount.getCompany().getId(), role.getId(), false);
		List<UserAccount> useraccounts = userAccountRepository
				.findByCompany_IdAndRole_IdAndActive(dbUserAccount.getCompany().getId(), role1.getId(), false);

		List<Person> persons = new ArrayList<Person>();
		for (UserAccount userAccount : useraccounts) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);
		}
		for (UserAccount userAccount : dbUseraccountList) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);

		}
		i = persons.size();
		return i;
	}

	@Override
	public int getAllInActiveSiteAdmins(String adminUsername) {
		int i = 0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Role role = roleRepository.findByRoleName("Facility Manager");
		if (role == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		if (dbUserAccount.getCompany() == null) {
			return i;
		}
		List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
		dbUseraccountList = userAccountRepository
				.findByCompany_IdAndRole_IdAndActive(dbUserAccount.getCompany().getId(), role.getId(), false);
		List<Person> persons = new ArrayList<Person>();
		for (UserAccount userAccount : dbUseraccountList) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);
		}
		i = persons.size();
		return i;
	}

	@Override
	public int getAllActiveSiteAdmins(String adminUsername) {
		int i = 0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Role role = roleRepository.findByRoleName("Facility Manager");
		if (role == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		if (dbUserAccount.getCompany() == null) {
			return i;
		}
		List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
		dbUseraccountList = userAccountRepository
				.findByCompany_IdAndRole_IdAndActive(dbUserAccount.getCompany().getId(), role.getId(), true);

		List<Person> persons = new ArrayList<Person>();
		for (UserAccount userAccount : dbUseraccountList) {
			Person person = personRepository.findByUserAccount_Id(userAccount.getId());
			persons.add(person);
		}
		i = persons.size();
		return i;
	}

	
	
	@Override
	public Page<Person> getAllPersonsBySuperAdminAndAdmin(String adminUsername, String roleName, Boolean active,
			int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Person> persons = personRepository.findByUserAccount_Company_IdAndUserAccount_Role_RoleNameAndActive(
				dbUserAccount.getCompany().getId(), roleName, active,
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		List<Person> persons1 = personRepository.findByUserAccount_Company_IdAndUserAccount_Role_RoleNameAndActive(
				dbUserAccount.getCompany().getId(), "Admin", active,
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		List<Person> personsList = new ArrayList<Person>();
		personsList.addAll(persons);
		personsList.addAll(persons1);
		return new PageImpl<Person>(personsList, new PageRequest(page, size), personsList.size());
	}
	
	public List<Person> getActiveAndInactivePersons(String adminUsername){
		List<Person> activePersons=new ArrayList<>();
		
		List<Person> persons =getAllPersons(adminUsername);
		for(Person person:persons){
			if(person.isActive()==true){
				activePersons.add(person);
			}
		}
		
		return activePersons;
	}
	
	public List<Person> getInactivePersons(String adminUsername){
		List<Person> inActivePersons=new ArrayList<>();
		List<Person> persons =getAllPersons(adminUsername);
		for(Person person:persons){
			if(person.isActive()==false){
				inActivePersons.add(person);
			}
		}
		return inActivePersons;
	}
	@Override
	public int getInactivePersonsCount(String adminUsername){
		List<Person> inActivePersons =getInactivePersons(adminUsername);
		return inActivePersons.size();
	}
	@Override
	public int getActivePersonsCount(String adminUsername){
		List<Person> activePersons =getActiveAndInactivePersons(adminUsername);
		return activePersons.size();
	}
	
	@Override
	public Page<Person> getAllActivePersonsByRegType(String adminUsername,int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Person> persons =getActiveAndInactivePersons(adminUsername);
		
		
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > persons.size() ? persons.size()
				: (start + new PageRequest(page, size).getPageSize());

		return new PageImpl<Person>(persons.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), persons.size());
	}
	
	@Override
	public Page<Person> getAllInActivePersonsByRegType(String adminUsername,int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Person> persons =getInactivePersons(adminUsername);
		
		
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > persons.size() ? persons.size()
				: (start + new PageRequest(page, size).getPageSize());

		return new PageImpl<Person>(persons.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), persons.size());
	}
	
	@Override

	public Page<Person> getAllPersonsByTherapists(String adminUsername, String roleName, Boolean active, int page,
			int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Page<Person> persons = null;
		persons = personRepository.findByUserAccount_Company_IdAndActiveAndUserAccount_Role_RoleName(
				dbUserAccount.getCompany().getId(), active, roleName,
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return persons;
	}

	@Override
	public Person getOnePerson(Long id) {
		return personRepository.findOne(id);
	}

	@Override
	public List<Person> getAllusersBySuperAdminAndAdmin(String adminUsername, String roleName, Boolean active) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Person> persons = personRepository.findByUserAccount_Company_IdAndUserAccount_Role_RoleNameAndActive(
				dbUserAccount.getCompany().getId(), roleName, active);
		List<Person> persons1 = personRepository.findByUserAccount_Company_IdAndUserAccount_Role_RoleNameAndActive(
				dbUserAccount.getCompany().getId(), "Admin", active);
		List<Person> personsList = new ArrayList<Person>();
		personsList.addAll(persons);
		personsList.addAll(persons1);
		return personsList;
	}

	@Override
	public void addUserTherapist(PersonDTO personDto) throws FileNotFoundException, IOException {
		Doctor oldDoctor = therapistRepository.findOne(personDto.getOldTherapistId());
		System.out.println(oldDoctor.getEmail());
		Role dbRole = roleRepository.findByRoleName(personDto.getRole().getRoleName());
		personDto.setRole(dbRole);
		// please cmnt this
		addPerson_user(personDto);
		Doctor newDoctor = therapistRepository.findByEmail(personDto.getUsername());
		System.out.println(newDoctor.getEmail());
		System.out.println(newDoctor.getId());
		List<Schedule> schedules = scheduleRepository.findByDoctor_Id(oldDoctor.getId());
		for (Schedule schedule : schedules) {
			/*
			 * Address address = null;
			 * address.setCountry(personDto.getCountryName());
			 * address.setState(personDto.getStateName());
			 */

			// newDoctor.setAddress();;
			schedule.setDoctor(newDoctor);/////
			// error
			scheduleRepository.save(schedule);
		}
		List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(oldDoctor.getId(), true);
		for (Appointment appointment : appointments) {
			List<SubAppointment> subAppointments = subAppointmentRepository.findByAppointment_Id(appointment.getId());
			boolean isPatientHasAwaitApps = false;
			for (SubAppointment subAppointment : subAppointments) {
				System.out.println(subAppointment.getPatient().getEmail());
				System.out.println(subAppointment.getAppointmentStartedDate());
				if (subAppointment.getStatus().equals(SubAppointmentStatus.AWAITING)
						&& subAppointment.getAppointmentStartedDate().isAfter(LocalDate.now())) {
					subAppointment.setDoctor(newDoctor);
					subAppointmentRepository.save(subAppointment);
					isPatientHasAwaitApps = true;
				}
			}
			if (isPatientHasAwaitApps == true) {
				appointment.setDoctor(newDoctor);
				appointmentRepository.save(appointment);
				List<Payment> dbPaymentsList = paymentRepository.findByPatient_Id(appointment.getPatient().getId());
				Payment payment = null;
				for (Payment dbPayment : dbPaymentsList) {					
					payment = dbPayment;
					System.out.println(payment);
				}
				// error
				payment.setDoctor(newDoctor);
				paymentRepository.save(payment);
			}
		}
		oldDoctor.setActive(false);
		Person dbPerson = personRepository.findOne(oldDoctor.getId());
		if (dbPerson == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		UserAccount user = userAccountRepository.findOne(oldDoctor.getUserAccount().getId());
		user.setActive(false);
		userAccountRepository.save(user);
		dbPerson.setActive(false);
		
		personRepository.save(dbPerson);
	}

	@Override
	public void activeUser(Person person) {
		// TODO Auto-generated method stub
		UserAccount dbusername = userAccountRepository.findByUsername(person.getEmail());
		Person dbPerson = personRepository.findByEmail(person.getEmail());
		
		dbPerson.setActiveStatus(person.getActiveStatus());
		
		if(person.getActiveStatus().equals(ActiveStatus.ACTIVE)){
			dbPerson.setActive(true);
		}else{
			dbPerson.setActive(false);
		}
		dbPerson.setCreatedDate(LocalDate.now());
		personRepository.save(dbPerson);
		
		if(person.getActiveStatus().equals(ActiveStatus.ACTIVE)){
			dbusername.setActive(true);
		}else{
			dbusername.setActive(false);
		}
		//dbusername.setActive(person.isActive());
		userAccountRepository.save(dbusername);
		if (dbPerson.getActiveStatus().equals(ActiveStatus.INACTIVE)) {
			Mail mail = new Mail();
			System.out.println(person.getModifiedByUser());
			mail.postMail(person.getModifiedByUser(), "Company InActivated !",
					ErrorMessageHandler.inactiveCompanyUserCanNotBeLoggedIn);
		}

	}
	
	public Doctor folderStructureCreation(PersonDTO personDto,Doctor doctor){
		
		UserAccount dbusername = userAccountRepository.findByUsername(personDto.getAdminUserName());

		S3Bucket s3Bucket = new S3Bucket();
		AWSCredentials credentials = new BasicAWSCredentials(AccessS3Bucket.keyName, AccessS3Bucket.securityKey);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		if (personDto.getSignaturePath() != null) {
			String locationPath1 = fileConfig.getLocationpath().replace("/", File.separator);
			String locationPath = AccessS3Bucket.bucketName.replace("/", File.separator);

			String companyFolder1 = dbusername.getUniqueId();
			boolean findCompanyFolder = s3Bucket.isValidFile(companyFolder1);
			if (findCompanyFolder == false) {
				s3Bucket.createFolder(companyFolder1);
				String yearfolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear();
				boolean findyearfolder = s3Bucket.isValidFile(yearfolder);
				if (findyearfolder == false) {
					s3Bucket.createFolder(yearfolder);
					String enterprisefolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear()
							+ "Therapist";
					boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
					if (findEnterprisefolder == false) {
						s3Bucket.createFolder(enterprisefolder);
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}

					} else {
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}
					}
				} else {
					String enterprisefolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear()
							+ "Therapist";
					boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
					if (findEnterprisefolder == false) {
						s3Bucket.createFolder(enterprisefolder);
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}

					} else {
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}
					}
				}
			} else {
				String yearfolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear();
				boolean findyearfolder = s3Bucket.isValidFile(yearfolder);
				if (findyearfolder == false) {
					s3Bucket.createFolder(yearfolder);
					String enterprisefolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear()
							+ "Therapist";
					boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
					if (findEnterprisefolder == false) {
						s3Bucket.createFolder(enterprisefolder);
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}

					} else {
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}
					}
				} else {
					String enterprisefolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear()
							+ "Therapist";
					boolean findEnterprisefolder = s3Bucket.isValidFile(enterprisefolder);
					if (findEnterprisefolder == false) {
						s3Bucket.createFolder(enterprisefolder);
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}

					} else {
						String therapistFolder = dbusername.getUniqueId() + "/" + LocalDate.now().getYear() + "/"
								+ "Therapist" + "/" + personDto.getFirstName()+personDto.getLastName() ;
						boolean findtherapistFolder = s3Bucket.isValidFile(therapistFolder);
						if (findtherapistFolder == false) {
							s3Bucket.createFolder(therapistFolder);
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						} else {
							s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
									therapistFolder + "/" + personDto.getSignaturePath(),
									new File(locationPath1 + personDto.getSignaturePath()))
											.withCannedAcl(CannedAccessControlList.PublicRead));
							doctor.setSignaturePath(therapistFolder + "/" + personDto.getSignaturePath());
						}
					}
				}
			}
		} /*else {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectAnImage);
		}*/
		return doctor;
	}

	@Override
	public Page<Person> getAllPersonsByLoginUser(String adminUsername, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Page<Person> persons =	personRepository.findByModifiedByUserAndUserAccount_Company_Id(adminUsername,dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		
		/*
		 * if (dbUserAccount.getCompany() != null) { persons =
		 * personRepository.findByUserAccount_Company_Id(dbUserAccount.getCompany().
		 * getId(), new PageRequest(page, size, Sort.Direction.DESC, "id")); } else {
		 * persons = personRepository.findByUserAccount_Id(dbUserAccount.getId(), new
		 * PageRequest(page, size, Sort.Direction.DESC, "id")); }
		 */
		 
		return persons;
	}
	@Override
	public Page<Person> getAllActiveUsersByLoginUser(String adminUsername,Boolean active, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Page<Person> persons =	personRepository.findByModifiedByUserAndActiveAndUserAccount_Company_Id(adminUsername,active, dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		 
		return persons;
	}

	@Override
	public int getActivePersonsCountByCompanyAndSite(String adminUsername) {
		List<Person> activePersonsByCompanyAndSite =getActiveAndInactivePersons(adminUsername);
		return activePersonsByCompanyAndSite.size();
	}

}
