package com.openspace.Model.Payment;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Currency;
import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.Lookups.ClinicExpenseType;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class MonthlyClinicExpense implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDate paidDate;
	
	private Paymethod paymentTypes;

	private ClinicExpenseType clinicExpenseType;

	private Company company;
	
	private Currency currency;
	
	private UserAccount userAccount;
	
	private String adminUserName;
	
	private Double paidAmount;

	private Double totalAmount;

	private Double remainingAmount;
	
	private ModeOfPaymentType modeOfPaymentTypes;
	
	private SubAppointmentStatus paymentStatus;
	
	private String description;
	
	private String createdBy;
    
    private String modifiedBy;
    
    public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	private String fileURL;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	@ManyToOne
	public Paymethod getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(Paymethod paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	@ManyToOne
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
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
	@Enumerated(EnumType.STRING)
	public SubAppointmentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(SubAppointmentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@ManyToOne
	public ClinicExpenseType getClinicExpenseType() {
		return clinicExpenseType;
	}

	public void setClinicExpenseType(ClinicExpenseType clinicExpenseType) {
		this.clinicExpenseType = clinicExpenseType;
	}

	@JsonIgnore
	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@JsonIgnore
	@ManyToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}


	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	
}
