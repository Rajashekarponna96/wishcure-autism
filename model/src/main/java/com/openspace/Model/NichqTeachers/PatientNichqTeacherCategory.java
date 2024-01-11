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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;

@Entity
public class PatientNichqTeacherCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private Patient patient;
	
	private LocalDate date;

	private List<PatientNichqTeacherQuestion> patientNichqTeacherQuestions;
	
	private NichqTeacherCategoryLookup nichqTeacherCategoryLookup;

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
	
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientNichqTeacherCategory")
	public List<PatientNichqTeacherQuestion> getPatientNichqTeacherQuestions() {
		return patientNichqTeacherQuestions;
	}

	public void setPatientNichqTeacherQuestions(List<PatientNichqTeacherQuestion> patientNichqTeacherQuestions) {
		this.patientNichqTeacherQuestions = patientNichqTeacherQuestions;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne
	public NichqTeacherCategoryLookup getNichqTeacherCategoryLookup() {
		return nichqTeacherCategoryLookup;
	}

	public void setNichqTeacherCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		this.nichqTeacherCategoryLookup = nichqTeacherCategoryLookup;
	}
	
	
	

}
