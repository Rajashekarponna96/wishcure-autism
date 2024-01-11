package com.openspace.HospitalMgnt.Template.GoalCategoryLookup;

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
@RestController
public class GoalCategoryLookupController {
	@Autowired
	private GoalCategoryLookupService goalCategoryLookupService;
	
	@RequestMapping(value=RestURIConstants.SAVE_GOAL_CATEGORY_LOOKUP, method=RequestMethod.POST)
	public @ResponseBody void saveGoalCategoryLookupService(@RequestBody GoalCategoryLookup goalCategoryLookup ){
		goalCategoryLookupService.saveGoalCategoryLookup(goalCategoryLookup);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_GOAL_CATEGORY_LOOKUP, method=RequestMethod.GET)
	public @ResponseBody List<GoalCategoryLookup> getAllGoalCategoryLookupServices(){
	
		return goalCategoryLookupService.getAllGoalCategoryLookups();
	}
	@RequestMapping(value=RestURIConstants.UPDATE_GOAL_CATEGORY_LOOKUP, method=RequestMethod.PUT)
	public @ResponseBody void updateGoalCategoryLookupService(@RequestBody GoalCategoryLookup goalCategoryLookup){
		goalCategoryLookupService.updateGoalCategoryLookup(goalCategoryLookup);
	}
	@RequestMapping(value=RestURIConstants.DELETE_GOAL_CATEGORY_LOOKUP,method=RequestMethod.DELETE)
	public @ResponseBody void deleteGoalCategoryLookupService(@PathVariable Long id){
		goalCategoryLookupService.deleteGoalCategoryLookup(id);
	}
	

}
