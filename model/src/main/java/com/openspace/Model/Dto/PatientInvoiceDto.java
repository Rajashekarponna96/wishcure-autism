package com.openspace.Model.Dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.Model.DoctorManagement.SubAppointment;

public class PatientInvoiceDto {
	

	private Patient patient;
	
	private Doctor  doctor;
	
	private String invoice;
	
	private Double totalAmount;
	
	private Double remainingAmount;
	
	private Double paidAmount;

	private LocalDate starDate;
	
	private LocalDate dueDate;
	
	private LocalDate paidDate;
	
	private LocalDate createdDate;
	
	/*private PaymentType paymentTypes;*/
	
	private Paymethod paymentTypes;
	
	private List<SubAppointment> subAppointments;
	
	private Double assignAmount;
	
	private Appointment appointment;
	
	private String newInvoiceNo;
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
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
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getStarDate() {
		return starDate;
	}

	public void setStarDate(LocalDate starDate) {
		this.starDate = starDate;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/*public PaymentType getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentType paymentTypes) {
		this.paymentTypes = paymentTypes;
	}*/

	public List<SubAppointment> getSubAppointments() {
		return subAppointments;
	}

	public void setSubAppointments(List<SubAppointment> subAppointments) {
		this.subAppointments = subAppointments;
	}

	public Double getAssignAmount() {
		return assignAmount;
	}

	public void setAssignAmount(Double assignAmount) {
		this.assignAmount = assignAmount;
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
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getNewInvoiceNo() {
		return newInvoiceNo;
	}

	public void setNewInvoiceNo(String newInvoiceNo) {
		this.newInvoiceNo = newInvoiceNo;
	}

	public Paymethod getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(Paymethod paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	
}
