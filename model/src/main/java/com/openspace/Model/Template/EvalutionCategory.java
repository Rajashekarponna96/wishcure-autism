package com.openspace.Model.Template;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EvalutionCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String categoryName;

	private List<EvalutionQuestion> evalutionQuestions;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE)
	public List<EvalutionQuestion> getEvalutionQuestions() {
		return evalutionQuestions;
	}

	public void setEvalutionQuestions(List<EvalutionQuestion> evalutionQuestions) {
		this.evalutionQuestions = evalutionQuestions;
	}

}
