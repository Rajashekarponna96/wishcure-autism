package com.openspace.PatientManagement.NICHQParent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.NICHQParent.NICHQParentCategoryLookup;
import com.openspace.Model.NICHQParent.NICHQParentQuestionLookup;
import com.openspace.Model.NICHQParent.NichqParentCategory;
import com.openspace.Model.NICHQParent.NichqParentCategoryDto;
import com.openspace.Model.NICHQParent.NichqParentQuestion;
import com.openspace.Model.NICHQParent.NichqParentResult;
import com.openspace.Model.NICHQParentRepository.NichqParentCategoryRepository;
import com.openspace.Model.NICHQParentRepository.NichqParentQuestionRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;

@Service
public class NichqParentCategoryServiceImpl implements NichqParentCategoryService {

	@Autowired
	private NichqParentCategoryRepository nichqParentCategoryRepository;

	@Autowired
	private NichqParentQuestionRepository nichqParentQuestionRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void saveNichqCategoryQuestions(NichqParentCategoryDto nichqParentCategoryDto) {
		Patient dbPatient = patientRepository.findOne(nichqParentCategoryDto.getPatient().getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not exists!!!");
		}
		NichqParentCategory dbNichqParentCategory = nichqParentCategoryRepository
				.findByPatient_IdAndNICHQParentCategoryLookup_Id(dbPatient.getId() ,nichqParentCategoryDto.getNichqParentCategoryLookup().getId());
		if (dbNichqParentCategory == null ) {
			NICHQParentCategoryLookup nichQParentCategoryLookup = nichqParentCategoryDto.getNichqParentCategoryLookup();
			if (nichQParentCategoryLookup != null) {
				NichqParentCategory nichqParentCategory = new NichqParentCategory();
				nichqParentCategory.setName(nichQParentCategoryLookup.getName());
				nichqParentCategory.setPatient(dbPatient);
				nichqParentCategory.setDate(LocalDate.now());
				nichqParentCategory.setnICHQParentCategoryLookup(nichQParentCategoryLookup);
				nichqParentCategoryRepository.save(nichqParentCategory);
				if (nichqParentCategoryDto.getNichqParentQuestionLookupList() != null
						&& nichqParentCategoryDto.getNichqParentQuestionLookupList().size() > 0) {

					for (NICHQParentQuestionLookup nichqParentQuestionLookup : nichqParentCategoryDto.getNichqParentQuestionLookupList()) {
						
						NichqParentQuestion nichqParentQuestion = new NichqParentQuestion();
						nichqParentQuestion.setName(nichqParentQuestionLookup.getName());
						nichqParentQuestion.setNichqParentCategory(nichqParentCategory);
						nichqParentQuestion.setAnsValue(nichqParentQuestionLookup.getAnswervalue());
						nichqParentQuestion.setNichqParentCategoryLookup(nichQParentCategoryLookup);
						nichqParentQuestionRepository.save(nichqParentQuestion);
					}
				}
			}
		} else if (dbNichqParentCategory != null) {
				if (nichqParentCategoryDto.getNichqParentCategory() != null) {
							if (dbNichqParentCategory.getNichqParentQuestion() != null
									&& dbNichqParentCategory.getNichqParentQuestion().size() > 0
									&& nichqParentCategoryDto.getNichqParentQuestionList() != null
									&& nichqParentCategoryDto.getNichqParentQuestionList().size() > 0) {
								for (NichqParentQuestion nichqParentQuestion : dbNichqParentCategory
										.getNichqParentQuestion()) {
									for (NichqParentQuestion nichqParentQuestion1 : nichqParentCategoryDto.getNichqParentQuestionList()) {
										if (nichqParentQuestion.getId().equals(nichqParentQuestion1.getId())) {
											nichqParentQuestion.setAnsValue(nichqParentQuestion1.getAnsValue());
											nichqParentQuestionRepository.save(nichqParentQuestion);
										}
									}
								}
							}
				}
		}
	}

	@Override
	public List<NichqParentCategory> getAllNichqCategorysByPatientId(Long patientId) {

		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		return nichqParentCategoryRepository.findByPatient_Id(dbPatient.getId());
	}

