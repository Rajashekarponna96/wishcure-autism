package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.SpeechTheraphyCategoryRepository;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateCategory;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class SpeechTheraphyCategoryServiceImpl implements SpeechTheraphyCategoryService {

	@Autowired
	private SpeechTheraphyCategoryRepository speechTheraphyCategoryRepository;

	@Override
	public void addSpeechTempalteCategory(SpeechTemplateCategory speechTemplateCategory) {
		SpeechTemplateCategory dbSpeechTemplateCategory = speechTheraphyCategoryRepository
				.findBySpeechCategoryName(speechTemplateCategory.getSpeechCategoryName());
		if (dbSpeechTemplateCategory != null) {
			throw new RuntimeException(ErrorMessageHandler.speechTemplateQuestionAlreadyExists);
		}
		speechTheraphyCategoryRepository.save(speechTemplateCategory);
	}

	@Override
	public List<SpeechTemplateCategory> getAll() {
		// TODO Auto-generated method stub
		return (List<SpeechTemplateCategory>) speechTheraphyCategoryRepository.findAll();
	}

	@Override
	public void updateSpeechTemplateCategory(SpeechTemplateCategory speechTemplateCategory) {

		SpeechTemplateCategory speechTemplateCategory1 = speechTheraphyCategoryRepository.findOne(speechTemplateCategory.getId());
		if (speechTemplateCategory1 == null) {
			throw new RuntimeException(ErrorMessageHandler.speechTemplateQuestionDoesNotExists);
		}
		SpeechTemplateCategory speechTemplateCategory2 = speechTheraphyCategoryRepository.findBySpeechCategoryName(speechTemplateCategory.getSpeechCategoryName());
		if (speechTemplateCategory2 == null) {
			speechTemplateCategory1.setSpeechCategoryName(speechTemplateCategory.getSpeechCategoryName());
		} else if (speechTemplateCategory2.getId().equals(speechTemplateCategory.getId())) {
			speechTemplateCategory1.setSpeechCategoryName(speechTemplateCategory.getSpeechCategoryName());
		} else {
			throw new RuntimeException(ErrorMessageHandler.speechTemplateQuestionAlreadyExists);
		}
		speechTemplateCategory1.setSpeechCategoryName(speechTemplateCategory.getSpeechCategoryName());

		speechTheraphyCategoryRepository.save(speechTemplateCategory1);
	}

	@Override
	public void deleteSpeechTemplateCategory(Long id) {
		SpeechTemplateCategory speechTemplateCategory = speechTheraphyCategoryRepository.findOne(id);
		if (speechTemplateCategory != null) {
			speechTheraphyCategoryRepository.delete(speechTemplateCategory);
		}
	}

}
