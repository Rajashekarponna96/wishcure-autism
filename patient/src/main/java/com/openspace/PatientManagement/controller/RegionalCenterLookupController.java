package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Lookups.RegionalCenterLookup;
import com.openspace.PatientManagement.service.RegionalCenterLookupService;

@RestController
@RequestMapping("/regionalCenterLookup")
public class RegionalCenterLookupController {
	
	@Autowired
	private RegionalCenterLookupService regionalCenterLookupService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void saveRegionalCenterLookup(@RequestBody RegionalCenterLookup regionalCenterLookup){
		regionalCenterLookupService.saveRegionalCenterLookup(regionalCenterLookup);
	}
	
	@RequestMapping(value="/getAll" ,method= RequestMethod.GET)
	public List<RegionalCenterLookup> getRegionalCenterLookup(){
		return regionalCenterLookupService.getRegionalCenterLookup();
	}
	
	@RequestMapping(value="/getAllByStatus" ,method= RequestMethod.GET)
	public List<RegionalCenterLookup> getRegionalCenterLookupByStatus(){
		return regionalCenterLookupService.getRegionalCenterLookupByStatus();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updateRegionalCenterLookup(@RequestBody RegionalCenterLookup regionalCenterLookup){
		regionalCenterLookupService.updateRegionalCenterLookup(regionalCenterLookup);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public void deleteRegionalCenterLookup(@PathVariable Long id){
		regionalCenterLookupService.deleteRegionalCenterLookup(id);
	}

}
