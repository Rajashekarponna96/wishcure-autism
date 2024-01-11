package com.openspace.Model.Lookups;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientRegistrationType;

@Entity
public class CategoryType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private Boolean status;

	private List<Patient> patients;

	// category type i.e early intervention has List of sub categories like ex.
	// developmental delay, cerebral palsy
	
	private List<SubCategoryType> subCategorys;
	
	private PatientRegistrationType patientRegistrationType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@JsonIgnore
	@OneToMany
	public List<Patient> getPatients() {
		return patients;
	}

	@JsonProperty
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryType") public
	 * List<SubCategoryType> getSubCategorys() { return subCategorys; }
	 * 
	 * public void setSubCategorys(List<SubCategoryType> subCategorys) {
	 * this.subCategorys = subCategorys; }
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryType")
	public List<SubCategoryType> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(List<SubCategoryType> subCategorys) {
		this.subCategorys = subCategorys;
	}
	
	@JsonIgnore
	@ManyToOne
	public PatientRegistrationType getPatientRegistrationType() {
		return patientRegistrationType;
	}

	@JsonProperty
	public void setPatientRegistrationType(PatientRegistrationType patientRegistrationType) {
		this.patientRegistrationType = patientRegistrationType;
	}

}
