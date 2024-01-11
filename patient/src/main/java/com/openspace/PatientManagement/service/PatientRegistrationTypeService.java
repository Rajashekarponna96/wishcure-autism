package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.CategoryType;

public interface PatientRegistrationTypeService {

	List<PatientRegistrationType> getAllPatientRegistrationTypes();
	
	void add(PatientRegistrationType patientRegistrationType);

	void deleteRegistration(Long id);

	void updateRegistration(PatientRegistrationType patientRegistrationType);

	List<PatientRegistrationType> getAllByStatus();

}
