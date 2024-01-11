package com.openspace.Model.Dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ScheduledHourDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ScheduleDtoForCalendar scheduleDtoForCalendar;
	
	// startTime used to get Hours
	private String startTime;
	
	// endtime used to get Hours
	private String endtime;
	
	private String appointmentStartedDate;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public ScheduleDtoForCalendar getScheduleDtoForCalendar() {
		return scheduleDtoForCalendar;
	}

	public void setScheduleDtoForCalendar(ScheduleDtoForCalendar scheduleDtoForCalendar) {
		this.scheduleDtoForCalendar = scheduleDtoForCalendar;
	}

	public String getAppointmentStartedDate() {
		return appointmentStartedDate;
	}

	public void setAppointmentStartedDate(String appointmentStartedDate) {
		this.appointmentStartedDate = appointmentStartedDate;
	}
}
