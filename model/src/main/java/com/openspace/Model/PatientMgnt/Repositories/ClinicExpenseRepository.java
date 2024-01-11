package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.ClinicExpenseType;

@Repository
public interface ClinicExpenseRepository extends PagingAndSortingRepository<ClinicExpenseType, Long> {

	List<ClinicExpenseType> findByCompany_Id(Long companyId);

	ClinicExpenseType findByExpenseTypeAndUserAccount_Id(String expenseType, Long id);




	
}

