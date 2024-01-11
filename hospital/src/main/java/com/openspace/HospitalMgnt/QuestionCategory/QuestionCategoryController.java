package com.openspace.HospitalMgnt.QuestionCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.QuestionCategory;
import com.openspace.Model.Dto.QuestionCategoryDto;

@RestController
public class QuestionCategoryController {

	@Autowired
	private QuestionCategoryService questionCategoryService;

	@RequestMapping(value = RestURIConstants.SAVE_QUESTION_CATEGORY, method = RequestMethod.POST)
	public @ResponseBody void saveQuestionCategory(@RequestBody QuestionCategory questionCategory) {
		questionCategoryService.saveQuestionCategory(questionCategory);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_QUESTION_CATEGORYS, method = RequestMethod.GET)
	public @ResponseBody List<QuestionCategoryDto> getAllQuestionCategoryDtos() {
		return questionCategoryService.getAllCategorys();
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_QUESTION_CATEGORYS_BY_PATIENT_ID, method = RequestMethod.GET)
	public @ResponseBody List<QuestionCategoryDto> getAllQuestionCategoryDtosByPatientId(@PathVariable Long patientId) {
		return questionCategoryService.getAllCategorysByPatientId(patientId);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_LIST_QUESTION_CATEGORYS, method = RequestMethod.GET)
	public @ResponseBody List<QuestionCategoryDto> getAllListQuestionCategoryDtos() {
		return questionCategoryService.getAllListCategorys();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_QUESTION_CATEGORY, method = RequestMethod.PUT)
	public @ResponseBody void updateQuestionCategory(@RequestBody QuestionCategory questionCategory) {
		questionCategoryService.updateQuestionCategory(questionCategory);
	}

	@RequestMapping(value = RestURIConstants.DELETE_QUESTION_CATEGORY, method = RequestMethod.DELETE)
	public @ResponseBody void deleteQuestionCategory(@PathVariable Long id) {
		questionCategoryService.deleteQuestionCategory(id);
	}

	@RequestMapping(value = RestURIConstants.FIND_ALL_QUESTION_CATEGORYS, method = RequestMethod.GET)
	public @ResponseBody List<QuestionCategory> getAllQuestionCategorys() {
		return questionCategoryService.getAllQuestionCategorys();
	}

}
