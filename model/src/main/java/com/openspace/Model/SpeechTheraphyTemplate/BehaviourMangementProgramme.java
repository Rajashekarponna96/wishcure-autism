package com.openspace.Model.SpeechTheraphyTemplate;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DoctorManagement.Patient;

@Entity
@Access(AccessType.PROPERTY)
public class BehaviourMangementProgramme implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Patient patient;

	private LocalDate date;
	
	private String identification;
	
	private String hierarchy;
	
	private String target;
	
	private String rewards;
	
	private String assets;
	
	private String baseline;
	
	private String functionalAnalysis;
	
	private String behaviouralPackage;
	
	private String followUp;
	
	private String consequences;
	
	private String siginificant;
	
	private String functionsIdentifiedWhen;
	
	private String functionsIdentifiedWhere;
	
	private String functionsIdentifiedWithWhom;
	
	private String otherSiginificant;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@OneToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}

	public String getBaseline() {
		return baseline;
	}

	public void setBaseline(String baseline) {
		this.baseline = baseline;
	}

	public String getFunctionalAnalysis() {
		return functionalAnalysis;
	}

	public void setFunctionalAnalysis(String functionalAnalysis) {
		this.functionalAnalysis = functionalAnalysis;
	}

	public String getBehaviouralPackage() {
		return behaviouralPackage;
	}

	public void setBehaviouralPackage(String behaviouralPackage) {
		this.behaviouralPackage = behaviouralPackage;
	}

	public String getFollowUp() {
		return followUp;
	}

	public void setFollowUp(String followUp) {
		this.followUp = followUp;
	}

	public String getConsequences() {
		return consequences;
	}

	public void setConsequences(String consequences) {
		this.consequences = consequences;
	}

	public String getSiginificant() {
		return siginificant;
	}

	public void setSiginificant(String siginificant) {
		this.siginificant = siginificant;
	}

	public String getFunctionsIdentifiedWhen() {
		return functionsIdentifiedWhen;
	}

	public void setFunctionsIdentifiedWhen(String functionsIdentifiedWhen) {
		this.functionsIdentifiedWhen = functionsIdentifiedWhen;
	}

	public String getFunctionsIdentifiedWhere() {
		return functionsIdentifiedWhere;
	}

	public void setFunctionsIdentifiedWhere(String functionsIdentifiedWhere) {
		this.functionsIdentifiedWhere = functionsIdentifiedWhere;
	}

	public String getFunctionsIdentifiedWithWhom() {
		return functionsIdentifiedWithWhom;
	}

	public void setFunctionsIdentifiedWithWhom(String functionsIdentifiedWithWhom) {
		this.functionsIdentifiedWithWhom = functionsIdentifiedWithWhom;
	}

	public String getOtherSiginificant() {
		return otherSiginificant;
	}

	public void setOtherSiginificant(String otherSiginificant) {
		this.otherSiginificant = otherSiginificant;
	}
	
	
	
	

	

	
	 

}
