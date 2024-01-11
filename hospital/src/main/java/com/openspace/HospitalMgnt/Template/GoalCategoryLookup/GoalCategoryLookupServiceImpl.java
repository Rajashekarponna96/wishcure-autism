package com.openspace.HospitalMgnt.Template.GoalCategoryLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.GoalCategoryLookup;
import com.openspace.Model.PatientMgnt.Repositories.GoalCategoryLookupRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
@Service
public class GoalCategoryLookupServiceImpl implements GoalCategoryLookupService {
	@Autowired
	private GoalCategoryLookupRepository goalCategoryLookupRepository;
	@Override
	public void saveGoalCategoryLookup(GoalCategoryLookup goalCategoryLookup) {
		// TODO Auto-generated method stub
		GoalCategoryLookup dbGoalCategoryLookup = goalCategoryLookupRepository.findByName(goalCategoryLookup.getName());
		if (dbGoalCategoryLookup != null) {
			throw new RuntimeException(ErrorMessageHandler.goalCategoryAlreadyExists);
		}
		
		goalCategoryLookupRepository.save(goalCategoryLookup);
	
	}

	@Override
	public List<GoalCategoryLookup> getAllGoalCategoryLookups() {
		// TODO Auto-generated method stub
		return (List<GoalCategoryLookup>) goalCategoryLookupRepository.findAll();
	}

	@Override
	public void updateGoalCategoryLookup(GoalCategoryLookup goalCategoryLookup) {
		/*
		 * GoalCategoryLookup dbGoalCategoryLookup =
		 * goalCategoryLookupRepository.findOne(goalCategoryLookup.getId()); if
		 * (dbGoalCategoryLookup == null) { throw new
		 * RuntimeException(ErrorMessageHandler.goalCategoryDoesNotExists); }
		 * GoalCategoryLookup dbGoalCategoryname =
		 * goalCategoryLookupRepository.findByName(goalCategoryLookup.getName());
		 * 
		 * if (dbGoalCategoryname.getId().equals(goalCategoryLookup.getId())) {
		 * dbGoalCategoryLookup.setName(goalCategoryLookup.getName());
		 * goalCategoryLookupRepository.save(dbGoalCategoryLookup);
		 * 
		 * } else { throw new
		 * RuntimeException(ErrorMessageHandler.goalCategoryAlreadyExists); }
		 * 
		 * 
		 * dbGoalCategoryLookup.setName(goalCategoryLookup.getName());
		 */
		goalCategoryLookupRepository.save(goalCategoryLookup);
	}

	@Override
	public void deleteGoalCategoryLookup(Long id) {
		// TODO Auto-generated method stub
		GoalCategoryLookup dbGoalCategoryLookup = goalCategoryLookupRepository.findOne(id);
		if (dbGoalCategoryLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.goalCategoryDoesNotExists);
		}
		goalCategoryLookupRepository.delete(dbGoalCategoryLookup);

	
	}

}
