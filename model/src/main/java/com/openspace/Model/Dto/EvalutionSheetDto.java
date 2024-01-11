package com.openspace.Model.Dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.EvalutionSheetResult;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientQuestionCategory;
import com.openspace.Model.DoctorManagement.PatientSpeechTherapyTemplate;

public class EvalutionSheetDto {

	private Long id;

	private String history;

	private String backgroundInformation;

	private String observation;

	private String testAdministered;

	private String oralMotor;

	private String pragmaticSkills;

	private String summary;

	private String recommandations;

	private String parentalTips;

	private EvalutionSheetResult evalutionSheetResult;

	private List<PatientSpeechTherapyTemplate> patientSpeechTherapyTemplates;

	private Patient patient;

	private boolean flag;

	private String status;

	private LocalDate date;

	private String evaluator;

	private String serviceCoordinator;

	private String receptiveLanguage;

	private String expressiveLanguage;

	private String evalutionSheetStatus;
	
	private String interpretation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getBackgroundInformation() {
		return backgroundInformation;
	}

	public void setBackgroundInformation(String backgroundInformation) {
		this.backgroundInformation = backgroundInformation;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getTestAdministered() {
		return testAdministered;
	}

	public void setTestAdministered(String testAdministered) {
		this.testAdministered = testAdministered;
	}

	public String getOralMotor() {
		return oralMotor;
	}

	public void setOralMotor(String oralMotor) {
		this.oralMotor = oralMotor;
	}

	public String getPragmaticSkills() {
		return pragmaticSkills;
	}

	public void setPragmaticSkills(String pragmaticSkills) {
		this.pragmaticSkills = pragmaticSkills;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRecommandations() {
		return recommandations;
	}

	public void setRecommandations(String recommandations) {
		this.recommandations = recommandations;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EvalutionSheetResult getEvalutionSheetResult() {
		return evalutionSheetResult;
	}

	public void setEvalutionSheetResult(EvalutionSheetResult evalutionSheetResult) {
		this.evalutionSheetResult = evalutionSheetResult;
	}

	public String getParentalTips() {
		return parentalTips;
	}

	public void setParentalTips(String parentalTips) {
		this.parentalTips = parentalTips;
	}
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getReceptiveLanguage() {
		return receptiveLanguage;
	}

	public void setReceptiveLanguage(String receptiveLanguage) {
		this.receptiveLanguage = receptiveLanguage;
	}

	public String getExpressiveLanguage() {
		return expressiveLanguage;
	}

	public void setExpressiveLanguage(String expressiveLanguage) {
		this.expressiveLanguage = expressiveLanguage;
	}

	public List<PatientSpeechTherapyTemplate> getPatientSpeechTherapyTemplates() {
		return patientSpeechTherapyTemplates;
	}

	public void setPatientSpeechTherapyTemplates(List<PatientSpeechTherapyTemplate> patientSpeechTherapyTemplates) {
		this.patientSpeechTherapyTemplates = patientSpeechTherapyTemplates;
	}

	public String getEvalutionSheetStatus() {
		return evalutionSheetStatus;
	}

	public void setEvalutionSheetStatus(String evalutionSheetStatus) {
		this.evalutionSheetStatus = evalutionSheetStatus;
	}

	public String getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(String evaluator) {
		this.evaluator = evaluator;
	}

	public String getServiceCoordinator() {
		return serviceCoordinator;
	}

	public void setServiceCoordinator(String serviceCoordinator) {
		this.serviceCoordinator = serviceCoordinator;
	}

	public String getInterpretation() {
		return interpretation;
	}

	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}
	
	

}
