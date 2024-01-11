package com.openspace.PatientManagement.dto;

import java.util.List;

import com.openspace.Model.DoctorManagement.PatientGoal;
import com.openspace.Model.Template.GoalTemplateLookup;

public class PatientGoalDtoObject {

	private List<PatientGoal> patientGoals;

	private List<GoalTemplateLookup> golaTemplatelookups;

	private String evaluator;

	private String serviceCoordinator;

	public List<PatientGoal> getPatientGoals() {
		return patientGoals;
	}

	public void setPatientGoals(List<PatientGoal> patientGoals) {
		this.patientGoals = patientGoals;
	}

	public List<GoalTemplateLookup> getGolaTemplatelookups() {
		return golaTemplatelookups;
	}

	public void setGolaTemplatelookups(List<GoalTemplateLookup> golaTemplatelookups) {
		this.golaTemplatelookups = golaTemplatelookups;
	}

	public String getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(String evaluator) {
		this.evaluator = evaluator;
	}

	public String getServiceCoordinator() {
		return serviceCoordinator;
	}

	public void setServiceCoordinator(String serviceCoordinator) {
		this.serviceCoordinator = serviceCoordinator;
	}

	
}
