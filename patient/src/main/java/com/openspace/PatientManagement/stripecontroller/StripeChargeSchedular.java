package com.openspace.PatientManagement.stripecontroller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.openspace.Model.PatientMgnt.Repositories.StripePlanRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.Model.Stripe.StripePlan;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.PatientManagement.controller.StripeClient;
import com.stripe.Stripe;
import com.stripe.model.Subscription;

public class StripeChargeSchedular {
	
	private StripeClient stripeClient;

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private StripePlanRepository stripePlanRepository;

	@Autowired
	StripeChargeSchedular(StripeClient stripeClient) {
		this.stripeClient = stripeClient;
		Stripe.apiKey = "sk_test_KnRgkZ9quhBpKBTaNQ0eu2GX";
	}
	@Scheduled(cron = "0 1 * * * ?")
	public void subScribeTheRegisterUsers() throws Exception {
		List<UserAccount> dbUserAccounts = userAccountRepository.findByRegisteredUser(true);
		for (UserAccount user : dbUserAccounts) {
			StripePackage dbStripePackage = user.getStripePackage();
			if (dbStripePackage != null && dbStripePackage.getPackageId() != null) {
				StripePlan dbStripePlan = stripePlanRepository.findByProductId(dbStripePackage.getPackageId());
				if (dbStripePlan != null && dbStripePlan.getTrialPeriodDays() != null) {
					LocalDate trialPeriodEndDate = user.getRegisteredDate().plusDays(dbStripePlan.getTrialPeriodDays());
					if (trialPeriodEndDate.equals(LocalDate.now())) {
						Map<String, Object> planParam = new HashMap<>();
						planParam.put("plan", dbStripePlan.getPlanId());
						Map<String, Object> items = new HashMap<>();
						items.put("0", planParam);
						Map<String, Object> params = new HashMap<>();
						params.put("customer", user.getCustomerId());
						params.put("items", items);
						Subscription subscription = Subscription.create(params);
					}
				}

			}

		}
System.out.println("*********************@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ called schedular");
	}

}
