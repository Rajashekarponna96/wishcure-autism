package com.openspace.Model.NICHQTeacherLookup;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class NichqTeacherCategoryLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<NichqTeacherQuestionLookup> nichqTeacherQuestionLookups;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="nichqTeacherCategoryLookup")
	public List<NichqTeacherQuestionLookup> getNichqTeacherQuestionLookups() {
		return nichqTeacherQuestionLookups;
	}

	public void setNichqTeacherQuestionLookups(List<NichqTeacherQuestionLookup> nichqTeacherQuestionLookups) {
		this.nichqTeacherQuestionLookups = nichqTeacherQuestionLookups;
	}

}
