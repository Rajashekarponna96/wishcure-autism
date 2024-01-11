package com.openspace.Model.PatientMgnt.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
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
import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.Dto.PaidStatus;
import com.openspace.Model.Payment.Payment;

@Repository
public interface SubAppointmentRepository extends PagingAndSortingRepository<SubAppointment, Long>,JpaSpecificationExecutor<SubAppointment> {

	List<SubAppointment> findByDoctor_IdAndAppointmentStartTimeGreaterThanEqualAndAppointmentEndTimeLessThanEqualAndAppointMentStatusAndAppointmentStartedDateOrAppointmentEndedDate(
			Long id, LocalTime startTime, LocalTime endTime, AppointMentStatus appointmentStatus, LocalDate startDate,
			LocalDate endDate);

	List<SubAppointment> findByDoctor_IdAndAppointmentStartTimeGreaterThanEqualAndAppointmentEndTimeLessThanEqualAndAppointMentStatusAndAppointmentStartedDateAndActive(
			Long id, LocalTime startTime, LocalTime endTime, AppointMentStatus appointmentStatus, LocalDate startDate,
			boolean activee);

	List<SubAppointment> findByDoctor_IdAndAppointmentStartedDateAndAppointmentEndedDateAndAppointMentStatusAndActive(
			Long id, LocalDate stdate, LocalDate enddate, AppointMentStatus appointMentStatus, boolean active);

	List<SubAppointment> findByDoctor_IdAndAppointmentStartedDateAndActive(Long id, LocalDate now, boolean active);

	List<SubAppointment> findByPerson_IdAndActive(Long id, boolean active);

	List<SubAppointment> findByDoctor_IdAndAppointmentStartedDateAndAppointmentStartTimeAndAppointmentEndTimeAndAppointMentStatusAndActive(
			Long doctorId, LocalDate startDate, LocalTime startTime, LocalTime endTime,
			AppointMentStatus appointMentStatus, boolean active);

	List<SubAppointment> findByDoctor_IdAndAppointmentStartedDateAndAppointMentStatusAndActiveAndAppointmentStartTimeBetween(
			Long id, LocalDate stdate, AppointMentStatus appointMentStatus, boolean active, LocalTime stTime,
			LocalTime endTime);

	public static String GET_SUB_APPOINTMENTS = "SELECT sa FROM  SubAppointment sa WHERE sa.doctor.id=:id  and sa.appointMentStatus=:status and sa.appointmentStartedDate=:startDate and sa.active=:active and sa.appointmentStartTime >= :startTime AND sa.appointmentEndTime <= :endTime";

	@Query(value = GET_SUB_APPOINTMENTS)
	List<SubAppointment> findSubAppointmentsByScheduledTimes(@Param("id") Long id,
			@Param("status") AppointMentStatus appointMentStatus, @Param("startDate") LocalDate startDate,
			@Param("active") boolean active, @Param("startTime") LocalTime startTime,
			@Param("endTime") LocalTime endTime);

	List<SubAppointment> findByDoctor_IdAndActive(Long id, boolean active);

	public static String GET_ALL_ACTIVE_SUBAPPOINTMENTS = "SELECT sa FROM  SubAppointment sa WHERE sa.active=:active AND sa.appointmentEndedDate >= :endDate";

	@Query(value = GET_ALL_ACTIVE_SUBAPPOINTMENTS)
	List<SubAppointment> getActiveSubAppointments(@Param("active") boolean active, @Param("endDate") LocalDate endDate);

	List<SubAppointment> findByPerson_IdAndAppointmentStartedDateAndActive(Long id, LocalDate now, boolean b);

	List<SubAppointment> findByPatient_IdAndActiveAndStatus(Long id, boolean b, SubAppointmentStatus complete);

	List<SubAppointment> findByPatient_IdAndActiveAndStatusAndMonth(Long id, boolean b, SubAppointmentStatus complete,
			Month month);
	
	List<SubAppointment> findByPatient_IdAndActiveAndStatusAndMonthAndYear(Long id, boolean b, SubAppointmentStatus complete,
			Month month, int year);

