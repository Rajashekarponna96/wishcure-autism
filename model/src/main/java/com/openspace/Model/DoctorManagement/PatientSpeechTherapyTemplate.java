package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;

@Entity
public class PatientSpeechTherapyTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String templateName;

	private PatientStandardScoreHeader patientStandardScoreHeader;

	/* private List<PatientStandardScore> patientStandardScores; */

	private List<PatientSpeechSubTest> patientSpeechsubTests;

	private String status;

	private Patient patient;

	private String evalutionSheetStatus;

	private EvalutionSheet evalutionSheet;

	private PatientStandardScore patientStandardScore;

	private LocalDate presentDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientSpeechTherapyTemplate")
	public List<PatientSpeechSubTest> getPatientSpeechsubTests() {
		return patientSpeechsubTests;
	}

	public void setPatientSpeechsubTests(List<PatientSpeechSubTest> patientSpeechsubTests) {
		this.patientSpeechsubTests = patientSpeechsubTests;
	}
	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy =
	 * "patientSpeechTherapyTemplate") public List<PatientStandardScore>
	 * getPatientStandardScores() { return patientStandardScores; }
	 * 
	 * public void setPatientStandardScores(List<PatientStandardScore>
	 * patientStandardScores) { this.patientStandardScores =
	 * patientStandardScores; }
	 */

	@OneToOne
	public PatientStandardScoreHeader getPatientStandardScoreHeader() {
		return patientStandardScoreHeader;
	}

	public void setPatientStandardScoreHeader(PatientStandardScoreHeader patientStandardScoreHeader) {
		this.patientStandardScoreHeader = patientStandardScoreHeader;
	}

	public String getEvalutionSheetStatus() {
		return evalutionSheetStatus;
	}

	public void setEvalutionSheetStatus(String evalutionSheetStatus) {
		this.evalutionSheetStatus = evalutionSheetStatus;
	}

	@JsonIgnore
	@ManyToOne
	public EvalutionSheet getEvalutionSheet() {
		return evalutionSheet;
	}

	@JsonProperty
	public void setEvalutionSheet(EvalutionSheet evalutionSheet) {
		this.evalutionSheet = evalutionSheet;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public PatientStandardScore getPatientStandardScore() {
		return patientStandardScore;
	}

	public void setPatientStandardScore(PatientStandardScore patientStandardScore) {
		this.patientStandardScore = patientStandardScore;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getPresentDate() {
		return presentDate;
	}

	public void setPresentDate(LocalDate presentDate) {
		this.presentDate = presentDate;
	}

}
