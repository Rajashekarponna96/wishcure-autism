package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.openspace.Model.Lookups.Company;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class Paymethod implements Serializable {

	private Long id;

	private String name;

	private String description;

	private Company company;

	private UserAccount userAccount;

	private String adminUserName;
	
	private boolean productOwnerStatus;

	private boolean productOwnerhasThis;
	
	public boolean isProductOwnerhasThis() {
		return productOwnerhasThis;
	}

	public void setProductOwnerhasThis(boolean productOwnerhasThis) {
		this.productOwnerhasThis = productOwnerhasThis;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public boolean isProductOwnerStatus() {
		return productOwnerStatus;
	}

	public void setProductOwnerStatus(boolean productOwnerStatus) {
		this.productOwnerStatus = productOwnerStatus;
	}

}
