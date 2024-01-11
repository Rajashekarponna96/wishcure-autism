package com.openspace.PatientManagement.csbsLookups;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookupDto;

@RestController
public class CsbsCategoryLookupController {
	@Autowired
	private CsbsCategoryLookupService csbsLookupService;
	
	@RequestMapping(value = "/savecsbsLookup", method = RequestMethod.POST)
	public @ResponseBody void savecsbsLookup(@RequestBody CsbsCategoryLookup csbsCategoryLookup){
		csbsLookupService.saveCsbsLookup(csbsCategoryLookup);
	}
	
	@RequestMapping(value = "/getAllcsbsLookup", method = RequestMethod.GET)
	public @ResponseBody List<CsbsCategoryLookup> getAllcsbsLookup(){
		return csbsLookupService.getAllCsbsLookup();
	}

	@RequestMapping(value = "/getAllcsbsLookupByCategoryStatus/{id}", method = RequestMethod.GET)
	public @ResponseBody List<CsbsCategoryLookupDto> getAllcsbsLookupByCategoryStatus(@PathVariable Long id){
		return csbsLookupService.getAllCsbsLookupByCategoryStatus(id);
	}
	
	@RequestMapping(value = "/updatecsbsLookup", method = RequestMethod.PUT)
	public @ResponseBody void updatecsbsLookup(@RequestBody CsbsCategoryLookup csbsCategoryLookup){
		csbsLookupService.updateCsbsLookup(csbsCategoryLookup);
	}

	@RequestMapping(value = "/deletecsbsLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deletecsbsLookup(@PathVariable Long id){
		csbsLookupService.deleteCsbsLookup(id);
	}
}