	@Override
	public NichqParentResult getNichqParentResultByPatientId(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		List<NichqParentCategory> nichqParentCategoryList = nichqParentCategoryRepository
				.findByPatient_Id(dbPatient.getId());
		List<NichqParentQuestion> allNichqParentQuestionsList = new ArrayList<NichqParentQuestion>();
		NichqParentResult parentFinalResult = new NichqParentResult();
		if (nichqParentCategoryList != null && nichqParentCategoryList.size() > 0) {

			NichqParentCategory firstNichqParentCategory = nichqParentCategoryList.get(0);
			NichqParentCategory lastNichqParentCategory = nichqParentCategoryList
					.get(nichqParentCategoryList.size() - 1);
			for (Long i = firstNichqParentCategory.getId(); i <= lastNichqParentCategory.getId(); i++) {
				List<NichqParentQuestion> dbNichqParentQuestions = nichqParentQuestionRepository
						.findByNichqParentCategory_Id(i);
				allNichqParentQuestionsList.addAll(dbNichqParentQuestions);

			}

		}
		if (allNichqParentQuestionsList.size() >= 0 && allNichqParentQuestionsList.size() == 55) {

			parentFinalResult = getParentResult(allNichqParentQuestionsList);
		}
		return parentFinalResult;
	}

	public NichqParentResult getParentResult(List<NichqParentQuestion> allNichqParentQuestionsList) {

		int score1to9 = 0;
		int score10to18 = 0;
		int score19to26 = 0;
		int score27to40 = 0;
		int score41to47 = 0;
		int score48to55 = 0;
		NichqParentQuestion firstQuestion = allNichqParentQuestionsList.get(0);

		NichqParentResult nichqParentResult = new NichqParentResult();
		int j = firstQuestion.getId().intValue();
		int k = j + 8;
		for (int i = 0; i <= 8; i++) {
			int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score1to9 = score1to9 + indvidualScore;
				nichqParentResult.setScore1to9(score1to9);
			}
		}
		// 10 to 18
		int a = k + 1;
		int b = a + 7;
		for (int i = 9; i <= 17; i++) {
			int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score10to18 = score10to18 + indvidualScore;
				nichqParentResult.setScore10to18(score10to18);
			}
		}
		nichqParentResult.setScore1to18(score1to9 + score10to18);
		// 19 to 26
		int c = b + 1;
		int d = c + 6;
		for (int i = 18; i <= 25; i++) {
			int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score19to26 = score19to26 + indvidualScore;
				nichqParentResult.setScore19to26(score19to26);
			}
		}
		// 27 to 40
		int e = d + 1;
		int f = e + 12;
		for (int i = 26; i <= 39; i++) {
			int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score27to40 = score27to40 + indvidualScore;
				nichqParentResult.setScore27to40(score27to40);
			}
		}
		// 41 to 47
		int g = f + 1;
		int h = g + 5;
		for (int i = 40; i <= 46; i++) {
			int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
			if (indvidualScore == 2 || indvidualScore == 3) {
				score41to47 = score41to47 + indvidualScore;
				nichqParentResult.setScore41to47(score41to47);
			}
		}

		// 48 to 55
		int m = h + 1;
		int n = m + 6;
		for (int i = 47; i <= 54; i++) {
			int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
			if (indvidualScore == 4 || indvidualScore == 5) {
				score48to55 = score48to55 + indvidualScore;
				nichqParentResult.setScore48to55(score48to55);
			}
		}
		nichqParentResult.setAvgPerformanceScore(
				(score48to55 + score41to47 + score27to40 + score19to26 + score10to18 + score1to9) / 55);

		return nichqParentResult;
	}

	@Override
	public void deleteNichqParentResultByPatientId(Long patientId) {
		// TODO Auto-generated method stub

		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!!");
		}
		List<NichqParentCategory> nichqParentCategoryList = nichqParentCategoryRepository
				.findByPatient_Id(dbPatient.getId());
		nichqParentCategoryRepository.delete(nichqParentCategoryList);
	}

}
