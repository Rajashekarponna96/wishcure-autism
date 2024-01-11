package com.openspace.PatientManagement.service;

import java.util.List;
import java.util.Set;

import com.openspace.Model.DoctorManagement.EvalutionSheet;
import com.openspace.Model.Dto.EvalutionSheetDto;
import com.openspace.PatientManagement.dto.EvalutionSheetDateDto;
import com.openspace.PatientManagement.dto.ProgressSheetDateDto;

public interface EvalutionSheetService {
	
	void saveEvalutionSheet(EvalutionSheetDto evalutionSheetDto);

	List<EvalutionSheet> getAllSheets();

	void updateEvalutionSheet(EvalutionSheet evalutionSheet);

	void deleteEvalutionSheet(Long id);

	EvalutionSheet getEvaluationSheetByPatientIdStatus(Long patientId, String status);

	EvalutionSheet getEvaluationSheetByPatientIdStatusInProgress(Long patientId);

	//extra save methods are here
	
	void savePatientEvalutionSheet(EvalutionSheetDto evalutionSheetDto);

	void saveProgressNote(EvalutionSheet evalutionSheet);

	void saveExitNote(EvalutionSheet evalutionSheet);

	EvalutionSheet getEvaluationSheetByPatientIdStatusInExit(Long patientId);

	EvalutionSheet getEvaluationSheetByPatientIdStatusInEvaluationReport(Long patientId);
	
	EvalutionSheet getEvaluationSheetByPatientIdStatusInProgressReport(Long patientId);

	EvalutionSheet getEvaluationSheetByPatientIdStatusInExitNoteReport(Long patientId);
	Set<String> getProgressSheetDatesByPatientId(Long patientId);

	EvalutionSheet getProgressSheetByDateAndStatus(ProgressSheetDateDto progressSheetDateDto);


	Set<String> getEvalutionSheetDatesByPatientId(Long patientId);

	EvalutionSheet getEvalutionSheetByDateAndStatus(EvalutionSheetDateDto evalutionSheetDateDto);

	


}
