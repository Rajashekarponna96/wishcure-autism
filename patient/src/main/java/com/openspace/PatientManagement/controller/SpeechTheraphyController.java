package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.PatientSpeechTherapyTemplate;
import com.openspace.Model.Template.SpeechTherapyTemplate;
import com.openspace.PatientManagement.service.SpeechTheraphyService;

@RestController
@RequestMapping(value = "/speechTheraphyTemplate")
public class SpeechTheraphyController {

	@Autowired
	private SpeechTheraphyService speechTheraphyService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody SpeechTherapyTemplate speechTherapyTemplate) {
		speechTheraphyService.add(speechTherapyTemplate);
	}

	@RequestMapping(value = "/findoneByStatus/{status}", method = RequestMethod.GET)
	public SpeechTherapyTemplate getTemplateByStatus(@PathVariable("status") String status) {
		return speechTheraphyService.getTemplateByStatus(status);
	}

	@RequestMapping(value = "/assignTemplate/{patientId}/evalutionStatus/{evalutionStatus}", method = RequestMethod.POST)
	public void assignTemplateToPatient(@PathVariable("patientId") Long patientId,
			@PathVariable("evalutionStatus") String evalutionStatus,
			@RequestBody List<SpeechTherapyTemplate> speechTemplates) {
		speechTheraphyService.assignTemplateToPatient(patientId, evalutionStatus, speechTemplates);
	}

	@RequestMapping(value = "/findallBy/{patientId}/evalution/{status}", method = RequestMethod.GET)
	public List<PatientSpeechTherapyTemplate> getPatientSpecchTemplatesByPatientInEvalution(
			@PathVariable("patientId") Long patientId,@PathVariable("status") String status) {
		return speechTheraphyService.getPatientSpecchTemplatesByPatientInEvalution(patientId,status);
	}
	
	@RequestMapping(value = "/findallBy/{patientId}/{tabdate}/progress", method = RequestMethod.GET)
	public List<PatientSpeechTherapyTemplate> getPatientSpecchTemplatesByPatientInProgress(
			@PathVariable("patientId") Long patientId,@PathVariable("tabdate") String tabdate) {
		return speechTheraphyService.getPatientSpecchTemplatesByPatientInProgress(patientId,tabdate);
	}
	
	@RequestMapping(value = "/deleteBy/{patientId}/{status}/{templateId}", method = RequestMethod.DELETE)
	public void deletePatientSpecchTemplatesByPatientAndStatus(
			@PathVariable("patientId") Long patientId,@PathVariable("status") String status,@PathVariable("templateId") Long templateId) {
		 speechTheraphyService.deletePatientSpecchTemplatesByPatientAndStatus(patientId,status,templateId);
	}

}
