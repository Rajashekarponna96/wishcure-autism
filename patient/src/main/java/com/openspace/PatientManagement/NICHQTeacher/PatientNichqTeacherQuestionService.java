package com.openspace.PatientManagement.NICHQTeacher;

import java.util.List;
import java.util.Set;

import com.openspace.Model.Dto.NichqTeacherResultDto;
import com.openspace.Model.NichqTeachers.NichqTeacherResult;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherCategory;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherQuestion;
import com.openspace.PatientManagement.dto.PatientNichqTeacherCategoryDto;

public interface PatientNichqTeacherQuestionService {

	void assignNichqTeacherCategoryToPatient(PatientNichqTeacherCategoryDto patientNichqTeacherCategoryDto);

	List<PatientNichqTeacherCategory> getAllNichqTeacherCategoriesByPatient(Long patientId);

	void updateNichqTeacherCategoryToPatient(PatientNichqTeacherCategory patientNichqTeacherCategory);

	NichqTeacherResult getNichqTeacherResultByPatient(Long patientId);

	void deleteNichqTeacherReportByPatient(Long patientId);

	List<PatientNichqTeacherQuestion> getAllPatientNichqTeacherQuestionsByPatientAndCategory(Long patientId,
			Long categoryId);

	List<List<NichqTeacherResultDto>> getNichqTeacherReportByPatient(Long patientId);

	Set<String> getEvalutionSheetDatesByPatientId(Long patientId);
	

}
