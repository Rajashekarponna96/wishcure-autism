package com.openspace.Model.Dto;

import java.util.List;

public class QuestionCategoryDto {

	private Long id;

	private String name;

	private int displayOrder;

	private List<QuestionDto> questionDtos;
	
	private int status;


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

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<QuestionDto> getQuestionDtos() {
		return questionDtos;
	}

	public void setQuestionDtos(List<QuestionDto> questionDtos) {
		this.questionDtos = questionDtos;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
