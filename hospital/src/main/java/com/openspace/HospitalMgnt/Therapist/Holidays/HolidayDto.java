package com.openspace.HospitalMgnt.Therapist.Holidays;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import com.openspace.Model.DoctorManagement.Doctor;



public class HolidayDto {
	

	private Long id;

	private String reason;

	private Timestamp toTime;

	private Timestamp fromTime;
	
	private Doctor doctor; 
	
	private String color;
	
	private String doctorUsername;
	
	private LocalDate toDate;

	private LocalDate fromDate;
	
	private LocalTime toLocalTime;

	private LocalTime fromLocalTime;
	
	private Long scheduleId;
	

	public Long getId() {
		return id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getToTime() {
		return toTime;
	}

	public void setToTime(Timestamp toTime) {
		this.toTime = toTime;
	}

	public Timestamp getFromTime() {
		return fromTime;
	}

	public void setFromTime(Timestamp fromTime) {
		this.fromTime = fromTime;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public static Timestamp convertToTimestamp(LocalDate localDate) {
		Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		return timestamp;
	}

	public static LocalDate convertToLocaldate(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalDate();
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public LocalTime getToLocalTime() {
		return toLocalTime;
	}

	public void setToLocalTime(LocalTime toLocalTime) {
		this.toLocalTime = toLocalTime;
	}

	public LocalTime getFromLocalTime() {
		return fromLocalTime;
	}

	public void setFromLocalTime(LocalTime fromLocalTime) {
		this.fromLocalTime = fromLocalTime;
	}

	public static LocalTime convertToLocalTime(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalTime();
	}

}
