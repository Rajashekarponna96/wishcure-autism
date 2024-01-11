package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.EvalutionSheet;

@Repository
public interface EvalutionSheetRepository extends PagingAndSortingRepository<EvalutionSheet, Long> {
	EvalutionSheet findByPatient_IdAndFlagAndStatus(Long id, boolean flag, String status);
	EvalutionSheet findByPatient_IdAndFlagAndStatusAndProgressDate(Long id, boolean flag, String status,LocalDate progressDate);


	List<EvalutionSheet> findById(Long id);

	EvalutionSheet findByPatient_IdAndStatus(Long id, String status);
	
	List<EvalutionSheet> findByStatusAndPatient_Id(String status,Long id );
	
	EvalutionSheet findByPatient_IdAndStatusAndProgressDate(Long id, String status,LocalDate progressDate);
	//EvalutionSheet findByPatient_IdAndStatus(Long patientId, String string);
}
