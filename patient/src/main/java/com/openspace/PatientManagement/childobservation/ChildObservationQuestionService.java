package com.openspace.PatientManagement.childobservation;

import java.util.List;

import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestion;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestionLookup;

public interface ChildObservationQuestionService {
	
	void saveChildObservationQuestion(List<ChildObservationQuestionLookup> questionLookups,String userName);
	
	//void updateChildObservationQuestion(ChildObservationQuestion childObservationQuestion);
	
	void deleteChildObservationQuestion(Long id);
	
	List<ChildObservationQuestion> getAllChildObservationQuestions();

	List<ChildObservationQuestion> getAllChildObservationQuestionsByStatus(Long categorystatus);

	List<ChildObservationQuestion> getChildObservationQuestionsByPatientIdAndCategoryId(Long pid, Long cid);

	List<ChildObservationQuestion> getChildObservationQuestionsByPatientAndCategoryId(String userName, Long cid);

	void updateChildObservationQuestion(List<ChildObservationQuestion> childObservationQuestionsList, String userName);

	List<ChildObservarionGraphDashboardDto> getAnswersCountForDashboard(String username);

}
