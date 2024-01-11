package com.openspace.Model.NICHQParent;

import java.util.List;

public class NICHQParentQuestionLookupDto {
	
	private Long id;
	
	private String name;
	
	private List<NICHQParentAnswerLookupDto> nichqParentAnswerLookupDto;
	
	private NICHQParentCategoryLookup nichqParentCategoryLookup;

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

	public List<NICHQParentAnswerLookupDto> getNichqParentAnswerLookupDto() {
		return nichqParentAnswerLookupDto;
	}

	public void setNichqParentAnswerLookupDto(List<NICHQParentAnswerLookupDto> nichqParentAnswerLookupDto) {
		this.nichqParentAnswerLookupDto = nichqParentAnswerLookupDto;
	}

	public NICHQParentCategoryLookup getNichqParentCategoryLookup() {
		return nichqParentCategoryLookup;
	}

	public void setNichqParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup) {
		this.nichqParentCategoryLookup = nichqParentCategoryLookup;
	}
	
	
}
