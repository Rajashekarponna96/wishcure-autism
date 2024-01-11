package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientGoal;
import com.openspace.Model.Template.GoalTemplateLookup;
import com.openspace.PatientManagement.dto.PatientGoalDTo;
import com.openspace.PatientManagement.dto.PatientGoalDtoObject;
import com.openspace.PatientManagement.dto.PatientGoalsReportDto;

public interface PatientGoalService {


	void savePatientGoals( PatientGoalDtoObject patientgoalDto, Long patientId);
	

	void updatePatientGoals(PatientGoal patientGoal);

	void deletePatientGoals(Long id);

	List<PatientGoal> getPatientGoalsByPatientId(Long patientId, String createdDate);


	List<PatientGoal> getAllPatientGoalsByPatientId(Long patientId);
	
	PatientGoalsReportDto getPatientGoalsInPresentWeek(Long patientId);
	
	int getGoalsByPatient_IdAndAnswerAndDate(Long patientId,boolean status,LocalDate todayDate);

	Set<String> getAllPatientGoalsCreatedDates(Long patientId);


	List<PatientGoal> getPatientGoalsByPatientId(PatientGoalDTo patientGoalDTo);


	void assignGoalsToPatient(Patient patient, List<GoalTemplateLookup> goalTemplatelookups);


	void deletePatientGoalById(Long patientId, Long goalId);


	List<PatientGoal> findAllPatientGoalsByPatient(Long patientId);

}
