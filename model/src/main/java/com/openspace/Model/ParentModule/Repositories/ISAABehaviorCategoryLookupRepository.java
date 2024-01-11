package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;

@Repository
public interface ISAABehaviorCategoryLookupRepository extends PagingAndSortingRepository<ISAABehaviorCategoryLookup, Long> {

	//ISAABehaviorCategoryLookup findByNameOrCategoryStatus(String name, String categoryStatus);
	
	ISAABehaviorCategoryLookup findByName(String name);

}
