package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.EvalutionSheet;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientSpeechSubTest;
import com.openspace.Model.DoctorManagement.PatientSpeechTherapyTemplate;
import com.openspace.Model.DoctorManagement.PatientStandardScore;
import com.openspace.Model.Dto.EvalutionSheetDto;
import com.openspace.Model.PatientMgnt.Repositories.EvalutionSheetRepository;
import com.openspace.Model.PatientMgnt.Repositories.EvalutionSheetResultRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientAnswerRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientQuestionCategoryRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientQuestionRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientSpeechSubTestRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientSpeechTherapyTemplateRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientStandardScoreRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.PatientManagement.dto.EvalutionSheetDateDto;
import com.openspace.PatientManagement.dto.ProgressSheetDateDto;

@Service
public class EvalutionSheetServiceImpl implements EvalutionSheetService {

	@Autowired
	private EvalutionSheetRepository evalutionSheetRepository;

	@Autowired
	private PatientQuestionCategoryRepository patientQuestionCategoryRepository;

	@Autowired
	private PatientQuestionRepository patientQuestionRepository;

	@Autowired
	private PatientAnswerRepository patientAnswerRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private EvalutionSheetService evalutionSheetService;

	@Autowired
	private EvalutionSheetResultRepository evalutionSheetResultRepository;

	@Autowired
	private PatientSpeechTherapyTemplateRepository patientSpeechTherapyTemplateRepository;

	@Autowired
	private PatientSpeechSubTestRepository patientSpeechSubTestRepository;

	@Autowired
	private PatientStandardScoreRepository patientStandardScoreRepository;

