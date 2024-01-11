package com.openspace.PatientManagement.childobservationLookup;

import java.util.List;

import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategoryLookup;

public interface ChildObservationCategoryLookupService {
	
	void saveChildObservationCategoryLookup(ChildObservationCategoryLookup childObservationCategoryLookup);
	
	void updateChildObservationCategoryLookup(ChildObservationCategoryLookup childObservationCategoryLookup);
	
	void deleteChildObservationCategoryLookup(Long id);
	
	List<ChildObservationCategoryLookup> getAllChildObservationCategoryLookups();
	
	List<Long> getAllChildObservationCategoryLookupsIds();

}
