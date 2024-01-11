package com.openspace.Model.ParentModule.Repositories;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.ParentModule.caretaker.CareTakerAnswerLookup;

public interface CareTakerAnswerLookupRepository extends PagingAndSortingRepository<CareTakerAnswerLookup, Long> {

}
