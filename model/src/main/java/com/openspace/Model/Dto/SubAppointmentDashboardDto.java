package com.openspace.Model.Dto;

public class SubAppointmentDashboardDto {

	private String month;
	
	private Integer completeAppointments;
	
	private Integer cancelAppointments;
	
	private Integer awaitingAppointments;


	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getCompleteAppointments() {
		return completeAppointments;
	}

	public void setCompleteAppointments(Integer completeAppointments) {
		this.completeAppointments = completeAppointments;
	}

	public Integer getCancelAppointments() {
		return cancelAppointments;
	}

	public void setCancelAppointments(Integer cancelAppointments) {
		this.cancelAppointments = cancelAppointments;
	}

	public Integer getAwaitingAppointments() {
		return awaitingAppointments;
	}

	public void setAwaitingAppointments(Integer awaitingAppointments) {
		this.awaitingAppointments = awaitingAppointments;
	}
	
	
}
