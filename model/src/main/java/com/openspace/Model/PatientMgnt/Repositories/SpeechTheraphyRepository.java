package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Template.SpeechTherapyTemplate;

@Repository
public interface SpeechTheraphyRepository extends PagingAndSortingRepository<SpeechTherapyTemplate, Long> {

	SpeechTherapyTemplate findByStatus(String status);
	
	
}
