package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PatientStandardScoreHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String standardScore;

	private String percentaileRank;

	private String summaryOfPerformance;

	private String description;

	private String subtest;

	private String target;

	private String substitutions;

	private String wordLevel;

	private String initial;

	private String medial;

	private String ending;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStandardScore() {
		return standardScore;
	}

	public void setStandardScore(String standardScore) {
		this.standardScore = standardScore;
	}

	public String getPercentaileRank() {
		return percentaileRank;
	}

	public void setPercentaileRank(String percentaileRank) {
		this.percentaileRank = percentaileRank;
	}

	public String getSummaryOfPerformance() {
		return summaryOfPerformance;
	}

	public void setSummaryOfPerformance(String summaryOfPerformance) {
		this.summaryOfPerformance = summaryOfPerformance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubtest() {
		return subtest;
	}

	public void setSubtest(String subtest) {
		this.subtest = subtest;
	}

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
	}

	public String getWordLevel() {
		return wordLevel;
	}

	public void setWordLevel(String wordLevel) {
		this.wordLevel = wordLevel;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getMedial() {
		return medial;
	}

	public void setMedial(String medial) {
		this.medial = medial;
	}

	public String getEnding() {
		return ending;
	}

	public void setEnding(String ending) {
		this.ending = ending;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
}
