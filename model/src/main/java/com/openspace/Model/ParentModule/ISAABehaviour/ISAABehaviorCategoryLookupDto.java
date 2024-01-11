package com.openspace.Model.ParentModule.ISAABehaviour;

import java.util.List;

public class ISAABehaviorCategoryLookupDto {
	
	private Long id;

	private String name;
	
	private String categoryStatus;
	
	private List<ISAABehaviorQuestionLookupDto> iSAABehaviorQuestionLookups;

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

	public List<ISAABehaviorQuestionLookupDto> getiSAABehaviorQuestionLookups() {
		return iSAABehaviorQuestionLookups;
	}

	public void setiSAABehaviorQuestionLookups(List<ISAABehaviorQuestionLookupDto> iSAABehaviorQuestionLookups) {
		this.iSAABehaviorQuestionLookups = iSAABehaviorQuestionLookups;
	}


}
