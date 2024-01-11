package com.openspace.HospitalMgnt.EvalutionTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.QuestionCategory.QuestionCategoryRepository;
import com.openspace.Model.DoctorManagement.QuestionCategory;
import com.openspace.Model.PatientMgnt.Repositories.EvalutionTemplateRepository;
import com.openspace.Model.Template.EvalutionTemplate;

@Service
public class EvalutionTemplateServiceImpl implements EvalutionTemplateService {

	@Autowired
	private EvalutionTemplateRepository evalutionTemplateRepository;

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;

	@Override
	public void saveEvalutionTemplate(EvalutionTemplate evalutionTemplate) {

		EvalutionTemplate dbEvalutionTemplate = evalutionTemplateRepository.findByFlag(true);
		if (dbEvalutionTemplate != null) {
			dbEvalutionTemplate.setBackgroundInformation(evalutionTemplate.getBackgroundInformation());
			dbEvalutionTemplate.setFlag(true);
			dbEvalutionTemplate.setHistory(evalutionTemplate.getHistory());
			dbEvalutionTemplate.setObservation(evalutionTemplate.getObservation());
			dbEvalutionTemplate.setOralMotor(evalutionTemplate.getOralMotor());
			dbEvalutionTemplate.setPragmaticSkills(evalutionTemplate.getPragmaticSkills());
			dbEvalutionTemplate.setRecommendations(evalutionTemplate.getRecommendations());
			dbEvalutionTemplate.setSummary(evalutionTemplate.getSummary());
			dbEvalutionTemplate.setTestAdministered(evalutionTemplate.getTestAdministered());
			dbEvalutionTemplate.setSignaturePath(evalutionTemplate.getSignaturePath());
			dbEvalutionTemplate.setReceptiveLanguage(evalutionTemplate.getReceptiveLanguage());
			dbEvalutionTemplate.setExpressiveLanguage(evalutionTemplate.getExpressiveLanguage());
			evalutionTemplateRepository.save(dbEvalutionTemplate);

		} else {

			EvalutionTemplate evalutionTemplate1 = new EvalutionTemplate();
			evalutionTemplate1.setBackgroundInformation(evalutionTemplate.getBackgroundInformation());
			evalutionTemplate1.setFlag(true);
			evalutionTemplate1.setHistory(evalutionTemplate.getHistory());
			evalutionTemplate1.setObservation(evalutionTemplate.getObservation());
			evalutionTemplate1.setOralMotor(evalutionTemplate.getOralMotor());
			evalutionTemplate1.setPragmaticSkills(evalutionTemplate.getPragmaticSkills());
			evalutionTemplate1.setRecommendations(evalutionTemplate.getRecommendations());
			evalutionTemplate1.setSummary(evalutionTemplate.getSummary());
			evalutionTemplate1.setTestAdministered(evalutionTemplate.getTestAdministered());
			evalutionTemplate1.setSignaturePath(evalutionTemplate.getSignaturePath());
			evalutionTemplate1.setReceptiveLanguage(evalutionTemplate.getReceptiveLanguage());
			evalutionTemplate1.setExpressiveLanguage(evalutionTemplate.getExpressiveLanguage());
			List<QuestionCategory> questionCategories = (List<QuestionCategory>) questionCategoryRepository.findAll();
			for (QuestionCategory questionCategory : questionCategories){
				questionCategory.setEvalutionTemplate(evalutionTemplate1);
			}
				evalutionTemplateRepository.save(evalutionTemplate1);
		}
		// TODO Auto-generated method stub
		/*
		 * List<QuestionCategory> dbQuestionCategories =
		 * questionCategoryRepository
		 * .findByEvalutionTemplate_Id(evalutionTemplate.getId());
		 * 
		 * List<QuestionCategory> questionCategories =
		 * evalutionTemplate.getQuestionCategorys(); List<QuestionCategory>
		 * questionCategoriesList=new ArrayList<QuestionCategory>(); for
		 * (QuestionCategory questionCategory : questionCategories) {
		 * questionCategory.setEvalutionTemplate(evalutionTemplate);
		 * questionCategoriesList.add(questionCategory); }
		 * questionCategoryRepository.save(questionCategoriesList);
		 * evalutionTemplate.setQuestionCategorys(questionCategoriesList);
		 */

	}

	@Override
	public List<EvalutionTemplate> getAllTemplates() {
		// TODO Auto-generated method stub
		return (List<EvalutionTemplate>) evalutionTemplateRepository.findAll();
	}

	@Override
	public void updateEvalutionTemplate(EvalutionTemplate evalutionTemplate) {

		evalutionTemplateRepository.save(evalutionTemplate);

	}

	@Override
	public void deleteEvalutionTemplate(Long id) {
		evalutionTemplateRepository.delete(id);
	}

}
