package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.DoctorManagement.PatientQuestion;

public interface PatientQuestionRepository extends PagingAndSortingRepository<PatientQuestion, Long> {

}
