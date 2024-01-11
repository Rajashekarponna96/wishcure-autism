package com.openspace.Model.ParentModule.screeningtest;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ScreeningTestQuestionLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private List<ScreeningTestAnswerLookup> screeningTestAnswerLookups;

	private ScreeningTestCategoryLookup screeningTestCategoryLookup;

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

	@ManyToOne
	public ScreeningTestCategoryLookup getScreeningTestCategoryLookup() {
		return screeningTestCategoryLookup;
	}

	@JsonProperty
	public void setScreeningTestCategoryLookup(ScreeningTestCategoryLookup screeningTestCategoryLookup) {
		this.screeningTestCategoryLookup = screeningTestCategoryLookup;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="screeningTestQuestionLookup")
	public List<ScreeningTestAnswerLookup> getScreeningTestAnswerLookups() {
		return screeningTestAnswerLookups;
	}

	public void setScreeningTestAnswerLookups(List<ScreeningTestAnswerLookup> screeningTestAnswerLookups) {
		this.screeningTestAnswerLookups = screeningTestAnswerLookups;
	}

	
	
}
