package com.openspace.HospitalMgnt.Question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.Answer.AnswerRepository;
import com.openspace.HospitalMgnt.QuestionCategory.QuestionCategoryRepository;
import com.openspace.Model.DoctorManagement.Answer;
import com.openspace.Model.DoctorManagement.Question;
import com.openspace.Model.DoctorManagement.QuestionCategory;
import com.openspace.Model.Dto.AnswerDto;
import com.openspace.Model.Dto.QuestionDto;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;

	@Override
	public void saveQuestion(Question question) {

		Question dbQuestion = questionRepository.findByquestionName(question.getQuestionName());
		if (dbQuestion != null) {
			throw new RuntimeException(ErrorMessageHandler.questionAlreadyExists);
		}
		QuestionCategory dbQuestionCategory = questionCategoryRepository
				.findOne(question.getQuestionCategory().getId());
		if (dbQuestionCategory == null) {
			throw new RuntimeException(ErrorMessageHandler.categoryOrModuleDoesNotExists);
		}

		Question question1 = new Question();
		question1.setQuestionCategory(dbQuestionCategory);
		question1.setQuestionName(question.getQuestionName());
		questionRepository.save(question1);

		List<Answer> answersList = question.getAnswers();
		List<Answer> answers = new ArrayList<Answer>();
		for (Answer answer : answersList) {
			Answer answer1=new Answer();
			answer1.setDescription(answer.getDescription());
			answer1.setShortAnswer(answer.getShortAnswer());
			answer1.setQuestion(question1);
			answerRepository.save(answer1);
		}

	}

	@Override
	public List<QuestionDto> getAllQuestions() {

		List<Question> questionsList = (List<Question>) questionRepository.findAll();
		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		for (Question question : questionsList) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.setId(question.getId());
			questionDto.setQuestionName(question.getQuestionName());

			List<Answer> answers = question.getAnswers();
			List<AnswerDto> answerDtos = new ArrayList<AnswerDto>();
			for (Answer answer : answers) {
				AnswerDto answerDto = new AnswerDto();
				answerDto.setId(answer.getId());
				answerDto.setShortAnswer(answer.getShortAnswer());
				answerDto.setDescription(answer.getDescription());
				answerDtos.add(answerDto);
			}
			questionDto.setAnswerDtos(answerDtos);
			questionDtos.add(questionDto);
		}

		return questionDtos;
	}

	@Override
	public void updateQuestion(Question question) {

		Question dbQuestion = questionRepository.findOne(question.getId());
		if (dbQuestion == null) {
			throw new RuntimeException(ErrorMessageHandler.questionDoesNotExists);
		}
		dbQuestion.setQuestionName(question.getQuestionName());
		questionRepository.save(dbQuestion);
	}

	@Override
	public void deleteQuestion(Long id) {
		// TODO Auto-generated method stub
		Question dbQuestion = questionRepository.findOne(id);
		if (dbQuestion == null) {
			throw new RuntimeException(ErrorMessageHandler.questionDoesNotExists);
		}
		questionRepository.delete(dbQuestion);

	}

	@Override
	public Question getQuestion(Long id) {
		Question question = questionRepository.findOne(id);
		return question;
	}

}
