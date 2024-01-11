package com.openspace.PatientManagement.childobservation;

import java.util.List;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;

public interface ChildObservationCategoryService {
	
	void saveChildObservationCategory(ChildObservationCategory childObservationCategory);
	
	void updateChildObservationCategory(ChildObservationCategory childObservationCategory);
	
	void deleteChildObservationCategory(Long id);
	
	List<ChildObservationCategory> getAllChildObservationCategories();

}
