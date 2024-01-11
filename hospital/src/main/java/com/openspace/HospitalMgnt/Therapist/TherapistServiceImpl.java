package com.openspace.HospitalMgnt.Therapist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.common.Base64Encoding;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.DoctorManagement.Address;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.City;
import com.openspace.Model.Lookups.Country;
import com.openspace.Model.Lookups.State;
import com.openspace.Model.PatientMgnt.Repositories.CityRepository;
import com.openspace.Model.PatientMgnt.Repositories.CountryRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.StateRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;
@Service
public class TherapistServiceImpl implements TherapistService {
	
	@Autowired
	private TherapistRepository therapistRepository;
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Override
	public void saveTherapist(TherapistDto therapistDto) {
		// TODO Auto-generated method stub
		UserAccount dbusername = userAccountRepository.findByUsername(therapistDto.getUsername());
		if (dbusername != null) {
			throw new RuntimeException(ErrorMessageHandler.userAlreadyExists);
		} else {
			UserAccount user=new UserAccount();
			user.setUsername(therapistDto.getUsername());
			user.setPassword(Base64Encoding.encodePassword(therapistDto.getPassword()));
			user.setActive(true);
			
			UserAccount adminUser = userAccountRepository.findByUsername(therapistDto.getAdminUsername());
			user.setCompany(adminUser.getCompany());
			userAccountRepository.save(user);
			
			Address1 address = new Address1();
			address.setCountry(therapistDto.getCountry());
			address.setState(therapistDto.getState());
			address.setCity(therapistDto.getCity());
			address.setAddress1(therapistDto.getAddress1());
			address.setAddress2(therapistDto.getAddress2());
			address.setZipcode(therapistDto.getZipcode());
			
			/*Person person1 = new Person();
			person1.setUserAccount(user);
			person1.setFirstName(therapistDto.getFirstName());
			person1.setLastName(therapistDto.getLastName());
			person1.setAddress(address);
			person1.setEmail(therapistDto.getUsername());
			person1.setMobileNumber(therapistDto.getMobileNumber());
			person1.setGender(therapistDto.getGender());
			person1.setActive(true);
			person1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			person1.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			personRepository.save(person1);*/
			
			Doctor doctor=new Doctor();
			doctor.setUserAccount(user);
			doctor.setCompany(adminUser.getCompany());
			doctor.setRciNumber(therapistDto.getRciNumer());
			doctor.setDesignation(therapistDto.getDesignation());
			doctor.setSpecialization(therapistDto.getSpecialization());
			doctor.setDepartment(therapistDto.getDepartment());
			doctor.setShortAutoBiography(therapistDto.getShortAutoBiography());
			doctor.setUserAccount(user);
			doctor.setFirstName(therapistDto.getFirstName());
			doctor.setLastName(therapistDto.getLastName());
			doctor.setAddress1(address);
			doctor.setEmail(therapistDto.getUsername());
			doctor.setMobileNumber(therapistDto.getMobileNumber());
			doctor.setGender(therapistDto.getGender());
			doctor.setActive(true);
			doctor.setCreatedDate(LocalDate.now());
			doctor.setModifiedDate(LocalDate.now());
			therapistRepository.save(doctor);
			Mail mail = new Mail();
			mail.postMail(therapistDto.getUsername(), "Registration Notification",
					"Your Account Registered Successfully By <b>" + adminUser.getCompany().getCompanyName()
							+ "</b><br><br>Your crediantials are . <br> <br> <b>USERNAME</b>:" + therapistDto.getUsername()
							+ "<br><b>Passworrd</b>:" + therapistDto.getPassword()
							+ "<br><br> <a href='http://103.255.146.157:8080/healthapp/'>Go to Our Company Website</a>");
		}
		
	}

