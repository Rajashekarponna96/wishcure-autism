package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Template.GoalTemplateLookup;
@Repository
public interface GoalTemplateLookupRepository extends PagingAndSortingRepository<GoalTemplateLookup, Long>{

	GoalTemplateLookup findByGoalTemplateLookupName(String goalTemplateLookupName);
	
	List<GoalTemplateLookup> findByStatus(String status);
	

}
