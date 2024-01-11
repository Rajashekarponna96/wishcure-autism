package com.openspace.Model.NICHQParent;

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

@Entity
public class NichqParentQuestion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private int ansValue;
	
	private LocalDate date;
	
	private Patient patient;

	private NichqParentCategory nichqParentCategory;

	private List<NichqParentAnswer> nichqParentAnswer;
	
	private NICHQParentCategoryLookup nichqParentCategoryLookup;
	
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
	
	@OneToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@ManyToOne
	@JsonIgnore
	public NichqParentCategory getNichqParentCategory() {
		return nichqParentCategory;
	}
	
	@JsonProperty
	public void setNichqParentCategory(NichqParentCategory nichqParentCategory) {
		this.nichqParentCategory = nichqParentCategory;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nichqParentQuestion")
	public List<NichqParentAnswer> getNichqParentAnswer() {
		return nichqParentAnswer;
	}

	public void setNichqParentAnswer(List<NichqParentAnswer> nichqParentAnswer) {
		this.nichqParentAnswer = nichqParentAnswer;
	}

	public int getAnsValue() {
		return ansValue;
	}

	public void setAnsValue(int ansValue) {
		this.ansValue = ansValue;
	}

	@ManyToOne
	public NICHQParentCategoryLookup getNichqParentCategoryLookup() {
		return nichqParentCategoryLookup;
	}

	public void setNichqParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup) {
		this.nichqParentCategoryLookup = nichqParentCategoryLookup;
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
