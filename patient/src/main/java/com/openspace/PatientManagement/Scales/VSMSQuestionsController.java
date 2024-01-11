package com.openspace.PatientManagement.Scales;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Scales.VSMSQuestion;
import com.openspace.Model.Scales.VSMSQuestionDto;
import com.openspace.Model.VSMS.PatientVSMSQuestion;

@RestController
public class VSMSQuestionsController {

	@Autowired
	private VSMSQuestionsService vsmsQuestionsService;

	@RequestMapping(value = "/saveVSMSQuestions", method = RequestMethod.POST)
	public @ResponseBody void saveVSMSQuestions(@RequestBody VSMSQuestion vsmsQuestion) {
		vsmsQuestionsService.saveVSMSQuestions(vsmsQuestion);
	}

	@RequestMapping(value = "/getAllVSMSQuestions", method = RequestMethod.GET)
	public @ResponseBody List<VSMSQuestion> getAllVSMSQuestions() {
		return vsmsQuestionsService.getAllVSMSQuestions();
	}

	@RequestMapping(value = "/updateVSMSQuestions", method = RequestMethod.PUT)
	public @ResponseBody void updateVSMSQuestions(@RequestBody VSMSQuestion vsmsQuestion) {
		vsmsQuestionsService.updateVSMSQuestions(vsmsQuestion);
	}

	@RequestMapping(value = "/deleteVSMSQuestions/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteVSMSQuestions(@PathVariable Long id) {
		vsmsQuestionsService.deleteVSMSQuestions(id);
	}

	// from test save
	@RequestMapping(value = "/assignVSMSQuestionsToPatient", method = RequestMethod.POST)
	public void assignVSMSQuestionsToPatient(@RequestBody VSMSQuestionDto vsmsQuestionDto) {
		if (vsmsQuestionDto.getPatient() == null) {
			throw new RuntimeException("Patient not Exists!");
		}
		List<PatientVSMSQuestion> patientVSMSQuestionList = new ArrayList<>();
		vsmsQuestionDto.getVsmsQuestionsList().forEach(vsmsQuestion -> {
			PatientVSMSQuestion patientVSMSQuestion = new PatientVSMSQuestion();
			// BeanUtils.copyProperties(vsmsQuestion, patientVSMSQuestion);
			patientVSMSQuestion.setPatient(vsmsQuestionDto.getPatient());
			patientVSMSQuestion.setVsmsQuestion(vsmsQuestion);
			patientVSMSQuestion.setDate(LocalDate.now());
			patientVSMSQuestion.setNote(vsmsQuestion.getNote());
			patientVSMSQuestion.setVsmsQuestionStatus(vsmsQuestion.getVsmsQuestionStatus());
			patientVSMSQuestionList.add(patientVSMSQuestion);

		});

		vsmsQuestionsService.savePatientVSMSQuestion(patientVSMSQuestionList);
		// vsmsQuestionsService.assignVSMSQuestionsToPatient(vsmsQuestionDto);
	}

	// get all mental scales that save in patient mental scales table
	@RequestMapping(value = "/getAllPatientVSMSQuestions/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<PatientVSMSQuestion> getAllPatientVSMSQuestions(@PathVariable Long patientId) {
		return vsmsQuestionsService.getAllPatientVSMSQuestions(patientId);
	}


}
