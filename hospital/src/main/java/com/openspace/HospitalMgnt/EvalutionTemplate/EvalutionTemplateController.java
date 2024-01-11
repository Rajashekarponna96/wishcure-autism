package com.openspace.HospitalMgnt.EvalutionTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Template.EvalutionTemplate;

@RestController
public class EvalutionTemplateController {

	@Autowired
	private EvalutionTemplateService evalutionTemplateService;
	
	@RequestMapping(value=RestURIConstants.SAVE_EVALUTION_TEMPLATE, method=RequestMethod.POST)
	public @ResponseBody void saveEvalutionTemplate(@RequestBody EvalutionTemplate evalutionTemplate ){
		evalutionTemplateService.saveEvalutionTemplate(evalutionTemplate);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_EVALUTION_TEMPLATE, method=RequestMethod.GET)
	public @ResponseBody List<EvalutionTemplate> getAllTemplates(){
		return evalutionTemplateService.getAllTemplates();
	}
	@RequestMapping(value=RestURIConstants.UPDATE_EVALUTION_TEMPLATE, method=RequestMethod.PUT)
	public @ResponseBody void updateEvalutionTemplate(@RequestBody EvalutionTemplate evalutionTemplate){
		evalutionTemplateService.updateEvalutionTemplate(evalutionTemplate);
	}
	@RequestMapping(value=RestURIConstants.DELETE_EVALUTION_TEMPLATE,method=RequestMethod.DELETE)
	public @ResponseBody void deleteEvalutionTemplate(@PathVariable Long id){
		evalutionTemplateService.deleteEvalutionTemplate(id);
	}
	
}
