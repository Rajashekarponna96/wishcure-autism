package com.openspace.Model.NichqTeachers;

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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;

@Entity
public class PatientNichqTeacherQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private int ansValue;
	private Patient patient;
	
	private LocalDate date;

	private List<PatientNichqTeacherAnswer> patientNichqTeacherAnswers;

	private PatientNichqTeacherCategory patientNichqTeacherCategory;
	
	private NichqTeacherCategoryLookup nichqTeacherCategoryLookup;
	
	//for disable the Report button in Patient evalution sheet
	private boolean updated;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
	@OneToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="patientNichqTeacherQuestion")
	public List<PatientNichqTeacherAnswer> getPatientNichqTeacherAnswers() {
		return patientNichqTeacherAnswers;
	}

	public void setPatientNichqTeacherAnswers(List<PatientNichqTeacherAnswer> patientNichqTeacherAnswers) {
		this.patientNichqTeacherAnswers = patientNichqTeacherAnswers;
	}

	@JsonIgnore
	@ManyToOne
	public PatientNichqTeacherCategory getPatientNichqTeacherCategory() {
		return patientNichqTeacherCategory;
	}

	@JsonProperty
	public void setPatientNichqTeacherCategory(PatientNichqTeacherCategory patientNichqTeacherCategory) {
		this.patientNichqTeacherCategory = patientNichqTeacherCategory;
	}

	public int getAnsValue() {
		return ansValue;
	}

	public void setAnsValue(int ansValue) {
		this.ansValue = ansValue;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	@ManyToOne
	public NichqTeacherCategoryLookup getNichqTeacherCategoryLookup() {
		return nichqTeacherCategoryLookup;
	}

	public void setNichqTeacherCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		this.nichqTeacherCategoryLookup = nichqTeacherCategoryLookup;
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
	
	
}
