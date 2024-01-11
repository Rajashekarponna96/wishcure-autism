package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
//gitlab.com/openspace2011/ModelNew.git
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.AppointMentStatus;
import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Patient;

@Repository
public interface AppointmentRepository
		extends PagingAndSortingRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {
	Page<Appointment> findByPerson_Id(Long id, Pageable pageable);
	Page<Appointment> findByPerson_IdAndActive(Long id,boolean active, Pageable pageable);
	List<Appointment> findByDoctor_Id(Long id);
	List<Appointment> findByDoctor_IdAndActive(Long id,boolean active);

	public static String GETALL_APPOINTMENTS_BY_DOCTOR_ID_BETWEENDATES = "SELECT a FROM Appointment a WHERE a.doctor.id=:id and  :startDate  BETWEEN a.appointmentStartedDate AND a.appointmentEndedDate  or  :endDate BETWEEN a.appointmentStartedDate AND a.appointmentEndedDate";

	@Query(value = GETALL_APPOINTMENTS_BY_DOCTOR_ID_BETWEENDATES)
	List<Appointment> getAllAppointments(@Param("id") Long id, @Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	public static String GETALL_APPOINTMENTS_BY_ADMIN_USERNAME_BETWEENDATES = "SELECT a FROM Appointment a WHERE a.person.id=:id and  :startDate  BETWEEN a.appointmentStartedDate AND a.appointmentEndedDate  or  :endDate BETWEEN a.appointmentStartedDate AND a.appointmentEndedDate";

	@Query(value = GETALL_APPOINTMENTS_BY_ADMIN_USERNAME_BETWEENDATES)
	List<Appointment> getAllAppointmentsByAdmin(@Param("id") Long id, @Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	public static String GETALL_APPOINTMENTS_BY_DOCTOR_ID = "SELECT a FROM Appointment a WHERE a.doctor.id=:id and  :presentdate BETWEEN a.appointmentStartedDate AND a.appointmentEndedDate";

	@Query(value = GETALL_APPOINTMENTS_BY_DOCTOR_ID)
	List<Appointment> findByDoctor_IdAnd(@Param("id") Long id, @Param("presentdate") LocalDate presentdate);

	List<Appointment> findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			Long id, LocalDate startdate, LocalDate enddate);

	List<Appointment> findByPerson_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			Long id, LocalDate startdate, LocalDate enddate);

	List<Appointment> findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndAppointmentStatus(
			Long id, LocalDate startdate, LocalDate enddate, AppointMentStatus appointmentStatus);

	Page<Appointment> findByDoctor_IdAndAppointmentStartedDate(Long id, LocalDate date,Pageable pageable);
	Page<Appointment> findByDoctor_IdAndAppointmentStartedDateAndActive(Long id, LocalDate date,boolean active,Pageable pageable);

	List<Appointment> findByDoctor_IdAndAppointmentStartTimeGreaterThanEqualAndAppointmentEndTimeLessThanEqualAndAppointmentStatusAndAppointmentStartedDateOrAppointmentEndedDate(
			Long id, LocalTime startTime, LocalTime endTime, AppointMentStatus appointmentStatus, LocalDate startDate,
			LocalDate endDate);

	List<Appointment> findByDoctor_IdAndAppointmentStartedDateAndAppointmentStatus(Long id, LocalDate date,
			AppointMentStatus appointMentStatus);
	

	List<Appointment> findByDoctor_IdAndAppointmentStartedDateAndAppointmentEndedDateAndAppointmentStatus(Long id, LocalDate stdate,LocalDate enddate,
			AppointMentStatus appointMentStatus);
	List<Appointment> findByDoctor_IdAndAppointmentStartedDate(Long id, LocalDate now);
	List<Appointment> findByDoctor_IdAndAppointmentStartedDateAndActive(Long id, LocalDate now,boolean active);
	List<Appointment> findByPerson_Id(Long id);
	List<Appointment> findByPerson_IdAndActive(Long id,boolean active);
	Page<Appointment> findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			Long id, LocalDate startlocalDate, LocalDate endDate,Pageable pageable);
	Page<Appointment> findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
			Long id, LocalDate startlocalDate, LocalDate endDate,boolean active,Pageable pageable);
	Page<Appointment> findByPerson_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			Long id, LocalDate startDate, LocalDate endDate,Pageable pageable);
	Page<Appointment> findByPerson_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
			Long id, LocalDate startDate, LocalDate endDate,boolean active,Pageable pageable);

	List<Appointment> findByPatient_IdAndActive(Long id,boolean active);
	List<Appointment> findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndActive(Long id, LocalDate now,boolean b);
	Page<Appointment> findByDoctor_IdAndAppointmentEndedDateGreaterThanEqualAndActive(Long id, LocalDate now,boolean b,Pageable pageable);
	Page<Appointment> findByDoctor_IdAndActive(Long id, boolean b, Pageable pageable);
			
			

}
