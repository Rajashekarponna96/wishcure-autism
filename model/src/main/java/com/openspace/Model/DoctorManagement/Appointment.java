package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DateConverters.LocalTimeConverter;
import com.openspace.Model.DateConverters.LocalTimeDeSerilizer;
import com.openspace.Model.DateConverters.LocalTimeSerilizer;
import com.openspace.Model.Lookups.Company;

@Entity
@Access(AccessType.PROPERTY)
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Doctor doctor;

	private Patient patient;

	private AppointMentStatus appointmentStatus;

	private Integer weeks;
	////////////////////////////////////
	private End end;

	private RepeateOn repeateOn;

	private Integer afterOccurrence;

	// private Float duration;

	private LocalTime duration;

	private Integer repeatOnDay;

	private String repeatOnWhichWeek;

	private String repeatOnWeek;
	////////////////////////////////////////
	private String weekDays;

	private String description;

	private Occurance occurance;

	private LocalDate appointmentStartedDate;// LocalDate

	private LocalDate appointmentEndedDate;
	
	private LocalDate createdDate;

	private LocalTime appointmentStartTime;

	private LocalTime appointmentEndTime;

	private Company company;

	private Person person;

	private List<SubAppointment> subAppointments;
	
	private boolean active;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Enumerated(EnumType.STRING)
	public AppointMentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointMentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	/*
	 * @ElementCollection public List<String> getWeekDays() { return weekDays; }
	 * 
	 * public void setWeekDays(List<String> weekDays) { this.weekDays =
	 * weekDays; }
	 */
	public String getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.STRING)
	public Occurance getOccurance() {
		return occurance;
	}

	public void setOccurance(Occurance occurance) {
		this.occurance = occurance;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Timestamp convertToTimestamp(LocalDate localDate) {
		Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		return timestamp;
	}

	public LocalDate convertToLocaldate(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalDate();
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

	public Integer getAfterOccurrence() {
		return afterOccurrence;
	}

	public void setAfterOccurrence(Integer afterOccurrence) {
		this.afterOccurrence = afterOccurrence;
	}

	public Integer getRepeatOnDay() {
		return repeatOnDay;
	}

	public void setRepeatOnDay(Integer repeatOnDay) {
		this.repeatOnDay = repeatOnDay;
	}

	public End getEnd() {
		return end;
	}

	public void setEnd(End end) {
		this.end = end;
	}

	public RepeateOn getRepeateOn() {
		return repeateOn;
	}

	public void setRepeateOn(RepeateOn repeateOn) {
		this.repeateOn = repeateOn;
	}

	public String getRepeatOnWhichWeek() {
		return repeatOnWhichWeek;
	}

	public void setRepeatOnWhichWeek(String repeatOnWhichWeek) {
		this.repeatOnWhichWeek = repeatOnWhichWeek;
	}

	public String getRepeatOnWeek() {
		return repeatOnWeek;
	}

	public void setRepeatOnWeek(String repeatOnWeek) {
		this.repeatOnWeek = repeatOnWeek;
	}

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
	public List<SubAppointment> getSubAppointments() {
		return subAppointments;
	}

	public void setSubAppointments(List<SubAppointment> subAppointments) {
		this.subAppointments = subAppointments;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
	
	
	
}
