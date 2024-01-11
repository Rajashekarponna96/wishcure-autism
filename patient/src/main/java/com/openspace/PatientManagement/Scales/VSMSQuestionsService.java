package com.openspace.PatientManagement.Scales;

import java.util.List;
import java.util.Set;

import com.openspace.Model.Scales.VSMSQuestion;
import com.openspace.Model.VSMS.PatientVSMSQuestion;
import com.openspace.Model.VSMS.VSMSAnswerCountAndScoringSheet;

public interface VSMSQuestionsService {

	void saveVSMSQuestions(VSMSQuestion vsmsQuestions);

	List<VSMSQuestion> getAllVSMSQuestions();

	void updateVSMSQuestions(VSMSQuestion vsmsQuestions);

	void deleteVSMSQuestions(Long id);
	
	void savePatientVSMSQuestion(List<PatientVSMSQuestion> patientVSMSQuestionList);

	List<PatientVSMSQuestion> getAllPatientVSMSQuestions(Long patientId);
	
	List<VSMSQuestion> findByVSMSClusterId(Long id);

	Set<String> getEvalutionSheetDatesByPatientId(Long patientId);



	

	

}