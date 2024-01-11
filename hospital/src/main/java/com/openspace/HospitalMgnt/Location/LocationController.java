package com.openspace.HospitalMgnt.Location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.Location;

@RestController
final class LocationController {

	@Autowired
	private LocationService locationService;

	@RequestMapping(value = RestURIConstants.GET_ALL_LOCATIONS, method = RequestMethod.GET)
	public List<Location> getAllLocations() {
		return locationService.getAllLocations();
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_LOCATIONS_BY_CITYID, method = RequestMethod.GET)
	public List<Location> getAllLocationByCityId(@PathVariable long cityId) {
		return locationService.getAllLocationsByCityId(cityId);
	}

}
