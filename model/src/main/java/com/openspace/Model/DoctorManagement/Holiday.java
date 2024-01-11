package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DateConverters.LocalTimeConverter;
import com.openspace.Model.DateConverters.LocalTimeDeSerilizer;
import com.openspace.Model.DateConverters.LocalTimeSerilizer;

@Entity
public class Holiday implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String reason;

	private Timestamp toTime;

	private Timestamp fromTime;

	private LocalDate toDate;

	private LocalDate fromDate;
	
	private LocalTime toLocalTime;

	private LocalTime fromLocalTime;

	private Doctor doctor;

	private String color;
	
	private boolean active;
	
	private HolidayStatus holidayStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@ManyToOne(cascade = CascadeType.MERGE)
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static Timestamp convertToTimestamp(LocalDate localDate) {
		Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		return timestamp;
	}

	public static LocalDate convertToLocaldate(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalDate();
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	@Convert(converter = LocalTimeConverter.class)
	@JsonDeserialize(using = LocalTimeDeSerilizer.class)
	@JsonSerialize(using = LocalTimeSerilizer.class)
	public LocalTime getToLocalTime() {
		return toLocalTime;
	}

	public void setToLocalTime(LocalTime toLocalTime) {
		this.toLocalTime = toLocalTime;
	}
	@Convert(converter = LocalTimeConverter.class)
	@JsonDeserialize(using = LocalTimeDeSerilizer.class)
	@JsonSerialize(using = LocalTimeSerilizer.class)
	public LocalTime getFromLocalTime() {
		return fromLocalTime;
	}
	@Enumerated(EnumType.STRING)
	public void setFromLocalTime(LocalTime fromLocalTime) {
		this.fromLocalTime = fromLocalTime;
	}

	public HolidayStatus getHolidayStatus() {
		return holidayStatus;
	}

	public void setHolidayStatus(HolidayStatus holidayStatus) {
		this.holidayStatus = holidayStatus;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	

}
