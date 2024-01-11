package com.openspace.Model.ParentModule.childobservation;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ChildObservationAnswerLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private boolean selectedAnswer;

	private boolean active;

	private ChildObservationQuestionLookup childObservationQuestionLookup;

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
	@JsonIgnore
	public ChildObservationQuestionLookup getChildObservationQuestionLookup() {
		return childObservationQuestionLookup;
	}

	@JsonProperty
	public void setChildObservationQuestionLookup(ChildObservationQuestionLookup childObservationQuestionLookup) {
		this.childObservationQuestionLookup = childObservationQuestionLookup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(boolean selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
