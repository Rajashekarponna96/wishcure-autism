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

import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.PatientManagement.service.PaymethodService;

@RestController
public class PaymethodController {
	
	@Autowired
	private PaymethodService paymethodService;
	
	@RequestMapping(value="/addPaymethod",method=RequestMethod.POST)
	public void addPaymethod(@RequestBody Paymethod paymethod){
		paymethodService.addPaymethod(paymethod);
	}
	
	@RequestMapping(value="/getAllPaymethods",method=RequestMethod.GET)
	public List<Paymethod> getAllPaymethods(){
		return paymethodService.getAllPaymethods();
	}
	
	@RequestMapping(value="/updatePaymethod",method=RequestMethod.PUT)
	public void updatePaymethod(@RequestBody Paymethod paymethod){
		paymethodService.updatePaymethod(paymethod);
	}
	@RequestMapping(value="/deletePaymethod/{id}",method=RequestMethod.DELETE)
	public void deletePaymethod(@PathVariable Long id){
		paymethodService.deletePaymethod(id);
	}
	
	@RequestMapping(value="/getAllPaymethodsByAdminPage/{username:.+}")
	public Page<Paymethod> getAllPaymethodsByAdminPage(@PathVariable String username,@RequestParam("page")int page,@RequestParam("size")int size){
		return paymethodService.getAllPaymethodsByAdminPage(username, page, size);
		
	}
	
	@RequestMapping(value="/getAllPaymethodsByAdmin/{username:.+}")
	public List<Paymethod> getAllPaymethodsByAdmin(@PathVariable String username){
		return paymethodService.getAllPaymethodsByAdmin(username);
		
	}

	
}
