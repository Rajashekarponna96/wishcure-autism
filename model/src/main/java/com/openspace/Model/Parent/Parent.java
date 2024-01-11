package com.openspace.Model.Parent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Person;

@Entity
public class Parent extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Patient> patients;

	private List<ParentQuestionCategory> parentQuestionCategories;

	@OneToMany
	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy="parent")
	public List<ParentQuestionCategory> getParentQuestionCategories() {
		return parentQuestionCategories;
	}

	public void setParentQuestionCategories(List<ParentQuestionCategory> parentQuestionCategories) {
		this.parentQuestionCategories = parentQuestionCategories;
	}

}
