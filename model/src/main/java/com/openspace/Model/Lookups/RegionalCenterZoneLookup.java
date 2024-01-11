package com.openspace.Model.Lookups;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openspace.Model.DoctorManagement.RegionalCenter;

@Entity
public class RegionalCenterZoneLookup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String regionalCenterZoneName;
	
	private List<RegionalCenterLookup> regionalCenterZoneLookups;
	
	private List<RegionalCenter> regionalCenters;
	
	private Boolean status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegionalCenterZoneName() {
		return regionalCenterZoneName;
	}

	public void setRegionalCenterZoneName(String regionalCenterZoneName) {
		this.regionalCenterZoneName = regionalCenterZoneName;
	}
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="regionalCenterZoneLookup")
	public List<RegionalCenterLookup> getRegionalCenterZoneLookups() {
		return regionalCenterZoneLookups;
	}

	public void setRegionalCenterZoneLookups(List<RegionalCenterLookup> regionalCenterZoneLookups) {
		this.regionalCenterZoneLookups = regionalCenterZoneLookups;
	}
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="regionalCenterZoneLookup")
	public List<RegionalCenter> getRegionalCenters() {
		return regionalCenters;
	}

	public void setRegionalCenters(List<RegionalCenter> regionalCenters) {
		this.regionalCenters = regionalCenters;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
