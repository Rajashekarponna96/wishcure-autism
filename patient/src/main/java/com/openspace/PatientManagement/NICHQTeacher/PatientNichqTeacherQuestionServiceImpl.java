package com.openspace.PatientManagement.NICHQTeacher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Dto.NichqTeacherResultDto;
import com.openspace.Model.NICHQTeacher.Repository.PatientNichqTeacherCategoryRepository;
import com.openspace.Model.NICHQTeacher.Repository.PatientNichqTeacherQuestionRepository;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherQuestionLookup;
import com.openspace.Model.NichqTeachers.NichqTeacherResult;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherCategory;
import com.openspace.Model.NichqTeachers.PatientNichqTeacherQuestion;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.PatientManagement.dto.PatientNichqTeacherCategoryDto;

@Service
public class PatientNichqTeacherQuestionServiceImpl implements PatientNichqTeacherQuestionService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientNichqTeacherCategoryRepository patientNichqTeacherCategoryRepository;

	@Autowired
	private PatientNichqTeacherQuestionRepository patientNichqTeacherQuestionRepository;

	@Override
	public void assignNichqTeacherCategoryToPatient(PatientNichqTeacherCategoryDto patientNichqTeacherCategoryDto) {
		Patient dbPatient = patientRepository.findOne(patientNichqTeacherCategoryDto.getPatient().getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not exists!!!");
		}
		PatientNichqTeacherCategory dbNichqTeacherCategory = patientNichqTeacherCategoryRepository
				.findByPatient_IdAndNichqTeacherCategoryLookup_Id(dbPatient.getId(),
						patientNichqTeacherCategoryDto.getNichqTeacherCategoryLookup().getId());
		if (dbNichqTeacherCategory == null) {
			NichqTeacherCategoryLookup nichqTeacherCategoryLookup = patientNichqTeacherCategoryDto
					.getNichqTeacherCategoryLookup();
			if (nichqTeacherCategoryLookup != null) {
				PatientNichqTeacherCategory patientNichqTeacherCategory = new PatientNichqTeacherCategory();
				patientNichqTeacherCategory.setName(nichqTeacherCategoryLookup.getName());
				patientNichqTeacherCategory.setPatient(dbPatient);
				patientNichqTeacherCategory.setNichqTeacherCategoryLookup(nichqTeacherCategoryLookup);
				patientNichqTeacherCategory.setDate(LocalDate.now());
				patientNichqTeacherCategoryRepository.save(patientNichqTeacherCategory);
				if (patientNichqTeacherCategoryDto.getNichqTeacherQuestionLookupsList() != null
						&& patientNichqTeacherCategoryDto.getNichqTeacherQuestionLookupsList().size() > 0) {

					for (NichqTeacherQuestionLookup nichqTeacherQuestionLookup : patientNichqTeacherCategoryDto
							.getNichqTeacherQuestionLookupsList()) {

						PatientNichqTeacherQuestion patientNichqTeacherQuestion = new PatientNichqTeacherQuestion();
						patientNichqTeacherQuestion.setName(nichqTeacherQuestionLookup.getName());
						patientNichqTeacherQuestion.setPatientNichqTeacherCategory(patientNichqTeacherCategory);
						patientNichqTeacherQuestion.setAnsValue(nichqTeacherQuestionLookup.getAnswervalue());
						patientNichqTeacherQuestion.setNichqTeacherCategoryLookup(nichqTeacherCategoryLookup);
						patientNichqTeacherQuestionRepository.save(patientNichqTeacherQuestion);
					}
				}
			}
		} else if (dbNichqTeacherCategory != null) {
			if (patientNichqTeacherCategoryDto.getPatientNichqTeacherCategory() != null) {
				if (dbNichqTeacherCategory.getId()
						.equals(patientNichqTeacherCategoryDto.getPatientNichqTeacherCategory().getId())) {
					if (dbNichqTeacherCategory.getPatientNichqTeacherQuestions() != null
							&& dbNichqTeacherCategory.getPatientNichqTeacherQuestions().size() > 0
							&& patientNichqTeacherCategoryDto.getPatientNichqTeacherQuestionsList() != null
							&& patientNichqTeacherCategoryDto.getPatientNichqTeacherQuestionsList().size() > 0) {
						for (PatientNichqTeacherQuestion patientNichqTeacherQuestion : dbNichqTeacherCategory
								.getPatientNichqTeacherQuestions()) {
							for (PatientNichqTeacherQuestion patientNichqTeacherQuestion1 : patientNichqTeacherCategoryDto
									.getPatientNichqTeacherQuestionsList()) {
								if (patientNichqTeacherQuestion.getId().equals(patientNichqTeacherQuestion1.getId())) {
									patientNichqTeacherQuestion.setAnsValue(patientNichqTeacherQuestion1.getAnsValue());
									patientNichqTeacherQuestionRepository.save(patientNichqTeacherQuestion);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public List<PatientNichqTeacherCategory> getAllNichqTeacherCategoriesByPatient(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		List<PatientNichqTeacherCategory> patientNichqTeacherCategoriesList = patientNichqTeacherCategoryRepository
				.findByPatient_Id(dbPatient.getId());

		return patientNichqTeacherCategoriesList;
	}

	@Override
	public void updateNichqTeacherCategoryToPatient(PatientNichqTeacherCategory patientNichqTeacherCategory) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientNichqTeacherCategory.getPatient().getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		PatientNichqTeacherCategory dbPatientNichqTeacherCategory = patientNichqTeacherCategoryRepository
				.findOne(patientNichqTeacherCategory.getId());
		if (dbPatientNichqTeacherCategory == null) {
			throw new RuntimeException("PatientNichqTeacherCategory does not exists!! ");
		}
		if (dbPatientNichqTeacherCategory.getPatientNichqTeacherQuestions() != null
				&& dbPatientNichqTeacherCategory.getPatientNichqTeacherQuestions().size() > 0
				&& patientNichqTeacherCategory.getPatientNichqTeacherQuestions() != null
				&& patientNichqTeacherCategory.getPatientNichqTeacherQuestions().size() > 0) {
			for (PatientNichqTeacherQuestion dbPatientNichqTeacherQuestion : dbPatientNichqTeacherCategory
					.getPatientNichqTeacherQuestions()) {

				for (PatientNichqTeacherQuestion dbPatientNichqTeacherQuestion1 : patientNichqTeacherCategory
						.getPatientNichqTeacherQuestions()) {
					if (dbPatientNichqTeacherQuestion.getId().equals(dbPatientNichqTeacherQuestion1.getId())) {
						dbPatientNichqTeacherQuestion.setAnsValue(dbPatientNichqTeacherQuestion1.getAnsValue());
						patientNichqTeacherQuestionRepository.save(dbPatientNichqTeacherQuestion);
					}
				}
			}
		}
	}

	@Override
	public NichqTeacherResult getNichqTeacherResultByPatient(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		NichqTeacherResult finalNichqTeacherResult = new NichqTeacherResult();
		List<PatientNichqTeacherQuestion> allPatientNichqTeacherQuestions = new ArrayList<PatientNichqTeacherQuestion>();
		List<PatientNichqTeacherCategory> patientNichqTeacherCategories = patientNichqTeacherCategoryRepository
				.findByPatient_Id(dbPatient.getId());
		if (patientNichqTeacherCategories != null && patientNichqTeacherCategories.size() > 0) {

			for (PatientNichqTeacherCategory patientNichqTeacherCategory : patientNichqTeacherCategories) {
				if (patientNichqTeacherCategory != null) {
					List<PatientNichqTeacherQuestion> dbPatientNichqTeacherQuestions = patientNichqTeacherQuestionRepository
							.findByPatientNichqTeacherCategory_Id(patientNichqTeacherCategory.getId());
					allPatientNichqTeacherQuestions.addAll(dbPatientNichqTeacherQuestions);
				}
			}
		}
		if (allPatientNichqTeacherQuestions != null && allPatientNichqTeacherQuestions.size() > 0) {
			finalNichqTeacherResult = getNichqTeacherResult(allPatientNichqTeacherQuestions);
		}
		return finalNichqTeacherResult;
	}

	public NichqTeacherResult getNichqTeacherResult(List<PatientNichqTeacherQuestion> allPatientNichqTeacherQuestions) {
		int score1to9 = 0;
		int score10to18 = 0;
		int score19to28 = 0;
		int score29to35 = 0;
		int score36to43 = 0;

		NichqTeacherResult nichqTeacherResult = new NichqTeacherResult();
		for (int i = 0; i <= 8; i++) {
			int indvidualScore = allPatientNichqTeacherQuestions.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score1to9 = score1to9 + indvidualScore;
				nichqTeacherResult.setScore1to9(score1to9);
			}
		}
		for (int i = 9; i <= 17; i++) {
			int indvidualScore = allPatientNichqTeacherQuestions.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score10to18 = score10to18 + indvidualScore;
				nichqTeacherResult.setScore10to18(score10to18);
			}
		}
		nichqTeacherResult.setScore1to18(score1to9 + score10to18);
		for (int i = 18; i <= 27; i++) {
			int indvidualScore = allPatientNichqTeacherQuestions.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score19to28 = score19to28 + indvidualScore;
				nichqTeacherResult.setScore19to28(score19to28);
			}
		}
		for (int i = 28; i <= 34; i++) {
			int indvidualScore = allPatientNichqTeacherQuestions.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score29to35 = score29to35 + indvidualScore;
				nichqTeacherResult.setScore29to35(score29to35);
			}
		}
		for (int i = 35; i <= 42; i++) {
			int indvidualScore = allPatientNichqTeacherQuestions.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score36to43 = score36to43 + indvidualScore;
				nichqTeacherResult.setScore36to43(score36to43);
			}
		}

		nichqTeacherResult.setAverageScore(
				(score1to9 + score10to18 + score10to18 + score19to28 + score29to35 + score36to43) / 43);

		return nichqTeacherResult;
	}

	@Override
	public void deleteNichqTeacherReportByPatient(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		List<PatientNichqTeacherCategory> patientNichqTeacherCategories = patientNichqTeacherCategoryRepository
				.findByPatient_Id(dbPatient.getId());
		patientNichqTeacherCategoryRepository.delete(patientNichqTeacherCategories);
	}

	// getall nichqteacher questions by category and patient

	@Override
	public List<PatientNichqTeacherQuestion> getAllPatientNichqTeacherQuestionsByPatientAndCategory(Long patientId,
			Long categoryId) {
		// TODO Auto-generated method stub
		return patientNichqTeacherQuestionRepository
				.findByPatientNichqTeacherCategory_Patient_IdAndNichqTeacherCategoryLookup_Id(patientId, categoryId);
	}

	@Override
	public List<List<NichqTeacherResultDto>> getNichqTeacherReportByPatient(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not exists!!");
		}
		List<List<NichqTeacherResultDto>> allresults = new ArrayList<List<NichqTeacherResultDto>>();
		List<PatientNichqTeacherCategory> categories = patientNichqTeacherCategoryRepository
				.findByPatient_Id(dbPatient.getId());
		List<PatientNichqTeacherQuestion> allIsaaBehaviorQuestionsList = new ArrayList<PatientNichqTeacherQuestion>();
		List<NichqTeacherResultDto> results1to35 = new ArrayList<NichqTeacherResultDto>();
		List<NichqTeacherResultDto> results36to38 = new ArrayList<NichqTeacherResultDto>();
		List<NichqTeacherResultDto> results39to43 = new ArrayList<NichqTeacherResultDto>();

		for (PatientNichqTeacherCategory category : categories) {
			if (category != null) {
				List<PatientNichqTeacherQuestion> questions = patientNichqTeacherQuestionRepository
						.findByPatientNichqTeacherCategory_Patient_IdAndPatientNichqTeacherCategory_Id(
								dbPatient.getId(), category.getId());
				if (questions != null && questions.size() > 0) {
					allIsaaBehaviorQuestionsList.addAll(questions);
				}
			}
		}
		if (allIsaaBehaviorQuestionsList != null && allIsaaBehaviorQuestionsList.size() > 0) {
			for (int i = 0; i <= 8; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				NichqTeacherResultDto nichqTeacherResultDto = new NichqTeacherResultDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				nichqTeacherResultDto.setQuestion("" + j);
				nichqTeacherResultDto.setAnswer(indvidualScore);
				results1to35.add(nichqTeacherResultDto);
			}
			for (int i = 9; i <= 17; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				NichqTeacherResultDto nichqTeacherResultDto = new NichqTeacherResultDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				nichqTeacherResultDto.setQuestion("" + j);
				nichqTeacherResultDto.setAnswer(indvidualScore);
				results36to38.add(nichqTeacherResultDto);
			}

			for (int i = 18; i <= 27; i++) {
				int indvidualScore = allIsaaBehaviorQuestionsList.get(i).getAnsValue();
				NichqTeacherResultDto nichqTeacherResultDto = new NichqTeacherResultDto();
				// starting index =0 so add 1 to i
				int j=i+1;
				nichqTeacherResultDto.setQuestion("" + j);
				nichqTeacherResultDto.setAnswer(indvidualScore);
				results39to43.add(nichqTeacherResultDto);
			}
		}
		allresults.add(results1to35);
		allresults.add(results36to38);
		allresults.add(results39to43);
		
		
		return allresults;
	}

	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<PatientNichqTeacherCategory> patientNichqTeacherCategoryList = patientNichqTeacherCategoryRepository.findByPatient_Id(patientId);
		for (PatientNichqTeacherCategory patientNichqTeacherCategory : patientNichqTeacherCategoryList) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (patientNichqTeacherCategory.getDate() != null) {
				dates.add(patientNichqTeacherCategory.getDate().format(formatter));
			}
		}
		return dates;
	}

}
