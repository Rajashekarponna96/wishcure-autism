package com.openspace.Model.NICHQTeacher.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NichqTeachers.PatientNichqTeacherAnswer;

@Repository
public interface PatientNichqTeacherAnswerRepository
		extends PagingAndSortingRepository<PatientNichqTeacherAnswer, Long> {

	
}
