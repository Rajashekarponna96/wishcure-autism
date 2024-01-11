package com.openspace.Model.Scales;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;

public class MotorScaleTemplateDto {

	private List<MotorScale> motorScale;
	
	private Patient patient;
	
	

	public List<MotorScale> getMotorScale() {
		return motorScale;
	}

	public void setMotorScales(List<MotorScale> motorScale) {
		this.motorScale = motorScale;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	
	
	

}
