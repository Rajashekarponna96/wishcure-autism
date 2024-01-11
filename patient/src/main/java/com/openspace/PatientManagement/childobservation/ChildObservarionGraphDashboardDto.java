package com.openspace.PatientManagement.childobservation;

public class ChildObservarionGraphDashboardDto {
	
	private String state;
	
	private Integer yes;

	private Integer no;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getYes() {
		return yes;
	}

	public void setYes(Integer yes) {
		this.yes = yes;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

}
