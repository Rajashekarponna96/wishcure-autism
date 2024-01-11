package com.openspace.Model.DepartmentofPhysiotherapy;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PhysiotherapyEvaluationCategoryAnswers implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String answerName;
	
	private PhysiotherapyEvaluationCategoryQuestions physiotherapyEvaluationCategoryQuestions;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	@ManyToOne
	public PhysiotherapyEvaluationCategoryQuestions getPhysiotherapyEvaluationCategoryQuestions() {
		return physiotherapyEvaluationCategoryQuestions;
	}

	public void setPhysiotherapyEvaluationCategoryQuestions(
			PhysiotherapyEvaluationCategoryQuestions physiotherapyEvaluationCategoryQuestions) {
		this.physiotherapyEvaluationCategoryQuestions = physiotherapyEvaluationCategoryQuestions;
	}
	
	
	
	

}
