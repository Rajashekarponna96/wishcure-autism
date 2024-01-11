package com.openspace.HospitalMgnt.common.Registration;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.Dto.PersonCompanyDto;

public interface RegistrationService {

	void userRegistration(RegistrationDto registrationDto);

	Boolean isEmailIdExists(String email);
	
	Boolean isUCINumberExists(String uci);
	
	Boolean isRciNumberExists(String rci);
	
	Boolean ismobileNumberExists(Long mobileNumber);

	List<Person> getRegisteredCompanies(String registrationType);

	Boolean isSSNNumberExists(String ssn);

	//List<Person> getRegisteredCompaniesFilter(String registrationType);

	Page<Person> getRegisteredCompaniesFilter(PersonCompanyDto personCompanyDto, int page, int size);

	Page<Person> getRegisteredCompaniesPage(String registrationType, int page, int size);

	List<Person> getRegisteredCompaniesForStripeInvoice(String registrationType);

	/*Page<Person> getRegisteredCompanies(String registrationType, int page, int size);*/

}
