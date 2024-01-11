package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategory;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestion;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookup;

public class ISAABehaviorObjectDto {

	private Patient patient;
	
	private ISAABehaviorCategoryLookup isaaBehaviorCategoryLookup;
	
	private ISAABehaviorCategory isaaBehaviorCategory;
	
	private List<ISAABehaviorQuestion> isaaBehaviorQuestions;
	
	private List<ISAABehaviorQuestionLookup> isaaBehaviorQuestionLookups;


	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ISAABehaviorCategoryLookup getIsaaBehaviorCategoryLookup() {
		return isaaBehaviorCategoryLookup;
	}

	public void setIsaaBehaviorCategoryLookup(ISAABehaviorCategoryLookup isaaBehaviorCategoryLookup) {
		this.isaaBehaviorCategoryLookup = isaaBehaviorCategoryLookup;
	}

	public ISAABehaviorCategory getIsaaBehaviorCategory() {
		return isaaBehaviorCategory;
	}

	public void setIsaaBehaviorCategory(ISAABehaviorCategory isaaBehaviorCategory) {
		this.isaaBehaviorCategory = isaaBehaviorCategory;
	}

	public List<ISAABehaviorQuestion> getIsaaBehaviorQuestions() {
		return isaaBehaviorQuestions;
	}

	public void setIsaaBehaviorQuestions(List<ISAABehaviorQuestion> isaaBehaviorQuestions) {
		this.isaaBehaviorQuestions = isaaBehaviorQuestions;
	}

	public List<ISAABehaviorQuestionLookup> getIsaaBehaviorQuestionLookups() {
		return isaaBehaviorQuestionLookups;
	}

	public void setIsaaBehaviorQuestionLookups(List<ISAABehaviorQuestionLookup> isaaBehaviorQuestionLookups) {
		this.isaaBehaviorQuestionLookups = isaaBehaviorQuestionLookups;
	}
	
}
