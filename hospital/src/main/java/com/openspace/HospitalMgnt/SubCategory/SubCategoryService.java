package com.openspace.HospitalMgnt.SubCategory;

import java.util.List;

import com.openspace.Model.Lookups.SubCategoryType;

public interface SubCategoryService {
	
	void saveSubCategory(SubCategoryType subcategory);

	List<SubCategoryType> getAllSubCategorys();

	void updateSubCategory(SubCategoryType subcategory);

	void deleteSubCategory(Long id);
	
	List<SubCategoryType> getAllSubCategoryTypesListByCategoryType(Long id);

}
