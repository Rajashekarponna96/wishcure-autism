package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Template.EvalutionTemplate;

@Repository
public interface EvalutionTemplateRepository extends PagingAndSortingRepository<EvalutionTemplate, Long> {

	List<EvalutionTemplate> findById(Long id);

	EvalutionTemplate findByFlag(boolean flag);
}
