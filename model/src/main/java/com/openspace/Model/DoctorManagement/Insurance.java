package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class Insurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String idNumber;

	private String insuranceCompanyName;

	private Long mobileNumber;

	private String emailId;

	private Company company;

	private UserAccount userAccount;
	
	private String adminUserName;
	
	private String fax;

	private Address1 address1;
	
	private boolean status;
	
	private boolean fromLookup;
	
	private Long insuranceLookupId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}

	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	@ManyToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	@Embedded
	public Address1 getAddress1() {
		return address1;
	}

	public void setAddress1(Address1 address1) {
		this.address1 = address1;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isFromLookup() {
		return fromLookup;
	}

	public void setFromLookup(boolean fromLookup) {
		this.fromLookup = fromLookup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getInsuranceLookupId() {
		return insuranceLookupId;
	}

	public void setInsuranceLookupId(Long insuranceLookupId) {
		this.insuranceLookupId = insuranceLookupId;
	}

}
