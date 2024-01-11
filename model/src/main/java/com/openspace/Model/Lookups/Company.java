package com.openspace.Model.Lookups;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String companyName;

	private String logoUrl;

	private List<Site> sites;

	private Address1 address;

	private List<UserAccount> userAccounts;

	private CompanyStatus companyStatus;

	//private PackagePriceMaster packagePriceMaster;

	private CompanyType companyType;
	
	private List<ClientType> clientTypes;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	@Embedded
	public Address1 getAddress1() {
		return address;
	}

	public void setAddress1(Address1 address) {
		this.address = address;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@OneToMany
	public List<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	@Enumerated(EnumType.STRING)
	public CompanyStatus getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(CompanyStatus companyStatus) {
		this.companyStatus = companyStatus;
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*@ManyToOne
	public PackagePriceMaster getPackagePriceMaster() {
		return packagePriceMaster;
	}

	public void setPackagePriceMaster(PackagePriceMaster packagePriceMaster) {
		this.packagePriceMaster = packagePriceMaster;
	}
*/
	@ManyToOne
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@OneToMany
	public List<ClientType> getClientTypes() {
		return clientTypes;
	}

	public void setClientTypes(List<ClientType> clientTypes) {
		this.clientTypes = clientTypes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
