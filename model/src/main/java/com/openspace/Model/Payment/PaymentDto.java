package com.openspace.Model.Payment;

public class PaymentDto {

	private String source;

	private Integer amount;

	private String currency;

	private String description;
	
	private String email;
	
	private String stripeProductId;
	
	private String stripePlanId;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStripeProductId() {
		return stripeProductId;
	}

	public void setStripeProductId(String stripeProductId) {
		this.stripeProductId = stripeProductId;
	}

	public String getStripePlanId() {
		return stripePlanId;
	}

	public void setStripePlanId(String stripePlanId) {
		this.stripePlanId = stripePlanId;
	}

}
