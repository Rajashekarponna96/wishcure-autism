package com.openspace.PatientManagement.IASSBehaviourLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookup;

@RestController
public class ISAABehaviorQuestionLookupController {

	@Autowired
	private ISAABehaviorQuestionLookupService iSAABehaviorQuestionLookupService;

	@RequestMapping(value = "/saveISAABehaviorQuestionLookup", method = RequestMethod.POST)
	public @ResponseBody void saveISAABehaviorQuestionLookup(
			@RequestBody ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup) {
		iSAABehaviorQuestionLookupService.saveISAABehaviorQuestionLookup(iSAABehaviorQuestionLookup);
	}

	@RequestMapping(value = "/getAllISAABehaviorQuestionLookup", method = RequestMethod.GET)
	public @ResponseBody List<ISAABehaviorQuestionLookup> getAllISAABehaviorQuestionLookup() {
		return iSAABehaviorQuestionLookupService.getAllISAABehaviorQuestionLookup();
	}

	@RequestMapping(value = "/updateISAABehaviorQuestionLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateISAABehaviorQuestionLookup(
			@RequestBody ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup) {
		iSAABehaviorQuestionLookupService.updateISAABehaviorLookup(iSAABehaviorQuestionLookup);
	}

	@RequestMapping(value = "/deleteISAABehaviorQuestionLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteISAABehaviorQuestionLookup(@PathVariable Long id) {
		iSAABehaviorQuestionLookupService.deleteISAABehaviorQuestionLookup(id);
	}

	@RequestMapping(value = "/getAllISAABehaviorQuestionLookupsByCategory/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<ISAABehaviorQuestionLookup> getAllISAABehaviorQuestionLookupByCategoryId(
			@PathVariable("categoryId") Long categoryId) {
		return iSAABehaviorQuestionLookupService.getAllISAABehaviorQuestionLookupByCategoryId(categoryId);
	}

}
