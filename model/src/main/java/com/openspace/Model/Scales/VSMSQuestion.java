package com.openspace.Model.Scales;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.openspace.Model.Clusters.VSMSCluster;
import com.openspace.Model.VSMS.VSMSAnswerCountAndScoringSheet;
import com.openspace.Model.VSMS.VSMSQuestionStatus;

@Entity
public class VSMSQuestion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	 
	private String questionName;
	
	private int questionNumber;
	
	private String ageInYears;
	
	private String ageInMonths;
	
	private Double minAge;
	
	 private Double maxAge;
	
	@Transient
	private String note;
	
	@Transient
	private VSMSQuestionStatus vsmsQuestionStatus;
	
	private VSMSCluster vsmsCluster;
	
	private VSMSAnswerCountAndScoringSheet vsmsAnswerCountAndScoringSheet;

	@ManyToOne
	public VSMSAnswerCountAndScoringSheet getVsmsAnswerCountAndScoringSheet() {
		return vsmsAnswerCountAndScoringSheet;
	}

	public void setVsmsAnswerCountAndScoringSheet(VSMSAnswerCountAndScoringSheet vsmsAnswerCountAndScoringSheet) {
		this.vsmsAnswerCountAndScoringSheet = vsmsAnswerCountAndScoringSheet;
	}

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

	public String getAgeInYears() {
		return ageInYears;
	}

	public void setAgeInYears(String ageInYears) {
		this.ageInYears = ageInYears;
	}

	public String getAgeInMonths() {
		return ageInMonths;
	}

	public void setAgeInMonths(String ageInMonths) {
		this.ageInMonths = ageInMonths;
	}

	@ManyToOne
	public VSMSCluster getVsmsCluster() {
		return vsmsCluster;
	}

	public void setVsmsCluster(VSMSCluster vsmsCluster) {
		this.vsmsCluster = vsmsCluster;
	}

	public VSMSQuestionStatus getVsmsQuestionStatus() {
		return vsmsQuestionStatus;
	}

	public void setVsmsQuestionStatus(VSMSQuestionStatus vsmsQuestionStatus) {
		this.vsmsQuestionStatus = vsmsQuestionStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public Double getMinAge() {
		return minAge;
	}

	public void setMinAge(Double minAge) {
		this.minAge = minAge;
	}

	public Double getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Double maxAge) {
		this.maxAge = maxAge;
	}

	

	

}
