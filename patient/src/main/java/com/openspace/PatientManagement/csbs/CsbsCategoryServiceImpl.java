package com.openspace.PatientManagement.csbs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Dto.CsbsObjectDto;
import com.openspace.Model.ParentModule.Repositories.CsbsAnswerRepository;
import com.openspace.Model.ParentModule.Repositories.CsbsCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.CsbsCategoryRepository;
import com.openspace.Model.ParentModule.Repositories.CsbsQuestionRepository;
import com.openspace.Model.ParentModule.csbs.CsbsAnswer;
import com.openspace.Model.ParentModule.csbs.CsbsAnswerLookup;
import com.openspace.Model.ParentModule.csbs.CsbsAnswerLookupDto;
import com.openspace.Model.ParentModule.csbs.CsbsCategory;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookupDto;
import com.openspace.Model.ParentModule.csbs.CsbsQuestion;
import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookup;
import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookupDto;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.PatientManagement.dto.CSBSCategoryDto;

@Service
public class CsbsCategoryServiceImpl implements CsbsCategoryService {

	@Autowired
	private CsbsCategoryLookupRepository csbsCategoryLookupRepository;

	@Autowired
	private CsbsCategoryRepository csbsCategoryRepository;

	@Autowired
	private CsbsQuestionRepository csbsQuestionRepository;

