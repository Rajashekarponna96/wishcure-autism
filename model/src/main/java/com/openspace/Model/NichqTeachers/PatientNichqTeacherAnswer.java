package com.openspace.Model.NichqTeachers;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PatientNichqTeacherAnswer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String solution;

	private boolean selectedAnswer;

	private boolean active;

	private PatientNichqTeacherQuestion patientNichqTeacherQuestion;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public boolean isSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(boolean selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@JsonIgnore
	@ManyToOne
	public PatientNichqTeacherQuestion getPatientNichqTeacherQuestion() {
		return patientNichqTeacherQuestion;
	}

	@JsonProperty
	public void setPatientNichqTeacherQuestion(PatientNichqTeacherQuestion patientNichqTeacherQuestion) {
		this.patientNichqTeacherQuestion = patientNichqTeacherQuestion;
	}


}
