package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.PatientStandardScoreHeader;

@Repository
public interface PatientStandardScoreHeaderRepository extends PagingAndSortingRepository<PatientStandardScoreHeader, Long>{

}
