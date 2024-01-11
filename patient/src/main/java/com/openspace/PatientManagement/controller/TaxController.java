package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.Tax;
import com.openspace.PatientManagement.service.TaxService;

@RestController
public class TaxController {
	
	@Autowired
	private TaxService taxService;
	
	@RequestMapping(value="/addTax",method=RequestMethod.POST)
	public void addTax(@RequestBody Tax tax){
		taxService.addTax(tax);
	}
	
	@RequestMapping(value="/getAllTaxs",method=RequestMethod.GET)
	public List<Tax> getAllTaxs(){
		return taxService.getAllTaxs();
	}
	
	@RequestMapping(value="/updateTax",method=RequestMethod.PUT)
	public void updateTax(@RequestBody Tax tax){
		taxService.updateTax(tax);
	}
	@RequestMapping(value="/deleteTax/{id}",method=RequestMethod.DELETE)
	public void deleteTax(@PathVariable Long id){
		taxService.deleteTax(id);
	}
	
	@RequestMapping(value="/getAllTaxsByAdminPage/{username:.+}")
	public Page<Tax> getAllTaxsByAdminPage(@PathVariable String username,@RequestParam("page")int page,@RequestParam("size")int size){
		return taxService.getAllTaxsByAdminPage(username, page, size);
		
	}
}
