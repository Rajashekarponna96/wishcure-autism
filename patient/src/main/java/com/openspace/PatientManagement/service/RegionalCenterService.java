package com.openspace.PatientManagement.service;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.RegionalCenter;

public interface RegionalCenterService {

	void addRegionalCenter(RegionalCenter regionalCenter);
	
	Page<RegionalCenter> getAllRegionalCenters(String username, int page, int size);
	
	void updateRegionalCenter(RegionalCenter regionalCenter);
	
	void deleteRegionalCenter(Long id);

	Page<RegionalCenter> getAllRegionalCentersByAdminPage(String username,int page,int size);
	
}
