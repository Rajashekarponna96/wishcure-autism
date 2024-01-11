package com.openspace.Model.PatientMgnt.Repositories;

public class MonthlyWiseExpenseDTO {

	String date;
	Double amount;
	
	public MonthlyWiseExpenseDTO(String date,Double amount ) {
		this.date = date;
		this.amount = amount;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
