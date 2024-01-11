package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.childobservation.ChildObservationAnswerLookup;

@Repository
public interface ChildObservationAnswerLookupRepository extends PagingAndSortingRepository<ChildObservationAnswerLookup, Long>{
	
	ChildObservationAnswerLookup findByName(String name);
	List<ChildObservationAnswerLookup> findById(Long id);

}
