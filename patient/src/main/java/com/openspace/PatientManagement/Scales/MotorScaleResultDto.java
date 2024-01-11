package com.openspace.PatientManagement.Scales;

public class MotorScaleResultDto {

	private String clusterName;

	private Long clusterCount;

	private String percentile;

	private String remarks;

	private Double motorAge;

	private Double motorQuotient;
	
	private Long graphCount;
	
	private int scaleCount;

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public Long getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(Long clusterCount) {
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

	public Long getGraphCount() {
		return graphCount;
	}

	public void setGraphCount(Long graphCount) {
		this.graphCount = graphCount;
	}

	public int getScaleCount() {
		return scaleCount;
	}

	public void setScaleCount(int scaleCount) {
		this.scaleCount = scaleCount;
	}
	
	

}
