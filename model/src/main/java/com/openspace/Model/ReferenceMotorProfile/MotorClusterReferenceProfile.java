package com.openspace.Model.ReferenceMotorProfile;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MotorClusterReferenceProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String motorClusterName;

	private List<MotorClusterHeader> motorClusterHeaders;

	private String month;

	private Float minAge;

	private Float maxAge;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Float getMinAge() {
		return minAge;
	}

	public void setMinAge(Float minAge) {
		this.minAge = minAge;
	}

	public Float getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Float maxAge) {
		this.maxAge = maxAge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMotorClusterName() {
		return motorClusterName;
	}

	public void setMotorClusterName(String motorClusterName) {
		this.motorClusterName = motorClusterName;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "motorClusterReferenceProfile")
	public List<MotorClusterHeader> getMotorClusterHeaders() {
		return motorClusterHeaders;
	}

	public void setMotorClusterHeaders(List<MotorClusterHeader> motorClusterHeaders) {
		this.motorClusterHeaders = motorClusterHeaders;
	}

}
