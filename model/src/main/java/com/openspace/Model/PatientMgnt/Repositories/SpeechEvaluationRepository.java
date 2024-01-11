package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.SpeechTheraphyTemplate.SpeechEvaluation;

@Repository
public interface SpeechEvaluationRepository extends PagingAndSortingRepository<SpeechEvaluation, Long> {

	SpeechEvaluation findByPatient_IdAndDate(Long patientId, LocalDate date);

	List<SpeechEvaluation> findByPatient_Id(Long patientId);
	
	
}
