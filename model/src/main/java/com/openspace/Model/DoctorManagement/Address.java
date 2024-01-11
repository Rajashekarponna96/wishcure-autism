package com.openspace.Model.DoctorManagement;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.openspace.Model.Lookups.City;
import com.openspace.Model.Lookups.Country;
import com.openspace.Model.Lookups.Location;
import com.openspace.Model.Lookups.State;

@Embeddable
public class Address implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	private String address1;

	private String address2;

	private Country country;

	private State state;

	private City city;

	private Location location;

	private String zipcode;
	
	private String cityName;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	//@ManyToOne(cascade=CascadeType.MERGE)
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "Address [address1=" + address1 + ", address2=" + address2 + ", country=" + country + ", state=" + state
				+ ", city=" + city + ", location=" + location + ", zipcode=" + zipcode + ", cityName=" + cityName + "]";
	}

	

}
