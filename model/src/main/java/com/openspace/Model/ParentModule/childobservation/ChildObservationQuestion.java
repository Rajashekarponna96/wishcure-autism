package com.openspace.Model.ParentModule.childobservation;

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
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class ChildObservationQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<ChildObservationAnswer> childObservationAnswers;

	private ChildObservationCategory childObservationCategory;
	
	private ChildObservationCategoryLookup childObservationCategery;

	private String answer;
	
	private boolean status;

	private Person person;

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
	public ChildObservationCategory getChildObservationCategory() {
		return childObservationCategory;
	}

	@JsonProperty
	public void setChildObservationCategory(ChildObservationCategory childObservationCategory) {
		this.childObservationCategory = childObservationCategory;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "childObservationQuestion")
	public List<ChildObservationAnswer> getChildObservationAnswers() {
		return childObservationAnswers;
	}

	public void setChildObservationAnswers(List<ChildObservationAnswer> childObservationAnswers) {
		this.childObservationAnswers = childObservationAnswers;
	}
	
	@ManyToOne
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@ManyToOne
	public ChildObservationCategoryLookup getChildObservationCategery() {
		return childObservationCategery;
	}

	public void setChildObservationCategery(ChildObservationCategoryLookup childObservationCategery) {
		this.childObservationCategery = childObservationCategery;
	}
	
}
