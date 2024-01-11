package com.openspace.Model.MentalReferenceProfile.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MentalReferenceProfile.MentalResult;

@Repository
public interface MentalResultRepository extends PagingAndSortingRepository<MentalResult, Long> {

	MentalResult findByPatient_Id(Long patientId);
	
	

}
