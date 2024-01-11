package com.openspace.PatientManagement.NICHQParent;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Dto.NichqParentResultDto;
import com.openspace.Model.NICHQParent.NichqParentCategory;
import com.openspace.Model.NICHQParent.NichqParentQuestion;
import com.openspace.Model.NICHQParentRepository.NichqParentCategoryRepository;
import com.openspace.Model.NICHQParentRepository.NichqParentQuestionRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class NichqParentQuestionServiceImpl implements NichqParentQuestionService {

	@Autowired
	private NichqParentQuestionRepository nichqParentQuestionRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private NichqParentCategoryRepository nichqParentCategoryRepository;

	@Override
	public List<NichqParentQuestion> getNichqParentQuestionsByPatientAndCategoryId(Long patientId, Long categoryId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not exists!!");
		}
		return nichqParentQuestionRepository
				.findByNichqParentCategory_Patient_IdAndNichqParentCategoryLookup_Id(dbPatient.getId(), categoryId);
	}

	@Override
	public List<List<NichqParentResultDto>> nichqParentResultByPatient(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not exists!!");
		}
		List<List<NichqParentResultDto>> allresults = new ArrayList<List<NichqParentResultDto>>();
		List<NichqParentCategory> categories = nichqParentCategoryRepository.findByPatient_Id(dbPatient.getId());
		List<NichqParentQuestion> allNichqParentQuestionsList = new ArrayList<NichqParentQuestion>();
		List<NichqParentResultDto> results1to9 = new ArrayList<NichqParentResultDto>();
		List<NichqParentResultDto> results10to18 = new ArrayList<NichqParentResultDto>();
		for (NichqParentCategory category : categories) {
			if (category != null) {
				List<NichqParentQuestion> questions = nichqParentQuestionRepository
						.findByNichqParentCategory_Patient_IdAndNichqParentCategory_Id(dbPatient.getId(),
								category.getId());
				if (questions != null && questions.size() > 0) {
					allNichqParentQuestionsList.addAll(questions);
				}
			}
		}
		if (allNichqParentQuestionsList != null && allNichqParentQuestionsList.size() > 0) {
			for (int i = 0; i <= 46; i++) {
				int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
				NichqParentResultDto nichqParentResultDto = new NichqParentResultDto();
				// starting index =0 so add 1 to i
				int p=i+1;
				nichqParentResultDto.setQuestion(""+ p);
				nichqParentResultDto.setAnswer(indvidualScore);
				results1to9.add(nichqParentResultDto);
			}
		
			for (int i = 47; i <= 54; i++) {
				int indvidualScore = allNichqParentQuestionsList.get(i).getAnsValue();
				NichqParentResultDto nichqParentResultDto = new NichqParentResultDto();
				// starting index =0 so add 1 to i
				int p=i+1;
				nichqParentResultDto.setQuestion(""+p);
				nichqParentResultDto.setAnswer(indvidualScore);
				results10to18.add(nichqParentResultDto);
				
			}
		}
		allresults.add(results1to9);
		allresults.add(results10to18);
		return allresults;
	}

	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<NichqParentCategory> nichqParentCategoryList = nichqParentCategoryRepository.findByPatient_Id(patientId);
		for (NichqParentCategory nichqParentCategory : nichqParentCategoryList) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (nichqParentCategory.getDate() != null) {
				dates.add(nichqParentCategory.getDate().format(formatter));
			}
		}
		return dates;
	}
}
