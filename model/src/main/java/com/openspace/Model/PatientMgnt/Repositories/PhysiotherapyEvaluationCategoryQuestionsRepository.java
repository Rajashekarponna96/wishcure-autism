package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryQuestions;

@Repository
public interface PhysiotherapyEvaluationCategoryQuestionsRepository extends PagingAndSortingRepository<PhysiotherapyEvaluationCategoryQuestions, Long> {
	
	PhysiotherapyEvaluationCategoryQuestions findById(Long id);
	
	List<PhysiotherapyEvaluationCategoryQuestions> findAll();

}
