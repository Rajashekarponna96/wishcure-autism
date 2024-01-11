package com.openspace.PatientManagement.ParentModule.service;

import java.util.List;

import com.openspace.Model.Dto.CareTakerGraphDashboardDto;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestion;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestionLookup;

public interface CareTakerCategoryService  {

	List<CareTakerQuestionLookup> getQuestionsBasedOnCategoryStatus(Long id) ;

	void saveCaretakerMilestones(String adminUserName, List<CareTakerQuestionLookup> careTakerQuestionLookupList);

	List<CareTakerQuestion> getAllCaretakerMilestones(Long id,String adminUserName);

	void updateCaretakerMilestones(List<CareTakerQuestion> careTakerQuestionList, String adminUserName);

	List<CareTakerGraphDashboardDto> getCaretakerMilstonesForDashboard(String adminUserName);
	
}
