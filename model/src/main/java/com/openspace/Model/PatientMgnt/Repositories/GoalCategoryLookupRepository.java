package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.GoalCategoryLookup;
@Repository
public interface GoalCategoryLookupRepository extends PagingAndSortingRepository<GoalCategoryLookup, Long> {

	GoalCategoryLookup findByName(String name);


}
