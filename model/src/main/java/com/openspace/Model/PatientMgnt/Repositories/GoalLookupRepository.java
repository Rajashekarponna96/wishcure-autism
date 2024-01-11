package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.GoalLookup;
@Repository
public interface GoalLookupRepository extends PagingAndSortingRepository<GoalLookup, Long>{

	GoalLookup findByGoalLookupName(String goalLookupName);

}
