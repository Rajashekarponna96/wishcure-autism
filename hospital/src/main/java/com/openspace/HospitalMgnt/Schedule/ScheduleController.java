package com.openspace.HospitalMgnt.Schedule;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.Schedule;
import com.openspace.Model.DoctorManagement.SearchScheduleDto;
import com.openspace.Model.Dto.CalendarDto;
import com.openspace.Model.Dto.DateDto;
import com.openspace.Model.Dto.ScheduleDtoForCalendar;
import com.openspace.Model.Dto.ScheduledHourDto;

@RestController
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping(value = RestURIConstants.SAVE_SCHEDULE, method = RequestMethod.POST)
	public @ResponseBody void saveSchedule(@RequestBody ScheduleDto scheduleDto) {
		scheduleService.saveSchedule(scheduleDto);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SCHEDULES, method = RequestMethod.GET)
	public @ResponseBody List<Schedule> getAllSchedules() {
		return scheduleService.getAllSchedules();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_SCHEDULE, method = RequestMethod.PUT)
	public @ResponseBody void updateSchedule(@RequestBody ScheduleDto schedule) {
		scheduleService.updateSchedule(schedule);
	}

	@RequestMapping(value = RestURIConstants.DELETE_SCHEDULE, method = RequestMethod.DELETE)
	public @ResponseBody void deleteSchedule(@PathVariable Long id) {
		scheduleService.deleteSchedule(id);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SCHEDULES_BY_DOCTOR_USERNAME_PAGE, method = RequestMethod.GET)
	public @ResponseBody Page<Schedule> getAllSchedulesByDoctorUsernameByPagination(@PathVariable String doctorUsername,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return scheduleService.getAllSchedulesByDoctorUsername(doctorUsername, page, size);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SCHEDULES_BY_DOCTOR_USERNAME, method = RequestMethod.GET)
	public @ResponseBody List<Schedule> getAllSchedulesByDoctorUsername(@PathVariable String doctorUsername) {
		return scheduleService.getAllSchedulesByDoctorUsername(doctorUsername);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SCHEDULES_BY_ROLE, method = RequestMethod.GET)
	public @ResponseBody List<Schedule> getAllTherapistSchedulesByAdminUsername(@PathVariable String adminUsername) {
		return scheduleService.getAllTherapistSchedulesByAdminUsername(adminUsername);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SCHEDULES_BY_ADMIN_USERNAME_PAGE, method = RequestMethod.GET)
	public @ResponseBody Page<Schedule> getAllTherapistSchedulesByAdminUsername(@PathVariable String adminUsername,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return scheduleService.getAllTherapistSchedulesByAdminUsername(adminUsername, page, size);
	}

	@RequestMapping(value = RestURIConstants.FIND_DATE, method = RequestMethod.POST)
	public @ResponseBody void findDate(@RequestBody CalendarDto calendarDto) {
		scheduleService.findDate(calendarDto);
	}

	@RequestMapping(value = RestURIConstants.TODAY_DATE, method = RequestMethod.POST)
	public @ResponseBody List<ScheduleDtoForCalendar> findByToDate(@RequestBody CalendarDto calendarDto)
			throws ParseException {
		return scheduleService.findByToDate(calendarDto);
	}

	@RequestMapping(value = RestURIConstants.GET_SCHEDULES_BY_AVAILABLEDAYS, method = RequestMethod.POST)
	public @ResponseBody List<Schedule> findschedulesAvailableDays(@PathVariable("availableDay") String availableDay,
			@PathVariable("id") Long therapistId) {
		return scheduleService.findschedulesAvailableDays(availableDay, therapistId);
	}

	@RequestMapping(value = RestURIConstants.GET_SCHEDULES_BY_DAY, method = RequestMethod.POST)
	public @ResponseBody List<ScheduleDtoForCalendar> findSchedulesByDay(@RequestBody DateDto dateDto) {
		return scheduleService.findschedulesAvailableDays(dateDto);
	}

	@RequestMapping(value = RestURIConstants.SEARCH_SCHEDULES_BY_DAY, method = RequestMethod.POST)
	public @ResponseBody List<ScheduleDtoForCalendar> searchSchedulesByDay(
			@RequestBody SearchScheduleDto searchScheduleDto) {
		return scheduleService.searchSchedulesByDay(searchScheduleDto);
	}
	
	@RequestMapping(value = RestURIConstants.SEARCH_SCHEDULES_BY_DAY1, method = RequestMethod.POST)
	public @ResponseBody List<ScheduleDtoForCalendar> searchSchedulesByDay1(
			@RequestBody SearchScheduleDto searchScheduleDto) {
		return scheduleService.searchSchedulesByDay1(searchScheduleDto);
	}

	@RequestMapping(value = RestURIConstants.GET_SCHEDULED_HOURS, method = RequestMethod.POST)
	public List<String> ListOfScheduledhours(@RequestBody ScheduledHourDto scheduledHourDto) {
			return scheduleService.findScheduledHours(scheduledHourDto);
	}

}
