package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateQuestion;

public interface SpeechTheraphyQuestionService {

	void addSpeechTemplateQuestion(SpeechTemplateQuestion speechTemplateQuestion);

	List<SpeechTemplateQuestion> getAll();

	void updateSpeechTemplateQuestion(SpeechTemplateQuestion speechTemplateQuestion);

	void deleteSpeechTemplateCategory(Long id);

}
