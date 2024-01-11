package com.openspace.HospitalMgnt.PackageMaster;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.PackageNameMaster;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PackageMasterServiceImpl implements PackageMasterService {

	@Autowired
	private PackageMasterRepository packageMasterRepository;

	@Override
	public void savePackageMaster(PackageNameMaster packageMaster) {
		PackageNameMaster dbPackageMaster = packageMasterRepository.findByPackageName(packageMaster.getPackageName());
		if (dbPackageMaster != null) {
			throw new RuntimeException(ErrorMessageHandler.packageNameAlreadyExists);
		}
		packageMaster.setActive(true);
		packageMaster.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		packageMaster.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		packageMasterRepository.save(packageMaster);
	}

	@Override
	public Page<PackageNameMaster> getAllPackageMasters(int page,int size) {
		return (Page<PackageNameMaster>) packageMasterRepository.findAll(new PageRequest(page, size,Sort.Direction.DESC,"id"));
	}
	@Override
	public List<PackageNameMaster> getAllPackageMasters() {
		return (List<PackageNameMaster>) packageMasterRepository.findAll();
	}

	@Override
	public void updatePackageMaster(PackageNameMaster packageMaster) {
		PackageNameMaster dbPackageMaster = packageMasterRepository.findOne(packageMaster.getId());
		if (dbPackageMaster == null) {
			throw new RuntimeException(ErrorMessageHandler.packageNameDoesNotExists);
		}
		PackageNameMaster dbPackageMaster2 = packageMasterRepository.findByPackageName(packageMaster.getPackageName());
		if (dbPackageMaster2 == null) {
			dbPackageMaster.setActive(packageMaster.getActive());
			dbPackageMaster.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			dbPackageMaster.setPackageName(packageMaster.getPackageName());
			dbPackageMaster.setPackageDescription(packageMaster.getPackageDescription());

		} else if (dbPackageMaster2.getId().equals(packageMaster.getId())) {
			dbPackageMaster.setActive(packageMaster.getActive());
			dbPackageMaster.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			dbPackageMaster.setPackageName(packageMaster.getPackageName());
			dbPackageMaster.setPackageDescription(packageMaster.getPackageDescription());
		} else {
			throw new RuntimeException(ErrorMessageHandler.packageNameAlreadyExists);
		}
		dbPackageMaster.setActive(packageMaster.getActive());
		dbPackageMaster.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		dbPackageMaster.setPackageName(packageMaster.getPackageName());
		dbPackageMaster.setPackageDescription(packageMaster.getPackageDescription());
		packageMasterRepository.save(dbPackageMaster);
	}

	@Override
	public void deletePackageMaster(Long id) {
		PackageNameMaster dbPackageMaster = packageMasterRepository.findOne(id);
		if (dbPackageMaster == null) {
			throw new RuntimeException(ErrorMessageHandler.packageNameDoesNotExists);
		}
		packageMasterRepository.delete(dbPackageMaster);
	}

}
