package com.openspace.HospitalMgnt.Template.GoalCategoryLookup;

import java.util.List;

import com.openspace.Model.DoctorManagement.GoalCategoryLookup;

public interface GoalCategoryLookupService {
	void saveGoalCategoryLookup(GoalCategoryLookup goalCategoryLookup);

	List<GoalCategoryLookup> getAllGoalCategoryLookups();

	void updateGoalCategoryLookup(GoalCategoryLookup GoalCategoryLookup);

	void deleteGoalCategoryLookup(Long id);

}
