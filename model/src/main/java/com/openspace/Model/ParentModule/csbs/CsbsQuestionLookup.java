package com.openspace.Model.ParentModule.csbs;

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
public class CsbsQuestionLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private List<CsbsAnswerLookup> csbsAnswerLookups;

	private CsbsCategoryLookup csbsCategoryLookup;

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
	public CsbsCategoryLookup getcsbsCategoryLookup() {
		return csbsCategoryLookup;
	}

	@JsonProperty
	public void setcsbsCategoryLookup(CsbsCategoryLookup csbsCategoryLookup) {
		this.csbsCategoryLookup = csbsCategoryLookup;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="csbsQuestionLookup")
	public List<CsbsAnswerLookup> getcsbsAnswerLookups() {
		return csbsAnswerLookups;
	}

	public void setcsbsAnswerLookups(List<CsbsAnswerLookup> csbsAnswerLookups) {
		this.csbsAnswerLookups = csbsAnswerLookups;
	}

	
	
}
