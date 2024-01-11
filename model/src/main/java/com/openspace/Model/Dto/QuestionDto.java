package com.openspace.Model.Dto;

import java.util.List;

public class QuestionDto {

	private Long id;

	private String questionName;

	private List<AnswerDto> answerDtos;
	
	private QuestionCategoryDto questionCategoryDto;

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

	public List<AnswerDto> getAnswerDtos() {
		return answerDtos;
	}

	public void setAnswerDtos(List<AnswerDto> answerDtos) {
		this.answerDtos = answerDtos;
	}

	public QuestionCategoryDto getQuestionCategoryDto() {
		return questionCategoryDto;
	}

	public void setQuestionCategoryDto(QuestionCategoryDto questionCategoryDto) {
		this.questionCategoryDto = questionCategoryDto;
	}

	
	

}
