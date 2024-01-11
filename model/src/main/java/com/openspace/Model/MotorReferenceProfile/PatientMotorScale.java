package com.openspace.Model.MotorReferenceProfile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author SYSTEM
 *
 */
@Entity
public class PatientMotorScale implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long motorScaleId;

	private String itemNo;

	private String itemDescripation;

	private String percent50;

	private String percent3;

	private String percent97;

	private Integer percent97Rank;

	private Long motorClusterId;
	
	private MotorScaleStatus motorScaleStatus;

	private MotorResult motorResult;
	
	private String note;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemDescripation() {
		return itemDescripation;
	}

	public void setItemDescripation(String itemDescripation) {
		this.itemDescripation = itemDescripation;
	}

	public String getPercent50() {
		return percent50;
	}

	public void setPercent50(String percent50) {
		this.percent50 = percent50;
	}

	public String getPercent3() {
		return percent3;
	}

	public void setPercent3(String percent3) {
		this.percent3 = percent3;
	}

	public String getPercent97() {
		return percent97;
	}

	public void setPercent97(String percent97) {
		this.percent97 = percent97;
	}

	public Integer getPercent97Rank() {
		return percent97Rank;
	}

	public void setPercent97Rank(Integer percent97Rank) {
		this.percent97Rank = percent97Rank;
	}

	
	public Long getMotorClusterId() {
		return motorClusterId;
	}

	public void setMotorClusterId(Long motorClusterId) {
		this.motorClusterId = motorClusterId;
	}
	
	@Enumerated(EnumType.STRING)
	public MotorScaleStatus getMotorScaleStatus() {
		return motorScaleStatus;
	}

	public void setMotorScaleStatus(MotorScaleStatus motorScaleStatus) {
		this.motorScaleStatus = motorScaleStatus;
	}
	
	@JsonIgnore
	@ManyToOne
	public MotorResult getMotorResult() {
		return motorResult;
	}

	public void setMotorResult(MotorResult motorResult) {
		this.motorResult = motorResult;
	}

	public Long getMotorScaleId() {
		return motorScaleId;
	}

	public void setMotorScaleId(Long motorScaleId) {
		this.motorScaleId = motorScaleId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}
