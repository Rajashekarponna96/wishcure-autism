package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientSpeechSubTest;
import com.openspace.Model.DoctorManagement.PatientSpeechTherapyTemplate;
import com.openspace.Model.DoctorManagement.PatientStandardScore;
import com.openspace.Model.DoctorManagement.PatientStandardScoreHeader;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientSpeechSubTestRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientSpeechTherapyTemplateRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientStandardScoreHeaderRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientStandardScoreRepository;
import com.openspace.Model.PatientMgnt.Repositories.SpeechTheraphyRepository;
import com.openspace.Model.Template.SpeechTherapyTemplate;
import com.openspace.Model.Template.StandardScore;

@Service
public class SpeechTheraphyService {

	@Autowired
	private SpeechTheraphyRepository speechTheraphyRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientSpeechTherapyTemplateRepository patientSpeechTherapyTemplateRepository;

	@Autowired
	private PatientStandardScoreHeaderRepository patientStandardScoreHeaderRepository;

	@Autowired
	private PatientSpeechSubTestRepository patientSpeechSubTestRepository;

	@Autowired
	private PatientStandardScoreRepository patientStandardScoreRepository;

	public void add(SpeechTherapyTemplate speechTherapyTemplate) {
		speechTheraphyRepository.save(speechTherapyTemplate);
	}

	public SpeechTherapyTemplate getTemplateByStatus(String status) {
		return speechTheraphyRepository.findByStatus(status);
	}

