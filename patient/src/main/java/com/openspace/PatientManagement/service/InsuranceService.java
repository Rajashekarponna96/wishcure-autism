package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Insurance;

public interface InsuranceService {

	void addInsurance(Insurance insurance);
	
	List<Insurance> getAllInsurances();
	
	void updateInsurance(Insurance insurance);
	
	void deleteInsurance(Long id);

	List<Insurance> getAllInsurancesByAdmin(String username);
	
	Page<Insurance> getAllInsurancesByAdminPage(String username,int page,int size);

	List<Insurance> getAllInsurancesByCompany(String username);
	
	
	
}
