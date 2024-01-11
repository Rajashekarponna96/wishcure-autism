package com.openspace.Model.Dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class CareTakerCategoryLookupDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private String categoryStatus;
	
	private List<CareTakerQuestionLookupDto> careTakerQuestionLookups;

	
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
	
	public List<CareTakerQuestionLookupDto> getCareTakerQuestionLookups() {
		return careTakerQuestionLookups;
	}

	public void setCareTakerQuestionLookups(List<CareTakerQuestionLookupDto> careTakerQuestionLookups) {
		this.careTakerQuestionLookups = careTakerQuestionLookups;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
	

}
