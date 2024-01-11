package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
@Access(AccessType.PROPERTY)
public class PatientQuestionCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private int displayOrder;

	private List<PatientQuestion> PatientQuestions;

	private Patient patient;

	private EvalutionSheet evalutionSheet;
	
	private String status; 

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

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientQuestionCategory")
	public List<PatientQuestion> getPatientQuestions() {
		return PatientQuestions;
	}

	public void setPatientQuestions(List<PatientQuestion> patientQuestions) {
		PatientQuestions = patientQuestions;
	}


	@ManyToOne
	public EvalutionSheet getEvalutionSheet() {
		return evalutionSheet;
	}

	@JsonProperty
	public void setEvalutionSheet(EvalutionSheet evalutionSheet) {
		this.evalutionSheet = evalutionSheet;
	}

	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
