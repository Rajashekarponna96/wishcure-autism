package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Paymethod;
@Repository
public interface PaymethodRepository extends PagingAndSortingRepository<Paymethod, Long> {
	
	Paymethod findById(Long id);
	
	Paymethod findByName(String name);
	
	Page<Paymethod> findByCompany_Id(Long id, Pageable pageable);
	
	List<Paymethod> findByCompany_Id(Long id);

	Paymethod findByNameAndCompany_Id(String name, Long id);

	Paymethod findByNameAndUserAccount_UniqueId(String name, String uniqueId);

	List<Paymethod> findByUserAccount_IdAndProductOwnerStatus(Long id, boolean b);

	Paymethod findByNameAndUserAccount_Id(String name, Long id);

	



}
