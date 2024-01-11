package com.openspace.Model.NICHQParent;

import java.util.List;

public class NICHQParentCategoryLookupDto {
	
	private Long id;
	
	private String name;
	
	private String categoryStatus;
	
	private List<NICHQParentQuestionLookupDto> nichqParentQuestionLookupDto;

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

	public List<NICHQParentQuestionLookupDto> getNichqParentQuestionLookupDto() {
		return nichqParentQuestionLookupDto;
	}

	public void setNichqParentQuestionLookupDto(List<NICHQParentQuestionLookupDto> nichqParentQuestionLookupDto) {
		this.nichqParentQuestionLookupDto = nichqParentQuestionLookupDto;
	}
	
	

}
