package com.openspace.Model.NICHQTeacher.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NichqTeachers.PatientNichqTeacherCategory;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherQuestion;

@Repository
public interface PatientNichqTeacherQuestionRepository
		extends PagingAndSortingRepository<PatientNichqTeacherQuestion, Long> {
	
	List<PatientNichqTeacherQuestion> findByPatientNichqTeacherCategory_Id(Long categoryId);
	

	List<PatientNichqTeacherQuestion> findByPatientNichqTeacherCategory_Patient_IdAndNichqTeacherCategoryLookup_Id(Long patientId,Long categoryId);
	
	List<PatientNichqTeacherQuestion> findByPatientNichqTeacherCategory_Patient_IdAndPatientNichqTeacherCategory_Id(Long patientId,Long categoryId);


	
	List<PatientNichqTeacherQuestion> findByPatient_Id(Long patientId);
	
}
