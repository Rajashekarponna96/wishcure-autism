package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.DoctorManagement.PatientGoal;

public interface PatientGoalRepository extends PagingAndSortingRepository<PatientGoal,Long>{
	
	
	PatientGoal findByName(String name);
	
	List<PatientGoal> findByPatient_IdAndDate(Long id,LocalDate date);
	
	List<PatientGoal> findByPatient_Id(Long patientId);
	
   List<PatientGoal> findByPatient_IdAndAnswerAndDate(Long patientId,boolean status,LocalDate todayDate);
	
	List<PatientGoal> findByPatient_IdAndStatusAndDate(Long patientId,boolean status,LocalDate date);
	
	List<PatientGoal> findByPatient_IdAndGoalTemplateLookup_IdAndDate(Long patientId,Long lookupId,LocalDate date);
	
	PatientGoal findByPatient_IdAndId(Long patientId,Long patientGoalId);
	
	PatientGoal findByPatient_IdAndIdAndDate(Long patientId,Long patientGoalId,LocalDate todayDate);
	
	List<PatientGoal> findByPatient_IdAndDateAndId(Long patientId,LocalDate todayDate,Long patientGoalId);
}
