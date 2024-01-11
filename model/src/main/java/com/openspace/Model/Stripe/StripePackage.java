package com.openspace.Model.Stripe;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class StripePackage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String packageName;

	private StripePlan plan;

	private String packageId;
	
	private List<UserAccount> userAccounts;
	
	private List<StripePayment> stripePayments;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public StripePlan getPlan() {
		return plan;
	}

	public void setPlan(StripePlan plan) {
		this.plan = plan;
	}

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="stripePackage")
	public List<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	@JsonProperty
	public void setUserAccounts(List<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="stripePackage")
	public List<StripePayment> getStripePayments() {
		return stripePayments;
	}
	@JsonProperty
	public void setStripePayments(List<StripePayment> stripePayments) {
		this.stripePayments = stripePayments;
	}

}
