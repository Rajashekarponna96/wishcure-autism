/*package com.openspace.HospitalMgnt.Answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.Answer;

@RestController
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;

	@RequestMapping(value = RestURIConstants.SAVE_ANSWER)
	public @ResponseBody void saveanswer(@RequestBody Answer answer) {
		answerService.saveAnswer(answer);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_ANSWERS, method = RequestMethod.GET)
	public @ResponseBody List<Answer> getAllanswers() {
		return answerService.getAllAnswers();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_ANSWER, method = RequestMethod.PUT)
	public @ResponseBody void updateFeatureLoookup(@RequestBody Answer answer) {
		answerService.updateAnswer(answer);
	}

	@RequestMapping(value = RestURIConstants.DELETE_ANSWER, method = RequestMethod.DELETE)
	public @ResponseBody void deleteanswer(@PathVariable Long id) {
		answerService.deleteAnswer(id);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_ANSWERS_BYQUESTION_ID, method = RequestMethod.GET)
	public @ResponseBody List<Answer> getAllanswersByQuestionId(@PathVariable Long questionId) {
		return answerService.getAllanswersByQuestionId(questionId);
	}
	

}
*/