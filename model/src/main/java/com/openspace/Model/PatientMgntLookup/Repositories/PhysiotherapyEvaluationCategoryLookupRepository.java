package com.openspace.Model.PatientMgntLookup.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryLookup;

@Repository
public interface PhysiotherapyEvaluationCategoryLookupRepository extends PagingAndSortingRepository<PhysiotherapyEvaluationCategoryLookup, Long> {
	
	PhysiotherapyEvaluationCategoryLookup findById(Long id);
	
	PhysiotherapyEvaluationCategoryLookup findByName(String name);
	
	

}
