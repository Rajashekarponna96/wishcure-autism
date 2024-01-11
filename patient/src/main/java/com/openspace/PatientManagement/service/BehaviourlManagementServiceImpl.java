package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.PatientMgnt.Repositories.BehaviourlManagementRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.SpeechTheraphyTemplate.BehaviourMangementProgramme;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechEvaluation;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class BehaviourlManagementServiceImpl implements BehaviourlManagementService{

	@Autowired
	private BehaviourlManagementRepository behaviourlManagementRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	
	@Override
	public BehaviourMangementProgramme findByPatient_IdAndDate(Long patientId, LocalDate date) {
		// TODO Auto-generated method stub
		return behaviourlManagementRepository.findByPatient_IdAndDate(patientId, date);
	}
	@Override
	public void save(BehaviourMangementProgramme behaviourMangementProgramme) {
		behaviourlManagementRepository.save(behaviourMangementProgramme);
		
	}
	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<BehaviourMangementProgramme> evalutionSheets = behaviourlManagementRepository.findByPatient_Id( patientId);
		for (BehaviourMangementProgramme evalutionSheet : evalutionSheets) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (evalutionSheet.getDate() != null) {
				dates.add(evalutionSheet.getDate().format(formatter));
			}
		}
		return dates;
		
		
		
		
	}
	@Override
	public List<BehaviourMangementProgramme> getAllPatientBehaviouralManagementByPatientId(Long patientId) {
		return behaviourlManagementRepository.findByPatient_Id(patientId) ;
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
