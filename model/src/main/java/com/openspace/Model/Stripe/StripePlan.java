package com.openspace.Model.Stripe;

import java.io.Serializable;
import java.time.LocalDate;
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
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;

@Entity
public class StripePlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String planId;

	private String object;

	private String planName;
	
	private String billingScheme;

	private LocalDate createddate;

	private String currency;
	
	private boolean livemode;
	
	private String nickname;
	
	private String payInterval;
	
	private int payIntervalCount;

	private String productId;

	private String tiersMode;

	private String usageType;

	private Integer trialPeriodDays;

	private List<StripeTier> tiers;
	
	private StripePackage stripePackage;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getBillingScheme() {
		return billingScheme;
	}

	public void setBillingScheme(String billingScheme) {
		this.billingScheme = billingScheme;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getCreateddate() {
		return createddate;
	}

	public void setCreateddate(LocalDate createddate) {
		this.createddate = createddate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	public String getPayInterval() {
		return payInterval;
	}

	public void setPayInterval(String payInterval) {
		this.payInterval = payInterval;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stripePlan")
	public List<StripeTier> getTiers() {
		return tiers;
	}

	public void setTiers(List<StripeTier> tiers) {
		this.tiers = tiers;
	}

	public String getTiersMode() {
		return tiersMode;
	}

	public void setTiersMode(String tiersMode) {
		this.tiersMode = tiersMode;
	}

	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public Integer getTrialPeriodDays() {
		return trialPeriodDays;
	}

	public void setTrialPeriodDays(Integer trialPeriodDays) {
		this.trialPeriodDays = trialPeriodDays;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isLivemode() {
		return livemode;
	}

	public void setLivemode(boolean livemode) {
		this.livemode = livemode;
	}

	public int getPayIntervalCount() {
		return payIntervalCount;
	}

	public void setPayIntervalCount(int payIntervalCount) {
		this.payIntervalCount = payIntervalCount;
	}

	@JsonIgnore
	@OneToOne
	public StripePackage getStripePackage() {
		return stripePackage;
	}

	@JsonProperty
	public void setStripePackage(StripePackage stripePackage) {
		this.stripePackage = stripePackage;
	}


}
