package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluation;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategory;


@Repository
public interface PhysiotherapyEvaluationRepository extends PagingAndSortingRepository<PhysiotherapyEvaluation, Long>{
	
	PhysiotherapyEvaluation findById(Long id);
	
	List<PhysiotherapyEvaluation> findAll();
	
	PhysiotherapyEvaluation findByPatient_Id(Long patientId);
	
	/* PhysiotherapyEvaluation findByName(String name); */

}
