package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Template.EvalutionCategory;

@Repository
public interface EvalutionCategoryRepository extends PagingAndSortingRepository<EvalutionCategory, Long>{

	EvalutionCategory findByCategoryName(String name);
	List<EvalutionCategory> findById(Long id);
}
