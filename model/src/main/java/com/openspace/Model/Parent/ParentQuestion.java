package com.openspace.Model.Parent;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParentQuestion {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String questionName;

	private List<ParentAnswer> parentAnswers;

	private ParentQuestionCategory parentQuestionCategory;

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

	@OneToMany(cascade = CascadeType.ALL,mappedBy="parentQuestion")
	public List<ParentAnswer> getParentAnswers() {
		return parentAnswers;
	}

	public void setParentAnswers(List<ParentAnswer> parentAnswers) {
		this.parentAnswers = parentAnswers;
	}

	@JsonIgnore
	@ManyToOne
	public ParentQuestionCategory getParentQuestionCategory() {
		return parentQuestionCategory;
	}

	public void setParentQuestionCategory(ParentQuestionCategory parentQuestionCategory) {
		this.parentQuestionCategory = parentQuestionCategory;
	}

}
