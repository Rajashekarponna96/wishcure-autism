package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.ParentModule.caretaker.CareTakerQuestion;

public interface CareTakerQuestionRepository extends PagingAndSortingRepository<CareTakerQuestion, Long>{

	CareTakerQuestion findByName(String name);
	
	List<CareTakerQuestion> findByCareTakerCategory_Id(Long long1);
	
	List<CareTakerQuestion> findByCareTakerCategoryLookup_IdAndPerson_Id(Long cid,Long pid);
	

}
