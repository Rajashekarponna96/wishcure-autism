package com.openspace.PatientManagement.ParentModule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.Dto.CareTakerGraphDashboardDto;
import com.openspace.Model.ParentModule.Repositories.CareTakerAnswerRepository;
import com.openspace.Model.ParentModule.Repositories.CareTakerCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.CareTakerCategoryRepository;
import com.openspace.Model.ParentModule.Repositories.CareTakerQuestionLookupRepository;
import com.openspace.Model.ParentModule.Repositories.CareTakerQuestionRepository;
import com.openspace.Model.ParentModule.caretaker.CareTakerAnswer;
import com.openspace.Model.ParentModule.caretaker.CareTakerAnswerLookup;
import com.openspace.Model.ParentModule.caretaker.CareTakerCategory;
import com.openspace.Model.ParentModule.caretaker.CareTakerCategoryLookup;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestion;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestionLookup;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;

@Service
public class CareTakerCategoryServiceImpl implements CareTakerCategoryService {
	@Autowired
	private CareTakerQuestionLookupRepository careTakerQuestionLookupRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserAccountRepository userRepository;

	@Autowired
	private CareTakerCategoryRepository careTakerCategoryRepository;

	@Autowired
	private CareTakerQuestionRepository careTakerQuestionRepository;

	@Autowired
	private CareTakerAnswerRepository careTakerAnswerRepository;

	@Autowired
	private CareTakerCategoryLookupRepository careTakerCategoryLookupRepository;

	@Override
	public List<CareTakerQuestionLookup> getQuestionsBasedOnCategoryStatus(Long id) {
		// TODO Auto-generated method stub
		/*CareTakerCategoryLookup dbcareTakerCategoryLookup = careTakerCategoryLookupRepository
				.findOne(id);*/
		/*if (dbcareTakerCategoryLookup == null) {
			throw new RuntimeException("Category Doesn't Exist");
		}*/
		List<CareTakerQuestionLookup> careTakerQuestionLookupList = careTakerQuestionLookupRepository
				.findByCareTakerCategoryLookup_Id(id);
		if (careTakerQuestionLookupList == null) {
			throw new RuntimeException("CareTaker Questions Doesn't Exist Under This Category");
		}
		return careTakerQuestionLookupList;
	}

	@Override
	public void saveCaretakerMilestones(String adminUserName,
			List<CareTakerQuestionLookup> careTakerQuestionLookupList) {
		UserAccount useraccount = userRepository.findByUsername(adminUserName);
		if (useraccount == null) {
			throw new RuntimeException("User Doesn't Exist");
		}
		Person person = personRepository.findByUserAccount_Id(useraccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Doesn't Exist");
		}
		List<CareTakerQuestionLookup> careTakerQuestionLookupList1= careTakerQuestionLookupList;
		for (CareTakerQuestionLookup careTakerQuestionLookup : careTakerQuestionLookupList) {
			System.out.println(careTakerQuestionLookupList.size());
			CareTakerCategory dbcareTakerCategory = careTakerCategoryRepository
					.findByNameAndPerson_Id(careTakerQuestionLookup.getCareTakerCategoryLookup().getName(),person.getId());
			CareTakerQuestion careTakerQuestion = new CareTakerQuestion();
			if (dbcareTakerCategory != null) {
				careTakerQuestion.setCareTakerCategory(dbcareTakerCategory);
				
			} else {
				CareTakerCategory category = new CareTakerCategory();
				category.setName(careTakerQuestionLookup.getCareTakerCategoryLookup().getName());
				category.setPerson(person);
				careTakerCategoryRepository.save(category);
				careTakerQuestion.setCareTakerCategory(category);
			}
			CareTakerQuestion dbcareTakerQuestion=careTakerQuestionRepository.findByName(careTakerQuestionLookup.getName());
			if(dbcareTakerQuestion==null){
			careTakerQuestion.setName(careTakerQuestionLookup.getName());
			careTakerQuestion.setPerson(person);
			careTakerQuestion.setCareTakerCategoryLookup(careTakerQuestionLookup.getCareTakerCategoryLookup());
			careTakerQuestionRepository.save(careTakerQuestion);
			}
			List<CareTakerAnswerLookup> careTakerAnswerLookupList=careTakerQuestionLookup.getCareTakerAnswerLookups();
			CareTakerAnswer careTakerAnswer = new CareTakerAnswer();
			for (CareTakerAnswerLookup careTakerAnswerLookup : careTakerAnswerLookupList) {
				careTakerAnswer.setName(careTakerAnswerLookup.getName());
				careTakerAnswer.setActive(careTakerAnswerLookup.isActive());
				careTakerAnswer.setSelectedAnswer(careTakerAnswerLookup.isSelectedAnswer());
			}
			if(dbcareTakerQuestion==null){
				careTakerAnswer.setCareTakerQuestion(careTakerQuestion);
			}else{
				careTakerAnswer.setCareTakerQuestion(dbcareTakerQuestion);
			}
			careTakerAnswerRepository.save(careTakerAnswer);
		}
	}

