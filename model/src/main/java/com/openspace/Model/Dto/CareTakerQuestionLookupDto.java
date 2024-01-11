package com.openspace.Model.Dto;

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


public class CareTakerQuestionLookupDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<CareTakerAnswerLookupDto> careTakerAnswerLookups;

	private CareTakerCategoryLookupDto careTakerCategoryLookup;

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

	
	public List<CareTakerAnswerLookupDto> getCareTakerAnswerLookups() {
		return careTakerAnswerLookups;
	}

	public void setCareTakerAnswerLookups(List<CareTakerAnswerLookupDto> careTakerAnswerLookups) {
		this.careTakerAnswerLookups = careTakerAnswerLookups;
	}
	
	
	public CareTakerCategoryLookupDto getCareTakerCategoryLookup() {
		return careTakerCategoryLookup;
	}

	
	public void setCareTakerCategoryLookup(CareTakerCategoryLookupDto careTakerCategoryLookup) {
		this.careTakerCategoryLookup = careTakerCategoryLookup;
	}

}
