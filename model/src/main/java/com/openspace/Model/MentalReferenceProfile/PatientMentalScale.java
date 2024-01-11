package com.openspace.Model.MentalReferenceProfile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PatientMentalScale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long mentalScaleId;

	private String itemDescripation;

	private String percent50;

	private String percent3;

	private String percent97;

	private Integer percent97Rank;

	private Long mentalClusterId;

	private MentalScaleStatus mentalScaleStatus;

	private MentalResult mentalResult;
	
	private String note;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMentalScaleId() {
		return mentalScaleId;
	}

	public void setMentalScaleId(Long mentalScaleId) {
		this.mentalScaleId = mentalScaleId;
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

	public Long getMentalClusterId() {
		return mentalClusterId;
	}

	public void setMentalClusterId(Long mentalClusterId) {
		this.mentalClusterId = mentalClusterId;
	}

	@Enumerated(EnumType.STRING)
	public MentalScaleStatus getMentalScaleStatus() {
		return mentalScaleStatus;
	}

	public void setMentalScaleStatus(MentalScaleStatus mentalScaleStatus) {
		this.mentalScaleStatus = mentalScaleStatus;
	}

	@JsonIgnore
	@ManyToOne
	public MentalResult getMentalResult() {
		return mentalResult;
	}

	@JsonProperty
	public void setMentalResult(MentalResult mentalResult) {
		this.mentalResult = mentalResult;
	}

	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "PatientMentalScale [id=" + id + ", mentalScaleId=" + mentalScaleId + ", itemDescripation="
				+ itemDescripation + ", percent50=" + percent50 + ", percent3=" + percent3 + ", percent97=" + percent97
				+ ", percent97Rank=" + percent97Rank + ", mentalClusterId=" + mentalClusterId + ", mentalScaleStatus="
				+ mentalScaleStatus + ", mentalResult=" + mentalResult + "]";
	}

}
