package com.openspace.PatientManagement.Scales;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.MentalReferenceProfile.MentalResult;
import com.openspace.Model.MentalReferenceProfile.PatientMentalScale;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategory;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Scales.VSMSQuestion;
import com.openspace.Model.ScalesRepositories.PatientVSMSQuestionRepository;
import com.openspace.Model.ScalesRepositories.VSMSQuestionRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.Model.VSMS.PatientVSMSQuestion;

@Service
public class VSMSQuestionsServiceImpl implements VSMSQuestionsService {
	@Autowired
	private VSMSQuestionRepository vsmsQuestionsRepository;
	
	@Autowired
	private PatientVSMSQuestionRepository patientVSMSQuestionRepository;
	
	@Autowired
	private PatientRepository patientRepository;


	@Override
	public void saveVSMSQuestions(VSMSQuestion vsmsQuestion) {
		
		VSMSQuestion dbVSMSQuestion = vsmsQuestionsRepository.findByQuestionName(vsmsQuestion.getQuestionName());
		if(dbVSMSQuestion !=null){
			throw new RuntimeException("VSMS Questions already exists!!");
		}
		else{
			vsmsQuestionsRepository.save(vsmsQuestion);
		}
	}

	@Override
	public List<VSMSQuestion> getAllVSMSQuestions() {
		return (List<VSMSQuestion>)vsmsQuestionsRepository.findAll();
	}

	@Override
	public void updateVSMSQuestions(VSMSQuestion vsmsQuestion) {
		
		VSMSQuestion dbVSMSQuestions = vsmsQuestionsRepository.findOne(vsmsQuestion.getId());
		if(dbVSMSQuestions == null){
			throw new RuntimeException("VSMS Question Not exists!!");
		}
		VSMSQuestion dbVSMSQuestions2 = vsmsQuestionsRepository.findByQuestionName(vsmsQuestion.getQuestionName());
		
		if (dbVSMSQuestions2 == null) {
			dbVSMSQuestions.setQuestionName(vsmsQuestion.getQuestionName());
			dbVSMSQuestions.setAgeInMonths(vsmsQuestion.getAgeInMonths());
			dbVSMSQuestions.setAgeInYears(vsmsQuestion.getAgeInYears());
			dbVSMSQuestions.setVsmsCluster(vsmsQuestion.getVsmsCluster());
		} else if (dbVSMSQuestions2.getId().equals(dbVSMSQuestions.getId())) {
			dbVSMSQuestions.setQuestionName(vsmsQuestion.getQuestionName());
			dbVSMSQuestions.setAgeInMonths(vsmsQuestion.getAgeInMonths());
			dbVSMSQuestions.setAgeInYears(vsmsQuestion.getAgeInYears());
			dbVSMSQuestions.setVsmsCluster(vsmsQuestion.getVsmsCluster());
		} else {
			throw new RuntimeException("VSMS Question Already Exist!");
		}
		dbVSMSQuestions.setQuestionName(vsmsQuestion.getQuestionName());
		dbVSMSQuestions.setAgeInMonths(vsmsQuestion.getAgeInMonths());
		dbVSMSQuestions.setAgeInYears(vsmsQuestion.getAgeInYears());
		dbVSMSQuestions.setVsmsCluster(vsmsQuestion.getVsmsCluster());
		vsmsQuestionsRepository.save(dbVSMSQuestions);
	}

	@Override
	public void deleteVSMSQuestions(Long id) {
		VSMSQuestion dbVSMSQuestions = vsmsQuestionsRepository.findOne(id);
		if(dbVSMSQuestions == null){
			throw new RuntimeException("VSMS Question Not exists!!");
		}
		else{
			vsmsQuestionsRepository.delete(id);
		}
	}

	@Override
	public void savePatientVSMSQuestion(List<PatientVSMSQuestion> patientVSMSQuestionList) {
		patientVSMSQuestionRepository.save(patientVSMSQuestionList);
	}

	@Override
	public List<PatientVSMSQuestion> getAllPatientVSMSQuestions(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		
		List<PatientVSMSQuestion> patientVSMSQuestionList = patientVSMSQuestionRepository
				.findByPatient_Id(patientId);
		return patientVSMSQuestionList;
	}

	@Override
	public List<VSMSQuestion> findByVSMSClusterId(Long id) {
		return vsmsQuestionsRepository.findByVsmsCluster_Id(id);
	}
	
	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<PatientVSMSQuestion> patientVSMSQuestionList = patientVSMSQuestionRepository.findByPatient_Id(patientId);
		for (PatientVSMSQuestion patientVSMSQuestion : patientVSMSQuestionList) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (patientVSMSQuestion.getDate() != null) {
				dates.add(patientVSMSQuestion.getDate().format(formatter));
			}
		}
		return dates;
	}
	
	
}
