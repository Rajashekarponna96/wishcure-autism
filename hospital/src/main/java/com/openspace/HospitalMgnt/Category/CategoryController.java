package com.openspace.HospitalMgnt.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.Category;
@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value=RestURIConstants.SAVE_CATEGORY)
	public @ResponseBody void saveCategory(@RequestBody Category category){
		System.out.println("cat name =>"+category.getCategoryName());
		categoryService.saveCategory(category);
	}
	

	@RequestMapping(value = RestURIConstants.GET_ALL_CATEGORYS, method = RequestMethod.GET)
	public @ResponseBody List<Category> getAllCategorys() {
		return categoryService.getAllCategorys();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_CATEGORY, method = RequestMethod.PUT)
	public @ResponseBody void updateFeatureLoookup(@RequestBody Category category) {
		categoryService.updateCategory(category);
	}

	@RequestMapping(value = RestURIConstants.DELETE_CATEGORY, method = RequestMethod.DELETE)
	public @ResponseBody void deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}


}
