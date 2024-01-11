package com.openspace.PatientManagement.childobservationLookup;

import java.util.List;

import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestionLookup;

public interface ChildObservationQuestionLookupService {
	
	void saveChildObservationQuestionLookup(ChildObservationQuestionLookup childObservationQuestionLookup);
	
	void updateChildObservationQuestionLookup(ChildObservationQuestionLookup childObservationQuestionLookup);
	
	void deleteChildObservationQuestionLookup(Long id);
	
	List<ChildObservationQuestionLookup> getAllChildObservationQuestionLookups();

	List<ChildObservationQuestionLookup> getAllChildObservationQuestionLookupsByStatus(Long categorystatus);

}