	@Override
	public void saveEvalutionSheet(EvalutionSheetDto evalutionSheetDto) {

		EvalutionSheet dbEvalutionSheet = evalutionSheetRepository.findByPatient_IdAndFlagAndStatus(
				evalutionSheetDto.getPatient().getId(), true, evalutionSheetDto.getStatus());
		if (dbEvalutionSheet != null) {
			dbEvalutionSheet.setBackgroundInformation(evalutionSheetDto.getBackgroundInformation());
			dbEvalutionSheet.setHistory(evalutionSheetDto.getHistory());
			dbEvalutionSheet.setObservation(evalutionSheetDto.getObservation());
			dbEvalutionSheet.setOralMotor(evalutionSheetDto.getOralMotor());
			dbEvalutionSheet.setPragmaticSkills(evalutionSheetDto.getPragmaticSkills());
			dbEvalutionSheet.setRecommandations(evalutionSheetDto.getRecommandations());
			dbEvalutionSheet.setSummary(evalutionSheetDto.getSummary());
			dbEvalutionSheet.setTestAdministered(evalutionSheetDto.getTestAdministered());
			dbEvalutionSheet.setParentalTips(evalutionSheetDto.getParentalTips());
			dbEvalutionSheet.setFlag(true);
			dbEvalutionSheet.setProgressDate(LocalDate.now());
			dbEvalutionSheet.setStatus(evalutionSheetDto.getStatus());
			dbEvalutionSheet.setDate(evalutionSheetDto.getDate());
			dbEvalutionSheet.setEvaluator(evalutionSheetDto.getEvaluator());
			dbEvalutionSheet.setServiceCoordinator(evalutionSheetDto.getServiceCoordinator());
			dbEvalutionSheet.setExpressiveLanguage(evalutionSheetDto.getExpressiveLanguage());
			dbEvalutionSheet.setReceptiveLanguage(evalutionSheetDto.getReceptiveLanguage());
			dbEvalutionSheet.setInterpretation(evalutionSheetDto.getInterpretation());
			Patient dbPatient = patientRepository.findOne(evalutionSheetDto.getPatient().getId());
			if (dbPatient == null) {
				throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
			}
			dbEvalutionSheet.setPatient(dbPatient);
			List<PatientSpeechTherapyTemplate> patientSpeechtemplateList = evalutionSheetDto
					.getPatientSpeechTherapyTemplates();
			if(patientSpeechtemplateList != null && patientSpeechtemplateList.size()>0){
			for (PatientSpeechTherapyTemplate pstt : patientSpeechtemplateList) {
				if (pstt != null) {
					PatientSpeechTherapyTemplate dbPatientSpeechTherapyTemplate=null;
					if(evalutionSheetDto.getEvalutionSheetStatus().equals("1")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndEvalutionSheetStatus(dbPatient.getId(), pstt.getStatus(),evalutionSheetDto.getEvalutionSheetStatus());
					}else if(evalutionSheetDto.getEvalutionSheetStatus().equals("2")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndPresentDate(dbPatient.getId(), pstt.getStatus(),LocalDate.now());
					}
					
					if (dbPatientSpeechTherapyTemplate != null) {
						if (pstt.getPatientSpeechsubTests() != null && pstt.getPatientSpeechsubTests().size() > 0) {
							List<PatientSpeechSubTest> patientSpeechSubTestsList=new ArrayList<PatientSpeechSubTest>();
							if(dbPatientSpeechTherapyTemplate.getPatientSpeechsubTests() != null && dbPatientSpeechTherapyTemplate.getPatientSpeechsubTests().size()>0){
								for (PatientSpeechSubTest dbpsst : dbPatientSpeechTherapyTemplate.getPatientSpeechsubTests()) {
								for (PatientSpeechSubTest fepsst : pstt.getPatientSpeechsubTests()) {
								
									if (dbpsst != null && fepsst != null) {
										if (fepsst.getId().equals(dbpsst.getId())) {
											dbpsst.setPatientSpeechTherapyTemplate(dbPatientSpeechTherapyTemplate);
											dbpsst.setSubTestName(dbpsst.getSubTestName());
											if (fepsst.getPatientSpeechStandardScore().getDescription() != null) {
												dbpsst.getPatientSpeechStandardScore().setDescription(
														fepsst.getPatientSpeechStandardScore().getDescription());
											}
											if (fepsst.getPatientSpeechStandardScore().getEnding() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setEnding(fepsst.getPatientSpeechStandardScore().getEnding());
											}
											if (fepsst.getPatientSpeechStandardScore().getInitial() != null) {
												dbpsst.getPatientSpeechStandardScore().setInitial(
														fepsst.getPatientSpeechStandardScore().getInitial());
											}
											if (fepsst.getPatientSpeechStandardScore().getMedial() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setMedial(fepsst.getPatientSpeechStandardScore().getMedial());
											}
											if (fepsst.getPatientSpeechStandardScore().getPercentaileRank() != null) {
												dbpsst.getPatientSpeechStandardScore().setPercentaileRank(
														fepsst.getPatientSpeechStandardScore().getPercentaileRank());
											}
											if (fepsst.getPatientSpeechStandardScore().getStandardScore() != null) {
												dbpsst.getPatientSpeechStandardScore().setStandardScore(
														fepsst.getPatientSpeechStandardScore().getStandardScore());

											}

											if (fepsst.getPatientSpeechStandardScore().getSubtest() != null) {
												dbpsst.getPatientSpeechStandardScore().setSubtest(
														fepsst.getPatientSpeechStandardScore().getSubtest());
											}
											if (fepsst.getPatientSpeechStandardScore()
													.getSummaryOfPerformance() != null) {
												dbpsst.getPatientSpeechStandardScore().setSummaryOfPerformance(fepsst
														.getPatientSpeechStandardScore().getSummaryOfPerformance());
											}

											if (fepsst.getPatientSpeechStandardScore().getWordLevel() != null) {
												dbpsst.getPatientSpeechStandardScore().setWordLevel(
														fepsst.getPatientSpeechStandardScore().getWordLevel());
											}
											dbpsst.getPatientSpeechStandardScore().setPatientSpeechSubTest(dbpsst);
											patientSpeechSubTestsList.add(dbpsst);
										}
									}
								}
							}
								for (PatientSpeechSubTest dbpsst1 : patientSpeechSubTestsList) {
									patientStandardScoreRepository.save(dbpsst1.getPatientSpeechStandardScore());
									patientSpeechSubTestRepository.save(dbpsst1);
									dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
									patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
								}
						}
						} else if (dbPatientSpeechTherapyTemplate.getPatientStandardScore() != null) {
							if (pstt.getPatientStandardScore() != null) {
							PatientStandardScore dbPatientStandardScore=	patientStandardScoreRepository.findOne(dbPatientSpeechTherapyTemplate.getPatientStandardScore().getId());
							if(dbPatientStandardScore != null){
							if (pstt.getPatientStandardScore().getDescription() != null) {
								dbPatientStandardScore.setDescription(pstt.getPatientStandardScore().getDescription());
								}
								if (pstt.getPatientStandardScore().getEnding() != null) {
									dbPatientStandardScore.setEnding(pstt.getPatientStandardScore().getEnding());
								}
								if (pstt.getPatientStandardScore().getInitial() != null) {
									dbPatientStandardScore.setInitial(pstt.getPatientStandardScore().getInitial());
								}
								if (pstt.getPatientStandardScore().getMedial() != null) {
									dbPatientStandardScore.setMedial(pstt.getPatientStandardScore().getMedial());
								}
								if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
									dbPatientStandardScore.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
								}
								if (pstt.getPatientStandardScore().getStandardScore() != null) {
									dbPatientStandardScore.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
								}

								if (pstt.getPatientStandardScore().getSubtest() != null) {
									dbPatientStandardScore.setSubtest(pstt.getPatientStandardScore().getSubtest());
								}
								if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
									dbPatientStandardScore.setSummaryOfPerformance(
											pstt.getPatientStandardScore().getSummaryOfPerformance());
								}
								if (pstt.getPatientStandardScore().getWordLevel() != null) {
									dbPatientStandardScore.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
								}
								patientStandardScoreRepository.save(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setPatientStandardScore(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
								patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
							}
							}
						} else {
							PatientStandardScore pss = new PatientStandardScore();
							if(pstt.getPatientStandardScore() != null){
							if (pstt.getPatientStandardScore().getDescription() != null) {
								pss.setDescription(pstt.getPatientStandardScore().getDescription());
							}
							if (pstt.getPatientStandardScore().getEnding() != null) {
								pss.setEnding(pstt.getPatientStandardScore().getEnding());
							}
							if (pstt.getPatientStandardScore().getInitial() != null) {
								pss.setInitial(pstt.getPatientStandardScore().getInitial());
							}
							if (pstt.getPatientStandardScore().getMedial() != null) {
								pss.setMedial(pstt.getPatientStandardScore().getMedial());
							}
							if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
								pss.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
							}
							if (pstt.getPatientStandardScore().getStandardScore() != null) {
								pss.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
							}
							if (pstt.getPatientStandardScore().getSubtest() != null) {
								pss.setSubtest(pstt.getPatientStandardScore().getSubtest());
							}
							if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
								pss.setSummaryOfPerformance(pstt.getPatientStandardScore().getSummaryOfPerformance());
							}
							if (pstt.getPatientStandardScore().getWordLevel() != null) {
								pss.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
							}
						}
							patientStandardScoreRepository.save(pss);
							dbPatientSpeechTherapyTemplate.setPatientStandardScore(pss);
							dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
							patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
						}
					}
				}

			}
		}
			dbEvalutionSheet.setPatient(evalutionSheetDto.getPatient());
			evalutionSheetRepository.save(dbEvalutionSheet);

		} else {
			EvalutionSheet evalutionSheet = new EvalutionSheet();
			evalutionSheet.setBackgroundInformation(evalutionSheetDto.getBackgroundInformation());
			evalutionSheet.setHistory(evalutionSheetDto.getHistory());
			evalutionSheet.setObservation(evalutionSheetDto.getObservation());
			evalutionSheet.setOralMotor(evalutionSheetDto.getOralMotor());
			evalutionSheet.setPragmaticSkills(evalutionSheetDto.getPragmaticSkills());
			evalutionSheet.setRecommandations(evalutionSheetDto.getRecommandations());
			evalutionSheet.setSummary(evalutionSheetDto.getSummary());
			evalutionSheet.setTestAdministered(evalutionSheetDto.getTestAdministered());
			evalutionSheet.setParentalTips(evalutionSheetDto.getParentalTips());
			evalutionSheet.setFlag(true);
			evalutionSheet.setProgressDate(LocalDate.now());
			evalutionSheet.setDate(evalutionSheetDto.getDate());
			evalutionSheet.setEvaluator(evalutionSheetDto.getEvaluator());
			evalutionSheet.setServiceCoordinator(evalutionSheetDto.getServiceCoordinator());
			evalutionSheet.setReceptiveLanguage(evalutionSheetDto.getReceptiveLanguage());
			evalutionSheet.setExpressiveLanguage(evalutionSheetDto.getExpressiveLanguage());
			Patient dbPatient = patientRepository.findOne(evalutionSheetDto.getPatient().getId());
			if (dbPatient == null) {
				throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
			}
			evalutionSheet.setPatient(dbPatient);
			List<PatientSpeechTherapyTemplate> patientSpeechtemplateList = evalutionSheetDto
					.getPatientSpeechTherapyTemplates();

			if(patientSpeechtemplateList != null && patientSpeechtemplateList.size()>0){
			for (PatientSpeechTherapyTemplate pstt : patientSpeechtemplateList) {
				if (pstt != null) {
					
					PatientSpeechTherapyTemplate dbPatientSpeechTherapyTemplate=null;
					if(evalutionSheetDto.getEvalutionSheetStatus().equals("1")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndEvalutionSheetStatus(dbPatient.getId(), pstt.getStatus(),evalutionSheetDto.getEvalutionSheetStatus());
					}else if(evalutionSheetDto.getEvalutionSheetStatus().equals("2")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndPresentDate(dbPatient.getId(), pstt.getStatus(),LocalDate.now());
					}
					if (dbPatientSpeechTherapyTemplate != null) {
						if (pstt.getPatientSpeechsubTests() != null && pstt.getPatientSpeechsubTests().size() > 0) {
							for (PatientSpeechSubTest dbpsst : dbPatientSpeechTherapyTemplate
									.getPatientSpeechsubTests()) {
								for (PatientSpeechSubTest fepsst : pstt.getPatientSpeechsubTests()) {
									if (dbpsst != null && fepsst != null) {
										if (fepsst.getId().equals(dbpsst.getId())) {
											dbpsst.setPatientSpeechTherapyTemplate(dbPatientSpeechTherapyTemplate);
											dbpsst.setSubTestName(dbpsst.getSubTestName());
											if (fepsst.getPatientSpeechStandardScore().getDescription() != null) {
												dbpsst.getPatientSpeechStandardScore().setDescription(
														fepsst.getPatientSpeechStandardScore().getDescription());
											}
											if (fepsst.getPatientSpeechStandardScore().getEnding() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setEnding(fepsst.getPatientSpeechStandardScore().getEnding());
											}
											if (fepsst.getPatientSpeechStandardScore().getInitial() != null) {
												dbpsst.getPatientSpeechStandardScore().setInitial(
														fepsst.getPatientSpeechStandardScore().getInitial());
											}
											if (fepsst.getPatientSpeechStandardScore().getMedial() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setMedial(fepsst.getPatientSpeechStandardScore().getMedial());
											}
											if (fepsst.getPatientSpeechStandardScore().getPercentaileRank() != null) {
												dbpsst.getPatientSpeechStandardScore().setPercentaileRank(
														fepsst.getPatientSpeechStandardScore().getPercentaileRank());
											}
											if (fepsst.getPatientSpeechStandardScore().getStandardScore() != null) {
												dbpsst.getPatientSpeechStandardScore().setStandardScore(
														fepsst.getPatientSpeechStandardScore().getStandardScore());

											}

											if (fepsst.getPatientSpeechStandardScore().getSubtest() != null) {
												dbpsst.getPatientSpeechStandardScore().setSubtest(
														fepsst.getPatientSpeechStandardScore().getSubtest());
											}
											if (fepsst.getPatientSpeechStandardScore()
													.getSummaryOfPerformance() != null) {
												dbpsst.getPatientSpeechStandardScore().setSummaryOfPerformance(fepsst
														.getPatientSpeechStandardScore().getSummaryOfPerformance());
											}

											if (fepsst.getPatientSpeechStandardScore().getWordLevel() != null) {
												dbpsst.getPatientSpeechStandardScore().setWordLevel(
														fepsst.getPatientSpeechStandardScore().getWordLevel());
											}
											dbpsst.getPatientSpeechStandardScore().setPatientSpeechSubTest(dbpsst);
											patientStandardScoreRepository.save(dbpsst.getPatientSpeechStandardScore());
											patientSpeechSubTestRepository.save(dbpsst);
										}
									}
								}
							}

						} else if (dbPatientSpeechTherapyTemplate.getPatientStandardScore() != null) {
							if (pstt.getPatientStandardScore() != null) {
							PatientStandardScore dbPatientStandardScore=	patientStandardScoreRepository.findOne(dbPatientSpeechTherapyTemplate.getPatientStandardScore().getId());
							if(dbPatientStandardScore != null){
							if (pstt.getPatientStandardScore().getDescription() != null) {
								dbPatientStandardScore.setDescription(pstt.getPatientStandardScore().getDescription());
								}
								if (pstt.getPatientStandardScore().getEnding() != null) {
									dbPatientStandardScore.setEnding(pstt.getPatientStandardScore().getEnding());
								}
								if (pstt.getPatientStandardScore().getInitial() != null) {
									dbPatientStandardScore.setInitial(pstt.getPatientStandardScore().getInitial());
								}
								if (pstt.getPatientStandardScore().getMedial() != null) {
									dbPatientStandardScore.setMedial(pstt.getPatientStandardScore().getMedial());
								}
								if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
									dbPatientStandardScore.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
								}
								if (pstt.getPatientStandardScore().getStandardScore() != null) {
									dbPatientStandardScore.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
								}

								if (pstt.getPatientStandardScore().getSubtest() != null) {
									dbPatientStandardScore.setSubtest(pstt.getPatientStandardScore().getSubtest());
								}
								if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
									dbPatientStandardScore.setSummaryOfPerformance(
											pstt.getPatientStandardScore().getSummaryOfPerformance());
								}
								if (pstt.getPatientStandardScore().getWordLevel() != null) {
									dbPatientStandardScore.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
								}
								patientStandardScoreRepository.save(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setPatientStandardScore(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
								patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
							}
							}
						} else {
							PatientStandardScore pss = new PatientStandardScore();
							if(pstt.getPatientStandardScore() != null){
							if (pstt.getPatientStandardScore().getDescription() != null) {
								pss.setDescription(pstt.getPatientStandardScore().getDescription());
							}
							if (pstt.getPatientStandardScore().getEnding() != null) {
								pss.setEnding(pstt.getPatientStandardScore().getEnding());
							}
							if (pstt.getPatientStandardScore().getInitial() != null) {
								pss.setInitial(pstt.getPatientStandardScore().getInitial());
							}
							if (pstt.getPatientStandardScore().getMedial() != null) {
								pss.setMedial(pstt.getPatientStandardScore().getMedial());
							}
							if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
								pss.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
							}
							if (pstt.getPatientStandardScore().getStandardScore() != null) {
								pss.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
							}
							if (pstt.getPatientStandardScore().getSubtest() != null) {
								pss.setSubtest(pstt.getPatientStandardScore().getSubtest());
							}
							if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
								pss.setSummaryOfPerformance(pstt.getPatientStandardScore().getSummaryOfPerformance());
							}
							if (pstt.getPatientStandardScore().getWordLevel() != null) {
								pss.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
							}
						}
							patientStandardScoreRepository.save(pss);
							dbPatientSpeechTherapyTemplate.setPatientStandardScore(pss);
							dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
							patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
						}
					}
				}

			}
		}
			evalutionSheet.setStatus(evalutionSheetDto.getStatus());
			evalutionSheet.setPatient(evalutionSheetDto.getPatient());
			evalutionSheetRepository.save(evalutionSheet);
		}
	
	}

	@Override
	public List<EvalutionSheet> getAllSheets() {
		// TODO Auto-generated method stub
		return (List<EvalutionSheet>) evalutionSheetRepository.findAll();
	}

	@Override
	public void updateEvalutionSheet(EvalutionSheet evalutionSheet) {

		evalutionSheetRepository.save(evalutionSheet);

	}

	@Override
	public void deleteEvalutionSheet(Long id) {
		evalutionSheetRepository.delete(id);
	}

	@Override
	public EvalutionSheet getEvaluationSheetByPatientIdStatus(Long patientId, String status) {
		EvalutionSheet dbEvalutionSheet = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, status);
		/*List<PatientQuestionCategory> patientQuestionCategories = patientQuestionCategoryRepository
				.findByPatient_IdAndStatus(patientId, "1");
		if (dbEvalutionSheet != null) {
			dbEvalutionSheet.setPatientQuestionCategories(patientQuestionCategories);
		}*/
		return dbEvalutionSheet;
	}

	@SuppressWarnings("unused")
	@Override
	public EvalutionSheet getEvaluationSheetByPatientIdStatusInProgress(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		EvalutionSheet result = null;
		/*List<PatientQuestionCategory> patientQuestionCategories = patientQuestionCategoryRepository
				.findByPatient_IdAndStatus(patientId, "2");*/
		result = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, "2");
		/*if (result != null) {
			result.setPatientQuestionCategories(patientQuestionCategories);
		}*/

		if (result == null) {
			/*patientQuestionCategories = patientQuestionCategoryRepository.findByPatient_IdAndStatus(patientId, "1");*/
			result = evalutionSheetRepository.findByPatient_IdAndStatusAndProgressDate(patientId, "1",LocalDate.now());
			/*if (result != null) {
				result.setPatientQuestionCategories(patientQuestionCategories);
			}*/
		}
		return result;
	}

	@Override
	public EvalutionSheet getEvaluationSheetByPatientIdStatusInExit(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		EvalutionSheet result = null;
		/*List<PatientQuestionCategory> patientQuestionCategories = patientQuestionCategoryRepository
				.findByPatient_IdAndStatus(patientId, "3");*/
		result = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, "3");
		/*if (result != null) {
			result.setPatientQuestionCategories(patientQuestionCategories);
		}*/
		/*if (result == null) {
			patientQuestionCategories = patientQuestionCategoryRepository.findByPatient_IdAndStatus(patientId, "2");
			result = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, "2");
			if (result != null) {
				result.setPatientQuestionCategories(patientQuestionCategories);
			}
		}*/
		return result;
	}

	public EvalutionSheet getEvaluationSheetByPatientIdStatusInEvaluationReport(Long patientId) {
		//EvalutionSheet dbEvalutionSheet = evalutionSheetRepository.findByPatient_IdAndStatusAndProgressDate(patientId, "1",LocalDate.now());
		EvalutionSheet dbEvalutionSheet = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, "1");
		/*List<PatientQuestionCategory> patientQuestionCategories = patientQuestionCategoryRepository
				.findByPatient_IdAndStatus(patientId, "1");
		if (dbEvalutionSheet != null) {
			dbEvalutionSheet.setPatientQuestionCategories(patientQuestionCategories);
		}*/

		return dbEvalutionSheet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EvalutionSheet getEvaluationSheetByPatientIdStatusInProgressReport(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		EvalutionSheet result = null;
		/*List<PatientQuestionCategory> patientQuestionCategories = patientQuestionCategoryRepository
				.findByPatient_IdAndStatus(patientId, "2");*/
		result = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, "2");
		/*if (result != null) {
			result.setPatientQuestionCategories(patientQuestionCategories);
		}*/

		if (result == null) {
			/*patientQuestionCategories = patientQuestionCategoryRepository.findByPatient_IdAndStatus(patientId, "1");*/
			result = evalutionSheetRepository.findByPatient_IdAndStatusAndProgressDate(patientId, "1",LocalDate.now());
			/*if (result != null) {
				result.setPatientQuestionCategories(patientQuestionCategories);
			}*/
		}
		return result;
	}

	public EvalutionSheet getEvaluationSheetByPatientIdStatusInExitNoteReport(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		EvalutionSheet result = null;
		/*List<PatientQuestionCategory> patientQuestionCategories = patientQuestionCategoryRepository
				.findByPatient_IdAndStatus(patientId, "3");*/
		result = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, "3");
		/*if (result != null) {
			result.setPatientQuestionCategories(patientQuestionCategories);
		}*/
		if (result == null) {
			/*patientQuestionCategories = patientQuestionCategoryRepository.findByPatient_IdAndStatus(patientId, "2");*/
			result = evalutionSheetRepository.findByPatient_IdAndStatus(patientId, "2");
			/*if (result != null) {
				result.setPatientQuestionCategories(patientQuestionCategories);
			}*/
		}
		return result;
	}

	@Override
	public void savePatientEvalutionSheet(EvalutionSheetDto evalutionSheetDto) {
		EvalutionSheet dbEvalutionSheet = evalutionSheetRepository.findByPatient_IdAndFlagAndStatusAndProgressDate(
				evalutionSheetDto.getPatient().getId(), true, evalutionSheetDto.getStatus(), LocalDate.now());
		if (dbEvalutionSheet != null) {
			dbEvalutionSheet.setBackgroundInformation(evalutionSheetDto.getBackgroundInformation());
			dbEvalutionSheet.setHistory(evalutionSheetDto.getHistory());
			dbEvalutionSheet.setObservation(evalutionSheetDto.getObservation());
			dbEvalutionSheet.setOralMotor(evalutionSheetDto.getOralMotor());
			dbEvalutionSheet.setPragmaticSkills(evalutionSheetDto.getPragmaticSkills());
			dbEvalutionSheet.setRecommandations(evalutionSheetDto.getRecommandations());
			dbEvalutionSheet.setSummary(evalutionSheetDto.getSummary());
			dbEvalutionSheet.setTestAdministered(evalutionSheetDto.getTestAdministered());
			dbEvalutionSheet.setParentalTips(evalutionSheetDto.getParentalTips());
			dbEvalutionSheet.setFlag(true);
			dbEvalutionSheet.setProgressDate(LocalDate.now());
			dbEvalutionSheet.setStatus(evalutionSheetDto.getStatus());
			dbEvalutionSheet.setDate(evalutionSheetDto.getDate());
			dbEvalutionSheet.setEvaluator(evalutionSheetDto.getEvaluator());
			dbEvalutionSheet.setServiceCoordinator(evalutionSheetDto.getServiceCoordinator());
			dbEvalutionSheet.setExpressiveLanguage(evalutionSheetDto.getExpressiveLanguage());
			dbEvalutionSheet.setReceptiveLanguage(evalutionSheetDto.getReceptiveLanguage());
			Patient dbPatient = patientRepository.findOne(evalutionSheetDto.getPatient().getId());
			if (dbPatient == null) {
				throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
			}
			dbEvalutionSheet.setPatient(dbPatient);
			List<PatientSpeechTherapyTemplate> patientSpeechtemplateList = evalutionSheetDto
					.getPatientSpeechTherapyTemplates();

			if(patientSpeechtemplateList != null && patientSpeechtemplateList.size()>0){
			for (PatientSpeechTherapyTemplate pstt : patientSpeechtemplateList) {
				if (pstt != null) {
					PatientSpeechTherapyTemplate dbPatientSpeechTherapyTemplate=null;
					if(evalutionSheetDto.getEvalutionSheetStatus().equals("1")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndEvalutionSheetStatus(dbPatient.getId(), pstt.getStatus(),evalutionSheetDto.getEvalutionSheetStatus());
					}else if(evalutionSheetDto.getEvalutionSheetStatus().equals("2")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndPresentDateAndEvalutionSheetStatus(dbPatient.getId(), pstt.getStatus(),LocalDate.now(),evalutionSheetDto.getEvalutionSheetStatus());
					}
					
					if (dbPatientSpeechTherapyTemplate != null) {
						if (pstt.getPatientSpeechsubTests() != null && pstt.getPatientSpeechsubTests().size() > 0) {
							List<PatientSpeechSubTest> patientSpeechSubTestsList=new ArrayList<PatientSpeechSubTest>();
							if(dbPatientSpeechTherapyTemplate.getPatientSpeechsubTests() != null && dbPatientSpeechTherapyTemplate.getPatientSpeechsubTests().size()>0){
							for (PatientSpeechSubTest dbpsst : dbPatientSpeechTherapyTemplate.getPatientSpeechsubTests()) {
								for (PatientSpeechSubTest fepsst : pstt.getPatientSpeechsubTests()) {
									if (dbpsst != null && fepsst != null) {
										if (fepsst.getId().equals(dbpsst.getId())) {
											dbpsst.setPatientSpeechTherapyTemplate(dbPatientSpeechTherapyTemplate);
											dbpsst.setSubTestName(dbpsst.getSubTestName());
											if (fepsst.getPatientSpeechStandardScore().getDescription() != null) {
												dbpsst.getPatientSpeechStandardScore().setDescription(
														fepsst.getPatientSpeechStandardScore().getDescription());
											}
											if (fepsst.getPatientSpeechStandardScore().getEnding() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setEnding(fepsst.getPatientSpeechStandardScore().getEnding());
											}
											if (fepsst.getPatientSpeechStandardScore().getInitial() != null) {
												dbpsst.getPatientSpeechStandardScore().setInitial(
														fepsst.getPatientSpeechStandardScore().getInitial());
											}
											if (fepsst.getPatientSpeechStandardScore().getMedial() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setMedial(fepsst.getPatientSpeechStandardScore().getMedial());
											}
											if (fepsst.getPatientSpeechStandardScore().getPercentaileRank() != null) {
												dbpsst.getPatientSpeechStandardScore().setPercentaileRank(
														fepsst.getPatientSpeechStandardScore().getPercentaileRank());
											}
											if (fepsst.getPatientSpeechStandardScore().getStandardScore() != null) {
												dbpsst.getPatientSpeechStandardScore().setStandardScore(
														fepsst.getPatientSpeechStandardScore().getStandardScore());

											}

											if (fepsst.getPatientSpeechStandardScore().getSubtest() != null) {
												dbpsst.getPatientSpeechStandardScore().setSubtest(
														fepsst.getPatientSpeechStandardScore().getSubtest());
											}
											if (fepsst.getPatientSpeechStandardScore()
													.getSummaryOfPerformance() != null) {
												dbpsst.getPatientSpeechStandardScore().setSummaryOfPerformance(fepsst
														.getPatientSpeechStandardScore().getSummaryOfPerformance());
											}

											if (fepsst.getPatientSpeechStandardScore().getWordLevel() != null) {
												dbpsst.getPatientSpeechStandardScore().setWordLevel(
														fepsst.getPatientSpeechStandardScore().getWordLevel());
											}
											dbpsst.getPatientSpeechStandardScore().setPatientSpeechSubTest(dbpsst);
											patientSpeechSubTestsList.add(dbpsst);
											
										}
									}
								}
							}
							for (PatientSpeechSubTest dbpsst1 : patientSpeechSubTestsList) {
								patientStandardScoreRepository.save(dbpsst1.getPatientSpeechStandardScore());
								patientSpeechSubTestRepository.save(dbpsst1);
								dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
								patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
							}
						}

						} else if (dbPatientSpeechTherapyTemplate.getPatientStandardScore() != null) {
							if (pstt.getPatientStandardScore() != null) {
							PatientStandardScore dbPatientStandardScore=	patientStandardScoreRepository.findOne(dbPatientSpeechTherapyTemplate.getPatientStandardScore().getId());
							if(dbPatientStandardScore != null){
							if (pstt.getPatientStandardScore().getDescription() != null) {
								dbPatientStandardScore.setDescription(pstt.getPatientStandardScore().getDescription());
								}
								if (pstt.getPatientStandardScore().getEnding() != null) {
									dbPatientStandardScore.setEnding(pstt.getPatientStandardScore().getEnding());
								}
								if (pstt.getPatientStandardScore().getInitial() != null) {
									dbPatientStandardScore.setInitial(pstt.getPatientStandardScore().getInitial());
								}
								if (pstt.getPatientStandardScore().getMedial() != null) {
									dbPatientStandardScore.setMedial(pstt.getPatientStandardScore().getMedial());
								}
								if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
									dbPatientStandardScore.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
								}
								if (pstt.getPatientStandardScore().getStandardScore() != null) {
									dbPatientStandardScore.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
								}

								if (pstt.getPatientStandardScore().getSubtest() != null) {
									dbPatientStandardScore.setSubtest(pstt.getPatientStandardScore().getSubtest());
								}
								if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
									dbPatientStandardScore.setSummaryOfPerformance(
											pstt.getPatientStandardScore().getSummaryOfPerformance());
								}
								if (pstt.getPatientStandardScore().getWordLevel() != null) {
									dbPatientStandardScore.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
								}
								patientStandardScoreRepository.save(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setPatientStandardScore(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
								patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
							}
							}
						} else {
							PatientStandardScore pss = new PatientStandardScore();
							if (pstt.getPatientStandardScore().getDescription() != null) {
								pss.setDescription(pstt.getPatientStandardScore().getDescription());
							}
							if (pstt.getPatientStandardScore().getEnding() != null) {
								pss.setEnding(pstt.getPatientStandardScore().getEnding());
							}
							if (pstt.getPatientStandardScore().getInitial() != null) {
								pss.setInitial(pstt.getPatientStandardScore().getInitial());
							}
							if (pstt.getPatientStandardScore().getMedial() != null) {
								pss.setMedial(pstt.getPatientStandardScore().getMedial());
							}
							if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
								pss.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
							}
							if (pstt.getPatientStandardScore().getStandardScore() != null) {
								pss.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
							}
							if (pstt.getPatientStandardScore().getSubtest() != null) {
								pss.setSubtest(pstt.getPatientStandardScore().getSubtest());
							}
							if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
								pss.setSummaryOfPerformance(pstt.getPatientStandardScore().getSummaryOfPerformance());
							}
							if (pstt.getPatientStandardScore().getWordLevel() != null) {
								pss.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
							}
							patientStandardScoreRepository.save(pss);
							dbPatientSpeechTherapyTemplate.setPatientStandardScore(pss);
							dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
							patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
						}
					}
				}

			}
		}
			// List<PatientQuestionCategory> patientQuestionCategories=
			// patientQuestionCategoryRepository.findByPatient_IdAndStatus(evalutionSheetDto.getPatient().getId(),evalutionSheetDto.getStatus());

			/*
			 * List<PatientQuestionCategory> patientQuestionCategoriesList =
			 * evalutionSheetDto .getPatientQuestionCategories(); for
			 * (PatientQuestionCategory patientQuestionCategory :
			 * patientQuestionCategoriesList) {
			 * patientQuestionCategoryRepository.save(patientQuestionCategory);
			 * List<PatientQuestion> patientQuestions =
			 * patientQuestionCategory.getPatientQuestions(); for
			 * (PatientQuestion patientQuestion : patientQuestions) {
			 * patientQuestion.setPatientQuestionCategory(
			 * patientQuestionCategory);
			 * patientQuestionRepository.save(patientQuestion);
			 * List<PatientAnswer> patientAnswers =
			 * patientQuestion.getPatientAnswers(); for (PatientAnswer
			 * patientAnswer : patientAnswers) {
			 * patientAnswer.setDescription(patientAnswer.getDescription());
			 * patientAnswer.setShortAnswer(patientAnswer.getShortAnswer());
			 * patientAnswer.setSelectedAnswer(patientAnswer.getSelectedAnswer()
			 * ); patientAnswer.setPatientQuestion(patientQuestion);
			 * patientAnswerRepository.save(patientAnswer); } } }
			 * dbEvalutionSheet.setPatientQuestionCategories(
			 * patientQuestionCategoriesList);
			 */
			dbEvalutionSheet.setPatient(evalutionSheetDto.getPatient());
			evalutionSheetRepository.save(dbEvalutionSheet);

		} else {
			EvalutionSheet evalutionSheet = new EvalutionSheet();
			evalutionSheet.setBackgroundInformation(evalutionSheetDto.getBackgroundInformation());
			evalutionSheet.setHistory(evalutionSheetDto.getHistory());
			evalutionSheet.setObservation(evalutionSheetDto.getObservation());
			evalutionSheet.setOralMotor(evalutionSheetDto.getOralMotor());
			evalutionSheet.setPragmaticSkills(evalutionSheetDto.getPragmaticSkills());
			evalutionSheet.setRecommandations(evalutionSheetDto.getRecommandations());
			evalutionSheet.setSummary(evalutionSheetDto.getSummary());
			evalutionSheet.setTestAdministered(evalutionSheetDto.getTestAdministered());
			evalutionSheet.setParentalTips(evalutionSheetDto.getParentalTips());
			evalutionSheet.setFlag(true);
			evalutionSheet.setProgressDate(LocalDate.now());
			evalutionSheet.setDate(evalutionSheetDto.getDate());
			evalutionSheet.setEvaluator(evalutionSheetDto.getEvaluator());
			evalutionSheet.setServiceCoordinator(evalutionSheetDto.getServiceCoordinator());
			evalutionSheet.setReceptiveLanguage(evalutionSheetDto.getReceptiveLanguage());
			evalutionSheet.setExpressiveLanguage(evalutionSheetDto.getExpressiveLanguage());
			Patient dbPatient = patientRepository.findOne(evalutionSheetDto.getPatient().getId());
			if (dbPatient == null) {
				throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
			}
			evalutionSheet.setPatient(dbPatient);
			List<PatientSpeechTherapyTemplate> patientSpeechtemplateList = evalutionSheetDto
					.getPatientSpeechTherapyTemplates();

			if(patientSpeechtemplateList != null && patientSpeechtemplateList.size()>0){
			for (PatientSpeechTherapyTemplate pstt : patientSpeechtemplateList) {
				if (pstt != null) {
					
					PatientSpeechTherapyTemplate dbPatientSpeechTherapyTemplate=null;
					if(evalutionSheetDto.getEvalutionSheetStatus().equals("1")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndEvalutionSheetStatus(dbPatient.getId(), pstt.getStatus(),evalutionSheetDto.getEvalutionSheetStatus());
					}else if(evalutionSheetDto.getEvalutionSheetStatus().equals("2")){
						 dbPatientSpeechTherapyTemplate = patientSpeechTherapyTemplateRepository
								.findByPatient_IdAndStatusAndPresentDate(dbPatient.getId(), pstt.getStatus(),LocalDate.now());
					}
					if (dbPatientSpeechTherapyTemplate != null) {
						if (pstt.getPatientSpeechsubTests() != null && pstt.getPatientSpeechsubTests().size() > 0) {
							for (PatientSpeechSubTest dbpsst : dbPatientSpeechTherapyTemplate
									.getPatientSpeechsubTests()) {
								for (PatientSpeechSubTest fepsst : pstt.getPatientSpeechsubTests()) {
									if (dbpsst != null && fepsst != null) {
										if (fepsst.getId().equals(dbpsst.getId())) {
											dbpsst.setPatientSpeechTherapyTemplate(dbPatientSpeechTherapyTemplate);
											dbpsst.setSubTestName(dbpsst.getSubTestName());
											if (fepsst.getPatientSpeechStandardScore().getDescription() != null) {
												dbpsst.getPatientSpeechStandardScore().setDescription(
														fepsst.getPatientSpeechStandardScore().getDescription());
											}
											if (fepsst.getPatientSpeechStandardScore().getEnding() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setEnding(fepsst.getPatientSpeechStandardScore().getEnding());
											}
											if (fepsst.getPatientSpeechStandardScore().getInitial() != null) {
												dbpsst.getPatientSpeechStandardScore().setInitial(
														fepsst.getPatientSpeechStandardScore().getInitial());
											}
											if (fepsst.getPatientSpeechStandardScore().getMedial() != null) {
												dbpsst.getPatientSpeechStandardScore()
														.setMedial(fepsst.getPatientSpeechStandardScore().getMedial());
											}
											if (fepsst.getPatientSpeechStandardScore().getPercentaileRank() != null) {
												dbpsst.getPatientSpeechStandardScore().setPercentaileRank(
														fepsst.getPatientSpeechStandardScore().getPercentaileRank());
											}
											if (fepsst.getPatientSpeechStandardScore().getStandardScore() != null) {
												dbpsst.getPatientSpeechStandardScore().setStandardScore(
														fepsst.getPatientSpeechStandardScore().getStandardScore());

											}

											if (fepsst.getPatientSpeechStandardScore().getSubtest() != null) {
												dbpsst.getPatientSpeechStandardScore().setSubtest(
														fepsst.getPatientSpeechStandardScore().getSubtest());
											}
											if (fepsst.getPatientSpeechStandardScore()
													.getSummaryOfPerformance() != null) {
												dbpsst.getPatientSpeechStandardScore().setSummaryOfPerformance(fepsst
														.getPatientSpeechStandardScore().getSummaryOfPerformance());
											}

											if (fepsst.getPatientSpeechStandardScore().getWordLevel() != null) {
												dbpsst.getPatientSpeechStandardScore().setWordLevel(
														fepsst.getPatientSpeechStandardScore().getWordLevel());
											}
											dbpsst.getPatientSpeechStandardScore().setPatientSpeechSubTest(dbpsst);
											patientStandardScoreRepository.save(dbpsst.getPatientSpeechStandardScore());
											patientSpeechSubTestRepository.save(dbpsst);
										}
									}
								}
							}

						} else if (dbPatientSpeechTherapyTemplate.getPatientStandardScore() != null) {
							if (pstt.getPatientStandardScore() != null) {
							PatientStandardScore dbPatientStandardScore=	patientStandardScoreRepository.findOne(dbPatientSpeechTherapyTemplate.getPatientStandardScore().getId());
							if(dbPatientStandardScore != null){
							if (pstt.getPatientStandardScore().getDescription() != null) {
								dbPatientStandardScore.setDescription(pstt.getPatientStandardScore().getDescription());
								}
								if (pstt.getPatientStandardScore().getEnding() != null) {
									dbPatientStandardScore.setEnding(pstt.getPatientStandardScore().getEnding());
								}
								if (pstt.getPatientStandardScore().getInitial() != null) {
									dbPatientStandardScore.setInitial(pstt.getPatientStandardScore().getInitial());
								}
								if (pstt.getPatientStandardScore().getMedial() != null) {
									dbPatientStandardScore.setMedial(pstt.getPatientStandardScore().getMedial());
								}
								if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
									dbPatientStandardScore.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
								}
								if (pstt.getPatientStandardScore().getStandardScore() != null) {
									dbPatientStandardScore.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
								}

								if (pstt.getPatientStandardScore().getSubtest() != null) {
									dbPatientStandardScore.setSubtest(pstt.getPatientStandardScore().getSubtest());
								}
								if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
									dbPatientStandardScore.setSummaryOfPerformance(
											pstt.getPatientStandardScore().getSummaryOfPerformance());
								}
								if (pstt.getPatientStandardScore().getWordLevel() != null) {
									dbPatientStandardScore.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
								}
								patientStandardScoreRepository.save(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setPatientStandardScore(dbPatientStandardScore);
								dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
								patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
							}
							}
						} else {
							PatientStandardScore pss = new PatientStandardScore();
							if (pstt.getPatientStandardScore().getDescription() != null) {
								pss.setDescription(pstt.getPatientStandardScore().getDescription());
							}
							if (pstt.getPatientStandardScore().getEnding() != null) {
								pss.setEnding(pstt.getPatientStandardScore().getEnding());
							}
							if (pstt.getPatientStandardScore().getInitial() != null) {
								pss.setInitial(pstt.getPatientStandardScore().getInitial());
							}
							if (pstt.getPatientStandardScore().getMedial() != null) {
								pss.setMedial(pstt.getPatientStandardScore().getMedial());
							}
							if (pstt.getPatientStandardScore().getPercentaileRank() != null) {
								pss.setPercentaileRank(pstt.getPatientStandardScore().getPercentaileRank());
							}
							if (pstt.getPatientStandardScore().getStandardScore() != null) {
								pss.setStandardScore(pstt.getPatientStandardScore().getStandardScore());
							}
							if (pstt.getPatientStandardScore().getSubtest() != null) {
								pss.setSubtest(pstt.getPatientStandardScore().getSubtest());
							}
							if (pstt.getPatientStandardScore().getSummaryOfPerformance() != null) {
								pss.setSummaryOfPerformance(pstt.getPatientStandardScore().getSummaryOfPerformance());
							}
							if (pstt.getPatientStandardScore().getWordLevel() != null) {
								pss.setWordLevel(pstt.getPatientStandardScore().getWordLevel());
							}
							patientStandardScoreRepository.save(pss);
							dbPatientSpeechTherapyTemplate.setPatientStandardScore(pss);
							dbPatientSpeechTherapyTemplate.setEvalutionSheet(dbEvalutionSheet);
							patientSpeechTherapyTemplateRepository.save(dbPatientSpeechTherapyTemplate);
						}
					}
				}

			}
		}
			
