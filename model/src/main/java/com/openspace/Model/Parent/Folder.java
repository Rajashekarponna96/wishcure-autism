package com.openspace.Model.Parent;

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
import com.openspace.Model.DoctorManagement.Document;
import com.openspace.Model.DoctorManagement.Patient;

@Entity

public class Folder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private Patient patient;
	
	private List<Document> documents;

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

	@JsonIgnore
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	@JsonProperty
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="folder")
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	

}
