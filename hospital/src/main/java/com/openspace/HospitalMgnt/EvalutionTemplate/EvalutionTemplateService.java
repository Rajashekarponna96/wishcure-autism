package com.openspace.HospitalMgnt.EvalutionTemplate;

import java.util.List;

import com.openspace.Model.Template.EvalutionTemplate;

public interface EvalutionTemplateService {
	
	void saveEvalutionTemplate(EvalutionTemplate evalutionTemplate);

	List<EvalutionTemplate> getAllTemplates();

	void updateEvalutionTemplate(EvalutionTemplate evalutionTemplate);

	void deleteEvalutionTemplate(Long id);

}
