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
public class SpeechEvaluation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Patient patient;

	private LocalDate date;

	private String oralLipsAppearance;

	private String oralMandibleAppearance;

	private String oralTongueAppearance;

	private String oralTeethAppearance;

	private String oralHardPlateAppearance;

	private String oralSoftPlateAppearance;

	private String oralLipsFunction;

	private String oralMandibleFunction;

	private String oralTongueFunction;

	private String oralTeethFunction;

	private String oralHardPlateFunction;

	private String oralSoftPlateFunction;

	private String spontaneousVocalization;

	private String vocalizationOnDemand;

	private String durationControl;

	private String loudnessControlLoudVoice;

	private String loudnessControlSoftVoice;

	private String loudnessControlWishperVoice;

	private String loudnessControlLoudness;

	private String pitchControlHigh;

	private String pitchControlMid;

	private String pitchControlLow;


	// Phonatic level assesment Segmantal

	private String phonaticBilabialsP;
	private String phonaticBilabialsPh;
	private String phonaticBilabialsB;
	private String phonaticBilabialsBh;
	private String phonaticBilabialsM;


	private String phonaticLoadedPathL;
	private String phonaticLoadedPathV;


	private String phonaticDentalsT;
	private String phonaticDentalsTh;
	private String phonaticDentalsD;
	private String phonaticDentalsDh;

	private String phonaticRetroflexT;
	private String phonaticRetroflexTt;
	private String phonaticRetroflexD;
	private String phonaticRetroflexDd;

	private String phonaticPaltalsC;
	private String phonaticPaltalsCh;
	private String phonaticPaltalsJ;
	private String phonaticPaltalsJh;

	private String phonaticVelarsK;
	private String phonaticVelarsKh;
	private String phonaticVelarsG;
	private String phonaticVelarsGh;

	private String phonaticGlottals;

	// Phonological level assesment Segmantal

	private String phonologicalBilabialsP;
	private String phonologicalBilabialsPh;
	private String phonologicalBilabialsB;
	private String phonologicalBilabialsBh;
	private String phonologicalBilabialsM;

	private String phonologicalDentalsT;
	private String phonologicalDentalsTh;
	private String phonologicalDentalsD;
	private String phonologicalDentalsDh;

	private String phonologicalAlveolarsL;
	private String phonologicalAlveolarsN;


	private String phonologicalRetroflexT;
	private String phonologicalRetroflexTt;
	private String phonologicalRetroflexD;
	private String phonologicalRetroflexDd;

	private String phonologicalPaltalsC;
	private String phonologicalPaltalsCh;
	private String phonologicalPaltalsJ;
	private String phonologicalPaltalsJh;


	private String phonologicalVelarsK;
	private String phonologicalVelarsKh;
	private String phonologicalVelarsG;
	private String phonologicalVelarsGh;

	private String phonologicalGlottals;

	private String phonologicalEmphsis;

	private String phonologicalPhrasing;

	private String phonologicalRate;

	// Vowels present/absent


	private boolean vowelA;

	private boolean vowelAA;

	private boolean vowelI;

	private boolean vowelII;

	private boolean vowelU;

	private boolean vowelUU;

	private boolean vowelE;

	private boolean vowelEE;

	private boolean vowelAE;

	private boolean vowelO;

	private boolean vowelOO;

	private boolean vowelAU;







	public String getOralLipsAppearance() {
		return oralLipsAppearance;
	}

	public void setOralLipsAppearance(String oralLipsAppearance) {
		this.oralLipsAppearance = oralLipsAppearance;
	}

	public String getOralMandibleAppearance() {
		return oralMandibleAppearance;
	}

	public void setOralMandibleAppearance(String oralMandibleAppearance) {
		this.oralMandibleAppearance = oralMandibleAppearance;
	}

	public String getOralTongueAppearance() {
		return oralTongueAppearance;
	}

	public void setOralTongueAppearance(String oralTongueAppearance) {
		this.oralTongueAppearance = oralTongueAppearance;
	}

	public String getOralTeethAppearance() {
		return oralTeethAppearance;
	}

	public void setOralTeethAppearance(String oralTeethAppearance) {
		this.oralTeethAppearance = oralTeethAppearance;
	}

	public String getOralHardPlateAppearance() {
		return oralHardPlateAppearance;
	}

	public void setOralHardPlateAppearance(String oralHardPlateAppearance) {
		this.oralHardPlateAppearance = oralHardPlateAppearance;
	}

	public String getOralSoftPlateAppearance() {
		return oralSoftPlateAppearance;
	}

	public void setOralSoftPlateAppearance(String oralSoftPlateAppearance) {
		this.oralSoftPlateAppearance = oralSoftPlateAppearance;
	}

	public String getOralLipsFunction() {
		return oralLipsFunction;
	}

	public void setOralLipsFunction(String oralLipsFunction) {
		this.oralLipsFunction = oralLipsFunction;
	}

	public String getOralMandibleFunction() {
		return oralMandibleFunction;
	}

	public void setOralMandibleFunction(String oralMandibleFunction) {
		this.oralMandibleFunction = oralMandibleFunction;
	}

	public String getOralTongueFunction() {
		return oralTongueFunction;
	}

	public void setOralTongueFunction(String oralTongueFunction) {
		this.oralTongueFunction = oralTongueFunction;
	}

	public String getOralTeethFunction() {
		return oralTeethFunction;
	}

	public void setOralTeethFunction(String oralTeethFunction) {
		this.oralTeethFunction = oralTeethFunction;
	}

	public String getOralHardPlateFunction() {
		return oralHardPlateFunction;
	}

	public void setOralHardPlateFunction(String oralHardPlateFunction) {
		this.oralHardPlateFunction = oralHardPlateFunction;
	}

	public String getOralSoftPlateFunction() {
		return oralSoftPlateFunction;
	}

	public void setOralSoftPlateFunction(String oralSoftPlateFunction) {
		this.oralSoftPlateFunction = oralSoftPlateFunction;
	}

	public String getSpontaneousVocalization() {
		return spontaneousVocalization;
	}

	public void setSpontaneousVocalization(String spontaneousVocalization) {
		this.spontaneousVocalization = spontaneousVocalization;
	}

	public String getVocalizationOnDemand() {
		return vocalizationOnDemand;
	}

	public void setVocalizationOnDemand(String vocalizationOnDemand) {
		this.vocalizationOnDemand = vocalizationOnDemand;
	}

	public String getDurationControl() {
		return durationControl;
	}

	public void setDurationControl(String durationControl) {
		this.durationControl = durationControl;
	}

	public String getLoudnessControlLoudVoice() {
		return loudnessControlLoudVoice;
	}

	public void setLoudnessControlLoudVoice(String loudnessControlLoudVoice) {
		this.loudnessControlLoudVoice = loudnessControlLoudVoice;
	}

	public String getLoudnessControlSoftVoice() {
		return loudnessControlSoftVoice;
	}

	public void setLoudnessControlSoftVoice(String loudnessControlSoftVoice) {
		this.loudnessControlSoftVoice = loudnessControlSoftVoice;
	}

	public String getLoudnessControlWishperVoice() {
		return loudnessControlWishperVoice;
	}

	public void setLoudnessControlWishperVoice(String loudnessControlWishperVoice) {
		this.loudnessControlWishperVoice = loudnessControlWishperVoice;
	}

	public String getLoudnessControlLoudness() {
		return loudnessControlLoudness;
	}

	public void setLoudnessControlLoudness(String loudnessControlLoudness) {
		this.loudnessControlLoudness = loudnessControlLoudness;
	}

	public String getPitchControlHigh() {
		return pitchControlHigh;
	}

	public void setPitchControlHigh(String pitchControlHigh) {
		this.pitchControlHigh = pitchControlHigh;
	}

	public String getPitchControlMid() {
		return pitchControlMid;
	}

	public void setPitchControlMid(String pitchControlMid) {
		this.pitchControlMid = pitchControlMid;
	}

	public String getPitchControlLow() {
		return pitchControlLow;
	}

	public void setPitchControlLow(String pitchControlLow) {
		this.pitchControlLow = pitchControlLow;
	}



	
	  public String getPhonaticGlottals() { return phonaticGlottals; }
	  
	  public void setPhonaticGlottals(String phonaticGlottals) {
	  this.phonaticGlottals = phonaticGlottals; }
	  
	  
	  
	  public String getPhonologicalGlottals() { return phonologicalGlottals; }
	  
	  public void setPhonologicalGlottals(String phonologicalGlottals) {
	  this.phonologicalGlottals = phonologicalGlottals; }
	  
	  public String getPhonologicalEmphsis() { return phonologicalEmphsis; }
	  
	  public void setPhonologicalEmphsis(String phonologicalEmphsis) {
	  this.phonologicalEmphsis = phonologicalEmphsis; }
	  
	  public String getPhonologicalPhrasing() { return phonologicalPhrasing; }
	  
	  public void setPhonologicalPhrasing(String phonologicalPhrasing) {
	  this.phonologicalPhrasing = phonologicalPhrasing; }
	  
	  public String getPhonologicalRate() { return phonologicalRate; }
	  
	  public void setPhonologicalRate(String phonologicalRate) {
	  this.phonologicalRate = phonologicalRate; }
	 

	public boolean isVowelA() {
		return vowelA;
	}

	public void setVowelA(boolean vowelA) {
		this.vowelA = vowelA;
	}

	public boolean isVowelAA() {
		return vowelAA;
	}

	public void setVowelAA(boolean vowelAA) {
		this.vowelAA = vowelAA;
	}

	public boolean isVowelI() {
		return vowelI;
	}

	public void setVowelI(boolean vowelI) {
		this.vowelI = vowelI;
	}

	public boolean isVowelII() {
		return vowelII;
	}

	public void setVowelII(boolean vowelII) {
		this.vowelII = vowelII;
	}

	public boolean isVowelU() {
		return vowelU;
	}

	public void setVowelU(boolean vowelU) {
		this.vowelU = vowelU;
	}

	public boolean isVowelUU() {
		return vowelUU;
	}

	public void setVowelUU(boolean vowelUU) {
		this.vowelUU = vowelUU;
	}

	public boolean isVowelE() {
		return vowelE;
	}

	public void setVowelE(boolean vowelE) {
		this.vowelE = vowelE;
	}

	public boolean isVowelEE() {
		return vowelEE;
	}

	public void setVowelEE(boolean vowelEE) {
		this.vowelEE = vowelEE;
	}

	public boolean isVowelAE() {
		return vowelAE;
	}

	public void setVowelAE(boolean vowelAE) {
		this.vowelAE = vowelAE;
	}

	public boolean isVowelO() {
		return vowelO;
	}

	public void setVowelO(boolean vowelO) {
		this.vowelO = vowelO;
	}

	public boolean isVowelOO() {
		return vowelOO;
	}

	public void setVowelOO(boolean vowelOO) {
		this.vowelOO = vowelOO;
	}

	public boolean isVowelAU() {
		return vowelAU;
	}

	public void setVowelAU(boolean vowelAU) {
		this.vowelAU = vowelAU;
	}

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

	
	  public String getPhonaticBilabialsP() { return phonaticBilabialsP; }
	  
	  public void setPhonaticBilabialsP(String phonaticBilabialsP) {
	  this.phonaticBilabialsP = phonaticBilabialsP; }
	  
	  public String getPhonaticBilabialsPh() { return phonaticBilabialsPh; }
	  
	  public void setPhonaticBilabialsPh(String phonaticBilabialsPh) {
	  this.phonaticBilabialsPh = phonaticBilabialsPh; }
	  
	  public String getPhonaticBilabialsB() { return phonaticBilabialsB; }
	  
	  public void setPhonaticBilabialsB(String phonaticBilabialsB) {
	  this.phonaticBilabialsB = phonaticBilabialsB; }
	  
	  public String getPhonaticBilabialsBh() { return phonaticBilabialsBh; }
	  
	  public void setPhonaticBilabialsBh(String phonaticBilabialsBh) {
	  this.phonaticBilabialsBh = phonaticBilabialsBh; }
	  
	  public String getPhonaticBilabialsM() { return phonaticBilabialsM; }
	  
	  public void setPhonaticBilabialsM(String phonaticBilabialsM) {
	  this.phonaticBilabialsM = phonaticBilabialsM; }
	  
	  public String getPhonaticLoadedPathL() { return phonaticLoadedPathL; }
	  
	  public void setPhonaticLoadedPathL(String phonaticLoadedPathL) {
	  this.phonaticLoadedPathL = phonaticLoadedPathL; }
	  
	  public String getPhonaticLoadedPathV() { return phonaticLoadedPathV; }
	  
	  public void setPhonaticLoadedPathV(String phonaticLoadedPathV) {
	  this.phonaticLoadedPathV = phonaticLoadedPathV; }
	  
	  public String getPhonaticDentalsT() { return phonaticDentalsT; }
	  
	  public void setPhonaticDentalsT(String phonaticDentalsT) {
	  this.phonaticDentalsT = phonaticDentalsT; }
	  
	  public String getPhonaticDentalsTh() { return phonaticDentalsTh; }
	  
	  public void setPhonaticDentalsTh(String phonaticDentalsTh) {
	  this.phonaticDentalsTh = phonaticDentalsTh; }
	  
	  public String getPhonaticDentalsD() { return phonaticDentalsD; }
	  
	  public void setPhonaticDentalsD(String phonaticDentalsD) {
	  this.phonaticDentalsD = phonaticDentalsD; }
	  
	  public String getPhonaticDentalsDh() { return phonaticDentalsDh; }
	  
	  public void setPhonaticDentalsDh(String phonaticDentalsDh) {
	  this.phonaticDentalsDh = phonaticDentalsDh; }
	  
	  public String getPhonaticRetroflexT() { return phonaticRetroflexT; }
	  
	  public void setPhonaticRetroflexT(String phonaticRetroflexT) {
	  this.phonaticRetroflexT = phonaticRetroflexT; }
	  
	  public String getPhonaticRetroflexTt() { return phonaticRetroflexTt; }
	  
	  public void setPhonaticRetroflexTt(String phonaticRetroflexTt) {
	  this.phonaticRetroflexTt = phonaticRetroflexTt; }
	  
	  public String getPhonaticRetroflexD() { return phonaticRetroflexD; }
	  
	  public void setPhonaticRetroflexD(String phonaticRetroflexD) {
	  this.phonaticRetroflexD = phonaticRetroflexD; }
	  
	  public String getPhonaticRetroflexDd() { return phonaticRetroflexDd; }
	  
	  public void setPhonaticRetroflexDd(String phonaticRetroflexDd) {
	  this.phonaticRetroflexDd = phonaticRetroflexDd; }
	  
	  public String getPhonaticPaltalsC() { return phonaticPaltalsC; }
	  
	  public void setPhonaticPaltalsC(String phonaticPaltalsC) {
	  this.phonaticPaltalsC = phonaticPaltalsC; }
	  
	  public String getPhonaticPaltalsCh() { return phonaticPaltalsCh; }
	  
	  public void setPhonaticPaltalsCh(String phonaticPaltalsCh) {
	  this.phonaticPaltalsCh = phonaticPaltalsCh; }
	  
	  public String getPhonaticPaltalsJ() { return phonaticPaltalsJ; }
	  
	  public void setPhonaticPaltalsJ(String phonaticPaltalsJ) {
	  this.phonaticPaltalsJ = phonaticPaltalsJ; }
	  
	  public String getPhonaticPaltalsJh() { return phonaticPaltalsJh; }
	  
	  public void setPhonaticPaltalsJh(String phonaticPaltalsJh) {
	  this.phonaticPaltalsJh = phonaticPaltalsJh; }
	  
	  public String getPhonaticVelarsK() { return phonaticVelarsK; }
	  
	  public void setPhonaticVelarsK(String phonaticVelarsK) { this.phonaticVelarsK
	  = phonaticVelarsK; }
	  
	  public String getPhonaticVelarsKh() { return phonaticVelarsKh; }
	  
	  public void setPhonaticVelarsKh(String phonaticVelarsKh) {
	  this.phonaticVelarsKh = phonaticVelarsKh; }
	  
	  public String getPhonaticVelarsG() { return phonaticVelarsG; }
	  
	  public void setPhonaticVelarsG(String phonaticVelarsG) { this.phonaticVelarsG
	  = phonaticVelarsG; }
	  
	  public String getPhonaticVelarsGh() { return phonaticVelarsGh; }
	  
	  public void setPhonaticVelarsGh(String phonaticVelarsGh) {
	  this.phonaticVelarsGh = phonaticVelarsGh; }
	  
	  public String getPhonologicalBilabialsP() { return phonologicalBilabialsP; }
	  
	  public void setPhonologicalBilabialsP(String phonologicalBilabialsP) {
	  this.phonologicalBilabialsP = phonologicalBilabialsP; }
	  
	  public String getPhonologicalBilabialsPh() { return phonologicalBilabialsPh;
	  }
	  
	  public void setPhonologicalBilabialsPh(String phonologicalBilabialsPh) {
	  this.phonologicalBilabialsPh = phonologicalBilabialsPh; }
	  
	  public String getPhonologicalBilabialsB() { return phonologicalBilabialsB; }
	  
	  public void setPhonologicalBilabialsB(String phonologicalBilabialsB) {
	  this.phonologicalBilabialsB = phonologicalBilabialsB; }
	  
	  public String getPhonologicalBilabialsBh() { return phonologicalBilabialsBh;
	  }
	  
	  public void setPhonologicalBilabialsBh(String phonologicalBilabialsBh) {
	  this.phonologicalBilabialsBh = phonologicalBilabialsBh; }
	  
	  public String getPhonologicalBilabialsM() { return phonologicalBilabialsM; }
	  
	  public void setPhonologicalBilabialsM(String phonologicalBilabialsM) {
	  this.phonologicalBilabialsM = phonologicalBilabialsM; }
	  
	  public String getPhonologicalDentalsT() { return phonologicalDentalsT; }
	  
	  public void setPhonologicalDentalsT(String phonologicalDentalsT) {
	  this.phonologicalDentalsT = phonologicalDentalsT; }
	  
	  public String getPhonologicalDentalsTh() { return phonologicalDentalsTh; }
	  
	  public void setPhonologicalDentalsTh(String phonologicalDentalsTh) {
	  this.phonologicalDentalsTh = phonologicalDentalsTh; }
	  
	  public String getPhonologicalDentalsD() { return phonologicalDentalsD; }
	  
	  public void setPhonologicalDentalsD(String phonologicalDentalsD) {
	  this.phonologicalDentalsD = phonologicalDentalsD; }
	  
	  public String getPhonologicalDentalsDh() { return phonologicalDentalsDh; }
	  
	  public void setPhonologicalDentalsDh(String phonologicalDentalsDh) {
	  this.phonologicalDentalsDh = phonologicalDentalsDh; }
	  
	  public String getPhonologicalAlveolarsL() { return phonologicalAlveolarsL; }
	  
	  public void setPhonologicalAlveolarsL(String phonologicalAlveolarsL) {
	  this.phonologicalAlveolarsL = phonologicalAlveolarsL; }
	  
	  public String getPhonologicalAlveolarsN() { return phonologicalAlveolarsN; }
	  
	  public void setPhonologicalAlveolarsN(String phonologicalAlveolarsN) {
	  this.phonologicalAlveolarsN = phonologicalAlveolarsN; }
	  
	  public String getPhonologicalRetroflexT() { return phonologicalRetroflexT; }
	  
	  public void setPhonologicalRetroflexT(String phonologicalRetroflexT) {
	  this.phonologicalRetroflexT = phonologicalRetroflexT; }
	  
	  public String getPhonologicalRetroflexTt() { return phonologicalRetroflexTt;
	  }
	  
	  public void setPhonologicalRetroflexTt(String phonologicalRetroflexTt) {
	  this.phonologicalRetroflexTt = phonologicalRetroflexTt; }
	  
	  public String getPhonologicalRetroflexD() { return phonologicalRetroflexD; }
	  
	  public void setPhonologicalRetroflexD(String phonologicalRetroflexD) {
	  this.phonologicalRetroflexD = phonologicalRetroflexD; }
	  
	  public String getPhonologicalRetroflexDd() { return phonologicalRetroflexDd;
	  }
	  
	  public void setPhonologicalRetroflexDd(String phonologicalRetroflexDd) {
	  this.phonologicalRetroflexDd = phonologicalRetroflexDd; }
	  
	  public String getPhonologicalPaltalsC() { return phonologicalPaltalsC; }
	  
	  public void setPhonologicalPaltalsC(String phonologicalPaltalsC) {
	  this.phonologicalPaltalsC = phonologicalPaltalsC; }
	  
	  public String getPhonologicalPaltalsCh() { return phonologicalPaltalsCh; }
	  
	  public void setPhonologicalPaltalsCh(String phonologicalPaltalsCh) {
	  this.phonologicalPaltalsCh = phonologicalPaltalsCh; }
	  
	  public String getPhonologicalPaltalsJ() { return phonologicalPaltalsJ; }
	  
	  public void setPhonologicalPaltalsJ(String phonologicalPaltalsJ) {
	  this.phonologicalPaltalsJ = phonologicalPaltalsJ; }
	  
	  public String getPhonologicalPaltalsJh() {
		  return phonologicalPaltalsJh;
	  }
	  
	  public void setPhonologicalPaltalsJh(String phonologicalPaltalsJh) {
	  this.phonologicalPaltalsJh = phonologicalPaltalsJh;
	  }
	  
	  public String getPhonologicalVelarsK() { 
		  return phonologicalVelarsK;
		  }
	  
	  public void setPhonologicalVelarsK(String phonologicalVelarsK) {
	  this.phonologicalVelarsK = phonologicalVelarsK;
	  }
	  
	  public String getPhonologicalVelarsKh() {
		  return phonologicalVelarsKh;
		  }
	  
	  public void setPhonologicalVelarsKh(String phonologicalVelarsKh) {
	  this.phonologicalVelarsKh = phonologicalVelarsKh;
	  }
	  
	  public String getPhonologicalVelarsG() { 
		  return phonologicalVelarsG;
		  }
	  
	  public void setPhonologicalVelarsG(String phonologicalVelarsG) {
	  this.phonologicalVelarsG = phonologicalVelarsG;
	  }
	  
	  public String getPhonologicalVelarsGh() { 
		  return phonologicalVelarsGh;
		  }
	  
	  public void setPhonologicalVelarsGh(String phonologicalVelarsGh) {
	  this.phonologicalVelarsGh = phonologicalVelarsGh; }
	 

}
