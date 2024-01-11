package com.openspace.Model.PatientMgntLookup.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryQuestionsLookup;

public interface PhysiotherapyEvaluationCategoryQuestionsLookupRepository extends PagingAndSortingRepository<PhysiotherapyEvaluationCategoryQuestionsLookup, Long>  {
	
	PhysiotherapyEvaluationCategoryQuestionsLookup findById(Long id);
	
	PhysiotherapyEvaluationCategoryQuestionsLookup findByName(String name);


}
