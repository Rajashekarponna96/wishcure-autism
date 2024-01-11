package com.openspace.PatientManagement.IASSBehaviour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.ISAABehaviorObjectDto;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategory;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorResult;

@RestController
public class ISAABehaviorCategoryController {

	@Autowired
	private ISAABehaviorCategoryService iSAABehaviorCategoryService;

	@RequestMapping(value = "/saveISAABehaviorCategoryQuestions", method = RequestMethod.POST)
	public @ResponseBody void saveISAABehaviorCategoryQuestions(
			@RequestBody ISAABehaviorObjectDto iSAABehaviorObjectDto) {
		iSAABehaviorCategoryService.saveISAABehaviorCategoryQuestions(iSAABehaviorObjectDto);
	}

	@RequestMapping(value = "/getAllISAABehaviorCategoryQuestions/{patientId}/{categorylookupId}", method = RequestMethod.GET)
	public @ResponseBody List<ISAABehaviorCategory> getAllISAABehaviorCategoryQuestions(
			@PathVariable("patientId") Long patentId, @PathVariable("categorylookupId") Long id) {
		return iSAABehaviorCategoryService.getAllISAABehaviorCategoryQuestions(patentId, id);
	}

	@RequestMapping(value = "/getAllISAABehaviorCategorysByPatientId/{patientId}/", method = RequestMethod.GET)
	public @ResponseBody List<ISAABehaviorCategory> getAllISAABehaviorCategorysByPatientId(
			@PathVariable("patientId") Long patentId) {
		return iSAABehaviorCategoryService.getAllISAABehaviorCategoryQuestions(patentId);
	}

	@RequestMapping(value = "/updateISAABehaviorCategoryQuestions", method = RequestMethod.PUT)
	public @ResponseBody void updateISAABehaviorCategoryQuestions(
			@RequestBody ISAABehaviorCategory iSAABehaviorCategory) {
		iSAABehaviorCategoryService.updateISAABehaviorCategoryQuestions(iSAABehaviorCategory);
	}

	@RequestMapping(value = "/deleteISAABehaviorCategoryQuestions/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteISAABehaviorCategoryQuestions(@PathVariable Long id) {
		iSAABehaviorCategoryService.deleteISAABehaviorCategoryQuestions(id);
	}

	@RequestMapping(value = "/isaaBehaviourResult/{patientId}/", method = RequestMethod.GET)
	public @ResponseBody ISAABehaviorResult getISAABehaviorReportByPatientId(
			@PathVariable("patientId") Long patentId) {
		return iSAABehaviorCategoryService.getISAABehaviorReportByPatientId(patentId);
	}
	
	@RequestMapping(value = "/deleteisaaBehaviourResult/{patientId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteISAABehaviorReportByPatientId(
			@PathVariable("patientId") Long patentId) {
		 iSAABehaviorCategoryService.deleteISAABehaviorReportByPatientId(patentId);
	}

}
