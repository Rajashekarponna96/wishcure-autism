package com.openspace.Model.ScalesRepositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Scales.VSMSQuestion;

public interface VSMSQuestionRepository  extends PagingAndSortingRepository<VSMSQuestion, Long>{

	VSMSQuestion findByQuestionName(String questionName);
	
	List<VSMSQuestion> findByVsmsCluster_Id(Long id);



}
