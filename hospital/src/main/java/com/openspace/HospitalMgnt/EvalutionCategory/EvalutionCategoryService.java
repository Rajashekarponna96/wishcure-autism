package com.openspace.HospitalMgnt.EvalutionCategory;

import java.util.List;

import com.openspace.Model.Template.EvalutionCategory;

public interface EvalutionCategoryService {
	
	void saveEvalutionCategory(EvalutionCategory evalutionCategory);

	List<EvalutionCategory> getAllCategorys();

	void updateEvalutionCategory(EvalutionCategory evalutionCategory);

	void deleteEvalutionCategory(Long id);

}
