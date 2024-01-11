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

import com.openspace.Model.DoctorManagement.Currency;
import com.openspace.PatientManagement.service.CurrencyService;

@RestController
public class CurrencyController {
	
	@Autowired
	private CurrencyService currencyService;
	
	@RequestMapping(value="/addCurrency",method=RequestMethod.POST)
	public void addCurrency(@RequestBody Currency currency){
		currencyService.addCurrency(currency);
	}
	
	@RequestMapping(value="/getAllCurrencys",method=RequestMethod.GET)
	public List<Currency> getAllCurrencys(){
		return currencyService.getAllCurrencys();
	}
	
	@RequestMapping(value="/updateCurrency",method=RequestMethod.PUT)
	public void updateCurrency(@RequestBody Currency currency){
		currencyService.updateCurrency(currency);
	}
	@RequestMapping(value="/deleteCurrency/{id}",method=RequestMethod.DELETE)
	public void deleteCurrency(@PathVariable Long id){
		currencyService.deleteCurrency(id);
	}
	
	@RequestMapping(value="/getAllCurrencysByAdminPage/{username:.+}",method=RequestMethod.GET)
	public Page<Currency> getAllCurrencysByAdminPage(@PathVariable String username,@RequestParam("page")int page,@RequestParam("size")int size){
		return currencyService.getAllCurrencysByAdminPage(username, page, size);
		
	}
	
	@RequestMapping(value="/getAllCurrencysByAdmin/{username:.+}",method=RequestMethod.GET)
	public List<Currency> getAllCurrencysByAdmin(@PathVariable String username){
		return currencyService.getAllCurrencysByAdmin(username);
		
	}
}
