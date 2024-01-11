package com.openspace.PatientManagement.PhysioEvalLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryLookup;
import com.openspace.Model.PatientMgntLookup.Repositories.PhysiotherapyEvaluationCategoryLookupRepository;

@Service
public class PhysiotherapyEvaluationCategoryLookupServiceImpl implements PhysiotherapyEvaluationCategoryLookupService {
	
	@Autowired
	PhysiotherapyEvaluationCategoryLookupRepository phyEvalCatLookupRespo;

	@Override
	public List<PhysiotherapyEvaluationCategoryLookup> getAllCategories() {
		// TODO Auto-generated method stub
		return (List<PhysiotherapyEvaluationCategoryLookup>) phyEvalCatLookupRespo.findAll();
	}

}
