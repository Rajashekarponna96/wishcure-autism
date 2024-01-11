package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Stripe.StripePackage;

@Repository
public interface StripePackageRepository extends PagingAndSortingRepository<StripePackage, Long> {
	
	StripePackage findByPackageName(String name);
	
	StripePackage findByPackageId(String packageId);

}
