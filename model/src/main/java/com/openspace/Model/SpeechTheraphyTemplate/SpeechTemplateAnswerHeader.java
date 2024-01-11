package com.openspace.Model.SpeechTheraphyTemplate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SpeechTemplateAnswerHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String answerName;

	private SpeechTemplateQuestion speechTemplateQuestion;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	@ManyToOne
	public SpeechTemplateQuestion getSpeechTemplateQuestion() {
		return speechTemplateQuestion;
	}

	public void setSpeechTemplateQuestion(SpeechTemplateQuestion speechTemplateQuestion) {
		this.speechTemplateQuestion = speechTemplateQuestion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
