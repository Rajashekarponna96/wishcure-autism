package com.openspace.PatientManagement.NICHQParentLookup;

import java.util.List;

import com.openspace.Model.NICHQParent.NICHQParentQuestionLookup;

public interface NICHQParentQuestionLookupService {

	void saveNICHQParentQuestionLookup(NICHQParentQuestionLookup nichqParentQuestionLookup);

	List<NICHQParentQuestionLookup> getAllnichqParentQuestionLookup();

	void updateNICHQParentQuestionLookup(NICHQParentQuestionLookup nichqParentQuestionLookup);

	void deleteNICHQParentQuestionLookup(Long id);

	List<NICHQParentQuestionLookup> getAllnichqParentQuestionLookupsByCategoryId(Long categoryId);

}
