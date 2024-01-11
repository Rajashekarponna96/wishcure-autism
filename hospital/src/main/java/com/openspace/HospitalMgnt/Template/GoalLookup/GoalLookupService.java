package com.openspace.HospitalMgnt.Template.GoalLookup;

import java.util.List;

import com.openspace.Model.DoctorManagement.GoalLookup;

public interface GoalLookupService {
	void saveQuestion(GoalLookup goalLookup);

	List<GoalLookup> getAllGoalLookups();

	void updateGoalLookup(GoalLookup goalLookup);

	void deleteGoalLookup(Long id);

	GoalLookup getGoalLookup(Long id);

}
