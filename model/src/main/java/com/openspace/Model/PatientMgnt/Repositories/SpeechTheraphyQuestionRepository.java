package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateQuestion;

public interface SpeechTheraphyQuestionRepository extends PagingAndSortingRepository<SpeechTemplateQuestion, Long> {
	SpeechTemplateQuestion findByQuestionName(String name);
}
