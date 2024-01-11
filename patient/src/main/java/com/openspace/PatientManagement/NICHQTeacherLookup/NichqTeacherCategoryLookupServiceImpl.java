package com.openspace.PatientManagement.NICHQTeacherLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.NICHQTeacher.Repository.NichqTeacherCategorylookupRepository;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;

@Service
public class NichqTeacherCategoryLookupServiceImpl implements NichqTeacherCategoryLookupService {

	@Autowired
	private NichqTeacherCategorylookupRepository nichqTeacherCategorylookupRepository;

	@Override
	public void addCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		// TODO Auto-generated method stub

		NichqTeacherCategoryLookup dbNichqTeacherCategoryLookup = nichqTeacherCategorylookupRepository
				.findByName(nichqTeacherCategoryLookup.getName());
		if (dbNichqTeacherCategoryLookup != null) {
			throw new RuntimeException("NichqTeacherCategoryLookup Already Exists!!");
		}
		nichqTeacherCategorylookupRepository.save(nichqTeacherCategoryLookup);
	}

	@Override
	public List<NichqTeacherCategoryLookup> getAllCategoryLookups() {
		// TODO Auto-generated method stub
		return (List<NichqTeacherCategoryLookup>) nichqTeacherCategorylookupRepository.findAll();
	}

	@Override
	public void deleteCategoryLookup(Long categoryId) {
		// TODO Auto-generated method stub
		nichqTeacherCategorylookupRepository.delete(categoryId);
	}

	@Override
	public void updateCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		// TODO Auto-generated method stub
		NichqTeacherCategoryLookup dbNichqTeacherCategoryLookup = nichqTeacherCategorylookupRepository
				.findOne(nichqTeacherCategoryLookup.getId());
		if (dbNichqTeacherCategoryLookup == null) {
			throw new RuntimeException("NichqTeacherCategoryLookup Does not Exists!!");
		}

		dbNichqTeacherCategoryLookup.setName(nichqTeacherCategoryLookup.getName());
		nichqTeacherCategorylookupRepository.save(dbNichqTeacherCategoryLookup);
	}

}
