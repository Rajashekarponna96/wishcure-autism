package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategoryLookup;
@Repository
public interface ScreeningTestCategoryLookupRepository extends PagingAndSortingRepository<ScreeningTestCategoryLookup, Long> {

	ScreeningTestCategoryLookup findByNameOrCategoryStatus(String name, String categoryStatus);

	List<ScreeningTestCategoryLookup> findByCategoryStatus(String categorystatus);
	
	List<ScreeningTestCategoryLookup> findByName(String name);
}
