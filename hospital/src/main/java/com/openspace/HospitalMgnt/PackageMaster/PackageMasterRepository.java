package com.openspace.HospitalMgnt.PackageMaster;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.PackageNameMaster;
@Repository
public interface PackageMasterRepository extends PagingAndSortingRepository<PackageNameMaster, Long> {
	PackageNameMaster findByPackageName(String name);
	List<PackageNameMaster> findById(Long id);
	
	

}
