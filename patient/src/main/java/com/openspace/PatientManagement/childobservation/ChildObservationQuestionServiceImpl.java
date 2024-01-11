package com.openspace.PatientManagement.childobservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.ParentModule.Repositories.ChildObservationAnswerRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationCategoryRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationQuestionLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationQuestionRepository;
import com.openspace.Model.ParentModule.childobservation.ChildObservationAnswer;
import com.openspace.Model.ParentModule.childobservation.ChildObservationAnswerLookup;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategoryLookup;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestion;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestionLookup;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;

@Service
public class ChildObservationQuestionServiceImpl implements ChildObservationQuestionService {

	@Autowired
	private ChildObservationQuestionRepository childObservationQuestionRepository;

	@Autowired
	private ChildObservationCategoryRepository categoryRepository;

	@Autowired
	private ChildObservationAnswerRepository answerRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private ChildObservationQuestionLookupRepository childObservationQuestionLookupRepository;

	@Autowired
	private ChildObservationCategoryLookupRepository childObservationCategoryLookupRepository;

	@Override
	public void saveChildObservationQuestion(List<ChildObservationQuestionLookup> questionLookupsList,
			String userName) {
		// TODO Auto-generated method stub
		// ChildObservationQuestion dbChildObservationQuestion=
		Person person = personRepository.findByEmail(userName);
		if (person == null) {
			throw new RuntimeException("User Does not Exist");
		}
		System.out.println(userName);

		ChildObservationCategory category = new ChildObservationCategory();

		if (questionLookupsList != null) {
			for (ChildObservationQuestionLookup questionLookup : questionLookupsList) {
				category.setName(questionLookup.getChildObservationCategoryLookup().getName());
				ChildObservationCategory dbCategory = categoryRepository
						.findByName(questionLookup.getChildObservationCategoryLookup().getName());
				if (dbCategory == null) {
					categoryRepository.save(category);
				}
				ChildObservationQuestion question = new ChildObservationQuestion();
				question.setName(questionLookup.getName());
				question.setAnswer(questionLookup.getAnswer());
				// question.setChildObservationCategory(category);
				question.setChildObservationCategery(questionLookup.getChildObservationCategoryLookup());
				if (dbCategory == null) {
					question.setChildObservationCategory(category);
				} else {
					question.setChildObservationCategory(dbCategory);
				}
				question.setPerson(person);
				ChildObservationQuestion dbQuestion = childObservationQuestionRepository
						.findByPerson_IdAndName(person.getId(), question.getName());
				if (dbQuestion == null) {
					childObservationQuestionRepository.save(question);
				} else {
					// dbQuestion.setAnswer(question.getAnswer());
					childObservationQuestionRepository.save(dbQuestion);
				}
				List<ChildObservationAnswerLookup> answerLookups = questionLookup.getChildObservationAnswerLookups();
				for (ChildObservationAnswerLookup answerLookup : answerLookups) {
					ChildObservationAnswer answer = new ChildObservationAnswer();
					answer.setSelectedAnswer(answerLookup.isSelectedAnswer());
					if (dbQuestion == null) {
						answer.setChildObservationQuestion(question);
					} else {
						answer.setChildObservationQuestion(dbQuestion);
					}
					List<ChildObservationAnswer> dbAnswersList;
					if (question.getId() == null) {
						dbAnswersList = answerRepository.findByChildObservationQuestion_Id(dbQuestion.getId());
					} else {
						dbAnswersList = answerRepository.findByChildObservationQuestion_Id(question.getId());
					}
					if (dbAnswersList.size() > 0) {
						for (ChildObservationAnswer dbAnswer : dbAnswersList) {

							dbAnswer.setSelectedAnswer(answer.isSelectedAnswer());
							System.out.println("normal::" + answer.isSelectedAnswer());
							System.out.println("DB::" + dbAnswer.isSelectedAnswer());
							answerRepository.save(dbAnswer);
						}
					} else {
						answerRepository.save(answer);
					}
				}

			}
		}
	}

	@Override
	public void updateChildObservationQuestion(List<ChildObservationQuestion> childObservationQuestionsList,
			String userName) {

		System.out.println(userName);
		for (ChildObservationQuestion question : childObservationQuestionsList) {
			childObservationQuestionRepository.save(question);
			// ChildObservationAnswer answer=new ChildObservationAnswer();
			List<ChildObservationAnswer> answersList = question.getChildObservationAnswers();
			for (ChildObservationAnswer answer : answersList) {
				System.out.println("Answer Id:" + answer.getId());
				answer.setChildObservationQuestion(question);
				System.out.println("question Id:" + answer.getChildObservationQuestion().getId());
				// answer.setSelectedAnswer(question.getChildObservationAnswers());
				// answer.setChildObservationQuestion(question);
				answerRepository.save(answer);
			}
		}

	}

