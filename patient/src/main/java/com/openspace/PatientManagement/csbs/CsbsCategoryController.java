package com.openspace.PatientManagement.csbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.CsbsObjectDto;
import com.openspace.Model.ParentModule.csbs.CsbsCategory;
import com.openspace.PatientManagement.dto.CSBSCategoryDto;

@RestController
public class CsbsCategoryController {
	@Autowired
	private CsbsCategoryService csbsCategoryService;

	@RequestMapping(value = "/savecsbsCategoryQuestions/{userName:.+}", method = RequestMethod.POST)
	public @ResponseBody void savecsbsCategoryQuestions(@PathVariable("userName") String userName,
			@RequestBody CsbsObjectDto CsbsObjectDto) {
		csbsCategoryService.savecsbsCategoryQuestions(userName, CsbsObjectDto);
	}

	@RequestMapping(value = "/savecsbsCategoryQuestionsToPatient", method = RequestMethod.POST)
	public @ResponseBody void savecsbsCategoryQuestionsToPatient(@RequestBody CSBSCategoryDto csbsCategoryDto) {
		csbsCategoryService.savecsbsCategoryQuestionsToPatient(csbsCategoryDto);
	}

	
	@RequestMapping(value = "/getAllcsbsCategoryQuestions/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<CsbsCategory> getAllcsbsCategoryQuestions(@PathVariable("categoryId") Long categoryId) {
		return csbsCategoryService.getAllcsbsCategoryQuestions(categoryId);
	}
	
	@RequestMapping(value = "/getAllcsbsCategoryQuestionsByPatient/{patientId}/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<CsbsCategory> getAllcsbsCategoryQuestionsByPatient(@PathVariable("patientId") Long patientId,@PathVariable("categoryId") Long categoryId) {
		return csbsCategoryService.getAllcsbsCategoryQuestionsByPatient(patientId,categoryId);
	}


	@RequestMapping(value = "/updatecsbsCategoryQuestions", method = RequestMethod.PUT)
	public @ResponseBody void updatecsbsCategoryQuestions(@RequestBody CsbsCategory csbsCategory) {
		csbsCategoryService.updatecsbsCategoryQuestions(csbsCategory);
	}

	@RequestMapping(value = "/deletecsbsCategoryQuestions/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deletecsbsCategoryQuestions(@PathVariable Long id) {
		csbsCategoryService.deletecsbsCategoryQuestions(id);
	}
}
