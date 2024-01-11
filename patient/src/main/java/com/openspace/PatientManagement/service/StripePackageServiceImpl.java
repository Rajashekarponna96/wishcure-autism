package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.StripePackageRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripePlanRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripeTierRepository;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.Model.Stripe.StripePlan;
import com.openspace.Model.Stripe.StripeTier;
import com.openspace.PatientManagement.controller.StripeClient;
import com.stripe.Stripe;
import com.stripe.model.Plan;
import com.stripe.model.PlanTier;
import com.stripe.model.Product;

@Service
public class StripePackageServiceImpl implements StripePackageService {

	@Autowired
	private StripePackageRepository stripePackageRepository;

	@Autowired
	private StripePlanRepository stripePlanRepository;

	@SuppressWarnings("unused")
	@Autowired
	private StripeTierRepository stripeTierRepository;

	@SuppressWarnings("unused")
	private StripeClient stripeClient;

	@Autowired
	StripePackageServiceImpl(StripeClient stripeClient) {
		this.stripeClient = stripeClient;
		Stripe.apiKey = "sk_test_KnRgkZ9quhBpKBTaNQ0eu2GX";
	}

	@Override
	public List<StripePackage> getAllStripePackages() {
		List<StripePackage> stripePackages = new ArrayList<StripePackage>();
		List<StripePackage> resultStripePackages = new ArrayList<StripePackage>();
		try {
			Map<String, Object> productParams = new HashMap<String, Object>();
			productParams.put("limit", "100");
			List<Product> products = Product.list(productParams).getData();
			Map<String, Object> planParams = new HashMap<>();
			planParams.put("limit", "100");
			List<Plan> stripePlans = Plan.list(planParams).getData();
			for (Product product : products) {
				StripePackage stripePackage1 = stripePackageRepository.findByPackageId(product.getId());
				if (stripePackage1 == null) {
					StripePackage stripePackage = new StripePackage();
					stripePackage.setPackageId(product.getId());
					stripePackage.setPackageName(product.getName());
					stripePackageRepository.save(stripePackage);
					stripePackages.add(stripePackage);
				} else if (stripePackage1.getPackageId().equals(product.getId())) {
					stripePackage1.setPackageName(product.getName());
					stripePackage1.setPackageId(stripePackage1.getPackageId());
					stripePackageRepository.save(stripePackage1);
					stripePackages.add(stripePackage1);
				}
			}
			for (Plan plan : stripePlans) {
				StripePlan dbStripePlan = stripePlanRepository.findByPlanId(plan.getId());
				if (dbStripePlan == null) {
					StripePlan StripePlan = convertToPlan(plan);
					stripePlanRepository.save(StripePlan);
				} else if (dbStripePlan.getPlanId().equals(plan.getId())) {
					dbStripePlan.setPlanName(plan.getNickname());
					dbStripePlan.setNickname(plan.getNickname());
					stripePlanRepository.save(dbStripePlan);
				}
			}
			if (stripePackages.size() < 1) {
				List<StripePackage> dbStripePackages = (List<StripePackage>) stripePackageRepository.findAll();
				resultStripePackages.addAll(dbStripePackages);
			}
			for (StripePackage stripePackage : stripePackages) {
				StripePlan stripePlan = stripePlanRepository.findByProductId(stripePackage.getPackageId());
				stripePackage.setPlan(stripePlan);
				resultStripePackages.add(stripePackage);
			}
			return resultStripePackages;
			// return stripePackages;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stripePackages;

	}

	@SuppressWarnings("unused")
	private StripePlan convertToPlan(Plan plan) {
		StripePlan stripePlan = new StripePlan();
		stripePlan.setBillingScheme(plan.getBillingScheme());
		stripePlan.setCreateddate(LocalDate.now());
		stripePlan.setCurrency(plan.getCurrency());
		stripePlan.setLivemode(plan.getLivemode());
		stripePlan.setNickname(plan.getNickname());
		stripePlan.setObject(plan.getObject());
		stripePlan.setPayInterval(plan.getInterval());
		stripePlan.setPayIntervalCount(plan.getIntervalCount());
		stripePlan.setPlanId(plan.getId());
		stripePlan.setPlanName(plan.getNickname());
		stripePlan.setProductId(plan.getProduct());
		stripePlan.setTiersMode(plan.getTiersMode());
		stripePlan.setTrialPeriodDays(plan.getTrialPeriodDays());
		stripePlan.setUsageType(plan.getUsageType());
		List<PlanTier> planTiers = plan.getTiers();
		List<StripeTier> stripeTiers = new ArrayList<StripeTier>();
		int i = 0;
		int j = 1;
		for (PlanTier planTier : planTiers) {
			StripeTier stripeTier = new StripeTier();
			stripeTier.setAmount((double) (planTier.getAmount() / 100));
			stripeTier.setStripePlan(stripePlan);
			if (planTiers.size() >= i && planTiers.get(i) != null) {
				if (planTier.getUpTo() != null) {
					stripeTier.setUserCount(j + " - " + planTier.getUpTo());
				} else {
					stripeTier.setUserCount("For "+j + " & Above");
				}
			}
			if (planTier.getUpTo() == null) {
				stripeTier.setUpTo(999L);
			} else {
				stripeTier.setUpTo(planTier.getUpTo());
			}
			// stripeTierRepository.save(stripeTier);
			stripeTiers.add(stripeTier);
			if (planTier.getUpTo() != null) {
				j = (int) (1 + planTier.getUpTo());
			}
			i++;
		}
		stripePlan.setTiers(stripeTiers);
		StripePackage stripePackage = stripePackageRepository.findByPackageId(plan.getProduct());
		if (stripePackage != null) {
			stripePlan.setStripePackage(stripePackage);
		}
		return stripePlan;

	}
}
