package com.openspace.PatientManagement.NICHQParent;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.NICHQParent.NichqParentCategory;
import com.openspace.Model.NICHQParent.NichqParentCategoryDto;
import com.openspace.Model.NICHQParent.NichqParentDto;
import com.openspace.Model.NICHQParent.NichqParentResult;

@RestController
@RequestMapping(value = "/nichqParentCategory")
public class NichqParentCategoryController {

	@Autowired
	private NichqParentCategoryService nichqParentCategoryService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody void saveNichqCategoryQuestions(@RequestBody NichqParentCategoryDto nichqParentCategoryDto) {
		nichqParentCategoryService.saveNichqCategoryQuestions(nichqParentCategoryDto);
	}

	@RequestMapping(value = "/allBy/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<NichqParentCategory> getAllNichqCategorysByPatientId(
			@PathVariable("patientId") Long patientId) {
		return nichqParentCategoryService.getAllNichqCategorysByPatientId(patientId);
	}
	
	@RequestMapping(value = "/parentResult/{patientId}", method = RequestMethod.GET)
	public @ResponseBody NichqParentResult getNichqParentResultByPatientId(@PathVariable("patientId") Long patientId) {
		return nichqParentCategoryService.getNichqParentResultByPatientId(patientId);
	}
	
	
	@RequestMapping(value = "/deleteParentResult/{patientId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteNichqParentResultByPatientId(@PathVariable("patientId") Long patientId) {
		 nichqParentCategoryService.deleteNichqParentResultByPatientId(patientId);
	}
	
	
}
