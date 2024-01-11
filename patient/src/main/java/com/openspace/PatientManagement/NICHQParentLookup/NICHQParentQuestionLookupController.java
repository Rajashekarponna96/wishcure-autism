package com.openspace.PatientManagement.NICHQParentLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.NICHQParent.NICHQParentQuestionLookup;

@RestController
public class NICHQParentQuestionLookupController {

	@Autowired
	private NICHQParentQuestionLookupService nichqParentQuestionLookupService;

	@RequestMapping(value = "/saveNICHQPQLookup", method = RequestMethod.POST)
	public @ResponseBody void saveNICHQParentQuestionLookup(@RequestBody NICHQParentQuestionLookup nichqParentQuestionLookup) {
		nichqParentQuestionLookupService.saveNICHQParentQuestionLookup(nichqParentQuestionLookup);
	}

	@RequestMapping(value = "/getAllnichqParentQuestionLookup", method = RequestMethod.GET)
	public @ResponseBody List<NICHQParentQuestionLookup> getAllnichqParentQuestionLookup() {
		return nichqParentQuestionLookupService.getAllnichqParentQuestionLookup();
	}

	@RequestMapping(value = "/updateNICHQParentQuestionLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateNICHQParentQuestionLookup(@RequestBody NICHQParentQuestionLookup nichqParentQuestionLookup) {
		nichqParentQuestionLookupService.updateNICHQParentQuestionLookup(nichqParentQuestionLookup);
	}

	@RequestMapping(value = "/deleteNICHQParentQuestionLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteNICHQParentQuestionLookup(@PathVariable Long id) {
		nichqParentQuestionLookupService.deleteNICHQParentQuestionLookup(id);
	}
	
	@RequestMapping(value = "/getAllnichqParentQuestionLookupsByCategoryId/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<NICHQParentQuestionLookup> getAllnichqParentQuestionLookupsByCategoryId(@PathVariable("categoryId") Long categoryId) {
		return nichqParentQuestionLookupService.getAllnichqParentQuestionLookupsByCategoryId(categoryId);
	}

}
