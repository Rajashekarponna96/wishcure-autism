package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.Lookups.PackageNameMaster;

public class StripeInvoiceDto {

	private Long id;
	
	private String packageNameMaster;
	
	private Long users;
	
	private List<StripeInvoiceItem> stripeInvoiceItems  ;
	
	private Double totalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsers() {
		return users;
	}

	public void setUsers(Long users) {
		this.users = users;
	}

	

	public String getPackageNameMaster() {
		return packageNameMaster;
	}

	public void setPackageNameMaster(String packageNameMaster) {
		this.packageNameMaster = packageNameMaster;
	}

	public List<StripeInvoiceItem> getStripeInvoiceItems() {
		return stripeInvoiceItems;
	}

	public void setStripeInvoiceItems(List<StripeInvoiceItem> stripeInvoiceItems) {
		this.stripeInvoiceItems = stripeInvoiceItems;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	
	
}
