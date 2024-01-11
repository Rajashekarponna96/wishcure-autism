package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Template.EvalutionQuestion;

@Repository
public interface EvalutionQuestionRepository extends PagingAndSortingRepository<EvalutionQuestion, Long>{

	EvalutionQuestion findByQuestionName(String name);
	List<EvalutionQuestion> findById(Long id);
	List<EvalutionQuestion> findByEvalutionCategory_Id(Long id);
}
