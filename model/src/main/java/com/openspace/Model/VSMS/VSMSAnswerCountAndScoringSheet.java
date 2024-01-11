package com.openspace.Model.VSMS;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VSMSAnswerCountAndScoringSheet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private int answerCount;
	
	private String ageInYears;
	
	private String scoringAge;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public String getAgeInYears() {
		return ageInYears;
	}

	public void setAgeInYears(String ageInYears) {
		this.ageInYears = ageInYears;
	}

	public String getScoringAge() {
		return scoringAge;
	}

	public void setScoringAge(String scoringAge) {
		this.scoringAge = scoringAge;
	}

	

}
