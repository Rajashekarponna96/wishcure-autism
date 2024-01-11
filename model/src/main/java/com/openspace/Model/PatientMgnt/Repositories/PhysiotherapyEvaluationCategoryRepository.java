package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategory;
import com.openspace.Model.Lookups.CategoryType;

@Repository
public interface PhysiotherapyEvaluationCategoryRepository extends PagingAndSortingRepository<PhysiotherapyEvaluationCategory, Long> {
	
	PhysiotherapyEvaluationCategory findById(Long id);
	
	List<PhysiotherapyEvaluationCategory> findAll();
	
	PhysiotherapyEvaluationCategory findByCategoryName(String name);
	

}
