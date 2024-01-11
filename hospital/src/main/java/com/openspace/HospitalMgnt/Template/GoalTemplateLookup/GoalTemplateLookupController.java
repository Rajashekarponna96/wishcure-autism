package com.openspace.HospitalMgnt.Template.GoalTemplateLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.GoalCategoryLookup;
import com.openspace.Model.DoctorManagement.GoalLookup;
import com.openspace.Model.ParentModule.caretaker.CareTakerCategoryLookup;
import com.openspace.Model.Template.GoalTemplateLookup;

@RestController
public class GoalTemplateLookupController {
	@Autowired
	private GoalTemplateLookupService goalTemplateLookupService;

	@RequestMapping(value = RestURIConstants.SAVE_GOAL_TEMPLATE_LOOKUP, method = RequestMethod.POST)
	public @ResponseBody void saveGoalTemplateLookup(@RequestBody GoalTemplateLookup goalLookup) {
		goalTemplateLookupService.saveGoalTemplateLookup(goalLookup);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_GOAL_TEMPLATE_LOOKUP, method = RequestMethod.GET)
	public @ResponseBody List<GoalTemplateLookup> getAllGoalTemplateLookups() {
		return goalTemplateLookupService.getAllGoalTemplateLookups();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_GOAL_TEMPLATE_LOOKUP, method = RequestMethod.PUT)
	public @ResponseBody void updateGoalLookup(@RequestBody GoalTemplateLookup goalTemplateLookup) {
		goalTemplateLookupService.updateGoalTemplateLookup(goalTemplateLookup);
	}

	@RequestMapping(value = RestURIConstants.DELETE_GOAL_TEMPLATE_LOOKUP, method = RequestMethod.DELETE)
	public @ResponseBody void deleteGoalLookup(@PathVariable Long id) {
		goalTemplateLookupService.deleteGoalTemplateLookup(id);
	}

	@RequestMapping(value = RestURIConstants.GET_GOAL_TEMPLATE_LOOKUP, method = RequestMethod.GET)
	public @ResponseBody GoalLookup getGoalLookup(@PathVariable Long id) {
		return goalTemplateLookupService.getGoalTemplateLookup(id);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_GOAL_TEMPLATE_LOOKUP_BY_PATIENT_ID, method = RequestMethod.GET)
	public @ResponseBody List<GoalTemplateLookup> getAllGoalTemplateLookupsByPatient(
			@PathVariable("patientId") Long patientId) {
		return goalTemplateLookupService.getAllGoalTemplateLookupsByPatient(patientId);
	}

	@RequestMapping(value = RestURIConstants.GET_GOAL_TEMPLATE_LOOKUPS_BY_STATUS, method = RequestMethod.GET)
	public @ResponseBody List<GoalTemplateLookup> getAllGoalLookupsByStatus(@PathVariable("status") String status) {
		return goalTemplateLookupService.getAllGoalLookupsByStatus(status);
	}

	@RequestMapping(value = RestURIConstants.ASSIGN_GOAL_LOOKUPS_TO_PATIENT, method = RequestMethod.POST)
	public @ResponseBody void assignGoalLookupsToPatient(@RequestBody List<GoalTemplateLookup> goalLookups,
			@PathVariable("patientId") Long patientId) {
		goalTemplateLookupService.assignGoalsToPatient(goalLookups,patientId);
	}
	
}
