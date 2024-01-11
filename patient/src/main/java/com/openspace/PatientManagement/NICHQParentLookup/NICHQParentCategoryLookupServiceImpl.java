package com.openspace.PatientManagement.NICHQParentLookup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.NICHQParent.NICHQParentAnswerLookup;
import com.openspace.Model.NICHQParent.NICHQParentAnswerLookupDto;
import com.openspace.Model.NICHQParent.NICHQParentCategoryLookup;
import com.openspace.Model.NICHQParent.NICHQParentCategoryLookupDto;
import com.openspace.Model.NICHQParent.NICHQParentQuestionLookup;
import com.openspace.Model.NICHQParent.NICHQParentQuestionLookupDto;
import com.openspace.Model.NICHQParentRepository.NICHQParentCategoryLookupRepository;

@Service
public class NICHQParentCategoryLookupServiceImpl implements NICHQParentCategoryLookupService {

	@Autowired
	private NICHQParentCategoryLookupRepository nichqParentCategoryLookupRepository;

	@Override
	public void saveNICHQParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup) {
		NICHQParentCategoryLookup dbNichqParentCategoryLookup = nichqParentCategoryLookupRepository
				.findByName(nichqParentCategoryLookup.getName());
		if (dbNichqParentCategoryLookup != null) {
			throw new RuntimeException("NICHQ category already exist!");
		}
		nichqParentCategoryLookupRepository.save(nichqParentCategoryLookup);
	}

	@Override
	public void updateNICHQParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup) {
		NICHQParentCategoryLookup dbNichqParentCategoryLookup = nichqParentCategoryLookupRepository
				.findOne(nichqParentCategoryLookup.getId());
		if (dbNichqParentCategoryLookup == null) {
			throw new RuntimeException("NICHQ Category doesn't Exist!!");
		}
		dbNichqParentCategoryLookup.setName(nichqParentCategoryLookup.getName());
		nichqParentCategoryLookupRepository.save(dbNichqParentCategoryLookup);
	}

	@Override
	public List<NICHQParentCategoryLookup> getAllNICHQParentCategoryLookup() {
		return (List<NICHQParentCategoryLookup>) nichqParentCategoryLookupRepository.findAll();
	}

	@Override
	public void deleteNICHQParentCategoryLookup(Long id) {
		NICHQParentCategoryLookup dbNichqParentCategoryLookup = nichqParentCategoryLookupRepository.findOne(id);
		if (dbNichqParentCategoryLookup == null) {
			throw new RuntimeException("NICHQ doesn't Exist!");
		}
		nichqParentCategoryLookupRepository.delete(dbNichqParentCategoryLookup);
	}

	@Override
	public List<NICHQParentCategoryLookupDto> getAllNichqParentCategoryLookupByStatus(Long id) {
		NICHQParentCategoryLookup nichqParentCategoryLookup = nichqParentCategoryLookupRepository.findOne(id);

		ArrayList<NICHQParentCategoryLookupDto> nichqParentCategoryLookupDtoList = new ArrayList<>();
		NICHQParentCategoryLookupDto nichqParentCategoryLookupDto = new NICHQParentCategoryLookupDto();
		nichqParentCategoryLookupDto.setId(nichqParentCategoryLookup.getId());
		nichqParentCategoryLookupDto.setName(nichqParentCategoryLookup.getName());

		ArrayList<NICHQParentQuestionLookupDto> nichqParentQuestionLookupDtoList = new ArrayList<>();
		List<NICHQParentQuestionLookup> nichqParentQuestionLookupList = nichqParentCategoryLookup
				.getNichqParentQuestionLookup();
		for (NICHQParentQuestionLookup nichqParentQuestionLookup : nichqParentQuestionLookupList) {
			NICHQParentQuestionLookupDto nichqParentQuestionLookupDto = new NICHQParentQuestionLookupDto();
			nichqParentQuestionLookupDto.setId(nichqParentQuestionLookup.getId());
			nichqParentQuestionLookupDto.setName(nichqParentQuestionLookup.getName());
			List<NICHQParentAnswerLookup> nichqParentAnswerLookupList = nichqParentQuestionLookup
					.getNichqParentAnswerLookup();
			ArrayList<NICHQParentAnswerLookupDto> nichqParentAnswerLookupDtoList = new ArrayList<>();
			for (NICHQParentAnswerLookup nichqParentAnswerLookup : nichqParentAnswerLookupList) {
				NICHQParentAnswerLookupDto nichqParentAnswerLookupDto = new NICHQParentAnswerLookupDto();
				nichqParentAnswerLookupDto.setId(nichqParentAnswerLookup.getId());
				nichqParentAnswerLookupDto.setName(nichqParentAnswerLookup.getName());
				nichqParentAnswerLookupDto.setSelectedAnswer(nichqParentAnswerLookup.isSelectedAnswer());
				nichqParentAnswerLookupDtoList.add(nichqParentAnswerLookupDto);
			}
			nichqParentQuestionLookupDto.setNichqParentAnswerLookupDto(nichqParentAnswerLookupDtoList);
			nichqParentQuestionLookupDtoList.add(nichqParentQuestionLookupDto);
		}
		nichqParentCategoryLookupDto.setNichqParentQuestionLookupDto(nichqParentQuestionLookupDtoList);
		nichqParentCategoryLookupDtoList.add(nichqParentCategoryLookupDto);

		return nichqParentCategoryLookupDtoList;

	}

}
