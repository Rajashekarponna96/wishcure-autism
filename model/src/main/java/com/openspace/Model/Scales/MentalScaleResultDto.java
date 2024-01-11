package com.openspace.Model.Scales;

public class MentalScaleResultDto {

	private String clusterName;

	private Long clusterCount;

	private String percentile;

	private String remarks;

	private Double mentalAge;

	private Double mentalQuotient;
	
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
