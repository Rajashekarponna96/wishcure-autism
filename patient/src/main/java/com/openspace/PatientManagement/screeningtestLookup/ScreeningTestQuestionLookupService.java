package com.openspace.PatientManagement.screeningtestLookup;

import java.util.List;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestQuestionLookup;

public interface ScreeningTestQuestionLookupService {

	void saveScreeningTestQuestionLookup(ScreeningTestQuestionLookup screeningTestQuestionLookup);

	List<ScreeningTestQuestionLookup> getAllScreeningTestQuestionLookup();

	void updateScreeningTestLookup(ScreeningTestQuestionLookup screeningTestQuestionLookup);

	void deleteScreeningTestQuestionLookup(Long id);

}
