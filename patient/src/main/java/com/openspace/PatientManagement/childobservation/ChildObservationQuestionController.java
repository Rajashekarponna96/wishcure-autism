package com.openspace.PatientManagement.childobservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestion;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestionLookup;

@RestController
public class ChildObservationQuestionController {

	@Autowired
	private ChildObservationQuestionService childObservationQuestionService;
	
	@RequestMapping(value="/saveChildObservationQuestion/{userName:.+}",method=RequestMethod.POST)
	public @ResponseBody void saveChildObservationQuestion(@RequestBody List<ChildObservationQuestionLookup> questionLookupsList,@PathVariable("userName") String userName){
		childObservationQuestionService.saveChildObservationQuestion(questionLookupsList,userName);
	}
	
	@RequestMapping(value="/getAllChildObservationQuestions",method=RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestion> getAllChildObservationQuestions(){
		return childObservationQuestionService.getAllChildObservationQuestions();
	}
	
	@RequestMapping(value="/updateChildObservationQuestion/{userName:.+}",method=RequestMethod.PUT)
	public @ResponseBody void updateChildObservationQuestion(@RequestBody List<ChildObservationQuestion> childObservationQuestionsList,String userName){
		childObservationQuestionService.updateChildObservationQuestion(childObservationQuestionsList, userName);
	}
	
	@RequestMapping(value="/deleteChildObservationQuestion/{id}",method=RequestMethod.DELETE)
	public @ResponseBody void deleteChildObservationQuestion(@PathVariable("id") Long id){
		childObservationQuestionService.deleteChildObservationQuestion(id);
	}
	
	@RequestMapping(value = "/getAllChildObservationQuestionsByCategoryStatus/{categorystatus}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestion> getAllChildObservationQuestionsByStatus(@PathVariable Long categorystatus){
		return childObservationQuestionService.getAllChildObservationQuestionsByStatus(categorystatus);
		
	}
	/*@RequestMapping(value = "/getChildObservationQuestionsByPatientIdAndCategoryId/{parentid}/{catid}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestion> getChildObservationQuestionsByPatientIdAndCategoryId(@PathVariable("parentid") Long pid,@PathVariable("catid") Long cid){
		return childObservationQuestionService.getChildObservationQuestionsByPatientIdAndCategoryId(pid, cid);
		
	}*/
	/*@RequestMapping(value = "/getChildObservationQuestionsByPatientIdAndCategoryId/{pid}/{cid}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestion> getChildObservationQuestionsByPatientIdAndCategoryId(@PathVariable("pid") Long pid,@PathVariable("cid") Long cid){
		return childObservationQuestionService.getChildObservationQuestionsByPatientIdAndCategoryId(pid, cid);
	}
	@RequestMapping(value = "/getChildObservationQuestionsByPatientIdAndCategoryId/{pid}/{cid}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestion> getChildObservationQuestionsByPatientIdAndCategoryId(@PathVariable Long pid,@PathVariable Long cid){
		return childObservationQuestionService.getChildObservationQuestionsByPatientIdAndCategoryId(pid, cid);
	}
	*/
	@RequestMapping(value = "/getChildObservationQuestionsByPatientIdAndCategoryId/{paid}/{caid}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestion> getChildObservationQuestionsByPatientIdAndCategoryId(@PathVariable Long paid,@PathVariable Long caid){
		return childObservationQuestionService.getChildObservationQuestionsByPatientIdAndCategoryId(paid, caid);
	}
	
	@RequestMapping(value = "/getChildObservationQuestionsByPatientUsernameAndCategoryId/{name:.+}/{caid}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservationQuestion> getChildObservationQuestionsByPatientAndCategoryId(@PathVariable String name,@PathVariable Long caid){
		return childObservationQuestionService.getChildObservationQuestionsByPatientAndCategoryId(name, caid);
	}
	
	@RequestMapping(value = "/getAnswersCountForDashboard/{username:.+}", method = RequestMethod.GET)
	public @ResponseBody List<ChildObservarionGraphDashboardDto> getChildObservationQuestionsByPatientAndCategoryId(@PathVariable String username){
		return childObservationQuestionService.getAnswersCountForDashboard(username);
	}
}
