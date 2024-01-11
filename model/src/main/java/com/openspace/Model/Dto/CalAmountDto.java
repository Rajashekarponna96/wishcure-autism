package com.openspace.Model.Dto;

public class CalAmountDto {

	private Long patientId;
	
	private Double assignedAmount;
	
	private int noOfCycles;
	
	private Double paidAmount;
	
	private Double remainingAmount;
	
	private Double totalAmount;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Double getAssignedAmount() {
		return assignedAmount;
	}

	public void setAssignedAmount(Double assignedAmount) {
		this.assignedAmount = assignedAmount;
	}

	public int getNoOfCycles() {
		return noOfCycles;
	}

	public void setNoOfCycles(int noOfCycles) {
		this.noOfCycles = noOfCycles;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(Double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	
}
