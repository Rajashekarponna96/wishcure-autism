package com.openspace.Model.Dto;

import java.time.LocalDate;

import javax.persistence.Convert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.Lookups.ClientType;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.Payment.ModeOfPaymentType;

public class SearchPatientDto {
	
	private LocalDate startedDate;
	
	private LocalDate endDate;

	private ClientType clientType;
	
	private Long clientTypeId;
	
	private Long departmentId;
	
	private PaidStatus status;
	
	private ModeOfPaymentType modeOfPaymentTypes;
	
	private Occurance occurance;
	
	private Department department;

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public PaidStatus getStatus() {
		return status;
	}

	public void setStatus(PaidStatus status) {
		this.status = status;
	}

	public ModeOfPaymentType getModeOfPaymentTypes() {
		return modeOfPaymentTypes;
	}

	public void setModeOfPaymentTypes(ModeOfPaymentType modeOfPaymentTypes) {
		this.modeOfPaymentTypes = modeOfPaymentTypes;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Occurance getOccurance() {
		return occurance;
	}

	public void setOccurance(Occurance occurance) {
		this.occurance = occurance;
	}
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(LocalDate startedDate) {
		this.startedDate = startedDate;
	}
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Long getClientTypeId() {
		return clientTypeId;
	}

	public void setClientTypeId(Long clientTypeId) {
		this.clientTypeId = clientTypeId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
}
