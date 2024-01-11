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

import com.openspace.Model.DoctorManagement.Insurance;
import com.openspace.Model.DoctorManagement.School;
import com.openspace.PatientManagement.service.InsuranceService;

@RestController
@RequestMapping(value="/insurance")
public class InsuranceController {
	
	@Autowired
	private InsuranceService insuranceService;
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public void addInsurance(@RequestBody Insurance insurance){
		insuranceService.addInsurance(insurance);
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public List<Insurance> getAllInsurances(){
		return insuranceService.getAllInsurances();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public void updateInsurance(@RequestBody Insurance insurance){
		insuranceService.updateInsurance(insurance);
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public void deleteInsurance(@PathVariable Long id){
		insuranceService.deleteInsurance(id);
	}
	
	@RequestMapping(value="/getAll/{username:.+}",method=RequestMethod.GET)
	public Page<Insurance> getAllInsurancesByAdminPage(@PathVariable String username,@RequestParam("page")int page,@RequestParam("size")int size){
		return insuranceService.getAllInsurancesByAdminPage(username, page, size);
	}
	
	@RequestMapping(value="/getAllwithoutPagination/{username:.+}",method=RequestMethod.GET)
	public List<Insurance> getAllInsurancesByAdminWithOutPagination(@PathVariable String username){
		return insuranceService.getAllInsurancesByAdmin(username);
	}
	
	@RequestMapping(value="/getAllInsurancesByCompany/{username:.+}", method=RequestMethod.GET)
	public List<Insurance> getAllInsurancesByCompany(@PathVariable String username) {
		return insuranceService.getAllInsurancesByCompany(username);
	}
}
