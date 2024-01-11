package com.openspace.Model.MentalReferenceProfile;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MentalReferenceProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String month;

	private float minAge;

	private float maxAge;

	private List<ReferenceMentalCluster> referenceMentalCluster;

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

	public float getMinAge() {
		return minAge;
	}

	public void setMinAge(float minAge) {
		this.minAge = minAge;
	}

	public float getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(float maxAge) {
		this.maxAge = maxAge;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentalReferenceProfile")
	public List<ReferenceMentalCluster> getReferenceMentalCluster() {
		return referenceMentalCluster;
	}

	public void setReferenceMentalCluster(List<ReferenceMentalCluster> referenceMentalCluster) {
		this.referenceMentalCluster = referenceMentalCluster;
	}

}
