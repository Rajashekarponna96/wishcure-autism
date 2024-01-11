/*package com.openspace.HospitalMgnt.Answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.Question.QuestionRepository;
import com.openspace.Model.DoctorManagement.Answer;
import com.openspace.Model.DoctorManagement.Question;

@Service
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public void saveAnswer(Answer answer) {
		// TODO Auto-generated method stub
		Question dbQuestion = questionRepository.findOne(answer.getQuestion().getId());
		if (dbQuestion == null) {
			throw new RuntimeException("Question not exits!");
		}
		Answer answer1 = new Answer();
		answer1.setDescription(answer.getDescription());
		answer1.setShortAnswer(answer.getShortAnswer());
		answer1.setQuestion(dbQuestion);
		answerRepository.save(answer1);
	}

	@Override
	public List<Answer> getAllAnswers() {
		// TODO Auto-generated method stub

		return (List<Answer>) answerRepository.findAll();
	}

	@Override
	public void updateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		answerRepository.save(answer);
	}

	@Override
	public void deleteAnswer(Long id) {
		// TODO Auto-generated method stub
		answerRepository.delete(id);
	}

	

	@Override
	public List<Answer> getAllanswersByQuestionId(Long questionId) {
		Question dbQuestion = questionRepository.findOne(questionId);
		if (dbQuestion == null) {
			throw new RuntimeException("Question Does not Exist");
		}
		return dbQuestion.getAnswers();
	}

}
*/