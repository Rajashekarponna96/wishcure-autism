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
public class CsbsQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private CsbsCategory csbsCategory;

	private List<CsbsAnswer> csbsAnswers;

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
	public CsbsCategory getcsbsCategory() {
		return csbsCategory;
	}

	@JsonProperty
	public void setcsbsCategory(CsbsCategory csbsCategory) {
		this.csbsCategory = csbsCategory;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "csbsQuestion")
	public List<CsbsAnswer> getcsbsAnswers() {
		return csbsAnswers;
	}

	public void setcsbsAnswers(List<CsbsAnswer> csbsAnswers) {
		this.csbsAnswers = csbsAnswers;
	}

}
