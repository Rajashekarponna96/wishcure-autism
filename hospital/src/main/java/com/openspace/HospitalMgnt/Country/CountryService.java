package com.openspace.HospitalMgnt.Country;

import java.util.List;

import com.openspace.Model.Lookups.Country;

public interface CountryService {

	List<Country> getAllCountries();

	void updateCountry(Country country);

	Country getCountry(Long id);

	List<Country> getCountryByIsdCode();

}
