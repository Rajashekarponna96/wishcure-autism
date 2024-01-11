package com.openspace.Model.NichqTeachers;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.openspace.Model.DoctorManagement.Patient;

@Entity
public class NichqTeacherResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Patient patient;

	private int score1to9;

	private int score10to18;

	private int score1to18;

	private int score19to28;

	private int score29to35;

	private int score36to43;

	private double averageScore;

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

	public int getScore19to28() {
		return score19to28;
	}

	public void setScore19to28(int score19to28) {
		this.score19to28 = score19to28;
	}

	public int getScore29to35() {
		return score29to35;
	}

	public void setScore29to35(int score29to35) {
		this.score29to35 = score29to35;
	}

	public int getScore36to43() {
		return score36to43;
	}

	public void setScore36to43(int score36to43) {
		this.score36to43 = score36to43;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

}
