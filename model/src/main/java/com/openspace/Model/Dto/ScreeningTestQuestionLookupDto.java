package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategoryLookup;

public class ScreeningTestQuestionLookupDto {
	
	private Long id;

	private String name;
	
	private List<ScreeningTestAnswerLookupDto> screeningTestAnswerLookups;

	private ScreeningTestCategoryLookup screeningTestCategoryLookup;

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


	public List<ScreeningTestAnswerLookupDto> getScreeningTestAnswerLookups() {
		return screeningTestAnswerLookups;
	}

	public void setScreeningTestAnswerLookups(List<ScreeningTestAnswerLookupDto> screeningTestAnswerLookups) {
		this.screeningTestAnswerLookups = screeningTestAnswerLookups;
	}

	public ScreeningTestCategoryLookup getScreeningTestCategoryLookup() {
		return screeningTestCategoryLookup;
	}

	public void setScreeningTestCategoryLookup(ScreeningTestCategoryLookup screeningTestCategoryLookup) {
		this.screeningTestCategoryLookup = screeningTestCategoryLookup;
	}


}
