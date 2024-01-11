package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.RegionalCenter;

@Repository
public interface RegionalCenterRepository extends PagingAndSortingRepository<RegionalCenter, Long> {

	List<RegionalCenter> findByName(String name);

	List<RegionalCenter> findByCompany_Id(Long id);

	Page<RegionalCenter> findByCompany_Id(Long id, Pageable pageable);

	List<RegionalCenter> findByRegionalCenterZoneLookup_IdAndAdminUserName(Long id,String adminUserName);

	//List<RegionalCenter> findByRegionalCenterZone_Id(Long id);

	RegionalCenter findByEmail(String name);
	
	RegionalCenter findByMobileNumber(Long mobileNumber);

	List<RegionalCenter> findByRegionalCenterZoneLookup_IdAndAdminUserNameAndStatus(Long id, String loggedUsername,boolean b);

	List<RegionalCenter> findByNameAndCompany_Id(String name, Long id);

	List<RegionalCenter> findByNameAndUserAccount_Id(String name, Long id);

	Page<RegionalCenter> findByUserAccount_Id(Long id, Pageable pageRequest);
}