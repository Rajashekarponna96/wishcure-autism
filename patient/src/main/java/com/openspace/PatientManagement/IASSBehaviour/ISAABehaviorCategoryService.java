package com.openspace.PatientManagement.IASSBehaviour;

import java.util.List;
import java.util.Set;

import com.openspace.Model.Dto.ISAABehaviorObjectDto;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategory;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorResult;

public interface ISAABehaviorCategoryService {

	void saveISAABehaviorCategoryQuestions(ISAABehaviorObjectDto iSAABehaviorObjectDto);

	List<ISAABehaviorCategory> getAllISAABehaviorCategoryQuestions(Long patentId, Long id);

	void updateISAABehaviorCategoryQuestions(ISAABehaviorCategory iSAABehaviorCategory);

	void deleteISAABehaviorCategoryQuestions(Long id);

	List<ISAABehaviorCategory> getAllISAABehaviorCategoryQuestions(Long patentId);

	ISAABehaviorResult getISAABehaviorReportByPatientId(Long patentId);

	void deleteISAABehaviorReportByPatientId(Long patentId);

	 Set<String> getEvalutionSheetDatesByPatientId(Long patientId);

}