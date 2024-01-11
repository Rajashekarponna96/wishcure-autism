package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.PatientSpeechSubTest;

@Repository
public interface PatientSpeechSubTestRepository extends PagingAndSortingRepository<PatientSpeechSubTest, Long> {

}
