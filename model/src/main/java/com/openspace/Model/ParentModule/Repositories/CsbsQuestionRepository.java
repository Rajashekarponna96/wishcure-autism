package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.ParentModule.csbs.CsbsCategory;
import com.openspace.Model.ParentModule.csbs.CsbsQuestion;

public interface CsbsQuestionRepository extends PagingAndSortingRepository<CsbsQuestion, Long>{
	
	
List<CsbsCategory> findByCsbsCategory_id(Long categoryId);
List<CsbsQuestion> findById(Long categoryId);
}
