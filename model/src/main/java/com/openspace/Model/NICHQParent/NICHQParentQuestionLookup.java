package com.openspace.Model.NICHQParent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class NICHQParentQuestionLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private int answervalue;

	private List<NICHQParentAnswerLookup> nichqParentAnswerLookup;

	private NICHQParentCategoryLookup nichqParentCategoryLookup;

	@Id
	@GeneratedValue
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nichqParentQuestionLookup")
	public List<NICHQParentAnswerLookup> getNichqParentAnswerLookup() {
		return nichqParentAnswerLookup;
	}

	public void setNichqParentAnswerLookup(List<NICHQParentAnswerLookup> nichqParentAnswerLookup) {
		this.nichqParentAnswerLookup = nichqParentAnswerLookup;
	}

	@JsonIgnore
	@ManyToOne
	public NICHQParentCategoryLookup getNichqParentCategoryLookup() {
		return nichqParentCategoryLookup;
	}

	@JsonProperty
	public void setNichqParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup) {
		this.nichqParentCategoryLookup = nichqParentCategoryLookup;
	}

	public int getAnswervalue() {
		return answervalue;
	}

	public void setAnswervalue(int answervalue) {
		this.answervalue = answervalue;
	}

	
	
}
