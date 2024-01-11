package com.openspace.HospitalMgnt.common.Registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.Dto.PersonCompanyDto;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = RestURIConstants.USER_REGISTRATION, method = RequestMethod.POST)
	public @ResponseBody void userRegistration(@RequestBody RegistrationDto registrationDto) {
		registrationService.userRegistration(registrationDto);
	}

	@RequestMapping(value = RestURIConstants.ISEMAILEXISTS, method = RequestMethod.GET)
	public @ResponseBody Boolean isEmailIdExists(@PathVariable String email) {
		return registrationService.isEmailIdExists(email);
	}

	@RequestMapping(value = RestURIConstants.ISMOBILENUMBEREXISTS, method = RequestMethod.GET)
	public @ResponseBody Boolean isMobileNumberExists(@PathVariable Long mobileNumber) {
		return registrationService.ismobileNumberExists(mobileNumber);
	}

	@RequestMapping(value = RestURIConstants.ISNPINUMBEREXISTS, method = RequestMethod.GET)
	public @ResponseBody Boolean isRciNumberExists(@PathVariable String rci) {
		return registrationService.isRciNumberExists(rci);
	}

	@RequestMapping(value = RestURIConstants.ISUCINUMBEREXISTS, method = RequestMethod.GET)
	public @ResponseBody Boolean isUCINumberExists(@PathVariable String uci) {
		return registrationService.isUCINumberExists(uci);
	}

	@RequestMapping(value = RestURIConstants.ISSSNNUMBEREXISTS, method = RequestMethod.GET)
	public @ResponseBody Boolean isSSNNumberExists(@PathVariable String ssn) {
		return registrationService.isSSNNumberExists(ssn);
	}
	
	@RequestMapping(value = RestURIConstants.REGISTEREDCOMPANIES, method = RequestMethod.GET)
	public @ResponseBody List<Person> getRegisteredCompanies(@PathVariable String registrationType) {
		return registrationService.getRegisteredCompanies(registrationType);
	}
	
	@RequestMapping(value = RestURIConstants.REGISTEREDCOMPANIES_STRIPE, method = RequestMethod.GET)
	public @ResponseBody List<Person> getRegisteredCompaniesForStripeInvoice(@PathVariable String registrationType) {
		return registrationService.getRegisteredCompaniesForStripeInvoice(registrationType);
	}

	@RequestMapping(value = RestURIConstants.REGISTEREDCOMPANIES_PAGE, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getRegisteredCompaniesPage(@PathVariable String registrationType,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return registrationService.getRegisteredCompaniesPage(registrationType, page, size);
	}

	@RequestMapping(value = RestURIConstants.GET_REGISTERED_COMPANIES_FILTER, method = RequestMethod.POST)
	public @ResponseBody Page<Person> getRegisteredCompaniesFilter(@RequestBody PersonCompanyDto personCompanyDto,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return registrationService.getRegisteredCompaniesFilter(personCompanyDto,page,size);
	}
}
