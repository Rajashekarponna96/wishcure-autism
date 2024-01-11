package com.openspace.PatientManagement.NICHQTeacherLookup;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.NICHQParent.NichqParentQuestion;
import com.openspace.Model.NICHQTeacher.Repository.NichqTeacherAnswerLookupRepository;
import com.openspace.Model.NICHQTeacher.Repository.NichqTeacherCategorylookupRepository;
import com.openspace.Model.NICHQTeacher.Repository.NichqTeacherQuestionLookupRepository;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherAnswerLookup;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherQuestionLookup;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class NichqTeacherQuestionLookupServiceImpl implements NichqTeacherQuestionLookupService {

	@Autowired
	private NichqTeacherQuestionLookupRepository nichqTeacherQuestionLookupRepository;

	@Autowired
	private NichqTeacherCategorylookupRepository nichqTeacherCategorylookupRepository;

	@Autowired
	private NichqTeacherAnswerLookupRepository nichqTeacherAnswerLookupRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void addQuestionLookup(NichqTeacherQuestionLookup nichqTeacherQuestionLookup) {
		// TODO Auto-generated method stub
		NichqTeacherQuestionLookup dbNichqTeacherQuestionLookup = nichqTeacherQuestionLookupRepository
				.findByName(nichqTeacherQuestionLookup.getName());
		if (dbNichqTeacherQuestionLookup != null) {
			throw new RuntimeException("NichqTeacher Question lookup already exists!!");
		}

		NichqTeacherCategoryLookup dbNichqTeacherCategoryLookup = nichqTeacherCategorylookupRepository
				.findByName(nichqTeacherQuestionLookup.getNichqTeacherCategoryLookup().getName());
		if (dbNichqTeacherCategoryLookup == null) {
			throw new RuntimeException("Category Does not Exists!!");
		}
		NichqTeacherQuestionLookup nichqTeacherQuestionLookup1 = new NichqTeacherQuestionLookup();
		nichqTeacherQuestionLookup1.setName(nichqTeacherQuestionLookup.getName());
		nichqTeacherQuestionLookup1.setNichqTeacherCategoryLookup(dbNichqTeacherCategoryLookup);
		nichqTeacherQuestionLookupRepository.save(nichqTeacherQuestionLookup1);
		for (NichqTeacherAnswerLookup answerLookup : nichqTeacherQuestionLookup.getNichqTeacherAnswerlookups()) {
			NichqTeacherAnswerLookup nichqTeacherAnswerLookup = new NichqTeacherAnswerLookup();
			nichqTeacherAnswerLookup.setActive(true);
			nichqTeacherAnswerLookup.setNichqTeacherQuestionLookup(dbNichqTeacherQuestionLookup);
			nichqTeacherAnswerLookup.setSelectedAnswer(answerLookup.isSelectedAnswer());
			nichqTeacherAnswerLookup.setSolution(answerLookup.getSolution());
			nichqTeacherAnswerLookup.setNichqTeacherQuestionLookup(nichqTeacherQuestionLookup1);
			nichqTeacherAnswerLookupRepository.save(nichqTeacherAnswerLookup);
		}

	}

	@Override
	public List<NichqTeacherQuestionLookup> getAllQuestionLookups() {
		// TODO Auto-generated method stub
		return (List<NichqTeacherQuestionLookup>) nichqTeacherQuestionLookupRepository.findAll();
	}

	@Override
	public void updateNichqTeacherQuestionLookup(NichqTeacherQuestionLookup nichqTeacherQuestionLookup) {
		// TODO Auto-generated method stub
		NichqTeacherQuestionLookup dbNichqTeacherQuestionLookup = nichqTeacherQuestionLookupRepository
				.findOne(nichqTeacherQuestionLookup.getId());
		if (dbNichqTeacherQuestionLookup == null) {
			throw new RuntimeException("NichqTeacherQuestionLookup Does not Exists!!");
		}

		dbNichqTeacherQuestionLookup.setName(nichqTeacherQuestionLookup.getName());
		dbNichqTeacherQuestionLookup
				.setNichqTeacherCategoryLookup(nichqTeacherQuestionLookup.getNichqTeacherCategoryLookup());
		dbNichqTeacherQuestionLookup
				.setNichqTeacherAnswerlookups(nichqTeacherQuestionLookup.getNichqTeacherAnswerlookups());
		nichqTeacherQuestionLookupRepository.save(dbNichqTeacherQuestionLookup);
	}

	@Override
	public void deleteNichqTeacherQuestionLookup(Long id) {
		// TODO Auto-generated method stub
		nichqTeacherQuestionLookupRepository.delete(id);
	}

	@Override
	public List<NichqTeacherQuestionLookup> getAllQuestionLookupsByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		NichqTeacherCategoryLookup dbNichqTeacherCategoryLookup = nichqTeacherCategorylookupRepository
				.findOne(categoryId);
		if (dbNichqTeacherCategoryLookup == null) {
			throw new RuntimeException("NichqTeacherCategoryLookup does not exists!!");
		}
		return nichqTeacherQuestionLookupRepository
				.findByNichqTeacherCategoryLookup_Id(dbNichqTeacherCategoryLookup.getId());
		
	}

	

}
