package com.openspace.PatientManagement.ParentModule.service;

import java.util.List;

import com.openspace.Model.ParentModule.caretaker.CareTakerCategoryLookup;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestionLookup;
public interface CareTakerQuestionLookupService {

	void saveCareTakerQuestionLookup(CareTakerQuestionLookup careTakerQuestionLookup);

	List<CareTakerQuestionLookup> getCareTakerQuestionLookups();

	void updateCareTakerQuestionLookup(CareTakerQuestionLookup careTakerQuestionLookup);

	void deleteCareTakerQuestionLookup(Long id);

	
}