	List<SubAppointment> findByPatient_IdAndActive(Long id, boolean b);

	List<SubAppointment> findByActiveAndPaidStatusAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			boolean b, PaidStatus status,LocalDate startlocalDate, LocalDate endDate);

	List<SubAppointment> findByActiveAndPaidStatus(boolean b, PaidStatus status);
//Now
	List<SubAppointment> findByActiveAndPaidStatusAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndOccuranceAndDoctor_Department_IdAndPatient_ClientType_Id(
			boolean b, PaidStatus status, LocalDate startedDate, LocalDate endDate, Occurance occurance, Long id,
			Long id2);

	List<SubAppointment> findByPatient_IdAndActiveAndPaidStatus(Long id, boolean b, PaidStatus paid);
	
	List<SubAppointment> findByMonthAndYear(Month month, int year);

	List<SubAppointment> findByMonthAndYearAndPatientId(Month month, Integer year, Long id);

	List<SubAppointment> findByPatient_IdAndActiveAndPaidStatusAndStatus(Long id, boolean b, PaidStatus due,
			SubAppointmentStatus complete);

	List<SubAppointment> findByDoctor_IdAndActiveAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			Long id, boolean b,LocalDate startDate, LocalDate endDate);

	List<SubAppointment> findByActiveAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			boolean b, LocalDate startDate, LocalDate endDate);

	List<SubAppointment> findByDoctor_IdAndActiveAndStatusAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			Long id, boolean b, SubAppointmentStatus complete, LocalDate startDate, LocalDate endDate);

	List<SubAppointment> findByDoctor_IdAndActiveAndStatus(Long id, boolean b, SubAppointmentStatus complete);

	List<SubAppointment> findByActiveAndStatusAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
			boolean b, SubAppointmentStatus complete, LocalDate startDate, LocalDate endDate);

	List<SubAppointment> findByActiveAndStatus(boolean b, SubAppointmentStatus complete);

	List<SubAppointment> findByPerson_IdAndActiveAndStatus(Long id, boolean b, SubAppointmentStatus complete);

	Page<SubAppointment> findByDoctor_IdAndAppointmentStartedDateAndActive(Long id, LocalDate now, boolean b,
			Pageable pageable);

	Page<SubAppointment> findByPerson_IdAndActive(Long id, boolean b, Pageable pageable);

	Page<SubAppointment> findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
			Long id, LocalDate startDate, LocalDate endDate, boolean b, Pageable pageable);

	Page<SubAppointment> findByPerson_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
			Long id, LocalDate startDate, LocalDate endDate, boolean b, Pageable pageable);

	List<SubAppointment> findByActiveAndInvoiceNo(boolean b, String invoiceNumber);

	List<SubAppointment> findByInvoiceNo(String invoiceNo);

	Page<SubAppointment> findByPerson_IdAndAppointmentStartedDateAndActive(Long id, LocalDate now, boolean b,
			Pageable pageable);

	List<SubAppointment> findByAppointment_Id(Long id);

	List<SubAppointment> findByDoctor_IdAndActiveAndStatusAndAppointmentStartedDateGreaterThanEqual(Long id, boolean b,
			SubAppointmentStatus awaiting, LocalDate now);

	SubAppointment findByAppointmentStartTimeGreaterThanEqualAndAppointmentEndTimeLessThanEqualAndAppointMentStatusAndAppointmentStartedDateAndActiveAndDoctor_Id(
			 LocalTime parse, LocalTime parse2, AppointMentStatus appointement, LocalDate appointedDate,
			boolean b, Long id2);

	List<SubAppointment> findByPerson_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
			Long id, LocalDate startDate, LocalDate endDate, boolean b);

	List<SubAppointment> findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
			Long id, LocalDate startDate, LocalDate endDate, boolean b);

	List<SubAppointment> findByActiveAndInvoiceNoAndPatient_Id(boolean b, String invoiceNumber, Long patientId);

}
