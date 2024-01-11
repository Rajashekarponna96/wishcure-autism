package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.PrivateClient;

@Repository
public interface PrivateClientRepository extends PagingAndSortingRepository<PrivateClient, Long> {

	
	
}
