package com.openspace.HospitalMgnt.PackageDiscount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.PackageDiscount;

@RestController
public class PackageDiscountController {
	@Autowired
	private PackageDiscountService packageDiscountService;
	
	@RequestMapping(value=RestURIConstants.SAVE_PACKAGE_DISCOUNT)
	public @ResponseBody void savePackageDiscount(@RequestBody PackageDiscount packageDiscount){
		packageDiscountService.savePackageDiscount(packageDiscount);
	}
	

	@RequestMapping(value = RestURIConstants.GET_ALL_PACKAGE_DISCOOUNTS, method = RequestMethod.GET)
	public @ResponseBody List<PackageDiscount> getAllPackageDiscounts() {
		return packageDiscountService.getAllPackageDiscounts();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_PACKAGE_DISCOUNT, method = RequestMethod.PUT)
	public @ResponseBody void updatePackageDiscount(@RequestBody PackageDiscount packageDiscount) {
		packageDiscountService.updatePackageDiscount(packageDiscount);
	}

	@RequestMapping(value = RestURIConstants.DELETE_PACKAGE_DISCOUNT, method = RequestMethod.DELETE)
	public @ResponseBody void deletePackageDiscount(@PathVariable Long id) {
		packageDiscountService.deletePackageDiscount(id);
	}

}
