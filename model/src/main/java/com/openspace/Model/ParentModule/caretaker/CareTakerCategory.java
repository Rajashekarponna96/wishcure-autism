package com.openspace.Model.ParentModule.caretaker;

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
public class CareTakerCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	
	private List<CareTakerQuestion> careTakerQuestions;
	
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
	@OneToMany(cascade=CascadeType.ALL,mappedBy="careTakerCategory")
	public List<CareTakerQuestion> getCareTakerQuestions() {
		return careTakerQuestions;
	}

	public void setCareTakerQuestions(List<CareTakerQuestion> careTakerQuestions) {
		this.careTakerQuestions = careTakerQuestions;
	}
	@ManyToOne
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
}
