package com.openspace.PatientManagement.NICHQParent;

import java.util.List;

import com.openspace.Model.NICHQParent.NichqParentCategory;
import com.openspace.Model.NICHQParent.NichqParentCategoryDto;
import com.openspace.Model.NICHQParent.NichqParentDto;
import com.openspace.Model.NICHQParent.NichqParentResult;

public interface NichqParentCategoryService {

	void saveNichqCategoryQuestions(NichqParentCategoryDto nichqParentCategoryDto);

	List<NichqParentCategory> getAllNichqCategorysByPatientId(Long patientId);

	NichqParentResult getNichqParentResultByPatientId(Long patientId);

	void deleteNichqParentResultByPatientId(Long patientId);

}
