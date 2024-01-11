package com.openspace.Model.Dto;

public class AppointmentsDateDto {

	private Long id;
	
	private String adminUserName;
	
	private String appointmentStartedDate;

	private String appointmentEndedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
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
	
	
	
	
}
