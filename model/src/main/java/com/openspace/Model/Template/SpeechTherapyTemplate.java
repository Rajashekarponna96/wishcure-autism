package com.openspace.Model.Template;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SpeechTherapyTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String templateName;

	private List<StandardScore> standardScores;

	private String status;
	
	private List<String> subTests;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "speechTherapyTemplate")
	public List<StandardScore> getStandardScores() {
		return standardScores;
	}

	public void setStandardScores(List<StandardScore> standardScores) {
		this.standardScores = standardScores;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ElementCollection
	public List<String> getSubTests() {
		return subTests;
	}

	public void setSubTests(List<String> subTests) {
		this.subTests = subTests;
	}


	

}
