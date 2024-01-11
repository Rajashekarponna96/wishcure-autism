package com.openspace.PatientManagement.dto;

import java.util.ArrayList;
import java.util.List;

public class PatientGoalsReportDto {

	private List<String> labels = new ArrayList<String>();
	
	private List<String> series = new ArrayList<String>();
	
	private List<ArrayList<Integer>> data = new ArrayList<>();

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

	public List<ArrayList<Integer>> getData() {
		return data;
	}

	public void setData(List<ArrayList<Integer>> data) {
		this.data = data;
	}


}
