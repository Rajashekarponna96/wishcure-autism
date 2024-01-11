package com.openspace.HospitalMgnt.Category;

import java.util.List;

import com.openspace.Model.Lookups.Category;

public interface CategoryService {
	
	void saveCategory(Category category);

	List<Category> getAllCategorys();

	void updateCategory(Category category);

	void deleteCategory(Long id);
}
