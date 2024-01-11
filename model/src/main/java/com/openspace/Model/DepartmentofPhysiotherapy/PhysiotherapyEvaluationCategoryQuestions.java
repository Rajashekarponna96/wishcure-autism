package com.openspace.Model.DepartmentofPhysiotherapy;

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
public class PhysiotherapyEvaluationCategoryQuestions implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String questName;
	
	private List<PhysiotherapyEvaluationCategoryAnswers> pEvaluationCategoryAnswers;
	
	private PhysiotherapyEvaluationCategory physiotherapyEvaluationCategory;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	@ManyToOne
	@JsonIgnore
	public PhysiotherapyEvaluationCategory getPhysiotherapyEvaluationCategory() {
		return physiotherapyEvaluationCategory;
	}

	@JsonProperty
	public void setPhysiotherapyEvaluationCategory(PhysiotherapyEvaluationCategory physiotherapyEvaluationCategory) {
		this.physiotherapyEvaluationCategory = physiotherapyEvaluationCategory;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "physiotherapyEvaluationCategoryQuestions")
	public List<PhysiotherapyEvaluationCategoryAnswers> getpEvaluationCategoryAnswers() {
		return pEvaluationCategoryAnswers;
	}

	public void setpEvaluationCategoryAnswers(List<PhysiotherapyEvaluationCategoryAnswers> pEvaluationCategoryAnswers) {
		this.pEvaluationCategoryAnswers = pEvaluationCategoryAnswers;
	}

	
	

}
