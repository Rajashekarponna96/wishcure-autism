package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import java.util.List;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategory;


public interface PhysiotherapyEvaluationCategoryService {
	
	void saveCategory(PhysiotherapyEvaluationCategory phCategory);
	
	List<PhysiotherapyEvaluationCategory> getAllCategories();
	
	void updatePhysiotherapyEvaluationCategory(PhysiotherapyEvaluationCategory physiotherapyEvaluationCategory);

	void deletePhysiotherapyEvaluationCategory(Long id);


}
