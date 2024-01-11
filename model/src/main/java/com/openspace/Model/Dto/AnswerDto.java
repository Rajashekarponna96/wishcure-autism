package com.openspace.Model.Dto;

public class AnswerDto {
	

	private Long id;

	private String shortAnswer;

	private String description;

	private Boolean selectedAnswer;
	
	private QuestionDto questionDto;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public QuestionDto getQuestionDto() {
		return questionDto;
	}

	public void setQuestionDto(QuestionDto questionDto) {
		this.questionDto = questionDto;
	}

	public Boolean getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Boolean selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
}
