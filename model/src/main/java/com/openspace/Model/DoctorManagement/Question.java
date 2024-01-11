package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String questionName;

	private List<Answer> answers;

	private DiseaseFeedback diseaseFeedback;

	private QuestionCategory questionCategory;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@ManyToOne
	public DiseaseFeedback getDiseaseFeedback() {
		return diseaseFeedback;
	}

	public void setDiseaseFeedback(DiseaseFeedback diseaseFeedback) {
		this.diseaseFeedback = diseaseFeedback;
	}

	@JsonIgnore
	@ManyToOne
	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	@JsonProperty
	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

}
