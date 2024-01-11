package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PatientAnswer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String shortAnswer;

	private String description;

	private Boolean selectedAnswer;

	private PatientQuestion patientQuestion;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortAnswer() {
		return shortAnswer;
	}

	public void setShortAnswer(String shortAnswer) {
		this.shortAnswer = shortAnswer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	@ManyToOne
	public PatientQuestion getPatientQuestion() {
		return patientQuestion;
	}
	
	@JsonProperty
	public void setPatientQuestion(PatientQuestion patientQuestion) {
		this.patientQuestion = patientQuestion;
	}

	public Boolean getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Boolean selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

}
