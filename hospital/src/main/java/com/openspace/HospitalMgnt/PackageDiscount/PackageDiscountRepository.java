package com.openspace.HospitalMgnt.PackageDiscount;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.PackageDiscount;
@Repository
public interface PackageDiscountRepository extends PagingAndSortingRepository<PackageDiscount, Long> {
	PackageDiscount findByPackageDiscountName(String name);
	List<PackageDiscount> findById(Long id);
}
