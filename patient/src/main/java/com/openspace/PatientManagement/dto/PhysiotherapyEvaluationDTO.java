package com.openspace.PatientManagement.dto;

import java.util.List;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategory;
import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryLookup;
import com.openspace.Model.DoctorManagement.Patient;

public class PhysiotherapyEvaluationDTO {
	
	private Patient patient;
	
	private List<PhysiotherapyEvaluationCategoryLookup> physioEvalCatLookup;
	
	private List<PhysiotherapyEvaluationCategory> physioEvalCat;
	
	private PhysiotherapyEvaluationCategoryLookup phyCategoryLookup;
	
	private String chiefComplaints;
	
	private String briefHistory;
	
	private String examinations;

	public Patient getPatient() {
		return patient;
	}

	public List<PhysiotherapyEvaluationCategoryLookup> getPhysioEvalCatLookup() {
		return physioEvalCatLookup;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setPhysioEvalCatLookup(List<PhysiotherapyEvaluationCategoryLookup> physioEvalCatLookup) {
		this.physioEvalCatLookup = physioEvalCatLookup;
	}

	public String getChiefComplaints() {
		return chiefComplaints;
	}

	public String getBriefHistory() {
		return briefHistory;
	}

	public String getExaminations() {
		return examinations;
	}

	public void setChiefComplaints(String chiefComplaints) {
		this.chiefComplaints = chiefComplaints;
	}

	public void setBriefHistory(String briefHistory) {
		this.briefHistory = briefHistory;
	}

	public void setExaminations(String examinations) {
		this.examinations = examinations;
	}

	public List<PhysiotherapyEvaluationCategory> getPhysioEvalCat() {
		return physioEvalCat;
	}

	public void setPhysioEvalCat(List<PhysiotherapyEvaluationCategory> physioEvalCat) {
		this.physioEvalCat = physioEvalCat;
	}

	public PhysiotherapyEvaluationCategoryLookup getPhyCategoryLookup() {
		return phyCategoryLookup;
	}

	public void setPhyCategoryLookup(PhysiotherapyEvaluationCategoryLookup phyCategoryLookup) {
		this.phyCategoryLookup = phyCategoryLookup;
	}
	
	
	
	
	
	
	
	
	
	

}
