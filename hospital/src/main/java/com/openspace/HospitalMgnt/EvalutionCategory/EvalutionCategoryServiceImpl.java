package com.openspace.HospitalMgnt.EvalutionCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.EvalutionCategoryRepository;
import com.openspace.Model.Template.EvalutionCategory;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class EvalutionCategoryServiceImpl implements EvalutionCategoryService {
	
	@Autowired
	private EvalutionCategoryRepository evalutionCategoryRepository;

	@Override
	public void saveEvalutionCategory(EvalutionCategory evalutionCategory) {
		// TODO Auto-generated method stub
		EvalutionCategory dbEvalutionCategory=evalutionCategoryRepository.findByCategoryName(evalutionCategory.getCategoryName());
		if(dbEvalutionCategory!=null){
			throw new RuntimeException(ErrorMessageHandler.evalauationCategoryAlreadyExists);
		}
		evalutionCategoryRepository.save(evalutionCategory);
	}

	@Override
	public List<EvalutionCategory> getAllCategorys() {
		// TODO Auto-generated method stub
		return (List<EvalutionCategory>) evalutionCategoryRepository.findAll();
	}

	@Override
	public void updateEvalutionCategory(EvalutionCategory evalutionCategory) {
		EvalutionCategory dbEvalutionCategory=evalutionCategoryRepository.findOne(evalutionCategory.getId());
		if(dbEvalutionCategory==null){
			throw new RuntimeException(ErrorMessageHandler.evalauationCategoryDoesNotExists);
		}
		dbEvalutionCategory.setCategoryName(evalutionCategory.getCategoryName());
		evalutionCategoryRepository.save(dbEvalutionCategory);
		
	}

	@Override
	public void deleteEvalutionCategory(Long id) {
		EvalutionCategory dbEvalutionCategory=evalutionCategoryRepository.findOne(id);
		if(dbEvalutionCategory==null){
			throw new RuntimeException(ErrorMessageHandler.evalauationCategoryDoesNotExists);
		}
		evalutionCategoryRepository.delete(dbEvalutionCategory);
	}

}
