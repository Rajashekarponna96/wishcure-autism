package com.openspace.Model.Parent;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ParentAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String shortAnswer;

	private Boolean selectedAnswer;

	private ParentQuestion parentQuestion;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortAnswer() {
		return shortAnswer;
	}

	public void setShortAnswer(String shortAnswer) {
		this.shortAnswer = shortAnswer;
	}

	public Boolean getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Boolean selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	@JsonIgnore
	@ManyToOne
	public ParentQuestion getParentQuestion() {
		return parentQuestion;
	}

	@JsonProperty
	public void setParentQuestion(ParentQuestion parentQuestion) {
		this.parentQuestion = parentQuestion;
	}

}
