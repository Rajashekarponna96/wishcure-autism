package com.openspace.Model.NICHQParent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class NICHQParentCategoryLookup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private List<NICHQParentQuestionLookup> nichqParentQuestionLookup;
	
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

	@OneToMany(cascade=CascadeType.ALL, mappedBy="nichqParentCategoryLookup")
	public List<NICHQParentQuestionLookup> getNichqParentQuestionLookup() {
		return nichqParentQuestionLookup;
	}

	public void setNichqParentQuestionLookup(List<NICHQParentQuestionLookup> nichqParentQuestionLookup) {
		this.nichqParentQuestionLookup = nichqParentQuestionLookup;
	}

}
