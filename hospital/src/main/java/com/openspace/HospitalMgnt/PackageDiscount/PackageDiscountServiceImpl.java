package com.openspace.HospitalMgnt.PackageDiscount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.PackageDiscount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PackageDiscountServiceImpl implements PackageDiscountService {
	@Autowired
	private PackageDiscountRepository packageDiscountRepository;

	@Override
	public void savePackageDiscount(PackageDiscount packageDiscount) {
		PackageDiscount dbPackageDiscount = packageDiscountRepository
				.findByPackageDiscountName(packageDiscount.getPackageDiscountName());
		if (dbPackageDiscount != null) {
			throw new RuntimeException(ErrorMessageHandler.packageDiscountAlreadyExists);
		}
		packageDiscountRepository.save(packageDiscount);
	}

	@Override
	public List<PackageDiscount> getAllPackageDiscounts() {
		return (List<PackageDiscount>) packageDiscountRepository.findAll();
	}

	@Override
	public void updatePackageDiscount(PackageDiscount packageDiscount) {
		PackageDiscount dbPackageDiscount = packageDiscountRepository.findOne(packageDiscount.getId());
		if (dbPackageDiscount == null) {
			throw new RuntimeException(ErrorMessageHandler.packageDiscountDoesNotExists);
		}
		PackageDiscount dbPackageDiscount2 = packageDiscountRepository
				.findByPackageDiscountName(packageDiscount.getPackageDiscountName());
		if (dbPackageDiscount2 != null) {
			throw new RuntimeException(ErrorMessageHandler.packageDiscountAlreadyExists);
		}
		dbPackageDiscount.setPackageDiscountName(packageDiscount.getPackageDiscountName());
		packageDiscountRepository.save(dbPackageDiscount);
	}

	@Override
	public void deletePackageDiscount(Long id) {
		PackageDiscount dbPackageDiscount = packageDiscountRepository.findOne(id);
		if (dbPackageDiscount == null) {
			throw new RuntimeException(ErrorMessageHandler.packageDiscountNotAvailableToDelete);
		}
		packageDiscountRepository.delete(dbPackageDiscount);
	}

}
