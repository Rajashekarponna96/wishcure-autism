package com.openspace.Model.Lookups;

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
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String code;
	
	private String name;
	
	private String isdcode;
	
	private String mobilelength;
	

	private List<State> states;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsdcode() {
		return isdcode;
	}

	public void setIsdcode(String isdcode) {
		this.isdcode = isdcode;
	}

	public String getMobilelength() {
		return mobilelength;
	}

	public void setMobilelength(String mobilelength) {
		this.mobilelength = mobilelength;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	public List<State> getStates() {
		return states;
	}

	@JsonProperty
	public void setStates(List<State> states) {
		this.states = states;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", code=" + code + ", name=" + name + ", isdcode=" + isdcode + ", mobilelength="
				+ mobilelength + ", states=" + states + "]";
	}

}
