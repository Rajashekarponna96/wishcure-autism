package com.openspace.Model.Dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.Model.Payment.ModeOfPaymentType;

public class CycleDto {

	private Double assignedAmount;
	
	private int noOfCycles;
	
	private Double totalAmount;

	private Double remainingAmount;
	
	private Doctor doctor;
	
	/*private PaymentType paymentTypes;*/
	private Paymethod paymentTypes;
	
	
	private ModeOfPaymentType modeOfPaymentTypes;

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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	@Enumerated(EnumType.STRING)
	public ModeOfPaymentType getModeOfPaymentTypes() {
		return modeOfPaymentTypes;
	}

	public void setModeOfPaymentTypes(ModeOfPaymentType modeOfPaymentTypes) {
		this.modeOfPaymentTypes = modeOfPaymentTypes;
	}
	/*@Enumerated(EnumType.STRING)
	public PaymentType getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentType paymentTypes) {
		this.paymentTypes = paymentTypes;
	}*/

	public Paymethod getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(Paymethod paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	
}
