package com.openspace.PatientManagement.ParentModule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.CareTakerGraphDashboardDto;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestion;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestionLookup;
import com.openspace.PatientManagement.ParentModule.service.CareTakerCategoryService;

@RestController
public class CareTakerCategoryController {
@Autowired
private CareTakerCategoryService  careTakerCategoryService;

	@RequestMapping(value = "/getCareTakerQuestionsByCategoryStatus/{id}")
	public@ResponseBody  List<CareTakerQuestionLookup>  getQuestionsBasedOnCategoryStatus(@PathVariable Long id) {
		return careTakerCategoryService.getQuestionsBasedOnCategoryStatus(id);
	}
	@RequestMapping(value = "/saveCaretakerMilestones/{adminUserName:.+}")
	public@ResponseBody  void  saveCaretakerMilestones(@PathVariable String adminUserName,@RequestBody List<CareTakerQuestionLookup> careTakerQuestionLookupList) {
		careTakerCategoryService.saveCaretakerMilestones(adminUserName,careTakerQuestionLookupList);
	}
	
	@RequestMapping(value = "/getCaretakerMilestones/{id}/{adminUserName:.+}")
	public@ResponseBody  List<CareTakerQuestion>  getAllCaretakerMilestones(@PathVariable Long id,@PathVariable String adminUserName) {
		return careTakerCategoryService.getAllCaretakerMilestones(id,adminUserName);
	}
	
	@RequestMapping(value = "/updateCaretakerMilestones/{adminUserName:.+}")
	public@ResponseBody  void  updateCaretakerMilestones(@RequestBody List<CareTakerQuestion> careTakerQuestionList ,@PathVariable String adminUserName) {
		System.out.println("Controller"+careTakerQuestionList.size());
		 careTakerCategoryService.updateCaretakerMilestones(careTakerQuestionList,adminUserName);
	}
	@RequestMapping(value = "/getCaretakerAnswersCountForDashboard/{adminUserName:.+}", method = RequestMethod.GET)
	public @ResponseBody List<CareTakerGraphDashboardDto> getCaretakerMilstonesForDashboard(@PathVariable String adminUserName){
		return careTakerCategoryService.getCaretakerMilstonesForDashboard(adminUserName);
	}
}
