package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.childobservation.ChildObservationAnswer;

@Repository
public interface ChildObservationAnswerRepository extends PagingAndSortingRepository<ChildObservationAnswer, Long>{
	
	ChildObservationAnswer findByName(String name);
	List<ChildObservationAnswer> findById(Long id);
	List<ChildObservationAnswer> findByChildObservationQuestion_Id(Long id);
}
