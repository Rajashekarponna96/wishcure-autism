package com.openspace.HospitalMgnt.QuestionCategory;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.QuestionCategory;

@Repository
public interface QuestionCategoryRepository extends PagingAndSortingRepository<QuestionCategory, Long>{

	QuestionCategory findByNameAndStatus(String name,Integer status);
	List<QuestionCategory> findById(Long id);
	List<QuestionCategory> findByStatus(Integer status);
	//List<QuestionCategory>  findByEvalutionTemplate_Id(Long id);
	
  
}
