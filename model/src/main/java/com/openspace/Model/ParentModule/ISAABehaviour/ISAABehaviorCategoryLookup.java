package com.openspace.Model.ParentModule.ISAABehaviour;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ISAABehaviorCategoryLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private String description;
	
	private List<ISAABehaviorQuestionLookup> iSAABehaviorQuestionLookups;

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
	@OneToMany(cascade=CascadeType.ALL,mappedBy="iSAABehaviorCategoryLookup")
	public List<ISAABehaviorQuestionLookup> getiSAABehaviorQuestionLookups() {
		return iSAABehaviorQuestionLookups;
	}

	public void setiSAABehaviorQuestionLookups(List<ISAABehaviorQuestionLookup> iSAABehaviorQuestionLookups) {
		this.iSAABehaviorQuestionLookups = iSAABehaviorQuestionLookups;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
