package com.openspace.Model.NICHQTeacher.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NichqTeachers.PatientNichqTeacherCategory;

@Repository
public interface PatientNichqTeacherCategoryRepository
		extends PagingAndSortingRepository<PatientNichqTeacherCategory, Long> {
	
	List<PatientNichqTeacherCategory> findByPatient_Id(Long patientId);
	
	PatientNichqTeacherCategory	findByPatient_IdAndNichqTeacherCategoryLookup_Id(Long patientId,Long categoryId);
	
	

	
}
