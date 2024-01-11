package com.openspace.PatientManagement.dto;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDto {

	private String address;

	private String fullName;

	private String email;

	private Integer invoiceId;
	
	private String invoiceNumber;

	private LocalDate issuedDate;

	private LocalDate paymentDueDate;

	private Double totalAmount;

	private Double discount;

	private Double vat;

	private Double grandTotal;

	private List<InvoiceItemsDto> itemsList;
	
	private Long patientId;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int i) {
		this.invoiceId = i;
	}

	public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}

	public LocalDate getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(LocalDate paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public List<InvoiceItemsDto> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<InvoiceItemsDto> itemsList) {
		this.itemsList = itemsList;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

}
