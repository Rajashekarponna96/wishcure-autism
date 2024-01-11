package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/hysiotherapyEvaluationCategoryAnswer")
public class PhysiotherapyEvaluationCategoryAnswersController {
	
	@Autowired
	PhysiotherapyEvaluationCategoryAnswersService phyAnswersService;
	
	

}
