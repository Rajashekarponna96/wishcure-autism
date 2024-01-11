package com.openspace.PatientManagement.childobservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;

@RestController
public class ChildObservationCategoryController {

	@Autowired
	private ChildObservationCategoryService childObservationCategoryService;
	
	@RequestMapping(value="/saveChildObservationCategory",method=RequestMethod.POST)
	public @ResponseBody void saveChildObservationCategory(@RequestBody ChildObservationCategory childObservationCategory){
		childObservationCategoryService.saveChildObservationCategory(childObservationCategory);
	}
	
	@RequestMapping(value="/getAllChildObservationCategories",method=RequestMethod.GET)
	public @ResponseBody List<ChildObservationCategory> getAllChildObservationCategories(){
		return childObservationCategoryService.getAllChildObservationCategories();
	}
	
	@RequestMapping(value="/updateChildObservationCategory",method=RequestMethod.PUT)
	public @ResponseBody void updateChildObservationCategory(@RequestBody ChildObservationCategory childObservationCategory){
		childObservationCategoryService.updateChildObservationCategory(childObservationCategory);
	}
	
	@RequestMapping(value="/deleteChildObservationCategory/{id}",method=RequestMethod.DELETE)
	public @ResponseBody void deleteChildObservationCategory(@PathVariable("id") Long id){
		childObservationCategoryService.deleteChildObservationCategory(id);
	}
}
