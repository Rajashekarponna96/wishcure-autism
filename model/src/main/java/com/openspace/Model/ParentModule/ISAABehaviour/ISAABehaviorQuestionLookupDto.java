package com.openspace.Model.ParentModule.ISAABehaviour;

import java.util.List;

public class ISAABehaviorQuestionLookupDto {
	
	private Long id;

	private String name;
	
	private List<ISAABehaviorAnswerLookupDto> iSAABehaviorAnswerLookups;

	private ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup;

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


	public List<ISAABehaviorAnswerLookupDto> getiSAABehaviorAnswerLookups() {
		return iSAABehaviorAnswerLookups;
	}

	public void setiSAABehaviorAnswerLookups(List<ISAABehaviorAnswerLookupDto> iSAABehaviorAnswerLookups) {
		this.iSAABehaviorAnswerLookups = iSAABehaviorAnswerLookups;
	}

	public ISAABehaviorCategoryLookup getiSAABehaviorCategoryLookup() {
		return iSAABehaviorCategoryLookup;
	}

	public void setiSAABehaviorCategoryLookup(ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup) {
		this.iSAABehaviorCategoryLookup = iSAABehaviorCategoryLookup;
	}


}
