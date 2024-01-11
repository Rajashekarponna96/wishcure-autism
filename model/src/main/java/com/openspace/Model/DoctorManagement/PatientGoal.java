package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.Template.GoalTemplateLookup;

@Entity
public class PatientGoal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private boolean answer;
	
	private String  yesValue;
	
	private String  noValue;

	private LocalDate date;

	private Patient patient;

	private boolean status;

	private GoalTemplateLookup goalTemplateLookup;

	private String comment;

	private Long percentile;
	
	private String evaluator;
	
	private String serviceCoordinator;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	@JsonProperty
	public void setPatient(Patient patient) {
		this.patient = patient;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToOne
	public GoalTemplateLookup getGoalTemplateLookup() {
		return goalTemplateLookup;
	}

	public void setGoalTemplateLookup(GoalTemplateLookup goalTemplateLookup) {
		this.goalTemplateLookup = goalTemplateLookup;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getPercentile() {
		return percentile;
	}

	public void setPercentile(Long percentile) {
		this.percentile = percentile;
	}

	
	public String getYesValue() {
		return yesValue;
	}

	public void setYesValue(String yesValue) {
		this.yesValue = yesValue;
	}

	public String getNoValue() {
		return noValue;
	}

	public void setNoValue(String noValue) {
		this.noValue = noValue;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
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

	
}
