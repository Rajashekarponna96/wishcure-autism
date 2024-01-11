package com.openspace.Model.ScalesRepositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.VSMS.VSMSAnswerCountAndScoringSheet;

public interface VSMSAnswerCountAndScoringSheetRepository  extends PagingAndSortingRepository<VSMSAnswerCountAndScoringSheet, Long>{

	
	VSMSAnswerCountAndScoringSheet  findByAnswerCount(int id);
}
