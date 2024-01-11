package com.openspace.Model.ParentModule.ISAABehaviour;

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
public class ISAABehaviorQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private int ansValue;

	private ISAABehaviorCategory iSAABehaviorCategory;

	private List<ISAABehaviorAnswer> iSAABehaviorAnswers;
	
	private ISAABehaviorCategoryLookup isaaBehaviorCategoryLookup;

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
	public ISAABehaviorCategory getiSAABehaviorCategory() {
		return iSAABehaviorCategory;
	}

	@JsonProperty
	public void setiSAABehaviorCategory(ISAABehaviorCategory iSAABehaviorCategory) {
		this.iSAABehaviorCategory = iSAABehaviorCategory;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "iSAABehaviorQuestion")
	public List<ISAABehaviorAnswer> getiSAABehaviorAnswers() {
		return iSAABehaviorAnswers;
	}

	public void setiSAABehaviorAnswers(List<ISAABehaviorAnswer> iSAABehaviorAnswers) {
		this.iSAABehaviorAnswers = iSAABehaviorAnswers;
	}

	public int getAnsValue() {
		return ansValue;
	}

	public void setAnsValue(int ansValue) {
		this.ansValue = ansValue;
	}

	@ManyToOne
	public ISAABehaviorCategoryLookup getIsaaBehaviorCategoryLookup() {
		return isaaBehaviorCategoryLookup;
	}

	public void setIsaaBehaviorCategoryLookup(ISAABehaviorCategoryLookup isaaBehaviorCategoryLookup) {
		this.isaaBehaviorCategoryLookup = isaaBehaviorCategoryLookup;
	}

	
	
}
