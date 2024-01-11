package com.openspace.PatientManagement.csbsLookups;

import java.util.List;

import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookup;

public interface CsbsQuestionLookupService {

	void savecsbsQuestionLookup(CsbsQuestionLookup csbsQuestionLookup);

	List<CsbsQuestionLookup> getAllcsbsQuestionLookup();

	void updatecsbsLookup(CsbsQuestionLookup csbsQuestionLookup);

	void deletecsbsQuestionLookup(Long id);

	List<CsbsQuestionLookup> getAllcsbsQuestionLookupByCategoryId(Long catId);

}
