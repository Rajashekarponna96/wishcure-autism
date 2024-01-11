package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.StripePlanRepository;
import com.openspace.Model.Stripe.StripePlan;

@Service
public class StripePlanServiceImpl implements StripePlanService {
	
	@Autowired
	private StripePlanRepository stripePlanRepository;

	@Override
	public List<StripePlan> getAllStripePlans() {
		
		return null;
	}

	@Override
	public StripePlan getPlanByProductId(String productId) {
		return stripePlanRepository.findByProductId(productId);
	}

}
