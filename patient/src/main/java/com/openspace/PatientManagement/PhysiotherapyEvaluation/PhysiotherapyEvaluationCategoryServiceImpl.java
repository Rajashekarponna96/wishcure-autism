package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluation;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategory;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryAnswers;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryQuestions;
import com.openspace.Model.DoctorManagement.Tax;
import com.openspace.Model.PatientMgnt.Repositories.PhysiotherapyEvaluationCategoryRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PhysiotherapyEvaluationCategoryServiceImpl implements PhysiotherapyEvaluationCategoryService {

	@Autowired
	PhysiotherapyEvaluationCategoryRepository physiotherapyEvalCategoryRep;

	@Override
	public void saveCategory(PhysiotherapyEvaluationCategory phCategory) {
		// TODO Auto-generated method stub

		PhysiotherapyEvaluationCategory phyCategory = physiotherapyEvalCategoryRep.findById(phCategory.getId());

		if (phyCategory != null) {
			throw new RuntimeException(ErrorMessageHandler.physiotherapyCategoryAlreadyExists);
		} else {

			/*
			 * PhysiotherapyEvaluationCategory pCategory = new
			 * PhysiotherapyEvaluationCategory();
			 * 
			 * pCategory.setCategoryName(phCategory.getCategoryName());
			 * 
			 * pCategory.setpCategoryQuestions(phCategory.getpCategoryQuestions());
			 * 
			 * pCategory.setPhysiotherapyEvaluation(phCategory.getPhysiotherapyEvaluation())
			 * ;
			 */

			physiotherapyEvalCategoryRep.save(phCategory);

		}

	}

	@Override
	public List<PhysiotherapyEvaluationCategory> getAllCategories() {
		// TODO Auto-generated method stub
		return (List<PhysiotherapyEvaluationCategory>) physiotherapyEvalCategoryRep.findAll();
	}

	@Override
	public void updatePhysiotherapyEvaluationCategory(PhysiotherapyEvaluationCategory physiotherapyEvaluationCategory) {
		// TODO Auto-generated method stub
		PhysiotherapyEvaluationCategory dbPhCategory = physiotherapyEvalCategoryRep
				.findOne(physiotherapyEvaluationCategory.getId());
		if (dbPhCategory == null) {
			throw new RuntimeException(ErrorMessageHandler.physiotherapyCategoryDoesNotExists);
		} else {
			PhysiotherapyEvaluationCategory dbPhCategory2 = physiotherapyEvalCategoryRep
					.findByCategoryName(physiotherapyEvaluationCategory.getCategoryName());

			if (dbPhCategory2 == null) {
				dbPhCategory.setCategoryName(dbPhCategory.getCategoryName());
			} else if (dbPhCategory2.getId().equals(physiotherapyEvaluationCategory.getId())) {
				dbPhCategory.setCategoryName(physiotherapyEvaluationCategory.getCategoryName());
			} else {
				throw new RuntimeException("PhysiothrapyEvaluationCategory already Exists!");
			}

			dbPhCategory.setPhysiotherapyEvaluation(physiotherapyEvaluationCategory.getPhysiotherapyEvaluation());
			dbPhCategory.setpCategoryQuestions(physiotherapyEvaluationCategory.getpCategoryQuestions());

			physiotherapyEvalCategoryRep.save(dbPhCategory);

		}

	}

	@Override
	public void deletePhysiotherapyEvaluationCategory(Long id) {
		// TODO Auto-generated method stub

		PhysiotherapyEvaluationCategory phCategory = physiotherapyEvalCategoryRep.findOne(id);

		if (phCategory == null) {
			throw new RuntimeException("PhysiothrapyEvaluationCategory doesn't exist");
		}

		physiotherapyEvalCategoryRep.delete(id);

	}

}
