package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.SpeechTheraphyTemplate.SpeechTemplateCategory;
import com.openspace.PatientManagement.service.SpeechTheraphyCategoryService;

@RestController
@RequestMapping(value = "/speechTemplateCategory")
public class SpeechTheraphyCategoryController {

	@Autowired
	private SpeechTheraphyCategoryService SpeechTheraphyCategoryService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody SpeechTemplateCategory speechTemplateCategory) {
		SpeechTheraphyCategoryService.addSpeechTempalteCategory(speechTemplateCategory);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<SpeechTemplateCategory> findAll() {
		return SpeechTheraphyCategoryService.getAll();
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public void updateCategory(@RequestBody SpeechTemplateCategory speechTemplateCategory){
		SpeechTheraphyCategoryService.updateSpeechTemplateCategory(speechTemplateCategory);
	}
	
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public void deleteCategory(@PathVariable("id") Long id){
		SpeechTheraphyCategoryService.deleteSpeechTemplateCategory(id);
	}
	

}
