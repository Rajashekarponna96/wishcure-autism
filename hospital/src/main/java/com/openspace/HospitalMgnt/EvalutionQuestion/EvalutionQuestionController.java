package com.openspace.HospitalMgnt.EvalutionQuestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Template.EvalutionQuestion;


@RestController
public class EvalutionQuestionController {

	@Autowired
	private EvalutionQuestionService evalutionQuestionService;
	
	@RequestMapping(value=RestURIConstants.SAVE_EVALUTION_QUESTION, method=RequestMethod.POST)
	public @ResponseBody void saveEvalutionQuestion(@RequestBody EvalutionQuestion evalutionQuestion ){
		evalutionQuestionService.saveEvalutionQuestion(evalutionQuestion);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_EVALUTION_QUESTIONS, method=RequestMethod.GET)
	public @ResponseBody List<EvalutionQuestion> getAllEvalutionQuestions(){
		return evalutionQuestionService.getAllEvalutionQuestions();
	}
	@RequestMapping(value=RestURIConstants.UPDATE_EVALUTION_QUESTION, method=RequestMethod.PUT)
	public @ResponseBody void updateEvalutionQuestion(@RequestBody EvalutionQuestion evalutionQuestion){
		evalutionQuestionService.updateEvalutionQuestion(evalutionQuestion);
	}
	@RequestMapping(value=RestURIConstants.DELETE_EVALUTION_QUESTION,method=RequestMethod.DELETE)
	public @ResponseBody void deleteEvalutionQuestion(@PathVariable Long id){
		evalutionQuestionService.deleteEvalutionQuestion(id);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_EVALUTION_QUESTIONS_BY_CATEGORYID, method=RequestMethod.GET)
	public @ResponseBody List<EvalutionQuestion> getAllEvalutionQuestionsByCategoryId(@PathVariable Long categoryId){
		return evalutionQuestionService.getAllEvalutionQuestionsByCategoryId(categoryId);
	}
	
	
}
