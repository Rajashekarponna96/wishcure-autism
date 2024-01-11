package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.Document;
import com.openspace.Model.DoctorManagement.DocumentType;
import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.PatientManagement.service.CategoryTypeService;

@RestController
@RequestMapping(value = "/CategoryType")
public class CategoryTypeController {

	@Autowired
	private CategoryTypeService categoryTypeService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody CategoryType categoryType) {
		categoryTypeService.add(categoryType);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CategoryType> getAll() {
		return categoryTypeService.getAll();
	}
	
	@RequestMapping(value = "/allByStatus", method = RequestMethod.GET)
	public List<CategoryType> getAllByStatus() {
		return categoryTypeService.getAllByStatus();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateCategory(@RequestBody CategoryType categoryType) {
		 categoryTypeService.updateCategory(categoryType);
	}
	
	
	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable(value="categoryId") Long id) {
		 categoryTypeService.deleteCategory(id);
	}
	
	@RequestMapping(value = "/getAllCatByRegistrationtype/{id}", method = RequestMethod.GET)
	public @ResponseBody List<CategoryType> getAllCategoryTypesListByRegistartionType(@PathVariable Long id) {
		System.out.println("ID "+id);
		return categoryTypeService.getAllCategoryTypesListByRegistartionType(id);
	}
	
	
	
	
	
	
}
