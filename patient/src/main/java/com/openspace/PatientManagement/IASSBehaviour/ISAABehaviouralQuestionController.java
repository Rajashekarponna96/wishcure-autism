package com.openspace.PatientManagement.IASSBehaviour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.ISAABehavioralReportDto;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestion;

@RestController
@RequestMapping(value = "/isaaBehaviouralQuestion")
public class ISAABehaviouralQuestionController {

	@Autowired
	private ISAABehaviouralQuestionService isaaBehaviouralQuestionService;

	@RequestMapping(value = "/allByPatient/{patientId}/categorylookup/{categoryId}", method = RequestMethod.GET)
	public List<ISAABehaviorQuestion> getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup(
			@PathVariable("patientId") Long patientId, @PathVariable("categoryId") Long categoryId) {
		return isaaBehaviouralQuestionService.getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup(patientId,
				categoryId);

	}
	
	@RequestMapping(value = "/reportbyPatient/{patientId}", method = RequestMethod.GET)
	public List<List<ISAABehavioralReportDto>> getIsaaBehaviorTemplateResultByPatient(@PathVariable("patientId") Long patientId) {
		return isaaBehaviouralQuestionService.getIsaaBehaviorTemplateResultByPatient(patientId);
	}

}
