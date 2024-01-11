package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.openspace.Model.SpeechTheraphyTemplate.SpeechEvaluation;

@Service
public interface SpeechEvaluationService {


	
	public void save(SpeechEvaluation speechEvaluation) ;

	/**
	 * findByPatient_IdAndDate
	 * @param patientId
	 * @param date
	 * @return
	 */
	public SpeechEvaluation findByPatient_IdAndDate(Long patientId, LocalDate date);

	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId);

	public List<SpeechEvaluation> findByPatient_Id(Long patientId);


	
	/*
	 * public void deletePatientSpecchTemplatesByPatientAndStatus(Long patientId,
	 * String status, Long templateId) { Patient dbPatient =
	 * patientRepository.findOne(patientId); if (dbPatient == null) { throw new
	 * RuntimeException("Patient Doesn't Exist!!"); }
	 * List<PatientSpeechTherapyTemplate> patientSpeechTherapyTemplateList=new
	 * ArrayList<PatientSpeechTherapyTemplate>(); PatientSpeechTherapyTemplate
	 * patientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
	 * .findByPatient_IdAndEvalutionSheetStatusAndId(dbPatient.getId(),
	 * status,templateId); //for(PatientSpeechTherapyTemplate
	 * patientSpeechTherapyTemplate: patientSpeechTherapyTemplates){
	 * dbPatient.setPatientSpeechTherapyTemplates(null);
	 * patientSpeechTherapyTemplate.setPatient(dbPatient);
	 * //patientSpeechTherapyTemplateList.add(patientSpeechTherapyTemplate); // }
	 * speechEvaluationRepository.delete(patientSpeechTherapyTemplate);
	 * 
	 * }
	 */

}
