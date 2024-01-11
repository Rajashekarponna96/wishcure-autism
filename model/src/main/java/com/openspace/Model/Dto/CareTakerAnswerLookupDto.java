package com.openspace.Model.Dto;

import java.io.Serializable;

public class CareTakerAnswerLookupDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private boolean selectedAnswer;

	private boolean active;

	private CareTakerQuestionLookupDto careTakerQuestionLookup;

	
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

	
	public CareTakerQuestionLookupDto getCareTakerQuestionLookup() {
		return careTakerQuestionLookup;
	}

	
	public void setCareTakerQuestionLookup(CareTakerQuestionLookupDto careTakerQuestionLookup) {
		this.careTakerQuestionLookup = careTakerQuestionLookup;
	}

}
