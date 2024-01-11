package com.openspace.PatientManagement.IASSBehaviourLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookupDto;

@RestController
public class ISAABehaviorCategoryLookupController {
	@Autowired
	private ISAABehaviorCategoryLookupService iSAABehaviorLookupService;
	
	@RequestMapping(value = "/saveISAABehaviorLookup", method = RequestMethod.POST)
	public @ResponseBody void saveISAABehaviorLookup(@RequestBody ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup){
		iSAABehaviorLookupService.saveISAABehaviorLookup(iSAABehaviorCategoryLookup);
	}
	
	@RequestMapping(value = "/getAllISAABehaviorLookup", method = RequestMethod.GET)
	public @ResponseBody List<ISAABehaviorCategoryLookup> getAllISAABehaviorLookup(){
		return iSAABehaviorLookupService.getAllISAABehaviorLookup();
	}

	@RequestMapping(value = "/getAllISAABehaviorLookupByCategoryStatus/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ISAABehaviorCategoryLookupDto> getAllISAABehaviorLookupByCategoryStatus(@PathVariable Long id){
		return iSAABehaviorLookupService.getAllISAABehaviorLookupByCategoryStatus(id);
	}
	
	@RequestMapping(value = "/updateISAABehaviorLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateISAABehaviorLookup(@RequestBody ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup){
		iSAABehaviorLookupService.updateISAABehaviorLookup(iSAABehaviorCategoryLookup);
	}

	@RequestMapping(value = "/deleteISAABehaviorLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteISAABehaviorLookup(@PathVariable Long id){
		iSAABehaviorLookupService.deleteISAABehaviorLookup(id);
	}
}
