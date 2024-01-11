package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.openspace.Model.Lookups.CategoryType;

@Entity
public class PatientRegistrationType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;
	
	private boolean status;

	private List<CategoryType> categoryType;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientRegistrationType")
	 * public List<Department> getDepartments() { return departments; }
	 * 
	 * public void setDepartments(List<Department> departments) { this.departments =
	 * departments; }
	 */

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientRegistrationType")
	public List<CategoryType> getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(List<CategoryType> categoryType) {
		this.categoryType = categoryType;
	}
	
	
	

}
