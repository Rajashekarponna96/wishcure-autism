package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookup;
@Repository
public interface ISAABehaviorQuestionLookupRepository extends PagingAndSortingRepository<ISAABehaviorQuestionLookup,Long>{

	ISAABehaviorQuestionLookup findByName(String name);
	
	List<ISAABehaviorQuestionLookup> findByISAABehaviorCategoryLookup_Id(Long categoryId);

	

}
