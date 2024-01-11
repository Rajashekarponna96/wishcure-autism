package com.openspace.HospitalMgnt.Question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.Question;
import com.openspace.Model.Dto.QuestionDto;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value=RestURIConstants.SAVE_QUESTION, method=RequestMethod.POST)
	public @ResponseBody void saveQuestion(@RequestBody Question question){
		questionService.saveQuestion(question);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_QUESTION, method=RequestMethod.GET)
	public @ResponseBody List<QuestionDto> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	@RequestMapping(value=RestURIConstants.UPDATE_QUESTION, method=RequestMethod.PUT)
	public @ResponseBody void updateQuestion(@RequestBody Question question){
		questionService.updateQuestion(question);
	}
	@RequestMapping(value=RestURIConstants.DELETE_QUESTION,method=RequestMethod.DELETE)
	public @ResponseBody void deleteQuestion(@PathVariable Long id){
		questionService.deleteQuestion(id);
	}
	@RequestMapping(value=RestURIConstants.GET_QUESTION, method=RequestMethod.GET)
	public @ResponseBody Question getQuestion(@PathVariable Long id){
		return questionService.getQuestion(id);
	}
	
}

