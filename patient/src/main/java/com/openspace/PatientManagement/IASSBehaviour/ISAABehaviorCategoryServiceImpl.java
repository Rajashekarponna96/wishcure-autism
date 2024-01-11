package com.openspace.PatientManagement.IASSBehaviour;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Dto.ISAABehaviorObjectDto;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherCategory;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorAnswer;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategory;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestion;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorResult;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorCategoryRepository;
import com.openspace.Model.ParentModule.Repositories.ISAABehaviorQuestionRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class ISAABehaviorCategoryServiceImpl implements ISAABehaviorCategoryService {

	@Autowired
	private ISAABehaviorCategoryRepository iSAABehaviorCategoryRepository;

	@Autowired
	private ISAABehaviorQuestionRepository iSAABehaviorQuestionRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void saveISAABehaviorCategoryQuestions(ISAABehaviorObjectDto iSAABehaviorObjectDto) {

		Patient dbPatient = patientRepository.findOne(iSAABehaviorObjectDto.getPatient().getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		ISAABehaviorCategory dbIsaaBehaviorCategory = iSAABehaviorCategoryRepository
				.findByPatient_IdAndISAABehaviorCategoryLookup_Id(dbPatient.getId(),
						iSAABehaviorObjectDto.getIsaaBehaviorCategoryLookup().getId());

		if (dbIsaaBehaviorCategory == null) {
			ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup = iSAABehaviorObjectDto
					.getIsaaBehaviorCategoryLookup();
			if (iSAABehaviorCategoryLookup != null) {
				ISAABehaviorCategory isaaBehaviorCategory = new ISAABehaviorCategory();
				isaaBehaviorCategory.setName(iSAABehaviorCategoryLookup.getName());
				isaaBehaviorCategory.setPatient(dbPatient);
				isaaBehaviorCategory.setiSAABehaviorCategoryLookup(iSAABehaviorCategoryLookup);
				iSAABehaviorCategoryRepository.save(isaaBehaviorCategory);

				if (iSAABehaviorObjectDto.getIsaaBehaviorQuestionLookups() != null
						&& iSAABehaviorObjectDto.getIsaaBehaviorQuestionLookups().size() > 0) {
					for (ISAABehaviorQuestionLookup isaaBehaviorQuestionLookup : iSAABehaviorObjectDto
							.getIsaaBehaviorQuestionLookups()) {
						ISAABehaviorQuestion isaaBehaviorQuestion = new ISAABehaviorQuestion();
						isaaBehaviorQuestion.setName(isaaBehaviorQuestionLookup.getName());
						isaaBehaviorQuestion.setiSAABehaviorCategory(isaaBehaviorCategory);
						isaaBehaviorQuestion.setAnsValue(isaaBehaviorQuestionLookup.getAnswervalue());
						isaaBehaviorQuestion.setIsaaBehaviorCategoryLookup(iSAABehaviorCategoryLookup);
						iSAABehaviorQuestionRepository.save(isaaBehaviorQuestion);

					}
				}
			}
		} else if (dbIsaaBehaviorCategory != null) {
			if (iSAABehaviorObjectDto.getIsaaBehaviorCategoryLookup() != null) {
				if (dbIsaaBehaviorCategory.getiSAABehaviorQuestions() != null
						&& dbIsaaBehaviorCategory.getiSAABehaviorQuestions().size() > 0
						&& iSAABehaviorObjectDto.getIsaaBehaviorQuestions() != null
						&& iSAABehaviorObjectDto.getIsaaBehaviorQuestions().size() > 0) {
					for (ISAABehaviorQuestion isaaBehaviorQuestion : dbIsaaBehaviorCategory
							.getiSAABehaviorQuestions()) {
						for (ISAABehaviorQuestion isaaBehaviorQuestion1 : iSAABehaviorObjectDto
								.getIsaaBehaviorQuestions()) {
							if (isaaBehaviorQuestion.getId().equals(isaaBehaviorQuestion1.getId())) {
								isaaBehaviorQuestion.setAnsValue(isaaBehaviorQuestion1.getAnsValue());
								iSAABehaviorQuestionRepository.save(isaaBehaviorQuestion);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<ISAABehaviorCategory> getAllISAABehaviorCategoryQuestions(Long patientId, Long id) {
		// TODO Auto-generated method stub
		/*
		 * UserAccount userAccount =
		 * userAccountRepository.findByUsername(userName); if (userAccount ==
		 * null) { throw new RuntimeException("User Account Does not Exist"); }
		 */

		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not exists!!");
		}
		List<ISAABehaviorCategory> iSAABehaviorCategories = (List<ISAABehaviorCategory>) iSAABehaviorCategoryRepository
				.findByISAABehaviorCategoryLookup_IdAndPatient_Id(id, dbPatient.getId());
		ArrayList<ISAABehaviorCategory> parentQuestionCategoryList = new ArrayList<ISAABehaviorCategory>();
		for (ISAABehaviorCategory parentQuestionCategory : iSAABehaviorCategories) {
			ISAABehaviorCategory parentQuestionCategory1 = new ISAABehaviorCategory();
			parentQuestionCategory1.setId(parentQuestionCategory.getId());
			parentQuestionCategory1.setName(parentQuestionCategory.getName());
			parentQuestionCategory1.setPatient(parentQuestionCategory.getPatient());

			ArrayList<ISAABehaviorQuestion> parentquestionList = new ArrayList<ISAABehaviorQuestion>();
			List<ISAABehaviorQuestion> parentQuestionList = parentQuestionCategory.getiSAABehaviorQuestions();
			for (ISAABehaviorQuestion parentQuestion : parentQuestionList) {
				ISAABehaviorQuestion parentQuestion1 = new ISAABehaviorQuestion();
				parentQuestion1.setId(parentQuestion.getId());
				parentQuestion1.setName(parentQuestion.getName());

				List<ISAABehaviorAnswer> parentAnswerList = parentQuestion.getiSAABehaviorAnswers();
				ArrayList<ISAABehaviorAnswer> parentAnswerList1 = new ArrayList<ISAABehaviorAnswer>();
				for (ISAABehaviorAnswer parentAnswer : parentAnswerList) {
					ISAABehaviorAnswer parentAnswer1 = new ISAABehaviorAnswer();
					parentAnswer1.setName(parentAnswer.getName());
					parentAnswer1.setSelectedAnswer(parentAnswer.isSelectedAnswer());
					parentAnswer1.setId(parentAnswer.getId());
					parentAnswerList1.add(parentAnswer1);
				}
				parentQuestion1.setiSAABehaviorAnswers(parentAnswerList1);
				parentquestionList.add(parentQuestion1);
			}
			parentQuestionCategory1.setiSAABehaviorQuestions(parentquestionList);
			parentQuestionCategoryList.add(parentQuestionCategory1);
		}
		return parentQuestionCategoryList;
	}

	@Override
	public void updateISAABehaviorCategoryQuestions(ISAABehaviorCategory iSAABehaviorCategory) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteISAABehaviorCategoryQuestions(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<ISAABehaviorCategory> getAllISAABehaviorCategoryQuestions(Long patentId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patentId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		return iSAABehaviorCategoryRepository.findByPatient_Id(dbPatient.getId());
	}

	@Override
	public ISAABehaviorResult getISAABehaviorReportByPatientId(Long patentId) {
		// TODO Auto-generated method stub

		Patient dbPatient = patientRepository.findOne(patentId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does  not Exist!!");
		}
		List<ISAABehaviorCategory> isaaBehaviorCategoryList = iSAABehaviorCategoryRepository
				.findByPatient_Id(dbPatient.getId());
		List<ISAABehaviorQuestion> allISAABehaviorQuestionsList = new ArrayList<ISAABehaviorQuestion>();

		ISAABehaviorResult isaaBehaviorResult = new ISAABehaviorResult();
		isaaBehaviorResult.setPatient(dbPatient);
		if (isaaBehaviorCategoryList != null && isaaBehaviorCategoryList.size() > 0) {
			for (ISAABehaviorCategory isaaBehaviorCategory : isaaBehaviorCategoryList) {
				List<ISAABehaviorQuestion> isaaBehaviorQuestions = iSAABehaviorQuestionRepository
						.findByISAABehaviorCategory_Id(isaaBehaviorCategory.getId());
				allISAABehaviorQuestionsList.addAll(isaaBehaviorQuestions);
			}

		}
		int sum = 0;
		if (allISAABehaviorQuestionsList != null && allISAABehaviorQuestionsList.size() > 0) {
			for (ISAABehaviorQuestion isaaBehaviorQuestion : allISAABehaviorQuestionsList) {
				sum = sum + isaaBehaviorQuestion.getAnsValue();
			}
			isaaBehaviorResult.setAvgPerformanceScore(sum);
			if (sum < 70) {
				isaaBehaviorResult.setStatement("No Autism");
				isaaBehaviorResult.setAvgPerformanceScorel70(sum);
			} else if (sum >= 70 && sum <= 106) {
				isaaBehaviorResult.setStatement("Mild Autism");
				isaaBehaviorResult.setAvgPerformanceScore70to106(sum);
			} else if (sum >= 107 && sum <= 153) {
				isaaBehaviorResult.setStatement("Moderate Autism");
				isaaBehaviorResult.setAvgPerformanceScore107to153(sum);
			} else {
				isaaBehaviorResult.setStatement("Severe Autism");
				isaaBehaviorResult.setAvgPerformanceScoreg153(sum);
			}
		}
		return isaaBehaviorResult;
	}

	@Override
	public void deleteISAABehaviorReportByPatientId(Long patentId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patentId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		List<ISAABehaviorCategory> isaaBehaviorCategorysList = iSAABehaviorCategoryRepository
				.findByPatient_Id(patentId);
		iSAABehaviorCategoryRepository.delete(isaaBehaviorCategorysList);
	}
	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<ISAABehaviorCategory> isaaBehaviorCategorysList = iSAABehaviorCategoryRepository.findByPatient_Id(patientId);
		for (ISAABehaviorCategory isaaBehaviorCategory : isaaBehaviorCategorysList) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (isaaBehaviorCategory.getDate() != null) {
				dates.add(isaaBehaviorCategory.getDate().format(formatter));
			}
		}
		return dates;
	}
	
	

}
