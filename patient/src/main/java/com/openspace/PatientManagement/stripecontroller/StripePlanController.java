package com.openspace.PatientManagement.stripecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Stripe.StripePlan;
import com.openspace.PatientManagement.service.StripePlanService;

@RestController
@RequestMapping(value="/stripePlan")
public class StripePlanController {
	
	@Autowired
	private StripePlanService StripePlanService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<StripePlan> getAll(){
		return StripePlanService.getAllStripePlans();
		
	}
	
	@RequestMapping(value="/byProcudtId/{id}",method=RequestMethod.GET)
	public StripePlan getPlanByProductId(@PathVariable("id") String productId){
		return StripePlanService.getPlanByProductId(productId);
		
	}
	
	
	
	

}
