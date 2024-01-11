package com.openspace.HospitalMgnt.Template.GoalLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.GoalLookup;
import com.openspace.Model.PatientMgnt.Repositories.GoalLookupRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
@Service
public class GoalLookupServiceImpl implements GoalLookupService  {
	@Autowired
	private GoalLookupRepository goalLookupRepository;
	@Override
	public void saveQuestion(GoalLookup goalLookup) {
		// TODO Auto-generated method stub
		GoalLookup dbGoalCategoryLookup = goalLookupRepository.findByGoalLookupName(goalLookup.getGoalLookupName());
		if (dbGoalCategoryLookup != null) {
			throw new RuntimeException(ErrorMessageHandler.goalLookupAlreadyExists);
		}
		GoalLookup goalLookup1=new GoalLookup();
		goalLookup1.setGoalLookupName(goalLookup.getGoalLookupName());
		goalLookupRepository.save(goalLookup1);
	}

	@Override
	public List<GoalLookup> getAllGoalLookups() {
		// TODO Auto-generated method stub
		return (List<GoalLookup>) goalLookupRepository.findAll();
	}

	@Override
	public void updateGoalLookup(GoalLookup goalLookup) {
		// TODO Auto-generated method stub
		GoalLookup dbGoalLookup = goalLookupRepository.findOne(goalLookup.getId());
		if (dbGoalLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.goalLookupDoesNotExists);
		}
		GoalLookup dbGoalname = goalLookupRepository.findByGoalLookupName(goalLookup.getGoalLookupName());

		if (dbGoalname.getId().equals(goalLookup.getId())) {
			dbGoalname.setGoalLookupName(goalLookup.getGoalLookupName());
			goalLookupRepository.save(dbGoalname);

		} else {
			throw new RuntimeException(ErrorMessageHandler.goalLookupAlreadyExists);
		}

		dbGoalname.setGoalLookupName(goalLookup.getGoalLookupName());
		goalLookupRepository.save(dbGoalname);
	}

	@Override
	public void deleteGoalLookup(Long id) {
		// TODO Auto-generated method stub
		GoalLookup dbGoalLookup = goalLookupRepository.findOne(id);
		if (dbGoalLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.goalLookupDoesNotExists);
		}
		goalLookupRepository.delete(dbGoalLookup);
	}

	@Override
	public GoalLookup getGoalLookup(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
