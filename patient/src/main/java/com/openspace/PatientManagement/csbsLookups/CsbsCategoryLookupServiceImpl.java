package com.openspace.PatientManagement.csbsLookups;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.CsbsCategoryLookupRepository;
import com.openspace.Model.ParentModule.csbs.CsbsAnswerLookup;
import com.openspace.Model.ParentModule.csbs.CsbsAnswerLookupDto;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookupDto;
import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookup;
import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookupDto;

@Service
public class CsbsCategoryLookupServiceImpl implements CsbsCategoryLookupService {

	@Autowired
	private CsbsCategoryLookupRepository csbsLookupRepository;
	
	@Override
	public void saveCsbsLookup(CsbsCategoryLookup csbsCategoryLookup) {
		// TODO Auto-generated method stub
		CsbsCategoryLookup dbCsbsCategoryLookup = csbsLookupRepository
				.findByNameOrCategoryStatus(csbsCategoryLookup.getName(),
						csbsCategoryLookup.getCategoryStatus());
		if (dbCsbsCategoryLookup != null) {
			throw new RuntimeException("CSBS Category Already Exist...!");
		}
		csbsLookupRepository.save(csbsCategoryLookup);
	}

	@Override
	public List<CsbsCategoryLookup> getAllCsbsLookup() {
		// TODO Auto-generated method stub
		return (List<CsbsCategoryLookup>) csbsLookupRepository.findAll();
	}

	@Override
	public void updateCsbsLookup(CsbsCategoryLookup csbsCategoryLookup) {
		// TODO Auto-generated method stub
		CsbsCategoryLookup dbCsbsCategoryLookup = csbsLookupRepository
				.findOne(csbsCategoryLookup.getId());
		if (dbCsbsCategoryLookup == null) {
			throw new RuntimeException("CSBS Category doesn't Exist");
		}
		dbCsbsCategoryLookup.setName(csbsCategoryLookup.getName());
		dbCsbsCategoryLookup.setCategoryStatus(csbsCategoryLookup.getCategoryStatus());
		csbsLookupRepository.save(dbCsbsCategoryLookup);
	}

	@Override
	public void deleteCsbsLookup(Long id) {
		// TODO Auto-generated method stub
		CsbsCategoryLookup dbCsbsCategoryLookup = csbsLookupRepository.findOne(id);
		if (dbCsbsCategoryLookup == null) {
			throw new RuntimeException("CSBS doesn't Exist");
		}
		csbsLookupRepository.delete(dbCsbsCategoryLookup);
	}

	@Override
	public List<CsbsCategoryLookupDto> getAllCsbsLookupByCategoryStatus(Long id) {
		CsbsCategoryLookup csbsCategoriesLookup = csbsLookupRepository
				.findOne(id);
		ArrayList<CsbsCategoryLookupDto> csbsCategoryLookupDtoList = new ArrayList<CsbsCategoryLookupDto>();
			CsbsCategoryLookupDto csbsCategoryLookupDto =new CsbsCategoryLookupDto();
			csbsCategoryLookupDto.setId(csbsCategoriesLookup.getId());
			csbsCategoryLookupDto.setName(csbsCategoriesLookup.getName());
			csbsCategoryLookupDto.setCategoryStatus(csbsCategoriesLookup.getCategoryStatus());
			ArrayList<CsbsQuestionLookupDto> csbsQuestionLookupDtoList = new ArrayList<CsbsQuestionLookupDto>();
			List<CsbsQuestionLookup> csbsQuestionLookupList = csbsCategoriesLookup.getcsbsQuestionLookups();
			for(CsbsQuestionLookup csbsQuestionLookup:csbsQuestionLookupList){
				CsbsQuestionLookupDto csbsQuestionLookupDto = new CsbsQuestionLookupDto();
				csbsQuestionLookupDto.setId(csbsQuestionLookup.getId());
				csbsQuestionLookupDto.setName(csbsQuestionLookup.getName());
				List<CsbsAnswerLookup> csbsAnswerLookupList = csbsQuestionLookup.getcsbsAnswerLookups();
				ArrayList<CsbsAnswerLookupDto> csbsAnswerLookupDtoList = new ArrayList<CsbsAnswerLookupDto>();
				for(CsbsAnswerLookup csbsAnswerLookup : csbsAnswerLookupList){
					CsbsAnswerLookupDto csbsAnswerLookupDto =new CsbsAnswerLookupDto();
					csbsAnswerLookupDto.setId(csbsAnswerLookup.getId());
					csbsAnswerLookupDto.setName(csbsAnswerLookup.getName());
					csbsAnswerLookupDto.setSelectedAnswer(csbsAnswerLookup.isSelectedAnswer());
					csbsAnswerLookupDtoList.add(csbsAnswerLookupDto);
				}
				csbsQuestionLookupDto.setcsbsAnswerLookups(csbsAnswerLookupDtoList);
				csbsQuestionLookupDtoList.add(csbsQuestionLookupDto);
			}
			csbsCategoryLookupDto.setcsbsQuestionLookups(csbsQuestionLookupDtoList);
			csbsCategoryLookupDtoList.add(csbsCategoryLookupDto);

		return csbsCategoryLookupDtoList;
	}
}