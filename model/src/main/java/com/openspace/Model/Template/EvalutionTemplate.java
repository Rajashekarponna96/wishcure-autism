package com.openspace.Model.Template;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.openspace.Model.DoctorManagement.QuestionCategory;

@Entity
public class EvalutionTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String history;

	private String backgroundInformation;

	private String observation;

	private String testAdministered;

	private String oralMotor;

	private String pragmaticSkills;

	private String summary;

	private String recommendations;

	private String parentalTips;

	private String signaturePath;

	private List<QuestionCategory> questionCategorys;

	private boolean flag;

	private String receptiveLanguage;
	
	private String expressiveLanguage;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "evalutionTemplate")
	public List<QuestionCategory> getQuestionCategorys() {
		return questionCategorys;
	}

	public void setQuestionCategorys(List<QuestionCategory> questionCategorys) {
		this.questionCategorys = questionCategorys;
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

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	public String getParentalTips() {
		return parentalTips;
	}

	public void setParentalTips(String parentalTips) {
		this.parentalTips = parentalTips;
	}

	public String getSignaturePath() {
		return signaturePath;
	}

	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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
	

}
