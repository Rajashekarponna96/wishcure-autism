package com.openspace.HospitalMgnt.PackagePriceMaster;


import org.springframework.data.domain.Page;

import com.openspace.Model.Lookups.PackagePriceMaster;

public interface PackagePriceMasterService {
	void savePackagePriceMaster(PackagePriceMaster packagePriceMaster);

	Page<PackagePriceMaster> getAllPackagePriceMasters(int page,int size);

	void updatePackagePriceMaster(PackagePriceMaster packagePriceMaster);

	void deletePackagePriceMaster(Long id);

	PackagePriceMaster getPackagePriceMatser(Long id);

	PackagePriceMaster getPackagePriceMatserinfo(String subscriptionName, String packageName);

}
