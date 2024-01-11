package com.openspace.PatientManagement.PhysiotherapyEvaluation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluation;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategory;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryAnswers;
import com.openspace.Model.DepartmentofPhysiotherapy.PhysiotherapyEvaluationCategoryQuestions;
import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryAnswersLookup;
import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryLookup;
import com.openspace.Model.DepartofPhysioLookup.PhysiotherapyEvaluationCategoryQuestionsLookup;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PhysiotherapyEvaluationCategoryAnswersRepository;
import com.openspace.Model.PatientMgnt.Repositories.PhysiotherapyEvaluationCategoryQuestionsRepository;
import com.openspace.Model.PatientMgnt.Repositories.PhysiotherapyEvaluationCategoryRepository;
import com.openspace.Model.PatientMgnt.Repositories.PhysiotherapyEvaluationRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.PatientManagement.dto.PhysiotherapyEvaluationDTO;

@Service
public class PhysiotherapyEvaluationServiceImpl implements PhysiotherapyEvaluationService {

	@Autowired
	PhysiotherapyEvaluationRepository phyEvalRepository;

	@Autowired
	PhysiotherapyEvaluationCategoryRepository phyEvalCatRep;

	@Autowired
	PhysiotherapyEvaluationCategoryQuestionsRepository phyEvalCatQuestionRep;

	@Autowired
	PhysiotherapyEvaluationCategoryAnswersRepository phyEvalCatAnswerRep;

	@Autowired
	PatientRepository patientRep;

