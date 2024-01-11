package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Doctor;
@Repository
public interface TherapistRepository  extends PagingAndSortingRepository<Doctor, Long> {

	Doctor findBySpecialization(String specializatioon);
	Doctor findByUserAccount_Id(Long id);
	Doctor findByEmail(String doctorUsername);
    List<Doctor> findByCompany_Id(Long long1);
//    /Doctor findByNpi(String npi);
    Doctor findByRciNumber(String rci);
	List<Doctor> findByCompany_IdAndActive(Long long1,boolean status);
	List<Doctor> findByDepartment_Id(Long id);
	List<Doctor> findByCompany_IdAndActiveAndCreatedDateBetween(Long id,boolean status,LocalDate startdate,LocalDate enddate);
	List<Doctor> findByCompany_IdAndDepartment_Id(Long id, Long id2);
	List<Doctor> findByCompany_IdAndDepartment_IdAndUserAccount_Active(Long id, Long id2, boolean b);
	List<Doctor> findBySite_IdAndActive(Long id, boolean b);

}
