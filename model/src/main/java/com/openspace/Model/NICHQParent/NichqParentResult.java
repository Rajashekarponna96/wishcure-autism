package com.openspace.Model.NICHQParent;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.openspace.Model.DoctorManagement.Patient;

@Entity
public class NichqParentResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Patient patient;

	private int score1to9;

	private int score10to18;

	private int score1to18;

	private int score19to26;

	private int score27to40;

	private int score41to47;

	private int score48to55;

	private double avgPerformanceScore;

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

	public int getScore1to9() {
		return score1to9;
	}

	public void setScore1to9(int score1to9) {
		this.score1to9 = score1to9;
	}

	public int getScore10to18() {
		return score10to18;
	}

	public void setScore10to18(int score10to18) {
		this.score10to18 = score10to18;
	}

	public int getScore1to18() {
		return score1to18;
	}

	public void setScore1to18(int score1to18) {
		this.score1to18 = score1to18;
	}

	public int getScore19to26() {
		return score19to26;
	}

	public void setScore19to26(int score19to26) {
		this.score19to26 = score19to26;
	}

	public int getScore27to40() {
		return score27to40;
	}

	public void setScore27to40(int score27to40) {
		this.score27to40 = score27to40;
	}

	public int getScore41to47() {
		return score41to47;
	}

	public void setScore41to47(int score41to47) {
		this.score41to47 = score41to47;
	}

	public int getScore48to55() {
		return score48to55;
	}

	public void setScore48to55(int score48to55) {
		this.score48to55 = score48to55;
	}

	public double getAvgPerformanceScore() {
		return avgPerformanceScore;
	}

	public void setAvgPerformanceScore(double avgPerformanceScore) {
		this.avgPerformanceScore = avgPerformanceScore;
	}
}
