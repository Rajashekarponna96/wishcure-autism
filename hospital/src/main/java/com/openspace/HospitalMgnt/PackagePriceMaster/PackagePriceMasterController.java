package com.openspace.HospitalMgnt.PackagePriceMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.PackagePriceMaster;

@RestController
public class PackagePriceMasterController {
	@Autowired
	private PackagePriceMasterService packagePriceMasterService;
	
	@RequestMapping(value=RestURIConstants.SAVE_PACKAGE_PRICE_MASTER)
	public @ResponseBody void savePackagePriceMaster(@RequestBody PackagePriceMaster packagePriceMaster){
		packagePriceMasterService.savePackagePriceMaster(packagePriceMaster);
	}
	

	@RequestMapping(value = RestURIConstants.GET_ALL_PACKAGE_PRICE_MASTERS, method = RequestMethod.GET)
	public @ResponseBody Page<PackagePriceMaster> getAllPackagePriceMasters(@RequestParam("page")int page,@RequestParam("size")int size) {
		return packagePriceMasterService.getAllPackagePriceMasters(page,size);
	}

	@RequestMapping(value = RestURIConstants.UPDATE_PACKAGE_PRICE_MASTER, method = RequestMethod.PUT)
	public @ResponseBody void updatePackagePriceMaster(@RequestBody PackagePriceMaster packagePriceMaster) {
		packagePriceMasterService.updatePackagePriceMaster(packagePriceMaster);
	}

	@RequestMapping(value = RestURIConstants.DELETE_PACKAGE_PRICE_MASTER, method = RequestMethod.DELETE)
	public @ResponseBody void deletePackagePriceMaster(@PathVariable Long id) {
		packagePriceMasterService.deletePackagePriceMaster(id);
	}
	
	@RequestMapping(value = RestURIConstants.GET_PACKAGE_PRICE_MASTER, method = RequestMethod.GET)
	public @ResponseBody PackagePriceMaster getPackagePriceMatser(@PathVariable Long id) {
		return packagePriceMasterService.getPackagePriceMatser(id);
	}
	
	@RequestMapping(value = RestURIConstants.GET_PACKAGE_PRICE_MASTER_INFO, method = RequestMethod.GET)
	public @ResponseBody PackagePriceMaster getPackagePriceMatserInfo(@PathVariable(value="subscriptionName") String subscriptionName,@PathVariable(value="packageName") String packageName ) {
		return packagePriceMasterService.getPackagePriceMatserinfo(subscriptionName,packageName);
	}
	
	
	
	

}
