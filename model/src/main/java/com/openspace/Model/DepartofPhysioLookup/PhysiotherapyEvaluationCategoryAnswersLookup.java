package com.openspace.Model.DepartofPhysioLookup;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PhysiotherapyEvaluationCategoryAnswersLookup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private PhysiotherapyEvaluationCategoryQuestionsLookup phyEvalCatQuesLookup;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne	
	@JsonIgnore
	public PhysiotherapyEvaluationCategoryQuestionsLookup getPhyEvalCatQuesLookup() {
		return phyEvalCatQuesLookup;
	}

	@JsonProperty
	public void setPhyEvalCatQuesLookup(PhysiotherapyEvaluationCategoryQuestionsLookup phyEvalCatQuesLookup) {
		this.phyEvalCatQuesLookup = phyEvalCatQuesLookup;
	}
	
	

	
}
