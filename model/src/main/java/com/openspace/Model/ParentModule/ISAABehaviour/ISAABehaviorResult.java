package com.openspace.Model.ParentModule.ISAABehaviour;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.openspace.Model.DoctorManagement.Patient;

@Entity
public class ISAABehaviorResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Patient patient;

	private double avgPerformanceScorel70;
	
	private double avgPerformanceScore70to106;
	
	private double avgPerformanceScore107to153;
	
	private double avgPerformanceScoreg153;
	
	private double avgPerformanceScore;
	
	private String statement;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.MERGE)
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public double getAvgPerformanceScore() {
		return avgPerformanceScore;
	}

	public void setAvgPerformanceScore(double avgPerformanceScore) {
		this.avgPerformanceScore = avgPerformanceScore;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public double getAvgPerformanceScorel70() {
		return avgPerformanceScorel70;
	}

	public void setAvgPerformanceScorel70(double avgPerformanceScorel70) {
		this.avgPerformanceScorel70 = avgPerformanceScorel70;
	}

	public double getAvgPerformanceScore70to106() {
		return avgPerformanceScore70to106;
	}

	public void setAvgPerformanceScore70to106(double avgPerformanceScore70to106) {
		this.avgPerformanceScore70to106 = avgPerformanceScore70to106;
	}

	public double getAvgPerformanceScore107to153() {
		return avgPerformanceScore107to153;
	}

	public void setAvgPerformanceScore107to153(double avgPerformanceScore107to153) {
		this.avgPerformanceScore107to153 = avgPerformanceScore107to153;
	}

	public double getAvgPerformanceScoreg153() {
		return avgPerformanceScoreg153;
	}

	public void setAvgPerformanceScoreg153(double avgPerformanceScoreg153) {
		this.avgPerformanceScoreg153 = avgPerformanceScoreg153;
	}
	
	
}
