package com.openspace.Model.Scales;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;

public class MentalScaleTemplateDto {

	private List<MentalScales> mentalScales;
	
	private Patient patient;

	public List<MentalScales> getMentalScales() {
		return mentalScales;
	}

	public void setMentalScales(List<MentalScales> mentalScales) {
		this.mentalScales = mentalScales;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	

}
