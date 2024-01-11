package com.openspace.Model.Scales;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.openspace.Model.Clusters.MentalClusters;
import com.openspace.Model.MentalReferenceProfile.MentalScaleStatus;

@Entity
public class MentalScales implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String itemNo;

	private String itemDescripation;

	private String percent50;

	private String percent3;

	private String percent97;

	private Integer percent97Rank;

	private MentalClusters mentalClusters;

	private MentalScaleStatus mentalScaleStatus;;
	
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

	@ManyToOne
	public MentalClusters getMentalClusters() {
		return mentalClusters;
	}

	public void setMentalClusters(MentalClusters mentalClusters) {
		this.mentalClusters = mentalClusters;
	}

	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Enumerated(EnumType.STRING)
	public MentalScaleStatus getMentalScaleStatus() {
		return mentalScaleStatus;
	}

	public void setMentalScaleStatus(MentalScaleStatus mentalScaleStatus) {
		this.mentalScaleStatus = mentalScaleStatus;
	}


}
