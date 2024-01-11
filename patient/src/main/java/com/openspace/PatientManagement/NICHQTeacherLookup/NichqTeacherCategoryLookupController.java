package com.openspace.PatientManagement.NICHQTeacherLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;


@RestController
@RequestMapping(value = "/nichqTeacherCategorylookup")
public class NichqTeacherCategoryLookupController {

	@Autowired
	private NichqTeacherCategoryLookupService nichqTeacherCategoryLookupService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addCategoryLookup(@RequestBody NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		nichqTeacherCategoryLookupService.addCategoryLookup(nichqTeacherCategoryLookup);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<NichqTeacherCategoryLookup> getAllCategoryLookups() {
		return nichqTeacherCategoryLookupService.getAllCategoryLookups();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteCategoryLookup(@PathVariable("id") Long categoryId) {
		 nichqTeacherCategoryLookupService.deleteCategoryLookup(categoryId);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateCategoryLookup(@RequestBody NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		nichqTeacherCategoryLookupService.updateCategoryLookup(nichqTeacherCategoryLookup);
	}
	
	
	
	

}
