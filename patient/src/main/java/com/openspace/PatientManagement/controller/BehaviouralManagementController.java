package com.openspace.PatientManagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.SpeechTheraphyTemplate.BehaviourMangementProgramme;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechEvaluation;
import com.openspace.PatientManagement.service.BehaviourlManagementService;
import com.openspace.PatientManagement.service.PatientService;
import com.openspace.PatientManagement.service.SpeechEvaluationService;

@RestController
@RequestMapping(value = "/behaviourlManagement")
public class BehaviouralManagementController {

	
	  @Autowired private BehaviourlManagementService behaviourlManagementService;
	 

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/{patientId}", method = RequestMethod.POST)
	public void save(@PathVariable("patientId") Long patientId, @RequestBody BehaviourMangementProgramme behaviourMangementProgramme) {
		Patient patient = patientService.findById(patientId);
		behaviourMangementProgramme.setPatient(patient);
		behaviourMangementProgramme.setDate(LocalDate.now());
		behaviourlManagementService.save(behaviourMangementProgramme);
	}

	@RequestMapping(value = "/findByPatient_IdAndDate/{patientId}/{date}", method = RequestMethod.GET)
	public BehaviourMangementProgramme getTemplateByStatus(@PathVariable("patientId") Long patientId, @PathVariable("date") LocalDate date ) {
		return behaviourlManagementService.findByPatient_IdAndDate(patientId, date);
	}

	@RequestMapping(value = "/getAllPatientBehaviouralManagement/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<BehaviourMangementProgramme> getAllPatientBehaviouralManagementByPatientId(@PathVariable("patientId") Long patientId) {
		return behaviourlManagementService.getAllPatientBehaviouralManagementByPatientId(patientId);
	}

	

}
