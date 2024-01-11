package com.openspace.Model.SpeechTheraphyTemplate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SpeechTemplateCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String speechCategoryName;

	private List<SpeechTemplateQuestion> speechTemplateQuestions;
	
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpeechCategoryName() {
		return speechCategoryName;
	}

	public void setSpeechCategoryName(String speechCategoryName) {
		this.speechCategoryName = speechCategoryName;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "speechTemplateCategory")
	public List<SpeechTemplateQuestion> getSpeechTemplateQuestions() {
		return speechTemplateQuestions;
	}

	@JsonProperty
	public void setSpeechTemplateQuestions(List<SpeechTemplateQuestion> speechTemplateQuestions) {
		this.speechTemplateQuestions = speechTemplateQuestions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
