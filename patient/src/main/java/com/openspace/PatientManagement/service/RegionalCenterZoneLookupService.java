package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.Lookups.RegionalCenterZoneLookup;

public interface RegionalCenterZoneLookupService {

	void saveRegionalCenterZoneLookup(RegionalCenterZoneLookup regionalCenterZoneLookup);

	List<RegionalCenterZoneLookup> getRegionalCenterZoneLookup();

	void updateRegionalCenterZoneLookup(RegionalCenterZoneLookup regionalCenterZoneLookup);

	void deleteRegionalCenterZoneLookup(Long id);

	List<RegionalCenterZoneLookup> getRegionalCenterZoneLookupStatus();
	

}
