package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.ActiveStatus;
import com.openspace.Model.DoctorManagement.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	Person findByEmail(String email);
	Person findByUserAccount_Id(Long id);
	Page<Person> findByUserAccount_Id(Long id,Pageable pageable);
	Person findByMobileNumber(Long mobileNumber);
	Person findByUserAccount_IdAndCreatedDateBetween(Long id, LocalDate startDatelocaldate, LocalDate endDatelocaldate);
	List<Person> findByUserAccount_Company_IdAndCreatedDateBetween(Long id, LocalDate startDatelocaldate,
			LocalDate endDatelocaldate);
	Page<Person> findByUserAccount_IdAndUserAccount_Company_Id(Long id, Long id2,Pageable pageable);
	Page<Person> findByUserAccount_Company_Id(Long id,Pageable pageable);
	List<Person> findByUserAccount_Company_IdAndActive(Long id,boolean b);
	List<Person> findByUserAccount_Company_Id(Long id);
	Person findByUserAccount_IdAndActive(Long id, boolean b);

	List<Person> findByUserAccount_Company_IdAndUserAccount_Role_RoleNameAndActive(Long id, String roleName,
			Boolean active, Pageable pageable);
	
	Page<Person> findByUserAccount_Company_IdAndActiveAndUserAccount_Role_RoleName(Long id, Boolean active,
			String roleName, Pageable pageable);
	
	public static String GETALL_PERSON_SEARCH = "SELECT a FROM Person a WHERE a.userAccount.company.id =:id AND(a.firstName LIKE %:search% OR a.lastName LIKE %:search%  OR a.email LIKE %:search%)";
	@Query(GETALL_PERSON_SEARCH)
	List<Person> findByPersonSearch(@Param("search") String search,@Param("id") Long id);
	List<Person> findByUserAccount_Company_IdAndUserAccount_Role_RoleNameAndActive(Long id, String roleName,
			Boolean active);
	Person findByUserAccount_Role_RoleNameAndUserAccount_Company_Id(String string, Long id);
	List<Person> findByUserAccount_Role_RoleNameAndActiveStatus(String string, ActiveStatus status);
	List<Person> findByUserAccount_Role_RoleNameAndActiveStatusAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(
			String string, ActiveStatus status, LocalDate startDate, LocalDate endDate);
	List<Person> findByUserAccount_Role_RoleNameAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(String string,
			LocalDate startDate, LocalDate endDate);
	Page<Person> findByModifiedByUserAndUserAccount_Company_Id(String adminUsername ,Long id,Pageable pageable);
	Page<Person> findByModifiedByUserAndActiveAndUserAccount_Company_Id(String adminUsername ,Boolean active ,Long id,Pageable pageable);
}
