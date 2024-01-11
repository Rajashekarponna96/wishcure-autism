package com.openspace.Model.MentalReferenceProfile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ReferenceMentalCluster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long MentalClusterId;

	private String resultPercentile;

	private Long resultValue;

	private MentalReferenceProfile mentalReferenceProfile;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMentalClusterId() {
		return MentalClusterId;
	}

	public void setMentalClusterId(Long mentalClusterId) {
		MentalClusterId = mentalClusterId;
	}

	public String getResultPercentile() {
		return resultPercentile;
	}

	public void setResultPercentile(String resultPercentile) {
		this.resultPercentile = resultPercentile;
	}

	public Long getResultValue() {
		return resultValue;
	}

	public void setResultValue(Long resultValue) {
		this.resultValue = resultValue;
	}

	@JsonIgnore
	@ManyToOne
	public MentalReferenceProfile getMentalReferenceProfile() {
		return mentalReferenceProfile;
	}

	@JsonProperty
	public void setMentalReferenceProfile(MentalReferenceProfile mentalReferenceProfile) {
		this.mentalReferenceProfile = mentalReferenceProfile;
	}

}
