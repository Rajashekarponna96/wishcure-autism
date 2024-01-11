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
public class School implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String identityId;
	
	private String schoolName;

	private Address1 address;
	
	private String fax;
	
	private Company company;

	private String adminUserName;
	
	private UserAccount userAccount;
	
	private boolean productOwnerStatus;
	
	private String email;
	
	private Long mobileNumber;

	private boolean productOwnerhasThis;
	
	private boolean status;
	
	private Long schoolId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@ManyToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	
	@Embedded
	public Address1 getAddress() {
		return address;
	}

	public void setAddress(Address1 address) {
		this.address = address;
	}
	
	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public boolean isProductOwnerStatus() {
		return productOwnerStatus;
	}

	public void setProductOwnerStatus(boolean productOwnerStatus) {
		this.productOwnerStatus = productOwnerStatus;
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

	public boolean isProductOwnerhasThis() {
		return productOwnerhasThis;
	}

	public void setProductOwnerhasThis(boolean productOwnerhasThis) {
		this.productOwnerhasThis = productOwnerhasThis;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	

}
