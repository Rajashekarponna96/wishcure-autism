package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateCategory;

public interface SpeechTheraphyCategoryService {

	void addSpeechTempalteCategory(SpeechTemplateCategory speechTemplateCategory);

	List<SpeechTemplateCategory> getAll();

	void updateSpeechTemplateCategory(SpeechTemplateCategory speechTemplateCategory);

	void deleteSpeechTemplateCategory(Long id);

}
