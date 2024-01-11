package com.openspace.PatientManagement.screeningtest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Dto.CreatedDatesDto;
import com.openspace.Model.Dto.GetScreeningTestDataDto;
import com.openspace.Model.Dto.ScreeningTestAnswerLookupDto;
import com.openspace.Model.Dto.ScreeningTestCategoryForGraph;
import com.openspace.Model.Dto.ScreeningTestCategoryLookupDto;
import com.openspace.Model.Dto.ScreeningTestGoalObjectDto;
import com.openspace.Model.Dto.ScreeningTestQuestionLookupDto;
import com.openspace.Model.ParentModule.Repositories.ScreeningTestAnswerRepository;
import com.openspace.Model.ParentModule.Repositories.ScreeningTestCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ScreeningTestCategoryRepository;
import com.openspace.Model.ParentModule.Repositories.ScreeningTestQuestionRepository;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestAnswer;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategory;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategoryLookup;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestQuestion;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;

@Service
public class ScreeningTestCategoryServiceImpl implements ScreeningTestCategoryService {
	
	@Autowired
	private ScreeningTestCategoryLookupRepository screeningTestCategoryLookupRepository;

	@Autowired
	private ScreeningTestCategoryRepository screeningTestCategoryRepository;

	@Autowired
	private ScreeningTestQuestionRepository screeningTestQuestionRepository;

