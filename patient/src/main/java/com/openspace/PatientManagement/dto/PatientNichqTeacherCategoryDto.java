package com.openspace.PatientManagement.dto;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherQuestionLookup;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherCategory;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherQuestion;

public class PatientNichqTeacherCategoryDto {
	
	private Patient patient;
	
	private NichqTeacherCategoryLookup nichqTeacherCategoryLookup;
	
	private PatientNichqTeacherCategory patientNichqTeacherCategory;
	
	private List<PatientNichqTeacherQuestion> patientNichqTeacherQuestionsList;
	
	private List<NichqTeacherQuestionLookup> nichqTeacherQuestionLookupsList;
	
	
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public NichqTeacherCategoryLookup getNichqTeacherCategoryLookup() {
		return nichqTeacherCategoryLookup;
	}

	public void setNichqTeacherCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		this.nichqTeacherCategoryLookup = nichqTeacherCategoryLookup;
	}

	public PatientNichqTeacherCategory getPatientNichqTeacherCategory() {
		return patientNichqTeacherCategory;
	}

	public void setPatientNichqTeacherCategory(PatientNichqTeacherCategory patientNichqTeacherCategory) {
		this.patientNichqTeacherCategory = patientNichqTeacherCategory;
	}

	public List<PatientNichqTeacherQuestion> getPatientNichqTeacherQuestionsList() {
		return patientNichqTeacherQuestionsList;
	}

	public void setPatientNichqTeacherQuestionsList(List<PatientNichqTeacherQuestion> patientNichqTeacherQuestionsList) {
		this.patientNichqTeacherQuestionsList = patientNichqTeacherQuestionsList;
	}

	public List<NichqTeacherQuestionLookup> getNichqTeacherQuestionLookupsList() {
		return nichqTeacherQuestionLookupsList;
	}

	public void setNichqTeacherQuestionLookupsList(List<NichqTeacherQuestionLookup> nichqTeacherQuestionLookupsList) {
		this.nichqTeacherQuestionLookupsList = nichqTeacherQuestionLookupsList;
	}
	
}
