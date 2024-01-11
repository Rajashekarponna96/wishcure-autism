package com.openspace.HospitalMgnt.Schedule;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Schedule;
import com.openspace.Model.DoctorManagement.SearchScheduleDto;
import com.openspace.Model.Dto.CalendarDto;
import com.openspace.Model.Dto.DateDto;
import com.openspace.Model.Dto.ScheduleDtoForCalendar;
import com.openspace.Model.Dto.ScheduledHourDto;



public interface ScheduleService {

	
	void saveSchedule(ScheduleDto scheduleDto);

	List<Schedule> getAllSchedules();

	void updateSchedule(ScheduleDto schedule);

	void deleteSchedule(Long id);
	
	Page<Schedule> getAllSchedulesByDoctorUsername(String doctorUsername,int page,int size);

	List<Schedule> getAllSchedulesByDoctorUsername(String doctorUsername);

	List<Schedule> getAllTherapistSchedulesByAdminUsername(String doctorUsername);

	Page<Schedule> getAllTherapistSchedulesByAdminUsername(String adminUsername, int page, int size);

	void findDate(CalendarDto calendarDto);

	List<ScheduleDtoForCalendar> findByToDate(CalendarDto calendarDto) throws ParseException ;

	List<Schedule> findschedulesAvailableDays(String availableDay, Long therapistId);

	List<ScheduleDtoForCalendar> findschedulesAvailableDays(DateDto dateDto);

	List<ScheduleDtoForCalendar> searchSchedulesByDay(SearchScheduleDto searchScheduleDto);

	List<String> findScheduledHours(ScheduledHourDto scheduledHourDto);

	List<ScheduleDtoForCalendar> searchSchedulesByDay1(SearchScheduleDto searchScheduleDto);

 }
