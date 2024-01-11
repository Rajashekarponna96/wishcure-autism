package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryQuestions;

@RestController
@RequestMapping(value = "/physiotherapyEvaluationCategoryQuestions")
public class PhysiotherapyEvaluationCategoryQuestionsController {
	
	@Autowired
	PhysiotherapyEvaluationCategoryQuestionsService phyCategoryQuestionsService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody void savePhysiotherapyEvaluationCategoryQuestion(@RequestBody PhysiotherapyEvaluationCategoryQuestions phyEvalCatQuestions) {
		phyCategoryQuestionsService.saveCategoryQuestion(phyEvalCatQuestions);
	}

}