/*
			EvalutionSheetResult evalutionSheetResult1 = new EvalutionSheetResult();
			if (evalutionSheetDto.getEvalutionSheetResult() != null) {
				evalutionSheetResult1.setAuditotyAge(evalutionSheetDto.getEvalutionSheetResult().getAuditotyAge());
				evalutionSheetResult1
						.setAuditotyCategoryName(evalutionSheetDto.getEvalutionSheetResult().getAuditotyCategoryName());
				evalutionSheetResult1.setAuditotyDelay(evalutionSheetDto.getEvalutionSheetResult().getAuditotyDelay());
				evalutionSheetResult1
						.setAuditotyPercentile(evalutionSheetDto.getEvalutionSheetResult().getAuditotyPercentile());
				evalutionSheetResult1.setAuditotyStandardScore(
						evalutionSheetDto.getEvalutionSheetResult().getAuditotyStandardScore());
				evalutionSheetResult1.setExpressiveAge(evalutionSheetDto.getEvalutionSheetResult().getExpressiveAge());
				evalutionSheetResult1.setExpressiveCategoryName(
						evalutionSheetDto.getEvalutionSheetResult().getExpressiveCategoryName());
				evalutionSheetResult1
						.setExpressiveDelay(evalutionSheetDto.getEvalutionSheetResult().getExpressiveDelay());
				evalutionSheetResult1
						.setExpressivePercentile(evalutionSheetDto.getEvalutionSheetResult().getExpressivePercentile());
				evalutionSheetResult1.setExpressiveStandardScore(
						evalutionSheetDto.getEvalutionSheetResult().getExpressiveStandardScore());
				evalutionSheetResultRepository.save(evalutionSheetResult1);
			}

			evalutionSheet.setEvalutionSheetResult(evalutionSheetResult1);*/
			evalutionSheet.setStatus(evalutionSheetDto.getStatus());
			
			/*
			 * List<QuestionCategoryDto> questionCategoriesList =
			 * evalutionSheetDto.getQuestionCategoriesDtos(); if
			 * (questionCategoriesList != null) { List<PatientQuestionCategory>
			 * patientQuestionCategoriesList = new
			 * ArrayList<PatientQuestionCategory>(); for (QuestionCategoryDto
			 * questionCategoryDto : questionCategoriesList) {
			 * PatientQuestionCategory patientQuestionCategory = new
			 * PatientQuestionCategory();
			 * patientQuestionCategory.setDisplayOrder(questionCategoryDto.
			 * getDisplayOrder());
			 * patientQuestionCategory.setName(questionCategoryDto.getName());
			 * patientQuestionCategory.setPatient(dbPatient);
			 * patientQuestionCategory.setStatus(evalutionSheetDto.getStatus());
			 * patientQuestionCategoryRepository.save(patientQuestionCategory);
			 * List<QuestionDto> questionDtos =
			 * questionCategoryDto.getQuestionDtos(); List<PatientQuestion>
			 * patientQuestions = new ArrayList<PatientQuestion>(); for
			 * (QuestionDto questionDto : questionDtos) { PatientQuestion
			 * patientQuestion = new PatientQuestion();
			 * patientQuestion.setQuestionName(questionDto.getQuestionName());
			 * patientQuestion.setPatientQuestionCategory(
			 * patientQuestionCategory);
			 * patientQuestionRepository.save(patientQuestion); List<AnswerDto>
			 * answerDtos = questionDto.getAnswerDtos(); List<PatientAnswer>
			 * patientAnswers = new ArrayList<PatientAnswer>(); for (AnswerDto
			 * answerDto : answerDtos) { PatientAnswer patientAnswer = new
			 * PatientAnswer();
			 * patientAnswer.setDescription(answerDto.getDescription());
			 * patientAnswer.setShortAnswer(answerDto.getShortAnswer());
			 * patientAnswer.setSelectedAnswer(answerDto.getSelectedAnswer());
			 * patientAnswer.setPatientQuestion(patientQuestion);
			 * patientAnswerRepository.save(patientAnswer);
			 * patientAnswers.add(patientAnswer); }
			 * patientQuestions.add(patientQuestion); }
			 * patientQuestionCategoriesList.add(patientQuestionCategory); }
			 * evalutionSheet.setPatientQuestionCategories(
			 * patientQuestionCategoriesList); } else {
			 * 
			 * // here new is created but same evaluation data is saved as //
			 * second record if any changes in ui doesn't reflected to data //
			 * base // List<PatientQuestionCategory>
			 * dbpatientQuestionCategoryList = //
			 * patientQuestionCategoryRepository.findByPatient_IdAndStatus(
			 * evalutionSheetDto.getPatient().getId(), //
			 * evalutionSheetDto.getStatus());
			 * 
			 * List<PatientQuestionCategory> patientQuestionCategoriesList =
			 * evalutionSheetDto .getPatientQuestionCategories();
			 * List<PatientQuestionCategory> patientQuestionCategoryList = new
			 * ArrayList<PatientQuestionCategory>(); for
			 * (PatientQuestionCategory patientQuestionCategory :
			 * patientQuestionCategoriesList) { PatientQuestionCategory
			 * patientQuestionCategory1 = new PatientQuestionCategory();
			 * patientQuestionCategory1.setDisplayOrder(patientQuestionCategory.
			 * getDisplayOrder());
			 * patientQuestionCategory1.setName(patientQuestionCategory.getName(
			 * )); patientQuestionCategory1.setPatient(dbPatient);
			 * patientQuestionCategory1.setStatus(evalutionSheet.getStatus());
			 * patientQuestionCategoryRepository.save(patientQuestionCategory1);
			 * 
			 * List<PatientQuestion> patientQuestions =
			 * patientQuestionCategory.getPatientQuestions();
			 * List<PatientQuestion> patientQuestionsList = new
			 * ArrayList<PatientQuestion>(); for (PatientQuestion
			 * patientQuestion : patientQuestions) { PatientQuestion
			 * patientQuestion1 = new PatientQuestion();
			 * patientQuestion1.setQuestionName(patientQuestion.getQuestionName(
			 * )); patientQuestion1.setPatientQuestionCategory(
			 * patientQuestionCategory1);
			 * patientQuestionRepository.save(patientQuestion1);
			 * List<PatientAnswer> patientAnswersList = new
			 * ArrayList<PatientAnswer>(); List<PatientAnswer> patientAnswers =
			 * patientQuestion.getPatientAnswers(); for (PatientAnswer
			 * patientAnswer : patientAnswers) { PatientAnswer patientAnswer1 =
			 * new PatientAnswer();
			 * patientAnswer1.setDescription(patientAnswer.getDescription());
			 * patientAnswer1.setShortAnswer(patientAnswer.getShortAnswer());
			 * patientAnswer1.setSelectedAnswer(patientAnswer.getSelectedAnswer(
			 * )); patientAnswer1.setPatientQuestion(patientQuestion1);
			 * patientAnswerRepository.save(patientAnswer1);
			 * patientAnswersList.add(patientAnswer1); }
			 * patientQuestionsList.add(patientQuestion1); }
			 * patientQuestionCategoryList.add(patientQuestionCategory1);
			 * 
			 * } evalutionSheet.setPatientQuestionCategories(
			 * patientQuestionCategoryList);
			 * 
			 * }
			 */
			evalutionSheet.setPatient(evalutionSheetDto.getPatient());
			evalutionSheetRepository.save(evalutionSheet);
			/*EvalutionSheetResult evalutionSheetResult = evalutionSheetResultRepository
					.findOne(evalutionSheetResult1.getId());
			evalutionSheetResult.setEvalutionSheet(evalutionSheet);
			evalutionSheetResultRepository.save(evalutionSheetResult);*/
		}
	}

	@Override
	public void saveProgressNote(EvalutionSheet evalutionSheet) {

		EvalutionSheet evalutionSheet1 = new EvalutionSheet();
		evalutionSheet1.setBackgroundInformation(evalutionSheet.getBackgroundInformation());
		evalutionSheet1.setHistory(evalutionSheet.getHistory());
		evalutionSheet1.setObservation(evalutionSheet.getObservation());
		evalutionSheet1.setOralMotor(evalutionSheet.getOralMotor());
		evalutionSheet1.setPragmaticSkills(evalutionSheet.getPragmaticSkills());
		evalutionSheet1.setRecommandations(evalutionSheet.getRecommandations());
		evalutionSheet1.setSummary(evalutionSheet.getSummary());
		evalutionSheet1.setTestAdministered(evalutionSheet.getTestAdministered());
		evalutionSheet1.setParentalTips(evalutionSheet.getParentalTips());
		evalutionSheet1.setFlag(true);
		evalutionSheet1.setDate(evalutionSheet.getDate());
		/*evalutionSheet1.setReceptiveLanguage(evalutionSheet.getReceptiveLanguage());
		evalutionSheet1.setExpressiveLanguage(evalutionSheet.getExpressiveLanguage());
		EvalutionSheetResult evalutionSheetResult1 = new EvalutionSheetResult();
		EvalutionSheetResult evalutionSheetResult = evalutionSheetResultRepository
				.findOne(evalutionSheet.getEvalutionSheetResult().getId());
		if (evalutionSheetResult != null) {
			evalutionSheetResult1.setAuditotyAge(evalutionSheet.getEvalutionSheetResult().getAuditotyAge());
			evalutionSheetResult1
					.setAuditotyCategoryName(evalutionSheet.getEvalutionSheetResult().getAuditotyCategoryName());
			evalutionSheetResult1.setAuditotyDelay(evalutionSheet.getEvalutionSheetResult().getAuditotyDelay());
			evalutionSheetResult1
					.setAuditotyPercentile(evalutionSheet.getEvalutionSheetResult().getAuditotyPercentile());
			evalutionSheetResult1
					.setAuditotyStandardScore(evalutionSheet.getEvalutionSheetResult().getAuditotyStandardScore());
			evalutionSheetResult1.setExpressiveAge(evalutionSheet.getEvalutionSheetResult().getExpressiveAge());
			evalutionSheetResult1
					.setExpressiveCategoryName(evalutionSheet.getEvalutionSheetResult().getExpressiveCategoryName());
			evalutionSheetResult1.setExpressiveDelay(evalutionSheet.getEvalutionSheetResult().getExpressiveDelay());
			evalutionSheetResult1
					.setExpressivePercentile(evalutionSheet.getEvalutionSheetResult().getExpressivePercentile());
			evalutionSheetResult1
					.setExpressiveStandardScore(evalutionSheet.getEvalutionSheetResult().getExpressiveStandardScore());
			evalutionSheetResultRepository.save(evalutionSheetResult1);
			evalutionSheet1.setEvalutionSheetResult(evalutionSheetResult1);
		}*/
		evalutionSheet1.setStatus(evalutionSheet.getStatus());
		Patient dbPatient = patientRepository.findOne(evalutionSheet.getPatient().getId());
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		evalutionSheet1.setPatient(dbPatient);

		// here data is viewed in ui screen from progress note but not
		// stored in database as new record as status "3"

		// List<PatientQuestionCategory> dbpatientQuestionCategoryList =
		// patientQuestionCategoryRepository.findByPatient_IdAndStatus(evalutionSheet.getPatient().getId(),
		// evalutionSheet.getStatus());

		/*List<PatientQuestionCategory> patientQuestionCategoriesList = evalutionSheet.getPatientQuestionCategories();
		List<PatientQuestionCategory> patientQuestionCategoryList = new ArrayList<PatientQuestionCategory>();
		for (PatientQuestionCategory patientQuestionCategory : patientQuestionCategoriesList) {
			PatientQuestionCategory patientQuestionCategory1 = new PatientQuestionCategory();
			patientQuestionCategory1.setDisplayOrder(patientQuestionCategory.getDisplayOrder());
			patientQuestionCategory1.setName(patientQuestionCategory.getName());
			patientQuestionCategory1.setPatient(dbPatient);
			patientQuestionCategory1.setStatus(evalutionSheet.getStatus());
			patientQuestionCategoryRepository.save(patientQuestionCategory1);

			List<PatientQuestion> patientQuestions = patientQuestionCategory.getPatientQuestions();
			List<PatientQuestion> patientQuestionsList = new ArrayList<PatientQuestion>();
			for (PatientQuestion patientQuestion : patientQuestions) {
				PatientQuestion patientQuestion1 = new PatientQuestion();
				patientQuestion1.setQuestionName(patientQuestion.getQuestionName());
				patientQuestion1.setPatientQuestionCategory(patientQuestionCategory1);
				patientQuestionRepository.save(patientQuestion1);
				List<PatientAnswer> patientAnswersList = new ArrayList<PatientAnswer>();
				List<PatientAnswer> patientAnswers = patientQuestion.getPatientAnswers();
				for (PatientAnswer patientAnswer : patientAnswers) {
					PatientAnswer patientAnswer1 = new PatientAnswer();
					patientAnswer1.setDescription(patientAnswer.getDescription());
					patientAnswer1.setShortAnswer(patientAnswer.getShortAnswer());
					patientAnswer1.setSelectedAnswer(patientAnswer.getSelectedAnswer());
					patientAnswer1.setPatientQuestion(patientQuestion1);
					patientAnswerRepository.save(patientAnswer1);
					patientAnswersList.add(patientAnswer1);
				}
				patientQuestionsList.add(patientQuestion1);
			}
			patientQuestionCategoryList.add(patientQuestionCategory1);

		}
		evalutionSheet1.setPatientQuestionCategories(patientQuestionCategoryList);*/

		evalutionSheet1.setPatient(evalutionSheet.getPatient());
		evalutionSheetRepository.save(evalutionSheet1);

	}

	@Override
	public void saveExitNote(EvalutionSheet evalutionSheet) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<String> getProgressSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<EvalutionSheet> evalutionSheets = evalutionSheetRepository.findByStatusAndPatient_Id("2", patientId);
		for (EvalutionSheet evalutionSheet : evalutionSheets) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (evalutionSheet.getProgressDate() != null) {
				dates.add(evalutionSheet.getProgressDate().format(formatter));
			}
		}
		return dates;
	}
	
	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<EvalutionSheet> evalutionSheets = evalutionSheetRepository.findByStatusAndPatient_Id("1", patientId);
		for (EvalutionSheet evalutionSheet : evalutionSheets) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (evalutionSheet.getProgressDate() != null) {
				dates.add(evalutionSheet.getProgressDate().format(formatter));
			}
		}
		return dates;
	}

	@Override
	public EvalutionSheet getProgressSheetByDateAndStatus(ProgressSheetDateDto progressSheetDateDto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		EvalutionSheet evalutionSheet = evalutionSheetRepository.findByPatient_IdAndStatusAndProgressDate(
				progressSheetDateDto.getPatientId(), "1",
				LocalDate.parse(progressSheetDateDto.getProgressDate(), formatter));
		return evalutionSheet;
	}

	@Override
	public EvalutionSheet getEvalutionSheetByDateAndStatus(EvalutionSheetDateDto evalutionSheetDateDto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		EvalutionSheet evalutionSheet = null;
		if ( evalutionSheetDateDto.getEvalutionDate() != null ) {
			evalutionSheet = evalutionSheetRepository.findByPatient_IdAndStatusAndProgressDate(
					evalutionSheetDateDto.getPatientId(), "1",
					LocalDate.parse(evalutionSheetDateDto.getEvalutionDate(), formatter));
		}
		return evalutionSheet;
	}

}
