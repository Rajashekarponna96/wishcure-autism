package com.openspace.HospitalMgnt.Answer;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Answer;

@Repository
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long>{
	/*Answer findByShortAnswer(String name);
	List<Answer> findById(Long id);
	List<Answer> findByQuestionId(Long id);*/

}
