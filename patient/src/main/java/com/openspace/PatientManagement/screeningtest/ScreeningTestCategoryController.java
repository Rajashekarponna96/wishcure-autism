package com.openspace.PatientManagement.screeningtest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.CreatedDatesDto;
import com.openspace.Model.Dto.GetScreeningTestDataDto;
import com.openspace.Model.Dto.ScreeningTestCategoryForGraph;
import com.openspace.Model.Dto.ScreeningTestGoalObjectDto;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategory;

@RestController
public class ScreeningTestCategoryController {
	@Autowired
	private ScreeningTestCategoryService screeningTestCategoryService;

	@RequestMapping(value = "/saveScreeningTestCategoryQuestions/{userName:.+}", method = RequestMethod.POST)
	public @ResponseBody void saveScreeningTestCategoryQuestions(@PathVariable("userName") String userName,
			@RequestBody ScreeningTestGoalObjectDto screeningTestGoalObjectDto) {
		screeningTestCategoryService.saveScreeningTestCategoryQuestions(userName,screeningTestGoalObjectDto);
	}

	@RequestMapping(value = "/getAllScreeningTestCategoryQuestions", method = RequestMethod.POST)
	public @ResponseBody List<ScreeningTestCategory> getAllScreeningTestCategoryQuestions(@RequestBody GetScreeningTestDataDto getScreeningTestDataDto) {
		return screeningTestCategoryService.getAllScreeningTestCategoryQuestions(getScreeningTestDataDto);
	}

	@RequestMapping(value = "/updateScreeningTestCategoryQuestions", method = RequestMethod.PUT)
	public @ResponseBody void updateScreeningTestCategoryQuestions(
			@RequestBody ScreeningTestCategory screeningTestCategory) {
		screeningTestCategoryService.updateScreeningTestCategoryQuestions(screeningTestCategory);
	}

	@RequestMapping(value = "/deleteScreeningTestCategoryQuestions/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteScreeningTestCategoryQuestions(@PathVariable Long id) {
		screeningTestCategoryService.deleteScreeningTestCategoryQuestions(id);
	}
	
	//screeningTest graphs logic
	@RequestMapping(value = "/getAllScreeningTestCategoryForSocialStack/{userName:.+}", method = RequestMethod.GET)
	public @ResponseBody List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForSocialStack(@PathVariable("userName") String userName) {
		return screeningTestCategoryService.getAllScreeningTestCategoryForSocialStack(userName);
	}
	
	@RequestMapping(value = "/getAllScreeningTestCategoryForImparimentStackGraph/{userName:.+}", method = RequestMethod.GET)
	public @ResponseBody List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForImparimentStackGraph(@PathVariable("userName") String userName) {
		return screeningTestCategoryService.getAllScreeningTestCategoryForImparimentStackGraph(userName);
	}
	
	@RequestMapping(value = "/getAllScreeningTestCategoryForBehaviourStackGraph/{userName:.+}", method = RequestMethod.GET)
	public @ResponseBody List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForBehaviourStackGraph(@PathVariable("userName") String userName) {
		return screeningTestCategoryService.getAllScreeningTestCategoryForBehaviourStackGraph(userName);
	}

	// screening test date get by dates logic
	@RequestMapping(value = "/getAllScreeningTestGoalsCreatedDates/{username:.+}/{id}", method = RequestMethod.GET)
	public @ResponseBody List<CreatedDatesDto> getAllScreeningTestGoalsCreatedDates(@PathVariable("username") String username,
			@PathVariable Long id) {
		return screeningTestCategoryService.getAllScreeningTestGoalsCreatedDates(username, id);
	}
}
