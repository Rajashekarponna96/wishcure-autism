package com.openspace.Model.Stripe;

public class StripeBank {
	
	private Long id;
	
    private String account_number;
	
	private String country;
	
	private String currency;
	
	private String account_holder_name;
	
	private String account_holder_type;
	
	private String routing_number;
	
	private String token;
	
	private String customerEmail;
	
	private String stripeProductId;
	
	private String stripePlanId;

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccount_holder_name() {
		return account_holder_name;
	}

	public void setAccount_holder_name(String account_holder_name) {
		this.account_holder_name = account_holder_name;
	}

	public String getAccount_holder_type() {
		return account_holder_type;
	}

	public void setAccount_holder_type(String account_holder_type) {
		this.account_holder_type = account_holder_type;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
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
