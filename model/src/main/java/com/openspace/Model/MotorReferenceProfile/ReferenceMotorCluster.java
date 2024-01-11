package com.openspace.Model.MotorReferenceProfile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ReferenceMotorCluster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long MotorClusterId;

	private String resultPercentile;

	private Long resultValue;

	private MotorReferenceProfile motorReferenceProfile;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMotorClusterId() {
		return MotorClusterId;
	}

	public void setMotorClusterId(Long motorClusterId) {
		MotorClusterId = motorClusterId;
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
	public MotorReferenceProfile getMotorReferenceProfile() {
		return motorReferenceProfile;
	}

	@JsonProperty
	public void setMotorReferenceProfile(MotorReferenceProfile motorReferenceProfile) {
		this.motorReferenceProfile = motorReferenceProfile;
	}

}
