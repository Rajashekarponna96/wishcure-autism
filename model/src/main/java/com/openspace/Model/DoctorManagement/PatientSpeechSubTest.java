package com.openspace.Model.DoctorManagement;

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

@Entity
public class PatientSpeechSubTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String subTestName;

	private PatientSpeechTherapyTemplate patientSpeechTherapyTemplate;
	
	private PatientStandardScore patientSpeechStandardScore;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubTestName() {
		return subTestName;
	}

	public void setSubTestName(String subTestName) {
		this.subTestName = subTestName;
	}

	@JsonIgnore
	@ManyToOne
	public PatientSpeechTherapyTemplate getPatientSpeechTherapyTemplate() {
		return patientSpeechTherapyTemplate;
	}

	@JsonProperty
	public void setPatientSpeechTherapyTemplate(PatientSpeechTherapyTemplate patientSpeechTherapyTemplate) {
		this.patientSpeechTherapyTemplate = patientSpeechTherapyTemplate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public PatientStandardScore getPatientSpeechStandardScore() {
		return patientSpeechStandardScore;
	}

	public void setPatientSpeechStandardScore(PatientStandardScore patientSpeechStandardScore) {
		this.patientSpeechStandardScore = patientSpeechStandardScore;
	}

	
	
	
	
}
