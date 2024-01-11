package com.openspace.Model.ParentModule.ISAABehaviour;

public class ISAABehaviorAnswerLookupDto {
	
	private Long id;

	private String name;

	private boolean selectedAnswer;

	private boolean active;

	private ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup;

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

	public ISAABehaviorQuestionLookup getiSAABehaviorQuestionLookup() {
		return iSAABehaviorQuestionLookup;
	}

	public void setiSAABehaviorQuestionLookup(ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup) {
		this.iSAABehaviorQuestionLookup = iSAABehaviorQuestionLookup;
	}

}
