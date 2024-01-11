package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.PatientManagement.service.PatientRegistrationTypeService;

@RestController
@RequestMapping(value = "/patientregistrationtype")
public class PatientRegistrationTypeController {

	@Autowired
	private PatientRegistrationTypeService patientRegistrationTypeService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<PatientRegistrationType> getAllPatientRegistrationTypes() {
		System.out.println("inside patient reg type ");
		return patientRegistrationTypeService.getAllPatientRegistrationTypes();
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody PatientRegistrationType patientRegType) {
		patientRegistrationTypeService.add(patientRegType);
	}
	
	@RequestMapping(value = "/delete/{patientRegId}", method = RequestMethod.DELETE)
	public void deletePatientRegType(@PathVariable(value="patientRegId") Long id) {
		patientRegistrationTypeService.deleteRegistration(id);
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updatePatientRegType(@RequestBody PatientRegistrationType patientRegistrationType) {
		System.out.println("inside add contrllr");
		patientRegistrationTypeService.updateRegistration(patientRegistrationType);
	}
	
	
}
