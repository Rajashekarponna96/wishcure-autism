package com.openspace.PatientManagement.csbs;

import java.util.List;

import com.openspace.Model.Dto.CsbsObjectDto;
import com.openspace.Model.ParentModule.csbs.CsbsCategory;
import com.openspace.PatientManagement.dto.CSBSCategoryDto;

public interface CsbsCategoryService {


	void savecsbsCategoryQuestions(String username,CsbsObjectDto csbsObjectDto);

	List<CsbsCategory> getAllcsbsCategoryQuestions(String userName, Long id);

	void updatecsbsCategoryQuestions(CsbsCategory csbsCategory);
	
	void deletecsbsCategoryQuestions(Long id);

	void savecsbsCategoryQuestionsToPatient(CSBSCategoryDto csbsCategoryDto);

	List<CsbsCategory> getAllcsbsCategoryQuestionsByPatient(Long patientId, Long categoryId);

	List<CsbsCategory> getAllcsbsCategoryQuestions(Long categoryId);

}