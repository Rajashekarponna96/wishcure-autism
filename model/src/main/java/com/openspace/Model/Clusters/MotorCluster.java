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
import com.openspace.Model.Scales.MotorScale;

@Entity
public class MotorCluster implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private List<MotorScale> motorScales;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "motorCluster")
	public List<MotorScale> getMotorScales() {
		return motorScales;
	}

	public void setMotorScales(List<MotorScale> motorScales) {
		this.motorScales = motorScales;
	}

	public void addscales(MotorScale motorScale) {
		motorScales.add(motorScale);
	}

}
