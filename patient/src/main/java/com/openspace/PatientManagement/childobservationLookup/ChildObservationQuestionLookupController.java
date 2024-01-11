package com.openspace.PatientManagement.childobservationLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestionLookup;

@RestController
public class ChildObservationQuestionLookupController {

	@Autowired
	private ChildObservationQuestionLookupService childObservationQuestionLookupService;
	
	@RequestMapping(value="/saveChildObservationQuestionLookup",method=RequestMethod.POST)
	public @ResponseBody void saveChildObservationQuestionLookup(@RequestBody ChildObservationQuestionLookup childObservationQuestionLookup){
		childObservationQuestionLookupService.saveChildObservationQuestionLookup(childObservationQuestionLookup);
	}
	
	@RequestMapping(value="/getAllChildObservationQuestionLookups",method=RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestionLookup> getAllChildObservationQuestionLookups(){
		return childObservationQuestionLookupService.getAllChildObservationQuestionLookups();
	}
	
	@RequestMapping(value="/updateChildObservationQuestionLookup",method=RequestMethod.PUT)
	public @ResponseBody void updateChildObservationQuestionLookup(@RequestBody ChildObservationQuestionLookup childObservationQuestionLookup){
		childObservationQuestionLookupService.updateChildObservationQuestionLookup(childObservationQuestionLookup);
	}
	
	@RequestMapping(value="/deleteChildObservationQuestionLookup/{id}",method=RequestMethod.DELETE)
	public @ResponseBody void deleteChildObservationQuestionLookup(@PathVariable("id") Long id){
		childObservationQuestionLookupService.deleteChildObservationQuestionLookup(id);
	}
	
	@RequestMapping(value = "/getAllChildObservationQuestionLookupsByCategoryStatus/{categorystatus}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestionLookup> getAllChildObservationQuestionLookupsByStatus(@PathVariable Long categorystatus){
		return childObservationQuestionLookupService.getAllChildObservationQuestionLookupsByStatus(categorystatus);
		
	}
}
