package com.openspace.Model.Stripe;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;

@Entity
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String transactionName;

	private String description;

	private TransactionStatus transactionStatus;

	private LocalDate transactionDate;

	private String paymentInvoiceNumber;

	private String paymentTransactionNumber;

	private String payby;
	
	private String status;
	
	private String chargeTransactionId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.STRING)
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPaymentInvoiceNumber() {
		return paymentInvoiceNumber;
	}

	public void setPaymentInvoiceNumber(String paymentInvoiceNumber) {
		this.paymentInvoiceNumber = paymentInvoiceNumber;
	}

	public String getPaymentTransactionNumber() {
		return paymentTransactionNumber;
	}

	public void setPaymentTransactionNumber(String paymentTransactionNumber) {
		this.paymentTransactionNumber = paymentTransactionNumber;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChargeTransactionId() {
		return chargeTransactionId;
	}

	public void setChargeTransactionId(String chargeTransactionId) {
		this.chargeTransactionId = chargeTransactionId;
	}

	
}
