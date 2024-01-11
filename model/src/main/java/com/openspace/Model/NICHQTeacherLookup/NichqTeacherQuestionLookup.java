package com.openspace.Model.NICHQTeacherLookup;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;

@Entity
public class NichqTeacherQuestionLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private int answervalue;
	
	private List<NichqTeacherAnswerLookup> nichqTeacherAnswerlookups;
	
	private NichqTeacherCategoryLookup nichqTeacherCategoryLookup;
	private LocalDate date;

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

	@OneToMany(cascade=CascadeType.ALL,mappedBy="nichqTeacherQuestionLookup")
	public List<NichqTeacherAnswerLookup> getNichqTeacherAnswerlookups() {
		return nichqTeacherAnswerlookups;
	}

	public void setNichqTeacherAnswerlookups(List<NichqTeacherAnswerLookup> nichqTeacherAnswerlookups) {
		this.nichqTeacherAnswerlookups = nichqTeacherAnswerlookups;
	}

	@JsonIgnore
	@ManyToOne
	public NichqTeacherCategoryLookup getNichqTeacherCategoryLookup() {
		return nichqTeacherCategoryLookup;
	}

	@JsonProperty
	public void setNichqTeacherCategoryLookup(NichqTeacherCategoryLookup nichqTeacherCategoryLookup) {
		this.nichqTeacherCategoryLookup = nichqTeacherCategoryLookup;
	}

	public int getAnswervalue() {
		return answervalue;
	}

	public void setAnswervalue(int answervalue) {
		this.answervalue = answervalue;
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
