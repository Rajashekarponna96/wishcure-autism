package com.openspace.Model.Payment;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.Lookups.ClientType;
import com.openspace.Model.Lookups.Department;

@Entity
public class Payment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private LocalDate paidDate;

	private String gatewayStatus;

	private LocalDate modifiedDate;
	
	/*private Transaction trasaction;
	
	private PackagePriceMaster packagePriceMaster;*/

	/*private PaymentType paymentTypes;*/
	
	private Paymethod paymentTypes;
	
	private String checkIssuedBank;
	
	private String ddIssuedBank;
	
	private Long checkNumber;
	
	private Long ddNumber;

	private Double assignedAmount;
	
	private int noOfCycles;

	private LocalDate therapyDoneDate;

	private Patient patient;
	
	private String userName;

	private Double paidAmount;

	private Double totalAmount;

	private Double remainingAmount;

	private ModeOfPaymentType modeOfPaymentTypes;
	
	private Doctor doctor;
	
   private Month month;
	
	private Integer year;
	
	private Occurance occurance;
	
	private String invoiceNo;
	
	private SubAppointmentStatus paymentStatus;
	
	private ClientType clientType;
	
	private Department department;
	
	private Integer totalSessions;
	
	private Integer completeSessions;
	
	private Integer awaitingSessions;
	
	private Integer cancelledSessions;
	
	private Integer paidSessions;
	
	private Integer unPaidSessions;
	
    private LocalDate createdDate;
	
    private String createdBy;
    
    private String modifiedBy;
    
    private Double actualPayment;
    
    private String paymentId;
    
    private Double perSessionCost;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

	

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Double getPerSessionCost() {
		return perSessionCost;
	}

	public void setPerSessionCost(Double perSessionCost) {
		this.perSessionCost = perSessionCost;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public String getGatewayStatus() {
		return gatewayStatus;
	}

	public void setGatewayStatus(String gatewayStatus) {
		this.gatewayStatus = gatewayStatus;
	}
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/*@OneToOne(cascade=CascadeType.ALL)
	public Transaction getTrasaction() {
		return trasaction;
	}

	public void setTrasaction(Transaction trasaction) {
		this.trasaction = trasaction;
	}

	@ManyToOne
	public PackagePriceMaster getPackagePriceMaster() {
		return packagePriceMaster;
	}

	public void setPackagePriceMaster(PackagePriceMaster packagePriceMaster) {
		this.packagePriceMaster = packagePriceMaster;
	}*/
///Naveen
	/*@Enumerated(EnumType.STRING)
	public PaymentType getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentType paymentTypes) {
		this.paymentTypes = paymentTypes;
	}*/

	@ManyToOne
	public Paymethod getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(Paymethod paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
    
	public int getNoOfCycles() {
		return noOfCycles;
	}

	public void setNoOfCycles(int noOfCycles) {
		this.noOfCycles = noOfCycles;
	}

	

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getTherapyDoneDate() {
		return therapyDoneDate;
	}

	public void setTherapyDoneDate(LocalDate therapyDoneDate) {
		this.therapyDoneDate = therapyDoneDate;
	}

	@JsonIgnore
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}
	@JsonProperty
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(Double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	@Enumerated(EnumType.STRING)
	public ModeOfPaymentType getModeOfPaymentTypes() {
		return modeOfPaymentTypes;
	}

	public void setModeOfPaymentTypes(ModeOfPaymentType modeOfPaymentTypes) {
		this.modeOfPaymentTypes = modeOfPaymentTypes;
	}

	public Double getAssignedAmount() {
		return assignedAmount;
	}

	public void setAssignedAmount(Double assignedAmount) {
		this.assignedAmount = assignedAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCheckIssuedBank() {
		return checkIssuedBank;
	}

	public void setCheckIssuedBank(String checkIssuedBank) {
		this.checkIssuedBank = checkIssuedBank;
	}

	public String getDdIssuedBank() {
		return ddIssuedBank;
	}

	public void setDdIssuedBank(String ddIssuedBank) {
		this.ddIssuedBank = ddIssuedBank;
	}

	public Long getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(Long checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Long getDdNumber() {
		return ddNumber;
	}

	public void setDdNumber(Long ddNumber) {
		this.ddNumber = ddNumber;
	}

	@ManyToOne
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	@Enumerated(EnumType.STRING)
	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	@Enumerated(EnumType.STRING)
	public Occurance getOccurance() {
		return occurance;
	}

	public void setOccurance(Occurance occurance) {
		this.occurance = occurance;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
    @Enumerated(EnumType.STRING)
	public SubAppointmentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(SubAppointmentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@ManyToOne
	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	@ManyToOne
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getTotalSessions() {
		return totalSessions;
	}

	public void setTotalSessions(Integer totalSessions) {
		this.totalSessions = totalSessions;
	}

	public Integer getCompleteSessions() {
		return completeSessions;
	}

	public void setCompleteSessions(Integer completeSessions) {
		this.completeSessions = completeSessions;
	}

	public Integer getAwaitingSessions() {
		return awaitingSessions;
	}

	public void setAwaitingSessions(Integer awaitingSessions) {
		this.awaitingSessions = awaitingSessions;
	}

	public Integer getCancelledSessions() {
		return cancelledSessions;
	}

	public void setCancelledSessions(Integer cancelledSessions) {
		this.cancelledSessions = cancelledSessions;
	}

	public Integer getPaidSessions() {
		return paidSessions;
	}

	public void setPaidSessions(Integer paidSessions) {
		this.paidSessions = paidSessions;
	}

	public Integer getUnPaidSessions() {
		return unPaidSessions;
	}

	public void setUnPaidSessions(Integer unPaidSessions) {
		this.unPaidSessions = unPaidSessions;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Double getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(Double actualPayment) {
		this.actualPayment = actualPayment;
	}
	
}
