package com.openspace.PatientManagement.NICHQTeacherLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.NICHQTeacherLookup.NichqTeacherQuestionLookup;

@RestController
@RequestMapping(value = "/nichqTeacherQuestionLookup")
public class NichqTeacherQuestionLookupController {

	@Autowired
	private NichqTeacherQuestionLookupService nichqTeacherQuestionLookupService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addQuestionLookup(@RequestBody NichqTeacherQuestionLookup NichqTeacherQuestionLookup) {
		nichqTeacherQuestionLookupService.addQuestionLookup(NichqTeacherQuestionLookup);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<NichqTeacherQuestionLookup> getAllQuestionLookups() {
		return nichqTeacherQuestionLookupService.getAllQuestionLookups();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateNichqTeacherQuestionLookup(@RequestBody NichqTeacherQuestionLookup NichqTeacherQuestionLookup ) {
		 nichqTeacherQuestionLookupService.updateNichqTeacherQuestionLookup(NichqTeacherQuestionLookup);
	}
	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void  deleteNichqTeacherQuestionLookup(@PathVariable("id") Long id) {
		 nichqTeacherQuestionLookupService.deleteNichqTeacherQuestionLookup(id);
	}
	

	@RequestMapping(value = "/allByCategoryId/{categoryId}", method = RequestMethod.GET)
	public List<NichqTeacherQuestionLookup> getAllQuestionLookupsByCategory(@PathVariable("categoryId") Long categoryId) {
		return nichqTeacherQuestionLookupService.getAllQuestionLookupsByCategory(categoryId);
	}
	
	
	

}