	@Override
	public List<Doctor> getAllTherapists(String adminUsername) {
		Person dbPerson = personRepository.findByEmail(adminUsername);
		if (dbPerson == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<UserAccount> dbUserList = new ArrayList<UserAccount>();
		if (dbPerson.getUserAccount().getCompany() != null) {
			dbUserList = userAccountRepository.findByCompany_Id(dbPerson.getUserAccount().getCompany().getId());
		}
		List<Person> personList = new ArrayList<Person>();;
		for(UserAccount useraccount:dbUserList){
			Person person =personRepository.findByUserAccount_Id(useraccount.getId());
			personList.add(person);
	}
		List<Doctor> doctorList = new ArrayList<Doctor>();;
		for(Person person:personList){
			doctorList =therapistRepository.findByCompany_IdAndActive(person.getUserAccount().getCompany().getId(),true);
	}
		return doctorList;
	}

	@SuppressWarnings("unused")
	@Override
	public void updateTherapist(TherapistDto therapistDto) {
		// TODO Auto-generated method stub
		Person dbPerson = personRepository.findByEmail(therapistDto.getEmail());
		UserAccount userAccount=userAccountRepository.findOne(dbPerson.getUserAccount().getId());
		if (dbPerson == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}else{
			userAccount.setUsername(therapistDto.getUsername());
			userAccount.setActive(therapistDto.isActive());
			
			userAccountRepository.save(userAccount);
			
			Address1 address = new Address1();
			address.setCountry(therapistDto.getCountry());
			address.setState(therapistDto.getState());
			address.setCity(therapistDto.getCity());
			address.setAddress1(therapistDto.getAddress1());
			address.setAddress2(therapistDto.getAddress2());
			address.setZipcode(therapistDto.getZipcode());
			
			dbPerson.setUserAccount(userAccount);
			dbPerson.setFirstName(therapistDto.getFirstName());
			dbPerson.setLastName(therapistDto.getLastName());
			dbPerson.setAddress1(address);
			dbPerson.setEmail(therapistDto.getEmail());
			dbPerson.setMobileNumber(therapistDto.getMobileNumber());
			dbPerson.setGender(therapistDto.getGender());
			dbPerson.setActive(therapistDto.isActive());
			dbPerson.setCreatedDate(LocalDate.now());
			dbPerson.setModifiedDate(LocalDate.now());
			personRepository.save(dbPerson);
			
			Doctor doctor=therapistRepository.findOne(dbPerson.getId());
			doctor.setUserAccount(userAccount);
			doctor.setFirstName(therapistDto.getFirstName());
			doctor.setLastName(therapistDto.getLastName());
			doctor.setAddress1(address);
			doctor.setEmail(therapistDto.getUsername());
			doctor.setMobileNumber(therapistDto.getMobileNumber());
			doctor.setGender(therapistDto.getGender());
			doctor.setActive(therapistDto.isActive());
			doctor.setModifiedDate(LocalDate.now());
			doctor.setRciNumber(therapistDto.getRciNumer());
			doctor.setDesignation(therapistDto.getDesignation());
			doctor.setSpecialization(therapistDto.getSpecialization());
			doctor.setDepartment(therapistDto.getDepartment());
			doctor.setShortAutoBiography(therapistDto.getShortAutoBiography());
			therapistRepository.save(doctor);
		}
		
	}

	@Override
	public void deleteTherapist(Long id) {
		// TODO Auto-generated method stub
		Doctor doctor=therapistRepository.findOne(id);
		Person dbPerson = personRepository.findOne(id);

		if (doctor == null) {
			throw new RuntimeException(ErrorMessageHandler.doctorDoesNotExists);
		}
		doctor.setActive(false);
		therapistRepository.save(doctor);
		
		UserAccount user = userAccountRepository.findOne(dbPerson.getUserAccount().getId());
		user.setActive(false);
		userAccountRepository.save(user);
		
	}

	@Override
	public TherapistDto getTherapistsByUsername(String adminUsername) {
		UserAccount dbUser=userAccountRepository.findByUsername(adminUsername);
		if(dbUser==null){
			throw new RuntimeException(ErrorMessageHandler.therapistDoesNotExists);
		}
		Person person=personRepository.findByUserAccount_Id(dbUser.getId());
		if(person==null){
			throw new RuntimeException(ErrorMessageHandler.therapistDoesNotExists);
		}
		Doctor doctor=therapistRepository.findOne(person.getId());
		if(doctor==null){
			throw new RuntimeException(ErrorMessageHandler.therapistDoesNotExists);
		}
		TherapistDto therapistDto=new TherapistDto();
				therapistDto.setUsername(dbUser.getUsername());
		therapistDto.setActive(dbUser.isActive());
		
		if(doctor.getAddress1() !=null){
		therapistDto.setCountry(person.getAddress1().getCountry());
		therapistDto.setState(person.getAddress1().getState());
		therapistDto.setCity(person.getAddress1().getCity());
		therapistDto.setAddress1(person.getAddress1().getAddress1());
		therapistDto.setAddress2(person.getAddress1().getAddress2());
		therapistDto.setZipcode(person.getAddress1().getZipcode());
		
		therapistDto.setGender(person.getGender());
		
		therapistDto.setDesignation(doctor.getDesignation());
		therapistDto.setSpecialization(doctor.getSpecialization());
		therapistDto.setDepartment(doctor.getDepartment());
		therapistDto.setShortAutoBiography(doctor.getShortAutoBiography());
		therapistDto.setRciNumer(doctor.getRciNumber());
		}
		therapistDto.setFirstName(person.getFirstName());
		therapistDto.setLastName(person.getLastName());
		therapistDto.setEmail(person.getEmail());
		therapistDto.setMobileNumber(person.getMobileNumber());
		therapistDto.setRole(person.getUserAccount().getRole());
		therapistDto.setActive(person.isActive());
		
		return therapistDto;
	}

	@Override
	public int getAllActiveTherapists(String adminUsername) {
		int i=0;
		UserAccount dbUser=userAccountRepository.findByUsername(adminUsername);
		if (dbUser == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person dbPerson = personRepository.findByUserAccount_Id(dbUser.getId());
		if (dbPerson == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		if(dbUser.getCompany()==null){
			return i;
		}
		List<UserAccount> dbUserList = null;
		List<Person> personList = new ArrayList<Person>();
		if (dbPerson.getUserAccount().getCompany() != null) {
			dbUserList = userAccountRepository.findByCompany_IdAndActive(dbUser.getCompany().getId(),true);
			List<Doctor> doctorList = new ArrayList<Doctor>();
			personList = new ArrayList<>(); 
			for(UserAccount useraccount:dbUserList){
				Person person =personRepository.findByUserAccount_Id(useraccount.getId());
				personList.add(person);
			}
			for(Person person:personList){
				doctorList =therapistRepository.findByCompany_IdAndActive(dbUser.getCompany().getId(),true);
			}
			i=doctorList.size();
		}

		if (dbPerson.getUserAccount().getSite() != null && dbUser.getRole().getRoleName().equals("Facility Manager")) {
			dbUserList = userAccountRepository.findBySite_IdAndActive(dbUser.getSite().getId(), true);
			List<Doctor> doctorList = new ArrayList<Doctor>(); 
			personList = new ArrayList<>(); 
			for(UserAccount useraccount:dbUserList){
				Person person =personRepository.findByUserAccount_Id(useraccount.getId());
				personList.add(person);
			}
			for(Person person:personList){
				doctorList=therapistRepository.findBySite_IdAndActive(dbUser.getSite().getId(),true);
			} 
			i=doctorList.size();

		}

		
		
		
		return i;

}

	@Override
	public List<Doctor> getAllTherapistsByDepartment(Long id) {
	List<Doctor> doctor=therapistRepository.findByDepartment_Id(id);
		return doctor;
	}

	@Override
	public int getAllInActiveTherapists(String adminUsername) {
		int i=0;
		UserAccount dbUser=userAccountRepository.findByUsername(adminUsername);
		if (dbUser == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person dbPerson = personRepository.findByUserAccount_Id(dbUser.getId());
		if (dbPerson == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		if(dbUser.getCompany()==null){
			return i;
		}
		List<UserAccount> dbUserList = null;
		if (dbPerson.getUserAccount().getCompany() != null) {
			dbUserList = userAccountRepository.findByCompany_IdAndActive(dbPerson.getUserAccount().getCompany().getId(),false);
		}
		List<Person> personList = new ArrayList<Person>();;
		for(UserAccount useraccount:dbUserList){
			Person person =personRepository.findByUserAccount_Id(useraccount.getId());
			personList.add(person);
		}
		List<Doctor> doctorList = new ArrayList<Doctor>();;
		for(Person person:personList){
			doctorList =therapistRepository.findByCompany_IdAndActive(person.getUserAccount().getCompany().getId(),false);
		}
		i=doctorList.size();
		return i;
	}

	@Override
	public List<Doctor> getAlltherapistsByAdminUsername(String username) {
		// TODO Auto-generated method stub
		UserAccount userAccount=userAccountRepository.findByUsername(username);
		if(userAccount==null){
			throw new  RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		List<Doctor> doctors=therapistRepository.findByCompany_IdAndActive(userAccount.getCompany().getId(), true);
		return doctors;
	}
	
	
}
