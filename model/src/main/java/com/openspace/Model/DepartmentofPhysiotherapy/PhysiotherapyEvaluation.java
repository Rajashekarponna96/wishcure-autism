package com.openspace.Model.DepartmentofPhysiotherapy;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Patient;

@Entity
public class PhysiotherapyEvaluation implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String chiefComplaints;
	
	private String briefHistory;
	
	private String examinations;
	
	private Patient patient;
	
	private List<PhysiotherapyEvaluationCategory> pEvaluationCategories;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	 public void setId(Long id) { this.id = id; }

	
	
	 

	public String getChiefComplaints() {
		return chiefComplaints;
	}

	public void setChiefComplaints(String chiefComplaints) {
		this.chiefComplaints = chiefComplaints;
	}

	public String getBriefHistory() {
		return briefHistory;
	}

	public void setBriefHistory(String briefHistory) {
		this.briefHistory = briefHistory;
	}

	public String getExaminations() {
		return examinations;
	}

	public void setExaminations(String examinations) {
		this.examinations = examinations;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "physiotherapyEvaluation")
	public List<PhysiotherapyEvaluationCategory> getpEvaluationCategories() {
		return pEvaluationCategories;
	}

	public void setpEvaluationCategories(List<PhysiotherapyEvaluationCategory> pEvaluationCategories) {
		this.pEvaluationCategories = pEvaluationCategories;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Patient getPatient() {
		return patient;
	}
	

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
	
	
	
	
	
	

}
