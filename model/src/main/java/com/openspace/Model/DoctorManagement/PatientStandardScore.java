package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PatientStandardScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer standardScore;

	private Integer percentaileRank;

	private Integer summaryOfPerformance;

	private String description;

	//private PatientSpeechTherapyTemplate patientSpeechTherapyTemplate;

	private String subtest;

	//private String target;

	//private String substitutions;

	private String wordLevel;

	private Integer initial;

	private Integer medial;

	private Integer ending;
	
	private PatientSpeechSubTest patientSpeechSubTest;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStandardScore() {
		return standardScore;
	}

	public void setStandardScore(Integer standardScore) {
		this.standardScore = standardScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
/*
	@JsonIgnore
	@ManyToOne
	public PatientSpeechTherapyTemplate getPatientSpeechTherapyTemplate() {
		return patientSpeechTherapyTemplate;
	}

	@JsonProperty
	public void setPatientSpeechTherapyTemplate(PatientSpeechTherapyTemplate patientSpeechTherapyTemplate) {
		this.patientSpeechTherapyTemplate = patientSpeechTherapyTemplate;
	}*/

	public String getSubtest() {
		return subtest;
	}

	public void setSubtest(String subtest) {
		this.subtest = subtest;
	}
/*
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(String substitutions) {
		this.substitutions = substitutions;
	}*/

	public String getWordLevel() {
		return wordLevel;
	}

	public void setWordLevel(String wordLevel) {
		this.wordLevel = wordLevel;
	}

	@JsonIgnore
	@OneToOne
	public PatientSpeechSubTest getPatientSpeechSubTest() {
		return patientSpeechSubTest;
	}

	@JsonProperty
	public void setPatientSpeechSubTest(PatientSpeechSubTest patientSpeechSubTest) {
		this.patientSpeechSubTest = patientSpeechSubTest;
	}

	public Integer getPercentaileRank() {
		return percentaileRank;
	}

	public void setPercentaileRank(Integer percentaileRank) {
		this.percentaileRank = percentaileRank;
	}

	public Integer getSummaryOfPerformance() {
		return summaryOfPerformance;
	}

	public void setSummaryOfPerformance(Integer summaryOfPerformance) {
		this.summaryOfPerformance = summaryOfPerformance;
	}

	public Integer getInitial() {
		return initial;
	}

	public void setInitial(Integer initial) {
		this.initial = initial;
	}

	public Integer getMedial() {
		return medial;
	}

	public void setMedial(Integer medial) {
		this.medial = medial;
	}

	public Integer getEnding() {
		return ending;
	}

	public void setEnding(Integer ending) {
		this.ending = ending;
	}
	
	

}
