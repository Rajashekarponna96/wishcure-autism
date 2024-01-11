package com.openspace.Model.Scales;

import java.util.List;

import com.openspace.Model.Clusters.MentalClusters;

public class MentalScaleDto {
	
	private List<MentalScales> mentalScales;
	
	private MentalClusters mentalClusters;
	 
	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<MentalScales> getMentalScales() {
		return mentalScales;
	}

	public void setMentalScales(List<MentalScales> mentalScales) {
		this.mentalScales = mentalScales;
	}

	public MentalClusters getMentalClusters() {
		return mentalClusters;
	}

	public void setMentalClusters(MentalClusters mentalClusters) {
		this.mentalClusters = mentalClusters;
	}
	
	
	
}
