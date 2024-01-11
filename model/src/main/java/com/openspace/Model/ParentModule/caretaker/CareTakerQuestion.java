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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.DoctorManagement.Person;

@Entity
public class CareTakerQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<CareTakerAnswer> careTakerAnswers;

	private CareTakerCategory careTakerCategory;
	
	private CareTakerCategoryLookup careTakerCategoryLookup;
	
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "careTakerQuestion")
	public List<CareTakerAnswer> getCareTakerAnswers() {
		return careTakerAnswers;
	}

	public void setCareTakerAnswers(List<CareTakerAnswer> careTakerAnswers) {
		this.careTakerAnswers = careTakerAnswers;
	}

	@ManyToOne
	
	public CareTakerCategory getCareTakerCategory() {
		return careTakerCategory;
	}

	@JsonProperty
	public void setCareTakerCategory(CareTakerCategory careTakerCategory) {
		this.careTakerCategory = careTakerCategory;
	}

	@ManyToOne
	public CareTakerCategoryLookup getCareTakerCategoryLookup() {
		return careTakerCategoryLookup;
	}

	public void setCareTakerCategoryLookup(CareTakerCategoryLookup careTakerCategoryLookup) {
		this.careTakerCategoryLookup = careTakerCategoryLookup;
	}

	@ManyToOne
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	

}
