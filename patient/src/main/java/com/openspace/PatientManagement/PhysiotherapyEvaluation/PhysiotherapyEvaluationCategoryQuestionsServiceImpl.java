package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluation;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryQuestions;
import com.openspace.Model.PatientMgnt.Repositories.PhysiotherapyEvaluationCategoryQuestionsRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PhysiotherapyEvaluationCategoryQuestionsServiceImpl implements PhysiotherapyEvaluationCategoryQuestionsService {

	@Autowired
	PhysiotherapyEvaluationCategoryQuestionsRepository phyEvalCategQuestRepos;
	
	@Override
	public void saveCategoryQuestion(PhysiotherapyEvaluationCategoryQuestions phCategoryQuestions) {
		// TODO Auto-generated method stub
		
		PhysiotherapyEvaluationCategoryQuestions physiotherapyEvaluationCatQues = phyEvalCategQuestRepos.findById(phCategoryQuestions.getId()); 
		
		if (physiotherapyEvaluationCatQues != null) {
			throw new RuntimeException(ErrorMessageHandler.physiotherapyCategoryQuestionsAlreadyExists);
		} else {
			
			PhysiotherapyEvaluationCategoryQuestions phyQuestions = new PhysiotherapyEvaluationCategoryQuestions();
			
			phyQuestions.setpEvaluationCategoryAnswers(phCategoryQuestions.getpEvaluationCategoryAnswers());
			phyQuestions.setQuestName(phCategoryQuestions.getQuestName());
			phyQuestions.setPhysiotherapyEvaluationCategory(phCategoryQuestions.getPhysiotherapyEvaluationCategory());
			
			phyEvalCategQuestRepos.save(phyQuestions);
			
		}
		
		
		
	}

	@Override
	public List<PhysiotherapyEvaluationCategoryQuestions> getAllCategoryQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

}
