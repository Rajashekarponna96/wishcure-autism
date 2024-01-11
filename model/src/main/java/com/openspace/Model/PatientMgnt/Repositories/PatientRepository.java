package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Lookups.ClientType;

@Repository
public interface PatientRepository
		extends PagingAndSortingRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

	List<Patient> findByCreatedDate(LocalDate time);

	/*Patient findByFirstNameAndMobileNumberAndEmail(String name, Long number, String email);

	Patient findByFirstNameAndMobileNumberAndAddress_Country_IdAndAddress_State_IdAndAddress_CityNameAndAddress_ZipcodeAndAddress_Address1(
			String name, Long number, Long cid, Long sid, String cityname, String zipcode, String address1);

	Patient findByFirstNameAndMobileNumberAndAddress_Country_IdAndAddress_State_IdAndAddress_ZipcodeAndAddress_Address1AndAddress_CityName(
			String name, Long number, Long cid, Long sid, String zipcode, String address1, String cityName);

	Patient findByFirstNameAndMobileNumberAndAddress_Country_IdAndAddress_State_Id(String name, Long number, Long cid,
			Long sid);*/

	Patient findByMobileNumber(Long number);
	
	Patient findByPhoneNumberAndEmailPatientAndFirstName(Long mobilenumber, String email, String fisrtname);

	Patient findByEmail(String email);

	Patient findByIdAndActive(Long id, boolean active);

	List<Patient> findByAdminUserAndCompany_IdAndActiveAndCreatedDate(String username, Long id, boolean status,
			LocalDate time);

	Page<Patient> findByIdAndActive(Long id, boolean status, Pageable pageable);

	Patient findByUcl(String uci);

	Patient findBySsn(String ssn);
	
	Patient findByRegNumber(String regNo);

	Page<Patient> findByCompany_Id(Long id, Pageable pageable);

	List<Patient> findByCompany_IdAndActive(Long id, boolean b);

	List<Patient> findByAdminUserAndCompany_IdAndActiveAndCreatedDateBetween(String adminUserName, Long id, boolean b,
			LocalDate startDatelocaldate, LocalDate endDatelocaldate);

	List<Patient> findByIdAndActiveAndCreatedDateBetween(Long id, boolean b, LocalDate startDatelocaldate,
			LocalDate endDatelocaldate);

	List<Patient> findByAdminUserAndActive(String adminUserName, boolean b);

	List<Patient> findByIdAndActiveBetween(Long id, boolean b, LocalDate startDatelocaldate,
			LocalDate endDatelocaldate);

	List<Patient> findByAdminUserAndActiveBetween(String adminUserName, boolean b, LocalDate startDatelocaldate,
			LocalDate endDatelocaldate);

	List<Patient> findByAdminUserAndActiveAndCreatedDateBetween(String adminUserName, boolean b,
			LocalDate startDatelocaldate, LocalDate endDatelocaldate);

	List<Patient> findByAppointmentCreated(boolean b);

	List<Patient> findByAdminUserAndActiveAndCreatedDate(String adminUserName, Long id, boolean b, LocalDate now);

	List<Patient> findByAdminUserAndActiveAndCreatedDate(String adminUserName, String username, boolean b,
			LocalDate now);

	Page<Patient> findByAdminUser(String username, Pageable pageable);

	List<Patient> findByAdminUserAndActiveAndCreatedDate(String username, boolean b, LocalDate presentDate);

	Page<Patient> findByAdminUserAndCompany_Id(String username, Long id, Pageable pageable);

	List<Patient> findByAdminUserAndCompany_IdAndActive(String adminUserName, Long id, boolean b);

	List<Patient> findByAdminUserAndCompany_Id(String adminUserName, Long id);

	List<Patient> findByAdminUserAndActive(String adminUserName, Long id, boolean b);

	List<Patient> findByAdminUser(String adminUserName);

	List<Patient> findByAdminUserAndActiveAndAppointmentCreated(String username, boolean b, boolean c);

	List<Patient> findByCompany_IdAndActiveAndAppointmentCreated(Long id, boolean b, boolean c);

	Page<Patient> findByAdminUserAndCompany_IdAndActive(String username, Long id, boolean b, Pageable pageable);

	Page<Patient> findByAdminUserAndActive(String username, boolean b, Pageable pageable);

	Page<Patient> findByIdAndActive(Set<Long> ids, boolean status, Pageable pageable);

	Set<Patient> findByClientTypeAndActive(ClientType clientType,boolean b);

	List<Patient> findByFirstNameOrMobileNumberOrLastName(String search, Long search2, String search3);
	
	public static String GETALL_PATIENTS_BY_FirstName_LastName = "SELECT a FROM Patient a WHERE a.adminUser=:adminUserName AND a.company.id=:cid AND a.active=:flag AND(a.firstName LIKE %:search% OR a.lastName LIKE %:search%)";
	@Query(GETALL_PATIENTS_BY_FirstName_LastName)
	List<Patient> findAllPatientsSearch(@Param("adminUserName") String adminUserName,@Param("cid") Long cid,@Param("search") String search,@Param("flag") Boolean flag);
	
	
	public static String GETALL_PATIENTS_BY_FirstName_LastName_THERAPIST = "SELECT a FROM Patient a WHERE a.id=:id AND a.active=:flag AND(a.firstName LIKE %:search% OR a.lastName LIKE %:search%)";
	@Query(GETALL_PATIENTS_BY_FirstName_LastName_THERAPIST)
	List<Patient> findAllPatientsSearchTherapist(@Param("id") Long id,@Param("flag") Boolean flag,@Param("search") String search);
	
	public static String GETALL_PATIENTS_BY_FirstName_LastName_INDIVIDUAL = "SELECT a FROM Patient a WHERE a.adminUser=:adminUserName AND a.active=:flag AND(a.firstName LIKE %:search% OR a.lastName LIKE %:search%)";
	@Query(GETALL_PATIENTS_BY_FirstName_LastName_INDIVIDUAL)
	List<Patient> findAllPatientsSearchIndividual(@Param("adminUserName") String adminUserName,@Param("flag") Boolean flag,@Param("search") String search);

	//List<Patient> findByDepartment_Id(Long id);

	List<Patient> findBySchool_Id(Long id);

	Patient findByEmailPatient(String email);
	
	Patient findByPhoneNumber(Long phoneNumber);
	
	Patient findByFirstNameAndEmailPatient(String fname,String email);
	
	Patient findByIdAndPatientRegistrationType_Id(Long patientId, Long regTypeId);

	Patient findById(Long id);

	List<Patient> findByCompany_Id(Long id);

}
