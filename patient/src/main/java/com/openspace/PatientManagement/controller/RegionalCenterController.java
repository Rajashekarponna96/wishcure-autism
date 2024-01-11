package com.openspace.PatientManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.RegionalCenter;
import com.openspace.PatientManagement.service.RegionalCenterService;

@RestController
@RequestMapping("/regionalCenter")
public class RegionalCenterController {
	
	@Autowired
	private RegionalCenterService regionalCenterService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void addRegionalCenter(@RequestBody RegionalCenter regionalCenter){
		regionalCenterService.addRegionalCenter(regionalCenter);
	}
	
	@RequestMapping(value="/getAll/{username:.+}",method=RequestMethod.GET)
	public Page<RegionalCenter> getAllRegionalCenters(@PathVariable String username,@RequestParam("page")int page,@RequestParam("size")int size){
		return regionalCenterService.getAllRegionalCenters(username, page, size);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public void updateRegionalCenter(@RequestBody RegionalCenter regionalCenter){
		regionalCenterService.updateRegionalCenter(regionalCenter);
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public void deleteRegionalCenter(@PathVariable Long id){
		regionalCenterService.deleteRegionalCenter(id);
	}
	
	@RequestMapping(value="/getAllRegionalCentersByAdminPage/{username:.+}")
	public Page<RegionalCenter> getAllRegionalCentersByAdminPage(@PathVariable String username,@RequestParam("page")int page,@RequestParam("size")int size){
		return regionalCenterService.getAllRegionalCentersByAdminPage(username, page, size);
		
	}
}
