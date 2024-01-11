package com.openspace.PatientManagement.PhysioEvalLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryLookup;

@RestController
@RequestMapping(value= "/physiotherapyEvaluationCategoryLookup")
public class PhysiotherapyEvaluationCategoryLookupController {
	
	@Autowired
	PhysiotherapyEvaluationCategoryLookupService physiotherapyEvaluationCategoryService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<PhysiotherapyEvaluationCategoryLookup> getphysiotherapyEvaluationCategoryLookup() {
		return (List<PhysiotherapyEvaluationCategoryLookup>) physiotherapyEvaluationCategoryService.getAllCategories();
	}

}
