package com.openspace.Model.ParentModule.childobservation;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ChildObservationQuestionLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private ChildObservationCategoryLookup childObservationCategoryLookup;

	 private List<ChildObservationAnswerLookup> childObservationAnswerLookups;
	 
	private String answer;

	private boolean selectedAnswer;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	public ChildObservationCategoryLookup getChildObservationCategoryLookup() {
		return childObservationCategoryLookup;
	}

	@JsonProperty
	public void setChildObservationCategoryLookup(ChildObservationCategoryLookup childObservationCategoryLookup) {
		this.childObservationCategoryLookup = childObservationCategoryLookup;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

	public boolean isSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(boolean selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "childObservationQuestionLookup")
	public List<ChildObservationAnswerLookup> getChildObservationAnswerLookups() {
		return childObservationAnswerLookups;
	}

	public void setChildObservationAnswerLookups(List<ChildObservationAnswerLookup> childObservationAnswerLookups) {
		this.childObservationAnswerLookups = childObservationAnswerLookups;
	}
	
	

}
