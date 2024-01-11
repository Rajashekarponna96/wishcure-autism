package com.openspace.Model.DepartofPhysioLookup;

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
public class PhysiotherapyEvaluationCategoryQuestionsLookup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private List<PhysiotherapyEvaluationCategoryAnswersLookup> PhysiotherapyEvalCatAnswerLookup;
	
	private PhysiotherapyEvaluationCategoryLookup phyEvalCatLookup;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "phyEvalCatQuesLookup")
	public List<PhysiotherapyEvaluationCategoryAnswersLookup> getPhysiotherapyEvalCatAnswerLookup() {
		return PhysiotherapyEvalCatAnswerLookup;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhysiotherapyEvalCatAnswerLookup(
			List<PhysiotherapyEvaluationCategoryAnswersLookup> physiotherapyEvalCatAnswerLookup) {
		PhysiotherapyEvalCatAnswerLookup = physiotherapyEvalCatAnswerLookup;
	}

	@ManyToOne
	@JsonIgnore
	public PhysiotherapyEvaluationCategoryLookup getPhyEvalCatLookup() {
		return phyEvalCatLookup;
	}

	@JsonProperty
	public void setPhyEvalCatLookup(PhysiotherapyEvaluationCategoryLookup phyEvalCatLookup) {
		this.phyEvalCatLookup = phyEvalCatLookup;
	}


	
	
	
	

}
