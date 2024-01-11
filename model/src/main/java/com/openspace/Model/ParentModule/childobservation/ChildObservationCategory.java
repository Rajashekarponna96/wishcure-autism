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
import com.openspace.Model.DoctorManagement.Person;

@Entity
public class ChildObservationCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private List<ChildObservationQuestion>  childObservationQuestions;
	
	private String status;
	
	private boolean active;
	
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
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="childObservationCategory")
	public List<ChildObservationQuestion> getChildObservationQuestions() {
		return childObservationQuestions;
	}

	public void setChildObservationQuestions(List<ChildObservationQuestion> childObservationQuestions) {
		this.childObservationQuestions = childObservationQuestions;
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

	@ManyToOne
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	

	
}
