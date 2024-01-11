package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategory;

public class ScreeningTestGoalObjectDto {
	
private List<ScreeningTestCategoryLookupDto> screeningTestCategoryLookupDto;
	
	private List<ScreeningTestCategory> screeningTestCategory;
	
	private Long id;
	
	private String date;

	public List<ScreeningTestCategoryLookupDto> getScreeningTestCategoryLookupDto() {
		return screeningTestCategoryLookupDto;
	}

	public void setScreeningTestCategoryLookupDto(List<ScreeningTestCategoryLookupDto> screeningTestCategoryLookupDto) {
		this.screeningTestCategoryLookupDto = screeningTestCategoryLookupDto;
	}

	public List<ScreeningTestCategory> getScreeningTestCategory() {
		return screeningTestCategory;
	}

	public void setScreeningTestCategory(List<ScreeningTestCategory> screeningTestCategory) {
		this.screeningTestCategory = screeningTestCategory;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
