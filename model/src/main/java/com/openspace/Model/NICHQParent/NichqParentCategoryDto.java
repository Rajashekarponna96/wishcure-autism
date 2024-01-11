package com.openspace.Model.NICHQParent;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;

public class NichqParentCategoryDto {

	private Patient patient;

	private List<NICHQParentCategoryLookup> nichqParentCategoryLookupsList;
	
	private NICHQParentCategoryLookup nichqParentCategoryLookup;
	
	//parent categories are already assigned to patient
	private List<NichqParentCategory> nichqParentCategoryList;
	
	private NichqParentCategory nichqParentCategory;
	
	private List<NICHQParentQuestionLookup> nichqParentQuestionLookupList;
	
	private List<NichqParentQuestion> nichqParentQuestionList;
	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<NICHQParentCategoryLookup> getNichqParentCategoryLookupsList() {
		return nichqParentCategoryLookupsList;
	}

	public void setNichqParentCategoryLookupsList(List<NICHQParentCategoryLookup> nichqParentCategoryLookupsList) {
		this.nichqParentCategoryLookupsList = nichqParentCategoryLookupsList;
	}

	public List<NichqParentCategory> getNichqParentCategoryList() {
		return nichqParentCategoryList;
	}

	public void setNichqParentCategoryList(List<NichqParentCategory> nichqParentCategoryList) {
		this.nichqParentCategoryList = nichqParentCategoryList;
	}

	public NICHQParentCategoryLookup getNichqParentCategoryLookup() {
		return nichqParentCategoryLookup;
	}

	public void setNichqParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup) {
		this.nichqParentCategoryLookup = nichqParentCategoryLookup;
	}

	public NichqParentCategory getNichqParentCategory() {
		return nichqParentCategory;
	}

	public void setNichqParentCategory(NichqParentCategory nichqParentCategory) {
		this.nichqParentCategory = nichqParentCategory;
	}

	public List<NICHQParentQuestionLookup> getNichqParentQuestionLookupList() {
		return nichqParentQuestionLookupList;
	}

	public void setNichqParentQuestionLookupList(List<NICHQParentQuestionLookup> nichqParentQuestionLookupList) {
		this.nichqParentQuestionLookupList = nichqParentQuestionLookupList;
	}

	public List<NichqParentQuestion> getNichqParentQuestionList() {
		return nichqParentQuestionList;
	}

	public void setNichqParentQuestionList(List<NichqParentQuestion> nichqParentQuestionList) {
		this.nichqParentQuestionList = nichqParentQuestionList;
	}
	
	
	
	

 }
