package com.openspace.HospitalMgnt.PackagePriceMaster;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.PackagePriceMaster;

@Repository
public interface PackagePriceMasterRepository extends PagingAndSortingRepository<PackagePriceMaster, Long> {

	List<PackagePriceMaster> findById(Long id);
	PackagePriceMaster findByPackageNameMaster_PackageNameAndSubScriptionMaster_SubscriptionName(String packagename,
			String subscriptionname);
}
