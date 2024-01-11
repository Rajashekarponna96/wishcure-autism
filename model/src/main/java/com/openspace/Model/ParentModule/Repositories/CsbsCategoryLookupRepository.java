package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;
@Repository
public interface CsbsCategoryLookupRepository extends PagingAndSortingRepository<CsbsCategoryLookup, Long> {

	CsbsCategoryLookup findByNameOrCategoryStatus(String name, String categoryStatus);

	
}