	@Autowired
	private ScreeningTestAnswerRepository screeningTestAnswerRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public void saveScreeningTestCategoryQuestions(String username,
			ScreeningTestGoalObjectDto screeningTestGoalObjectDto) {

		UserAccount userAccount = userAccountRepository.findByUsername(username);
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		ScreeningTestCategoryLookup screeningTestCategoryLookup = screeningTestCategoryLookupRepository.findOne(screeningTestGoalObjectDto.getId());
		if (screeningTestCategoryLookup == null) {
			throw new RuntimeException("screeningTestCategoryLookup Does not Exist");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate date = LocalDate.parse(screeningTestGoalObjectDto.getDate(), formatter);
		System.out.println("date::" + date);
		List<ScreeningTestCategory> dbScreeningTestCategoryGoals = screeningTestCategoryRepository
				.findByScreeningTestCategoryLookup_IdAndUserAccount_IdAndDate(screeningTestGoalObjectDto.getId(),
						userAccount.getId(), LocalDate.now());
		if (dbScreeningTestCategoryGoals.size() == 0) {

			List<ScreeningTestCategory> list2 = new ArrayList<ScreeningTestCategory>();
			for (ScreeningTestCategoryLookupDto questionCategoryDto : screeningTestGoalObjectDto
					.getScreeningTestCategoryLookupDto()) {
				ScreeningTestCategory parentQuestionCategory = new ScreeningTestCategory();
				parentQuestionCategory.setName(questionCategoryDto.getName());
				parentQuestionCategory.setScreeningTestCategoryLookup(screeningTestCategoryLookup);
				parentQuestionCategory.setDate(LocalDate.now());
				parentQuestionCategory.setUserAccount(userAccount);
				screeningTestCategoryRepository.save(parentQuestionCategory);
				List<ScreeningTestQuestion> list3 = new ArrayList<ScreeningTestQuestion>();
				for (ScreeningTestQuestionLookupDto questionDto : questionCategoryDto
						.getScreeningTestQuestionLookups()) {
					ScreeningTestQuestion parentQuestion = new ScreeningTestQuestion();
					parentQuestion.setName(questionDto.getName());
					parentQuestion.setScreeningTestCategory(parentQuestionCategory);
					screeningTestQuestionRepository.save(parentQuestion);
					List<ScreeningTestAnswer> list4 = new ArrayList<ScreeningTestAnswer>();
					for (ScreeningTestAnswerLookupDto answerDto : questionDto.getScreeningTestAnswerLookups()) {
						ScreeningTestAnswer parentAnswer = new ScreeningTestAnswer();
						parentAnswer.setSelectedAnswer(answerDto.isSelectedAnswer());
						parentAnswer.setScreeningTestQuestion(parentQuestion);
						screeningTestAnswerRepository.save(parentAnswer);
						list4.add(parentAnswer);
					}
					list3.add(parentQuestion);
				}
				list2.add(parentQuestionCategory);
			}
		} else {
			if (screeningTestGoalObjectDto.getScreeningTestCategory().size() > 0) {
				for (ScreeningTestCategory parentQuestionCategory : screeningTestGoalObjectDto
						.getScreeningTestCategory()) {
					for (ScreeningTestQuestion parentQuestion : parentQuestionCategory.getScreeningTestQuestions()) {
						for (ScreeningTestAnswer parentAnswer : parentQuestion.getScreeningTestAnswers()) {
							ScreeningTestAnswer dbScreeningTestAnswer = screeningTestAnswerRepository
									.findOne(parentAnswer.getId());
							if (dbScreeningTestAnswer != null) {
								dbScreeningTestAnswer.setSelectedAnswer(parentAnswer.isSelectedAnswer());
								screeningTestAnswerRepository.save(dbScreeningTestAnswer);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<ScreeningTestCategory> getAllScreeningTestCategoryQuestions(GetScreeningTestDataDto getScreeningTestDataDto) {
		UserAccount userAccount = userAccountRepository.findByUsername(getScreeningTestDataDto.getUsername());
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate date = LocalDate.parse(getScreeningTestDataDto.getDate().trim(), formatter);

		List<ScreeningTestCategory> screeningTestCategories = (List<ScreeningTestCategory>) screeningTestCategoryRepository
				.findByScreeningTestCategoryLookup_IdAndUserAccount_IdAndDate(getScreeningTestDataDto.getId(), userAccount.getId(), date);
		ArrayList<ScreeningTestCategory> parentQuestionCategoryList = new ArrayList<ScreeningTestCategory>();
		for (ScreeningTestCategory parentQuestionCategory : screeningTestCategories) {
			ScreeningTestCategory parentQuestionCategory1 = new ScreeningTestCategory();
			parentQuestionCategory1.setId(parentQuestionCategory.getId());
			parentQuestionCategory1.setName(parentQuestionCategory.getName());
			parentQuestionCategory1.setCategoryStatus(parentQuestionCategory.getCategoryStatus());
			parentQuestionCategory1.setUserAccount(parentQuestionCategory.getUserAccount());

			ArrayList<ScreeningTestQuestion> parentquestionList = new ArrayList<ScreeningTestQuestion>();
			List<ScreeningTestQuestion> parentQuestionList = parentQuestionCategory.getScreeningTestQuestions();
			for (ScreeningTestQuestion parentQuestion : parentQuestionList) {
				ScreeningTestQuestion parentQuestion1 = new ScreeningTestQuestion();
				parentQuestion1.setId(parentQuestion.getId());
				parentQuestion1.setName(parentQuestion.getName());

				List<ScreeningTestAnswer> parentAnswerList = parentQuestion.getScreeningTestAnswers();
				ArrayList<ScreeningTestAnswer> parentAnswerList1 = new ArrayList<ScreeningTestAnswer>();
				for (ScreeningTestAnswer parentAnswer : parentAnswerList) {
					ScreeningTestAnswer parentAnswer1 = new ScreeningTestAnswer();
					parentAnswer1.setSelectedAnswer(parentAnswer.isSelectedAnswer());
					parentAnswer1.setId(parentAnswer.getId());
					parentAnswerList1.add(parentAnswer1);
				}
				parentQuestion1.setScreeningTestAnswers(parentAnswerList1);
				parentquestionList.add(parentQuestion1);
			}
			parentQuestionCategory1.setScreeningTestQuestions(parentquestionList);
			parentQuestionCategoryList.add(parentQuestionCategory1);

		}
		return parentQuestionCategoryList;
	}

	@Override
	public void updateScreeningTestCategoryQuestions(ScreeningTestCategory screeningTestCategory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteScreeningTestCategoryQuestions(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CreatedDatesDto> getAllScreeningTestGoalsCreatedDates(String username,Long id) {

		UserAccount userAccount = userAccountRepository.findByUsername(username);
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		ScreeningTestCategoryLookup screeningTestCategoryLookup = screeningTestCategoryLookupRepository.findOne(id);
		if (screeningTestCategoryLookup == null) {
			throw new RuntimeException("ScreeningTest CategoryLookup Does not Exist");
		}
		Map<LocalDate,Long> dates = new HashMap<LocalDate,Long>();
		List<ScreeningTestCategory> screeningTestCategory = screeningTestCategoryRepository
				.findByScreeningTestCategoryLookup_IdAndUserAccount_Id(screeningTestCategoryLookup.getId(), userAccount.getId());
		for (ScreeningTestCategory screeningTestCategoryGoals : screeningTestCategory) {
			dates.put(screeningTestCategoryGoals.getDate(), screeningTestCategoryGoals.getScreeningTestCategoryLookup().getId());
		}
		Map<LocalDate, Long> sortedMap = new TreeMap<LocalDate, Long>(dates);
		List<CreatedDatesDto> list=new ArrayList<CreatedDatesDto>();
		for(Entry<LocalDate, Long> map:sortedMap.entrySet()){
			CreatedDatesDto createdDatesDto=new CreatedDatesDto();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			createdDatesDto.setDate(map.getKey().format(formatter));
			createdDatesDto.setId(map.getValue());
			list.add(createdDatesDto);
		}
		return list;
	}
	

	@Override
	public List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForSocialStack(String userName) {
		
		List<ScreeningTestCategoryForGraph> stackDataList = new ArrayList<ScreeningTestCategoryForGraph>();
		String[] state = getAllSocialGoalsCreatedDates(userName,"1");
		List<LocalDate> parentGoalstateList = new ArrayList<LocalDate>();
		
		for (int i = 0; i < state.length; i++) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			LocalDate stDate = LocalDate.parse(state[i],formatter);
			parentGoalstateList.add(stDate);
		}
		for (int k = 0; k < parentGoalstateList.size(); k++) {
			Integer parentgoalcomplete = getYesGoalsByUserAccount_IdAndStatusAndDate(userName,"1",parentGoalstateList.get(k));
			Integer paerntgoalincomplete = getNoGoalsByUserAccount_IdAndStatusAndDate(userName,"1",parentGoalstateList.get(k));
			ScreeningTestCategoryForGraph graph = new ScreeningTestCategoryForGraph();

			if (k < parentGoalstateList.size()) {
				graph.setState(state[k]);
				graph.setYes(parentgoalcomplete);
				graph.setNo(paerntgoalincomplete);
				stackDataList.add(graph);
			}
		}
		return stackDataList;
	}
	
	public Integer getYesGoalsByUserAccount_IdAndStatusAndDate(String userName,String categoryStatus,
			LocalDate date) {
		UserAccount userAccount = userAccountRepository.findByUsername(userName);
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		int count = 0;
		ScreeningTestCategory screeningTestCategory = screeningTestCategoryRepository.findByCategoryStatusAndDateAndUserAccount_Id(categoryStatus,date,userAccount.getId());
		if (screeningTestCategory != null) {
			List<ScreeningTestQuestion> screeningTestQuestion = screeningTestCategory.getScreeningTestQuestions();
			if (screeningTestQuestion.size() > 0) {
				for (ScreeningTestQuestion screeningTestQuestion1 : screeningTestQuestion) {
					List<ScreeningTestAnswer> screeningTestAnswers = screeningTestQuestion1.getScreeningTestAnswers();
					if (screeningTestAnswers.size() > 0) {
						for (ScreeningTestAnswer screeningTestAnswer : screeningTestAnswers) {
							if (screeningTestAnswer.isSelectedAnswer()==true) {
								count++;
							}
						}
					}
				}
			}
		}
		return count;
	}

	public Integer getNoGoalsByUserAccount_IdAndStatusAndDate(String userName,String categoryStatus,
			LocalDate date) {
		UserAccount userAccount = userAccountRepository.findByUsername(userName);
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		int count = 0;
		ScreeningTestCategory screeningTestCategory = screeningTestCategoryRepository.findByCategoryStatusAndDateAndUserAccount_Id(categoryStatus,date,userAccount.getId());
		if (screeningTestCategory != null) {
			List<ScreeningTestQuestion> screeningTestQuestion = screeningTestCategory.getScreeningTestQuestions();
			if (screeningTestQuestion.size() > 0) {
				for (ScreeningTestQuestion screeningTestQuestion1 : screeningTestQuestion) {
					List<ScreeningTestAnswer> screeningTestAnswers = screeningTestQuestion1.getScreeningTestAnswers();
					if (screeningTestAnswers.size() > 0) {
						for (ScreeningTestAnswer screeningTestAnswer : screeningTestAnswers) {
							if (screeningTestAnswer.isSelectedAnswer()==false) {
								count++;
							}
						}
					}
				}
			}
		}
		return count;
	}

	private String[] getAllSocialGoalsCreatedDates(String username, String categoryStatus) {
		UserAccount userAccount = userAccountRepository.findByUsername(username);
		if (userAccount == null) {
			throw new RuntimeException("User Account Does not Exist");
		}
		Set<String> dates = new HashSet<String>();
		List<ScreeningTestCategory> screeningTestCategory = screeningTestCategoryRepository
				.findByCategoryStatusAndUserAccount_Id(categoryStatus, userAccount.getId());
		for (ScreeningTestCategory screeningTestCategoryGoals : screeningTestCategory) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			dates.add(screeningTestCategoryGoals.getDate().format(formatter));
		}
		TreeSet<String> tset = new TreeSet<String>(dates);
		String[] array = tset.toArray(new String[0]);
		System.out.println(Arrays.toString(array));
		return array;
	}

	@Override
	public List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForImparimentStackGraph(String userName) {
		
		List<ScreeningTestCategoryForGraph> stackDataList = new ArrayList<ScreeningTestCategoryForGraph>();
		String[] state = getAllSocialGoalsCreatedDates(userName,"2");
		List<LocalDate> parentGoalstateList = new ArrayList<LocalDate>();
		
		for (int i = 0; i < state.length; i++) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			LocalDate stDate = LocalDate.parse(state[i],formatter);
			parentGoalstateList.add(stDate);
		}
		for (int k = 0; k < parentGoalstateList.size(); k++) {
			Integer parentgoalcomplete = getYesGoalsByUserAccount_IdAndStatusAndDate(userName,"2",parentGoalstateList.get(k));
			Integer paerntgoalincomplete = getNoGoalsByUserAccount_IdAndStatusAndDate(userName,"2",parentGoalstateList.get(k));
			ScreeningTestCategoryForGraph graph = new ScreeningTestCategoryForGraph();

			if (k < parentGoalstateList.size()) {
				graph.setState(state[k]);
				graph.setYes(parentgoalcomplete);
				graph.setNo(paerntgoalincomplete);
				stackDataList.add(graph);
			}
		}
		return stackDataList;
	}

	@Override
	public List<ScreeningTestCategoryForGraph> getAllScreeningTestCategoryForBehaviourStackGraph(String userName) {
		
		List<ScreeningTestCategoryForGraph> stackDataList = new ArrayList<ScreeningTestCategoryForGraph>();
		String[] state = getAllSocialGoalsCreatedDates(userName,"3");
		List<LocalDate> parentGoalstateList = new ArrayList<LocalDate>();
		
		for (int i = 0; i < state.length; i++) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			LocalDate stDate = LocalDate.parse(state[i],formatter);
			parentGoalstateList.add(stDate);
		}
		for (int k = 0; k < parentGoalstateList.size(); k++) {
			Integer parentgoalcomplete = getYesGoalsByUserAccount_IdAndStatusAndDate(userName,"3",parentGoalstateList.get(k));
			Integer paerntgoalincomplete = getNoGoalsByUserAccount_IdAndStatusAndDate(userName,"3",parentGoalstateList.get(k));
			ScreeningTestCategoryForGraph graph = new ScreeningTestCategoryForGraph();

			if (k < parentGoalstateList.size()) {
				graph.setState(state[k]);
				graph.setYes(parentgoalcomplete);
				graph.setNo(paerntgoalincomplete);
				stackDataList.add(graph);
			}
		}
		return stackDataList;
	}

}
