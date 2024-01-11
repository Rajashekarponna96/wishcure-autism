package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.ParentModule.caretaker.CareTakerQuestionLookup;

public interface CareTakerQuestionLookupRepository extends PagingAndSortingRepository<CareTakerQuestionLookup, Long>{

	CareTakerQuestionLookup findByName(String name);
	
	List<CareTakerQuestionLookup> findByCareTakerCategoryLookup_Id(Long long1);
	

}
