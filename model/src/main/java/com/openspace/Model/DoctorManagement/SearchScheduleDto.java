package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import com.openspace.Model.Lookups.Department;

public class SearchScheduleDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String localDate;
	
	private String fromTime;
	
	private String toTime;
	
	private String username;
	
	private Department department;
	
	/* for search schedules based on start and Enddates*/
	private String enddate;
	
	private String accurateFromTime;
	
	private String accurateToTime;
	
	private String accurateEndDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLocalDate() {
		return localDate;
	}

	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getAccurateFromTime() {
		return accurateFromTime;
	}

	public void setAccurateFromTime(String accurateFromTime) {
		this.accurateFromTime = accurateFromTime;
	}

	public String getAccurateToTime() {
		return accurateToTime;
	}

	public void setAccurateToTime(String accurateToTime) {
		this.accurateToTime = accurateToTime;
	}

	public String getAccurateEndDate() {
		return accurateEndDate;
	}

	public void setAccurateEndDate(String accurateEndDate) {
		this.accurateEndDate = accurateEndDate;
	}
	
	

}
