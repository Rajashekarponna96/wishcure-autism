package com.openspace.Model.ParentModule.csbs;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class CsbsCategory implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private List<CsbsQuestion> csbsQuestions;
	
	private UserAccount userAccount;
	
	private Patient patient;
	
	private CsbsCategoryLookup csbsCategoryLookup;
	

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

	@OneToMany(cascade=CascadeType.ALL,mappedBy="csbsCategory")
	public List<CsbsQuestion> getcsbsQuestions() {
		return csbsQuestions;
	}

	public void setcsbsQuestions(List<CsbsQuestion> csbsQuestions) {
		this.csbsQuestions = csbsQuestions;
	}
	
	@OneToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	

	@ManyToOne
	public CsbsCategoryLookup getcsbsCategoryLookup() {
		return csbsCategoryLookup;
	}

	public void setcsbsCategoryLookup(CsbsCategoryLookup csbsCategoryLookup) {
		this.csbsCategoryLookup = csbsCategoryLookup;
	}

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	public Patient getPatient() {
		return patient;
	}

	@JsonProperty
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	

}
