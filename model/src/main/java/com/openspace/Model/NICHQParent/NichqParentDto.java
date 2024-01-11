package com.openspace.Model.NICHQParent;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;

public class NichqParentDto {
	
	private Patient patient;
	
	private List<NichqParentCategory> nichqParentCategoryList;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<NichqParentCategory> getNichqParentCategoryList() {
		return nichqParentCategoryList;
	}

	public void setNichqParentCategoryList(List<NichqParentCategory> nichqParentCategoryList) {
		this.nichqParentCategoryList = nichqParentCategoryList;
	}
	
	

}
