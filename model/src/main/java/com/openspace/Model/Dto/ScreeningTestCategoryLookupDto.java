package com.openspace.Model.Dto;

import java.util.List;

public class ScreeningTestCategoryLookupDto {
	private Long id;

	private String name;
	
	private String categoryStatus;
	
	private List<ScreeningTestQuestionLookupDto> screeningTestQuestionLookups;

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

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public List<ScreeningTestQuestionLookupDto> getScreeningTestQuestionLookups() {
		return screeningTestQuestionLookups;
	}

	public void setScreeningTestQuestionLookups(List<ScreeningTestQuestionLookupDto> screeningTestQuestionLookups) {
		this.screeningTestQuestionLookups = screeningTestQuestionLookups;
	}


}
