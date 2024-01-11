package com.openspace.Model.NICHQParent;

public class NICHQParentAnswerLookupDto {
	
	private Long id;

	private String name;

	private boolean selectedAnswer;

	private boolean active;
	
	private NICHQParentQuestionLookup nichqParentQuestionLookup;

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

	public NICHQParentQuestionLookup getNichqParentQuestionLookup() {
		return nichqParentQuestionLookup;
	}

	public void setNichqParentQuestionLookup(NICHQParentQuestionLookup nichqParentQuestionLookup) {
		this.nichqParentQuestionLookup = nichqParentQuestionLookup;
	}
	
	

}
