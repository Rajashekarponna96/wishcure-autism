package com.openspace.PatientManagement.csbsLookups;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookup;
@RestController
public class CsbsQuestionLookupController {

	@Autowired
	private CsbsQuestionLookupService csbsQuestionLookupService;
	
	@RequestMapping(value = "/savecsbsQuestionLookup", method = RequestMethod.POST)
	public @ResponseBody void savecsbsQuestionLookup(@RequestBody CsbsQuestionLookup csbsQuestionLookup){
		csbsQuestionLookupService.savecsbsQuestionLookup(csbsQuestionLookup);
	}

	@RequestMapping(value = "/getAllcsbsQuestionLookup", method = RequestMethod.GET)
	public @ResponseBody List<CsbsQuestionLookup> getAllcsbsQuestionLookup(){
		return csbsQuestionLookupService.getAllcsbsQuestionLookup();
	}
	
	@RequestMapping(value = "/getAllcsbsQuestionLookupByCategoryId/{id}", method = RequestMethod.GET)
	public @ResponseBody List<CsbsQuestionLookup> getAllcsbsQuestionLookupByCategoryId(@PathVariable Long id){
		return csbsQuestionLookupService.getAllcsbsQuestionLookupByCategoryId(id);
	}
	
	@RequestMapping(value = "/updatecsbsQuestionLookup", method = RequestMethod.PUT)
	public @ResponseBody void updatecsbsQuestionLookup(@RequestBody CsbsQuestionLookup csbsQuestionLookup){
		csbsQuestionLookupService.updatecsbsLookup(csbsQuestionLookup);
	}

	@RequestMapping(value = "/deletecsbsQuestionLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deletecsbsQuestionLookup(@PathVariable Long id){
		csbsQuestionLookupService.deletecsbsQuestionLookup(id);
	}

}
