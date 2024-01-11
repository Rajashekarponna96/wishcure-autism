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
public class ISAABehaviorQuestionLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private int answervalue;
	
	private List<ISAABehaviorAnswerLookup> iSAABehaviorAnswerLookups;

	private ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup;

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
	@ManyToOne
	public ISAABehaviorCategoryLookup getiSAABehaviorCategoryLookup() {
		return iSAABehaviorCategoryLookup;
	}

	@JsonProperty
	public void setiSAABehaviorCategoryLookup(ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup) {
		this.iSAABehaviorCategoryLookup = iSAABehaviorCategoryLookup;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="iSAABehaviorQuestionLookup")
	public List<ISAABehaviorAnswerLookup> getiSAABehaviorAnswerLookups() {
		return iSAABehaviorAnswerLookups;
	}

	public void setiSAABehaviorAnswerLookups(List<ISAABehaviorAnswerLookup> iSAABehaviorAnswerLookups) {
		this.iSAABehaviorAnswerLookups = iSAABehaviorAnswerLookups;
	}

	public int getAnswervalue() {
		return answervalue;
	}

	public void setAnswervalue(int answervalue) {
		this.answervalue = answervalue;
	}
	
}
