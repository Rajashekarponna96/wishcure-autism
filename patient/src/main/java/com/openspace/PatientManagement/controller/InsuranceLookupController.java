package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Lookups.InsuranceLookup;
import com.openspace.PatientManagement.service.InsuranceLookupService;

@RestController
@RequestMapping("/insuranceLookup")
public class InsuranceLookupController {

	@Autowired
	private InsuranceLookupService insuranceLookupService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody InsuranceLookup insuranceLookup) {
		insuranceLookupService.add(insuranceLookup);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<InsuranceLookup> getAll() {
		return insuranceLookupService.getAll();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteInsuranceLookup(@PathVariable("id") Long insurancelookupId) {
		 insuranceLookupService.deleteInsuranceLookup(insurancelookupId);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateInsuranceLookup(@RequestBody InsuranceLookup insuranceLookup) {
		insuranceLookupService.updateInsuranceLookup(insuranceLookup);
	}

}
