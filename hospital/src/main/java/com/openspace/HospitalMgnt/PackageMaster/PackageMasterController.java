package com.openspace.HospitalMgnt.PackageMaster;

import java.util.List;

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
import com.openspace.Model.Lookups.PackageNameMaster;

@RestController
public class PackageMasterController {
	@Autowired
	private PackageMasterService packageMasterService;
	
	@RequestMapping(value=RestURIConstants.SAVE_PACKAGE_MASTER)
	public @ResponseBody void savePackageMaster(@RequestBody PackageNameMaster packagemaster){
		packageMasterService.savePackageMaster(packagemaster);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_PACKAGE_MASTERS_LIST, method = RequestMethod.GET)
	public @ResponseBody List<PackageNameMaster> getAllPackageMasters() {
		return packageMasterService.getAllPackageMasters();
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_PACKAGE_MASTERS, method = RequestMethod.GET)
	public @ResponseBody Page<PackageNameMaster> getAllPackageMasters(@RequestParam("page")int page,@RequestParam("size")int size) {
		return packageMasterService.getAllPackageMasters(page, size);
	}

	@RequestMapping(value = RestURIConstants.UPDATE_PACKAGE_MASTER, method = RequestMethod.PUT)
	public @ResponseBody void updatePackageMaster(@RequestBody PackageNameMaster packageMaster) {
		packageMasterService.updatePackageMaster(packageMaster);
	}

	@RequestMapping(value = RestURIConstants.DELETE_PACKAGE_MASTER, method = RequestMethod.DELETE)
	public @ResponseBody void deletePackageMaster(@PathVariable Long id) {
		packageMasterService.deletePackageMaster(id);
	}

}
