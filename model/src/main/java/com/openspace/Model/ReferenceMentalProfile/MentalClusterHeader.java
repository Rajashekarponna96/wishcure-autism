package com.openspace.Model.ReferenceMentalProfile;

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
public class MentalClusterHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String mentalClusterId;
	
	private Integer value;
	
	private MentalClusterReferenceProfile contentClusterReferenceProfile;
	
	private String Percentile;
 
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

	@JsonIgnore
	@ManyToOne
	public MentalClusterReferenceProfile getContentClusterReferenceProfile() {
		return contentClusterReferenceProfile;
	}

	@JsonProperty
	public void setContentClusterReferenceProfile(MentalClusterReferenceProfile contentClusterReferenceProfile) {
		this.contentClusterReferenceProfile = contentClusterReferenceProfile;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getMentalClusterId() {
		return mentalClusterId;
	}

	public void setMentalClusterId(String mentalClusterId) {
		this.mentalClusterId = mentalClusterId;
	}

	public String getPercentile() {
		return Percentile;
	}

	public void setPercentile(String percentile) {
		Percentile = percentile;
	}

	
}
