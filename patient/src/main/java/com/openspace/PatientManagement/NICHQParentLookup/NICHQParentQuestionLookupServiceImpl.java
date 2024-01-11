package com.openspace.PatientManagement.NICHQParentLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.NICHQParent.NICHQParentAnswerLookup;
import com.openspace.Model.NICHQParent.NICHQParentCategoryLookup;
import com.openspace.Model.NICHQParent.NICHQParentQuestionLookup;
import com.openspace.Model.NICHQParentRepository.NICHQParentAnswerLookupRepository;
import com.openspace.Model.NICHQParentRepository.NICHQParentCategoryLookupRepository;
import com.openspace.Model.NICHQParentRepository.NICHQParentQuestionLookupRepository;

@Service
public class NICHQParentQuestionLookupServiceImpl implements NICHQParentQuestionLookupService {

	@Autowired
	private NICHQParentCategoryLookupRepository nichqParentCategoryLookupRepository;

	@Autowired
	private NICHQParentAnswerLookupRepository nichqParentAnswerLookupRepository;

	@Autowired
	private NICHQParentQuestionLookupRepository nichqParentQuestionLookupRepository;

	@Override
	public void saveNICHQParentQuestionLookup(NICHQParentQuestionLookup nichqParentQuestionLookup) {
		NICHQParentQuestionLookup dbNICHQParentQuestionLookup = nichqParentQuestionLookupRepository
				.findByName(nichqParentQuestionLookup.getName());
		if (dbNICHQParentQuestionLookup != null) {
			throw new RuntimeException("NICHQ Question Already Exist!");
		}
		NICHQParentCategoryLookup dbNICHQParentCategory = nichqParentCategoryLookupRepository
				.findOne(nichqParentQuestionLookup.getNichqParentCategoryLookup().getId());

		if (dbNICHQParentCategory == null) {
			throw new RuntimeException("Category doesn't exist!");
		}
		NICHQParentQuestionLookup nichqParentQuestionLookup1 = new NICHQParentQuestionLookup();
		nichqParentQuestionLookup1.setNichqParentCategoryLookup(dbNICHQParentCategory);
		nichqParentQuestionLookup1.setName(nichqParentQuestionLookup.getName());
		nichqParentQuestionLookupRepository.save(nichqParentQuestionLookup1);

		List<NICHQParentAnswerLookup> nichqParentAnswerLookupList = nichqParentQuestionLookup
				.getNichqParentAnswerLookup();
		for (NICHQParentAnswerLookup nichqParentAnswerLookup : nichqParentAnswerLookupList) {
			NICHQParentAnswerLookup nichqParentAnswerLookup1 = new NICHQParentAnswerLookup();
			nichqParentAnswerLookup1.setName(nichqParentAnswerLookup.getName());
			nichqParentAnswerLookup1.setNichqParentQuestionLookup(nichqParentQuestionLookup1);
			nichqParentAnswerLookup1.setActive(true);
			nichqParentAnswerLookup1.setSelectedAnswer(nichqParentAnswerLookup.isSelectedAnswer());
			nichqParentAnswerLookupRepository.save(nichqParentAnswerLookup1);
		}

	}

	@Override
	public List<NICHQParentQuestionLookup> getAllnichqParentQuestionLookup() {
		return (List<NICHQParentQuestionLookup>) nichqParentQuestionLookupRepository.findAll();
	}

	@Override
	public void updateNICHQParentQuestionLookup(NICHQParentQuestionLookup nichqParentQuestionLookup) {
		NICHQParentQuestionLookup dbNICHQParentQuestionLookup = nichqParentQuestionLookupRepository
				.findOne(nichqParentQuestionLookup.getId());
		if (dbNICHQParentQuestionLookup == null) {
			throw new RuntimeException("NICHQ Question Lookup Doesn't Exist!");
		}
		NICHQParentQuestionLookup nichqParentQuestionLookup1 = nichqParentQuestionLookupRepository
				.findByName(nichqParentQuestionLookup.getName());
		if (nichqParentQuestionLookup1 == null) {

		} else if (nichqParentQuestionLookup1.getId().equals(nichqParentQuestionLookup.getId())) {

		} else {
			throw new RuntimeException(" NICHQ Question Already Exist!");
		}
		dbNICHQParentQuestionLookup.setName(nichqParentQuestionLookup.getName());
		dbNICHQParentQuestionLookup
				.setNichqParentCategoryLookup(nichqParentQuestionLookup.getNichqParentCategoryLookup());
		nichqParentQuestionLookupRepository.save(nichqParentQuestionLookup);

		List<NICHQParentAnswerLookup> nichqParentAnswerLookupList = nichqParentQuestionLookup
				.getNichqParentAnswerLookup();
		for (NICHQParentAnswerLookup nichqParentAnswerLookup : nichqParentAnswerLookupList) {
			for (NICHQParentAnswerLookup answer : dbNICHQParentQuestionLookup.getNichqParentAnswerLookup()) {
				if (answer.getId().equals(nichqParentAnswerLookup.getId())) {
					answer.setActive(true);
					answer.setNichqParentQuestionLookup(dbNICHQParentQuestionLookup);
					answer.setSelectedAnswer(nichqParentAnswerLookup.isSelectedAnswer());

					nichqParentAnswerLookupRepository.save(answer);
				}

			}
		}
	}

	@Override
	public void deleteNICHQParentQuestionLookup(Long id) {
		nichqParentQuestionLookupRepository.delete(id);
	}

	@Override
	public List<NICHQParentQuestionLookup> getAllnichqParentQuestionLookupsByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		NICHQParentCategoryLookup dbNichqParentCategoryLookup = nichqParentCategoryLookupRepository.findOne(categoryId);
		if (dbNichqParentCategoryLookup == null) {
			throw new RuntimeException("NICHQParentCategoryLookup does not exists");
		}
		return nichqParentQuestionLookupRepository
				.findByNichqParentCategoryLookup_Id(dbNichqParentCategoryLookup.getId());
	}

}
