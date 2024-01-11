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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PatientQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String questionName;

	private List<PatientAnswer> patientAnswers;

	private PatientQuestionCategory patientQuestionCategory;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientQuestion")
	public List<PatientAnswer> getPatientAnswers() {
		return patientAnswers;
	}

	public void setPatientAnswers(List<PatientAnswer> patientAnswers) {
		this.patientAnswers = patientAnswers;
	}

	@JsonIgnore
	@ManyToOne
	public PatientQuestionCategory getPatientQuestionCategory() {
		return patientQuestionCategory;
	}
	@JsonProperty
	public void setPatientQuestionCategory(PatientQuestionCategory patientQuestionCategory) {
		this.patientQuestionCategory = patientQuestionCategory;
	}

}
