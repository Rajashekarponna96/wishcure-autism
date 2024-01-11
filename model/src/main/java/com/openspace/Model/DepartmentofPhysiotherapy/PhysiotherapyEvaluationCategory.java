package com.openspace.Model.DepartmentofPhysiotherapy;

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
public class PhysiotherapyEvaluationCategory  implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String categoryName;
	
	private List<PhysiotherapyEvaluationCategoryQuestions> pCategoryQuestions;
	
	private PhysiotherapyEvaluation physiotherapyEvaluation;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@ManyToOne
	public PhysiotherapyEvaluation getPhysiotherapyEvaluation() {
		return physiotherapyEvaluation;
	}

	public void setPhysiotherapyEvaluation(PhysiotherapyEvaluation physiotherapyEvaluation) {
		this.physiotherapyEvaluation = physiotherapyEvaluation;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "physiotherapyEvaluationCategory")
	public List<PhysiotherapyEvaluationCategoryQuestions> getpCategoryQuestions() {
		return pCategoryQuestions;
	}

	public void setpCategoryQuestions(List<PhysiotherapyEvaluationCategoryQuestions> pCategoryQuestions) {
		this.pCategoryQuestions = pCategoryQuestions;
	}

	
	
	
	

}
