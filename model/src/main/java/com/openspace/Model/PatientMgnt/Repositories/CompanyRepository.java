package com.openspace.Model.PatientMgnt.Repositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.Company;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
	Company findByCompanyName(String name);
	Company findById(Long id);

}
