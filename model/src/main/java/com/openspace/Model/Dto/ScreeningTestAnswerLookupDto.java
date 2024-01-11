package com.openspace.Model.Dto;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestQuestionLookup;

public class ScreeningTestAnswerLookupDto {
	
	private Long id;

	private String name;

	private boolean selectedAnswer;

	private boolean active;

	private ScreeningTestQuestionLookup screeningTestQuestionLookup;

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

	public ScreeningTestQuestionLookup getScreeningTestQuestionLookup() {
		return screeningTestQuestionLookup;
	}

	public void setScreeningTestQuestionLookup(ScreeningTestQuestionLookup screeningTestQuestionLookup) {
		this.screeningTestQuestionLookup = screeningTestQuestionLookup;
	}

}