	@Override
	public List<CareTakerQuestion> getAllCaretakerMilestones(Long id,String adminUserName) {
		// TODO Auto-generated method stub
		UserAccount useraccount = userRepository.findByUsername(adminUserName);
		if (useraccount == null) {
			throw new RuntimeException("User Doesn't Exist");
		}
		Person person = personRepository.findByUserAccount_Id(useraccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Doesn't Exist");
		}
		List<CareTakerQuestion> dbcareTakerQuestionList=careTakerQuestionRepository.findByCareTakerCategoryLookup_IdAndPerson_Id(id,person.getId());
			System.out.println(dbcareTakerQuestionList.size());
		return dbcareTakerQuestionList;
	}

	@Override
	public void updateCaretakerMilestones(List<CareTakerQuestion> careTakerQuestionList, String adminUserName) {
		// TODO Auto-generated method stub
		UserAccount useraccount = userRepository.findByUsername(adminUserName);
		if (useraccount == null) {
			throw new RuntimeException("User Doesn't Exist");
		}
		Person person = personRepository.findByUserAccount_Id(useraccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Doesn't Exist");
		}
		List<CareTakerQuestion> categoryQuestionList=careTakerQuestionList;
		if(categoryQuestionList==null){
			throw new RuntimeException("Categories Can't be null");
		}
		for(CareTakerQuestion careTakerQuestion:categoryQuestionList){
			List<CareTakerAnswer> careTakerAnswers=careTakerQuestion.getCareTakerAnswers();
			for(CareTakerAnswer careTakerAnswer:careTakerAnswers){
				careTakerAnswer.setCareTakerQuestion(careTakerQuestion);
				careTakerAnswerRepository.save(careTakerAnswer);
			}
			
		}
	}

	@Override
	public List<CareTakerGraphDashboardDto> getCaretakerMilstonesForDashboard(String adminUserName) {
		Person person = personRepository.findByEmail(adminUserName);
		if (person == null) {
			throw new RuntimeException("User Does not Exist");
		}
		
		List<CareTakerGraphDashboardDto> dashboardDtosList = new ArrayList<CareTakerGraphDashboardDto>();
		List<CareTakerCategoryLookup> idsList = (List<CareTakerCategoryLookup>) careTakerCategoryLookupRepository
				.findAll();

		List<Long> list = new ArrayList<Long>();
		for (CareTakerCategoryLookup id : idsList) {
			Long a = id.getId();
			list.add(a);
		}
		System.out.println(list);
		for (Long id : list) {
			
			CareTakerGraphDashboardDto dashboardDto=new CareTakerGraphDashboardDto();
			String name = null;
			if (id != null) {
				List<CareTakerQuestion> dbCaretakerQuestions = careTakerQuestionRepository
						.findByCareTakerCategoryLookup_IdAndPerson_Id(id,person.getId());
				if(dbCaretakerQuestions.size()>0){
					int yesCount=0,noCount=0;
				for(CareTakerQuestion question:dbCaretakerQuestions){
					//if(id==question.getChildObservationCategery().getId()){
				name=question.getCareTakerCategory().getName();
				List<CareTakerAnswer> dbAnswers=question.getCareTakerAnswers();
				for(CareTakerAnswer answer:dbAnswers ){
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
