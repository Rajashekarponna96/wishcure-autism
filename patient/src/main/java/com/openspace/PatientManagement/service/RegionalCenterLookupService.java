package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.Lookups.RegionalCenterLookup;

public interface RegionalCenterLookupService {

	void saveRegionalCenterLookup(RegionalCenterLookup regionalCenterLookup);

	List<RegionalCenterLookup> getRegionalCenterLookup();

	void updateRegionalCenterLookup(RegionalCenterLookup regionalCenterLookup);

	void deleteRegionalCenterLookup(Long id);

	List<RegionalCenterLookup> getRegionalCenterLookupByStatus();

}
