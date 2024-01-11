package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.NICHQParent.NICHQParentCategoryLookupDto;
import com.openspace.Model.NICHQParent.NichqParentCategory;

public class NichqObjectDto {
	
	private Long id;
	
	private List<NichqParentCategory> nichqParentCategory;
	
	private List<NICHQParentCategoryLookupDto>  nICHQParentCategoryLookupDto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<NichqParentCategory> getNichqParentCategory() {
		return nichqParentCategory;
	}

	public void setNichqParentCategory(List<NichqParentCategory> nichqParentCategory) {
		this.nichqParentCategory = nichqParentCategory;
	}

	public List<NICHQParentCategoryLookupDto> getnICHQParentCategoryLookupDto() {
		return nICHQParentCategoryLookupDto;
	}

	public void setnICHQParentCategoryLookupDto(List<NICHQParentCategoryLookupDto> nICHQParentCategoryLookupDto) {
		this.nICHQParentCategoryLookupDto = nICHQParentCategoryLookupDto;
	}

}
