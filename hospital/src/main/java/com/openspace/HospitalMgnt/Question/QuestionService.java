package com.openspace.HospitalMgnt.Question;

import java.util.List;

import com.openspace.Model.DoctorManagement.Question;
import com.openspace.Model.Dto.QuestionDto;

public interface QuestionService {
	
	void saveQuestion(Question question);

	List<QuestionDto> getAllQuestions();

	void updateQuestion(Question question);

	void deleteQuestion(Long id);

	Question getQuestion(Long id);

}
