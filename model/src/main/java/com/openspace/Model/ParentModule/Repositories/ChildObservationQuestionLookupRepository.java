package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategoryLookup;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestionLookup;

@Repository
public interface ChildObservationQuestionLookupRepository extends PagingAndSortingRepository<ChildObservationQuestionLookup, Long>{
	
	ChildObservationQuestionLookup findByName(String name);
	List<ChildObservationQuestionLookup> findById(Long id);
	ChildObservationQuestionLookup findByChildObservationCategoryLookup(ChildObservationCategoryLookup childObservationCategoryLookup);
	List<ChildObservationQuestionLookup> findByChildObservationCategoryLookup_Id(Long id);
}
