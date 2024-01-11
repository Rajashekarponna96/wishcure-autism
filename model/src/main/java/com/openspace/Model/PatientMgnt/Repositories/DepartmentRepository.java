package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.Department;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

	List<Department> findByDepartmentName(String name);
	
	List<Department> findById(long id);
	

	List<Department> findByDepartmentNameOrDescriptionName(String search, String search2);
	
	public static String GETALL_DEPARTMENT_SEARCH = "SELECT a FROM Department a WHERE a.company.id=:id and(a.departmentName LIKE %:search% OR a.descriptionName LIKE %:search%)";
	@Query(GETALL_DEPARTMENT_SEARCH)
	List<Department> findByDepartmentSearch(@Param("search") String search,@Param("id")  long id);

	List<Department> findByDepartmentNameAndCompany_Id(String departmentName, long id);

	Department findByDepartmentNameAndUserAccount_UniqueId(String departmentName, String uniqueId);

	Page<Department> findByCompany_Id(Long id, Pageable pageable);

	List<Department> findByCompany_Id(Long id);
	
	List<Department> findByCompany_IdAndUserAccount_Id(Long companyId, Long userId);

	List<Department> findByUserAccount_IdAndProductOwnerStatus(Long id, boolean b);

	List<Department> findByCompany_IdAndStatus(Long id, boolean b);

	List<Department> findByCompany_IdAndStatusAndRole_Id(Long id, boolean b,Long roleId);
	
	Department findByDepartmentNameAndUserAccount_Id(String departmentName, Long id);

	Department findByCompany_IdAndDepartmentName(Long id, String departmentName);
	
	List<Department> findByRole_Id(Long roleId);
	
	List<Department> findByPatientRegistrationType_Id(Long id);

	List<Department> findByCompany_IdAndStatusAndPatientRegistrationType_Id(Long companyId, boolean status, Long regTypeId);

	List<Department> findByUserAccount_IdAndProductOwnerStatusAndPatientRegistrationType_Id(Long id, boolean status,
			Long regTypeId);
	
	
	
	
	


}
