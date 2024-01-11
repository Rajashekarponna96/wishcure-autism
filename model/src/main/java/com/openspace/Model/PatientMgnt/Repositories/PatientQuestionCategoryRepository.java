package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.DoctorManagement.PatientQuestionCategory;

public interface PatientQuestionCategoryRepository extends PagingAndSortingRepository<PatientQuestionCategory, Long> {
	List<PatientQuestionCategory> findByPatient_Id(Long id);
	List<PatientQuestionCategory>	findByPatient_IdAndStatus(Long id,String status);

}
