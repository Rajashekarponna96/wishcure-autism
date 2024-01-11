package com.openspace.HospitalMgnt.Template.GoalTemplateLookup;

import java.util.List;
import com.openspace.Model.DoctorManagement.GoalLookup;
import com.openspace.Model.Template.GoalTemplateLookup;

public interface GoalTemplateLookupService {
	
	void saveGoalTemplateLookup(GoalTemplateLookup goalTemplateLookup);

	List<GoalTemplateLookup> getAllGoalTemplateLookups();

	void updateGoalTemplateLookup(GoalTemplateLookup goalTemplateLookup);

	void deleteGoalTemplateLookup(Long id);

	GoalLookup getGoalTemplateLookup(Long id);

	List<GoalTemplateLookup> getAllGoalTemplateLookupsByPatient(Long patientId);

	List<GoalTemplateLookup> getAllGoalLookupsByStatus(String status);

	void assignGoalsToPatient(List<GoalTemplateLookup> goalLookups, Long patientId);

}
