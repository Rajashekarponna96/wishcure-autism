package com.openspace.Model.ParentModule.csbs;

public class CsbsAnswerLookupDto {
	
	private Long id;

	private String name;

	private boolean selectedAnswer;

	private boolean active;

	private CsbsQuestionLookup csbsQuestionLookup;

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

	public CsbsQuestionLookup getcsbsQuestionLookup() {
		return csbsQuestionLookup;
	}

	public void setCsbsQuestionLookup(CsbsQuestionLookup csbsQuestionLookup) {
		this.csbsQuestionLookup = csbsQuestionLookup;
	}

}
