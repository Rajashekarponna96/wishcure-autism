package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.Lookups.Location;
import com.openspace.Model.Lookups.Site;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class Doctor extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String specialization;

	private String designation;

	// identification number i.e unique
	private String rciNumber;

	// private String doctorOfType;

	private UserAccount userAccount;

	private List<Schedule> schedules;

	private Company company;

	private Site site;

	private Department department;

	private Location location;

	private List<Holiday> holidays;

	private String shortAutoBiography;
	
	private String signaturePath;

	

	public String getShortAutoBiography() {
		return shortAutoBiography;
	}

	public void setShortAutoBiography(String shortAutoBiography) {
		this.shortAutoBiography = shortAutoBiography;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@OneToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "doctor")
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	/*
	 * public String getDoctorOfType() { return doctorOfType; }
	 * 
	 * public void setDoctorOfType(String doctorOfType) { this.doctorOfType =
	 * doctorOfType; }
	 */

	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@ManyToOne
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@OneToMany
	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	public String getSignaturePath() {
		return signaturePath;
	}

	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}

	public String getRciNumber() {
		return rciNumber;
	}

	public void setRciNumber(String rciNumber) {
		this.rciNumber = rciNumber;
	}
	
	

}
