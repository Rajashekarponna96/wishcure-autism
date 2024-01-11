package com.openspace.Model.ScalesRepositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.VSMS.PatientVSMSQuestion;

public interface PatientVSMSQuestionRepository  extends PagingAndSortingRepository<PatientVSMSQuestion, Long>{

	
	List<PatientVSMSQuestion>  findByPatient_Id(Long patientId);
}
