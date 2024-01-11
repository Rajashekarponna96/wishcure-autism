package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestion;

@Repository
public interface ChildObservationQuestionRepository extends PagingAndSortingRepository<ChildObservationQuestion, Long>{
	
	ChildObservationQuestion findByName(String name);
	List<ChildObservationQuestion> findById(Long id);
	ChildObservationQuestion findByChildObservationCategory(ChildObservationCategory childObservationCategory);
	List<ChildObservationQuestion> findByChildObservationCategory_Id(Long id);
	ChildObservationQuestion findByPerson_IdAndName(Long id,String name);
	List<ChildObservationQuestion> findByPerson_IdAndChildObservationCategory_Id(Long pid,Long cid);
	List<ChildObservationQuestion> findByPerson_IdAndChildObservationCategery_Id(Long pid,Long cid);
	List<ChildObservationQuestion> findByChildObservationCategery_Id(Long cid);
}
