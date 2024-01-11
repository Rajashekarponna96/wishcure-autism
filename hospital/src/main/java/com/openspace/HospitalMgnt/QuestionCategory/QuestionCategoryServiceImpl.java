package com.openspace.HospitalMgnt.QuestionCategory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Answer;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Question;
import com.openspace.Model.DoctorManagement.QuestionCategory;
import com.openspace.Model.Dto.AnswerDto;
import com.openspace.Model.Dto.QuestionCategoryDto;
import com.openspace.Model.Dto.QuestionDto;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class QuestionCategoryServiceImpl implements QuestionCategoryService {

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void saveQuestionCategory(QuestionCategory questionCategory) {
		QuestionCategory dbQuestionCategory = questionCategoryRepository.findByNameAndStatus(questionCategory.getName(),
				questionCategory.getStatus());
		if (dbQuestionCategory != null) {
			throw new RuntimeException(ErrorMessageHandler.questionCategoryAlreadyExists);
		}
		questionCategoryRepository.save(questionCategory);
	}

	@Override
	public List<QuestionCategoryDto> getAllCategorys() {
		List<QuestionCategory> questionCategories = (List<QuestionCategory>) questionCategoryRepository.findByStatus(3);
		ArrayList<QuestionCategoryDto> questionCategoryDtoList = new ArrayList<QuestionCategoryDto>();

		for (QuestionCategory questionCategory : questionCategories) {
			QuestionCategoryDto questionCategoryDto = new QuestionCategoryDto();
			questionCategoryDto.setId(questionCategory.getId());
			questionCategoryDto.setName(questionCategory.getName());
			questionCategoryDto.setDisplayOrder(questionCategory.getDisplayOrder());
			questionCategoryDto.setStatus(questionCategory.getStatus());

			ArrayList<QuestionDto> questionDtoList = new ArrayList<QuestionDto>();
			List<Question> questionsList = questionCategory.getQuestions();
			for (Question question : questionsList) {
				QuestionDto questionDto = new QuestionDto();
				questionDto.setId(question.getId());
				questionDto.setQuestionName(question.getQuestionName());
				List<Answer> answersList = question.getAnswers();
				ArrayList<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
				for (Answer answer : answersList) {
					AnswerDto answerDto = new AnswerDto();
					answerDto.setDescription(answer.getDescription());
					answerDto.setId(answer.getId());
					answerDto.setShortAnswer(answer.getShortAnswer());
					answerDtoList.add(answerDto);
				}
				questionDto.setAnswerDtos(answerDtoList);
				questionDtoList.add(questionDto);
			}
			questionCategoryDto.setQuestionDtos(questionDtoList);
			questionCategoryDtoList.add(questionCategoryDto);

		}
		return questionCategoryDtoList;
	}

	@Override
	public List<QuestionCategoryDto> getAllListCategorys() {
		List<QuestionCategory> questionCategories = (List<QuestionCategory>) questionCategoryRepository.findAll();
		ArrayList<QuestionCategoryDto> questionCategoryDtoList = new ArrayList<QuestionCategoryDto>();

		for (QuestionCategory questionCategory : questionCategories) {
			QuestionCategoryDto questionCategoryDto = new QuestionCategoryDto();
			questionCategoryDto.setId(questionCategory.getId());
			questionCategoryDto.setName(questionCategory.getName());
			questionCategoryDto.setDisplayOrder(questionCategory.getDisplayOrder());
			questionCategoryDto.setStatus(questionCategory.getStatus());

			ArrayList<QuestionDto> questionDtoList = new ArrayList<QuestionDto>();
			List<Question> questionsList = questionCategory.getQuestions();
			for (Question question : questionsList) {
				QuestionDto questionDto = new QuestionDto();
				questionDto.setId(question.getId());
				questionDto.setQuestionName(question.getQuestionName());
				List<Answer> answersList = question.getAnswers();
				ArrayList<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
				for (Answer answer : answersList) {
					AnswerDto answerDto = new AnswerDto();
					answerDto.setDescription(answer.getDescription());
					answerDto.setId(answer.getId());
					answerDto.setShortAnswer(answer.getShortAnswer());
					answerDtoList.add(answerDto);
				}
				questionDto.setAnswerDtos(answerDtoList);
				questionDtoList.add(questionDto);
			}
			questionCategoryDto.setQuestionDtos(questionDtoList);
			questionCategoryDtoList.add(questionCategoryDto);

		}
		return questionCategoryDtoList;
	}

	@Override
	public void updateQuestionCategory(QuestionCategory questionCategory) {

		QuestionCategory dbQuestionCategory = questionCategoryRepository.findOne(questionCategory.getId());
		if (dbQuestionCategory == null) {
			throw new RuntimeException(ErrorMessageHandler.questionCategoryDoesNotExists);
		}
		dbQuestionCategory.setName(questionCategory.getName());
		dbQuestionCategory.setDisplayOrder(questionCategory.getDisplayOrder());
		questionCategoryRepository.save(dbQuestionCategory);
	}

	@Override
	public void deleteQuestionCategory(Long id) {
		// TODO Auto-generated method stub
		QuestionCategory dbQuestionCategory = questionCategoryRepository.findOne(id);
		if (dbQuestionCategory == null) {
			throw new RuntimeException(ErrorMessageHandler.questionCategoryDoesNotExists);
		}
		questionCategoryRepository.delete(dbQuestionCategory);

	}

	@Override
	public List<QuestionCategory> getAllQuestionCategorys() {
		List<QuestionCategory> dbQuestionCategoryList = (List<QuestionCategory>) questionCategoryRepository.findAll();
		return dbQuestionCategoryList;
	}

	@Override
	public List<QuestionCategoryDto> getAllCategorysByPatientId(Long patientId) {
		// TODO Auto-generated method stub
		List<QuestionCategory> questionCategories = (List<QuestionCategory>) questionCategoryRepository.findByStatus(3);
		ArrayList<QuestionCategoryDto> questionCategoryDtoList = new ArrayList<QuestionCategoryDto>();

		for (QuestionCategory questionCategory : questionCategories) {
			QuestionCategoryDto questionCategoryDto = new QuestionCategoryDto();
			questionCategoryDto.setId(questionCategory.getId());
			questionCategoryDto.setName(questionCategory.getName());
			questionCategoryDto.setDisplayOrder(questionCategory.getDisplayOrder());
			questionCategoryDto.setStatus(questionCategory.getStatus());

			ArrayList<QuestionDto> questionDtoList = new ArrayList<QuestionDto>();
			List<Question> questionsList = questionCategory.getQuestions();
			for (Question question : questionsList) {
				QuestionDto questionDto = new QuestionDto();
				if (patientId != null) {
					questionDto.setQuestionName(question.getQuestionName());
					Patient patient = patientRepository.findOne(patientId);
					if (patient == null) {
						throw new RuntimeException(ErrorMessageHandler.patientNotFound);
					}
					String fullname = patient.getFirstName() + " " + patient.getLastName();
					questionDto.setQuestionName(String.format(questionDto.getQuestionName(), new String[] { fullname }));
				}
				questionDto.setId(question.getId());
				/*questionDto.setQuestionName(question.getQuestionName());*/
				
				List<Answer> answersList = question.getAnswers();
				ArrayList<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
				for (Answer answer : answersList) {
					AnswerDto answerDto = new AnswerDto();
					answerDto.setDescription(answer.getDescription());
					answerDto.setId(answer.getId());
					answerDto.setShortAnswer(answer.getShortAnswer());
					answerDtoList.add(answerDto);
				}
				questionDto.setAnswerDtos(answerDtoList);
				questionDtoList.add(questionDto);
			}
			questionCategoryDto.setQuestionDtos(questionDtoList);
			questionCategoryDtoList.add(questionCategoryDto);

		}
		return questionCategoryDtoList;
	}
}
