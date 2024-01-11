package com.openspace.HospitalMgnt.Site;

import com.openspace.Model.Lookups.City;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.Country;
import com.openspace.Model.Lookups.State;

public class SiteDto {

	private Long id;
	
	private String siteName;

	private Boolean status;

	private String companyAdminUsername;

	private String country;
	
	private String state;
	
	private String city;
	
	private String address1;
	
	private String address2;
	
	private String zipcode;
	
	private Company company;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCompanyAdminUsername() {
		return companyAdminUsername;
	}

	public void setCompanyAdminUsername(String companyAdminUsername) {
		this.companyAdminUsername = companyAdminUsername;
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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

}
