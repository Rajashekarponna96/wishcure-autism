package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.Lookups.InsuranceLookup;

public interface InsuranceLookupService {

	void add(InsuranceLookup insuranceLookup);

	List<InsuranceLookup> getAll();

	void deleteInsuranceLookup(Long insurancelookupId);

	void updateInsuranceLookup(InsuranceLookup insuranceLookup);

}
