package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategoryLookup;

@Repository
public interface ChildObservationCategoryLookupRepository extends PagingAndSortingRepository<ChildObservationCategoryLookup, Long>{
	
	ChildObservationCategoryLookup findByName(String name);
	List<ChildObservationCategoryLookup> findById(Long id);

}
