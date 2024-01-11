package com.openspace.HospitalMgnt.EvalutionQuestion;

import java.util.List;

import com.openspace.Model.Template.EvalutionQuestion;

public interface EvalutionQuestionService {
	
	void saveEvalutionQuestion(EvalutionQuestion evalutionQuestion);

	List<EvalutionQuestion> getAllEvalutionQuestions();

	void updateEvalutionQuestion(EvalutionQuestion evalutionQuestion);

	void deleteEvalutionQuestion(Long id);

	List<EvalutionQuestion> getAllEvalutionQuestionsByCategoryId(Long categoryId);

}
