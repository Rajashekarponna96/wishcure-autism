package com.openspace.HospitalMgnt.Category;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void saveCategory(Category category) {
		Category dbFeatureLookup = categoryRepository.findByCategoryName(category.getCategoryName());
		if (dbFeatureLookup != null) {
			throw new RuntimeException("Category already exists");
		}
		category.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		category.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		category.setStatus(true);
		categoryRepository.save(category);

	}

	@Override
	public List<Category> getAllCategorys() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public void updateCategory(Category category) {
		Category dbCategory = categoryRepository.findOne(category.getId());
		if (dbCategory == null) {
			throw new RuntimeException("Category Doesn't Exists");
		}
		Category dbCategory2 = categoryRepository.findByCategoryName(category.getCategoryName());
		if (dbCategory2 == null) {
			dbCategory.setCategoryName(category.getCategoryName());
			dbCategory.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
			dbCategory.setCategoryDescription(category.getCategoryDescription());
			dbCategory.setStatus(category.getStatus());

		} else if (dbCategory2.getId().equals(category.getId())) {
			dbCategory.setCategoryName(category.getCategoryName());
			dbCategory.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
			dbCategory.setCategoryDescription(category.getCategoryDescription());
			dbCategory.setStatus(category.getStatus());

		} else {
			throw new RuntimeException("Category Already Exists");
		}
		dbCategory.setCategoryName(category.getCategoryName());
		dbCategory.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		dbCategory.setCategoryDescription(category.getCategoryDescription());
		dbCategory.setStatus(category.getStatus());
		categoryRepository.save(dbCategory);
	}

	@Override
	public void deleteCategory(Long id) {
		Category dbCategory = categoryRepository.findOne(id);
		if (dbCategory == null) {
			throw new RuntimeException("Category Doesn't exists");
		}
		categoryRepository.delete(dbCategory);
	}

}
