package com.openspace.PatientManagement.NICHQParent;

import java.util.List;
import java.util.Set;

import com.openspace.Model.Dto.NichqParentResultDto;
import com.openspace.Model.NICHQParent.NichqParentQuestion;

public interface NichqParentQuestionService {

	List<NichqParentQuestion> getNichqParentQuestionsByPatientAndCategoryId(Long patientId, Long categoryId);

	List<List<NichqParentResultDto>>  nichqParentResultByPatient(Long patientId);

	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId);

}
