package com.openspace.Model.ParentModule.csbs;

import java.util.List;

public class CsbsQuestionLookupDto {
	
	private Long id;

	private String name;
	
	private List<CsbsAnswerLookupDto> csbsAnswerLookups;

	private CsbsCategoryLookup csbsCategoryLookup;

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


	public List<CsbsAnswerLookupDto> getcsbsAnswerLookups() {
		return csbsAnswerLookups;
	}

	public void setcsbsAnswerLookups(List<CsbsAnswerLookupDto> csbsAnswerLookups) {
		this.csbsAnswerLookups = csbsAnswerLookups;
	}

	public CsbsCategoryLookup getcsbsCategoryLookup() {
		return csbsCategoryLookup;
	}

	public void setCsbsCategoryLookup(CsbsCategoryLookup csbsCategoryLookup) {
		this.csbsCategoryLookup = csbsCategoryLookup;
	}


}
