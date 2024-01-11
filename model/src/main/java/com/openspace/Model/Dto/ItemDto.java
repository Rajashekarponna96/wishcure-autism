package com.openspace.Model.Dto;

import java.time.LocalDate;

import javax.persistence.Convert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;

public class ItemDto {

	private LocalDate appointmentStartedDate;
	
	private String itemName;
	
	private Double price;
	
	//private Double totalAmount;
	
	
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getAppointmentStartedDate() {
		return appointmentStartedDate;
	}

	public void setAppointmentStartedDate(LocalDate appointmentStartedDate) {
		this.appointmentStartedDate = appointmentStartedDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/*public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}*/
	
}
