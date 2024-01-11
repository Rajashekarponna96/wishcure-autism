package com.openspace.HospitalMgnt.PackagePriceMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.PackagePriceMaster;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PackagePriceMasterServiceImpl implements PackagePriceMasterService {

	@Autowired
	private PackagePriceMasterRepository packagePriceMasterRepository;

	@Override
	public void savePackagePriceMaster(PackagePriceMaster packagePriceMaster) {

		PackagePriceMaster dbPackagePriceMaster = packagePriceMasterRepository
				.findByPackageNameMaster_PackageNameAndSubScriptionMaster_SubscriptionName(
						packagePriceMaster.getPackageNameMaster().getPackageName(),
						packagePriceMaster.getSubScriptionMaster().getSubscriptionName());
		if (dbPackagePriceMaster != null) {
			throw new RuntimeException(ErrorMessageHandler.combinationOfPackagenameAndSubscriptionnameAlreadyExists);
		}
		packagePriceMasterRepository.save(packagePriceMaster);
	}

	@Override
	public Page<PackagePriceMaster> getAllPackagePriceMasters(int page, int size) {
		// TODO Auto-generated method stub
		return (Page<PackagePriceMaster>) packagePriceMasterRepository
				.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id"));
	}

	@Override
	public void updatePackagePriceMaster(PackagePriceMaster packagePriceMaster) {
		PackagePriceMaster dbpackagePriceMaster = packagePriceMasterRepository.findOne(packagePriceMaster.getId());
		if (dbpackagePriceMaster == null) {
			throw new RuntimeException(ErrorMessageHandler.packagePriceMasternameDoesNotExists);
		}
		PackagePriceMaster dbPackagePriceMaster = packagePriceMasterRepository
				.findByPackageNameMaster_PackageNameAndSubScriptionMaster_SubscriptionName(
						packagePriceMaster.getPackageNameMaster().getPackageName(),
						packagePriceMaster.getSubScriptionMaster().getSubscriptionName());
		if (dbPackagePriceMaster == null) {
			dbpackagePriceMaster.setSubScriptionMaster(packagePriceMaster.getSubScriptionMaster());
			dbpackagePriceMaster.setPackageNameMaster(packagePriceMaster.getPackageNameMaster());
			dbpackagePriceMaster.setMaxDataStorage(packagePriceMaster.getMaxDataStorage());
			dbpackagePriceMaster.setMaxNoOfUsers(packagePriceMaster.getMaxNoOfUsers());
			dbpackagePriceMaster.setMinDataStorage(packagePriceMaster.getMinDataStorage());
			dbpackagePriceMaster.setMinNoOfUsers(packagePriceMaster.getMinNoOfUsers());
			dbpackagePriceMaster.setPackagePricePerUsers(packagePriceMaster.getPackagePricePerUsers());
			dbpackagePriceMaster.setPackageDescription(packagePriceMaster.getPackageDescription());
			dbpackagePriceMaster.setActive(packagePriceMaster.isActive());

		} else if (dbPackagePriceMaster.getId().equals(packagePriceMaster.getId())) {
			dbpackagePriceMaster.setSubScriptionMaster(packagePriceMaster.getSubScriptionMaster());
			dbpackagePriceMaster.setPackageNameMaster(packagePriceMaster.getPackageNameMaster());
			dbpackagePriceMaster.setMaxDataStorage(packagePriceMaster.getMaxDataStorage());
			dbpackagePriceMaster.setMaxNoOfUsers(packagePriceMaster.getMaxNoOfUsers());
			dbpackagePriceMaster.setMinDataStorage(packagePriceMaster.getMinDataStorage());
			dbpackagePriceMaster.setMinNoOfUsers(packagePriceMaster.getMinNoOfUsers());
			dbpackagePriceMaster.setPackagePricePerUsers(packagePriceMaster.getPackagePricePerUsers());
			dbpackagePriceMaster.setPackageDescription(packagePriceMaster.getPackageDescription());
			dbpackagePriceMaster.setActive(packagePriceMaster.isActive());
		} else {
			throw new RuntimeException(ErrorMessageHandler.combinationOfPackagenameAndSubscriptionnameAlreadyExists);
		}

		dbpackagePriceMaster.setSubScriptionMaster(packagePriceMaster.getSubScriptionMaster());
		dbpackagePriceMaster.setPackageNameMaster(packagePriceMaster.getPackageNameMaster());
		dbpackagePriceMaster.setMaxDataStorage(packagePriceMaster.getMaxDataStorage());
		dbpackagePriceMaster.setMaxNoOfUsers(packagePriceMaster.getMaxNoOfUsers());
		dbpackagePriceMaster.setMinDataStorage(packagePriceMaster.getMinDataStorage());
		dbpackagePriceMaster.setMinNoOfUsers(packagePriceMaster.getMinNoOfUsers());
		dbpackagePriceMaster.setPackagePricePerUsers(packagePriceMaster.getPackagePricePerUsers());
		dbpackagePriceMaster.setPackageDescription(packagePriceMaster.getPackageDescription());
		dbpackagePriceMaster.setActive(packagePriceMaster.isActive());

		packagePriceMasterRepository.save(dbpackagePriceMaster);
	}

	@Override
	public void deletePackagePriceMaster(Long id) {
		PackagePriceMaster dbPackagePriceMaster = packagePriceMasterRepository.findOne(id);
		if (dbPackagePriceMaster == null) {
			throw new RuntimeException(ErrorMessageHandler.packagePriceMasternameDoesNotExists);
		}
		packagePriceMasterRepository.delete(dbPackagePriceMaster);
	}

	@Override
	public PackagePriceMaster getPackagePriceMatser(Long id) {
		return packagePriceMasterRepository.findOne(id);
	}

	@Override
	public PackagePriceMaster getPackagePriceMatserinfo(String subscriptionName, String packageName) {
		return packagePriceMasterRepository.findByPackageNameMaster_PackageNameAndSubScriptionMaster_SubscriptionName(
				packageName, subscriptionName);
	}

}