	public void assignTemplateToPatient(Long patientId, String evalutionStatus,
			List<SpeechTherapyTemplate> speechTemplates) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Doesn't Exist!");
		}
		List<PatientSpeechTherapyTemplate> list = new ArrayList<PatientSpeechTherapyTemplate>();
		if (speechTemplates.size() > 0) {
			for (SpeechTherapyTemplate speechTherapyTemplate : speechTemplates) {
				PatientSpeechTherapyTemplate dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
						.findByPatient_IdAndStatusAndEvalutionSheetStatus(dbPatient.getId(), speechTherapyTemplate.getStatus(),evalutionStatus);
				if (dbPatientSpeechTherapyTemplate == null) {
					PatientSpeechTherapyTemplate patientSpeechTherapyTemplate = convertSpeechTemplateToPatientSpeechTheraphyTemplate(
							dbPatient, evalutionStatus, speechTherapyTemplate);
					patientSpeechTherapyTemplate.setPresentDate(LocalDate.now());
					patientSpeechTherapyTemplateRepository.save(patientSpeechTherapyTemplate);
					list.add(patientSpeechTherapyTemplate);
				}
			}
		}

		dbPatient.setPatientSpeechTherapyTemplates(list);
		patientRepository.save(dbPatient);

	}

	public PatientSpeechTherapyTemplate convertSpeechTemplateToPatientSpeechTheraphyTemplate(Patient patient,
			String evalutionStatus, SpeechTherapyTemplate speechTherapyTemplate) {
		PatientSpeechTherapyTemplate patientSpeechTherapyTemplate = new PatientSpeechTherapyTemplate();
		List<PatientSpeechSubTest> patientSpeechsubTests = new ArrayList<PatientSpeechSubTest>();
		patientSpeechTherapyTemplate.setPatient(patient);
		if (speechTherapyTemplate.getStandardScores().size() > 0) {
			for (StandardScore standardScore : speechTherapyTemplate.getStandardScores()) {

				PatientStandardScoreHeader patientStandardScoreHeader = new PatientStandardScoreHeader();
				if (standardScore.getDescription() != null) {
					patientStandardScoreHeader.setDescription(standardScore.getDescription());
				}
				if (standardScore.getEnding() != null) {
					patientStandardScoreHeader.setEnding(standardScore.getEnding());
				}
				if (standardScore.getInitial() != null) {
					patientStandardScoreHeader.setInitial(standardScore.getInitial());
				}
				if (standardScore.getMedial() != null) {
					patientStandardScoreHeader.setMedial(standardScore.getMedial());
				}
				if (standardScore.getPercentaileRank() != null) {
					patientStandardScoreHeader.setPercentaileRank(standardScore.getPercentaileRank());
				}
				if (standardScore.getStandardScore() != null) {
					patientStandardScoreHeader.setStandardScore(standardScore.getStandardScore());
				}
				if (standardScore.getSubstitutions() != null) {
					patientStandardScoreHeader.setSubstitutions(standardScore.getSubstitutions());
				}
				if (standardScore.getSubtest() != null) {
					patientStandardScoreHeader.setSubtest(standardScore.getSubtest());
				}
				if (standardScore.getSummaryOfPerformance() != null) {
					patientStandardScoreHeader.setSummaryOfPerformance(standardScore.getSummaryOfPerformance());
				}
				if (standardScore.getTarget() != null) {
					patientStandardScoreHeader.setTarget(standardScore.getTarget());
				}
				if (standardScore.getWordLevel() != null) {
					patientStandardScoreHeader.setWordLevel(standardScore.getWordLevel());
				}
				patientStandardScoreHeaderRepository.save(patientStandardScoreHeader);
				patientSpeechTherapyTemplate.setPatientStandardScoreHeader(patientStandardScoreHeader);
			}
		}

		patientSpeechTherapyTemplate.setStatus(speechTherapyTemplate.getStatus());
		if (speechTherapyTemplate.getSubTests() != null) {
			if (speechTherapyTemplate.getSubTests().size() > 0) {
				for (String subTest : speechTherapyTemplate.getSubTests()) {
					PatientSpeechSubTest patientSpeechSubTest = new PatientSpeechSubTest();
					PatientStandardScore patientStandardScore = new PatientStandardScore();
					patientStandardScore.setPatientSpeechSubTest(patientSpeechSubTest);
					patientSpeechSubTest.setSubTestName(subTest);
					patientSpeechSubTest.setPatientSpeechStandardScore(patientStandardScore);
					patientSpeechSubTest.setPatientSpeechTherapyTemplate(patientSpeechTherapyTemplate);
					patientSpeechsubTests.add(patientSpeechSubTest);
				}
			}
		}
		if (evalutionStatus != null) {
			patientSpeechTherapyTemplate.setEvalutionSheetStatus(evalutionStatus);
		}
		patientSpeechTherapyTemplate.setPresentDate(LocalDate.now());
		patientSpeechTherapyTemplate.setPatientSpeechsubTests(patientSpeechsubTests);
		patientSpeechTherapyTemplate.setTemplateName(speechTherapyTemplate.getTemplateName());
		return patientSpeechTherapyTemplate;

	}

	public List<PatientSpeechTherapyTemplate> getPatientSpecchTemplatesByPatientInEvalution(Long patientId,String status) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Doesn't Exist!!");
		}
		return patientSpeechTherapyTemplateRepository.findByPatient_IdAndEvalutionSheetStatus(dbPatient.getId(), status);
	}

	public List<PatientSpeechTherapyTemplate> getPatientSpecchTemplatesByPatientInProgress(Long patientId,String tabdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Doesn't Exist!!");
		}
		return patientSpeechTherapyTemplateRepository.findByPatient_IdAndEvalutionSheetStatusAndPresentDate(dbPatient.getId(), "2",LocalDate.parse(tabdate, formatter));
	}

	public void deletePatientSpecchTemplatesByPatientAndStatus(Long patientId, String status, Long templateId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Doesn't Exist!!");
		}
		List<PatientSpeechTherapyTemplate> patientSpeechTherapyTemplateList=new ArrayList<PatientSpeechTherapyTemplate>();
		PatientSpeechTherapyTemplate patientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
				.findByPatient_IdAndEvalutionSheetStatusAndId(dbPatient.getId(), status,templateId);
		//for(PatientSpeechTherapyTemplate patientSpeechTherapyTemplate:  patientSpeechTherapyTemplates){
			dbPatient.setPatientSpeechTherapyTemplates(null);
			patientSpeechTherapyTemplate.setPatient(dbPatient);
			//patientSpeechTherapyTemplateList.add(patientSpeechTherapyTemplate);
	//	}
		patientSpeechTherapyTemplateRepository.delete(patientSpeechTherapyTemplate);
		
	}

}
