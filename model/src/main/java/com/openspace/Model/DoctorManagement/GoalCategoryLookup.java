package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GoalCategoryLookup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	private String description;

/*	private List<GoalLookup> goalLookups;
*/
	//private GoalTemplate goalTemplate;

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
	/*@OneToMany
	public List<GoalLookup> getGoalLookups() {
		return goalLookups;
	}

	public void setGoalLookups(List<GoalLookup> goalLookups) {
		this.goalLookups = goalLookups;
	}*/

	/*@ManyToOne
	public GoalTemplate getGoalTemplate() {
		return goalTemplate;
	}

	public void setGoalTemplate(GoalTemplate goalTemplate) {
		this.goalTemplate = goalTemplate;
	}*/

}
