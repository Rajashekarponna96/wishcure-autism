package com.openspace.PatientManagement.dto;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.ParentModule.csbs.CsbsCategory;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;

public class CSBSCategoryDto {
	
	private List<CsbsCategoryLookup> csbsCategoryLookups;
	
	private List<CsbsCategory> csbsCategories;
	
	private Patient patient;

	public List<CsbsCategoryLookup> getCsbsCategoryLookups() {
		return csbsCategoryLookups;
	}

	public void setCsbsCategoryLookups(List<CsbsCategoryLookup> csbsCategoryLookups) {
		this.csbsCategoryLookups = csbsCategoryLookups;
	}

	public List<CsbsCategory> getCsbsCategories() {
		return csbsCategories;
	}

	public void setCsbsCategories(List<CsbsCategory> csbsCategories) {
		this.csbsCategories = csbsCategories;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	

}
