package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Insurance;

@Repository
public interface InsuranceRepository extends PagingAndSortingRepository<Insurance, Long> {
	
	Insurance findByInsuranceCompanyName(String name);

	List<Insurance> findByCompany_Id(Long id);
	
	Page<Insurance> findByCompany_Id(Long id, Pageable pageable);

	Insurance findByEmailId(String email);
	
	Insurance findByMobileNumber(Long mobileNumber);

	List<Insurance> findByCompany_IdAndStatus(Long id, boolean b);

	List<Insurance> findByUserAccount_IdAndStatus(Long id, boolean b);

	List<Insurance> findByInsuranceLookupId(Long id);

	Page<Insurance> findByuserAccount_Id(Long id, Pageable pageRequest);

	Insurance findByInsuranceCompanyNameAndUserAccount_Id(String insuranceCompanyName, Long id);
}
