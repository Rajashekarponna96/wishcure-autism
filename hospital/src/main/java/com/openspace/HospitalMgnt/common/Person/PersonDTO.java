package com.openspace.HospitalMgnt.common.Person;

import java.sql.Timestamp;

import com.openspace.Model.DoctorManagement.Gender;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.Lookups.Site;
import com.openspace.Model.UserManagement.Role;

public class PersonDTO {
	
	private Long id;

	private String firstName;

	private String lastName;
	
	private String username;
	 
	private String adminUserName;
	
	private String password;

	private Long mobileNumber;
	
	private Role role;
	
	private String designation;
	
	private String rciNumber;
	
	private String address1;

	private String address2;

	private String country;

	private String state;

	private String city;

	private String location;

	private String zipcode;

	private String specialization;
	
	private Department department;
	
	private Timestamp createdDate;

	private Timestamp modifiedDate;
	
	private String shortAutoBiography;

	private String signaturePath;

	private Gender gender;
	
	private boolean isActive;
	
	private Site site;
	
	private Long oldTherapistId;
	
    private String aadharNumber;
	
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}



	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getRciNumber() {
		return rciNumber;
	}

	public void setRciNumber(String rciNumber) {
		this.rciNumber = rciNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getShortAutoBiography() {
		return shortAutoBiography;
	}

	public void setShortAutoBiography(String shortAutoBiography) {
		this.shortAutoBiography = shortAutoBiography;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getSignaturePath() {
		return signaturePath;
	}

	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}

	public Long getOldTherapistId() {
		return oldTherapistId;
	}

	public void setOldTherapistId(Long oldTherapistId) {
		this.oldTherapistId = oldTherapistId;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	
	

}
