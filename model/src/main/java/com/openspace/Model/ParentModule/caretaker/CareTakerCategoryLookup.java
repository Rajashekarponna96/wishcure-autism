package com.openspace.Model.ParentModule.caretaker;

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
public class CareTakerCategoryLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private String description;
	
	
	
	private List<CareTakerQuestionLookup> careTakerQuestionLookups;

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
	@OneToMany(cascade=CascadeType.ALL,mappedBy="careTakerCategoryLookup")
	public List<CareTakerQuestionLookup> getCareTakerQuestionLookups() {
		return careTakerQuestionLookups;
	}

	public void setCareTakerQuestionLookups(List<CareTakerQuestionLookup> careTakerQuestionLookups) {
		this.careTakerQuestionLookups = careTakerQuestionLookups;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
