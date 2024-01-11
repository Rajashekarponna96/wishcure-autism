package com.openspace.Model.ParentModule.childobservation;

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
public class ChildObservationCategoryLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<ChildObservationQuestionLookup> childObservationQuestionLookups;

	private String status;

	private boolean active;

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

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "childObservationCategoryLookup")
	public List<ChildObservationQuestionLookup> getChildObservationQuestionLookups() {
		return childObservationQuestionLookups;
	}

	public void setChildObservationQuestionLookups(
			List<ChildObservationQuestionLookup> childObservationQuestionLookups) {
		this.childObservationQuestionLookups = childObservationQuestionLookups;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}
