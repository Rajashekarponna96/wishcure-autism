package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Currency;
@Repository
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long> {
	
	Currency findById(Long id);
	
	Currency findByName(String name);
	
	Page<Currency> findByCompany_Id(Long id, Pageable pageable);
	
	List<Currency> findByCompany_Id(Long id);

	Currency findByNameAndCompany_Id(String name, Long id);

	Currency findByNameAndUserAccount_UniqueId(String name, String uniqueId);



}
