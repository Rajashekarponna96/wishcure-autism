package com.openspace.PatientManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.SpeechTheraphyAnswerHeaderRepository;
import com.openspace.Model.PatientMgnt.Repositories.SpeechTheraphyCategoryRepository;
import com.openspace.Model.PatientMgnt.Repositories.SpeechTheraphyQuestionRepository;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateAnswerHeader;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateCategory;
import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateQuestion;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class SpeechTheraphyQuestionServiceImpl implements SpeechTheraphyQuestionService {
	@Autowired
	private SpeechTheraphyQuestionRepository speechTheraphyQuestionRepository;

	@Autowired
	private SpeechTheraphyCategoryRepository speechTheraphyCategoryRepository;
	
	@Autowired
	private SpeechTheraphyAnswerHeaderRepository speechTheraphyAnswerHeaderRepository;

	@Override
	public void addSpeechTemplateQuestion(SpeechTemplateQuestion speechTemplateQuestion) {

		SpeechTemplateQuestion dbSpeechTemplateQuestion = speechTheraphyQuestionRepository
				.findByQuestionName(speechTemplateQuestion.getQuestionName());
		if (dbSpeechTemplateQuestion != null) {
			throw new RuntimeException(ErrorMessageHandler.speechTemplateQuestionAlreadyExists);
		}
		SpeechTemplateCategory dbspeechTemplateCategory = speechTheraphyCategoryRepository
				.findOne(speechTemplateQuestion.getSpeechTemplateCategory().getId());
		if (dbspeechTemplateCategory == null) {
			throw new RuntimeException(ErrorMessageHandler.speechTemplateQuestionDoesNotExists);
		}

		SpeechTemplateQuestion speechTemplateQuestion1=new SpeechTemplateQuestion();
		speechTemplateQuestion1.setQuestionName(speechTemplateQuestion.getQuestionName());
		speechTemplateQuestion1.setSpeechTemplateCategory(dbspeechTemplateCategory);
		speechTheraphyQuestionRepository.save(speechTemplateQuestion1);

		List<SpeechTemplateAnswerHeader> answersList = speechTemplateQuestion.getAnswerHeaders();
		List<SpeechTemplateAnswerHeader> answers = new ArrayList<SpeechTemplateAnswerHeader>();
		for (SpeechTemplateAnswerHeader speechTemplateAnswerHeader : answersList) {
			SpeechTemplateAnswerHeader answerHeader = new SpeechTemplateAnswerHeader();
			answerHeader.setAnswerName(speechTemplateAnswerHeader.getAnswerName());
			answerHeader.setSpeechTemplateQuestion(speechTemplateQuestion1);
			speechTheraphyAnswerHeaderRepository.save(answerHeader);
		}

		// TODO Auto-generated method stub

		speechTheraphyQuestionRepository.save(speechTemplateQuestion);
	}

	@Override
	public List<SpeechTemplateQuestion> getAll() {
		// TODO Auto-generated method stub
		return (List<SpeechTemplateQuestion>) speechTheraphyQuestionRepository.findAll();
	}

	@Override
	public void updateSpeechTemplateQuestion(SpeechTemplateQuestion speechTemplateQuestion) {
		// TODO Auto-generated method stub
		SpeechTemplateQuestion dbSpeechTemplateQuestion = speechTheraphyQuestionRepository
				.findByQuestionName(speechTemplateQuestion.getQuestionName());
		if (dbSpeechTemplateQuestion == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		if (dbSpeechTemplateQuestion == null) {
			speechTheraphyQuestionRepository.save(dbSpeechTemplateQuestion);
		} else if (dbSpeechTemplateQuestion.getId().equals(speechTemplateQuestion.getId())) {

			speechTheraphyQuestionRepository.save(dbSpeechTemplateQuestion);
		} else {
			throw new RuntimeException(ErrorMessageHandler.speechTemplateQuestionAlreadyExists);
		}

	}

	@Override
	public void deleteSpeechTemplateCategory(Long id) {
		// TODO Auto-generated method stub
		SpeechTemplateQuestion speechTemplateQuestion = speechTheraphyQuestionRepository.findOne(id);
		if (speechTemplateQuestion != null) {
			throw new RuntimeException(ErrorMessageHandler.speechTemplateQuestionAlreadyExists);
		}
		speechTheraphyQuestionRepository.delete(speechTemplateQuestion);
	}

}
