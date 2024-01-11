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
public class EvalutionSheetResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String auditotyCategoryName;

	private Long auditotyStandardScore;

	private Long auditotyAge;

	private Long auditotyDelay;

	private Long auditotyPercentile;

	private String expressiveCategoryName;

	private Long expressiveStandardScore;

	private Long expressiveAge;

	private Long expressiveDelay;

	private Long expressivePercentile;

	private EvalutionSheet evalutionSheet;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuditotyCategoryName() {
		return auditotyCategoryName;
	}

	public void setAuditotyCategoryName(String auditotyCategoryName) {
		this.auditotyCategoryName = auditotyCategoryName;
	}

	public Long getAuditotyStandardScore() {
		return auditotyStandardScore;
	}

	public void setAuditotyStandardScore(Long auditotyStandardScore) {
		this.auditotyStandardScore = auditotyStandardScore;
	}

	public Long getAuditotyAge() {
		return auditotyAge;
	}

	public void setAuditotyAge(Long auditotyAge) {
		this.auditotyAge = auditotyAge;
	}

	public Long getAuditotyDelay() {
		return auditotyDelay;
	}

	public void setAuditotyDelay(Long auditotyDelay) {
		this.auditotyDelay = auditotyDelay;
	}

	public Long getAuditotyPercentile() {
		return auditotyPercentile;
	}

	public void setAuditotyPercentile(Long auditotyPercentile) {
		this.auditotyPercentile = auditotyPercentile;
	}

	public String getExpressiveCategoryName() {
		return expressiveCategoryName;
	}

	public void setExpressiveCategoryName(String expressiveCategoryName) {
		this.expressiveCategoryName = expressiveCategoryName;
	}

	public Long getExpressiveStandardScore() {
		return expressiveStandardScore;
	}

	public void setExpressiveStandardScore(Long expressiveStandardScore) {
		this.expressiveStandardScore = expressiveStandardScore;
	}

	public Long getExpressiveAge() {
		return expressiveAge;
	}

	public void setExpressiveAge(Long expressiveAge) {
		this.expressiveAge = expressiveAge;
	}

	public Long getExpressiveDelay() {
		return expressiveDelay;
	}

	public void setExpressiveDelay(Long expressiveDelay) {
		this.expressiveDelay = expressiveDelay;
	}

	public Long getExpressivePercentile() {
		return expressivePercentile;
	}

	public void setExpressivePercentile(Long expressivePercentile) {
		this.expressivePercentile = expressivePercentile;
	}

	@JsonIgnore
	@OneToOne
	public EvalutionSheet getEvalutionSheet() {
		return evalutionSheet;
	}

	@JsonProperty
	public void setEvalutionSheet(EvalutionSheet evalutionSheet) {
		this.evalutionSheet = evalutionSheet;
	}

}
