package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.Model.DoctorManagement.School;

@Repository
public interface SchoolRepository extends PagingAndSortingRepository<School, Long> {

	School findBySchoolName(String schoolName);

	Page<School> findByCompany_Id(Long id, Pageable pageRequest);
    
    School findBySchoolNameAndUserAccount_UniqueId(String schoolName, String uniqueId);

	School findBySchoolNameAndCompany_Id(String schoolName, Long id);

	School findBySchoolNameAndUserAccount_Id(String schoolName, Long id);

	List<School> findByUserAccount_IdAndProductOwnerStatus(Long id, boolean b);

	List<School> findByCompany_Id(Long id);

	School findByEmail(String email);

	School findByMobileNumber(Long mobileNumber);

	List<School> findBySchoolId(Long id);

	List<School> findByCompany_IdAndStatus(Long id, boolean b);

	List<School> findByUserAccount_IdAndStatus(Long id, boolean b);
	
}
