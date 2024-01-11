package com.openspace.PatientManagement.IASSBehaviour;

import java.util.List;

import com.openspace.Model.Dto.ISAABehavioralReportDto;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestion;

public interface ISAABehaviouralQuestionService {

	List<ISAABehaviorQuestion> getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup(Long patientId, Long categoryId);

	List<List<ISAABehavioralReportDto>> getIsaaBehaviorTemplateResultByPatient(Long patientId);

}
