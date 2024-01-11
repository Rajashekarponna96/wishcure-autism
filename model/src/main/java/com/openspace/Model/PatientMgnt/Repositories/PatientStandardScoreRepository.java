package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.PatientStandardScore;

@Repository
public interface PatientStandardScoreRepository extends PagingAndSortingRepository<PatientStandardScore, Long> {

}
