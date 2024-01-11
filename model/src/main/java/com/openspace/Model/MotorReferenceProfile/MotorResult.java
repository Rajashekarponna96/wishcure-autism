package com.openspace.Model.MotorReferenceProfile;

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
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Scales.MotorClusterCount;

@Entity
public class MotorResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private List<PatientMotorScale> patientMotorScales;
	
	private List<MotorClusterCount> motorClusterCountList;

	private Patient patient;
	
	private Double motorAge;
	
	private Double motorQuotient;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "motorResult")
	public List<PatientMotorScale> getPatientMotorScales() {
		return patientMotorScales;
	}

	public void setPatientMotorScales(List<PatientMotorScale> patientMotorScales) {
		this.patientMotorScales = patientMotorScales;
	}

	@JsonIgnore
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	@JsonProperty
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Double getMotorAge() {
		return motorAge;
	}

	public void setMotorAge(Double motorAge) {
		this.motorAge = motorAge;
	}

	public Double getMotorQuotient() {
		return motorQuotient;
	}

	public void setMotorQuotient(Double motorQuotient) {
		this.motorQuotient = motorQuotient;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "motorResult")
	public List<MotorClusterCount> getMotorClusterCountList() {
		return motorClusterCountList;
	}

	public void setMotorClusterCountList(List<MotorClusterCount> motorClusterCountList) {
		this.motorClusterCountList = motorClusterCountList;
	}

	
}