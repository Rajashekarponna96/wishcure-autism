package com.openspace.PatientManagement.screeningtestLookup;

import java.util.List;

import com.openspace.Model.Dto.ScreeningTestCategoryLookupDto;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategoryLookup;

public interface ScreeningTestCategoryLookupService {


	void saveScreeningTestLookup(ScreeningTestCategoryLookup screeningTestCategoryLookup);

	List<ScreeningTestCategoryLookup> getAllScreeningTestLookup();

	void updateScreeningTestLookup(ScreeningTestCategoryLookup screeningTestCategoryLookup);
	
	void deleteScreeningTestLookup(Long id);

	List<ScreeningTestCategoryLookupDto> getAllScreeningTestLookupByCategoryStatus(Long id);
}
