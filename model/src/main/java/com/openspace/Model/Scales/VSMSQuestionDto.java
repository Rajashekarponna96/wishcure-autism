package com.openspace.Model.Scales;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;

public class VSMSQuestionDto {

	private List<VSMSQuestion> vsmsQuestionsList;
	
	private Patient patient;

	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<VSMSQuestion> getVsmsQuestionsList() {
		return vsmsQuestionsList;
	}

	public void setVsmsQuestionsList(List<VSMSQuestion> vsmsQuestionsList) {
		this.vsmsQuestionsList = vsmsQuestionsList;
	}

	
	
	

}