	@Override
	public void savePhysiotherapyEvalution(PhysiotherapyEvaluationDTO phEval) {
		// TODO Auto-generated method stub

		Patient dbpatient = patientRep.findOne(phEval.getPatient().getId());

		PhysiotherapyEvaluation phyEvaluation = new PhysiotherapyEvaluation();
		
		phyEvaluation.setBriefHistory(phEval.getBriefHistory());
		phyEvaluation.setChiefComplaints(phEval.getChiefComplaints());
		phyEvaluation.setExaminations(phEval.getExaminations());
		phyEvaluation.setPatient(dbpatient);
		
		phyEvalRepository.save(phyEvaluation);
		
		List<PhysiotherapyEvaluationCategoryLookup> phyEvalCatLookups = phEval.getPhysioEvalCatLookup();
		
		
				/*phEval.getPhysioEvalCat();*/
		
		if (dbpatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		} else {
			
			PhysiotherapyEvaluation phEvaluation = phyEvalRepository.findByPatient_Id(phEval.getPatient().getId());
			
			if(phEvaluation == null) {
				throw new RuntimeException(ErrorMessageHandler.patientDoesNotEvaluated);
		}
			else {
			List<PhysiotherapyEvaluationCategory> phyEvalCats =  phEvaluation.getpEvaluationCategories();
			
			if(phyEvalCats == null)
			{
			
			for (PhysiotherapyEvaluationCategoryLookup phyEvalCatLookup : phyEvalCatLookups) {
				
				PhysiotherapyEvaluationCategory physioEvalCat = new PhysiotherapyEvaluationCategory();

				physioEvalCat.setCategoryName(phyEvalCatLookup.getName());
				
				physioEvalCat.setPhysiotherapyEvaluation(phyEvaluation);
				
				phyEvalCatRep.save(physioEvalCat);

				List<PhysiotherapyEvaluationCategoryQuestionsLookup> phyEvalCatQuestLookups = phyEvalCatLookup
						.getPhysiotherapyEvalQuesLookup();
				

				for (PhysiotherapyEvaluationCategoryQuestionsLookup phyEvalCatQuestLookup : phyEvalCatQuestLookups) {
					
					PhysiotherapyEvaluationCategoryQuestions phyEvalCatQues = new PhysiotherapyEvaluationCategoryQuestions();

					phyEvalCatQues.setQuestName(phyEvalCatQuestLookup.getName());
					phyEvalCatQues.setPhysiotherapyEvaluationCategory(physioEvalCat);

					phyEvalCatQuestionRep.save(phyEvalCatQues);

					List<PhysiotherapyEvaluationCategoryAnswersLookup> phyEvalCatAnswertLookups = phyEvalCatQuestLookup
							.getPhysiotherapyEvalCatAnswerLookup();
					

					for (PhysiotherapyEvaluationCategoryAnswersLookup phyEvalCatAnswertLookup : phyEvalCatAnswertLookups) {
						PhysiotherapyEvaluationCategoryAnswers phyEvalCatAns = new PhysiotherapyEvaluationCategoryAnswers();

						phyEvalCatAns.setAnswerName(phyEvalCatAnswertLookup.getName());
						phyEvalCatAns.setPhysiotherapyEvaluationCategoryQuestions(phyEvalCatQues);

						phyEvalCatAnswerRep.save(phyEvalCatAns);

					}

				}

			}
		}

			else
			{
				for (PhysiotherapyEvaluationCategory dbphyEvalCategory : phyEvalCats) {
					
					for (PhysiotherapyEvaluationCategory phyEvalCategory : phEval.getPhysioEvalCat()) {
						
						if(dbphyEvalCategory.getId().equals(phyEvalCategory.getId()))
						{
							PhysiotherapyEvaluationCategory nphEvalCat = new PhysiotherapyEvaluationCategory();
							
							PhysiotherapyEvaluation phEvaldata = new PhysiotherapyEvaluation();
							
							phEvaldata.setBriefHistory(phEval.getBriefHistory());
							phEvaldata.setChiefComplaints(phEval.getChiefComplaints());
							phEvaldata.setExaminations(phEval.getExaminations());
							phEvaldata.setPatient(dbpatient);
							
							phyEvalRepository.save(phEvaldata);
							
							
							nphEvalCat.setCategoryName(phyEvalCategory.getCategoryName());
							nphEvalCat.setPhysiotherapyEvaluation(phEvaldata);
							
							phyEvalCatRep.save(nphEvalCat);
							
							
							for(PhysiotherapyEvaluationCategoryQuestions phyCatQuestion : dbphyEvalCategory.getpCategoryQuestions())
							{
								PhysiotherapyEvaluationCategoryQuestions phyCategoryQuestion = new PhysiotherapyEvaluationCategoryQuestions();
								
								phyCategoryQuestion.setQuestName(phyCatQuestion.getQuestName());
								
								phyCategoryQuestion.setPhysiotherapyEvaluationCategory(nphEvalCat);
								
								phyEvalCatQuestionRep.save(phyCategoryQuestion);
								
								for(PhysiotherapyEvaluationCategoryAnswers phyCatAns : phyCatQuestion.getpEvaluationCategoryAnswers())
								{
									PhysiotherapyEvaluationCategoryAnswers phyEvalCatAns = new PhysiotherapyEvaluationCategoryAnswers();
									
									phyEvalCatAns.setAnswerName(phyCatAns.getAnswerName());
									phyEvalCatAns.setPhysiotherapyEvaluationCategoryQuestions(phyCategoryQuestion);
									
									phyEvalCatAnswerRep.save(phyEvalCatAns);
									
								}
								
								
							}
							
							
							
							
						}
						
					}
					
					
					
				}
				
			}
		}

		}
		

	}

	@Override
	public List<PhysiotherapyEvaluation> getAllPhysiotherapyEvaluations() {
		// TODO Auto-generated method stub
		return (List<PhysiotherapyEvaluation>) phyEvalRepository.findAll();
	}

	@Override
	public void updatePhysiotherapyEvaluation(PhysiotherapyEvaluationDTO phEval) {
		// TODO Auto-generated method stub
		
		

		

	}

	@Override
	public void deletePhysiotherapyEvaluation(Long id) {
		// TODO Auto-generated method stub

		PhysiotherapyEvaluation phEvaluation = phyEvalRepository.findOne(id);

		if (phEvaluation == null) {
			throw new RuntimeException("PhysiothrapyEvaluation doesn't exist");
		}
		phyEvalRepository.delete(id);

	}

}
