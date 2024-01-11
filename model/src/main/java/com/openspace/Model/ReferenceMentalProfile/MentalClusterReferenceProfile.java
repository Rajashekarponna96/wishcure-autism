
package com.openspace.Model.ReferenceMentalProfile;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MentalClusterReferenceProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String clusterName;

	private List<MentalClusterHeader> clusterHeaders;

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

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="contentClusterReferenceProfile")
	public List<MentalClusterHeader> getClusterHeaders() {
		return clusterHeaders;
	}

	public void setClusterHeaders(List<MentalClusterHeader> clusterHeaders) {
		this.clusterHeaders = clusterHeaders;
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

}
