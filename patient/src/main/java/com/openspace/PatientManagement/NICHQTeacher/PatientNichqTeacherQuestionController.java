package com.openspace.PatientManagement.NICHQTeacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.NichqTeachers.PatientNichqTeacherQuestion;

@RestController
@RequestMapping(value = "/patientNichqTeacherQuestion")
public class PatientNichqTeacherQuestionController {

	@Autowired
	private PatientNichqTeacherQuestionService patientNichqTeacherQuestionService;

	@RequestMapping(value = "/patient/{patientId}/category/{categoryId}", method = RequestMethod.GET)
	public List<PatientNichqTeacherQuestion> getAllPatientNichqTeacherQuestionsByPatientAndCategory(
			@PathVariable("patientId") Long patientId, @PathVariable("categoryId") Long categoryId) {
		return patientNichqTeacherQuestionService.getAllPatientNichqTeacherQuestionsByPatientAndCategory(patientId,
				categoryId);
	}

}
