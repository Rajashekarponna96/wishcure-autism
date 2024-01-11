package com.openspace.HospitalMgnt.Company;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.HospitalMgnt.common.Registration.RegistrationDto;
import com.openspace.Model.Dto.StripeInvoiceDto;
import com.openspace.Model.Lookups.Company;

public interface CompanyService {

	void saveCompany(Company company);

	List<Company> getAllCompanies();

	void updateCompany(Company company);

	void deleteCompany(Long id);

	RegistrationDto getCompanyUser(String username);

	void updateCompanyUser(RegistrationDto registrationDto) throws IOException;

	Page<Company> getAllCompaniesPage(int page, int size);

	StripeInvoiceDto getStripeInvoice(String adminUsername);

	Boolean isCompanyExists(String company);

}
