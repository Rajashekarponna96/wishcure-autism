package com.openspace.Model.Lookups;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.UserManagement.Role;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String departmentName; 
	
	private String descriptionName;
	
	private boolean status;
	
	private java.sql.Timestamp createdDate;
	
	private java.sql.Timestamp modifiedDate;
	
	private Company company;
	
	private UserAccount userAccount;
	
	private String adminUserName;
	
	private Site site;
	
	private boolean productOwnerStatus;
	
	private boolean productOwnerhasThis;
	
	private List<Patient> patients;
	
	private Role role;
	
	private PatientRegistrationType patientRegistrationType;
	
	private String type;
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescriptionName() {
		return descriptionName;
	}

	public void setDescriptionName(String descriptionName) {
		this.descriptionName = descriptionName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.sql.Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public java.sql.Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(java.sql.Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	

	
	@JsonIgnore
	@ManyToOne
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne
	@JsonIgnore
	public UserAccount getUserAccount() {
		return userAccount;
	}
	@JsonProperty
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

	public boolean isProductOwnerhasThis() {
		return productOwnerhasThis;
	}

	public void setProductOwnerhasThis(boolean productOwnerhasThis) {
		this.productOwnerhasThis = productOwnerhasThis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + "]";
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,mappedBy="departments")
	public List<Patient> getPatients() {
		return patients;
	}

	@JsonProperty
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	public Role getRole() {
		return role;
	}

	@JsonProperty
	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	@ManyToOne
	public PatientRegistrationType getPatientRegistrationType() {
		return patientRegistrationType;
	}

	@JsonProperty
	public void setPatientRegistrationType(PatientRegistrationType patientRegistrationType) {
		this.patientRegistrationType = patientRegistrationType;
	}
	
	

	
	
	
}
