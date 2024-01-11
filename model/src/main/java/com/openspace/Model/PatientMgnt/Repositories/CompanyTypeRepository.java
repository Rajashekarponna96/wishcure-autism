package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.CompanyType;

@Repository
public interface CompanyTypeRepository extends PagingAndSortingRepository<CompanyType, Long> {
	CompanyType findByCompanyTypeName(String name);
	CompanyType findById(Long id);
	List<CompanyType> findByStatus(boolean b);

}
