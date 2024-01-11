package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.SpeechTheraphyTemplate.BehaviourMangementProgramme;

@Repository
public interface BehaviourlManagementRepository extends PagingAndSortingRepository<BehaviourMangementProgramme, Long> {

	BehaviourMangementProgramme findByPatient_IdAndDate(Long patientId, LocalDate date);

	List<BehaviourMangementProgramme> findByPatient_Id(Long patientId);

	
	
	
}
