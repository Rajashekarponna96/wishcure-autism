package com.openspace.HospitalMgnt.Question;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Question;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long>{

	Question findByquestionName(String name);
	List<Question> findById(Long id);
}
