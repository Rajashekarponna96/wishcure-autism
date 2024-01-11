package com.openspace.Model.Dto;

import com.stripe.model.InvoiceLineItemCollection;

public class InvoiceStipeDto {

public String id;
public String object;
public Float amountDue;
/*public Long applicationFee;
public Integer attemptCount;
public Boolean attempted;
public String billing;
public ExpandableField<Charge> charge;
public Boolean closed;*/
public Long created;
public String currency;
public String customer;
public String date;
/*public String description;
public Discount discount;
public Long dueDate;
public Long endingBalance;
public Boolean forgiven;
public InvoiceLineItemCollection lines;
public Boolean livemode;
public Map<String, String> metadata;
public Long nextPaymentAttempt;*/
public InvoiceLineItemCollection lines;


public String number;
public Boolean paid;
/*public Long periodEnd;
public Long periodStart;
public String receiptNumber;
public Long startingBalance;
public String statementDescriptor;
public ExpandableField<Subscription> subscription;
public Long subscriptionProrationDate;*/
public Float subtotal;
public Long tax;
public Double taxPercent;
public Float total;
public Long webhooksDeliveredAt;

public String description;
public String end;
public String start;
public Integer quantity;
public String unitPrice;
public String desc;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getObject() {
	return object;
}
public void setObject(String object) {
	this.object = object;
}
public Float getAmountDue() {
	return amountDue;
}
public void setAmountDue(Float amountDue) {
	this.amountDue = amountDue;
}
public Long getCreated() {
	return created;
}
public void setCreated(Long created) {
	this.created = created;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public String getCustomer() {
	return customer;
}
public void setCustomer(String customer) {
	this.customer = customer;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public Boolean getPaid() {
	return paid;
}
public void setPaid(Boolean paid) {
	this.paid = paid;
}
public Float getSubtotal() {
	return subtotal;
}
public void setSubtotal(Float subtotal) {
	this.subtotal = subtotal;
}
public Long getTax() {
	return tax;
}
public void setTax(Long tax) {
	this.tax = tax;
}
public Double getTaxPercent() {
	return taxPercent;
}
public void setTaxPercent(Double taxPercent) {
	this.taxPercent = taxPercent;
}
public Float getTotal() {
	return total;
}
public void setTotal(Float total) {
	this.total = total;
}
public Long getWebhooksDeliveredAt() {
	return webhooksDeliveredAt;
}
public void setWebhooksDeliveredAt(Long webhooksDeliveredAt) {
	this.webhooksDeliveredAt = webhooksDeliveredAt;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getEnd() {
	return end;
}
public void setEnd(String end) {
	this.end = end;
}
public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public String getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(String unitPrice) {
	this.unitPrice = unitPrice;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}



}
