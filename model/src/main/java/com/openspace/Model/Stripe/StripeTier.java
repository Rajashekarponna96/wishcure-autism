package com.openspace.Model.Stripe;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class StripeTier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private Long upTo;

	private StripePlan stripePlan;
	
	private String userCount;

	/*public StripeTier(PlanTier tier) {
		this.amount = tier.getAmount();
		this.upTo = tier.getUpTo();
	}*/

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUpTo() {
		return upTo;
	}

	public void setUpTo(Long upTo) {
		this.upTo = upTo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@JsonIgnore
	@ManyToOne
	public StripePlan getStripePlan() {
		return stripePlan;
	}

	@JsonProperty
	public void setStripePlan(StripePlan stripePlan) {
		this.stripePlan = stripePlan;
	}

	public String getUserCount() {
		return userCount;
	}

	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	
}
