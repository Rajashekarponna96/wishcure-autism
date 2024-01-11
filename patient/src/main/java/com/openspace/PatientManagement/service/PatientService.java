package com.openspace.PatientManagement.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.openspace.Model.Config.FileConfig;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.AppointmentInvoice;
import com.openspace.Model.DoctorManagement.AppointmentInvoiceTemplate;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Document;
import com.openspace.Model.DoctorManagement.DocumentType;
import com.openspace.Model.DoctorManagement.Insurance;
import com.openspace.Model.DoctorManagement.Items;
import com.openspace.Model.DoctorManagement.ItemsTemplate;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.PrivateClient;
import com.openspace.Model.DoctorManagement.RegionalCenter;
import com.openspace.Model.DoctorManagement.School;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.DoctorManagement.TherapyAddress;
import com.openspace.Model.Dto.CalAmountDto;
import com.openspace.Model.Dto.ChargeDto1;
import com.openspace.Model.Dto.CycleDto;
import com.openspace.Model.Dto.InvoiceStipeDto;
import com.openspace.Model.Dto.MchatAssessmentDto;
import com.openspace.Model.Dto.PaidAmountDto;
import com.openspace.Model.Dto.PaidStatus;
import com.openspace.Model.Dto.PatientBillingDto;
import com.openspace.Model.Dto.PatientInvoiceDto;
import com.openspace.Model.Dto.PatientOb;
import com.openspace.Model.Dto.PaymentDashBoardDto;
import com.openspace.Model.Dto.SearchPatientDto;
import com.openspace.Model.Dto.Sessions;
import com.openspace.Model.Dto.SubAppointmentDashboardDto;
import com.openspace.Model.Dto.TotalPaymentDashBoard;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.ClientType;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.Lookups.DocumentTypeLookup;
import com.openspace.Model.Lookups.RegionalCenterZoneLookup;
import com.openspace.Model.Lookups.SubCategoryType;
import com.openspace.Model.Parent.Folder;
import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.ParentModule.Mchart.MchartLookup;
import com.openspace.Model.PatientMgnt.Repositories.AppointmentInvoiceRpository;
import com.openspace.Model.PatientMgnt.Repositories.AppointmentInvoiceTemplateRpository;
import com.openspace.Model.PatientMgnt.Repositories.AppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.CategoryTypeServiceRepository;
import com.openspace.Model.PatientMgnt.Repositories.CityRepository;
import com.openspace.Model.PatientMgnt.Repositories.CountryRepository;
import com.openspace.Model.PatientMgnt.Repositories.DepartmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.DocumentTypeLookupRepository;
import com.openspace.Model.PatientMgnt.Repositories.FolderRepository;
import com.openspace.Model.PatientMgnt.Repositories.InsuranceRepository;
import com.openspace.Model.PatientMgnt.Repositories.ItemsRepository;
import com.openspace.Model.PatientMgnt.Repositories.ItemsTemplateRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRegistrationTypeRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PaymentRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.PrivateClientRepository;
import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterRepository;
//import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterRepository;
import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterZoneLookupRepository;
import com.openspace.Model.PatientMgnt.Repositories.SchoolRepository;
import com.openspace.Model.PatientMgnt.Repositories.StateRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripePaymentRepository;
import com.openspace.Model.PatientMgnt.Repositories.SubAppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapyAddressRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.Stripe.StripePayment;
import com.openspace.Model.Stripe.Transaction;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.spec.SubAppointmentSpec;
import com.openspace.PatientManagement.controller.DocumentRepository;
import com.openspace.PatientManagement.controller.PaymentController;
import com.openspace.PatientManagement.dto.InvoiceDto;
import com.openspace.PatientManagement.dto.PatientInfoDto;
import com.openspace.PatientManagement.s3.AccessS3Bucket;
import com.openspace.PatientManagement.s3.S3Bucket;
import com.openspace.PatientManagement.s3.S3LocatioDto;
import com.openspace.PatientManagement.spec.PatientSpec;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private RegionalCenterZoneLookupRepository regionalCenterZoneRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private PrivateClientRepository privateClientRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private TherapistRepository therapistRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private FileConfig fileConfig;

	@Autowired
	private RegionalCenterRepository regionalCenterRepository;

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SubAppointmentRepository subAppointmentRepository;

	@Autowired
	private TherapyAddressRepository therapyAddressRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AppointmentInvoiceRpository appointmentInvoiceRpository;

	@Autowired
	private StripePaymentRepository stripePaymentRepository;

	// private String filePathLocation = fileConfig.getLocationpath();
	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private AppointmentInvoiceTemplateRpository appointmentInvoiceTemplateRpository;

	@Autowired
	private ItemsTemplateRepository itemsTemplateRepository;

	@Autowired
	private DocumentTypeLookupRepository documentTypeLookupRepository;
	
	@Autowired
	PatientRegistrationTypeRepository patientRegistrationTypeRepository;
	
	@Autowired
	CategoryTypeServiceRepository categoryTypeServiceRepository;
	
	
	
	

	private Path pathLocation = Paths.get("upload-dir");

	public void add(Patient patient) throws FileNotFoundException, IOException {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(patient.getAdminUser());
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		for (Payment payment : patient.getPayments()) {
			if (payment == null || payment.getModeOfPaymentTypes() == null || payment.getAssignedAmount() == null) {
				throw new RuntimeException("Please Select the mode of payment and Number of sessions in Payments!");
			}
		}

		if (patient.getEmail() != null) {
			Person dbperson = personRepository.findByEmail(patient.getEmail());
			if (dbperson != null) {
				throw new RuntimeException("Please, Choose Different EmailId");
			}
		}
		Random random = new Random();
		String number = String.valueOf(random.nextDouble() * 1000000);
		String number1 = number.substring(0, 5);
		String patientUniqueId;
		if (dbUserAccount.getCompany() != null) {
			String companyTag = dbUserAccount.getCompany().getCompanyName().substring(0, 3);
			patientUniqueId = companyTag.toUpperCase() + number1;
			System.out.println("Reg Number is -->"+patientUniqueId);
		} else {
			Random random2 = new Random();
			patientUniqueId = "PA_" + random2.nextInt();
		}
		patient.setUniqueId(patientUniqueId);
		if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
			Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
			if (person == null) {
				throw new RuntimeException("Individual Doesnot Exists");
			}
			Doctor dbdoctor = therapistRepository.findOne(person.getId());
			if (dbdoctor == null) {
				throw new RuntimeException("Individual Doesnot Exists");
			}
			
			if (patient.getAddress() != null) {
				Address1 address = new Address1();
				address.setAddress1(patient.getAddress().getAddress1());
				address.setAddress2(patient.getAddress().getAddress2());
				address.setCountry(patient.getAddress().getCountry());
				address.setState(patient.getAddress().getState());
				address.setCity(patient.getAddress().getCity());
				address.setZipcode(patient.getAddress().getZipcode());
				patient.setAddress(address);
			}
			patient.setActive(patient.isActive());
			patient.setFullName(patient.getFirstName() + " " + patient.getLastName());
			patient.setCreatedDate(LocalDate.now());
			patient.setModifiedDate(LocalDate.now());
			patient.setCreatedByUser(dbUserAccount.getUsername());
			patient.setModifiedByUser(dbUserAccount.getUsername());
			patient.setCompanyUniqueId(dbUserAccount.getUniqueId());
			patient.setAdminUser(dbUserAccount.getUsername());
			
			patient.setCreatedDate(LocalDate.now());
			patient.setMobileNumber(patient.getMobileNumber());
			patient.setReasonForReferral(patient.getReasonForReferral());
			patient.setRegDate(LocalDate.now());
			patient.setReferredBy(patient.getReferredBy());
			patient.setRegNumber(patient.getRegNumber());
			patient.setMotherTongue(patient.getMotherTongue());
			patient.setLanguagesKnown(patient.getLanguagesKnown());
			patient.setMotherName(patient.getMotherName());
			patient.setSiblings(patient.getSiblings());
			patient.setPatientNote(patient.getPatientNote());

			if (patient.getRegionalCenter() != null) {
				if (patient.getRegionalCenter().getId() != null) {
					RegionalCenter dbRegionalCenter = regionalCenterRepository
							.findOne(patient.getRegionalCenter().getId());
					if (dbRegionalCenter == null) {
						throw new RuntimeException("No Regional Center Found..!!");
					}
					patient.setRegionalCenter(dbRegionalCenter);
					patient.setServiceCoordinatorName(patient.getServiceCoordinatorName());
					patient.setPhoneNumber(patient.getPhoneNumber());
					patient.setEmailPatient(patient.getEmailPatient());
				}
			}

			if (patient.getSchool() != null) {
				if (patient.getSchool().getId() != null) {
					School dbSchool = schoolRepository.findOne(patient.getSchool().getId());
					if (dbSchool == null) {
						throw new RuntimeException("No School Found..!!");
					}
					patient.setSchool(dbSchool);
					patient.setContactName(patient.getContactName());
					patient.setPhoneNumber(patient.getPhoneNumber());
					patient.setEmailPatient(patient.getEmailPatient());
				}
			}
			if (patient.getInsurance() != null) {
				if (patient.getInsurance().getId() != null) {
					Insurance dbInsurance = insuranceRepository.findOne(patient.getInsurance().getId());
					if (dbInsurance == null) {
						throw new RuntimeException("No Insurance Found..!!");
					}
					patient.setInsurance(dbInsurance);
					patient.setPatientInsuraceNo(patient.getPatientInsuraceNo());
					patient.setGroupNumber(patient.getGroupNumber());
					patient.setPlanCode(patient.getPlanCode());
				}
			}
			if (patient.getTherapyAddress() != null) {
				TherapyAddress therapyAddress = new TherapyAddress();

				therapyAddress.setAddress1(patient.getTherapyAddress().getAddress1());
				therapyAddress.setAddress2(patient.getTherapyAddress().getAddress2());
				therapyAddress.setCountry(patient.getTherapyAddress().getCountry());
				therapyAddress.setState(patient.getTherapyAddress().getState());
				therapyAddress.setCity(patient.getTherapyAddress().getCity());
				therapyAddress.setZipcode(patient.getTherapyAddress().getZipcode());
				therapyAddressRepository.save(therapyAddress);
				patient.setTherapyAddress(therapyAddress);
			}

			/*
			 * List<Department> dbdepartment = departmentRepository
			 * .findByDepartmentName(patient.getDepartments().get(0).getDepartmentName());
			 * if (dbdepartment != null) { patient.setDepartments(dbdepartment); }
			 */
			
			PatientRegistrationType dbpRegistartionType = patientRegistrationTypeRepository.findByName(patient.getPatientRegistrationType().getName());
			
			if(dbpRegistartionType != null)
			{
				CategoryType dbcaCategoryType = categoryTypeServiceRepository.findByName(patient.getCategoryType().getName());
				
				if(dbcaCategoryType !=null)
				{
					List<SubCategoryType> subcats = dbcaCategoryType.getSubCategorys();
					
					dbcaCategoryType.setSubCategorys(subcats);
					
					patient.setCategoryType(dbcaCategoryType);
				}
				
				patient.setPatientRegistrationType(dbpRegistartionType);
			}
			
			
					
			

			patientRepository.save(patient);
			List<Payment> payments = patient.getPayments();
			System.out.println(payments.size());
			for (Payment payment : payments) {
				System.out.println("Individual"+payment.getId());
				Payment dbPayment = paymentRepository.findOne(payment.getId());
				dbPayment.setMonth(LocalDate.now().getMonth());
				dbPayment.setYear(LocalDate.now().getYear());
				dbPayment.setNoOfCycles(0);
				dbPayment.setPatient(patient);
				dbPayment.setPaymentId(payment.getPaymentId());
				dbPayment.setPerSessionCost(payment.getPerSessionCost());
				paymentRepository.save(dbPayment);
			}
			if (patient.getEmail() != null) {
				Mail mail = new Mail();
			/*	mail.postMail(patient.getEmail(), "Registration Notification",
						"Your Account Registered Successfully By <b>" + dbdoctor.getFirstName() + "");*/
				
				mail.postMail(patient.getEmail(), "Registration Notification",
						"A new  patient has been registered successfully in the system. If you need to update any details, please login in back to the application and lookup the patient through Patient List option and update the record. " 
                 +"<br><br><br><br>Cheers, <br>ezClinic Team.");

			}
		} else {
			Company dbCompany = dbUserAccount.getCompany();
			if (dbCompany == null) {
				throw new RuntimeException("Company Does not Exist ");
			}
			Address1 address = new Address1();

			if (patient.getAddress1() != null) {
				address.setAddress1(patient.getAddress1().getAddress1());
				address.setAddress2(patient.getAddress1().getAddress2());
				address.setCountry(patient.getAddress1().getCountry());
				address.setState(patient.getAddress1().getState());
				address.setCity(patient.getAddress1().getCity());
				address.setZipcode(patient.getAddress1().getZipcode());
				patient.setAddress1(address);
			}
			patient.setActive(true);
			patient.setCompany(dbCompany);
			patient.setFullName(patient.getFirstName() + " " + patient.getLastName());
			patient.setCreatedDate(LocalDate.now());
			patient.setModifiedDate(LocalDate.now());
			patient.setCreatedByUser(dbUserAccount.getUsername());
			patient.setModifiedByUser(dbUserAccount.getUsername());
			patient.setCompanyUniqueId(dbUserAccount.getUniqueId());
			
			patient.setCreatedDate(LocalDate.now());
			patient.setMobileNumber(patient.getMobileNumber());
			patient.setReasonForReferral(patient.getReasonForReferral());
			patient.setRegDate(LocalDate.now());
			patient.setReferredBy(patient.getReferredBy());
			patient.setRegNumber(patient.getRegNumber());
			patient.setLanguagesKnown(patient.getLanguagesKnown());
			patient.setMotherTongue(patient.getMotherTongue());
			patient.setMotherName(patient.getMotherName());
			patient.setSiblings(patient.getSiblings());
			patient.setPatientNote(patient.getPatientNote());

			if (patient.getRegionalCenter() != null) {
				if (patient.getRegionalCenter().getId() != null) {
					RegionalCenter dbRegionalCenter = regionalCenterRepository
							.findOne(patient.getRegionalCenter().getId());
					if (dbRegionalCenter == null) {
						throw new RuntimeException("No Regional Center Found..!!");
					}
					patient.setRegionalCenter(dbRegionalCenter);
					patient.setServiceCoordinatorName(patient.getServiceCoordinatorName());
					patient.setPhoneNumber(patient.getPhoneNumber());
					patient.setEmailPatient(patient.getEmailPatient());
				}
			}

			if (patient.getSchool() != null) {
				if (patient.getSchool().getId() != null) {
					School dbSchool = schoolRepository.findOne(patient.getSchool().getId());
					if (dbSchool == null) {
						throw new RuntimeException("No School Found..!!");
					}
					patient.setSchool(dbSchool);
					patient.setContactName(patient.getContactName());
					patient.setPhoneNumber(patient.getPhoneNumber());
					patient.setEmailPatient(patient.getEmailPatient());
				}
			}

			if (patient.getInsurance() != null) {
				if (patient.getInsurance().getId() != null) {
					Insurance dbInsurance = insuranceRepository.findOne(patient.getInsurance().getId());
					if (dbInsurance == null) {
						throw new RuntimeException("No Insurance Found..!!");
					}
					patient.setInsurance(dbInsurance);
					patient.setPatientInsuraceNo(patient.getPatientInsuraceNo());
					patient.setGroupNumber(patient.getGroupNumber());
					patient.setPlanCode(patient.getPlanCode());

				}
			}
			if (patient.getTherapyAddress() != null) {
				TherapyAddress therapyAddress = new TherapyAddress();

				therapyAddress.setAddress1(patient.getTherapyAddress().getAddress1());
				therapyAddress.setAddress2(patient.getTherapyAddress().getAddress2());
				therapyAddress.setCountry(patient.getTherapyAddress().getCountry());
				therapyAddress.setState(patient.getTherapyAddress().getState());
				therapyAddress.setCity(patient.getTherapyAddress().getCity());
				therapyAddress.setZipcode(patient.getTherapyAddress().getZipcode());
				therapyAddressRepository.save(therapyAddress);
				patient.setTherapyAddress(therapyAddress);
			}

			/*
			 * List<Department> dbdepartment =
			 * departmentRepository.findByDepartmentNameAndCompany_Id(
			 * patient.getDepartments().get(0).getDepartmentName(),
			 * dbUserAccount.getCompany().getId()); if (dbdepartment != null) {
			 * patient.setDepartments(dbdepartment); }
			 */

			// assign lookup folders to patient folders....
			List<DocumentTypeLookup> lookupFolders = (List<DocumentTypeLookup>) documentTypeLookupRepository.findAll();
			List<Folder> foldersList = new ArrayList<Folder>();
			if (lookupFolders.size() > 0) {
				for (DocumentTypeLookup documentTypeLookup : lookupFolders) {
					Folder folder1 = folderRepository.findByNameAndPatient_Id(documentTypeLookup.getFolderName(),
							patient.getId());
					if (folder1 == null) {
						Folder folder = new Folder();
						if (documentTypeLookup.getDescription() != null) {
							folder.setDescription(documentTypeLookup.getDescription());
						}
						if (documentTypeLookup.getFolderName() != null) {
							folder.setName(documentTypeLookup.getFolderName());
						}
						folder.setPatient(patient);
						// addFolder(folder);
						foldersList.add(folder);
					}
				}
			}
			patient.setFolders(foldersList);
			
			PatientRegistrationType dbpRegistartionType = patientRegistrationTypeRepository.findByName(patient.getPatientRegistrationType().getName());
			
			if(dbpRegistartionType != null && patient.getCategoryType() != null)
			{
				CategoryType dbcaCategoryType = categoryTypeServiceRepository.findByName(patient.getCategoryType().getName());
				
				if(dbcaCategoryType !=null)
				{
					patient.setCategoryType(dbcaCategoryType);
				}
				
				patient.setPatientRegistrationType(dbpRegistartionType);
			}
			

			patientRepository.save(patient);

			List<Payment> payments = patient.getPayments();
			System.out.println(payments.size());
			for (Payment payment : payments) {
				System.out.println("Enterprise payment id "+payment.getId());
				Payment dbPayment = paymentRepository.findOne(payment.getId());
				dbPayment.setMonth(LocalDate.now().getMonth());
				dbPayment.setYear(LocalDate.now().getYear());
				dbPayment.setNoOfCycles(0);
				dbPayment.setPatient(patient);
				paymentRepository.save(dbPayment);
			}

			if (patient.getEmail() != null) {
				Mail mail = new Mail();
				/*mail.postMail(patient.getEmail(), "Registration Notification",
						"Your Account Registered Successfully By <b>" + dbCompany.getCompanyName()
								+ "<br><br> <a href='http://103.255.146.157:8080/healthapp/'>Go to Our Company Website</a>");
*/
				mail.postMail(patient.getEmail(), "Registration Notification",
						"A new  patient has been registered successfully in the system. If you need to update any details, please login in back to the application and lookup the patient through Patient List option and update the record. " 
                 +"<br><br><br><br>Cheers, <br>ezClinic Team.");

			}

		}
	}

	public void createDirectory(Company company, Patient patient, Document document) throws IOException {
		File companyfolder = new File("upload-dir", company.getCompanyName());
		if (!companyfolder.exists()) {
			if (companyfolder.mkdir()) {
				File patientfolder = new File(companyfolder, "Patient");
				if (!patientfolder.exists()) {
					if (patientfolder.mkdirs()) {
						File UCIfolder = new File(patientfolder, patient.getUcl());
						if (!UCIfolder.exists()) {
							if (UCIfolder.mkdir()) {
								Path fileLocationPath = Paths.get(UCIfolder.getPath());
								FileOutputStream outputStream = new FileOutputStream("upload-dir" + File.separator
										+ company.getCompanyName() + File.separator + "Patient" + File.separator
										+ patient.getUcl() + File.separator + document.getPath());
								Files.copy(pathLocation.resolve(document.getPath()), outputStream);
								document.setPath("upload-dir" + File.separator + company.getCompanyName()
										+ File.separator + "Patient" + File.separator + patient.getUcl()
										+ File.separator + document.getPath());
							}
						} else {
							Path fileLocationPath = Paths.get(UCIfolder.getPath());
							FileOutputStream outputStream = new FileOutputStream("upload-dir" + File.separator
									+ company.getCompanyName() + File.separator + "Patient" + File.separator
									+ patient.getUcl() + File.separator + document.getPath());
							Files.copy(pathLocation.resolve(document.getPath()), outputStream);
							document.setPath("upload-dir" + File.separator + company.getCompanyName() + File.separator
									+ "Patient" + File.separator + patient.getUcl() + File.separator
									+ document.getPath());
						}
					}
				} else {
					File UCIfolder = new File(patientfolder, patient.getUcl());
					if (!UCIfolder.exists()) {
						if (UCIfolder.mkdir()) {
							Path fileLocationPath = Paths.get(UCIfolder.getPath());
							FileOutputStream outputStream = new FileOutputStream("upload-dir" + File.separator
									+ company.getCompanyName() + File.separator + "Patient" + File.separator
									+ patient.getUcl() + File.separator + document.getPath());
							Files.copy(pathLocation.resolve(document.getPath()), outputStream);
							document.setPath("upload-dir" + File.separator + company.getCompanyName() + File.separator
									+ "Patient" + File.separator + patient.getUcl() + File.separator
									+ document.getPath());
						}
					}

				}
			} else {
				File patientfolder = new File(companyfolder, "Patient");
				if (!patientfolder.exists()) {
					if (patientfolder.mkdirs()) {
						File UCIfolder = new File(patientfolder, patient.getUcl());
						if (!UCIfolder.exists()) {
							if (UCIfolder.mkdir()) {
								Path fileLocationPath = Paths.get(UCIfolder.getPath());
								FileOutputStream outputStream = new FileOutputStream("upload-dir" + File.separator
										+ company.getCompanyName() + File.separator + "Patient" + File.separator
										+ patient.getUcl() + File.separator + document.getPath());
								Files.copy(pathLocation.resolve(document.getPath()), outputStream);
								document.setPath("upload-dir" + File.separator + company.getCompanyName()
										+ File.separator + "Patient" + File.separator + patient.getUcl()
										+ File.separator + document.getPath());
							}
						}
					}
				}
			}
		}
	}

	public Page<Patient> findAll(int page, int size) {
		return patientRepository.findAll(new PageRequest(page, size));
	}

	public List<Document> getDocumentsByPatient(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		// return dbPatient.getDocuments();
		return null;
	}

	public List<Document> allDocumentsbyPatientAndStatus(Long patientId, DocumentType status) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		/*
		 * List<Document> patientDocuments=new ArrayList<Document>();
		 * List<Document> documents=dbPatient.getDocuments(); for(Document
		 * document:documents){
		 * if(document.getDocumentType().name().equals(status)){
		 * patientDocuments.add(document); } }
		 */
		// List<Document>
		// documentrList=documentRepository.findByPatient_IdAndDocumentType(patientId,status);
		// return documentrList;
		return null;
	}

	public int findPatientsByDate(String adminUserName) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		List<Patient> patientsList = new ArrayList<Patient>();

		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			List<Appointment> appointments = appointmentRepository
					.findByDoctor_IdAndAppointmentStartedDateAndActive(person.getId(), LocalDate.now(), true);
			if (appointments == null) {
				throw new RuntimeException("Patients are not assigned");
			}
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
				patientsList.add(patient);
			}
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				patientsList = patientRepository.findByAdminUserAndActiveAndCreatedDate(dbUserAccount.getUsername(),
						true, LocalDate.now());
				if (patientsList == null) {
					throw new RuntimeException("Patients are not assigned");
				}

			} else {
				patientsList = patientRepository.findByAdminUserAndCompany_IdAndActiveAndCreatedDate(adminUserName,
						dbUserAccount.getCompany().getId(), true, LocalDate.now());
				if (patientsList == null) {
					throw new RuntimeException("No patients");
				}
			}
		}
		return patientsList.size();
	}

	public int totalPatientsRegistered(String adminUserName) {
		int i = 0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		List<Patient> patientsList = new ArrayList<Patient>();
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(person.getId(), true);
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
				patientsList.add(patient);
			}
			i = patientsList.size();

		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				patientsList = patientRepository.findByAdminUserAndActive(adminUserName, true);
				i = patientsList.size();
			} else {
				patientsList = patientRepository.findByAdminUserAndCompany_IdAndActive(adminUserName,
						dbUserAccount.getCompany().getId(), true);
				i = patientsList.size();
			}
		}
		return i;
	}

	public Page<Patient> findAllPatientsByRole(String adminUsername, String search, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException("Admin User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		System.out.println("the search data is:::::::::" + search);
		if (search == null) {
			System.out.println("TRUE");
		}
		if (search == null || search.equals("null")) {
			List<Patient> patients = new ArrayList<Patient>();
			Page<Patient> patientsList = null;
			if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
				Doctor doctor = therapistRepository.findOne(person.getId());
				if (doctor == null) {
					throw new RuntimeException("Therapist Does not Exist");
				}
				List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
				for (Appointment appointment : appointments) {
					Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
					patients.add(patient);
				}
				return patientsList = new PageImpl<Patient>(patients, new PageRequest(page, size), patients.size());
			} else {
				if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
					return patientsList = patientRepository.findByAdminUserAndActive(dbUserAccount.getUsername(), true,
							new PageRequest(page, size, Sort.Direction.DESC, "id"));
				} else {
					return patientsList = patientRepository.findByAdminUserAndCompany_IdAndActive(
							dbUserAccount.getUsername(), dbUserAccount.getCompany().getId(), true,
							new PageRequest(page, size, Sort.Direction.DESC, "id"));
				}
			}
		} else {
			List<Patient> patientsList = new ArrayList<Patient>();
			if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
				Doctor doctor = therapistRepository.findOne(person.getId());
				if (doctor == null) {
					throw new RuntimeException("Therapist Does not Exist");
				}
				List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
				for (Appointment appointment : appointments) {
					List<Patient> patient = patientRepository
							.findAllPatientsSearchTherapist(appointment.getPatient().getId(), true, search);
					patientsList.addAll(patient);
				}
				return new PageImpl<Patient>(patientsList, new PageRequest(page, size), patientsList.size());
			} else {
				if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
					patientsList = patientRepository.findAllPatientsSearchIndividual(dbUserAccount.getUsername(), true,
							search);
				} else {
					patientsList = patientRepository.findAllPatientsSearch(adminUsername,
							dbUserAccount.getCompany().getId(), search, true);
				}
				return new PageImpl<Patient>(patientsList, new PageRequest(page, size), patientsList.size());
			}
		}

	}

	public List<Patient> findAllPatientsByRoleList(String adminUsername, String search) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException("Admin User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		System.out.println("the search data is:::::::::" + search);
		if (search == null) {
			System.out.println("TRUE");
		}
		if (search == null || search.equals("null")) {
			List<Patient> patients = new ArrayList<Patient>();
			List<Patient> patientsList = null;
			if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
				Doctor doctor = therapistRepository.findOne(person.getId());
				if (doctor == null) {
					throw new RuntimeException("Therapist Does not Exist");
				}
				List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
				for (Appointment appointment : appointments) {
					Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
					patients.add(patient);
				}
				return patientsList = patients;
			} else {
				if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
					return patientsList = patientRepository.findByAdminUserAndActive(dbUserAccount.getUsername(), true);
				} else {
					return patientsList = patientRepository.findByAdminUserAndCompany_IdAndActive(
							dbUserAccount.getUsername(), dbUserAccount.getCompany().getId(), true);
				}
			}
		} else {
			List<Patient> patientsList = new ArrayList<Patient>();
			if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
				Doctor doctor = therapistRepository.findOne(person.getId());
				if (doctor == null) {
					throw new RuntimeException("Therapist Does not Exist");
				}
				List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
				for (Appointment appointment : appointments) {
					List<Patient> patient = patientRepository
							.findAllPatientsSearchTherapist(appointment.getPatient().getId(), true, search);
					patientsList.addAll(patient);
				}
				return patientsList;
			} else {
				if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
					patientsList = patientRepository.findAllPatientsSearchIndividual(dbUserAccount.getUsername(), true,
							search);
				} else {
					patientsList = patientRepository.findAllPatientsSearch(adminUsername,
							dbUserAccount.getCompany().getId(), search, true);
				}
				return patientsList;
			}
		}

	}

	public List<Patient> findAllPatientsByRoleForBillings(String adminUsername, String search, ClientType clientType,
			LocalDate startDate, LocalDate endDate) {

		List<Patient> patients = findAllPatientsByRoleList(adminUsername, search);
		System.out.println("All Patients Page:" + patients.size());
		List<Patient> patientsList = patients;
		System.out.println("All Patients List:" + patientsList.size());
		List<Patient> regionalPatientsList = new ArrayList<>();
		List<Patient> clientTypePatientsList = new ArrayList<>();
		// List<Patient> DatePatientsList=new ArrayList<>();
		List<Patient> finalPatientsList = new ArrayList<>();

		/*
		 * for (Patient patient : patientsList) { // veedu Regional Center ani ela
		 * telusthadi? if
		 * (patient.getClientType().getClientTypeName().equals("Regional Center")) {//
		 * idhi // Correct // ah // if(patient.getRegionalCenter()!=null){//idhi Correct
		 * ah // edhi correct? regionalPatientsList.add(patient); // } } }
		 * System.out.println("Regional Patients List:" + regionalPatientsList.size());
		 * patientsList.removeAll(regionalPatientsList);
		 * System.out.println("After removeAll Regional Patients List:" +
		 * patientsList.size());
		 */
		/*
		 * if (clientType != null && startDate != null) { for (Patient patient :
		 * patientsList) { if
		 * (patient.getClientType().getClientTypeName().equals(clientType.
		 * getClientTypeName())) { clientTypePatientsList.add(patient); } } for (Patient
		 * patient : clientTypePatientsList) { if
		 * (patient.getCreatedDate().isAfter(startDate) &&
		 * patient.getCreatedDate().isBefore(endDate)) { finalPatientsList.add(patient);
		 * } if (patient.getCreatedDate().equals(startDate) ||
		 * patient.getCreatedDate().equals(endDate)) { finalPatientsList.add(patient); }
		 * } System.out.println("finalPatientsList Both Date& Clent:" +
		 * finalPatientsList.size()); }
		 * 
		 * if (clientType != null && startDate == null) {
		 * System.out.println("CLIENT NAME:" + clientType.getClientTypeName()); for
		 * (Patient patient : patientsList) { //
		 * System.out.println("TYPE:"+patient.getClientType());
		 * System.out.println("TYPE NAME:" +
		 * patient.getClientType().getClientTypeName());
		 * 
		 * if (patient.getClientType().getClientTypeName().equals(clientType.
		 * getClientTypeName())) { finalPatientsList.add(patient); } }
		 * System.out.println("finalPatientsList Clent:" + finalPatientsList.size()); }
		 * 
		 * if (clientType == null && startDate != null) { for (Patient patient :
		 * patientsList) {
		 * 
		 * if (patient.getCreatedDate().isAfter(startDate) &&
		 * patient.getCreatedDate().isBefore(endDate)) { finalPatientsList.add(patient);
		 * } if (patient.getCreatedDate().equals(startDate) ||
		 * patient.getCreatedDate().equals(endDate)) { finalPatientsList.add(patient); }
		 * 
		 * } System.out.println("finalPatientsList Date:" + finalPatientsList.size()); }
		 */

		//if (clientType == null && startDate == null) {
			finalPatientsList = patientsList;
		//}
		System.out.println("SuccessFully Final O/P:" + finalPatientsList.size());
		return finalPatientsList;
	}

	public Page<Patient> findAllPatientsByRoleForBillingsPage(PatientBillingDto patientBillingDto, int page, int size) {
		if (patientBillingDto.getStartDate() != null && patientBillingDto.getEndDate() == null) {
			throw new RuntimeException("Please Choose The End Date!");
		}

		List<Patient> patientsList = findAllPatientsByRoleForBillings(patientBillingDto.getAdminUsername(),
				patientBillingDto.getSearch(), patientBillingDto.getClientType(), patientBillingDto.getStartDate(),
				patientBillingDto.getEndDate());
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > patientsList.size() ? patientsList.size()
				: (start + new PageRequest(page, size).getPageSize());

		return new PageImpl<Patient>(patientsList.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), patientsList.size());
	}

	public List<Patient> findAllPatients(String adminUserName) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("User Does not Exist");
		}
		List<Patient> patients = new ArrayList<Patient>();
		if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
			Doctor doctor = therapistRepository.findOne(person.getId());
			patients = patientRepository.findByAdminUserAndActiveAndAppointmentCreated(dbUserAccount.getUsername(),
					true, false);
		} else {
			Doctor doctor = therapistRepository.findOne(person.getId());
			List<UserAccount> dbUserList = new ArrayList<UserAccount>();
			if (dbUserAccount.getCompany() != null) {
				dbUserList = userAccountRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			}

			for (UserAccount useraccount : dbUserList) {
				patients = patientRepository
						.findByCompany_IdAndActiveAndAppointmentCreated(useraccount.getCompany().getId(), true, false);
			}

		}

		return patients;
	}

	@SuppressWarnings("unused")
	public void updatePatient(PatientInfoDto patientInfo) throws FileNotFoundException, IOException {
		Patient patient = patientInfo.getPatient();
		List<Document> documents = patientInfo.getDocuments();
		UserAccount dbUserAccount = userAccountRepository.findByUsername(patient.getAdminUser());
		if (dbUserAccount == null) {
			throw new RuntimeException("Admin or Therapisty Does not Exist");
		}

		Patient dbPatient = patientRepository.findOne(patient.getId());

		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		dbPatient.setFirstName(patient.getFirstName());
		dbPatient.setLastName(patient.getLastName());
		dbPatient.setMobileNumber(patient.getMobileNumber());
		dbPatient.setSsn(patient.getSsn());
		dbPatient.setReferredBy(patient.getReferredBy());
		dbPatient.setReasonForReferral(patient.getReasonForReferral());
		dbPatient.setRelationshipToChild(patient.getRelationshipToChild());
		dbPatient.setParentName(patient.getParentName());
		dbPatient.setReasonForReferral(patient.getReasonForReferral());
		dbPatient.setRegDate(LocalDate.now());
		dbPatient.setReferredBy(patient.getReferredBy());
		dbPatient.setRegNumber(patient.getRegNumber());
		dbPatient.setMotherTongue(patient.getMotherTongue());
		dbPatient.setLanguagesKnown(patient.getLanguagesKnown());
		dbPatient.setMotherName(patient.getMotherName());
		dbPatient.setSiblings(patient.getSiblings());
		dbPatient.setPatientNote(patient.getPatientNote());
		
		PatientRegistrationType dbpRegistartionType = patientRegistrationTypeRepository.findOne(patient.getPatientRegistrationType().getId());
		
		if(dbpRegistartionType != null && patient.getCategoryType() != null )
		{
			CategoryType dbcaCategoryType = categoryTypeServiceRepository.findOne(patient.getCategoryType().getId());
			
			if(dbcaCategoryType !=null)
			{
				List<SubCategoryType> subcats = patient.getCategoryType().getSubCategorys();
				
				dbcaCategoryType.setSubCategorys(subcats);
				dbPatient.setCategoryType(dbcaCategoryType);
			}
			
			dbPatient.setPatientRegistrationType(dbpRegistartionType);
		}

		// assign lookup folders to patient folders....
		List<DocumentTypeLookup> lookupFolders = (List<DocumentTypeLookup>) documentTypeLookupRepository.findAll();
		List<Folder> foldersList = new ArrayList<Folder>();
		if (lookupFolders.size() > 0) {
			for (DocumentTypeLookup documentTypeLookup : lookupFolders) {
				Folder folder1 = folderRepository.findByNameAndPatient_Id(documentTypeLookup.getFolderName(),
						dbPatient.getId());
				if (folder1 == null) {
					Folder folder = new Folder();
					if (documentTypeLookup.getDescription() != null) {
						folder.setDescription(documentTypeLookup.getDescription());
					}
					if (documentTypeLookup.getFolderName() != null) {
						folder.setName(documentTypeLookup.getFolderName());
					}
					folder.setPatient(dbPatient);
					addFolder(folder);
					foldersList.add(folder);
				}
			}
		}
		dbPatient.setFolders(foldersList);

		if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
			Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
			if (person == null) {
				throw new RuntimeException("Individual Doesnot Exists");
			}
			Doctor dbdoctor = therapistRepository.findOne(person.getId());
			if (dbdoctor == null) {
				throw new RuntimeException("Individual Doesnot Exists");
			}
			if (dbPatient.getEmail() == null) {
				if (patient.getEmail() != null) {
					dbPatient.setEmail(patient.getEmail());
				}
			}

			Address1 address = dbPatient.getAddress();
			System.out.println("address one --->"+patient.getAddress().getAddress1());
			address.setAddress1(patient.getAddress().getAddress1());
			address.setAddress2(patient.getAddress().getAddress2());
			address.setCountry(patient.getAddress().getCountry());
			address.setState(patient.getAddress().getState());
			address.setCity(patient.getAddress().getCity());

			if (patient.getRegionalCenter() != null) {
				RegionalCenter dbRegionalCenter = regionalCenterRepository.findOne(patient.getRegionalCenter().getId());
				if (dbRegionalCenter == null) {
					throw new RuntimeException("No Regional Center Found..!!");
				}
				dbPatient.setRegionalCenter(dbRegionalCenter);
				dbPatient.setServiceCoordinatorName(patient.getServiceCoordinatorName());
				dbPatient.setPhoneNumber(patient.getPhoneNumber());
				dbPatient.setEmailPatient(patient.getEmailPatient());

			}
			if (patient.getInsurance() != null) {

				Insurance dbInsurance = insuranceRepository.findOne(patient.getInsurance().getId());
				if (dbInsurance == null) {
					throw new RuntimeException("No Insurance Found..!!");
				}
				dbPatient.setInsurance(dbInsurance);
				dbPatient.setPatientInsuraceNo(patient.getPatientInsuraceNo());
				dbPatient.setGroupNumber(patient.getGroupNumber());
				dbPatient.setPlanCode(patient.getPlanCode());

			}
			if(patient.getSchool() !=null){

				School dbSchool = schoolRepository.findOne(patient.getSchool().getId());
				if (dbSchool == null) {
					throw new RuntimeException("No School Found..!!");
				}
				dbPatient.setSchool(dbSchool);
				dbPatient.setContactName(patient.getContactName());
				dbPatient.setPhoneNumber(patient.getPhoneNumber());
				dbPatient.setEmailPatient(patient.getEmailPatient());
			
			}
			if (patient.getTherapyAddress() != null) {
				TherapyAddress therapyAddress = dbPatient.getTherapyAddress();
				therapyAddress.setAddress1(patient.getTherapyAddress().getAddress1());
				therapyAddress.setAddress2(patient.getTherapyAddress().getAddress2());
				therapyAddress.setCountry(patient.getTherapyAddress().getCountry());
				therapyAddress.setState(patient.getTherapyAddress().getState());
				therapyAddress.setCity(patient.getTherapyAddress().getCity());
				therapyAddress.setZipcode(patient.getTherapyAddress().getZipcode());
				therapyAddressRepository.save(therapyAddress);
				dbPatient.setTherapyAddress(therapyAddress);
			}
			dbPatient.setActive(patient.isActive());
			dbPatient.setMobileNumber(patient.getMobileNumber());
			dbPatient.setSecondaryNumber(patient.getSecondaryNumber());
			dbPatient.setModelData(patient.getModelData());
			dbPatient.setModifiedDate(LocalDate.now());
			dbPatient.setModifiedByUser(dbUserAccount.getUsername());
			dbPatient.setCategory(patient.getCategory());
			List<Department> dbdepartment = departmentRepository
					.findByDepartmentName(patient.getDepartments().get(0).getDepartmentName());
			if (dbdepartment != null) {
				dbPatient.setDepartments(dbdepartment);
			}
			dbPatient.setAdminUser(dbUserAccount.getUsername());
			dbPatient.setAddress(address);
			dbPatient.setFullName(patient.getFirstName() + " " + patient.getLastName());
			dbPatient.setModifiedDate(LocalDate.now());
			dbPatient.setGaurdian(patient.getGaurdian());
			dbPatient.setSms(patient.isSms());
			System.out.println(patient.isSms());
			dbPatient.setCommunicatonEmail(patient.isCommunicatonEmail());
			System.out.println(patient.isCommunicatonEmail());
			dbPatient.setEvalutionDone(patient.isEvalutionDone());
			System.out.println(patient.isEvalutionDone());
			if (patient.getEmail() != null) {
				dbPatient.setEmail(patient.getEmail());
			}
			if (documents != null && documents.size() > 0) {
				for (Document document : documents) {
					document.setPatient(dbPatient);
					String folderType = document.getFolder().getName();
					String locationPath = fileConfig.getLocationpath().replace("/", File.separator);
					File companyfolder = new File(locationPath, dbUserAccount.getUniqueId());
					if (!companyfolder.exists()) {
						if (companyfolder.mkdir()) {
							File patientfolder = new File(companyfolder, "Patient");
							if (!patientfolder.exists()) {
								if (patientfolder.mkdirs()) {
									File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
									if (!UCIfolder.exists()) {
										if (UCIfolder.mkdir()) {
											File documentTypeFolder = new File(UCIfolder, folderType);
											if (!documentTypeFolder.exists()) {
												if (documentTypeFolder.mkdir()) {
													Path fileLocationPath = Paths.get(documentTypeFolder.getPath());
													FileOutputStream outputStream = new FileOutputStream(
															locationPath + File.separator + dbUserAccount.getUniqueId()
																	+ File.separator + "Patient" + File.separator
																	+ dbPatient.getUniqueId() + File.separator
																	+ folderType + File.separator + document.getPath());
													Files.copy(pathLocation.resolve(locationPath + document.getPath()),
															outputStream);
													document.setPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator + document.getPath());
													document.setDirectoryPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator);
													documentRepository.save(document);
												}
											} else {
												Path fileLocationPath = Paths.get(documentTypeFolder.getPath());
												FileOutputStream outputStream = new FileOutputStream(
														locationPath + File.separator + dbUserAccount.getUniqueId()
																+ File.separator + "Patient" + File.separator
																+ dbPatient.getUniqueId() + File.separator + folderType
																+ File.separator + document.getPath());
												Files.copy(pathLocation.resolve(locationPath + document.getPath()),
														outputStream);
												document.setPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator + document.getPath());
												document.setDirectoryPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator);
												documentRepository.save(document);
											}

										}
									} else {
										Path fileLocationPath = Paths.get(UCIfolder.getPath());
										FileOutputStream outputStream = new FileOutputStream(locationPath
												+ File.separator + dbUserAccount.getUniqueId() + File.separator
												+ "Patient" + File.separator + dbPatient.getUniqueId() + File.separator
												+ folderType + File.separator + document.getPath());
										Files.copy(pathLocation.resolve(locationPath + document.getPath()),
												outputStream);
										document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
												+ File.separator + "Patient" + File.separator + dbPatient.getUniqueId()
												+ File.separator + folderType + File.separator + document.getPath());
										document.setDirectoryPath(locationPath + File.separator
												+ dbUserAccount.getUniqueId() + File.separator + "Patient"
												+ File.separator + dbPatient.getUniqueId() + File.separator + folderType
												+ File.separator);
										documentRepository.save(document);
									}
								}
							} else {
								File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
								if (!UCIfolder.exists()) {
									if (UCIfolder.mkdir()) {
										Path fileLocationPath = Paths.get(UCIfolder.getPath());
										File documentTypeFolder = new File(UCIfolder, folderType);
										if (!documentTypeFolder.exists()) {
											if (documentTypeFolder.mkdir()) {
												FileOutputStream outputStream = new FileOutputStream(
														locationPath + File.separator + dbUserAccount.getUniqueId()
																+ File.separator + "Patient" + File.separator
																+ dbPatient.getUniqueId() + File.separator + folderType
																+ File.separator + document.getPath());
												Files.copy(pathLocation.resolve(locationPath + document.getPath()),
														outputStream);
												document.setPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator + document.getPath());
												document.setDirectoryPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator);
												documentRepository.save(document);
											}
										}
									}
								}

							}
						}
					} else {

						File patientfolder = new File(companyfolder, "Patient");
						if (!patientfolder.exists()) {
							if (patientfolder.mkdirs()) {
								File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
								if (!UCIfolder.exists()) {
									if (UCIfolder.mkdir()) {
										Path fileLocationPath = Paths.get(UCIfolder.getPath());
										File documentTypeFolder = new File(patientfolder, folderType);
										if (!documentTypeFolder.exists()) {
											if (documentTypeFolder.mkdir()) {
												FileOutputStream outputStream = new FileOutputStream(
														locationPath + File.separator + dbUserAccount.getUniqueId()
																+ File.separator + "Patient" + File.separator
																+ dbPatient.getUniqueId() + File.separator + folderType
																+ File.separator + document.getPath());
												Files.copy(pathLocation.resolve(locationPath + document.getPath()),
														outputStream);
												document.setPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator + document.getPath());
												document.setDirectoryPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator);
												documentRepository.save(document);
											}
										}
									}
								}
							}
						} else {
							File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
							if (!UCIfolder.exists()) {
								if (UCIfolder.mkdir()) {
									Path fileLocationPath = Paths.get(UCIfolder.getPath());
									File documentTypeFolder = new File(UCIfolder, folderType);
									if (!documentTypeFolder.exists()) {
										if (documentTypeFolder.mkdir()) {
											FileOutputStream outputStream = new FileOutputStream(
													locationPath + File.separator + dbUserAccount.getUniqueId()
															+ File.separator + "Patient" + File.separator
															+ dbPatient.getUniqueId() + File.separator + folderType
															+ File.separator + document.getPath());
											Files.copy(pathLocation.resolve(locationPath + document.getPath()),
													outputStream);
											document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
													+ File.separator + "Patient" + File.separator
													+ dbPatient.getUniqueId() + File.separator + folderType
													+ File.separator + document.getPath());
											document.setDirectoryPath(locationPath + File.separator
													+ dbUserAccount.getUniqueId() + File.separator + "Patient"
													+ File.separator + dbPatient.getUniqueId() + File.separator
													+ folderType + File.separator);
											// documentRepository.save(document);
										}
									}
								}
							} else {

								Path fileLocationPath = Paths.get(UCIfolder.getPath());
								File documentTypeFolder = new File(UCIfolder, folderType);
								if (!documentTypeFolder.exists()) {
									if (documentTypeFolder.mkdir()) {
										FileOutputStream outputStream = new FileOutputStream(locationPath
												+ File.separator + dbUserAccount.getUniqueId() + File.separator
												+ "Patient" + File.separator + dbPatient.getUniqueId() + File.separator
												+ folderType + File.separator + document.getPath());
										Files.copy(pathLocation.resolve(locationPath + document.getPath()),
												outputStream);
										document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
												+ File.separator + "Patient" + File.separator + dbPatient.getUniqueId()
												+ File.separator + folderType + File.separator + document.getPath());
										document.setDirectoryPath(locationPath + File.separator
												+ dbUserAccount.getUniqueId() + File.separator + "Patient"
												+ File.separator + dbPatient.getUniqueId() + File.separator + folderType
												+ File.separator);
										documentRepository.save(document);
									}
								} else {
									FileOutputStream outputStream = new FileOutputStream(locationPath + File.separator
											+ dbUserAccount.getUniqueId() + File.separator + "Patient" + File.separator
											+ dbPatient.getUniqueId() + File.separator + folderType + File.separator
											+ document.getPath());
									Files.copy(pathLocation.resolve(locationPath + document.getPath()), outputStream);
									document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
											+ File.separator + "Patient" + File.separator + dbPatient.getUniqueId()
											+ File.separator + folderType + File.separator + document.getPath());
									document.setDirectoryPath(locationPath + File.separator
											+ dbUserAccount.getUniqueId() + File.separator + "Patient" + File.separator
											+ dbPatient.getUniqueId() + File.separator + folderType + File.separator);
									documentRepository.save(document);
								}

							}
						}

					}

				}
			}

			patientRepository.save(dbPatient);

		} else {
			Company dbCompany = dbUserAccount.getCompany();
			if (dbCompany == null) {
				throw new RuntimeException("Company Does not Exist ");
			}

			if (dbPatient.getEmail() == null) {
				if (patient.getEmail() != null) {
					dbPatient.setEmail(patient.getEmail());
				}
			}
			Address1 address = dbPatient.getAddress1();
			if (patient.getAddress1().getAddress1() != null) {
				address.setAddress1(patient.getAddress1().getAddress1());
			}
			if (patient.getAddress1().getAddress2() != null) {
				address.setAddress2(patient.getAddress1().getAddress2());
			}
			address.setCountry(patient.getAddress1().getCountry());
			address.setState(patient.getAddress1().getState());
			address.setCity(patient.getAddress1().getCity());
			address.setZipcode(patient.getAddress1().getZipcode());
			if (dbPatient.isAppointmentCreated()) {
				if (patient.isActive() == false) {
					throw new RuntimeException("You Can't Inactivate the Patient... Treatment is in Progress");
				}
			}

			if (patient.getRegionalCenter() != null) {
				RegionalCenter dbRegionalCenter = regionalCenterRepository.findOne(patient.getRegionalCenter().getId());
				if (dbRegionalCenter == null) {
					throw new RuntimeException("No Regional Center Found..!!");
				}
				dbPatient.setRegionalCenter(dbRegionalCenter);
				dbPatient.setServiceCoordinatorName(patient.getServiceCoordinatorName());
				dbPatient.setPhoneNumber(patient.getPhoneNumber());
				dbPatient.setEmailPatient(patient.getEmailPatient());
			}
			if (patient.getSchool() != null) {

				School dbSchool = schoolRepository.findOne(patient.getSchool().getId());
				if (dbSchool == null) {
					throw new RuntimeException("No School Found..!!");
				}
				dbPatient.setSchool(dbSchool);
				dbPatient.setContactName(patient.getContactName());
				dbPatient.setPhoneNumber(patient.getPhoneNumber());
				dbPatient.setEmailPatient(patient.getEmailPatient());
			
			}

			if (patient.getInsurance() != null) {
				Insurance dbInsurance = insuranceRepository.findOne(patient.getInsurance().getId());
				if (dbInsurance == null) {
					throw new RuntimeException("No Insurance Found..!!");
				}
				dbPatient.setInsurance(dbInsurance);
				dbPatient.setPatientInsuraceNo(patient.getPatientInsuraceNo());
				dbPatient.setGroupNumber(patient.getGroupNumber());
				dbPatient.setPlanCode(patient.getPlanCode());
			}
			if (patient.getTherapyAddress() != null) {
				TherapyAddress therapyAddress = dbPatient.getTherapyAddress();
				therapyAddress.setAddress1(patient.getTherapyAddress().getAddress1());
				therapyAddress.setAddress2(patient.getTherapyAddress().getAddress2());
				therapyAddress.setCountry(patient.getTherapyAddress().getCountry());
				therapyAddress.setState(patient.getTherapyAddress().getState());
				therapyAddress.setCity(patient.getTherapyAddress().getCity());
				therapyAddress.setZipcode(patient.getTherapyAddress().getZipcode());
				therapyAddressRepository.save(therapyAddress);
				dbPatient.setTherapyAddress(therapyAddress);
			}

			dbPatient.setActive(patient.isActive());
			dbPatient.setGender(patient.getGender());
			dbPatient.setCompany(dbCompany);
			dbPatient.setFullName(patient.getFirstName() + " " + patient.getLastName());
			dbPatient.setModifiedDate(LocalDate.now());
			dbPatient.setModifiedByUser(dbUserAccount.getUsername());
			dbPatient.setAdminUser(dbUserAccount.getUsername());
			if (patient.getSecondaryNumber() != null) {
				dbPatient.setSecondaryNumber(patient.getSecondaryNumber());
			}
			dbPatient.setModelData(patient.getModelData());
			dbPatient.setCategory(patient.getCategory());
			List<Department> dbdepartment = departmentRepository.findByDepartmentNameAndCompany_Id(
					/*patient.getDepartments().get(0).getDepartmentName()*/"Speech Therapy", dbUserAccount.getCompany().getId());
			if (dbdepartment != null) {
				dbPatient.setDepartments(dbdepartment);
			}
			dbPatient.setAddress1(address);
			dbPatient.setModifiedDate(LocalDate.now());
			dbPatient.setGaurdian(patient.getGaurdian());
			dbPatient.setSms(patient.isSms());
			if (patient.getEmail() != null) {
				dbPatient.setEmail(patient.getEmail());
			}

			dbPatient.setCommunicatonEmail(patient.isCommunicatonEmail());
			dbPatient.setEvalutionDone(patient.isEvalutionDone());
			if (documents != null) {
				if (documents.size() > 0) {
					for (Document document : documents) {
						document.setPatient(dbPatient);
						String folderType = document.getFolder().getName();
						String locationPath = fileConfig.getLocationpath().replace("/", File.separator);
						File companyfolder = new File(locationPath, dbUserAccount.getUniqueId());
						if (!companyfolder.exists()) {
							if (companyfolder.mkdir()) {
								File patientfolder = new File(companyfolder, "Patient");
								if (!patientfolder.exists()) {
									if (patientfolder.mkdirs()) {
										File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
										if (!UCIfolder.exists()) {
											if (UCIfolder.mkdir()) {
												File documentTypeFolder = new File(UCIfolder, folderType);
												if (!documentTypeFolder.exists()) {
													if (documentTypeFolder.mkdir()) {
														Path fileLocationPath = Paths.get(documentTypeFolder.getPath());
														FileOutputStream outputStream = new FileOutputStream(
																locationPath + File.separator
																		+ dbUserAccount.getUniqueId() + File.separator
																		+ "Patient" + File.separator
																		+ dbPatient.getUniqueId() + File.separator
																		+ folderType + File.separator
																		+ document.getPath());
														Files.copy(
																pathLocation.resolve(locationPath + document.getPath()),
																outputStream);
														document.setPath(locationPath + File.separator
																+ dbUserAccount.getUniqueId() + File.separator
																+ "Patient" + File.separator + dbPatient.getUniqueId()
																+ File.separator + folderType + File.separator
																+ document.getPath());
														document.setDirectoryPath(locationPath + File.separator
																+ dbUserAccount.getUniqueId() + File.separator
																+ "Patient" + File.separator + dbPatient.getUniqueId()
																+ File.separator + folderType + File.separator);
														documentRepository.save(document);
													}
												} else {
													Path fileLocationPath = Paths.get(documentTypeFolder.getPath());
													FileOutputStream outputStream = new FileOutputStream(
															locationPath + File.separator + dbUserAccount.getUniqueId()
																	+ File.separator + "Patient" + File.separator
																	+ dbPatient.getUniqueId() + File.separator
																	+ folderType + File.separator + document.getPath());
													Files.copy(pathLocation.resolve(locationPath + document.getPath()),
															outputStream);
													document.setPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator + document.getPath());
													document.setDirectoryPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator);
													documentRepository.save(document);
												}

											}
										} else {
											Path fileLocationPath = Paths.get(UCIfolder.getPath());
											FileOutputStream outputStream = new FileOutputStream(
													locationPath + File.separator + dbUserAccount.getUniqueId()
															+ File.separator + "Patient" + File.separator
															+ dbPatient.getUniqueId() + File.separator + folderType
															+ File.separator + document.getPath());
											Files.copy(pathLocation.resolve(locationPath + document.getPath()),
													outputStream);
											document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
													+ File.separator + "Patient" + File.separator
													+ dbPatient.getUniqueId() + File.separator + folderType
													+ File.separator + document.getPath());
											document.setDirectoryPath(locationPath + File.separator
													+ dbUserAccount.getUniqueId() + File.separator + "Patient"
													+ File.separator + dbPatient.getUniqueId() + File.separator
													+ folderType + File.separator);
											documentRepository.save(document);
										}
									}
								} else {
									File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
									if (!UCIfolder.exists()) {
										if (UCIfolder.mkdir()) {
											Path fileLocationPath = Paths.get(UCIfolder.getPath());
											File documentTypeFolder = new File(UCIfolder, folderType);
											if (!documentTypeFolder.exists()) {
												if (documentTypeFolder.mkdir()) {
													FileOutputStream outputStream = new FileOutputStream(
															locationPath + File.separator + dbUserAccount.getUniqueId()
																	+ File.separator + "Patient" + File.separator
																	+ dbPatient.getUniqueId() + File.separator
																	+ folderType + File.separator + document.getPath());
													Files.copy(pathLocation.resolve(locationPath + document.getPath()),
															outputStream);
													document.setPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator + document.getPath());
													document.setDirectoryPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator);
													documentRepository.save(document);
												}
											} else {
												FileOutputStream outputStream = new FileOutputStream(
														locationPath + File.separator + dbUserAccount.getUniqueId()
																+ File.separator + "Patient" + File.separator
																+ dbPatient.getUniqueId() + File.separator + folderType
																+ File.separator + document.getPath());
												Files.copy(pathLocation.resolve(locationPath + document.getPath()),
														outputStream);
												document.setPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator + document.getPath());
												document.setDirectoryPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator);
												documentRepository.save(document);
											}
										}
									}
								}
							}
						} else {

							File patientfolder = new File(companyfolder, "Patient");
							if (!patientfolder.exists()) {
								if (patientfolder.mkdirs()) {
									File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
									if (!UCIfolder.exists()) {
										if (UCIfolder.mkdir()) {
											Path fileLocationPath = Paths.get(UCIfolder.getPath());
											File documentTypeFolder = new File(UCIfolder, folderType);
											if (!documentTypeFolder.exists()) {
												if (documentTypeFolder.mkdir()) {
													FileOutputStream outputStream = new FileOutputStream(
															locationPath + File.separator + dbUserAccount.getUniqueId()
																	+ File.separator + "Patient" + File.separator
																	+ dbPatient.getUniqueId() + File.separator
																	+ folderType + File.separator + document.getPath());
													Files.copy(pathLocation.resolve(locationPath + document.getPath()),
															outputStream);
													document.setPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator + document.getPath());
													document.setDirectoryPath(locationPath + File.separator
															+ dbUserAccount.getUniqueId() + File.separator + "Patient"
															+ File.separator + dbPatient.getUniqueId() + File.separator
															+ folderType + File.separator);
													documentRepository.save(document);
												}
											} else {
												FileOutputStream outputStream = new FileOutputStream(
														locationPath + File.separator + dbUserAccount.getUniqueId()
																+ File.separator + "Patient" + File.separator
																+ dbPatient.getUniqueId() + File.separator + folderType
																+ File.separator + document.getPath());
												Files.copy(pathLocation.resolve(locationPath + document.getPath()),
														outputStream);
												document.setPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator + document.getPath());
												document.setDirectoryPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator);
												documentRepository.save(document);
											}
										}
									}
								}
							} else {
								File UCIfolder = new File(patientfolder, dbPatient.getUniqueId());
								if (!UCIfolder.exists()) {
									if (UCIfolder.mkdir()) {
										Path fileLocationPath = Paths.get(UCIfolder.getPath());
										File documentTypeFolder = new File(UCIfolder, folderType);
										if (!documentTypeFolder.exists()) {
											if (documentTypeFolder.mkdir()) {
												FileOutputStream outputStream = new FileOutputStream(
														locationPath + File.separator + dbUserAccount.getUniqueId()
																+ File.separator + "Patient" + File.separator
																+ dbPatient.getUniqueId() + File.separator + folderType
																+ File.separator + document.getPath());
												Files.copy(pathLocation.resolve(locationPath + document.getPath()),
														outputStream);
												document.setPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator + document.getPath());
												document.setDirectoryPath(locationPath + File.separator
														+ dbUserAccount.getUniqueId() + File.separator + "Patient"
														+ File.separator + dbPatient.getUniqueId() + File.separator
														+ folderType + File.separator);
												documentRepository.save(document);
											}
										} else {
											FileOutputStream outputStream = new FileOutputStream(
													locationPath + File.separator + dbUserAccount.getUniqueId()
															+ File.separator + "Patient" + File.separator
															+ dbPatient.getUniqueId() + File.separator + folderType
															+ File.separator + document.getPath());
											Files.copy(pathLocation.resolve(locationPath + document.getPath()),
													outputStream);
											document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
													+ File.separator + "Patient" + File.separator
													+ dbPatient.getUniqueId() + File.separator + folderType
													+ File.separator + document.getPath());
											document.setDirectoryPath(locationPath + File.separator
													+ dbUserAccount.getUniqueId() + File.separator + "Patient"
													+ File.separator + dbPatient.getUniqueId() + File.separator
													+ folderType + File.separator);
											documentRepository.save(document);
										}
									}
								} else {

									Path fileLocationPath = Paths.get(UCIfolder.getPath());
									File documentTypeFolder = new File(UCIfolder, folderType);
									if (!documentTypeFolder.exists()) {
										if (documentTypeFolder.mkdir()) {
											FileOutputStream outputStream = new FileOutputStream(
													locationPath + File.separator + dbUserAccount.getUniqueId()
															+ File.separator + "Patient" + File.separator
															+ dbPatient.getUniqueId() + File.separator + folderType
															+ File.separator + document.getPath());
											Files.copy(pathLocation.resolve(locationPath + document.getPath()),
													outputStream);
											document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
													+ File.separator + "Patient" + File.separator
													+ dbPatient.getUniqueId() + File.separator + folderType
													+ File.separator + document.getPath());
											document.setDirectoryPath(locationPath + File.separator
													+ dbUserAccount.getUniqueId() + File.separator + "Patient"
													+ File.separator + dbPatient.getUniqueId() + File.separator
													+ folderType + File.separator);
											documentRepository.save(document);
										}
									} else {
										FileOutputStream outputStream = new FileOutputStream(locationPath
												+ File.separator + dbUserAccount.getUniqueId() + File.separator
												+ "Patient" + File.separator + dbPatient.getUniqueId() + File.separator
												+ folderType + File.separator + document.getPath());
										Files.copy(pathLocation.resolve(locationPath + document.getPath()),
												outputStream);
										document.setPath(locationPath + File.separator + dbUserAccount.getUniqueId()
												+ File.separator + "Patient" + File.separator + dbPatient.getUniqueId()
												+ File.separator + folderType + File.separator + document.getPath());
										document.setDirectoryPath(locationPath + File.separator
												+ dbUserAccount.getUniqueId() + File.separator + "Patient"
												+ File.separator + dbPatient.getUniqueId() + File.separator + folderType
												+ File.separator);
										documentRepository.save(document);
									}

								}

							}
						}

					}
				}
			}
			patientRepository.save(dbPatient);
		}

	}

	public void addFolder(Folder folder) {

		Patient patient = folder.getPatient();
		UserAccount dbUserAccount = userAccountRepository.findByUsername(patient.getAdminUser());
		if (dbUserAccount == null) {
			throw new RuntimeException("Admin or Therapisty Does not Exist");
		}
		Patient dbPatient = patientRepository.findOne(patient.getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		/*
		 * Patient dbPatient=patientRepository.findByIdAndActive(id, true);
		 * UserAccount dbUserAccount =
		 * userAccountRepository.findByUsername(patient.getAdminUser()); if
		 * (dbUserAccount == null) { throw new
		 * RuntimeException("Admin or Therapisty Does not Exist"); }
		 */
		S3Bucket s3Bucket = new S3Bucket();
		AWSCredentials credentials = new BasicAWSCredentials(AccessS3Bucket.keyName, AccessS3Bucket.securityKey);
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);

		String folderType = folder.getName();
		String locationPath1 = fileConfig.getLocationpath().replace("/", File.separator);// D:\Libs\
		String locationPath = AccessS3Bucket.bucketName.replace("/", File.separator);// dev.teamwork
		// String locationPath2 = AccessS3Bucket.bucketName +"/";
		File companyfolder = new File(locationPath, dbUserAccount.getUniqueId()); // dev.teamwork\EN_9328_Ope
		String companyFolder1 = dbUserAccount.getUniqueId();
		boolean findCompanyFolder = s3Bucket.isValidFile(companyFolder1);
		if (findCompanyFolder == false) {
			s3Bucket.createFolder(companyFolder1);
			String yearFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear();
			boolean findyearFolder = s3Bucket.isValidFile(yearFolder);
			if (findyearFolder == false) {
				s3Bucket.createFolder(yearFolder);
				String patientfolder = dbUserAccount.getUniqueId() + "/" + "Patient";
				boolean findpatientfolder = s3Bucket.isValidFile(patientfolder);
				if (findpatientfolder == false) {
					s3Bucket.createFolder(patientfolder);
					String UCIfolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/" + dbPatient.getUniqueId();
					boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
					if (findUCIfolder == false) {
						s3Bucket.createFolder(UCIfolder);
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					} else {
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					}
				}
			} else {

				String UCIfolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/" + dbPatient.getUniqueId();
				boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
				if (findUCIfolder == false) {
					s3Bucket.createFolder(UCIfolder);
					String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
							+ dbPatient.getUniqueId() + "/" + folderType;
					boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
					if (findDocumentTypeFolder == false) {
						s3Bucket.createFolder(documentTypeFolder);

					}

				} else {
					String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
							+ dbPatient.getUniqueId() + "/" + folderType;
					boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
					if (findDocumentTypeFolder == false) {
						s3Bucket.createFolder(documentTypeFolder);

					}

				}
			}

		} else {

			String yearFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear();
			boolean findyearFolder = s3Bucket.isValidFile(yearFolder);
			if (findyearFolder == false) {
				s3Bucket.createFolder(yearFolder);
				String patientfolder = dbUserAccount.getUniqueId() + "/" + "Patient";
				boolean findpatientfolder = s3Bucket.isValidFile(patientfolder);
				if (findpatientfolder == false) {
					s3Bucket.createFolder(patientfolder);
					String UCIfolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/" + dbPatient.getUniqueId();
					boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
					if (findUCIfolder == false) {
						s3Bucket.createFolder(UCIfolder);
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					} else {
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					}
				} else {
					String UCIfolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/" + dbPatient.getUniqueId();
					boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
					if (findUCIfolder == false) {
						s3Bucket.createFolder(UCIfolder);
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					} else {
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(UCIfolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					}
				}
			} else {
				String patientfolder = dbUserAccount.getUniqueId() + "/" + "Patient";
				boolean findpatientfolder = s3Bucket.isValidFile(patientfolder);
				if (findpatientfolder == false) {
					s3Bucket.createFolder(patientfolder);
					String UCIfolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/" + dbPatient.getUniqueId();
					boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
					if (findUCIfolder == false) {
						s3Bucket.createFolder(UCIfolder);
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					} else {
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					}
				} else {
					String UCIfolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/" + dbPatient.getUniqueId();
					boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
					if (findUCIfolder == false) {
						s3Bucket.createFolder(UCIfolder);
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					} else {
						String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + "Patient" + "/"
								+ dbPatient.getUniqueId() + "/" + folderType;
						boolean findDocumentTypeFolder = s3Bucket.isValidFile(UCIfolder);
						if (findDocumentTypeFolder == false) {
							s3Bucket.createFolder(documentTypeFolder);

						}
					}
				}

			}
		}

	}

	public void addDocument(PatientInfoDto patientInfo) throws FileNotFoundException, IOException {
		System.out.println(patientInfo.getDocumentTypeLookup());
		Patient patient = patientInfo.getPatient();
		List<Document> documents = patientInfo.getDocuments();
		UserAccount dbUserAccount = userAccountRepository.findByUsername(patient.getAdminUser());
		if (dbUserAccount == null) {
			throw new RuntimeException("Admin or Therapisty Does not Exist");
		}
		Patient dbPatient = patientRepository.findOne(patient.getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		/*
		 * Patient dbPatient=patientRepository.findByIdAndActive(id, true);
		 * UserAccount dbUserAccount =
		 * userAccountRepository.findByUsername(patient.getAdminUser()); if
		 * (dbUserAccount == null) { throw new
		 * RuntimeException("Admin or Therapisty Does not Exist"); }
		 */
		S3Bucket s3Bucket = new S3Bucket();
		AWSCredentials credentials = new BasicAWSCredentials(AccessS3Bucket.keyName, AccessS3Bucket.securityKey);
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);
		if (documents != null) {
			if (documents.size() > 0) {
				for (Document document : documents) {
					if (document.getFolder() == null) {
						throw new RuntimeException("Please select Folder !!");
					}
					document.setPatient(dbPatient);
					String folderType = document.getFolder().getName();
					String locationPath1 = fileConfig.getLocationpath().replace("/", File.separator);
					String locationPath = AccessS3Bucket.bucketName.replace("/", File.separator);
					// String locationPath2 = AccessS3Bucket.bucketName +"/";
					File companyfolder = new File(locationPath, dbUserAccount.getUniqueId()); //
					String companyFolder1 = dbUserAccount.getUniqueId();
					boolean findCompanyFolder = s3Bucket.isValidFile(companyFolder1);
					if (findCompanyFolder == false) {
						s3Bucket.createFolder(companyFolder1);
						String yearFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear();
						boolean findyearFolder = s3Bucket.isValidFile(yearFolder);
						if (findyearFolder == false) {
							s3Bucket.createFolder(yearFolder);
							String patientfolder = dbUserAccount.getUniqueId() + "/" +  LocalDate.now().getYear()+"/"+"Patient";
							boolean findpatientfolder = s3Bucket.isValidFile(patientfolder);
							if (findpatientfolder == false) {
								s3Bucket.createFolder(patientfolder);
								String UCIfolder = dbUserAccount.getUniqueId() + "/" +  LocalDate.now().getYear()+"/"+ "Patient" + "/"
										+ dbPatient.getUniqueId();
								boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
								if (findUCIfolder == false) {
									s3Bucket.createFolder(UCIfolder);
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear()+"/"+"Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										document.setFolder(document.getFolder());
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								} else {
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										document.setFolder(document.getFolder());
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								}
							}
						} else {

							String UCIfolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
									+ dbPatient.getUniqueId();
							boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
							if (findUCIfolder == false) {
								s3Bucket.createFolder(UCIfolder);
								String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
										+ dbPatient.getUniqueId() + "/" + folderType;
								boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
								if (findDocumentTypeFolder == false) {
									s3Bucket.createFolder(documentTypeFolder);
									s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
											documentTypeFolder + "/" + document.getName(),
											new File(locationPath1 + document.getName()))
													.withCannedAcl(CannedAccessControlList.PublicRead));
									document.setPath(documentTypeFolder + "/" + document.getName());
									document.setDirectoryPath(documentTypeFolder);
									documentRepository.save(document);
								} else {
									s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
											documentTypeFolder + "/" + document.getName(),
											new File(locationPath1 + document.getName()))
													.withCannedAcl(CannedAccessControlList.PublicRead));
									document.setPath(documentTypeFolder + "/" + document.getName());
									document.setDirectoryPath(documentTypeFolder);
									documentRepository.save(document);
								}

							} else {
								String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
										+ dbPatient.getUniqueId() + "/" + folderType;
								boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
								if (findDocumentTypeFolder == false) {
									s3Bucket.createFolder(documentTypeFolder);
									s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
											documentTypeFolder + "/" + document.getName(),
											new File(locationPath1 + document.getName()))
													.withCannedAcl(CannedAccessControlList.PublicRead));
									document.setPath(documentTypeFolder + "/" + document.getName());
									document.setDirectoryPath(documentTypeFolder);
									documentRepository.save(document);
								} else {
									s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
											documentTypeFolder + "/" + document.getName(),
											new File(locationPath1 + document.getName()))
													.withCannedAcl(CannedAccessControlList.PublicRead));
									document.setPath(documentTypeFolder + "/" + document.getName());
									document.setDirectoryPath(documentTypeFolder);
									documentRepository.save(document);
								}

							}
						}

					} else {

						String yearFolder = dbUserAccount.getUniqueId() + "/" + LocalDate.now().getYear();
						boolean findyearFolder = s3Bucket.isValidFile(yearFolder);
						if (findyearFolder == false) {
							s3Bucket.createFolder(yearFolder);
							String patientfolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient";
							boolean findpatientfolder = s3Bucket.isValidFile(patientfolder);
							if (findpatientfolder == false) {
								s3Bucket.createFolder(patientfolder);
								String UCIfolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
										+ dbPatient.getUniqueId();
								boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
								if (findUCIfolder == false) {
									s3Bucket.createFolder(UCIfolder);
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								} else {
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								}
							} else {
								String UCIfolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
										+ dbPatient.getUniqueId();
								boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
								if (findUCIfolder == false) {
									s3Bucket.createFolder(UCIfolder);
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								} else {
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(UCIfolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								}
							}
						} else {
							String patientfolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient";
							boolean findpatientfolder = s3Bucket.isValidFile(patientfolder);
							if (findpatientfolder == false) {
								s3Bucket.createFolder(patientfolder);
								String UCIfolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
										+ dbPatient.getUniqueId();
								boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
								if (findUCIfolder == false) {
									s3Bucket.createFolder(UCIfolder);
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								} else {
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								}
							} else {
								String UCIfolder = dbUserAccount.getUniqueId()  + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
										+ dbPatient.getUniqueId();
								boolean findUCIfolder = s3Bucket.isValidFile(UCIfolder);
								if (findUCIfolder == false) {
									s3Bucket.createFolder(UCIfolder);
									String documentTypeFolder = dbUserAccount.getUniqueId() + "/" +LocalDate.now().getYear()+"/"+ "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(documentTypeFolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								} else {
									String documentTypeFolder = dbUserAccount.getUniqueId()  + "/" +LocalDate.now().getYear()+"/" + "Patient" + "/"
											+ dbPatient.getUniqueId() + "/" + folderType;
									boolean findDocumentTypeFolder = s3Bucket.isValidFile(UCIfolder);
									if (findDocumentTypeFolder == false) {
										s3Bucket.createFolder(documentTypeFolder);
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									} else {
										s3client.putObject(new PutObjectRequest(AccessS3Bucket.bucketName,
												documentTypeFolder + "/" + document.getName(),
												new File(locationPath1 + document.getName()))
														.withCannedAcl(CannedAccessControlList.PublicRead));// Folder
																											// is
																											// Creating
																											// Here
																											// and
																											// File
																											// also
																											// inserting
																											// here
										document.setPath(documentTypeFolder + "/" + document.getName());
										document.setDirectoryPath(documentTypeFolder);
										documentRepository.save(document);
									}
								}
							}

						}
					}

				}
			}
		} else

		{
			throw new RuntimeException("Please select Files to Upload!!");
		}

	}

	public int patientListMonthlyWise() {
		return 0;
	}

	public int getAllInactivePatients(String adminUserName) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(person.getId(), true);
		List<Patient> patientsList = new ArrayList<Patient>();

		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), false);
				if (patient != null) {
					patientsList.add(patient);
				}
			}
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				patientsList = patientRepository.findByAdminUserAndActive(adminUserName, false);
			} else {
				patientsList = patientRepository.findByAdminUserAndCompany_IdAndActive(adminUserName,
						dbUserAccount.getCompany().getId(), false);
			}
		}
		return patientsList.size();
	}

	public Doctor findDoctoerByPatientId(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist!");
		}
		Doctor doctor = null;
		List<Appointment> list = appointmentRepository.findByPatient_IdAndActive(dbPatient.getId(), true);
		if (list.size() > 0) {
			Appointment appointment = list.get(0);
			doctor = appointment.getDoctor();
		}

		return doctor;
	}

	public List<Document> deleteDoucmentByPatient(Long documentId) {
		List<Document> documents = new ArrayList<Document>();
		Document dbDocument = documentRepository.findOne(documentId);
		if (dbDocument != null) {
			documentRepository.delete(dbDocument);
		}
		if (dbDocument.getFolder() != null) {
			documents = documentRepository.findByFolder_Id(dbDocument.getFolder().getId());
		}
		return documents;
	}

	public List<Patient> findAllPatientsByRoleWithoutPagination(String adminUsername) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException("Admin User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		List<Patient> patients = new ArrayList<Patient>();
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (doctor == null) {
				throw new RuntimeException("Therapist Does not Exist");
			}
			List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
				patients.add(patient);
			}
			return patients;
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				return patients = patientRepository.findByAdminUserAndActive(dbUserAccount.getUsername(), true);
			} else {
				return patients = patientRepository.findByAdminUserAndCompany_IdAndActive(dbUserAccount.getUsername(),
						dbUserAccount.getCompany().getId(), true);
			}
		}
	}

	public Set<Patient> searchPatient1(SearchPatientDto patient) {

		Long deptId = null;
		Long clientTypeId = null;
		if (patient.getDepartment() != null) {
			deptId = patient.getDepartment().getId();
		}
		if (patient.getClientType() != null) {
			clientTypeId = patient.getClientType().getId();
		}
		Specification<Patient> spec = PatientSpec
				.serachByAppointTypeAndPaymentTypeAndClientTypeAndDepatmentTypeAndDates(patient.getStartedDate(),
						patient.getEndDate(), clientTypeId, deptId, patient.getOccurance(), patient.getStatus());
		List<Patient> patients = patientRepository.findAll(spec);
		Set<Patient> SetPatientsList = new HashSet<Patient>();
		SetPatientsList.addAll(patients);
		return SetPatientsList;
	}

	public Set<Patient> searchPatient(SearchPatientDto patient) {
		boolean subAppFlag = false;
		boolean patientFlag = false;
		Set<Patient> patients = new LinkedHashSet<>();
		Set<Patient> list = new LinkedHashSet<>();
		if (patient.getStartedDate() != null || patient.getStatus() != null || patient.getDepartment() != null
				|| patient.getOccurance() != null) {
			subAppFlag = true;
			Specification<SubAppointment> spec = SubAppointmentSpec
					.searchByStratDateAndEndDateAndPaidStatusAndOccuranceAndDepartmentIdAndAppointmentType(
							patient.getStartedDate(), patient.getEndDate(), patient.getStatus(),
							patient.getDepartment(), patient.getOccurance());
			List<SubAppointment> appointments = subAppointmentRepository.findAll(spec);
			for (SubAppointment appointment : appointments) {
				patients.add(appointment.getPatient());
			}
		}

		Set<Patient> dbPatients = null;
		if (patient.getClientType() != null) {
			patientFlag = true;
			dbPatients = patientRepository.findByClientTypeAndActive(patient.getClientType(), true);
			System.out.println("2127::" + dbPatients.size());
		}

		// Part2 completed
		if (subAppFlag == true && patientFlag == true) {
			for (Patient patient2 : patients) {
				for (Patient patient3 : dbPatients) {
					if (patient2 == patient3) {
						list.add(patient3);
						list.add(patient2);
					}
				}
			}
		} else {
			if (subAppFlag == true) {
				list.addAll(patients);
			} else {
				list.addAll(dbPatients);
			}
		}
		return list;
	}

	public Page<Patient> searchPatientPage(SearchPatientDto patient, int page, int size) {
		List<Patient> patientsList = new ArrayList<Patient>();
		Set<Patient> list = searchPatient(patient);
		patientsList.addAll(list);
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > patientsList.size() ? patientsList.size()
				: (start + new PageRequest(page, size).getPageSize());

		return new PageImpl<Patient>(patientsList.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), patientsList.size());
	}

	public List<Payment> getPaymentsByPatient(Long id) {
		Patient patient = patientRepository.findByIdAndActive(id, true);
		if (patient == null) {
			throw new RuntimeException("patient doesn't exist!");
		}
		List<Payment> payments = new ArrayList<>();
		payments = paymentRepository.findByPatient_Id(patient.getId());
		return payments;

	}

	public void updatePayment(Payment payment) {
		Payment dbPayment = paymentRepository.findOne(payment.getId());
		if (dbPayment == null) {
			throw new RuntimeException("payment doesn't exist!");
		}
		if (dbPayment.getPaidAmount() == null) {
			dbPayment.setAssignedAmount(payment.getAssignedAmount());
			dbPayment.setModeOfPaymentTypes(payment.getModeOfPaymentTypes());
			dbPayment.setPaymentTypes(payment.getPaymentTypes());
			paymentRepository.save(dbPayment);
		} else {
			Payment payment2 = new Payment();

			payment2.setMonth(LocalDate.now().getMonth());
			payment2.setYear(LocalDate.now().getYear());
			payment2.setNoOfCycles(0);
			Patient patient = patientRepository.findByIdAndActive(payment.getPatient().getId(), true);
			payment2.setPatient(patient);
			payment2.setAssignedAmount(payment.getAssignedAmount());
			payment2.setModeOfPaymentTypes(payment.getModeOfPaymentTypes());
			payment2.setPaymentTypes(payment.getPaymentTypes());
			paymentRepository.save(payment2);
		}
	}

	public CalAmountDto calculateAmount(CalAmountDto calAmountDto) {
		double totalPaidAmount = 0;
		Sessions sessions = getSessions(calAmountDto.getPatientId());
		CalAmountDto afterCcalAmountDto = new CalAmountDto();
		List<Payment> payments = paymentRepository.findByPatient_Id(calAmountDto.getPatientId());
		for (Payment dbPayment : payments) {
			System.out.println(dbPayment.getPaidAmount());
			if (dbPayment.getPaidAmount() != null) {
				totalPaidAmount = totalPaidAmount + dbPayment.getPaidAmount();
			}
		}
		List<SubAppointment> dbSubAppointments = subAppointmentRepository
				.findByPatient_IdAndActiveAndPaidStatusAndStatus(calAmountDto.getPatientId(), true, PaidStatus.DUE,
						SubAppointmentStatus.COMPLETE);
		int remSchedules = dbSubAppointments.size() - calAmountDto.getNoOfCycles();
		Double balAmount = remSchedules * calAmountDto.getAssignedAmount();
		afterCcalAmountDto.setRemainingAmount(balAmount);
		Double totalAmount = totalPaidAmount + balAmount + calAmountDto.getPaidAmount();
		afterCcalAmountDto.setTotalAmount(totalAmount);
		afterCcalAmountDto.setNoOfCycles(calAmountDto.getNoOfCycles());
		return afterCcalAmountDto;
	}

	// 1.invoice Number
	// 2.cost per session nothing But Assigned Amount
	public void addPayment(Payment payment) {
		
		UserAccount dbUserAccount=userAccountRepository.findByUsername(payment.getCreatedBy());
		if(dbUserAccount==null){
			throw new RuntimeException("User Doesn't Exist!");
		}
		System.out.println("1899:" + payment.getAssignedAmount());
		Sessions sessions = getSessions(payment.getPatient().getId());
		if (payment.getNoOfCycles() > sessions.getUnPaidSessions()) {
			throw new RuntimeException("you have to pay for '" + sessions.getUnPaidSessions() + "' Session only");
		}
		if (payment.getRemainingAmount() < 0) {
			throw new RuntimeException("Balance Amount should not be minus");
		}

		String invoiceNo = invoiceNo(payment.getPaidDate());
		payment.setInvoiceNo(invoiceNo);
		System.out.println(invoiceNo);
		System.out.println("mail::-" + payment.getPatient().getEmail());
		payment.setMonth(payment.getPaidDate().getMonth());
		payment.setYear(payment.getPaidDate().getYear());
		
		List<Payment> dbPaymentsList = paymentRepository.findByPatient_Id(payment.getPatient().getId());
		for (Payment payment2 : dbPaymentsList) {
			if (payment2.getPaidAmount() == null) {
				payment.setId(payment2.getId());
				payment.setDoctor(payment2.getDoctor());
				System.out.println(payment.getId());
				// paymentRepository.save(payment2);
			} else {
				payment.setDoctor(payment2.getDoctor());
			}
			
		}
		System.out.println(payment.getId());
		System.out.println(payment.getPaymentStatus());
		payment.setCreatedDate(LocalDate.now());
		payment.setModifiedDate(LocalDate.now());
		payment.setModifiedBy(dbUserAccount.getUsername());
		payment.setPaidSessions(payment.getPaidSessions()+payment.getNoOfCycles());
		payment.setUnPaidSessions(payment.getCompleteSessions()-payment.getPaidSessions());
		
		paymentRepository.save(payment);
		int noOfPaidAppointments = payment.getNoOfCycles();
		System.out.println(noOfPaidAppointments);
		System.out.println(payment.getPatient().getId());
		List<SubAppointment> subAppointments1 = subAppointmentRepository
				.findByPatient_IdAndActiveAndPaidStatusAndStatus(payment.getPatient().getId(), true, PaidStatus.DUE,
						SubAppointmentStatus.COMPLETE);
		for (int i = 1; i <= noOfPaidAppointments; i++) {
			SubAppointment subAppointment = subAppointments1.get(i - 1);
			System.out.println(subAppointment.getId());
			subAppointment.setPaidStatus(PaidStatus.PAID);
			subAppointment.setAssignAmount(payment.getAssignedAmount());
			subAppointment.setPaidDate(payment.getPaidDate());
			subAppointmentRepository.save(subAppointment);
		}
	}

	public String invoiceNo(LocalDate date) {
		// invoice
		List<Payment> payments = paymentRepository.findByMonthAndYear(date.getMonth(), date.getYear());
		/*
		 * LocalDate date = null; date.getMonth().toString().substring(0, 2);
		 * date.getYear();
		 */
		System.out.println(payments.size());
		int a = payments.size() + 1;
		System.out.println("a:" + a);
		String b = String.format("%04d", a);
		System.out.println("b:" + b);
		String invoiceNo = date.getMonth() + "_" + b + "_" + date.getYear();
		System.out.println("invoiceNo:" + invoiceNo);
		String invoiceNo1 = date.getMonth().toString().substring(0, 2) + "_" + b + "_" + date.getYear();
		System.out.println("invoiceNo1:" + invoiceNo1);
		String d = Integer.toString(date.getYear()).substring(2);
		String invoiceNo2 = date.getMonth().toString().substring(0, 3) + "/" + b + "/" + d;
		System.out.println("invoiceNo2:" + invoiceNo2);
		// invoice completed
		return invoiceNo2;

	}

	public CycleDto getCycles(Long id) {
		List<Payment> dbPaymentsList = paymentRepository.findByPatient_Id(id);
		CycleDto cycle = new CycleDto();
		for (Payment payment : dbPaymentsList) {
			cycle.setAssignedAmount(payment.getAssignedAmount());
			cycle.setModeOfPaymentTypes(payment.getModeOfPaymentTypes());
			cycle.setPaymentTypes(payment.getPaymentTypes());
			List<SubAppointment> subAppointments = subAppointmentRepository.findByPatient_IdAndActiveAndStatus(
					payment.getPatient().getId(), true, SubAppointmentStatus.COMPLETE);
			cycle.setNoOfCycles(subAppointments.size());
			cycle.setDoctor(payment.getDoctor());
			cycle.setRemainingAmount(payment.getRemainingAmount());
		}
		return cycle;
	}

	public Sessions getSessions(Long id) {
		Sessions sessions = new Sessions();
		Patient patient = patientRepository.findByIdAndActive(id, true);
		System.out.println(patient.getId());
		List<SubAppointment> totalSessions = subAppointmentRepository.findByPatient_IdAndActive(patient.getId(), true);
		List<SubAppointment> completeSessions = subAppointmentRepository
				.findByPatient_IdAndActiveAndStatus(patient.getId(), true, SubAppointmentStatus.COMPLETE);
		List<SubAppointment> awaitingSessions = subAppointmentRepository
				.findByPatient_IdAndActiveAndStatus(patient.getId(), true, SubAppointmentStatus.AWAITING);
		List<SubAppointment> cancelledSessions = subAppointmentRepository
				.findByPatient_IdAndActiveAndStatus(patient.getId(), true, SubAppointmentStatus.CANCEL);

		List<SubAppointment> paidSessions = subAppointmentRepository
				.findByPatient_IdAndActiveAndPaidStatus(patient.getId(), true, PaidStatus.PAID);
		List<SubAppointment> unPaidSessions = subAppointmentRepository
				.findByPatient_IdAndActiveAndPaidStatus(patient.getId(), true, PaidStatus.DUE);
		for (SubAppointment subAppointment : totalSessions) {
			Integer a = subAppointment.getAppointmentEndTime().getHour() * 60
					+ subAppointment.getAppointmentEndTime().getMinute();
			Integer b = subAppointment.getAppointmentStartTime().getHour() * 60
					+ subAppointment.getAppointmentStartTime().getMinute();
			System.out.println(a);
			System.out.println(b);
			System.out.println(a - b);
			sessions.setDuration(a - b);
			System.out.println(a - b + "minutes");
		}
		List<Payment> payments = paymentRepository.findByPatient_Id(patient.getId());
		for (Payment payment : payments) {
			System.out.println(payment.getPaidDate());
			if (payment.getPaidDate() != null) {
				sessions.setLastPaymentDate(payment.getPaidDate().toString());
			}
		}
		sessions.setTotalSessions(totalSessions.size());
		sessions.setCompleteSessions(completeSessions.size());
		sessions.setAwaitingSessions(awaitingSessions.size());
		sessions.setCancelledSessions(cancelledSessions.size());
		sessions.setPaidSessions(paidSessions.size());
		sessions.setUnPaidSessions(unPaidSessions.size());

		return sessions;
	}

	public Page<Payment> getAllPayments(int page, int size) {
		return paymentRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id"));
	}

	public Page<Payment> getPaymentsByRole(String username, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Doesn't Exists!");
		}
		Person dbPerson = personRepository.findByUserAccount_IdAndActive(dbUserAccount.getId(), true);
		Page<Payment> paymentsList;
		if (dbUserAccount.getRole().getRoleName().equals("Patient")) {
			return paymentsList = paymentRepository.findByPatientIdAndPaidAmountGreaterThan(dbPerson.getId(), 0.0,
					new PageRequest(page, size, Sort.Direction.DESC, "id"));
		} else if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			return paymentsList = paymentRepository.findByDoctor_IdAndPaidAmountGreaterThan(dbPerson.getId(), 0.0,
					new PageRequest(page, size, Sort.Direction.DESC, "id"));
		} else {
			return paymentsList = paymentRepository.findByPatient_AdminUserAndPaidAmountGreaterThan(
					dbUserAccount.getUsername(), 0.0, new PageRequest(page, size, Sort.Direction.DESC, "id"));
		}
	}

	public List<PaymentDashBoardDto> paymentDashboard(String userName) {
		List<PaymentDashBoardDto> dashBoards = new ArrayList<>();
		Patient patient = patientRepository.findByEmail(userName);
		if (userName == null) {
			throw new RuntimeException("User Doesn't Exists!");
		}
		Set<Month> monthsList = new LinkedHashSet<>();

		List<Payment> paymentsList = paymentRepository.findByPatient_Id(patient.getId());

		for (Payment payment : paymentsList) {
			Month monthName = payment.getPaidDate().getMonth();
			System.out.println(monthName);
			monthsList.add(monthName);
		}
		System.out.println(monthsList);
		for (Month month : monthsList) {
			PaymentDashBoardDto dashBoardDto = new PaymentDashBoardDto();
			dashBoardDto.setMonth(month.toString());
			List<Payment> payments = paymentRepository.findByPatient_IdAndMonth(patient.getId(), month);
			double paidAmount = 0.0;
			for (Payment payment : payments) {
				paidAmount = paidAmount + payment.getPaidAmount();
			}
			dashBoardDto.setPaidAmount(paidAmount);
			dashBoards.add(dashBoardDto);
		}
		return dashBoards;
	}

	public List<SubAppointmentDashboardDto> appointmentDashboardForPatient(String userName) {
		List<SubAppointmentDashboardDto> dtosList = new ArrayList<>();
		Patient dbPatient = patientRepository.findByEmail(userName);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Doesn't Exists!");
		}
		Set<Month> monthsList = new LinkedHashSet<>();
		List<SubAppointment> subAppointmentsList = subAppointmentRepository.findByPatient_IdAndActive(dbPatient.getId(),
				true);

		for (SubAppointment subAppointment : subAppointmentsList) {
			Month monthName = subAppointment.getAppointmentStartedDate().getMonth();
			monthsList.add(monthName);
		}
		for (Month month : monthsList) {
			List<SubAppointment> completeAppointments = subAppointmentRepository
					.findByPatient_IdAndActiveAndStatusAndMonth(dbPatient.getId(), true, SubAppointmentStatus.COMPLETE,
							month);
			List<SubAppointment> cancelAppointments = subAppointmentRepository
					.findByPatient_IdAndActiveAndStatusAndMonth(dbPatient.getId(), true, SubAppointmentStatus.CANCEL,
							month);
			List<SubAppointment> awaitingAppointments = subAppointmentRepository
					.findByPatient_IdAndActiveAndStatusAndMonth(dbPatient.getId(), true, SubAppointmentStatus.AWAITING,
							month);
			SubAppointmentDashboardDto dto = new SubAppointmentDashboardDto();
			dto.setMonth(month.toString());
			dto.setAwaitingAppointments(awaitingAppointments.size());
			dto.setCancelAppointments(cancelAppointments.size());
			dto.setCompleteAppointments(completeAppointments.size());
			dtosList.add(dto);
		}
		return dtosList;
	}

	public List<TotalPaymentDashBoard> totalPaymentDashBoard(String userName) {
		Double totalPaidAmount = 0.0;
		List<TotalPaymentDashBoard> dashBoardList = new ArrayList<>();
		TotalPaymentDashBoard dashBoard = new TotalPaymentDashBoard();
		Patient patient = patientRepository.findByEmail(userName);
		if (userName == null) {
			throw new RuntimeException("User Doesn't Exists!");
		}
		List<Payment> paymentsList = paymentRepository.findByPatient_Id(patient.getId());
		for (Payment payment : paymentsList) {
			totalPaidAmount = totalPaidAmount + payment.getPaidAmount();
			dashBoard.setRemainingAmount(payment.getRemainingAmount());
		}
		System.out.println(totalPaidAmount);
		dashBoard.setPaidAmount(totalPaidAmount);
		dashBoard.setState(patient.getFirstName());
		dashBoardList.add(dashBoard);
		return dashBoardList;

	}

	public Integer invoice() {

		int num = ThreadLocalRandom.current().nextInt(100000, 1000000);
		System.out.println(num);
		return num;
	}

	public PaidAmountDto paidAmount(PaidAmountDto paidAmountDto) {
		List<SubAppointment> subAppointments = subAppointmentRepository.findByActiveAndInvoiceNo(true,
				paidAmountDto.getInvoiceNo());
		double totalPaidAmount = 0;
		SubAppointment appointment = null;
		for (SubAppointment subAppointment : subAppointments) {
			appointment = subAppointment;
		}
		List<Payment> payments = paymentRepository.findByPatientIdAndMonthAndYear(appointment.getPatient().getId(),
				appointment.getAppointmentStartedDate().getMonth(), appointment.getAppointmentStartedDate().getYear());
		for (Payment payment : payments) {
			if (payment.getPaidAmount() != null) {
				totalPaidAmount = totalPaidAmount + payment.getPaidAmount();
			}
		}
		paidAmountDto.setPaidAmount(totalPaidAmount);
		return paidAmountDto;
	}

	// getAll documents by patient id and folder id
	public List<Document> getAllDocumentsByPatientIdAndFolderId(Long patientid, Long folderId) {
		Patient dbPatient = patientRepository.findOne(patientid);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<Document> documents = documentRepository.findByPatient_IdAndFolder_Id(dbPatient.getId(), folderId);
		return documents;
	}

	public void saveInvoice(AppointmentInvoice appointmentInvoice) {
		AppointmentInvoice dbInvoice = appointmentInvoiceRpository.findByInvoiceNumberAndPatient_Id(
				appointmentInvoice.getInvoiceNumber(), appointmentInvoice.getPatient().getId());
		if (appointmentInvoice.getTotalAmount() == null) {
			throw new RuntimeException("Please fill out all the details!");
		}
		if (dbInvoice != null) {
			System.out.println("AppointmentInvoice is Not Null");
			for (Items item : appointmentInvoice.getItems()) {

				Items dbItems = itemsRepository.findById(item.getId());

				if (dbItems != null) {
					Items dbItem = itemsRepository.findById(item.getId());
					dbItem.setAppointmentStartedDate(item.getAppointmentStartedDate());
					dbItem.setItemName(item.getItemName());
					dbItem.setPrice(item.getPrice());
					dbItem.setQuantity(item.getQuantity());
					dbItem.setUnitCost(item.getUnitCost());
					dbItem.setAppointmentInvoice(dbInvoice);
					itemsRepository.save(dbItem);
				} else {
					item.setAppointmentInvoice(dbInvoice);
					itemsRepository.save(item);
				}
			}

			dbInvoice.setCurrency(appointmentInvoice.getCurrency());
			dbInvoice.setPaidStatus(appointmentInvoice.getPaidStatus());
			dbInvoice.setPaidDate(appointmentInvoice.getPaidDate());
			dbInvoice.setPaymethod(appointmentInvoice.getPaymethod());
			dbInvoice.setPaidAmount(appointmentInvoice.getPaidAmount());
			dbInvoice.setCustNotes(appointmentInvoice.getCustNotes());
			dbInvoice.setTermsConditions(appointmentInvoice.getTermsConditions());
			appointmentInvoiceRpository.save(dbInvoice);

		} else {
			for (Items item : appointmentInvoice.getItems()) {
				if (item.getId() != null) {
					item.setId(null);
				}
			}
			appointmentInvoiceRpository.save(appointmentInvoice);
			List<Items> items = appointmentInvoice.getItems();
			System.out.println(items.size());
			for (Items item : items) {
				System.out.println(item.getId());
				Items dbItem = itemsRepository.findOne(item.getId());
				dbItem.setAppointmentInvoice(appointmentInvoice);
				itemsRepository.save(dbItem);
			}
		}

	}

	public void updateInvoice(PatientInvoiceDto patientInvoiceDto) {
		System.out.println(patientInvoiceDto.getInvoice());
		List<SubAppointment> dbInvoices = subAppointmentRepository.findByInvoiceNo(patientInvoiceDto.getInvoice());
		for (SubAppointment dbInvoice : dbInvoices) {
			if (dbInvoice.getPatient().getEmail().equals("BABU@gmail.com")) {
				System.out.println(dbInvoice.getPatient().getEmail());
				dbInvoice.setInvoiceNo(patientInvoiceDto.getNewInvoiceNo());
				subAppointmentRepository.save(dbInvoice);
			}
		}

	}

	public void deleteItem(Long id) {
		System.out.println("id::" + id);
		Items item = itemsRepository.findOne(id);
		itemsRepository.delete(item);
	}

	public AppointmentInvoice getInvoice(InvoiceDto AppointmentInvoiceDto) {

		AppointmentInvoice invoices = appointmentInvoiceRpository.findByInvoiceNumberAndPatient_Id(
				AppointmentInvoiceDto.getInvoiceNumber(), AppointmentInvoiceDto.getPatientId());

		return invoices;
	}

	public Double getTotalAmount(AppointmentInvoice AppointmentInvoiceDto) {
		AppointmentInvoice invoice = appointmentInvoiceRpository
				.findByInvoiceNumber(AppointmentInvoiceDto.getInvoiceNumber());
		Double toatalAmt = 0.0;
		if (invoice != null) {
			for (Items item : invoice.getItems()) {
				toatalAmt = toatalAmt + item.getPrice();
			}
		}
		if (toatalAmt == 0) {
			toatalAmt = 0.0;
		}
		return toatalAmt;
	}

	public List<AppointmentInvoiceTemplate> getAllItems1() {
		return (List<AppointmentInvoiceTemplate>) appointmentInvoiceTemplateRpository.findAll();
	}

	public List<ItemsTemplate> getAllItems() {
		return (List<ItemsTemplate>) itemsTemplateRepository.findAll();
	}

	public Page<Patient> findAllPatientsByRoleInActive(String adminUsername, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException("Admin User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		List<Patient> patients = new ArrayList<Patient>();
		Page<Patient> patientsList = null;
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (doctor == null) {
				throw new RuntimeException("Therapist Does not Exist");
			}
			List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), false);
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), false);
				patients.add(patient);
			}
			return patientsList = new PageImpl<Patient>(patients, new PageRequest(page, size), patients.size());
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				return patientsList = patientRepository.findByAdminUserAndActive(dbUserAccount.getUsername(), false,
						new PageRequest(page, size, Sort.Direction.DESC, "id"));
			} else {
				patientsList = patientRepository.findByAdminUserAndCompany_IdAndActive(
						dbUserAccount.getUsername(), dbUserAccount.getCompany().getId(), false,
						new PageRequest(page, size, Sort.Direction.DESC, "id"));
				System.out.println("patientlist 0-->"+patientsList.getSize());
				return patientsList;
			}
		}
	}

	public Page<Patient> findPatientsByDateList(String adminUserName, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		List<Patient> patientsList = new ArrayList<Patient>();
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			List<Appointment> appointments = appointmentRepository
					.findByDoctor_IdAndAppointmentStartedDateAndActive(person.getId(), LocalDate.now(), true);
			if (appointments == null) {
				throw new RuntimeException("Patients are not assigned");
			}
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
				patientsList.add(patient);
			}
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				patientsList = patientRepository.findByAdminUserAndActiveAndCreatedDate(dbUserAccount.getUsername(),
						true, LocalDate.now());
				if (patientsList == null) {
					throw new RuntimeException("Patients are not assigned");
				}

			} else {
				patientsList = patientRepository.findByAdminUserAndCompany_IdAndActiveAndCreatedDate(adminUserName,
						dbUserAccount.getCompany().getId(), true, LocalDate.now());
				if (patientsList == null) {
					throw new RuntimeException("No patients");
				}
			}
		}
		return new PageImpl<Patient>(patientsList, new PageRequest(page, size), patientsList.size());
	}

	public Page<Patient> findAllPatientsSearch(String adminUserName, String search, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		List<Patient> patientsList = new ArrayList<Patient>();
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (doctor == null) {
				throw new RuntimeException("Therapist Does not Exist");
			}
			List<Appointment> appointments = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
			for (Appointment appointment : appointments) {
				List<Patient> patient = patientRepository
						.findAllPatientsSearchTherapist(appointment.getPatient().getId(), true, search);
				patientsList.addAll(patient);
			}
			return new PageImpl<Patient>(patientsList, new PageRequest(page, size), patientsList.size());
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				patientsList = patientRepository.findAllPatientsSearchIndividual(dbUserAccount.getUsername(), true,
						search);
			} else {
				patientsList = patientRepository.findAllPatientsSearch(adminUserName,
						dbUserAccount.getCompany().getId(), search, true);
			}
			return new PageImpl<Patient>(patientsList, new PageRequest(page, size), patientsList.size());
		}
	}

	public AppointmentInvoice downloadInvoice(String invoiceNo) {
		// TODO Auto-generated method stub
		AppointmentInvoice invoices = appointmentInvoiceRpository.findByInvoiceNumber(invoiceNo);
		return invoices;
	}

	public Payment getpayment(Long id) {
		List<Payment> payments = paymentRepository.findByPatient_Id(id);
		Payment payment = null;
		for (Payment dbPayment : payments) {
			payment = dbPayment;
		}
		return payment;
	}

	public int getInvoiceItems(AppointmentInvoice invoiceNo) {
		// TODO Auto-generated method stub
		AppointmentInvoice invoices = appointmentInvoiceRpository.findByInvoiceNumber(invoiceNo.getInvoiceNumber());
		int i = 0;
		if (invoices == null) {
			return i;
		}
		List<Items> items = itemsRepository.findByAppointmentInvoice_Id(invoices.getId());

		return items.size();
	}

	public void updateFolderName(Folder folder) {
		// TODO Auto-generated method stub
		Folder dbFolder = folderRepository.findOne(folder.getId());
		if (dbFolder == null) {
			throw new RuntimeException("folder are not assigned");
		}
		System.out.println(folder.getId());
		System.out.println(folder.getName());
		dbFolder.setName(folder.getName());
		dbFolder.setDescription(folder.getDescription());
		if (folder.getDocuments() != null) {
			dbFolder.setDocuments(folder.getDocuments());
		}

		folderRepository.save(dbFolder);
	}

	// @RequestMapping(value = "/getAllUSerPaymentsStripe/{username:.+}", method
	// = RequestMethod.GET)
	public List<ChargeDto1> getAllPaymentsStripe(String username) {
		UserAccount userAccount = userAccountRepository.findByUsername(username);
		if (userAccount == null) {
			throw new RuntimeException("User Doesn't Exist!");
		}
		List<Transaction> transactionList = new ArrayList<>();
		List<ChargeDto1> stringList = new ArrayList<>();
		List<StripePayment> stripePayments = stripePaymentRepository.findByUserAccount_Id(userAccount.getId());
		for (StripePayment stripePayment : stripePayments) {
			transactionList.add(stripePayment.getTrasaction());
		}
		for (Transaction transaction : transactionList) {
			ChargeDto1 string = PaymentController.getAllPaymentsStripe(transaction);
			stringList.add(string);
		}
		return stringList;
	}

	public List<RegionalCenter> getRegionalCenters(Long id,String loggedUsername) {
		// TODO Auto-generated method stub
		List<RegionalCenter> regionalCenters = regionalCenterRepository.findByRegionalCenterZoneLookup_IdAndAdminUserNameAndStatus(id, loggedUsername,true);
		return regionalCenters;
	}

	public List<School> getschoolClientTypes() {
		// TODO Auto-generated method stub
		return (List<School>) schoolRepository.findAll();
	}

	public List<PrivateClient> getPrivateClienTypes() {
		// TODO Auto-generated method stub
		return (List<PrivateClient>) privateClientRepository.findAll();
	}

	public List<Insurance> getInsurenceClientTypes() {
		// TODO Auto-generated method stub
		return (List<Insurance>) insuranceRepository.findAll();
	}

	public List<RegionalCenterZoneLookup> getRegionalCenterZones() {
		// TODO Auto-generated method stub
		return (List<RegionalCenterZoneLookup>) regionalCenterZoneRepository.findByStatus(true);
	}

	public Patient updateRegionalCenter(Patient dbPatient) {

		return null;
	}

	public String getFilePathinS3(String directoryPath, String filename) {
		String filePathUrl = "http://dev.teamwork.s3.amazonaws.com/" + directoryPath + "/" + filename;
		return filePathUrl;
	}

	public S3LocatioDto getFilePathinS3(Long documentId) {
		Document dbDocument = documentRepository.findOne(documentId);
		if (dbDocument == null) {
			throw new RuntimeException("Document Does not exists!");
		}
		S3LocatioDto s3LocatioDto = new S3LocatioDto();
		String filePathUrl = AccessS3Bucket.URL_TO_ACCESS_GLOBLA_EXTERAL_FILES_LOGOS + dbDocument.getDirectoryPath()
				+ "/" + dbDocument.getName();
		s3LocatioDto.setLocation(filePathUrl);
		return s3LocatioDto;
	}

	public void downloadTheFilePathFromS3(Long documentId) {
		Document dbDocument = documentRepository.findOne(documentId);
		if (dbDocument == null) {
			throw new RuntimeException("Document Does not exists!");
		}
		String filePathUrl = dbDocument.getDirectoryPath() + "/" + dbDocument.getName();
		AWSCredentials credentials = new BasicAWSCredentials(AccessS3Bucket.keyName, AccessS3Bucket.securityKey);

		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);
		S3Object object = s3client.getObject(AccessS3Bucket.bucketName, filePathUrl);
		File file = new File("Z:/new/sample.pdf");
		System.out.println("Downloading an object" + file);
		// FileUtils.copyInputStreamToFile(object.getObjectContent(), file);

	}

	public List<InvoiceStipeDto> getStripePaymentsInvoice(String username) {
		// InvoiceStipeDto invoiceStipeDto=null;
		UserAccount userAccount = userAccountRepository.findByUsername(username);
		if (userAccount == null) {
			System.out.println("username:"+username);
			throw new RuntimeException("User Doesn't Exist!");
		}
		if (userAccount.getCustomerId() == null) {
			throw new RuntimeException("User Doesn't Has Account in Stripe");
		}
		List<InvoiceStipeDto> invoiceStipeDtos = PaymentController.getAllinvoicee(userAccount);

		return invoiceStipeDtos;
	}
	
	public void patientMchatAssessment(MchatAssessmentDto mchatAssessmentDto) {
		Patient patient = mchatAssessmentDto.getPatient();
		List<MchartLookup> mchartLookups = mchatAssessmentDto.getMchartLookups();
		List<Mchart> mcharts = mchatAssessmentDto.getMcharts();
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findByPhoneNumberAndEmailPatientAndFirstName(patient.getPhoneNumber(),
				patient.getEmailPatient(), patient.getFirstName());
		if (dbPatient != null) {
			throw new RuntimeException("Patient Already Created!!");
		}
		List<Mchart> mchatsList = new ArrayList<Mchart>();
		for (MchartLookup mchartLookup : mchartLookups) {
			Mchart mchart = new Mchart();
			mchart.setName(mchartLookup.getName());
			mchart.setSelectedAnswer(mchartLookup.getSelectedAnswer());
			mchart.setPatient(patient);
			mchatsList.add(mchart);
		}
		patient.setMchatTemplatesQuestios(mchatsList);
		patientRepository.save(patient);
	}
	public Patient getPatientId(PatientOb patient) {
		// TODO Auto-generated method stub
		return patientRepository.findByFirstNameAndEmailPatient(patient.getFirstName(), patient.getEmail());
	}
	
	
	
	public Patient findPatientBYSSN(String ssn) {
		// TODO Auto-generated method stub
		return patientRepository.findBySsn(ssn);
	}
	
	
	public Patient findPatientBYREGNO(String regNo) {
		// TODO Auto-generated method stub
		return patientRepository.findByRegNumber(regNo);
	}
	
	
	public Patient fetchCatPatientByRegType(Patient patientId) {
		// TODO Auto-generated method stub
		return patientRepository.findByIdAndPatientRegistrationType_Id(patientId.getId(), patientId.getPatientRegistrationType().getId());
	}

	public void updatePatientMchatAssmnt(List<Mchart> mchart, Long patientId) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Patient findById(Long id) {
		return patientRepository.findById(id);
	}

	public List<Patient> findAllpatientsByCompanyAndDeptId(String adminUserName, int deptId) {
		Department department = departmentRepository.findById(deptId).get(0);
		List<Patient> patientsByDept = new ArrayList<>();
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		List<Patient> patientList = patientRepository.findByCompany_Id(userAccount.getCompany().getId());
		patientsByDept = patientList.stream().filter(predicate->predicate.getDepartments().contains(department)).collect(Collectors.toList());
		return patientsByDept;
	}

	public Patient findPatientById(Long id) {
		return patientRepository.findById(id);
	}

	

}