	@Override
	public void deleteChildObservationQuestion(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ChildObservationQuestion> getAllChildObservationQuestions() {
		// TODO Auto-generated method stub
		return (List<ChildObservationQuestion>) childObservationQuestionRepository.findAll();
	}

	@Override
	public List<ChildObservationQuestion> getAllChildObservationQuestionsByStatus(Long categorystatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildObservationQuestion> getChildObservationQuestionsByPatientIdAndCategoryId(Long pid, Long cid) {

		List<ChildObservationQuestion> childObservationQuestions = childObservationQuestionRepository
				.findByPerson_IdAndChildObservationCategory_Id(pid, cid);
		return childObservationQuestions;
	}

	@Override
	public List<ChildObservationQuestion> getChildObservationQuestionsByPatientAndCategoryId(String userName,
			Long cid) {
		Person person = personRepository.findByEmail(userName);
		if (person == null) {
			throw new RuntimeException("User Does not Exist");
		}
		System.out.println(userName);
		List<ChildObservationQuestion> childObservationQuestions = childObservationQuestionRepository
				.findByPerson_IdAndChildObservationCategery_Id(person.getId(), cid);
		return childObservationQuestions;
	}

	public List<ChildObservarionGraphDashboardDto> getAllAnswersCountForDashboard(String username) {
		List<ChildObservarionGraphDashboardDto> dashboardDtosList = new ArrayList<ChildObservarionGraphDashboardDto>();
		Person person = personRepository.findByEmail(username);
		if (person == null) {
			throw new RuntimeException("User Does not Exist");
		}
		List<Long> categoryname = new ArrayList<Long>();
		List<ChildObservationCategoryLookup> idsList = (List<ChildObservationCategoryLookup>) childObservationCategoryLookupRepository
				.findAll();
		List<Long> list = new ArrayList<Long>();
		for (ChildObservationCategoryLookup id : idsList) {
			Long a = id.getId();
			list.add(a);
		}
		List<Long> ids = list;
		System.out.println(list);
		for (int i = 0; i < ids.size(); i++) {
			categoryname.addAll(ids);
		}
		/*
		 * for(int j=1,k=0;j<=6;j++){ Integer
		 * complete=getYesGoals(username,categoryname); Integer
		 * inComplete=getNoGoals(username,categoryname);
		 * ChildObservarionGraphDashboardDto dashboardDto=new
		 * ChildObservarionGraphDashboardDto(); if(k<=5){
		 * dashboardDto.setId(categoryname); dashboardDto.setYes(complete);
		 * dashboardDto.setNo(inComplete); dashboardDtosList.add(dashboardDto);
		 * } }
		 */
		return dashboardDtosList;
	}

	@Override
	public List<ChildObservarionGraphDashboardDto> getAnswersCountForDashboard(String username) {
		Person person = personRepository.findByEmail(username);
		if (person == null) {
			throw new RuntimeException("User Does not Exist");
		}
		
		List<ChildObservarionGraphDashboardDto> dashboardDtosList = new ArrayList<>();
		List<ChildObservationCategoryLookup> idsList = (List<ChildObservationCategoryLookup>) childObservationCategoryLookupRepository
				.findAll();

		List<Long> list = new ArrayList<Long>();
		for (ChildObservationCategoryLookup id : idsList) {
			Long a = id.getId();
			list.add(a);
		}
		System.out.println(list);
		for (Long id : list) {
			
			ChildObservarionGraphDashboardDto dashboardDto=new ChildObservarionGraphDashboardDto();
			String name = null;
			if (id != null) {
				List<ChildObservationQuestion> dbChildObservationQuestions = childObservationQuestionRepository
						.findByPerson_IdAndChildObservationCategery_Id(person.getId(), id);
				if(dbChildObservationQuestions.size()>0){
					int yesCount=0,noCount=0;
				for(ChildObservationQuestion question:dbChildObservationQuestions){
					//if(id==question.getChildObservationCategery().getId()){
				name=question.getChildObservationCategery().getName();
				List<ChildObservationAnswer> dbAnswers=question.getChildObservationAnswers();
				for(ChildObservationAnswer answer:dbAnswers ){
					if(answer.isSelectedAnswer()==true ){
						yesCount++;
						System.out.println(yesCount);
					}else{
						noCount++;
						System.out.println(noCount);
					}
				}
				
				if(name!=null){
				dashboardDto.setState(name);
				dashboardDto.setYes(yesCount);
				dashboardDto.setNo(noCount);
				}
				}
				dashboardDtosList.add(dashboardDto);
				}
			}
				
			
				
		}

		return dashboardDtosList;

	}
}
