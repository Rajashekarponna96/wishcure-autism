package com.openspace.HospitalMgnt.SubCategory;

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
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.SubCategoryType;

@RestController
public class SubCategoryController {
	
	@Autowired
	SubCategoryService subcatService;
	
	@RequestMapping(value=RestURIConstants.SAVE_SUB_CATEGORY)
	public @ResponseBody void saveCategory(@RequestBody SubCategoryType subCategory){
		subcatService.saveSubCategory(subCategory);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_SUB_CATEGORYS, method = RequestMethod.GET)
	public @ResponseBody List<SubCategoryType> getAllSubCategorys() {
		return subcatService.getAllSubCategorys();
	}
	/* UPDATE_SUB_CATEGORY */
	
	@RequestMapping(value = RestURIConstants.UPDATE_SUB_CATEGORY, method = RequestMethod.PUT)
	public @ResponseBody void updateFeatureLoookup(@RequestBody SubCategoryType subCategory) {
		subcatService.updateSubCategory(subCategory);
	}
	
	@RequestMapping(value = RestURIConstants.DELETE_SUB_CATEGORY, method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable(value="id") Long id) {
		subcatService.deleteSubCategory(id);
	}
	
	
	@RequestMapping(value = RestURIConstants.GET_ALL_SUB_CATEGORYS_BY_CATEGORY_TYPE, method = RequestMethod.GET)
	public @ResponseBody List<SubCategoryType> getAllSubCategoryTypesListByCategoryType(@PathVariable Long id) {
		System.out.println("inside sub cat contrlr");
		return subcatService.getAllSubCategoryTypesListByCategoryType(id);
	}


}
