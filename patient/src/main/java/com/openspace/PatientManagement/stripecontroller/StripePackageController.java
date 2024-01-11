package com.openspace.PatientManagement.stripecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Stripe.StripePackage;
import com.openspace.PatientManagement.service.StripePackageService;

@RestController
@RequestMapping(value="/stripePackage")
public class StripePackageController {
	
	@Autowired
	private StripePackageService stripePackageService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<StripePackage> getAllPackages(){
		return stripePackageService.getAllStripePackages();
	}
	
}
