package com.openspace.Model.NICHQParent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class NichqParentCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<NichqParentQuestion> nichqParentQuestion;

	private Patient patient;
	
	private LocalDate date;
	
	private NICHQParentCategoryLookup nICHQParentCategoryLookup;

	@Id
	@GeneratedValue
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nichqParentCategory")
	public List<NichqParentQuestion> getNichqParentQuestion() {
		return nichqParentQuestion;
	}

	public void setNichqParentQuestion(List<NichqParentQuestion> nichqParentQuestion) {
		this.nichqParentQuestion = nichqParentQuestion;
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

	@ManyToOne
	public NICHQParentCategoryLookup getnICHQParentCategoryLookup() {
		return nICHQParentCategoryLookup;
	}

	public void setnICHQParentCategoryLookup(NICHQParentCategoryLookup nICHQParentCategoryLookup) {
		this.nICHQParentCategoryLookup = nICHQParentCategoryLookup;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
