package com.openspace.PatientManagement.screeningtestLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestQuestionLookup;
@RestController
public class ScreeningTestQuestionLookupController {

	@Autowired
	private ScreeningTestQuestionLookupService screeningTestQuestionLookupService;
	
	@RequestMapping(value = "/saveScreeningTestQuestionLookup", method = RequestMethod.POST)
	public @ResponseBody void saveScreeningTestQuestionLookup(@RequestBody ScreeningTestQuestionLookup screeningTestQuestionLookup){
		screeningTestQuestionLookupService.saveScreeningTestQuestionLookup(screeningTestQuestionLookup);
	}

	@RequestMapping(value = "/getAllScreeningTestQuestionLookup", method = RequestMethod.GET)
	public @ResponseBody List<ScreeningTestQuestionLookup> getAllScreeningTestQuestionLookup(){
		return screeningTestQuestionLookupService.getAllScreeningTestQuestionLookup();
	}
	
	@RequestMapping(value = "/updateScreeningTestQuestionLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateScreeningTestQuestionLookup(@RequestBody ScreeningTestQuestionLookup screeningTestQuestionLookup){
		screeningTestQuestionLookupService.updateScreeningTestLookup(screeningTestQuestionLookup);
	}

	@RequestMapping(value = "/deleteScreeningTestQuestionLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteScreeningTestQuestionLookup(@PathVariable Long id){
		screeningTestQuestionLookupService.deleteScreeningTestQuestionLookup(id);
	}

}
