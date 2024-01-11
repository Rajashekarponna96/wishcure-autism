package com.openspace.HospitalMgnt.City;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.City;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = RestURIConstants.GET_ALL_CITIES, method = RequestMethod.GET)
	public @ResponseBody List<City> getAllCities() {
		return cityService.getAllCities();
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_CITIES_BY_STATEID, method = RequestMethod.GET)
	public @ResponseBody List<City> getAllCitiesByState(@PathVariable Long stateId) {
		return cityService.getAllCitiesByState(stateId);
	}

	
}
