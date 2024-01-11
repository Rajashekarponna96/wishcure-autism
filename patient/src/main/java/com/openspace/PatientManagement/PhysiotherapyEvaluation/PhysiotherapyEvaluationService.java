package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import java.util.List;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluation;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategory;
import com.openspace.PatientManagement.dto.PhysiotherapyEvaluationDTO;

public interface PhysiotherapyEvaluationService {
	
	void savePhysiotherapyEvalution(PhysiotherapyEvaluationDTO phEval);
	
	List<PhysiotherapyEvaluation> getAllPhysiotherapyEvaluations();
	
	void updatePhysiotherapyEvaluation(PhysiotherapyEvaluationDTO phEvalDto);
	
	void deletePhysiotherapyEvaluation(Long id);

}
