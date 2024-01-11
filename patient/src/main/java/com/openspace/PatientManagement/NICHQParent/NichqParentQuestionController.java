package com.openspace.PatientManagement.NICHQParent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.NichqParentResultDto;
import com.openspace.Model.NICHQParent.NichqParentQuestion;

@RestController
@RequestMapping(value = "/nichqParentQuestion")
public class NichqParentQuestionController {

	@Autowired
	private NichqParentQuestionService nichqParentQuestionService;

	@RequestMapping(value = "/byPatient/{patientId}/categoryId/{categoryId}", method = RequestMethod.GET)
	public List<NichqParentQuestion> getNichqParentQuestionsByPatientAndCategoryId(@PathVariable("patientId") Long patientId,
			@PathVariable("categoryId") Long categoryId) {
		return nichqParentQuestionService.getNichqParentQuestionsByPatientAndCategoryId(patientId,categoryId);
	}
	
	@RequestMapping(value = "/reportbyPatient/{patientId}", method = RequestMethod.GET)
	public List<List<NichqParentResultDto>> nichqParentResultByPatient(@PathVariable("patientId") Long patientId) {
		return nichqParentQuestionService.nichqParentResultByPatient(patientId);
	}
	

}
