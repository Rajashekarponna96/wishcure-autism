package com.openspace.PatientManagement.screeningtestLookup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Dto.ScreeningTestAnswerLookupDto;
import com.openspace.Model.Dto.ScreeningTestCategoryLookupDto;
import com.openspace.Model.Dto.ScreeningTestQuestionLookupDto;
import com.openspace.Model.ParentModule.Repositories.ScreeningTestCategoryLookupRepository;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestAnswerLookup;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategoryLookup;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestQuestionLookup;

@Service
public class ScreeningTestCategoryLookupServiceImpl implements ScreeningTestCategoryLookupService {

	@Autowired
	private ScreeningTestCategoryLookupRepository screeningTestLookupRepository;

	@Override
	public void saveScreeningTestLookup(ScreeningTestCategoryLookup screeningTestCategoryLookup) {
		// TODO Auto-generated method stub
		ScreeningTestCategoryLookup dbScreeningTestCategoryLookup = screeningTestLookupRepository
				.findByNameOrCategoryStatus(screeningTestCategoryLookup.getName(),
						screeningTestCategoryLookup.getCategoryStatus());
		if (dbScreeningTestCategoryLookup != null) {
			throw new RuntimeException("Screening Test Category Already Exist...!");
		}
		screeningTestLookupRepository.save(screeningTestCategoryLookup);

	}

	@Override
	public List<ScreeningTestCategoryLookup> getAllScreeningTestLookup() {
		// TODO Auto-generated method stub
		return (List<ScreeningTestCategoryLookup>) screeningTestLookupRepository.findAll();
	}

	@Override
	public List<ScreeningTestCategoryLookupDto> getAllScreeningTestLookupByCategoryStatus(Long id) {
		ScreeningTestCategoryLookup screeningTestCategoriesLookup = screeningTestLookupRepository
				.findOne(id);
		ArrayList<ScreeningTestCategoryLookupDto> screeningTestCategoryLookupDtoList = new ArrayList<ScreeningTestCategoryLookupDto>();
			ScreeningTestCategoryLookupDto ScreeningTestCategoryLookupDto =new ScreeningTestCategoryLookupDto();
			ScreeningTestCategoryLookupDto.setId(screeningTestCategoriesLookup.getId());
			ScreeningTestCategoryLookupDto.setName(screeningTestCategoriesLookup.getName());
			ArrayList<ScreeningTestQuestionLookupDto> screeningTestQuestionLookupDtoList = new ArrayList<ScreeningTestQuestionLookupDto>();
			List<ScreeningTestQuestionLookup> screeningTestQuestionLookupList = screeningTestCategoriesLookup.getScreeningTestQuestionLookups();
			for(ScreeningTestQuestionLookup screeningTestQuestionLookup:screeningTestQuestionLookupList){
				ScreeningTestQuestionLookupDto screeningTestQuestionLookupDto = new ScreeningTestQuestionLookupDto();
				screeningTestQuestionLookupDto.setId(screeningTestQuestionLookup.getId());
				screeningTestQuestionLookupDto.setName(screeningTestQuestionLookup.getName());
				List<ScreeningTestAnswerLookup> screeningTestAnswerLookupList = screeningTestQuestionLookup.getScreeningTestAnswerLookups();
				ArrayList<ScreeningTestAnswerLookupDto> screeningTestAnswerLookupDtoList = new ArrayList<ScreeningTestAnswerLookupDto>();
				for(ScreeningTestAnswerLookup screeningTestAnswerLookup : screeningTestAnswerLookupList){
					ScreeningTestAnswerLookupDto screeningTestAnswerLookupDto =new ScreeningTestAnswerLookupDto();
					screeningTestAnswerLookupDto.setId(screeningTestAnswerLookup.getId());
					screeningTestAnswerLookupDto.setName(screeningTestAnswerLookup.getName());
					screeningTestAnswerLookupDto.setSelectedAnswer(screeningTestAnswerLookup.isSelectedAnswer());
					screeningTestAnswerLookupDtoList.add(screeningTestAnswerLookupDto);
				}
				screeningTestQuestionLookupDto.setScreeningTestAnswerLookups(screeningTestAnswerLookupDtoList);
				screeningTestQuestionLookupDtoList.add(screeningTestQuestionLookupDto);
			}
			ScreeningTestCategoryLookupDto.setScreeningTestQuestionLookups(screeningTestQuestionLookupDtoList);
			screeningTestCategoryLookupDtoList.add(ScreeningTestCategoryLookupDto);

		return screeningTestCategoryLookupDtoList;
	}

	@Override
	public void updateScreeningTestLookup(ScreeningTestCategoryLookup screeningTestCategoryLookup) {
		// TODO Auto-generated method stub
		ScreeningTestCategoryLookup dbScreeningTestCategoryLookup = screeningTestLookupRepository
				.findOne(screeningTestCategoryLookup.getId());
		if (dbScreeningTestCategoryLookup == null) {
			throw new RuntimeException("Screening Test Category doesn't Exist");
		}
		dbScreeningTestCategoryLookup.setName(screeningTestCategoryLookup.getName());
		dbScreeningTestCategoryLookup.setCategoryStatus(screeningTestCategoryLookup.getCategoryStatus());
		screeningTestLookupRepository.save(dbScreeningTestCategoryLookup);

	}

	@Override
	public void deleteScreeningTestLookup(Long id) {
		// TODO Auto-generated method stub
		ScreeningTestCategoryLookup dbScreeningTestCategoryLookup = screeningTestLookupRepository.findOne(id);
		if (dbScreeningTestCategoryLookup == null) {
			throw new RuntimeException("Screening Test doesn't Exist");
		}
		screeningTestLookupRepository.delete(dbScreeningTestCategoryLookup);
	}

}
