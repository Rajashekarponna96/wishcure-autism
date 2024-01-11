package com.openspace.HospitalMgnt.EvalutionQuestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.EvalutionQuestionRepository;
import com.openspace.Model.Template.EvalutionQuestion;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class EvalutionQuestionServiceImpl implements EvalutionQuestionService {

	@Autowired
	private EvalutionQuestionRepository evalutionQuestionRepository;

	@Override
	public void saveEvalutionQuestion(EvalutionQuestion evalutionQuestion) {
		// TODO Auto-generated method stub
		EvalutionQuestion dbEvalutionQuestion = evalutionQuestionRepository
				.findByQuestionName(evalutionQuestion.getQuestionName());
		if (dbEvalutionQuestion != null) {
			throw new RuntimeException(ErrorMessageHandler.evaluationQuestionAlreadyExists);
		}

		evalutionQuestionRepository.save(evalutionQuestion);
	}

	@Override
	public void updateEvalutionQuestion(EvalutionQuestion evalutionQuestion) {
		EvalutionQuestion dbEvalutionQuestion = evalutionQuestionRepository.findOne(evalutionQuestion.getId());
		if (dbEvalutionQuestion == null) {
			throw new RuntimeException(ErrorMessageHandler.evaluationQuestionDoesNotExists);
		}
		dbEvalutionQuestion.setQuestionName(evalutionQuestion.getQuestionName());
		evalutionQuestionRepository.save(dbEvalutionQuestion);

	}

	@Override
	public void deleteEvalutionQuestion(Long id) {
		EvalutionQuestion dbEvalutionQuestion = evalutionQuestionRepository.findOne(id);
		if (dbEvalutionQuestion == null) {
			throw new RuntimeException(ErrorMessageHandler.evaluationQuestionDoesNotExists);
		}
		evalutionQuestionRepository.delete(dbEvalutionQuestion);
	}

	@Override
	public List<EvalutionQuestion> getAllEvalutionQuestions() {
		// TODO Auto-generated method stub
		return (List<EvalutionQuestion>) evalutionQuestionRepository.findAll();
	}

	@Override
	public List<EvalutionQuestion> getAllEvalutionQuestionsByCategoryId(Long categoryId) {
		return evalutionQuestionRepository.findByEvalutionCategory_Id(categoryId);
	}

}
