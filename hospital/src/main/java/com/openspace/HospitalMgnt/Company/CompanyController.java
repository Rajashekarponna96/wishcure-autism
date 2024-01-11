package com.openspace.HospitalMgnt.Company;

import java.io.IOException;
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
import com.openspace.HospitalMgnt.common.Registration.RegistrationDto;
import com.openspace.Model.Dto.StripeInvoiceDto;
import com.openspace.Model.Lookups.Company;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = RestURIConstants.SAVE_COMPANY, method = RequestMethod.POST)
	public @ResponseBody void saveCompany(@RequestBody Company company) {
		companyService.saveCompany(company);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_COMPANIES, method = RequestMethod.GET)
	public @ResponseBody List<Company> getAllCompanies() {
		return companyService.getAllCompanies();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_COMPANY, method = RequestMethod.PUT)
	public @ResponseBody void updateCompany(@RequestBody Company company) {
		companyService.updateCompany(company);
	}

	@RequestMapping(value = RestURIConstants.DELETE_COMPANY, method = RequestMethod.DELETE)
	public @ResponseBody void deleteCompany(@PathVariable Long id) {
		companyService.deleteCompany(id);
	}

	@RequestMapping(value = RestURIConstants.GET_COMPANY_USER, method = RequestMethod.GET)
	public @ResponseBody RegistrationDto getCompanyUser(@PathVariable String username) {
		return companyService.getCompanyUser(username);
	}

	@RequestMapping(value = RestURIConstants.UPDATE_COMPANY_USER, method = RequestMethod.PUT)
	public @ResponseBody void updateCompanyUser(@RequestBody RegistrationDto registrationDto) throws IOException {
		companyService.updateCompanyUser(registrationDto);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_COMPANIES_PAGE, method = RequestMethod.GET)
	public @ResponseBody Page<Company> getAllCompaniesPage(@RequestParam("page")int page,@RequestParam("size")int size) {
		return companyService.getAllCompaniesPage(page,size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_STRIPE_INVOICE, method = RequestMethod.GET)
	public @ResponseBody StripeInvoiceDto getStripeInvoice(@PathVariable String username) {
		return companyService.getStripeInvoice(username);
	}
	
	@RequestMapping(value = RestURIConstants.ISCOMPANYEXISTS, method = RequestMethod.GET)
	public @ResponseBody Boolean isCompanyExists(@PathVariable String company){
			return companyService.isCompanyExists(company);
		}
}
