package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;

@Repository
public interface ChildObservationCategoryRepository extends PagingAndSortingRepository<ChildObservationCategory, Long>{
	
	ChildObservationCategory findByName(String name);
	List<ChildObservationCategory> findById(Long id);

}
