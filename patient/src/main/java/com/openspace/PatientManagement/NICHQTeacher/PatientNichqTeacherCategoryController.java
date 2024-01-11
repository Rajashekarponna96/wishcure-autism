package com.openspace.PatientManagement.NICHQTeacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.NichqTeacherResultDto;
import com.openspace.Model.NichqTeachers.NichqTeacherResult;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherCategory;
import com.openspace.PatientManagement.dto.PatientNichqTeacherCategoryDto;

@RestController
@RequestMapping(value = "/patientNichqTeacherCategory")
public class PatientNichqTeacherCategoryController {

	@Autowired
	private PatientNichqTeacherQuestionService patientNichqTeacherQuestionService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void assignNichqTeacherCategoryToPatient(
			@RequestBody PatientNichqTeacherCategoryDto patientNichqTeacherCategoryDto) {
		patientNichqTeacherQuestionService.assignNichqTeacherCategoryToPatient(patientNichqTeacherCategoryDto);
	}

	@RequestMapping(value = "/allByPatientId/{patientId}", method = RequestMethod.GET)
	public List<PatientNichqTeacherCategory> getAllNichqTeacherCategoriesByPatient(
			@PathVariable("patientId") Long patientId) {
		return patientNichqTeacherQuestionService.getAllNichqTeacherCategoriesByPatient(patientId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateNichqTeacherCategoryToPatient(
			@RequestBody PatientNichqTeacherCategory patientNichqTeacherCategory) {
		patientNichqTeacherQuestionService.updateNichqTeacherCategoryToPatient(patientNichqTeacherCategory);
	}

	@RequestMapping(value = "/nichqTeacherResult/{patientId}", method = RequestMethod.GET)
	public NichqTeacherResult getNichqTeacherResultByPatient(@PathVariable("patientId") Long patientId) {
		return patientNichqTeacherQuestionService.getNichqTeacherResultByPatient(patientId);
	}

	@RequestMapping(value = "/deletenichqTeacherReport/{patientId}", method = RequestMethod.DELETE)
	public void deleteNichqTeacherReportByPatient(@PathVariable("patientId") Long patientId) {
		patientNichqTeacherQuestionService.deleteNichqTeacherReportByPatient(patientId);
	}

	@RequestMapping(value = "/nichqTeacherReport/{patientId}", method = RequestMethod.GET)
	public List<List<NichqTeacherResultDto>> getNichqTeacherReportByPatient(@PathVariable("patientId") Long patientId) {
		return patientNichqTeacherQuestionService.getNichqTeacherReportByPatient(patientId);
	}

}
