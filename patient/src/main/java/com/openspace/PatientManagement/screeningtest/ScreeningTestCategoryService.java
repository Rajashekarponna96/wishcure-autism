package com.openspace.PatientManagement.screeningtest;

import java.util.List;

import com.openspace.Model.Dto.CreatedDatesDto;
import com.openspace.Model.Dto.GetScreeningTestDataDto;
import com.openspace.Model.Dto.ScreeningTestCategoryForGraph;
import com.openspace.Model.Dto.ScreeningTestGoalObjectDto;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategory;

public interface ScreeningTestCategoryService {


	void saveScreeningTestCategoryQuestions(String username,ScreeningTestGoalObjectDto screeningTestGoalObjectDto);

	List<ScreeningTestCategory> getAllScreeningTestCategoryQuestions(GetScreeningTestDataDto getScreeningTestDataDto);

	void updateScreeningTestCategoryQuestions(ScreeningTestCategory screeningTestCategory);
	
	void deleteScreeningTestCategoryQuestions(Long id);

	List<CreatedDatesDto> getAllScreeningTestGoalsCreatedDates(String username, Long id);

	List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForSocialStack(String userName);

	List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForImparimentStackGraph(String userName);

	List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForBehaviourStackGraph(String userName);

}
