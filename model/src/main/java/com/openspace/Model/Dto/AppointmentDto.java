package com.openspace.Model.Dto;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.End;
import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.RepeateOn;
import com.openspace.Model.Lookups.Department;

public class AppointmentDto {

	private Long id;

	private Doctor doctor;

	private Department department;

	private Patient patient;

	private String adminUserName;

	private Integer weeks;

	////////////////////////////////////
	private End end;

	private RepeateOn repeateOn;

	private String duration;

	private Integer afterOccurrence;

	private Integer repeatOnDay;

	private String repeatOnWhichWeek;

	private String repeatOnWeek;
	////////////////////////////////////////
	// private List<String> weekDays;

	private String weekDays;

	private String description;

	private Occurance occurance;

	private String appointmentStartedDate;

	private String appointmentEndedDate;

	/*
	 * private Timestamp appointmentStartDate;
	 * 
	 * private Timestamp appointmentEndDate;
	 */

	private String appointmentStartTime;

	private String appointmentEndTime;

	private Long scheduleId;

	/*
	 * for search schedules based on dates
	 */
	private String appointmentEnddate;

	public Long getId() {
		return id;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	/*
	 * public List<String> getWeekDays() { return weekDays; }
	 * 
	 * public void setWeekDays(List<String> weekDays) { this.weekDays =
	 * weekDays; }
	 */public String getWeekDays() {
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

	public Occurance getOccurance() {
		return occurance;
	}

	public void setOccurance(Occurance occurance) {
		this.occurance = occurance;
	}

	/*
	 * public Timestamp getAppointmentStartDate() { return appointmentStartDate;
	 * }
	 * 
	 * public void setAppointmentStartDate(Timestamp appointmentStartDate) {
	 * this.appointmentStartDate = appointmentStartDate; }
	 * 
	 * public Timestamp getAppointmentEndDate() { return appointmentEndDate; }
	 * 
	 * public void setAppointmentEndDate(Timestamp appointmentEndDate) {
	 * this.appointmentEndDate = appointmentEndDate; }
	 */

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
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

	public Timestamp convertToTimestamp(LocalDate localDate) {
		Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		return timestamp;
	}

	public LocalDate convertToLocaldate(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalDate();
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

	public String getRepeatOnWeek() {
		return repeatOnWeek;
	}

	public void setRepeatOnWeek(String repeatOnWeek) {
		this.repeatOnWeek = repeatOnWeek;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

}
