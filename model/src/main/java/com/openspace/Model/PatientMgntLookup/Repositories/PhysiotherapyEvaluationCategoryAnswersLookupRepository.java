package com.openspace.Model.PatientMgntLookup.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryAnswersLookup;

public interface PhysiotherapyEvaluationCategoryAnswersLookupRepository extends PagingAndSortingRepository<PhysiotherapyEvaluationCategoryAnswersLookup, Long> {
	
	PhysiotherapyEvaluationCategoryAnswersLookup findById(Long id);
	
	PhysiotherapyEvaluationCategoryAnswersLookup findByName(String name);

}
