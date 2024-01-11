package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import java.util.List;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryQuestions;

public interface PhysiotherapyEvaluationCategoryQuestionsService {
	
	void saveCategoryQuestion(PhysiotherapyEvaluationCategoryQuestions phCategoryQuestions);
	
	List<PhysiotherapyEvaluationCategoryQuestions> getAllCategoryQuestions();

}
