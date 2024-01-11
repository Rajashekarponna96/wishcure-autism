package com.openspace.HospitalMgnt.CompanyType;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Lookups.CompanyType;

public interface CompanyTypeService {

	
	void saveCompanyType(CompanyType companyType);

	Page<CompanyType> getAllCompanyTypes(int page,int size);

	List<CompanyType> getAllCompanyTypes();

	void updateCompanyType(CompanyType companyType);

	void deleteCompanyType(Long id);
}
