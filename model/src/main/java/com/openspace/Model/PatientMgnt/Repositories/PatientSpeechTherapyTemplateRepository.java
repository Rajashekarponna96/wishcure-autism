package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.PatientSpeechTherapyTemplate;

@Repository
public interface PatientSpeechTherapyTemplateRepository
		extends PagingAndSortingRepository<PatientSpeechTherapyTemplate, Long> {
	
	PatientSpeechTherapyTemplate findByPatient_IdAndStatus(Long id, String sttaus);
	
	PatientSpeechTherapyTemplate findByPatient_IdAndStatusAndEvalutionSheetStatus(Long id, String sttaus,String evalutionStatus);

	List<PatientSpeechTherapyTemplate> findByPatient_Id(Long id);
	
	List<PatientSpeechTherapyTemplate> findByPatient_IdAndEvalutionSheetStatus(Long id,String evalutionSheetStatus);
	
	List<PatientSpeechTherapyTemplate> findByPatient_IdAndEvalutionSheetStatusAndPresentDate(Long id,String evalutionSheetStatus,LocalDate presentdate);
	
	PatientSpeechTherapyTemplate	findByPatient_IdAndEvalutionSheetStatusAndId(Long id,String evalutionSheetStatus,Long templateId);
	
	PatientSpeechTherapyTemplate findByPatient_IdAndStatusAndPresentDate(Long id, String sttaus,LocalDate presentdate);
	
	PatientSpeechTherapyTemplate findByPatient_IdAndStatusAndPresentDateAndEvalutionSheetStatus(Long id, String sttaus,LocalDate presentdate,String evalutionStatus);

}
