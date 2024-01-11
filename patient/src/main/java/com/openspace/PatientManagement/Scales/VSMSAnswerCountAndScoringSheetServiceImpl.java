package com.openspace.PatientManagement.Scales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.ScalesRepositories.PatientVSMSQuestionRepository;
import com.openspace.Model.ScalesRepositories.VSMSAnswerCountAndScoringSheetRepository;
import com.openspace.Model.VSMS.VSMSAnswerCountAndScoringSheet;

@Service
public class VSMSAnswerCountAndScoringSheetServiceImpl implements VSMSAnswerCountAndScoringSheetService {
	@Autowired
	private VSMSAnswerCountAndScoringSheetRepository vsmsAnswerCountAndScoringSheetRepository;
	
	@Autowired
	private PatientVSMSQuestionRepository patientVSMSQuestionRepository;
	
	@Autowired
	private PatientRepository patientRepository;


	
	@Override
	public VSMSAnswerCountAndScoringSheet findByAnswerCount(int id) {
		return vsmsAnswerCountAndScoringSheetRepository.findByAnswerCount(id);
	}
	
	
}
