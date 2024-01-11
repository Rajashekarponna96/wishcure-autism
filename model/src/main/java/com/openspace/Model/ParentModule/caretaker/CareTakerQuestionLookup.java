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

@Entity
public class CareTakerQuestionLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<CareTakerAnswerLookup> careTakerAnswerLookups;

	private CareTakerCategoryLookup careTakerCategoryLookup;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "careTakerQuestionLookup")
	public List<CareTakerAnswerLookup> getCareTakerAnswerLookups() {
		return careTakerAnswerLookups;
	}

	public void setCareTakerAnswerLookups(List<CareTakerAnswerLookup> careTakerAnswerLookups) {
		this.careTakerAnswerLookups = careTakerAnswerLookups;
	}
	
	@ManyToOne
	public CareTakerCategoryLookup getCareTakerCategoryLookup() {
		return careTakerCategoryLookup;
	}

	
	public void setCareTakerCategoryLookup(CareTakerCategoryLookup careTakerCategoryLookup) {
		this.careTakerCategoryLookup = careTakerCategoryLookup;
	}

}
