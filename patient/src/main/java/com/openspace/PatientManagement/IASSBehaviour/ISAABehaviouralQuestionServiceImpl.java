package com.openspace.PatientManagement.IASSBehaviour;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Dto.ISAABehavioralReportDto;
import com.openspace.Model.Dto.NichqParentResultDto;
import com.openspace.Model.NICHQParent.NichqParentCategory;
import com.openspace.Model.NICHQParent.NichqParentQuestion;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategory;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestion;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorCategoryRepository;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorQuestionRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;

@Service
public class ISAABehaviouralQuestionServiceImpl implements ISAABehaviouralQuestionService {

	@Autowired
	private ISAABehaviorQuestionRepository isaaBehaviorQuestionRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private ISAABehaviorCategoryLookupRepository isaaBehaviorCategoryLookupRepository;

	@Autowired
	private ISAABehaviorCategoryRepository isaaBehaviorCategoryRepository;

	@Override
	public List<ISAABehaviorQuestion> getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup(Long patientId,
			Long categoryId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		ISAABehaviorCategoryLookup isaaBehaviorCategoryLookup = isaaBehaviorCategoryLookupRepository
				.findOne(categoryId);
		if (isaaBehaviorCategoryLookup == null) {
			throw new RuntimeException("ISAABehaviorCategoryLookup Does not Exists!!");
		}

		List<ISAABehaviorQuestion> isaaBehaviorQuestionsList = isaaBehaviorQuestionRepository
				.findByISAABehaviorCategory_Patient_IdAndIsaaBehaviorCategoryLookup_Id(dbPatient.getId(),
						isaaBehaviorCategoryLookup.getId());
		return isaaBehaviorQuestionsList;
	}

	@Override
	public List<List<ISAABehavioralReportDto>> getIsaaBehaviorTemplateResultByPatient(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not exists!!");
		}
		List<List<ISAABehavioralReportDto>> allresults = new ArrayList<List<ISAABehavioralReportDto>>();
		List<ISAABehaviorCategory> categories = isaaBehaviorCategoryRepository.findByPatient_Id(dbPatient.getId());
		List<ISAABehaviorQuestion> allIsaaBehaviorQuestionsList = new ArrayList<ISAABehaviorQuestion>();
		List<ISAABehavioralReportDto> results1to9 = new ArrayList<ISAABehavioralReportDto>();
		List<ISAABehavioralReportDto> results10to14 = new ArrayList<ISAABehavioralReportDto>();
		List<ISAABehavioralReportDto> results15to23 = new ArrayList<ISAABehavioralReportDto>();
		List<ISAABehavioralReportDto> results24to30 = new ArrayList<ISAABehavioralReportDto>();
		List<ISAABehavioralReportDto> results31to36 = new ArrayList<ISAABehavioralReportDto>();
		List<ISAABehavioralReportDto> results37to40 = new ArrayList<ISAABehavioralReportDto>();
		for (ISAABehaviorCategory category : categories) {
			if (category != null) {
				List<ISAABehaviorQuestion> questions = isaaBehaviorQuestionRepository
						.findByISAABehaviorCategory_Patient_IdAndISAABehaviorCategory_Id(dbPatient.getId(),
								category.getId());
				if (questions != null && questions.size() > 0) {
					allIsaaBehaviorQuestionsList.addAll(questions);
				}
			}
		}
		if (allIsaaBehaviorQuestionsList != null && allIsaaBehaviorQuestionsList.size() > 0) {

			for (int i = 0; i <= 8; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				ISAABehavioralReportDto isaaBehavioralReportDto = new ISAABehavioralReportDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				isaaBehavioralReportDto.setQuestion("" + j);
				isaaBehavioralReportDto.setAnswer(indvidualScore);
				results1to9.add(isaaBehavioralReportDto);
			}
			
			for (int i = 9; i <= 13; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				ISAABehavioralReportDto isaaBehavioralReportDto = new ISAABehavioralReportDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				isaaBehavioralReportDto.setQuestion("" + j);
				isaaBehavioralReportDto.setAnswer(indvidualScore);
				results10to14.add(isaaBehavioralReportDto);
			}
			
			for (int i = 14; i <= 22; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				ISAABehavioralReportDto isaaBehavioralReportDto = new ISAABehavioralReportDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				isaaBehavioralReportDto.setQuestion("" + j);
				isaaBehavioralReportDto.setAnswer(indvidualScore);
				results15to23.add(isaaBehavioralReportDto);
			}
			
			for (int i = 23; i <= 29; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				ISAABehavioralReportDto isaaBehavioralReportDto = new ISAABehavioralReportDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				isaaBehavioralReportDto.setQuestion("" + j);
				isaaBehavioralReportDto.setAnswer(indvidualScore);
				results24to30.add(isaaBehavioralReportDto);
			}

			for (int i = 30; i <= 35; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				ISAABehavioralReportDto isaaBehavioralReportDto = new ISAABehavioralReportDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				isaaBehavioralReportDto.setQuestion("" + j);
				isaaBehavioralReportDto.setAnswer(indvidualScore);
				results31to36.add(isaaBehavioralReportDto);
			}
			
			for (int i = 36; i <= 39; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				ISAABehavioralReportDto isaaBehavioralReportDto = new ISAABehavioralReportDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				isaaBehavioralReportDto.setQuestion("" + j);
				isaaBehavioralReportDto.setAnswer(indvidualScore);
				results37to40.add(isaaBehavioralReportDto);
			}
		}
		allresults.add(results1to9);
		allresults.add(results10to14);
		allresults.add(results15to23);
		allresults.add(results24to30);
		allresults.add(results31to36);
		allresults.add(results37to40);
		
		return allresults;
	}

}
