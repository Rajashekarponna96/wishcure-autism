package com.openspace.HospitalMgnt.Location;

import java.util.List;

import com.openspace.Model.Lookups.Location;

public interface LocationService {

	List<Location> getAllLocationsByCityId(long cityId);

	List<Location> getAllLocations();

}
