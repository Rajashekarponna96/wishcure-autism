package com.openspace.Model.Lookups;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RegionalCenterLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String email;

	private Long mobileNumber;

	private Address1 reginoalCenterAddress;

	private String faxNo;
	
	private RegionalCenterZoneLookup regionalCenterZoneLookup;

	private Boolean status;
	
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
	public RegionalCenterZoneLookup getRegionalCenterZoneLookup() {
		return regionalCenterZoneLookup;
	}

	public void setRegionalCenterZoneLookup(RegionalCenterZoneLookup regionalCenterZone) {
		this.regionalCenterZoneLookup = regionalCenterZone;
	}
	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	

}
