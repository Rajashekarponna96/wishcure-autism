package com.openspace.Model.Dto;

public class VSMSChildPerformanceDTO {

	private String nameOfCluster;
	private int clusterCount;
	private int totalNoOfItemsPassed;
	
	private double percentile;
	private String remarks;

	public String getNameOfCluster() {
		return nameOfCluster;
	}

	public void setNameOfCluster(String nameOfCluster) {
		this.nameOfCluster = nameOfCluster;
	}

	public int getTotalNoOfItemsPassed() {
		return totalNoOfItemsPassed;
	}

	public void setTotalNoOfItemsPassed(int totalNoOfItemsPassed) {
		this.totalNoOfItemsPassed = totalNoOfItemsPassed;
	}

	public double getPercentile() {
		return percentile;
	}

	public void setPercentile(double percentile) {
		this.percentile = percentile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public int getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
	}
	

	@Override
	public String toString() {
		return "VSMSChildPerformanceDTO [nameOfCluster=" + nameOfCluster + ", totalNoOfItemsPassed="
				+ totalNoOfItemsPassed + ", percentile=" + percentile + ", remarks=" + remarks + "]";
	}

}
