package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.RegionalCenterZoneLookup;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class RegionalCenter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String email;

	private Long mobileNumber;

	private Address1 reginoalCenterAddress;

	private Company company;

	private UserAccount userAccount;

	private String adminUserName;

	private String faxNo;

	private RegionalCenterZoneLookup regionalCenterZoneLookup;

	private List<Patient> patient;
	
	private Boolean status;
	
	private boolean fromRcebLookup;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Embedded
	public Address1 getReginoalCenterAddress() {
		return reginoalCenterAddress;
	}

	public void setReginoalCenterAddress(Address1 reginoalCenterAddress) {
		this.reginoalCenterAddress = reginoalCenterAddress;
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

	@ManyToOne
	public RegionalCenterZoneLookup getRegionalCenterZoneLookup() {
		return regionalCenterZoneLookup;
	}

	public void setRegionalCenterZoneLookup(RegionalCenterZoneLookup regionalCenterZoneLookup) {
		this.regionalCenterZoneLookup = regionalCenterZoneLookup;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "regionalCenter")
	public List<Patient> getPatient() {
		return patient;
	}

	@JsonProperty
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public boolean isFromRcebLookup() {
		return fromRcebLookup;
	}

	public void setFromRcebLookup(boolean fromRcebLookup) {
		this.fromRcebLookup = fromRcebLookup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
