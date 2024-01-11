package com.openspace.PatientManagement.IASSBehaviourLookup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorAnswerLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorAnswerLookupDto;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookupDto;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookupDto;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorCategoryLookupRepository;

@Service
public class ISAABehaviorCategoryLookupServiceImpl implements ISAABehaviorCategoryLookupService {

	@Autowired
	private ISAABehaviorCategoryLookupRepository iSAABehaviorLookupRepository;

	@Override
	public void saveISAABehaviorLookup(ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup) {
		// TODO Auto-generated method stub
		ISAABehaviorCategoryLookup dbISAABehaviorCategoryLookup = iSAABehaviorLookupRepository
				.findByName(iSAABehaviorCategoryLookup.getName());
		if (dbISAABehaviorCategoryLookup != null) {
			throw new RuntimeException("ISAABehavior Category Already Exist...!");
		}
		iSAABehaviorLookupRepository.save(iSAABehaviorCategoryLookup);
	}

	@Override
	public List<ISAABehaviorCategoryLookup> getAllISAABehaviorLookup() {
		// TODO Auto-generated method stub
		return (List<ISAABehaviorCategoryLookup>) iSAABehaviorLookupRepository.findAll();
	}

	@Override
	public void updateISAABehaviorLookup(ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup) {
		// TODO Auto-generated method stub
		ISAABehaviorCategoryLookup dbISAABehaviorCategoryLookup = iSAABehaviorLookupRepository
				.findOne(iSAABehaviorCategoryLookup.getId());
		if (dbISAABehaviorCategoryLookup == null) {
			throw new RuntimeException("ISAABehavior Category doesn't Exist");
		}
		dbISAABehaviorCategoryLookup.setName(iSAABehaviorCategoryLookup.getName());
		dbISAABehaviorCategoryLookup.setDescription(iSAABehaviorCategoryLookup.getDescription());
		iSAABehaviorLookupRepository.save(dbISAABehaviorCategoryLookup);
	}

	@Override
	public void deleteISAABehaviorLookup(Long id) {
		// TODO Auto-generated method stub
		ISAABehaviorCategoryLookup dbISAABehaviorCategoryLookup = iSAABehaviorLookupRepository.findOne(id);
		if (dbISAABehaviorCategoryLookup == null) {
			throw new RuntimeException("ISAABehavior doesn't Exist");
		}
		iSAABehaviorLookupRepository.delete(dbISAABehaviorCategoryLookup);
	}

	@Override
	public List<ISAABehaviorCategoryLookupDto> getAllISAABehaviorLookupByCategoryStatus(Long id) {
		ISAABehaviorCategoryLookup iSAABehaviorCategoriesLookup = iSAABehaviorLookupRepository.findOne(id);
		ArrayList<ISAABehaviorCategoryLookupDto> iSAABehaviorCategoryLookupDtoList = new ArrayList<ISAABehaviorCategoryLookupDto>();
		ISAABehaviorCategoryLookupDto iSAABehaviorCategoryLookupDto = new ISAABehaviorCategoryLookupDto();
		iSAABehaviorCategoryLookupDto.setId(iSAABehaviorCategoriesLookup.getId());
		iSAABehaviorCategoryLookupDto.setName(iSAABehaviorCategoriesLookup.getName());
		ArrayList<ISAABehaviorQuestionLookupDto> iSAABehaviorQuestionLookupDtoList = new ArrayList<ISAABehaviorQuestionLookupDto>();
		List<ISAABehaviorQuestionLookup> iSAABehaviorQuestionLookupList = iSAABehaviorCategoriesLookup
				.getiSAABehaviorQuestionLookups();
		for (ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup : iSAABehaviorQuestionLookupList) {
			ISAABehaviorQuestionLookupDto iSAABehaviorQuestionLookupDto = new ISAABehaviorQuestionLookupDto();
			iSAABehaviorQuestionLookupDto.setId(iSAABehaviorQuestionLookup.getId());
			iSAABehaviorQuestionLookupDto.setName(iSAABehaviorQuestionLookup.getName());
			List<ISAABehaviorAnswerLookup> iSAABehaviorAnswerLookupList = iSAABehaviorQuestionLookup
					.getiSAABehaviorAnswerLookups();
			ArrayList<ISAABehaviorAnswerLookupDto> iSAABehaviorAnswerLookupDtoList = new ArrayList<ISAABehaviorAnswerLookupDto>();
			for (ISAABehaviorAnswerLookup iSAABehaviorAnswerLookup : iSAABehaviorAnswerLookupList) {
				ISAABehaviorAnswerLookupDto iSAABehaviorAnswerLookupDto = new ISAABehaviorAnswerLookupDto();
				iSAABehaviorAnswerLookupDto.setId(iSAABehaviorAnswerLookup.getId());
				iSAABehaviorAnswerLookupDto.setName(iSAABehaviorAnswerLookup.getName());
				iSAABehaviorAnswerLookupDto.setSelectedAnswer(iSAABehaviorAnswerLookup.isSelectedAnswer());
				iSAABehaviorAnswerLookupDtoList.add(iSAABehaviorAnswerLookupDto);
			}
			iSAABehaviorQuestionLookupDto.setiSAABehaviorAnswerLookups(iSAABehaviorAnswerLookupDtoList);
			iSAABehaviorQuestionLookupDtoList.add(iSAABehaviorQuestionLookupDto);
		}
		iSAABehaviorCategoryLookupDto.setiSAABehaviorQuestionLookups(iSAABehaviorQuestionLookupDtoList);
		iSAABehaviorCategoryLookupDtoList.add(iSAABehaviorCategoryLookupDto);

		return iSAABehaviorCategoryLookupDtoList;
	}
}