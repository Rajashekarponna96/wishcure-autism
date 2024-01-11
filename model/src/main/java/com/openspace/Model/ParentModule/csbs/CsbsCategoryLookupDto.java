package com.openspace.Model.ParentModule.csbs;

import java.util.List;

public class CsbsCategoryLookupDto {
	private Long id;

	private String name;
	
	private String categoryStatus;
	
	private List<CsbsQuestionLookupDto> csbsQuestionLookups;

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

	public List<CsbsQuestionLookupDto> getcsbsQuestionLookups() {
		return csbsQuestionLookups;
	}

	public void setcsbsQuestionLookups(List<CsbsQuestionLookupDto> csbsQuestionLookups) {
		this.csbsQuestionLookups = csbsQuestionLookups;
	}


}
