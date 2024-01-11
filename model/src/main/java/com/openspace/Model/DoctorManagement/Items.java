package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Items implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String itemName;

	private String appointmentStartedDate;

	private int quantity;

	private Double unitCost;

	private Double price;

	private AppointmentInvoice appointmentInvoice;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/*public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}*/
	public String getAppointmentStartedDate() {
		return appointmentStartedDate;
	}

	public void setAppointmentStartedDate(String appointmentStartedDate) {
		this.appointmentStartedDate = appointmentStartedDate;
	}
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonIgnore
	@ManyToOne
	public AppointmentInvoice getAppointmentInvoice() {
		return appointmentInvoice;
	}

	@JsonProperty
	public void setAppointmentInvoice(AppointmentInvoice appointmentInvoice) {
		this.appointmentInvoice = appointmentInvoice;
	}

}
