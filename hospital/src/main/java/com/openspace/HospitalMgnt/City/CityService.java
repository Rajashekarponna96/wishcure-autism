package com.openspace.HospitalMgnt.City;
import java.util.List;

import com.openspace.Model.Lookups.City;

public interface CityService {

	List<City> getAllCitiesByState(Long stateId);

	List<City> getAllCities();

}
