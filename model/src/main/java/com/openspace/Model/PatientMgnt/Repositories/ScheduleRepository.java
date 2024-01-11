package com.openspace.Model.PatientMgnt.Repositories;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.AppointMentStatus;
import com.openspace.Model.DoctorManagement.Schedule;
import com.openspace.Model.DoctorManagement.ScheduleStatus;
import com.openspace.Model.DoctorManagement.SubAppointment;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {

	Schedule findById(Long id);

	Page<Schedule> findByDoctor_Id(Long id, Pageable pageable);

	Schedule findByFromTimeAndToTimeAndAvailableDays(Timestamp fromTime, Timestamp toTime, List<String> list);

	// List<Schedule> findByPresentDateBetween(LocalDate startDate, LocalDate
	// endDate);
	public static String FIND_SCHEDULES = "select s from Schedule s where :day in s.availableDays";

	@Query(value = FIND_SCHEDULES)
	List<Schedule> findSchedulesByDay(@Param("day") String day);

	List<Schedule> findByAvailableDaysIn(List<String> availableDays);

	List<Schedule> findByAvailableDaysInAndDoctor_Id(List<String> availableDays, Long id);

	List<Schedule> findByAvailableDaysInAndDoctor_IdAndScheduleStatus(List<String> availableDays, Long id,
			ScheduleStatus scheduleStatus);

	Schedule findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
			List<String> availableDays, Long id, LocalTime start, LocalTime endtime);

	Schedule findByAvailableDaysInAndDoctor_IdAndScheduleStatusAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
			List<String> availableDays, Long id, ScheduleStatus scheduleStatus, LocalTime start, LocalTime endtime);

	List<Schedule> findByDoctor_IdAndAvailableDaysInAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(Long id,
			List<String> availableDays, LocalTime start, LocalTime endtime);
	
	List<Schedule> findByDoctor_IdAndAvailableDaysInAndFromTimeLessThanEqualAndToTimeGreaterThanEqual(Long id,
			List<String> availableDays, LocalTime start, LocalTime endtime);

	List<Schedule> findByDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(Long id,
			 LocalTime start, LocalTime endtime);
	
	Set<Schedule> findByAvailableDaysInAndDoctor_IdAndFromTimeLessThanEqualAndToTimeGreaterThanEqual(
			List<String> availableDays, Long id, LocalTime start, LocalTime endtime);

	List<Schedule> findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqualAndScheduleStatus(
			List<String> availableDays, Long id, LocalTime start, LocalTime endtime, ScheduleStatus scheduleStatus);

	List<Schedule> findByAvailableDaysInAndDoctor_IdAndToTimeGreaterThanEqualAndFromTimeLessThanEqual(
			List<String> availableDays, Long id, LocalTime endtime, LocalTime start);
	/* Page<Schedule> findByDoctor_Id(Long id, PageRequest pageRequest); */

	List<Schedule> findByDoctor_Id(Long id);

	Schedule findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndFromTimeLessThan(List<String> availableDays,
			Long id, LocalTime start, LocalTime endtime);

	Schedule findByAvailableDaysInAndDoctor_IdAndToTimeGreaterThanAndToTimeLessThanEqual(List<String> list, Long id,
			LocalTime appointmentStartTime, LocalTime appointmentEndTime);

	Schedule findByAvailableDaysInAndDoctor_IdAndFromTimeLessThanAndToTimeGreaterThan(List<String> list, Long id,
			LocalTime appointmentStartTime, LocalTime appointmentEndTime);

	public static String GET_SCHEDULES = "SELECT s FROM Schedule s WHERE  s.doctor.id=:id and s.availableDays=:days and s.fromTime >= :fromTime AND s.toTime <= :totime";

	@Query(value = GET_SCHEDULES)
	List<Schedule> findSchedulesbyTimings(@Param("id") Long id, @Param("days") List<String> availableDays,
			@Param("fromTime") LocalTime fromTime, @Param("totime") LocalTime totime);

}
