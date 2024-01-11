package com.openspace.Model.ReferenceMotorProfile;

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
public class MotorClusterHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String motorClustorName;

	private Integer value;

	private MotorClusterReferenceProfile motorClusterReferenceProfile;

	private List<MotorPercentile> motorPercentiles;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getMotorClustorName() {
		return motorClustorName;
	}

	public void setMotorClustorName(String motorClustorName) {
		this.motorClustorName = motorClustorName;
	}

	@JsonIgnore
	@ManyToOne
	public MotorClusterReferenceProfile getMotorClusterReferenceProfile() {
		return motorClusterReferenceProfile;
	}

	@JsonProperty
	public void setMotorClusterReferenceProfile(MotorClusterReferenceProfile motorClusterReferenceProfile) {
		this.motorClusterReferenceProfile = motorClusterReferenceProfile;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "motorClusterHeader")
	public List<MotorPercentile> getMotorPercentiles() {
		return motorPercentiles;
	}

	public void setMotorPercentiles(List<MotorPercentile> motorPercentiles) {
		this.motorPercentiles = motorPercentiles;
	}

}
