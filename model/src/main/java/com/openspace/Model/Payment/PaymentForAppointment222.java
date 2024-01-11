/*package com.openspace.Model.Payment;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Patient;

@Entity
public class PaymentForAppointment222 implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	private Long id;

	private PaymentType paymentTypes;

	private Double amount;
	
	private String checkIssuedBank;
	
	private String ddIssuedBank;
	
	private Long checkNumber;
	
	private Long ddNumber;

	private Double assignedAmount;
	
	private int noOfCycles;

	private LocalDate paidDate;

	private LocalDate therapyDoneDate;

	private Patient patient;
	
	private String userName;

	private Double paidAmount;

	private Double totalAmount;

	private Double remainingAmount;

	private ModeOfPaymentType modeOfPaymentTypes;
	
	private Doctor doctor;
	
   private Month month;
	
	private Integer year;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public PaymentType getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentType paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getNoOfCycles() {
		return noOfCycles;
	}

	public void setNoOfCycles(int noOfCycles) {
		this.noOfCycles = noOfCycles;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getTherapyDoneDate() {
		return therapyDoneDate;
	}

	public void setTherapyDoneDate(LocalDate therapyDoneDate) {
		this.therapyDoneDate = therapyDoneDate;
	}

	
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}
	@JsonProperty
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
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

	@Enumerated(EnumType.STRING)
	public ModeOfPaymentType getModeOfPaymentTypes() {
		return modeOfPaymentTypes;
	}

	public void setModeOfPaymentTypes(ModeOfPaymentType modeOfPaymentTypes) {
		this.modeOfPaymentTypes = modeOfPaymentTypes;
	}

	public Double getAssignedAmount() {
		return assignedAmount;
	}

	public void setAssignedAmount(Double assignedAmount) {
		this.assignedAmount = assignedAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCheckIssuedBank() {
		return checkIssuedBank;
	}

	public void setCheckIssuedBank(String checkIssuedBank) {
		this.checkIssuedBank = checkIssuedBank;
	}

	public String getDdIssuedBank() {
		return ddIssuedBank;
	}

	public void setDdIssuedBank(String ddIssuedBank) {
		this.ddIssuedBank = ddIssuedBank;
	}

	public Long getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(Long checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Long getDdNumber() {
		return ddNumber;
	}

	public void setDdNumber(Long ddNumber) {
		this.ddNumber = ddNumber;
	}

	@ManyToOne
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	@Enumerated(EnumType.STRING)
	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
*/