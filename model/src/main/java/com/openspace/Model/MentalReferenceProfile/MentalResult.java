package com.openspace.Model.MentalReferenceProfile;

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
import com.openspace.Model.Scales.MentalClusterCount;

@Entity
public class MentalResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private List<PatientMentalScale> patientMentalScales;
	
	private List<MentalClusterCount> mentalClusterCountList;

	private Patient patient;
	
	private Double mentalAge;
	
	private Double mentalQuotient;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentalResult")
	public List<PatientMentalScale> getPatientMentalScales() {
		return patientMentalScales;
	}

	public void setPatientMentalScales(List<PatientMentalScale> patientMentalScales) {
		this.patientMentalScales = patientMentalScales;
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

	public Double getMentalAge() {
		return mentalAge;
	}

	public void setMentalAge(Double mentalAge) {
		this.mentalAge = mentalAge;
	}

	public Double getMentalQuotient() {
		return mentalQuotient;
	}

	public void setMentalQuotient(Double mentalQuotient) {
		this.mentalQuotient = mentalQuotient;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentalResult")
	public List<MentalClusterCount> getMentalClusterCountList() {
		return mentalClusterCountList;
	}

	public void setMentalClusterCountList(List<MentalClusterCount> mentalClusterCountList) {
		this.mentalClusterCountList = mentalClusterCountList;
	}

	
}
