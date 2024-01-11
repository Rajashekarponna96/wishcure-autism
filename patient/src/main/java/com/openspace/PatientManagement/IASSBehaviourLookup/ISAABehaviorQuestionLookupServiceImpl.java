package com.openspace.PatientManagement.IASSBehaviourLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorAnswerLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookup;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorAnswerLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorQuestionLookupRepository;
import com.openspace.Model.ParentModule.csbs.CsbsAnswerLookup;
import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookup;

@Service
public class ISAABehaviorQuestionLookupServiceImpl implements ISAABehaviorQuestionLookupService {

	@Autowired
	private ISAABehaviorCategoryLookupRepository iSAABehaviorCategoryLookupRepository;
	@Autowired
	private ISAABehaviorAnswerLookupRepository iSAABehaviorAnswerLookupRepository;
	@Autowired
	private ISAABehaviorQuestionLookupRepository iSAABehaviorQuestionLookupRepository;

	@Override
	public void saveISAABehaviorQuestionLookup(ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup) {
		// TODO Auto-generated method stub
		ISAABehaviorQuestionLookup dbISAABehaviorQuestionLookup = iSAABehaviorQuestionLookupRepository
				.findByName(iSAABehaviorQuestionLookup.getName());
		if (dbISAABehaviorQuestionLookup != null) {
			throw new RuntimeException("Question Already Exist...!");
		}
		ISAABehaviorCategoryLookup dbISAABehaviorCategory = new ISAABehaviorCategoryLookup();
		if (iSAABehaviorQuestionLookup.getiSAABehaviorCategoryLookup() != null) {
			dbISAABehaviorCategory = iSAABehaviorCategoryLookupRepository
					.findOne(iSAABehaviorQuestionLookup.getiSAABehaviorCategoryLookup().getId());
			if (dbISAABehaviorCategory == null) {
				throw new RuntimeException("Category Doesnot Exist");
			}
		}

		ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup1 = new ISAABehaviorQuestionLookup();
		if (dbISAABehaviorCategory != null) {
			iSAABehaviorQuestionLookup1.setiSAABehaviorCategoryLookup(dbISAABehaviorCategory);
		}
		iSAABehaviorQuestionLookup1.setName(iSAABehaviorQuestionLookup.getName());
		iSAABehaviorQuestionLookupRepository.save(iSAABehaviorQuestionLookup1);

		List<ISAABehaviorAnswerLookup> iSAABehaviorAnswerLookupList = iSAABehaviorQuestionLookup
				.getiSAABehaviorAnswerLookups();
		for (ISAABehaviorAnswerLookup iSAABehaviorAnswerLookup : iSAABehaviorAnswerLookupList) {
			ISAABehaviorAnswerLookup iSAABehaviorAnswerLookup1 = new ISAABehaviorAnswerLookup();
			iSAABehaviorAnswerLookup1.setName(iSAABehaviorAnswerLookup.getName());
			iSAABehaviorAnswerLookup1.setiSAABehaviorQuestionLookup(iSAABehaviorQuestionLookup1);
			iSAABehaviorAnswerLookupRepository.save(iSAABehaviorAnswerLookup1);
		}

	}

	@Override
	public List<ISAABehaviorQuestionLookup> getAllISAABehaviorQuestionLookup() {
		// TODO Auto-generated method stub
		return (List<ISAABehaviorQuestionLookup>) iSAABehaviorQuestionLookupRepository.findAll();
	}

	@Override
	public void updateISAABehaviorLookup(ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup) {
		// TODO Auto-generated method stub
		ISAABehaviorQuestionLookup dbISAABehaviorQuestionLookup = iSAABehaviorQuestionLookupRepository
				.findOne(iSAABehaviorQuestionLookup.getId());
		if (dbISAABehaviorQuestionLookup == null) {
			throw new RuntimeException("ISAABehavior QuestionLookup Doesnot Exist");
		}
		ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup2 = iSAABehaviorQuestionLookupRepository
				.findByName(iSAABehaviorQuestionLookup.getName());
		if (iSAABehaviorQuestionLookup2 == null) {

		} else if (iSAABehaviorQuestionLookup2.getId().equals(iSAABehaviorQuestionLookup.getId())) {

		} else {
			throw new RuntimeException("Question Already Exist...!");
		}
		dbISAABehaviorQuestionLookup.setName(iSAABehaviorQuestionLookup.getName());
		dbISAABehaviorQuestionLookup
				.setiSAABehaviorCategoryLookup(iSAABehaviorQuestionLookup.getiSAABehaviorCategoryLookup());
		iSAABehaviorQuestionLookupRepository.save(dbISAABehaviorQuestionLookup);

		List<ISAABehaviorAnswerLookup> iSAABehaviorAnswerLookups = iSAABehaviorQuestionLookup
				.getiSAABehaviorAnswerLookups();
		for (ISAABehaviorAnswerLookup answerLookup : iSAABehaviorAnswerLookups) {
			answerLookup.setiSAABehaviorQuestionLookup(dbISAABehaviorQuestionLookup);
			iSAABehaviorAnswerLookupRepository.save(answerLookup);
		}
	}

	@Override
	public void deleteISAABehaviorQuestionLookup(Long id) {

		ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup = iSAABehaviorQuestionLookupRepository.findOne(id);
		if (iSAABehaviorQuestionLookup == null) {
			throw new RuntimeException("Question Not Found");
		}
		iSAABehaviorQuestionLookupRepository.delete(id);
	}

	@Override
	public List<ISAABehaviorQuestionLookup> getAllISAABehaviorQuestionLookupByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		ISAABehaviorCategoryLookup dbIsaaBehaviorCategoryLookup = iSAABehaviorCategoryLookupRepository
				.findOne(categoryId);
		if (dbIsaaBehaviorCategoryLookup == null) {
			throw new RuntimeException("ISAABehaviorCategoryLookup Does not exists!!");
		}

		List<ISAABehaviorQuestionLookup> isBehaviorQuestionLookups = iSAABehaviorQuestionLookupRepository
				.findByISAABehaviorCategoryLookup_Id(dbIsaaBehaviorCategoryLookup.getId());

		return isBehaviorQuestionLookups;
	}

}
