package com.openspace.Model.Scales;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openspace.Model.MotorReferenceProfile.MotorResult;

@Entity
public class MotorClusterCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long clusterId;

	private Long clusterCount;

	private String percentile;

	private String remarks;

	private String clusterName;
	
	private MotorResult motorResult;
	
	//for show the percentage value in graph 
	private Long graphCount;

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	public Long getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(Long clusterCount) {
		this.clusterCount = clusterCount;
	}
	public MotorClusterCount() {
		
	}

	public MotorClusterCount(Long clusterId, Long clusterCount) {
		this.clusterId = clusterId;
		this.clusterCount = clusterCount;
	}

	public String getPercentile() {
		return percentile;
	}

	public void setPercentile(String percentile) {
		this.percentile = percentile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@JsonIgnore
	@ManyToOne
	public MotorResult getMotorResult() {
		return motorResult;
	}

	public void setMotorResult(MotorResult motorResult) {
		this.motorResult = motorResult;
	}

	public Long getGraphCount() {
		return graphCount;
	}

	public void setGraphCount(Long graphCount) {
		this.graphCount = graphCount;
	}

	
}
