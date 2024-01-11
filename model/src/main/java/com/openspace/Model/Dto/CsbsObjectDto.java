package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.ParentModule.csbs.CsbsCategory;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookupDto;

public class CsbsObjectDto {

	private List<CsbsCategoryLookupDto> csbsCategoryLookupDto;
	
	private List<CsbsCategory> csbsCategory;
	
	private Long id;

	public List<CsbsCategoryLookupDto> getCsbsCategoryLookupDto() {
		return csbsCategoryLookupDto;
	}

	public void setCsbsCategoryLookupDto(List<CsbsCategoryLookupDto> csbsCategoryLookupDto) {
		this.csbsCategoryLookupDto = csbsCategoryLookupDto;
	}

	public List<CsbsCategory> getCsbsCategory() {
		return csbsCategory;
	}

	public void setCsbsCategory(List<CsbsCategory> csbsCategory) {
		this.csbsCategory = csbsCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	


}


