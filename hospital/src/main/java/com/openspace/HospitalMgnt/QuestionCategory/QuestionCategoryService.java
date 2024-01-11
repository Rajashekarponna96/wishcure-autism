package com.openspace.HospitalMgnt.QuestionCategory;

import java.util.List;

import com.openspace.Model.DoctorManagement.QuestionCategory;
import com.openspace.Model.Dto.QuestionCategoryDto;

public interface QuestionCategoryService {
	
	void saveQuestionCategory(QuestionCategory questionCategory);

	List<QuestionCategoryDto> getAllCategorys();
	
	List<QuestionCategoryDto> getAllCategorysByPatientId(Long patientId);
	
	List<QuestionCategoryDto> getAllListCategorys();

	void updateQuestionCategory(QuestionCategory questionCategory);

	void deleteQuestionCategory(Long id);

	List<QuestionCategory> getAllQuestionCategorys();


}
