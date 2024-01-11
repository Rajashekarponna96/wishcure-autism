package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.CategoryType;

@Repository
public interface PatientRegistrationTypeRepository extends PagingAndSortingRepository<PatientRegistrationType, Long> {

	PatientRegistrationType findByName(String name);
	List<PatientRegistrationType> findByStatus(boolean b);
	
}
