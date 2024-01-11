package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.Stripe.StripePlan;

public interface StripePlanService {

	List<StripePlan> getAllStripePlans();

	StripePlan getPlanByProductId(String productId);

}
