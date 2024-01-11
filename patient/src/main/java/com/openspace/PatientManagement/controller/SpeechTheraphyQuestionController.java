package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateQuestion;
import com.openspace.PatientManagement.service.SpeechTheraphyQuestionService;

@RestController
@RequestMapping(value = "/speechTemplateQuestion")
public class SpeechTheraphyQuestionController {
	@Autowired
	private SpeechTheraphyQuestionService speechTheraphyQuestionService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody SpeechTemplateQuestion speechTemplateQuestion) {
		speechTheraphyQuestionService.addSpeechTemplateQuestion(speechTemplateQuestion);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<SpeechTemplateQuestion> findAll() {
		return speechTheraphyQuestionService.getAll();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public void update(@RequestBody SpeechTemplateQuestion speechTemplateQuestion){
		speechTheraphyQuestionService.updateSpeechTemplateQuestion(speechTemplateQuestion);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		speechTheraphyQuestionService.deleteSpeechTemplateCategory(id);
		
	}
}
