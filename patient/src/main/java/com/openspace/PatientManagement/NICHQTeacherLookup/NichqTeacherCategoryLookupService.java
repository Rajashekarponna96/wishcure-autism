package com.openspace.PatientManagement.NICHQTeacherLookup;

import java.util.List;

import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;

public interface NichqTeacherCategoryLookupService {

	void addCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup);

	List<NichqTeacherCategoryLookup> getAllCategoryLookups();

	void deleteCategoryLookup(Long categoryId);

	void updateCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup);


}
