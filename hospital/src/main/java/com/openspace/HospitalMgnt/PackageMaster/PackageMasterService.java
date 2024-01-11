package com.openspace.HospitalMgnt.PackageMaster;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Lookups.PackageNameMaster;

public interface PackageMasterService {
	
	void savePackageMaster(PackageNameMaster packageMaster);

	Page<PackageNameMaster> getAllPackageMasters(int page,int size);

	void updatePackageMaster(PackageNameMaster packageMaster);

	void deletePackageMaster(Long id);

	List<PackageNameMaster> getAllPackageMasters();
}
