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
import com.openspace.Model.SpeechTheraphyTemplate.BehaviourMangementProgramme;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechEvaluation;
import com.openspace.PatientManagement.service.PatientService;
import com.openspace.PatientManagement.service.SpeechEvaluationService;

@RestController
@RequestMapping(value = "/speechEvaluation")
public class SpeechEvaluationController {

	@Autowired
	private SpeechEvaluationService speechEvaluationService;

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/{patientId}", method = RequestMethod.POST)
	public void save(@PathVariable("patientId") Long patientId, @RequestBody SpeechEvaluation speechEvaluation) {
		Patient patient = patientService.findById(patientId);
		speechEvaluation.setPatient(patient);
		speechEvaluation.setDate(LocalDate.now());
		speechEvaluationService.save(speechEvaluation);
	}

	@RequestMapping(value = "/findByPatient_IdAndDate/{patientId}/{date}", method = RequestMethod.GET)
	public SpeechEvaluation getTemplateByStatus(@PathVariable("patientId") Long patientId, @PathVariable("date") LocalDate date ) {
		return speechEvaluationService.findByPatient_IdAndDate(patientId, date);
	}
	@RequestMapping(value = "/getAllPatientSpeechEvaluationByPatientId/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<SpeechEvaluation> getAllPatientBehaviouralManagementByPatientId(@PathVariable("patientId") Long patientId) {
		return speechEvaluationService.findByPatient_Id(patientId);
	}
	

}
