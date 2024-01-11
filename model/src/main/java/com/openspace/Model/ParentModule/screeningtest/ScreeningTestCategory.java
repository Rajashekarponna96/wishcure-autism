package com.openspace.Model.ParentModule.screeningtest;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.openspace.Model.UserManagement.UserAccount;

@Entity
public class ScreeningTestCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private List<ScreeningTestQuestion> screeningTestQuestions;
	
	private String categoryStatus;
	
	private UserAccount userAccount;
	
	private ScreeningTestCategoryLookup screeningTestCategoryLookup;
	
	private LocalDate date;

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

	@OneToMany(cascade=CascadeType.ALL,mappedBy="screeningTestCategory")
	public List<ScreeningTestQuestion> getScreeningTestQuestions() {
		return screeningTestQuestions;
	}

	public void setScreeningTestQuestions(List<ScreeningTestQuestion> screeningTestQuestions) {
		this.screeningTestQuestions = screeningTestQuestions;
	}
	
	@OneToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	@ManyToOne
	public ScreeningTestCategoryLookup getScreeningTestCategoryLookup() {
		return screeningTestCategoryLookup;
	}

	public void setScreeningTestCategoryLookup(ScreeningTestCategoryLookup screeningTestCategoryLookup) {
		this.screeningTestCategoryLookup = screeningTestCategoryLookup;
	}
	

}
