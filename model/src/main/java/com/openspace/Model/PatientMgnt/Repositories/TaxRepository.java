package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Tax;
@Repository
public interface TaxRepository extends PagingAndSortingRepository<Tax, Long> {
	
	Tax findById(Long id);
	
	Tax findByName(String name);
	
	Page<Tax> findByCompany_Id(Long id, Pageable pageable);
	
	List<Tax> findByCompany_Id(Long id);


}
