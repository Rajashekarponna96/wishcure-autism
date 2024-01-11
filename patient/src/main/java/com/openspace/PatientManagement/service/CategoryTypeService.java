package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.Department;

public interface CategoryTypeService {

	void add(CategoryType categoryType);

	List<CategoryType> getAll();

	void deleteCategory(Long id);

	void updateCategory(CategoryType categoryType);

	List<CategoryType> getAllByStatus();
	
	List<CategoryType> getAllCategoryTypesListByRegistartionType(Long id);
	
	

}
