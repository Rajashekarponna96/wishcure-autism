package com.openspace.HospitalMgnt.Location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.Location;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public List<Location> getAllLocationsByCityId(long cityId) {
		return locationRepository.findByCity_Id(cityId);
	}

	@Override
	public List<Location> getAllLocations() {
		return (List<Location>) locationRepository.findAll();
	}

}
