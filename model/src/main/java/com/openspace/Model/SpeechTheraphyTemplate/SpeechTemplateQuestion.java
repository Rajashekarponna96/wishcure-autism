package com.openspace.Model.SpeechTheraphyTemplate;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SpeechTemplateQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String questionName;

	private SpeechTemplateCategory speechTemplateCategory;

	private List<SpeechTemplateAnswerHeader> answerHeaders;

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

	@ManyToOne
	public SpeechTemplateCategory getSpeechTemplateCategory() {
		return speechTemplateCategory;
	}

	public void setSpeechTemplateCategory(SpeechTemplateCategory speechTemplateCategory) {
		this.speechTemplateCategory = speechTemplateCategory;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="speechTemplateQuestion")
	public List<SpeechTemplateAnswerHeader> getAnswerHeaders() {
		return answerHeaders;
	}

	public void setAnswerHeaders(List<SpeechTemplateAnswerHeader> answerHeaders) {
		this.answerHeaders = answerHeaders;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
