package com.openspace.HospitalMgnt.City;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.City;
import com.openspace.Model.PatientMgnt.Repositories.CityRepository;
	
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override	
	public List<City> getAllCitiesByState(Long stateId) {
		return cityRepository.findByState_Id(stateId);
	}

	@Override
	public List<City> getAllCities() {
		return (List<City>) cityRepository.findAll();
	}

}
	