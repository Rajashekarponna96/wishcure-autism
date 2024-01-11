package com.openspace.Model.SpeechTheraphyTemplate;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SpeechTemplateAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String answerName;

	private SpeechTemplateQuestion speechTemplateQuestion;

	private SpeechTemplateAnswerHeader speechTemplateAnswerHeader;

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

	@OneToOne(cascade = CascadeType.MERGE)
	public SpeechTemplateQuestion getSpeechTemplateQuestion() {
		return speechTemplateQuestion;
	}

	public void setSpeechTemplateQuestion(SpeechTemplateQuestion speechTemplateQuestion) {
		this.speechTemplateQuestion = speechTemplateQuestion;
	}

	@OneToOne(cascade=CascadeType.MERGE)
	public SpeechTemplateAnswerHeader getSpeechTemplateAnswerHeader() {
		return speechTemplateAnswerHeader;
	}

	public void setSpeechTemplateAnswerHeader(SpeechTemplateAnswerHeader speechTemplateAnswerHeader) {
		this.speechTemplateAnswerHeader = speechTemplateAnswerHeader;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
