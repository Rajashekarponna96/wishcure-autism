package com.openspace.PatientManagement.ParentModule.service;

import java.util.List;

import com.openspace.Model.ParentModule.caretaker.CareTakerCategoryLookup;

public interface CareTakerCategoryLookupService {

	void saveCareTakerCategoryLookup(CareTakerCategoryLookup careTakerCategoryLooku);

	List<CareTakerCategoryLookup> getCareTakerCategoryLookups();

	void updateCareTakerCategoryLookup(CareTakerCategoryLookup careTakerCategoryLookup);

	void deleteCareTakerCategoryLookup(Long id);

}
