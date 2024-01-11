package com.openspace.Model.Template;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EvalutionQuestion {

	private Long id;
	
	private String questionName;

	private EvalutionCategory evalutionCategory;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
    @ManyToOne
	public EvalutionCategory getEvalutionCategory() {
		return evalutionCategory;
	}

	public void setEvalutionCategory(EvalutionCategory evalutionCategory) {
		this.evalutionCategory = evalutionCategory;
	}
	
	
}
