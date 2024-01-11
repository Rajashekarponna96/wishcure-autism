package com.openspace.Model.ParentModule.csbs;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CsbsCategoryLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private String categoryStatus;
	
	private List<CsbsQuestionLookup> csbsQuestionLookups;

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
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="csbsCategoryLookup")
	public List<CsbsQuestionLookup> getcsbsQuestionLookups() {
		return csbsQuestionLookups;
	}

	public void setcsbsQuestionLookups(List<CsbsQuestionLookup> csbsQuestionLookups) {
		this.csbsQuestionLookups = csbsQuestionLookups;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

}
