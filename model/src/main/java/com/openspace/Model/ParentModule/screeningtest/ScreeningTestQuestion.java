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
public class ScreeningTestQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private ScreeningTestCategory screeningTestCategory;

	private List<ScreeningTestAnswer> screeningTestAnswers;

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
	@JsonIgnore
	public ScreeningTestCategory getScreeningTestCategory() {
		return screeningTestCategory;
	}

	@JsonProperty
	public void setScreeningTestCategory(ScreeningTestCategory screeningTestCategory) {
		this.screeningTestCategory = screeningTestCategory;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "screeningTestQuestion")
	public List<ScreeningTestAnswer> getScreeningTestAnswers() {
		return screeningTestAnswers;
	}

	public void setScreeningTestAnswers(List<ScreeningTestAnswer> screeningTestAnswers) {
		this.screeningTestAnswers = screeningTestAnswers;
	}

}
