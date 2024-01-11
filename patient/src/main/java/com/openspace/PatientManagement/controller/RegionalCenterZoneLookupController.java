package com.openspace.PatientManagement.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Lookups.RegionalCenterZoneLookup;
import com.openspace.PatientManagement.service.RegionalCenterZoneLookupService;

@RestController
@RequestMapping("/regionalCenterZoneLookup")
public class RegionalCenterZoneLookupController {
	
	@Autowired
	private RegionalCenterZoneLookupService regionalCenterZoneLookupService;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public void saveRegionalCenterZoneLookup(@RequestBody RegionalCenterZoneLookup regionalCenterZoneLookup){
		regionalCenterZoneLookupService.saveRegionalCenterZoneLookup(regionalCenterZoneLookup);
	}
	
	@RequestMapping(value="/getAll" ,method= RequestMethod.GET)
	public List<RegionalCenterZoneLookup> getRegionalCenterZoneLookup(){
		return regionalCenterZoneLookupService.getRegionalCenterZoneLookup();
	}
	
	@RequestMapping(value="/getAllByStatus" ,method= RequestMethod.GET)
	public List<RegionalCenterZoneLookup> getRegionalCenterZoneLookupByStatus(){
		return regionalCenterZoneLookupService.getRegionalCenterZoneLookupStatus();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updateRegionalCenterZoneLookup(@RequestBody RegionalCenterZoneLookup regionalCenterZoneLookup){
		regionalCenterZoneLookupService.updateRegionalCenterZoneLookup(regionalCenterZoneLookup);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public void deleteRegionalCenterZoneLookup(@PathVariable Long id){
		regionalCenterZoneLookupService.deleteRegionalCenterZoneLookup(id);
	}
	

}
