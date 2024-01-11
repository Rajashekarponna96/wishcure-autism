package com.openspace.PatientManagement.screeningtestLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.ScreeningTestAnswerLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ScreeningTestCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ScreeningTestQuestionLookupRepository;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestAnswerLookup;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategoryLookup;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestQuestionLookup;
@Service
public class ScreeningTestQuestionLookupServiceImpl implements ScreeningTestQuestionLookupService {
	@Autowired
	private ScreeningTestCategoryLookupRepository screeningTestLookupRepository;
	@Autowired
	private ScreeningTestAnswerLookupRepository screeningTestAnswerLookupRepository;
	@Autowired
	private ScreeningTestQuestionLookupRepository screeningTestQuestionLookupRepository;

	@Override
	public void saveScreeningTestQuestionLookup(ScreeningTestQuestionLookup screeningTestQuestionLookup) {
		// TODO Auto-generated method stub
		ScreeningTestQuestionLookup dbScreeningTestQuestionLookup = screeningTestQuestionLookupRepository
				.findByName(screeningTestQuestionLookup.getName());
		if (dbScreeningTestQuestionLookup != null) {
			throw new RuntimeException("Question Already Exist...!");
		}
		ScreeningTestCategoryLookup dbScreeningTestCategory = screeningTestLookupRepository
				.findOne(screeningTestQuestionLookup.getScreeningTestCategoryLookup().getId());
		if (dbScreeningTestCategory == null) {
			throw new RuntimeException("Category Doesnot Exist");
		}
		ScreeningTestQuestionLookup screeningTestQuestionLookup1 = new ScreeningTestQuestionLookup();
		screeningTestQuestionLookup1.setScreeningTestCategoryLookup(dbScreeningTestCategory);
		screeningTestQuestionLookup1.setName(screeningTestQuestionLookup.getName());
		screeningTestQuestionLookupRepository.save(screeningTestQuestionLookup1);
		
		List<ScreeningTestAnswerLookup> screeningTestAnswerLookupList = screeningTestQuestionLookup.getScreeningTestAnswerLookups();
		for(ScreeningTestAnswerLookup screeningTestAnswerLookup : screeningTestAnswerLookupList){
			ScreeningTestAnswerLookup screeningTestAnswerLookup1 = new ScreeningTestAnswerLookup();
			screeningTestAnswerLookup1.setName(screeningTestAnswerLookup.getName());
			screeningTestAnswerLookup1.setScreeningTestQuestionLookup(screeningTestQuestionLookup1);
			screeningTestAnswerLookupRepository.save(screeningTestAnswerLookup1);
	}

	}

	@Override
	public List<ScreeningTestQuestionLookup> getAllScreeningTestQuestionLookup() {
		// TODO Auto-generated method stub
		
       return (List<ScreeningTestQuestionLookup>) screeningTestQuestionLookupRepository.findAll();
       
        
	}

	@Override
	public void updateScreeningTestLookup(ScreeningTestQuestionLookup screeningTestQuestionLookup) {
		// TODO Auto-generated method stub
		ScreeningTestQuestionLookup dbScreeningTestQuestionLookup=screeningTestQuestionLookupRepository.findOne(screeningTestQuestionLookup.getId());
		if(dbScreeningTestQuestionLookup==null){
			throw new RuntimeException("screeningTestQuestionLookup Doesnot Exist");
		}
		ScreeningTestQuestionLookup screeningTestQuestionLookup2=screeningTestQuestionLookupRepository.findByName(screeningTestQuestionLookup.getName());
		if(screeningTestQuestionLookup2==null){
			
		}else if(screeningTestQuestionLookup2.getId().equals(screeningTestQuestionLookup.getId())){
			
		}else{
			throw new RuntimeException("Question Already Exist...!");
		}
		dbScreeningTestQuestionLookup.setName(screeningTestQuestionLookup.getName());
		dbScreeningTestQuestionLookup.setScreeningTestCategoryLookup(screeningTestQuestionLookup.getScreeningTestCategoryLookup());
		screeningTestQuestionLookupRepository.save(dbScreeningTestQuestionLookup);
		
		List<ScreeningTestAnswerLookup> screeningTestAnswerLookups=screeningTestQuestionLookup.getScreeningTestAnswerLookups();
		for(ScreeningTestAnswerLookup answerLookup :screeningTestAnswerLookups){
			answerLookup.setScreeningTestQuestionLookup(dbScreeningTestQuestionLookup);
			screeningTestAnswerLookupRepository.save(answerLookup);
		}
	}

	@Override
	public void deleteScreeningTestQuestionLookup(Long id) {
		// TODO Auto-generated method stub
		ScreeningTestQuestionLookup screeningTestQuestionLookup=screeningTestQuestionLookupRepository.findOne(id);
		screeningTestQuestionLookupRepository.delete(id);
	}

}