	@Autowired
	private CsbsAnswerRepository csbsAnswerRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void savecsbsCategoryQuestions(String username, CsbsObjectDto csbsObjectDto) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountRepository.findByUsername(username);
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		CsbsCategoryLookup csbsCategoryLookup = csbsCategoryLookupRepository.findOne(csbsObjectDto.getId());
		if (csbsCategoryLookup == null) {
			throw new RuntimeException("csbsCategoryLookup Does not Exist");
		}
		List<CsbsCategory> dbcsbsCategoryGoals = csbsCategoryRepository
				.findByCsbsCategoryLookup_IdAndUserAccount_Id(csbsObjectDto.getId(), userAccount.getId());
		if (dbcsbsCategoryGoals.size() == 0) {

			List<CsbsCategory> list2 = new ArrayList<CsbsCategory>();
			for (CsbsCategoryLookupDto questionCategoryDto : csbsObjectDto.getCsbsCategoryLookupDto()) {
				CsbsCategory parentQuestionCategory = new CsbsCategory();
				parentQuestionCategory.setName(questionCategoryDto.getName());
				parentQuestionCategory.setcsbsCategoryLookup(csbsCategoryLookup);
				parentQuestionCategory.setUserAccount(userAccount);
				csbsCategoryRepository.save(parentQuestionCategory);
				List<CsbsQuestion> list3 = new ArrayList<CsbsQuestion>();
				for (CsbsQuestionLookupDto questionDto : questionCategoryDto.getcsbsQuestionLookups()) {
					CsbsQuestion parentQuestion = new CsbsQuestion();
					parentQuestion.setName(questionDto.getName());
					parentQuestion.setcsbsCategory(parentQuestionCategory);
					csbsQuestionRepository.save(parentQuestion);
					List<CsbsAnswer> list4 = new ArrayList<CsbsAnswer>();
					for (CsbsAnswerLookupDto answerDto : questionDto.getcsbsAnswerLookups()) {
						CsbsAnswer parentAnswer = new CsbsAnswer();
						parentAnswer.setName(answerDto.getName());
						parentAnswer.setSelectedAnswer(answerDto.isSelectedAnswer());
						parentAnswer.setcsbsQuestion(parentQuestion);
						csbsAnswerRepository.save(parentAnswer);
						list4.add(parentAnswer);
					}
					list3.add(parentQuestion);
				}
				list2.add(parentQuestionCategory);
			}
		} else {
			if (csbsObjectDto.getCsbsCategory().size() > 0) {
				for (CsbsCategory parentQuestionCategory : csbsObjectDto.getCsbsCategory()) {
					for (CsbsQuestion parentQuestion : parentQuestionCategory.getcsbsQuestions()) {
						for (CsbsAnswer parentAnswer : parentQuestion.getcsbsAnswers()) {
							CsbsAnswer dbcsbsAnswer = csbsAnswerRepository.findOne(parentAnswer.getId());
							if (dbcsbsAnswer != null) {
								dbcsbsAnswer.setSelectedAnswer(parentAnswer.isSelectedAnswer());
								csbsAnswerRepository.save(dbcsbsAnswer);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<CsbsCategory> getAllcsbsCategoryQuestions(String userName, Long id) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountRepository.findByUsername(userName);
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		List<CsbsCategory> csbsCategories = (List<CsbsCategory>) csbsCategoryRepository
				.findByCsbsCategoryLookup_IdAndUserAccount_Id(id, userAccount.getId());
		ArrayList<CsbsCategory> parentQuestionCategoryList = new ArrayList<CsbsCategory>();
		for (CsbsCategory parentQuestionCategory : csbsCategories) {
			CsbsCategory parentQuestionCategory1 = new CsbsCategory();
			parentQuestionCategory1.setId(parentQuestionCategory.getId());
			parentQuestionCategory1.setName(parentQuestionCategory.getName());
			parentQuestionCategory1.setUserAccount(parentQuestionCategory.getUserAccount());

			ArrayList<CsbsQuestion> parentquestionList = new ArrayList<CsbsQuestion>();
			List<CsbsQuestion> parentQuestionList = parentQuestionCategory.getcsbsQuestions();
			for (CsbsQuestion parentQuestion : parentQuestionList) {
				CsbsQuestion parentQuestion1 = new CsbsQuestion();
				parentQuestion1.setId(parentQuestion.getId());
				parentQuestion1.setName(parentQuestion.getName());

				List<CsbsAnswer> parentAnswerList = parentQuestion.getcsbsAnswers();
				ArrayList<CsbsAnswer> parentAnswerList1 = new ArrayList<CsbsAnswer>();
				for (CsbsAnswer parentAnswer : parentAnswerList) {
					CsbsAnswer parentAnswer1 = new CsbsAnswer();
					parentAnswer1.setName(parentAnswer.getName());
					parentAnswer1.setSelectedAnswer(parentAnswer.isSelectedAnswer());
					parentAnswer1.setId(parentAnswer.getId());
					parentAnswerList1.add(parentAnswer1);
				}
				parentQuestion1.setcsbsAnswers(parentAnswerList1);
				parentquestionList.add(parentQuestion1);
			}
			parentQuestionCategory1.setcsbsQuestions(parentquestionList);
			parentQuestionCategoryList.add(parentQuestionCategory1);

		}
		return parentQuestionCategoryList;
	}

	@Override
	public void updatecsbsCategoryQuestions(CsbsCategory csbsCategory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletecsbsCategoryQuestions(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void savecsbsCategoryQuestionsToPatient(CSBSCategoryDto csbsCategoryDto) {

		// TODO Auto-generated method stub
		Patient patient = csbsCategoryDto.getPatient();
		List<CsbsCategoryLookup> csbsCategoryLookups = csbsCategoryDto.getCsbsCategoryLookups();
		List<CsbsCategory> csbsCategories = csbsCategoryDto.getCsbsCategories();

		Patient dbPatient = patientRepository.findOne(patient.getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}

		List<CsbsCategory> dbcsbsCategoryGoals = csbsCategoryRepository.findByPatient_Id(dbPatient.getId());
		if (dbcsbsCategoryGoals.size() == 0) {

			List<CsbsCategory> list2 = new ArrayList<CsbsCategory>();
			for (CsbsCategoryLookup csbsCategoryLookup : csbsCategoryLookups) {
				CsbsCategory parentQuestionCategory = new CsbsCategory();
				parentQuestionCategory.setName(csbsCategoryLookup.getName());
				// parentQuestionCategory.setcsbsCategoryLookup(csbsCategoryLookup);
				// parentQuestionCategory.setUserAccount(userAccount);
				parentQuestionCategory.setPatient(dbPatient);
				csbsCategoryRepository.save(parentQuestionCategory);
				List<CsbsQuestion> list3 = new ArrayList<CsbsQuestion>();
				for (CsbsQuestionLookup csbsQuestionLookup : csbsCategoryLookup.getcsbsQuestionLookups()) {
					CsbsQuestion parentQuestion = new CsbsQuestion();
					parentQuestion.setName(csbsQuestionLookup.getName());
					parentQuestion.setcsbsCategory(parentQuestionCategory);
					csbsQuestionRepository.save(parentQuestion);
					List<CsbsAnswer> list4 = new ArrayList<CsbsAnswer>();
					for (CsbsAnswerLookup csbsAnswerLookup : csbsQuestionLookup.getcsbsAnswerLookups()) {
						CsbsAnswer parentAnswer = new CsbsAnswer();
						parentAnswer.setName(csbsAnswerLookup.getName());
						parentAnswer.setSelectedAnswer(csbsAnswerLookup.isSelectedAnswer());
						parentAnswer.setcsbsQuestion(parentQuestion);
						csbsAnswerRepository.save(parentAnswer);
						list4.add(parentAnswer);
					}
					list3.add(parentQuestion);
				}
				list2.add(parentQuestionCategory);
			}
		} else {
			if (csbsCategories != null && csbsCategories.size() > 0) {
				for (CsbsCategory parentQuestionCategory : csbsCategories) {
					for (CsbsQuestion parentQuestion : parentQuestionCategory.getcsbsQuestions()) {
						for (CsbsAnswer parentAnswer : parentQuestion.getcsbsAnswers()) {
							CsbsAnswer dbcsbsAnswer = csbsAnswerRepository.findOne(parentAnswer.getId());
							if (dbcsbsAnswer != null) {
								dbcsbsAnswer.setSelectedAnswer(parentAnswer.isSelectedAnswer());
								csbsAnswerRepository.save(dbcsbsAnswer);
							}
						}
					}
				}
			}
		}

	}

	@Override
	public List<CsbsCategory> getAllcsbsCategoryQuestionsByPatient(Long patientId, Long categoryId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if(dbPatient == null){
			throw new RuntimeException("Patient does not found");
		}
		
		return null;
	}

	@Override
	public List<CsbsCategory> getAllcsbsCategoryQuestions(Long categoryId) {
		return csbsQuestionRepository.findByCsbsCategory_id(categoryId);
	}

}
