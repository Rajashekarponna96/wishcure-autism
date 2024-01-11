package com.openspace.PatientManagement.csbsLookups;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.CsbsAnswerLookupRepository;
import com.openspace.Model.ParentModule.Repositories.CsbsCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.CsbsQuestionLookupRepository;
import com.openspace.Model.ParentModule.csbs.CsbsAnswerLookup;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;
import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookup;
@Service
public class CsbsQuestionLookupServiceImpl implements CsbsQuestionLookupService {

	@Autowired
	private CsbsCategoryLookupRepository csbsCategoryLookupRepository;
	@Autowired
	private CsbsAnswerLookupRepository csbsAnswerLookupRepository;
	@Autowired
	private CsbsQuestionLookupRepository csbsQuestionLookupRepository;
	@Override
	public void savecsbsQuestionLookup(CsbsQuestionLookup csbsQuestionLookup) {
		// TODO Auto-generated method stub
		CsbsQuestionLookup dbCsbsQuestionLookup = csbsQuestionLookupRepository.findByName(csbsQuestionLookup.getName());
		if (dbCsbsQuestionLookup != null) {
			throw new RuntimeException("Question Already Exist...!");
		}
		CsbsCategoryLookup dbCsbsCategory = csbsCategoryLookupRepository.findOne(csbsQuestionLookup.getcsbsCategoryLookup().getId());
		if (dbCsbsCategory == null) {
			throw new RuntimeException("Category Doesnot Exist");
		}
		CsbsQuestionLookup csbsQuestionLookup1 = new CsbsQuestionLookup();
		csbsQuestionLookup1.setcsbsCategoryLookup(dbCsbsCategory);
		csbsQuestionLookup1.setName(csbsQuestionLookup.getName());
		csbsQuestionLookupRepository.save(csbsQuestionLookup1);
		
		List<CsbsAnswerLookup> csbsAnswerLookupList = csbsQuestionLookup.getcsbsAnswerLookups();
		for(CsbsAnswerLookup csbsAnswerLookup : csbsAnswerLookupList){
			CsbsAnswerLookup csbsAnswerLookup1 = new CsbsAnswerLookup();
			csbsAnswerLookup1.setName(csbsAnswerLookup.getName());
			csbsAnswerLookup1.setcsbsQuestionLookup(csbsQuestionLookup1);
			csbsAnswerLookupRepository.save(csbsAnswerLookup1);
	}
		
	}

	@Override
	public List<CsbsQuestionLookup> getAllcsbsQuestionLookup() {
		// TODO Auto-generated method stub
		 return (List<CsbsQuestionLookup>) csbsQuestionLookupRepository.findAll();
	}

	@Override
	public void updatecsbsLookup(CsbsQuestionLookup csbsQuestionLookup) {
		// TODO Auto-generated method stub
		CsbsQuestionLookup dbCsbsQuestionLookup=csbsQuestionLookupRepository.findOne(csbsQuestionLookup.getId());
		if(dbCsbsQuestionLookup==null){
			throw new RuntimeException("CSBS QuestionLookup Doesnot Exist");
		}
		CsbsQuestionLookup csbsQuestionLookup2=csbsQuestionLookupRepository.findByName(csbsQuestionLookup.getName());
		if(csbsQuestionLookup2==null){
			
		}else if(csbsQuestionLookup2.getId().equals(csbsQuestionLookup.getId())){
			
		}else{
			throw new RuntimeException("Question Already Exist...!");
		}
		dbCsbsQuestionLookup.setName(csbsQuestionLookup.getName());
		dbCsbsQuestionLookup.setcsbsCategoryLookup(csbsQuestionLookup.getcsbsCategoryLookup());
		csbsQuestionLookupRepository.save(dbCsbsQuestionLookup);
		
		List<CsbsAnswerLookup> csbsAnswerLookups=csbsQuestionLookup.getcsbsAnswerLookups();
		for(CsbsAnswerLookup answerLookup :csbsAnswerLookups){
			answerLookup.setcsbsQuestionLookup(dbCsbsQuestionLookup);
			csbsAnswerLookupRepository.save(answerLookup);
		}
	}

	@Override
	public void deletecsbsQuestionLookup(Long id) {
		CsbsQuestionLookup csbsQuestionLookup=csbsQuestionLookupRepository.findOne(id);
		csbsQuestionLookupRepository.delete(id);
	}
	/*@Autowired
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
*/

	@Override
	public List<CsbsQuestionLookup> getAllcsbsQuestionLookupByCategoryId(Long catId) {
		return csbsQuestionLookupRepository.findByCsbsCategoryLookup_id(catId);
	}
}
