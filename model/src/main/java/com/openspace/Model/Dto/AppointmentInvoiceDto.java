package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.DoctorManagement.AppointmentInvoice;
import com.openspace.Model.DoctorManagement.Items;

public class AppointmentInvoiceDto {

private Long id;
	
	private String invoiceNumber;
	
	private List<Items> items;
	
	private String custNotes;
	
	private String termsConditions;
	
	private Long patientId;
/*	
	private AppointmentInvoice appointmentInvoice;
	
	private List<AppointmentInvoice> appointmentInvoices;
	
	public AppointmentInvoice getAppointmentInvoice() {
		return appointmentInvoice;
	}

	public void setAppointmentInvoice(AppointmentInvoice appointmentInvoice) {
		this.appointmentInvoice = appointmentInvoice;
	}

	public List<AppointmentInvoice> getAppointmentInvoices() {
		return appointmentInvoices;
	}

	public void setAppointmentInvoices(List<AppointmentInvoice> appointmentInvoices) {
		this.appointmentInvoices = appointmentInvoices;
	}
*/
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public String getCustNotes() {
		return custNotes;
	}

	public void setCustNotes(String custNotes) {
		this.custNotes = custNotes;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
	
}
