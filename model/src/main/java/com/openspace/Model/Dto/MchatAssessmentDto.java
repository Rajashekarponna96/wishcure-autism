package com.openspace.Model.Dto;

import java.util.List;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.ParentModule.Mchart.MchartLookup;

public class MchatAssessmentDto {

	private Patient patient;

	private List<MchartLookup> mchartLookups;

	private List<Mchart> mcharts;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<MchartLookup> getMchartLookups() {
		return mchartLookups;
	}

	public void setMchartLookups(List<MchartLookup> mchartLookups) {
		this.mchartLookups = mchartLookups;
	}

	public List<Mchart> getMcharts() {
		return mcharts;
	}

	public void setMcharts(List<Mchart> mcharts) {
		this.mcharts = mcharts;
	}

}
