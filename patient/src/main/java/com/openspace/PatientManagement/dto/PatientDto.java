package com.openspace.PatientManagement.dto;

public class PatientDto {

	private String ucl;
	
	private String firstName;

	private String email;

	private Long mobileNumber;

	public String getUcl() {
		return ucl;
	}

	public void setUcl(String ucl) {
		this.ucl = ucl;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
