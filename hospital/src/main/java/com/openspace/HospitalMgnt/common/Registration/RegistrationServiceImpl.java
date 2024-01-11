package com.openspace.HospitalMgnt.common.Registration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.common.Base64Encoding;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Insurance;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.RegionalCenter;
import com.openspace.Model.DoctorManagement.School;
import com.openspace.Model.Dto.PersonCompanyDto;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.CompanyStatus;
import com.openspace.Model.PatientMgnt.Repositories.CompanyRepository;
import com.openspace.Model.PatientMgnt.Repositories.CompanyTypeRepository;
import com.openspace.Model.PatientMgnt.Repositories.InsuranceRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PaymentRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterRepository;
import com.openspace.Model.PatientMgnt.Repositories.RoleRepository;
import com.openspace.Model.PatientMgnt.Repositories.SchoolRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripePackageRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripePaymentRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripePlanRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.TransactionRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.Model.Stripe.StripePayment;
import com.openspace.Model.Stripe.StripePlan;
import com.openspace.Model.Stripe.Transaction;
import com.openspace.Model.Stripe.TransactionStatus;
import com.openspace.Model.UserManagement.Role;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private PersonRepository personRepository; 

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TherapistRepository therapistRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private CompanyTypeRepository companyTypeRepository;

	@Autowired
	private StripePlanRepository stripePlanRepository;

	@Autowired
	private StripePackageRepository stripePackageRepository;

	@Autowired
	private StripePaymentRepository stripePaymentRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Autowired
	private RegionalCenterRepository regionalCenterRepository;

	// add role dynamically in database for userRegistration reminder
	@Override
	public void userRegistration(RegistrationDto registrationDto) {

		if (registrationDto.getEmail() == null) {
			throw new RuntimeException(ErrorMessageHandler.invalidUsername);
		}
		if (registrationDto.getPassword() == null) {
			throw new RuntimeException(ErrorMessageHandler.invalidPassword);
		}

		/*UserAccount dbuserAccount1 = userAccountRepository.findByUsernameAndPaymentDone(registrationDto.getEmail(),
				false);
		if (dbuserAccount1 != null) {
			Person dbPerson = personRepository.findByUserAccount_Id(dbuserAccount1.getId());
			personRepository.delete(dbPerson);
			userAccountRepository.delete(dbuserAccount1);
		}*/

		UserAccount dbUserAccount = userAccountRepository.findByUsername(registrationDto.getEmail());
		if (dbUserAccount != null) {
			throw new RuntimeException(ErrorMessageHandler.userAlreadyRegistered);
		}
		/*if (!registrationDto.getPassword().equals(registrationDto.getRepeatPassword())) {
			throw new RuntimeException(ErrorMessageHandler.passwordMustMatch);
		}*/

		UserAccount userAccount = new UserAccount();
		userAccount.setPassword(Base64Encoding.encodePassword(registrationDto.getPassword()));
		userAccount.setUsername(registrationDto.getEmail());
		userAccount.setActive(true);
		// userAccount.setPaymentDone(false);
		Random random = new Random();
		String number = String.valueOf(random.nextDouble() * 1000000);
		String number1 = number.substring(0, 4);
		String individual = "IN_" + number1;

		/*Address1 address = new Address1();
		address.setCountry(registrationDto.getCountry());
		address.setState(registrationDto.getState());
		address.setCity(registrationDto.getCity());
		address.setAddress1(registrationDto.getAddress1());
		address.setAddress2(registrationDto.getAddress2());
		address.setZipcode(registrationDto.getZipcode());*/

		if (registrationDto.getCompanyName() != null) {

			Company dbCompany = companyRepository.findByCompanyName(registrationDto.getCompanyName());
			if (dbCompany == null) {

				// companyTypeRepository.save(registrationDto.getCompanyType());
				Company company = new Company();
				if (registrationDto.getCompanyName() != null) {
					company.setCompanyName(registrationDto.getCompanyName());
				}
				company.setCompanyStatus(CompanyStatus.APPROVED);
				//company.setAddress1(address);
				company.setCompanyType(registrationDto.getCompanyType());
				companyRepository.save(company);

				if (registrationDto.getCompanyType().getId() == 1) {
					if (registrationDto.getRole() != null) {
						Role dbRole = roleRepository.findByRoleName(registrationDto.getRole().getRoleName());
						if (dbRole == null) {
							throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
						}
						userAccount.setRole(dbRole);
					}
					if (registrationDto.getRole() == null) {
						Role dbRole = roleRepository.findByRoleName("Super Admin");
						if (dbRole == null) {
							throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
						}
						userAccount.setRole(dbRole);
					}
					String companyTag = null;
					if (registrationDto.getCompanyName().length() <= 2) {
						companyTag = registrationDto.getCompanyName().substring(0, 1);
					} else {
						companyTag = registrationDto.getCompanyName().substring(0, 3);
					}
					String enterprise = "EN_" + number1 + "_" + companyTag;
					userAccount.setUniqueId(enterprise);
					userAccount.setActive(true);
					userAccount.setCompany(company);
					userAccount.setCompanyType(registrationDto.getCompanyType());
					userAccount.setRegisteredUser(true);
					/*StripePackage stripePackage = assignStripePaymentsToUser(registrationDto.getStripePackage(),
							registrationDto.getStripePayment(), registrationDto.getTransaction());
					userAccount.setStripePackage(stripePackage);*/
					userAccount.setRegisteredDate(LocalDate.now());
					userAccountRepository.save(userAccount);
					Person person = new Person();
					person.setFirstName(registrationDto.getFirstName());
					person.setLastName(registrationDto.getLastName());
					person.setEmail(registrationDto.getEmail());
					person.setMobileNumber(registrationDto.getMoiblenumber());
					person.setUserAccount(userAccount);
					person.setActive(true);
					person.setCreatedDate(LocalDate.now());
					person.setModifiedDate(LocalDate.now());
					personRepository.save(person);

				} else if (registrationDto.getCompanyType().getId() == 2) {
					String individual1 = "";
					if (registrationDto.getCompanyName() != null && registrationDto.getCompanyName().length() > 0) {
						String companyTag = null;
						if (registrationDto.getCompanyName().length() <= 2) {
							companyTag = registrationDto.getCompanyName().substring(0, 1);
						} else {
							companyTag = registrationDto.getCompanyName().substring(0, 3);
						}
						individual1 = "IN_" + number1 + "_" + companyTag;
					} else {
						Random random2 = new Random();
						individual1 = "IN_" + random2.nextInt();
					}

					;
					if (registrationDto.getRole() != null) {
						Role dbRole = roleRepository.findByRoleName(registrationDto.getRole().getRoleName());
						if (dbRole == null) {
							throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
						}
						userAccount.setRole(dbRole);
					}

					if (registrationDto.getRole() == null) {
						Role dbRole = roleRepository.findByRoleName("Individual");
						if (dbRole == null) {
							throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
						}
						userAccount.setRole(dbRole);
					}
					userAccount.setCompanyType(registrationDto.getCompanyType());
					userAccount.setActive(true);
					userAccount.setUniqueId(individual1);
					userAccount.setCompany(company);
					userAccount.setRegisteredUser(true);
					userAccount.setRegisteredDate(LocalDate.now());
					userAccountRepository.save(userAccount);
					UserAccount dbUserAccount2 = userAccountRepository.findByUsername(registrationDto.getEmail());
					/*StripePackage stripePackage = assignStripePaymentsToUser(registrationDto.getStripePackage(),
							registrationDto.getStripePayment(), registrationDto.getTransaction());
					dbUserAccount2.setStripePackage(stripePackage);*/
					userAccountRepository.save(dbUserAccount2);
					Doctor doctor = new Doctor();
					doctor.setFirstName(registrationDto.getFirstName());
					doctor.setLastName(registrationDto.getLastName());
					doctor.setEmail(registrationDto.getEmail());
					doctor.setUserAccount(userAccount);
					doctor.setMobileNumber(registrationDto.getMoiblenumber());
					doctor.setActive(true);
				//	doctor.setAddress1(address);
					doctor.setCreatedDate(LocalDate.now());
					doctor.setModifiedDate(LocalDate.now());
					therapistRepository.save(doctor);

				}
				else if (registrationDto.getCompanyType().getId() == 3) {
					if (registrationDto.getRole() != null) {
						Role dbRole = roleRepository.findByRoleName(registrationDto.getRole().getRoleName());
						if (dbRole == null) {
							throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
						}
						userAccount.setRole(dbRole);
					}
					if (registrationDto.getRole() == null) {
						Role dbRole = roleRepository.findByRoleName("Special School Admin");
						if (dbRole == null) {
							throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
						}
						userAccount.setRole(dbRole);
					}
					String companyTag = null;
					if (registrationDto.getCompanyName().length() <= 2) {
						companyTag = registrationDto.getCompanyName().substring(0, 1);
					} else {
						companyTag = registrationDto.getCompanyName().substring(0, 3);
					}
					String specialschool = "SP_" + number1 + "_" + companyTag;
					userAccount.setUniqueId(specialschool);
					userAccount.setActive(true);
					userAccount.setCompany(company);
					userAccount.setCompanyType(registrationDto.getCompanyType());
					userAccount.setRegisteredUser(true);
					/*StripePackage stripePackage = assignStripePaymentsToUser(registrationDto.getStripePackage(),
							registrationDto.getStripePayment(), registrationDto.getTransaction());
					userAccount.setStripePackage(stripePackage);*/
					userAccount.setRegisteredDate(LocalDate.now());
					userAccountRepository.save(userAccount);
					Person person = new Person();
					person.setFirstName(registrationDto.getFirstName());
					person.setLastName(registrationDto.getLastName());
					person.setEmail(registrationDto.getEmail());
					person.setMobileNumber(registrationDto.getMoiblenumber());
					person.setUserAccount(userAccount);
					person.setActive(true);
					person.setCreatedDate(LocalDate.now());
					person.setModifiedDate(LocalDate.now());
					personRepository.save(person);

				}
				

				Mail mail = new Mail();
				/*
				 * mail.postMail(registrationDto.getEmail(),
				 * "Registration Successfully !",
				 * "Thank you for Registration.<br> Your UserName is <br>  <b>USERNAME</b>:"
				 * + registrationDto.getEmail());
				 */

				mail.postMail(registrationDto.getEmail(), "Registration Successfully !",
						"Thank you for registering with EzClinic. We are excited to have you as a customer and ensure that you will have a great experience with our platform."
						+ " When you login for the first time, you will be prompted for a OTP ( one time password )."
						+ " A separate email will be sent to your registered email address with the OTP.Please enter the OTP when prompted and you should be all set.In case of any issues,"
								+ " please email us at support@ezclinic.com and we will get back to you with 24-48 hours of your email.<br><br><br><br>Cheers, <br>ezClinic Team.");

			} else {
				throw new RuntimeException(ErrorMessageHandler.companyAlreadyExists);
			}
		} else {

			if (registrationDto.getCompanyType().getId() == 1) {
				if (registrationDto.getRole() != null) {
					Role dbRole = roleRepository.findByRoleName(registrationDto.getRole().getRoleName());
					if (dbRole == null) {
						throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
					}
					userAccount.setRole(dbRole);
				}
				if (registrationDto.getRole() == null) {
					Role dbRole = roleRepository.findByRoleName("Super Admin");
					if (dbRole == null) {
						throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
					}
					userAccount.setRole(dbRole);
				}
				String companyTag = null;
				if (registrationDto.getCompanyName().length() <= 2) {
					companyTag = registrationDto.getCompanyName().substring(0, 1);
				} else {
					companyTag = registrationDto.getCompanyName().substring(0, 3);
				}
				String enterprise = "EN_" + number1 + "_" + companyTag;
				userAccount.setUniqueId(enterprise);
				userAccount.setActive(true);
				
				Company company = new Company();
				company.setCompanyName(registrationDto.getCompanyName());
				company.setCompanyStatus(CompanyStatus.APPROVED);
				company.setCompanyType(registrationDto.getCompanyType());
				/*if (address != null) {
					company.setAddress1(address);
				}*/
				companyRepository.save(company);
				userAccount.setCompany(company);
				
				
				userAccount.setCompanyType(registrationDto.getCompanyType());
				userAccount.setRegisteredUser(true);
				/*StripePackage stripePackage = assignStripePaymentsToUser(registrationDto.getStripePackage(),
						registrationDto.getStripePayment(), registrationDto.getTransaction());
				userAccount.setStripePackage(stripePackage);*/
				userAccount.setRegisteredDate(LocalDate.now());
				userAccountRepository.save(userAccount);
				Person person = new Person();
				person.setFirstName(registrationDto.getFirstName());
				person.setLastName(registrationDto.getLastName());
				person.setEmail(registrationDto.getEmail());
				person.setMobileNumber(registrationDto.getMoiblenumber());
				person.setUserAccount(userAccount);
				person.setActive(true);
				person.setCreatedDate(LocalDate.now());
				person.setModifiedDate(LocalDate.now());
				personRepository.save(person);

			}
			else if (registrationDto.getCompanyType().getId() == 2) {
				String individual1 = "";
				if (registrationDto.getCompanyName() != null && registrationDto.getCompanyName().length() > 0) {
					String companyTag = null;
					if (registrationDto.getCompanyName().length() >= 2) {
						companyTag = registrationDto.getCompanyName().substring(0, 1);
					} else {
						companyTag = registrationDto.getCompanyName().substring(0, 3);
					}
					individual1 = "IN_" + number1 + "_" + companyTag;
					Company company = new Company();
					company.setCompanyName(registrationDto.getCompanyName());
					company.setCompanyStatus(CompanyStatus.APPROVED);
					company.setCompanyType(registrationDto.getCompanyType());
					/*if (address != null) {
						company.setAddress1(address);
					}*/
					companyRepository.save(company);
					userAccount.setCompany(company);

				} else {
					Random random2 = new Random();
					individual1 = "IN_" + random2.nextInt();
				}

				if (registrationDto.getRole() != null) {
					Role dbRole = roleRepository.findByRoleName(registrationDto.getRole().getRoleName());
					if (dbRole == null) {
						throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
					}
					userAccount.setRole(dbRole);
				}

				if (registrationDto.getRole() == null) {
					Role dbRole = roleRepository.findByRoleName("Individual");
					if (dbRole == null) {
						throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
					}
					userAccount.setRole(dbRole);
				}
				userAccount.setCompanyType(registrationDto.getCompanyType());
				userAccount.setActive(true);
				userAccount.setUniqueId(individual1);
				userAccount.setRegisteredUser(true);
				/*StripePackage stripePackage = assignStripePaymentsToUser(registrationDto.getStripePackage(),
						registrationDto.getStripePayment(), registrationDto.getTransaction());
				userAccount.setStripePackage(stripePackage);*/
				userAccount.setRegisteredDate(LocalDate.now());
				userAccountRepository.save(userAccount);
				Doctor doctor = new Doctor();
				doctor.setFirstName(registrationDto.getFirstName());
				doctor.setLastName(registrationDto.getLastName());
				doctor.setEmail(registrationDto.getEmail());
				doctor.setUserAccount(userAccount);
				doctor.setMobileNumber(registrationDto.getMoiblenumber());
				doctor.setActive(true);
			//	doctor.setAddress1(address);
				doctor.setCreatedDate(LocalDate.now());
				doctor.setModifiedDate(LocalDate.now());
				therapistRepository.save(doctor);
				Mail mail = new Mail();
				/*mail.postMail(registrationDto.getEmail(), "Registration Successfully !",
						"Thank you for Registration.<br>Your UserName is <br>  <b>USERNAME</b>:"
								+ registrationDto.getEmail());*/
				mail.postMail(registrationDto.getEmail(), "Registration Successfully !",
						"Thank you for registering with EzClinic. We are excited to have you as a customer and ensure that you will have a great experience with our platform."
						+ " When you login for the first time, you will be prompted for a OTP ( one time password )."
						+ " A separate email will be sent to your registered email address with the OTP.Please enter the OTP when prompted and you should be all set.In case of any issues,"
								+ " please email us at support@ezclinic.com and we will get back to you with 24-48 hours of your email. "+
								"<br><br><br><br>Cheers, <br>ezClinic Team.");

			}
			else if (registrationDto.getCompanyType().getId() == 3) {
				if (registrationDto.getRole() != null) {
					Role dbRole = roleRepository.findByRoleName(registrationDto.getRole().getRoleName());
					if (dbRole == null) {
						throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
					}
					userAccount.setRole(dbRole);
				}
				if (registrationDto.getRole() == null) {
					Role dbRole = roleRepository.findByRoleName("Super Admin");
					if (dbRole == null) {
						throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
					}
					userAccount.setRole(dbRole);
				}
				String companyTag = null;
				if (registrationDto.getCompanyName().length() <= 2) {
					companyTag = registrationDto.getCompanyName().substring(0, 1);
				} else {
					companyTag = registrationDto.getCompanyName().substring(0, 3);
				}
				String specialschool = "SP_" + number1 + "_" + companyTag;
				userAccount.setUniqueId(specialschool);
				userAccount.setActive(true);
				Company company = new Company();
				company.setCompanyName(registrationDto.getCompanyName());
				company.setCompanyStatus(CompanyStatus.APPROVED);
				company.setCompanyType(registrationDto.getCompanyType());
				/*if (address != null) {
					company.setAddress1(address);
				}*/
				companyRepository.save(company);
				userAccount.setCompany(company);
				userAccount.setCompanyType(registrationDto.getCompanyType());
				userAccount.setRegisteredUser(true);
				/*StripePackage stripePackage = assignStripePaymentsToUser(registrationDto.getStripePackage(),
						registrationDto.getStripePayment(), registrationDto.getTransaction());
				userAccount.setStripePackage(stripePackage);*/
				userAccount.setRegisteredDate(LocalDate.now());
				userAccountRepository.save(userAccount);
				Person person = new Person();
				person.setFirstName(registrationDto.getFirstName());
				person.setLastName(registrationDto.getLastName());
				person.setEmail(registrationDto.getEmail());
				person.setMobileNumber(registrationDto.getMoiblenumber());
				person.setUserAccount(userAccount);
				person.setActive(true);
				person.setCreatedDate(LocalDate.now());
				person.setModifiedDate(LocalDate.now());
				personRepository.save(person);

			}

			/*
			 * if (registrationDto.getRole() != null) { Role dbRole =
			 * roleRepository.findByRoleName(registrationDto.getRole().
			 * getRoleName()); if (dbRole == null) { throw new
			 * RuntimeException(ErrorMessageHandler.roleDoesNotExists); }
			 * userAccount.setRole(dbRole); }
			 * 
			 * if (registrationDto.getRole() == null) { Role dbRole =
			 * roleRepository.findByRoleName("Individual"); if (dbRole == null)
			 * { throw new
			 * RuntimeException(ErrorMessageHandler.roleDoesNotExists); }
			 * userAccount.setRole(dbRole); }
			 * userAccount.setUniqueId(individual); userAccount.setActive(true);
			 * // userAccount.setPackagePriceMaster(registrationDto.
			 * getPackagePriceMaster()); //
			 * userAccount.setSubScriptionMaster(registrationDto.
			 * getSubScriptionMaster());
			 * userAccountRepository.save(userAccount);
			 * 
			 * Person person = new Person();
			 * person.setFirstName(registrationDto.getFirstName());
			 * person.setLastName(registrationDto.getLastName());
			 * person.setEmail(registrationDto.getEmail());
			 * person.setUserAccount(userAccount);
			 * personRepository.save(person);
			 * 
			 * Doctor doctor = new Doctor();
			 * doctor.setFirstName(registrationDto.getFirstName());
			 * doctor.setLastName(registrationDto.getLastName());
			 * doctor.setEmail(registrationDto.getEmail());
			 * doctor.setUserAccount(userAccount);
			 * doctor.setMobileNumber(registrationDto.getMoiblenumber());
			 * doctor.setActive(true); doctor.setAddress(address);
			 * doctor.setCreatedDate(LocalDate.now());
			 * doctor.setModifiedDate(LocalDate.now());
			 * therapistRepository.save(doctor); Mail mail = new Mail();
			 * mail.postMail(registrationDto.getEmail(),
			 * "Registration Successfully !",
			 * "Thank you for Registration.<br>Your UserName is <br>  <b>USERNAME</b>:"
			 * + registrationDto.getEmail());
			 */
		}

	}

	@Override
	public Boolean isEmailIdExists(String email) {
		boolean result = false;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(email);
		if (dbUserAccount != null) {
			result = true;
		} else {
			result = false;
		}

		Person dbPerson = personRepository.findByEmail(email);
		if (dbPerson != null) {
			result = true;
		} else {
			result = false;
		}
		School dbSchool = schoolRepository.findByEmail(email);
		if (dbSchool != null) {
			result = true;
		} else {
			result = false;
		}
		Insurance dbInsurance = insuranceRepository.findByEmailId(email);
		if (dbInsurance != null) {
			result = true;
		} else {
			result = false;
		}
		RegionalCenter dbRegionalCenter = regionalCenterRepository.findByEmail(email);
		if (dbRegionalCenter != null) {
			result = true;
		} else {
			result = false;
		}

		Patient dbPatient = patientRepository.findByEmailPatient(email);
		if (dbPatient != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public Boolean ismobileNumberExists(Long mobileNumber) {
		boolean result = false;
		Person person = personRepository.findByMobileNumber(mobileNumber);
		if (person != null) {
			result = true;
		} else {
			result = false;
		}

		School dbSchool = schoolRepository.findByMobileNumber(mobileNumber);
		if (dbSchool != null) {
			result = true;
		} else {
			result = false;
		}
		Insurance dbInsurance = insuranceRepository.findByMobileNumber(mobileNumber);
		if (dbInsurance != null) {
			result = true;
		} else {
			result = false;
		}
		RegionalCenter dbRegionalCenter = regionalCenterRepository.findByMobileNumber(mobileNumber);
		if (dbRegionalCenter != null) {
			result = true;
		} else {
			result = false;
		}

		Patient dbPatient = patientRepository.findByPhoneNumber(mobileNumber);
		if (dbPatient != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public Boolean isRciNumberExists(String rci) {
		boolean result = false;
		Doctor doctor = therapistRepository.findByRciNumber(rci);
		if (doctor != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public Boolean isUCINumberExists(String uci) {
		boolean result = false;
		Patient patient = patientRepository.findByUcl(uci);
		if (patient != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public Boolean isSSNNumberExists(String ssn) {
		boolean result = false;
		Patient patient = patientRepository.findBySsn(ssn);
		if (patient != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public List<Person> getRegisteredCompanies(String registrationType) {
		int count = 0;
		List<UserAccount> regiteredCompanies = new ArrayList<UserAccount>();
		List<Person> regiteredCompanies1 = new ArrayList<Person>();
		if (registrationType.equals("All")) {
			List<UserAccount> enterprisecompanies = userAccountRepository.findByRole_RoleName("Super Admin");
			if (enterprisecompanies != null) {
				regiteredCompanies.addAll(enterprisecompanies);
			}
			List<UserAccount> indvidualCompanies = userAccountRepository.findByRole_RoleName("Individual");
			if (indvidualCompanies != null) {
				regiteredCompanies.addAll(indvidualCompanies);
			}
			for (UserAccount user : regiteredCompanies) {
				Person person = personRepository.findByUserAccount_Id(user.getId());
				regiteredCompanies1.add(person);
			}

		} else if (registrationType.equals("Super Admin")) {
			List<UserAccount> enterprisecompanies = userAccountRepository.findByRole_RoleName("Super Admin");
			if (enterprisecompanies != null) {
				regiteredCompanies.addAll(enterprisecompanies);
			}
			for (UserAccount user : regiteredCompanies) {
				Person person = personRepository.findByUserAccount_Id(user.getId());
				regiteredCompanies1.add(person);
			}
		} else {
			List<UserAccount> indvidualCompanies = userAccountRepository.findByRole_RoleName("Individual");
			if (indvidualCompanies != null) {
				regiteredCompanies.addAll(indvidualCompanies);
			}
			for (UserAccount user : regiteredCompanies) {
				Person person = personRepository.findByUserAccount_Id(user.getId());
				if (person == null) {
					count++;
					System.out.println(count++);
				}
				if (person != null) {
					regiteredCompanies1.add(person);
				}
			}
		}
		return regiteredCompanies1;
	}

	@Override
	public List<Person> getRegisteredCompaniesForStripeInvoice(String registrationType) {
		registrationType = "All";
		List<UserAccount> regiteredCompanies = new ArrayList<UserAccount>();
		List<Person> regiteredCompanies1 = new ArrayList<Person>();
		if (registrationType.equals("All")) {
			List<UserAccount> enterprisecompanies = userAccountRepository.findByRole_RoleName("Super Admin");
			if (enterprisecompanies != null) {
				regiteredCompanies.addAll(enterprisecompanies);
			}
			List<UserAccount> indvidualCompanies = userAccountRepository.findByRole_RoleName("Individual");
			if (indvidualCompanies != null) {
				regiteredCompanies.addAll(indvidualCompanies);
			}
			for (UserAccount user : regiteredCompanies) {
				Person person = personRepository.findByUserAccount_Id(user.getId());
				// regiteredCompanies1.add(person);
				if (person != null) {
					regiteredCompanies1.add(person);
				}
			}

		}
		System.out.println(regiteredCompanies1.size());
		return regiteredCompanies1;
	}

	@Override
	public Page<Person> getRegisteredCompaniesPage(String registrationType, int page, int size) {
		List<Person> regiteredCompanies = getRegisteredCompanies(registrationType);
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > regiteredCompanies.size()
				? regiteredCompanies.size() : (start + new PageRequest(page, size).getPageSize());
		return new PageImpl<Person>(regiteredCompanies.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), regiteredCompanies.size());
	}

	public List<Person> getRegisteredCompaniesFilter(PersonCompanyDto personCompanyDto) {
		List<Person> regiteredCompanies1 = new ArrayList<Person>();
		if (personCompanyDto.getStartDate() != null && personCompanyDto.getEndDate() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectEndDate);
		}
		if (personCompanyDto.getEndDate() != null && personCompanyDto.getStartDate() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectStartDate);
		}
		/*
		 * if(registrationType.equals("All")){ List<UserAccount>
		 * enterprisecompanies = userAccountRepository.findByRole_RoleName(
		 * "Super Admin"); if (enterprisecompanies != null) {
		 * regiteredCompanies.addAll(enterprisecompanies); } List<UserAccount>
		 * indvidualCompanies =
		 * userAccountRepository.findByRole_RoleName("Individual"); if
		 * (indvidualCompanies != null) {
		 * regiteredCompanies.addAll(indvidualCompanies); } for(UserAccount
		 * user:regiteredCompanies){ Person person
		 * =personRepository.findByUserAccount_Id(user.getId());
		 * regiteredCompanies1.add(person); }
		 * 
		 * }
		 */
		// else
		if (personCompanyDto.getUser().equals("Super Admin")) {
			List<Person> persons = new ArrayList<>();
			if (personCompanyDto.getStatus() != null && personCompanyDto.getStartDate() == null) {
				persons = personRepository.findByUserAccount_Role_RoleNameAndActiveStatus("Super Admin",
						personCompanyDto.getStatus());
			}

			if (personCompanyDto.getStatus() != null && personCompanyDto.getStartDate() != null) {
				persons = personRepository
						.findByUserAccount_Role_RoleNameAndActiveStatusAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(
								"Super Admin", personCompanyDto.getStatus(), personCompanyDto.getStartDate(),
								personCompanyDto.getEndDate());
			}

			if (personCompanyDto.getStatus() == null && personCompanyDto.getStartDate() != null) {
				persons = personRepository
						.findByUserAccount_Role_RoleNameAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(
								"Super Admin", personCompanyDto.getStartDate(), personCompanyDto.getEndDate());
			}
			regiteredCompanies1.addAll(persons);
		}

		if (personCompanyDto.getUser().equals("Individual")) {
			List<Person> persons = new ArrayList<>();
			if (personCompanyDto.getStatus() != null && personCompanyDto.getStartDate() == null) {
				persons = personRepository.findByUserAccount_Role_RoleNameAndActiveStatus("Individual",
						personCompanyDto.getStatus());
			}

			if (personCompanyDto.getStatus() != null && personCompanyDto.getStartDate() != null) {
				persons = personRepository
						.findByUserAccount_Role_RoleNameAndActiveStatusAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(
								"Individual", personCompanyDto.getStatus(), personCompanyDto.getStartDate(),
								personCompanyDto.getEndDate());
			}

			if (personCompanyDto.getStatus() == null && personCompanyDto.getStartDate() != null) {
				persons = personRepository
						.findByUserAccount_Role_RoleNameAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(
								"Individual", personCompanyDto.getStartDate(), personCompanyDto.getEndDate());
			}
			regiteredCompanies1.addAll(persons);
		}
		/*
		 * }else{ List<UserAccount> indvidualCompanies =
		 * userAccountRepository.findByRole_RoleName("Individual"); if
		 * (indvidualCompanies != null) {
		 * regiteredCompanies.addAll(indvidualCompanies); } for(UserAccount
		 * user:regiteredCompanies){ Person person
		 * =personRepository.findByUserAccount_Id(user.getId());
		 * regiteredCompanies1.add(person); } }
		 */

		return regiteredCompanies1;
	}

	@Override
	public Page<Person> getRegisteredCompaniesFilter(PersonCompanyDto personCompanyDto, int page, int size) {
		List<Person> regiteredCompanies = getRegisteredCompaniesFilter(personCompanyDto);
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > regiteredCompanies.size()
				? regiteredCompanies.size() : (start + new PageRequest(page, size).getPageSize());
		return new PageImpl<Person>(regiteredCompanies.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), regiteredCompanies.size());
	}

	public StripePayment convertStripePayment(StripePayment payment, StripePackage dbStripePackage,
			Transaction trasaction) {
		StripePayment stripePayment = new StripePayment();
		stripePayment.setAmount(0L);
		stripePayment.setGatewayStatus(payment.getGatewayStatus());
		stripePayment.setModifiedDate(LocalDate.now());
		stripePayment.setPaidDate(payment.getPaidDate());
		stripePayment.setStripePackage(dbStripePackage);
		stripePayment.setTrasaction(trasaction);
		return stripePayment;
	}

	public Transaction convertTransaction(Transaction transaction1) {
		Transaction transaction = new Transaction();
		transaction.setDescription(transaction1.getDescription());
		transaction.setPayby(transaction1.getPayby());
		transaction.setPaymentInvoiceNumber(transaction1.getPaymentInvoiceNumber());
		transaction.setPaymentTransactionNumber(transaction1.getPaymentTransactionNumber());
		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransactionName(transaction1.getTransactionName());
		transaction.setTransactionStatus(TransactionStatus.PAYMENTDONE);
		transaction.setStatus(transaction1.getStatus());
		transaction.setChargeTransactionId(transaction1.getChargeTransactionId());
		return transaction;
	}

	// stripe payment registration logic
	public StripePackage assignStripePaymentsToUser(StripePackage stripePackage, StripePayment stripePayment2,
			Transaction transaction) {
		StripePackage dbStripePackage = stripePackageRepository.findByPackageId(stripePackage.getPackageId());
		if (dbStripePackage != null) {
			StripePlan dbStripePlan = stripePlanRepository.findByProductId(dbStripePackage.getPackageId());
			if (dbStripePlan != null) {
				dbStripePackage.setPlan(dbStripePlan);
			}
		}
		Transaction transaction1 = null;
		if (transaction != null) {
			transaction1 = convertTransaction(transaction);
			if (transaction1 != null) {
				transactionRepository.save(transaction1);
			}
		}
		if (transaction1 != null) {
			StripePayment stripePayment1 = convertStripePayment(stripePayment2, dbStripePackage, transaction1);
			if (stripePayment1 != null) {
				stripePaymentRepository.save(stripePayment1);
			}
		}
		return dbStripePackage;
	}

}
