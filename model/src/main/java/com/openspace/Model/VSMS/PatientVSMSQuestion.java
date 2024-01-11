package com.openspace.Model.VSMS;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Scales.VSMSQuestion;

@Entity
public class PatientVSMSQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private VSMSQuestion vsmsQuestion;
	
	private Patient patient;
	
	private LocalDate date;

	private VSMSQuestionStatus vsmsQuestionStatus;

	
	private String note;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToOne
	public VSMSQuestion getVsmsQuestion() {
		return vsmsQuestion;
	}

	public void setVsmsQuestion(VSMSQuestion vsmsQuestion) {
		this.vsmsQuestion = vsmsQuestion;
	}

	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Enumerated(EnumType.STRING)
	public VSMSQuestionStatus getVsmsQuestionStatus() {
		return vsmsQuestionStatus;
	}

	public void setVsmsQuestionStatus(VSMSQuestionStatus vsmsQuestionStatus) {
		this.vsmsQuestionStatus = vsmsQuestionStatus;
	}

	

}
