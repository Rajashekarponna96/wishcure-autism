package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.caretaker.CareTakerCategoryLookup;
@Repository
public interface CareTakerCategoryLookupRepository extends PagingAndSortingRepository<CareTakerCategoryLookup, Long> {
	CareTakerCategoryLookup findByName(String categoryName);
}
