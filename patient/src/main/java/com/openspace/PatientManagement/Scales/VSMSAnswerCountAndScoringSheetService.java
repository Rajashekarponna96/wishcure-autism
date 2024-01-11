package com.openspace.PatientManagement.Scales;

import com.openspace.Model.VSMS.VSMSAnswerCountAndScoringSheet;

public interface VSMSAnswerCountAndScoringSheetService {

	VSMSAnswerCountAndScoringSheet findByAnswerCount(int id);

}