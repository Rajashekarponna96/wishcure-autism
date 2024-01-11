package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.caretaker.CareTakerCategory;
@Repository
public interface CareTakerCategoryRepository extends PagingAndSortingRepository<CareTakerCategory, Long> {
	CareTakerCategory findByNameAndPerson_Id(String categoryName,Long id);

	CareTakerCategory findByName(String name);


}
