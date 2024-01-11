package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluation;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.PatientManagement.dto.PhysiotherapyEvaluationDTO;

@RestController
@RequestMapping(value= "/physiotherapyEvaluation")
public class PhysiotherapyEvaluationController {
	
	
	@Autowired
	
	PhysiotherapyEvaluationService phyEvaluationService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody void savePhysiotherapyEvaluation(@RequestBody PhysiotherapyEvaluationDTO phEval) {
		phyEvaluationService.savePhysiotherapyEvalution(phEval);
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<PhysiotherapyEvaluation> getAllPhyEvaluations(){
		return phyEvaluationService.getAllPhysiotherapyEvaluations();
	}

}
