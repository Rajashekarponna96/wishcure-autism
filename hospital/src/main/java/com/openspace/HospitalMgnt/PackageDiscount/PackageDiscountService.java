package com.openspace.HospitalMgnt.PackageDiscount;

import java.util.List;

import com.openspace.Model.Lookups.PackageDiscount;


public interface PackageDiscountService {
	void savePackageDiscount(PackageDiscount packageDiscount);

	List<PackageDiscount> getAllPackageDiscounts();

	void updatePackageDiscount(PackageDiscount packageDiscount);

	void deletePackageDiscount(Long id);

}
