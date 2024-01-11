package com.openspace.Model.Dto;

import java.util.Map;

import com.stripe.model.Account;
import com.stripe.model.AlternateStatementDescriptors;
import com.stripe.model.Application;
import com.stripe.model.ApplicationFee;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.Charge;
import com.stripe.model.ChargeOutcome;
import com.stripe.model.ChargeRefundCollection;
import com.stripe.model.Customer;
import com.stripe.model.Dispute;
import com.stripe.model.ExpandableField;
import com.stripe.model.ExternalAccount;
import com.stripe.model.FraudDetails;
import com.stripe.model.Invoice;
import com.stripe.model.Order;
import com.stripe.model.Review;
import com.stripe.model.ShippingDetails;
import com.stripe.model.Transfer;

public class ChargeDto {

	public String id;
	public String object;
	public Long amount;
	public Long amountRefunded;
	public ExpandableField<Application> application;
	public ExpandableField<ApplicationFee> applicationFee;
	public AlternateStatementDescriptors alternateStatementDescriptors;
	public ExpandableField<BalanceTransaction> balanceTransaction;
	public Boolean captured;
	public Long created;
	public String currency;
	public ExpandableField<Customer> customer;
	public String description;
	public ExpandableField<Account> destination;
	public ExpandableField<Dispute> dispute;
	public String failureCode;
	public String failureMessage;
	public FraudDetails fraudDetails;
	public ExpandableField<Invoice> invoice;
	public Boolean livemode;
	public Map<String, String> metadata;
	public ChargeOutcome outcome;
	public ExpandableField<Order> order;
	public Boolean paid;
	public String receiptEmail;
	public String receiptNumber;
	public Boolean refunded;
	public ChargeRefundCollection refunds;
	public ExpandableField<Review> review;
	public ShippingDetails shipping;
	public ExternalAccount source;
	public ExpandableField<Transfer> sourceTransfer;
	public String statementDescriptor;
	public String status;
	public ExpandableField<Transfer> transfer;
	public String transferGroup;
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getAmountRefunded() {
		return amountRefunded;
	}
	public void setAmountRefunded(Long amountRefunded) {
		this.amountRefunded = amountRefunded;
	}
	public ExpandableField<Application> getApplication() {
		return application;
	}
	public void setApplication(ExpandableField<Application> application) {
		this.application = application;
	}
	public ExpandableField<ApplicationFee> getApplicationFee() {
		return applicationFee;
	}
	public void setApplicationFee(ExpandableField<ApplicationFee> applicationFee) {
		this.applicationFee = applicationFee;
	}
	public AlternateStatementDescriptors getAlternateStatementDescriptors() {
		return alternateStatementDescriptors;
	}
	public void setAlternateStatementDescriptors(AlternateStatementDescriptors alternateStatementDescriptors) {
		this.alternateStatementDescriptors = alternateStatementDescriptors;
	}
	public ExpandableField<BalanceTransaction> getBalanceTransaction() {
		return balanceTransaction;
	}
	public void setBalanceTransaction(ExpandableField<BalanceTransaction> balanceTransaction) {
		this.balanceTransaction = balanceTransaction;
	}
	public Boolean getCaptured() {
		return captured;
	}
	public void setCaptured(Boolean captured) {
		this.captured = captured;
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
	public ExpandableField<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(ExpandableField<Customer> customer) {
		this.customer = customer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ExpandableField<Account> getDestination() {
		return destination;
	}
	public void setDestination(ExpandableField<Account> destination) {
		this.destination = destination;
	}
	public ExpandableField<Dispute> getDispute() {
		return dispute;
	}
	public void setDispute(ExpandableField<Dispute> dispute) {
		this.dispute = dispute;
	}
	public String getFailureCode() {
		return failureCode;
	}
	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}
	public String getFailureMessage() {
		return failureMessage;
	}
	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	public FraudDetails getFraudDetails() {
		return fraudDetails;
	}
	public void setFraudDetails(FraudDetails fraudDetails) {
		this.fraudDetails = fraudDetails;
	}
	public ExpandableField<Invoice> getInvoice() {
		return invoice;
	}
	public void setInvoice(ExpandableField<Invoice> invoice) {
		this.invoice = invoice;
	}
	public Boolean getLivemode() {
		return livemode;
	}
	public void setLivemode(Boolean livemode) {
		this.livemode = livemode;
	}
	public Map<String, String> getMetadata() {
		return metadata;
	}
	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	public ChargeOutcome getOutcome() {
		return outcome;
	}
	public void setOutcome(ChargeOutcome outcome) {
		this.outcome = outcome;
	}
	public ExpandableField<Order> getOrder() {
		return order;
	}
	public void setOrder(ExpandableField<Order> order) {
		this.order = order;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public String getReceiptEmail() {
		return receiptEmail;
	}
	public void setReceiptEmail(String receiptEmail) {
		this.receiptEmail = receiptEmail;
	}
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public Boolean getRefunded() {
		return refunded;
	}
	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}
	public ChargeRefundCollection getRefunds() {
		return refunds;
	}
	public void setRefunds(ChargeRefundCollection refunds) {
		this.refunds = refunds;
	}
	public ExpandableField<Review> getReview() {
		return review;
	}
	public void setReview(ExpandableField<Review> review) {
		this.review = review;
	}
	public ShippingDetails getShipping() {
		return shipping;
	}
	public void setShipping(ShippingDetails shipping) {
		this.shipping = shipping;
	}
	public ExternalAccount getSource() {
		return source;
	}
	public void setSource(ExternalAccount source) {
		this.source = source;
	}
	public ExpandableField<Transfer> getSourceTransfer() {
		return sourceTransfer;
	}
	public void setSourceTransfer(ExpandableField<Transfer> sourceTransfer) {
		this.sourceTransfer = sourceTransfer;
	}
	public String getStatementDescriptor() {
		return statementDescriptor;
	}
	public void setStatementDescriptor(String statementDescriptor) {
		this.statementDescriptor = statementDescriptor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ExpandableField<Transfer> getTransfer() {
		return transfer;
	}
	public void setTransfer(ExpandableField<Transfer> transfer) {
		this.transfer = transfer;
	}
	public String getTransferGroup() {
		return transferGroup;
	}
	public void setTransferGroup(String transferGroup) {
		this.transferGroup = transferGroup;
	}
	
	
}
