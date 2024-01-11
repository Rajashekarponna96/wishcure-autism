package com.openspace.Model.Clusters;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.Scales.MentalScales;

@Entity
public class MentalClusters implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	 
	private String name;
	
	private String descripation;
	
	private List<MentalScales> mentalScales;  

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

	public String getDescripation() {
		return descripation;
	}

	public void setDescripation(String descripation) {
		this.descripation = descripation;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="mentalClusters")
	public List<MentalScales> getMentalScales() {
		return mentalScales;
	}

	@JsonProperty
	public void setMentalScales(List<MentalScales> mentalScales) {
		this.mentalScales = mentalScales;
	}

}
