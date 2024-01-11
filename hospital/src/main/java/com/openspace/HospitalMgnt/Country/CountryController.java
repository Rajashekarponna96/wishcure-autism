package com.openspace.HospitalMgnt.Country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.Country;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(value = RestURIConstants.GET_ALL_COUNTRIES, method = RequestMethod.GET)
	public @ResponseBody List<Country> getAllCountries() {
		return countryService.getAllCountries();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_COUNTRY, method = RequestMethod.PUT)
	public @ResponseBody void updateCountry(@RequestBody Country country) {
		countryService.updateCountry(country);
	}

	@RequestMapping(value = RestURIConstants.GET_COUNTRY, method = RequestMethod.GET)
	public @ResponseBody Country getCountry(@PathVariable Long id) {
		return countryService.getCountry(id);
	}
	
	
	@RequestMapping(value = RestURIConstants.GET_COUNTRY_BY_ISDCODE, method = RequestMethod.GET)
	public @ResponseBody List<Country> getCountryByIsdCode() {
		return countryService.getCountryByIsdCode();
	}
}
