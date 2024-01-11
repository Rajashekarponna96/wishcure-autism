package com.openspace.Model.MotorReferenceProfile.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MotorReferenceProfile.MotorResult;

@Repository
public interface MotorResultRepository extends PagingAndSortingRepository<MotorResult, Long> {

	MotorResult findByPatient_Id(Long patientId);
	
	

}
