package com.openspace.Model.ParentModule.ISAABehaviour;

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
public class ISAABehaviorCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	/*private int ansValue;*/

	private List<ISAABehaviorQuestion> iSAABehaviorQuestions;

	private ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup;

	private Patient patient;
	
	private LocalDate date;
	

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "iSAABehaviorCategory")
	public List<ISAABehaviorQuestion> getiSAABehaviorQuestions() {
		return iSAABehaviorQuestions;
	}

	public void setiSAABehaviorQuestions(List<ISAABehaviorQuestion> iSAABehaviorQuestions) {
		this.iSAABehaviorQuestions = iSAABehaviorQuestions;
	}


	@ManyToOne
	public ISAABehaviorCategoryLookup getiSAABehaviorCategoryLookup() {
		return iSAABehaviorCategoryLookup;
	}

	public void setiSAABehaviorCategoryLookup(ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup) {
		this.iSAABehaviorCategoryLookup = iSAABehaviorCategoryLookup;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/*public int getAnsValue() {
		return ansValue;
	}

	public void setAnsValue(int ansValue) {
		this.ansValue = ansValue;
	}*/

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
