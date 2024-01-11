package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryAnswers;

@Repository
public interface PhysiotherapyEvaluationCategoryAnswersRepository extends PagingAndSortingRepository<PhysiotherapyEvaluationCategoryAnswers, Long>{

	PhysiotherapyEvaluationCategoryAnswers findById(Long id);
	
	List<PhysiotherapyEvaluationCategoryAnswers> findAll();
	
}
