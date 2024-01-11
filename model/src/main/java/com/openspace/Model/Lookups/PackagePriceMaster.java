package com.openspace.Model.Lookups;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateTimeConverter;
import com.openspace.Model.DateConverters.LocalDateTimeDeserializer;
import com.openspace.Model.DateConverters.LocalDateTimeSerializer;
import com.openspace.Model.Payment.Payment;

@Entity
public class PackagePriceMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDateTime createdTime;

	private LocalDateTime modifiedTime;

	private PackageNameMaster packageNameMaster;

	private SubScriptionMaster subScriptionMaster;

	private String packageDescription;

	private Integer minNoOfUsers;

	private Integer maxNoOfUsers;

	private String minDataStorage;

	private String maxDataStorage;

	private Double packagePricePerUsers;

	private Integer validityPeriod;

	private boolean active;

	//private List<UserAccount> userAccounts;
	
	//private List<Company> companies;
	
	//private List<Payment>  payments;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Convert(converter=LocalDateTimeConverter.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	@Convert(converter=LocalDateTimeConverter.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@OneToOne(cascade = CascadeType.MERGE)
	public PackageNameMaster getPackageNameMaster() {
		return packageNameMaster;
	}

	public void setPackageNameMaster(PackageNameMaster packageNameMaster) {
		this.packageNameMaster = packageNameMaster;
	}

	@OneToOne(cascade = CascadeType.MERGE)
	public SubScriptionMaster getSubScriptionMaster() {
		return subScriptionMaster;
	}

	public void setSubScriptionMaster(SubScriptionMaster subScriptionMaster) {
		this.subScriptionMaster = subScriptionMaster;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public Integer getMinNoOfUsers() {
		return minNoOfUsers;
	}

	public void setMinNoOfUsers(Integer minNoOfUsers) {
		this.minNoOfUsers = minNoOfUsers;
	}

	public Integer getMaxNoOfUsers() {
		return maxNoOfUsers;
	}

	public void setMaxNoOfUsers(Integer maxNoOfUsers) {
		this.maxNoOfUsers = maxNoOfUsers;
	}

	public String getMinDataStorage() {
		return minDataStorage;
	}

	public void setMinDataStorage(String minDataStorage) {
		this.minDataStorage = minDataStorage;
	}

	public String getMaxDataStorage() {
		return maxDataStorage;
	}

	public void setMaxDataStorage(String maxDataStorage) {
		this.maxDataStorage = maxDataStorage;
	}

	public Double getPackagePricePerUsers() {
		return packagePricePerUsers;
	}

	public void setPackagePricePerUsers(Double packagePricePerUsers) {
		this.packagePricePerUsers = packagePricePerUsers;
	}

	public Integer getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(Integer validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	/*@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="packagePriceMaster")
	public List<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	@JsonProperty
	public void setUserAccounts(List<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}*/
	/*@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="packagePriceMaster")
	public List<Company> getCompanies() {
		return companies;
	}
	@JsonProperty
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
*//*
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="packagePriceMaster")
	public List<Payment> getPayments() {
		return payments;
	}

	@JsonProperty
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}*/



}
