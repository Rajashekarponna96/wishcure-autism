package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DateConverters.LocalTimeConverter;
import com.openspace.Model.DateConverters.LocalTimeDeSerilizer;
import com.openspace.Model.DateConverters.LocalTimeSerilizer;
import com.openspace.Model.Dto.PaidStatus;
import com.openspace.Model.Lookups.Company;

@Entity
public class SubAppointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDate appointmentStartedDate;

	private LocalDate appointmentEndedDate;

	private LocalTime appointmentStartTime;

	private LocalTime appointmentEndTime;
	
	private LocalDate createdDate;
	
	private LocalDate paidDate;

	private Appointment appointment;

	private Doctor doctor;

	private Patient patient;

	private Company company;

	private Person person;

	private AppointMentStatus appointMentStatus;

	private boolean active;

	private SubAppointmentStatus status;

	private Month month;

	private Integer year;
	
	private PaidStatus paidStatus;
	
	private Occurance occurance;
	
	private String invoiceNo;
	
	private Double assignAmount;
	
	private boolean dateFlag;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getAppointmentStartedDate() {
		return appointmentStartedDate;
	}

	public void setAppointmentStartedDate(LocalDate appointmentStartedDate) {
		this.appointmentStartedDate = appointmentStartedDate;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getAppointmentEndedDate() {
		return appointmentEndedDate;
	}

	public void setAppointmentEndedDate(LocalDate appointmentEndedDate) {
		this.appointmentEndedDate = appointmentEndedDate;
	}
	@Convert(converter = LocalTimeConverter.class)
	@JsonDeserialize(using = LocalTimeDeSerilizer.class)
	@JsonSerialize(using = LocalTimeSerilizer.class)
	public LocalTime getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(LocalTime appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}
	@Convert(converter = LocalTimeConverter.class)
	@JsonDeserialize(using = LocalTimeDeSerilizer.class)
	@JsonSerialize(using = LocalTimeSerilizer.class)
	public LocalTime getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(LocalTime appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	@JsonIgnore
	@ManyToOne
	public Appointment getAppointment() {
		return appointment;
	}

	@JsonProperty
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@ManyToOne
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Enumerated(EnumType.STRING)
	public AppointMentStatus getAppointMentStatus() {
		return appointMentStatus;
	}

	public void setAppointMentStatus(AppointMentStatus appointMentStatus) {
		this.appointMentStatus = appointMentStatus;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Enumerated(EnumType.STRING)
	public SubAppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(SubAppointmentStatus status) {
		this.status = status;
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
	@Enumerated(EnumType.STRING)
	public PaidStatus getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(PaidStatus paidStatus) {
		this.paidStatus = paidStatus;
	}
	@Enumerated(EnumType.STRING)
	public Occurance getOccurance() {
		return occurance;
	}

	public void setOccurance(Occurance occurance) {
		this.occurance = occurance;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
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

	public boolean isDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(boolean dateFlag) {
		this.dateFlag = dateFlag;
	}
	
}
