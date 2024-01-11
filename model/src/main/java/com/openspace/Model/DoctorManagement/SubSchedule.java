package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

@Entity
public class SubSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Doctor doctor;

	private LocalTime fromTime;

	private LocalTime toTime;

	private LocalDate presentDate;

	private String color;

	private ScheduleStatus scheduleStatus;
	
	private boolean active;
	
	private Schedule schedule;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// @JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/*@ElementCollection
	@CollectionTable(name = "availableday", joinColumns = @JoinColumn(name = "id"))
	public List<String> getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(List<String> availableDays) {
		this.availableDays = availableDays;
	}*/

	@Convert(converter = LocalTimeConverter.class)
	@JsonDeserialize(using = LocalTimeDeSerilizer.class)
	@JsonSerialize(using = LocalTimeSerilizer.class)
	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	@Convert(converter = LocalTimeConverter.class)
	@JsonDeserialize(using = LocalTimeDeSerilizer.class)
	@JsonSerialize(using = LocalTimeSerilizer.class)
	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getPresentDate() {
		return presentDate;
	}

	public void setPresentDate(LocalDate presentDate) {
		this.presentDate = presentDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Enumerated(EnumType.STRING)
	public ScheduleStatus getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(ScheduleStatus scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	@JsonIgnore
	@ManyToOne
	public Schedule getSchedule() {
		return schedule;
	}
	@JsonProperty
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}

