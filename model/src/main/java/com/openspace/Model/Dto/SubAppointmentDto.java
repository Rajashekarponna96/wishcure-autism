package com.openspace.Model.Dto;

import java.time.Month;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.openspace.Model.DoctorManagement.AppointMentStatus;
import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.Lookups.Company;

public class SubAppointmentDto {
	
	private Long id;
	
	private String appointmentStartedDate;

	private String appointmentEndedDate;

	private String appointmentStartTime;
	

	private String appointmentEndTime;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getAppointmentStartedDate() {
		return appointmentStartedDate;
	}

	public void setAppointmentStartedDate(String appointmentStartedDate) {
		this.appointmentStartedDate = appointmentStartedDate;
	}

	public String getAppointmentEndedDate() {
		return appointmentEndedDate;
	}

	public void setAppointmentEndedDate(String appointmentEndedDate) {
		this.appointmentEndedDate = appointmentEndedDate;
	}

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

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
	
}
