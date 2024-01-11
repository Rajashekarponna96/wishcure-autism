package com.openspace.PatientManagement.childobservationLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategoryLookup;

@RestController
public class ChildObservationCategoryLookupController {

	@Autowired
	private ChildObservationCategoryLookupService childObservationCategoryLookupService;
	
	@RequestMapping(value="/saveChildObservationCategoryLookup",method=RequestMethod.POST)
	public @ResponseBody void saveChildObservationCategoryLookup(@RequestBody ChildObservationCategoryLookup childObservationCategoryLookup){
		childObservationCategoryLookupService.saveChildObservationCategoryLookup(childObservationCategoryLookup);
	}
	
	@RequestMapping(value="/getAllChildObservationCategoryLookups",method=RequestMethod.GET)
	public @ResponseBody List<ChildObservationCategoryLookup> getAllChildObservationCategoryLookups(){
		return childObservationCategoryLookupService.getAllChildObservationCategoryLookups();
	}
	@RequestMapping(value="/getAllChildObservationCategoryLookupsIds",method=RequestMethod.GET)
	public @ResponseBody List<Long> getAllChildObservationCategoryLookupsIds(){
		return childObservationCategoryLookupService.getAllChildObservationCategoryLookupsIds();
	}
	
	@RequestMapping(value="/updateChildObservationCategoryLookup",method=RequestMethod.PUT)
	public @ResponseBody void updateChildObservationCategoryLookup(@RequestBody ChildObservationCategoryLookup childObservationCategoryLookup){
		childObservationCategoryLookupService.updateChildObservationCategoryLookup(childObservationCategoryLookup);
	}
	
	@RequestMapping(value="/deleteChildObservationCategoryLookup/{id}",method=RequestMethod.DELETE)
	public @ResponseBody void deleteChildObservationCategoryLookup(@PathVariable("id") Long id){
		childObservationCategoryLookupService.deleteChildObservationCategoryLookup(id);
	}
}
