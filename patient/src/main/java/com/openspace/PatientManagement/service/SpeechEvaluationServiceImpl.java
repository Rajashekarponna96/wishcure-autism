package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.SpeechEvaluationRepository;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechEvaluation;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class SpeechEvaluationServiceImpl implements SpeechEvaluationService{

	@Autowired
	private SpeechEvaluationRepository speechEvaluationRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void save(SpeechEvaluation speechEvaluation) {
		speechEvaluationRepository.save(speechEvaluation);
	}
	@Override
	public SpeechEvaluation findByPatient_IdAndDate(Long patientId, LocalDate date) {
		return speechEvaluationRepository.findByPatient_IdAndDate(patientId, date);
	}
	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<SpeechEvaluation> evalutionSheets = speechEvaluationRepository.findByPatient_Id( patientId);
		for (SpeechEvaluation evalutionSheet : evalutionSheets) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (evalutionSheet.getDate() != null) {
				dates.add(evalutionSheet.getDate().format(formatter));
			}
		}
		return dates;
	}
	@Override
	public List<SpeechEvaluation> findByPatient_Id(Long patientId) {
		// TODO Auto-generated method stub
		return speechEvaluationRepository.findByPatient_Id(patientId);
	}

	

	
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
