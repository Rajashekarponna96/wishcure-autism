
package com.openspace.HospitalMgnt.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.AppointMentStatus;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Holiday;
import com.openspace.Model.DoctorManagement.Schedule;
import com.openspace.Model.DoctorManagement.ScheduleStatus;
import com.openspace.Model.DoctorManagement.SearchScheduleDto;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.Dto.CalendarDto;
import com.openspace.Model.Dto.DateDto;
import com.openspace.Model.Dto.ScheduleDtoForCalendar;
import com.openspace.Model.Dto.ScheduledHourDto;
import com.openspace.Model.PatientMgnt.Repositories.HolidaysRepository;
import com.openspace.Model.PatientMgnt.Repositories.ScheduleRepository;
import com.openspace.Model.PatientMgnt.Repositories.SubAppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.CalendarUtil;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private TherapistRepository therapistRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private HolidaysRepository holidaysRepository;

	@Autowired
	private SubAppointmentRepository subAppointmentRepository;

	@Override
	public List<Schedule> getAllSchedules() {
		// TODO Auto-generated method stub
		return (List<Schedule>) scheduleRepository.findAll();
	}

	@Override
	public void updateSchedule(ScheduleDto schedule) {
		Schedule dbSchedule = scheduleRepository.findOne(schedule.getId());
		if (dbSchedule == null) {
			throw new RuntimeException(ErrorMessageHandler.doctorDoesNotExists);
		}
		scheduleRepository.save(dbSchedule);
	}

	@Override
	public void deleteSchedule(Long id) {
		Schedule dbSchedule = scheduleRepository.findOne(id);
		if (dbSchedule == null) {
			throw new RuntimeException("Doctor Doesn't exists");
		}
		if (dbSchedule.getScheduleStatus().name().equals("APPOINTED")) {
			throw new RuntimeException(ErrorMessageHandler.appointedSchedulesCannotBeDeleted);

		}
		scheduleRepository.delete(id);
	}

	@Override
	public void saveSchedule(ScheduleDto scheduleDto) {
		Doctor doctor = therapistRepository.findByEmail(scheduleDto.getDoctorUsername());
		LocalTime toTime = LocalTime.parse(scheduleDto.getToTime());
		LocalTime fromTime = LocalTime.parse(scheduleDto.getFromTime());
		List<Schedule> scheduleList = scheduleRepository.findByDoctor_Id(doctor.getId());
		for (Schedule sc : scheduleList) {

			for (String dbday : sc.getAvailableDays()) {
				for (String day : scheduleDto.getAvailableDays()) {
					if (dbday.equals(day)) {

						if (sc.getFromTime().equals(fromTime)) {
							throw new RuntimeException(ErrorMessageHandler.scheduleCannotBeCreatedDuringTheseHours);
						}
						if (sc.getToTime().equals(toTime)) {
							throw new RuntimeException(ErrorMessageHandler.scheduleCannotBeCreatedDuringTheseHours);
						}

						if (fromTime.isAfter(sc.getFromTime()) && fromTime.isBefore(sc.getToTime())) {
							// checkes whether the current time is between
							// 14:49:00 and
							// 20:11:13.
							throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
						}
						if (fromTime.isBefore(sc.getFromTime()) && toTime.isAfter(sc.getToTime())) {
							throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
						}
						if (toTime.isAfter(sc.getFromTime()) && toTime.isBefore(sc.getToTime())) {
							// checkes whether the current time is between
							// 14:49:00 and
							// 20:11:13.
							throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
						}

					}
				}
			}
		}
		if (fromTime.equals(toTime)) {
			throw new RuntimeException(ErrorMessageHandler.endtimeAndStartTimeShouldNotBeEqual);
		}
		if (fromTime.isAfter(toTime)) {
			throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterThanStarttime);
		}
		for (String day : scheduleDto.getAvailableDays()) {
			Schedule schedule = new Schedule();
			schedule.setDoctor(doctor);
			List<String> days = new ArrayList<String>();
			days.add(day);
			schedule.setFromTime(fromTime);
			schedule.setToTime(toTime);
			schedule.setAvailableDays(days);
			schedule.setScheduleStatus(ScheduleStatus.AVAILABILITY);
			// schedule.setPresentDate(LocalDate.now());
			scheduleRepository.save(schedule);
		}

	}

	@Override
	public Page<Schedule> getAllSchedulesByDoctorUsername(String doctorUsername, int page, int size) {
		Doctor doctor = therapistRepository.findByEmail(doctorUsername);
		Page<Schedule> schedules = null;
		if (doctor.getEmail() != null) {
			schedules = scheduleRepository.findByDoctor_Id(doctor.getId(),
					new PageRequest(page, size, Sort.Direction.DESC, "id"));
		} else {
			throw new RuntimeException(ErrorMessageHandler.doctorDoesNotExists);
		}
		return schedules;
	}

	@Override
	public List<Schedule> getAllTherapistSchedulesByAdminUsername(String adminUsername) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		List<Doctor> therapists = new ArrayList<Doctor>();
		List<Schedule> schedulsList = new ArrayList<Schedule>();
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			Doctor doctor = therapistRepository.findByEmail(dbUserAccount.getUsername());
			if (doctor != null) {
				schedulsList = scheduleRepository.findByDoctor_Id(doctor.getId());
			}
		} else {
			therapists = therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			List<Schedule> scheduls = new ArrayList<Schedule>();
			for (Doctor doctor : therapists) {
				scheduls = scheduleRepository.findByDoctor_Id(doctor.getId());
				schedulsList.addAll(scheduls);
			}
		}

		return schedulsList;

	}

	/*
	 * @Override public Page<Schedule>
	 * getAllTherapistSchedulesByAdminUsername(String adminUsername, int page,
	 * int size) { UserAccount dbUserAccount =
	 * userAccountRepository.findByUsername(adminUsername); if (dbUserAccount ==
	 * null) { throw new RuntimeException("Admin Does not Exist"); }
	 * List<Doctor> therapists = new ArrayList<Doctor>(); Page<Schedule>
	 * schedulsList = null; if
	 * (dbUserAccount.getRole().getRoleName().equals("Therapist") ||
	 * dbUserAccount.getRole().getRoleName().equals("Individual")) { Doctor
	 * doctor = therapistRepository.findByEmail(dbUserAccount.getUsername()); if
	 * (doctor != null) { schedulsList =
	 * scheduleRepository.findByDoctor_Id(doctor.getId(), new PageRequest(page,
	 * size, Sort.Direction.DESC, "id")); } } else { therapists =
	 * therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
	 * List<Schedule> scheduls = new ArrayList<Schedule>(); for (Doctor doctor :
	 * therapists) { scheduls =
	 * scheduleRepository.findByDoctor_Id(doctor.getId());
	 * //schedulsList.addAll(scheduls); } }
	 * 
	 * return schedulsList;
	 * 
	 * }
	 */
	@Override
	public Page<Schedule> getAllTherapistSchedulesByAdminUsername(String adminUsername, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		List<Doctor> therapists = new ArrayList<Doctor>();
		List<Schedule> schedulsList1 = new ArrayList<Schedule>();
		List<Schedule> schedule = new ArrayList<Schedule>();
		Page<Schedule> schedulsList = null;

		if (dbUserAccount.getRole().getRoleName().equals("Therapist")
				|| dbUserAccount.getRole().getRoleName().equals("Individual")) {
			Doctor doctor = therapistRepository.findByEmail(dbUserAccount.getUsername());
			if (doctor != null) {
				schedule = scheduleRepository.findByDoctor_Id(doctor.getId());
				schedulsList1.addAll(schedule);
			}
		} else {
			therapists = therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			List<Schedule> scheduls = new ArrayList<Schedule>();
			for (Doctor doctor : therapists) {
				scheduls = scheduleRepository.findByDoctor_Id(doctor.getId());
				schedulsList1.addAll(scheduls);
			}
		}

		return schedulsList = new PageImpl<Schedule>(schedulsList1, new PageRequest(page, size), schedulsList1.size());

	}

	/*
	 * @Override public Page<Schedule>
	 * getAllTherapistSchedulesByAdminUsername(String adminUsername, int page,
	 * int size) { UserAccount dbUserAccount =
	 * userAccountRepository.findByUsername(adminUsername); if (dbUserAccount ==
	 * null) { throw new RuntimeException("User Doesnot  Exist"); } Page<Doctor>
	 * therapists = (Page<Doctor>) new ArrayList<Doctor>(); Page<Schedule>
	 * schedulsList = (Page<Schedule>) new ArrayList<Schedule>(); if
	 * (dbUserAccount.getRole().getRoleName().equals("Admin")) { therapists =
	 * (Page<Doctor>)
	 * therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
	 * Page<Schedule> scheduls = (Page<Schedule>) new ArrayList<Schedule>(); for
	 * (Doctor doctor : therapists) { scheduls = (Page<Schedule>)
	 * scheduleRepository.findByDoctor_Id(doctor.getId(), new PageRequest(page,
	 * size)); ((List<Schedule>) schedulsList).addAll((Collection<? extends
	 * Schedule>) scheduls); }
	 * 
	 * }
	 * 
	 * return schedulsList;
	 * 
	 * }
	 */

	@Override
	public void findDate(CalendarDto calendarDto) {
		if (calendarDto.getSourceType().equals("year")) {

		}
	}

	@Override
	public List<ScheduleDtoForCalendar> findByToDate(CalendarDto calendarDto) {
		CalendarUtil calendarUtil = new CalendarUtil();
		Map<String, List<ScheduleDtoForCalendar>> schedulesMap = new HashMap<String, List<ScheduleDtoForCalendar>>();
		

		List<Schedule> ListOfschedules = new ArrayList<Schedule>();
		List<ScheduleDtoForCalendar> ListOfschedulesDtos = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> dailySchedules = new ArrayList<ScheduleDtoForCalendar>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		UserAccount dbUserAccount = userAccountRepository.findByUsername(calendarDto.getUsername());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		List<Doctor> therapists = new ArrayList<Doctor>();
		if (calendarDto.getSourceType().equalsIgnoreCase("day")) {
			LocalDate presentDate = null;
			if (calendarDto.getSourceDate() == null) {
				presentDate = LocalDate.now();
			} else {
				presentDate = calendarUtil.convertDayToLocalDate(calendarDto.getSourceDate());
			}
			// String date = calendarDto.getSourceDate();
			// String[] values = date.split(" ");
			// String day = values[0].trim();
			DayOfWeek dayOfWeek = presentDate.getDayOfWeek();
			List<String> list = new ArrayList<String>();
			list.add(dayOfWeek.name());
			if (dbUserAccount.getRole().getRoleName().equals("Therapist")
					|| dbUserAccount.getRole().getRoleName().equals("Individual")) {
				Doctor doctor = therapistRepository.findByEmail(dbUserAccount.getUsername());
				if (doctor != null) {
					ListOfschedulesDtos = getDailySchedules(presentDate, doctor, list, formatter);
					
				}				
			} else {
				//Here adding Active
				therapists = therapistRepository.findByCompany_IdAndActive(dbUserAccount.getCompany().getId(),true);
				for (Doctor doctor : therapists) {

					dailySchedules = getDailySchedules(presentDate, doctor, list, formatter);
					ListOfschedulesDtos.addAll(dailySchedules);
				}
			}
		}
		if (calendarDto.getSourceType().equalsIgnoreCase("week")) {
			// String var="Week 47 of 2017";
			String date = calendarDto.getSourceDate();
			String[] values = date.split(" ");
			int week = Integer.parseInt(values[1].trim());
			int year = Integer.parseInt(values[3].trim());
			LocalDate startWeekDay = calendarUtil.convertweekAndYearToStartDateOfWeek(week, year);
			LocalDate endWeekDay = calendarUtil.convertweekAndYearToLastDateOfWeek(week, year);
			LocalDate endofWeekDay = endWeekDay.plusDays(1);
			for (LocalDate startDate = startWeekDay; startDate
					.isBefore(endofWeekDay); startDate = startDate.plusDays(1)) {
				LocalDate localDate = startDate;
				System.out.println(ListOfschedulesDtos.size());
				List<String> list = new ArrayList<String>();
				list.add(localDate.getDayOfWeek().name());
				if (dbUserAccount.getRole().getRoleName().equals("Therapist")
						|| dbUserAccount.getRole().getRoleName().equals("Individual")) {
					Doctor doctor = therapistRepository.findByEmail(dbUserAccount.getUsername());
					if (doctor != null) {

						schedulesMap=getWeeklySchedules(localDate, doctor, list, formatter);
						List<ScheduleDtoForCalendar> schedulesDtos = schedulesMap.get(startDate.getDayOfWeek().name());
						if (schedulesDtos != null) {
							ListOfschedulesDtos.addAll(schedulesDtos);
						}
					}
				} else {
					//Here Adding Active Status
					therapists = therapistRepository.findByCompany_IdAndActive(dbUserAccount.getCompany().getId(),true);
					for (Doctor doctor : therapists) {
						schedulesMap=getWeeklySchedules(localDate, doctor, list, formatter);
						List<ScheduleDtoForCalendar> schedulesDtos = schedulesMap.get(startDate.getDayOfWeek().name());
						if (schedulesDtos != null) {
							ListOfschedulesDtos.addAll(schedulesDtos);
						}
					}
				}
				list.removeAll(list);
			}
			return ListOfschedulesDtos;
		}
		if (calendarDto.getSourceType().equalsIgnoreCase("month")) {
			// String date="November 2017";

			LocalDate startDateOfMonth = null;
			LocalDate endDateOfMonth = null;
			if (calendarDto.getSourceDate() != null) {

				String givendate = calendarDto.getSourceDate();
				String[] values2 = givendate.split(" ");
				int year1 = Integer.parseInt(values2[1].trim());
				String monthName = values2[0];
				startDateOfMonth = calendarUtil.convertMonthNameToStartDate(monthName, year1);
				endDateOfMonth = calendarUtil.convertMonthNameToEndDate(monthName, year1);

			} else {
				LocalDate presentDate = LocalDate.now();
				String monthName = presentDate.getMonth().name();
				int year1 = presentDate.getYear();
				startDateOfMonth = calendarUtil.convertMonthNameToStartDate(monthName, year1);
				endDateOfMonth = calendarUtil.convertMonthNameToEndDate(monthName, year1);
			}

			LocalDate endDateOftheMonthDate = endDateOfMonth.plusDays(1);
			for (LocalDate startDate = startDateOfMonth; startDate
					.isBefore(endDateOftheMonthDate); startDate = startDate.plusDays(1)) {
				LocalDate localDate = startDate;
				System.out.println(ListOfschedulesDtos.size());
				List<String> list = new ArrayList<String>();
				list.add(localDate.getDayOfWeek().name());
				if (dbUserAccount.getRole().getRoleName().equals("Therapist")
						|| dbUserAccount.getRole().getRoleName().equals("Individual")) {
					Doctor doctor = therapistRepository.findByEmail(dbUserAccount.getUsername());
					if (doctor != null) {	
						dailySchedules = getDailySchedules(localDate, doctor, list, formatter);
					ListOfschedulesDtos.addAll(dailySchedules);
						}
				} else {
					therapists = therapistRepository.findByCompany_IdAndActive(dbUserAccount.getCompany().getId(),true);
					for (Doctor doctor : therapists) {
						dailySchedules = getDailySchedules(localDate, doctor, list, formatter);
						ListOfschedulesDtos.addAll(dailySchedules);
					}

				}
				list.removeAll(list);
			}
			return ListOfschedulesDtos;
		}
		if (calendarDto.getSourceType().equalsIgnoreCase("year")) {
			String year3 = calendarDto.getSourceDate();
			LocalDate startDateOfYear = calendarUtil.convertYeartoDate(Integer.parseInt(year3));
			LocalDate endDateOfYear = calendarUtil.convertYeartoEndDate(Integer.parseInt(year3));

			System.out.println("startDateOfYear" + startDateOfYear);
			System.out.println("endDateOfYear" + endDateOfYear);
		}
		System.out.println(ListOfschedules);
		return ListOfschedulesDtos;
	}

	@Override
	public List<Schedule> findschedulesAvailableDays(String availableDay, Long therapistId) {
		List<String> list = new ArrayList<String>();
		list.add(availableDay);
		List<Schedule> schedules = scheduleRepository.findByAvailableDaysInAndDoctor_Id(list, therapistId);
		return schedules;
	}
	@Override
	public List<ScheduleDtoForCalendar> findschedulesAvailableDays(DateDto dateDto) {
		Map<String, List<ScheduleDtoForCalendar>> schedulesMap = new HashMap<String, List<ScheduleDtoForCalendar>>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<ScheduleDtoForCalendar> sundaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> mondaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> tuesdaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> wednesdaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> thursdaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> fridaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> saturedaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<String> list = new ArrayList<String>();
		list.add(dateDto.getStartDate().getDayOfWeek().name());
		LocalDate date = dateDto.getStartDate();
		List<Schedule> schedules2 = scheduleRepository.findByAvailableDaysIn(list);
		for (Schedule schedule : schedules2) {
			ScheduleDtoForCalendar scheduleDtoForCalendar = new ScheduleDtoForCalendar();
			scheduleDtoForCalendar.setDoctorName(schedule.getDoctor().getFirstName());
			scheduleDtoForCalendar.setAvailableDays(schedule.getAvailableDays());
			scheduleDtoForCalendar.setColor(schedule.getColor());
			scheduleDtoForCalendar.setFromTime((schedule.getFromTime()).toString());
			scheduleDtoForCalendar.setToTime(schedule.getToTime().toString());

			scheduleDtoForCalendar
					.setStartDate(date.format(formatter) + "T" + (schedule.getFromTime()).toString() + ":00");
			scheduleDtoForCalendar.setEndDate(date.format(formatter) + "T" + (schedule.getToTime()).toString() + ":00");
			if (date.getDayOfWeek().name().equalsIgnoreCase("SUNDAY")) {
				sundaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(date.getDayOfWeek().name(), sundaySchedules);
			}
			if (date.getDayOfWeek().name().equalsIgnoreCase("MONDAY")) {
				mondaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(date.getDayOfWeek().name(), mondaySchedules);
			}
			if (date.getDayOfWeek().name().equalsIgnoreCase("TUESDAY")) {
				tuesdaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(date.getDayOfWeek().name(), tuesdaySchedules);
			}
			if (date.getDayOfWeek().name().equalsIgnoreCase("WEDNESDAY")) {
				wednesdaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(date.getDayOfWeek().name(), wednesdaySchedules);
			}
			if (date.getDayOfWeek().name().equalsIgnoreCase("THURSDAY")) {
				thursdaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(date.getDayOfWeek().name(), thursdaySchedules);
			}
			if (date.getDayOfWeek().name().equalsIgnoreCase("FRIDAY")) {
				fridaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(date.getDayOfWeek().name(), fridaySchedules);
			}
			if (date.getDayOfWeek().name().equalsIgnoreCase("SATURDAY")) {
				saturedaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(date.getDayOfWeek().name(), saturedaySchedules);
			}
		}
		return schedulesMap.get(date.getDayOfWeek().name());
	}

	@Override
	public List<ScheduleDtoForCalendar> searchSchedulesByDay1(SearchScheduleDto searchScheduleDto) {
		//searchScheduleDto.accurateEndDate="17-08-2018" dd-mm-yyyy  x
		  //														  /
		    //searchScheduleDto.localDate="2018-07-20"   yyyy-mm-dd \/
		if (searchScheduleDto.getLocalDate() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectStartDateAndTime);
		}
		if (searchScheduleDto.getFromTime() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectFromtime);
		}
		if (searchScheduleDto.getToTime() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectTotime);
		}
		if (searchScheduleDto.getEnddate() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectEndDate);
		}
		if (searchScheduleDto.getFromTime().equals(searchScheduleDto.getToTime())) {
			throw new RuntimeException(ErrorMessageHandler.endtimeShouldNotEqualToFromtime);
		}
		if (LocalTime.parse(searchScheduleDto.getFromTime()).isAfter(LocalTime.parse(searchScheduleDto.getToTime()))) {
			throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterthanFromtime);
		}
		List<Schedule> schedulesList = new ArrayList<Schedule>();
		List<Schedule> singleTherapistSchedulesList = new ArrayList<Schedule>();
		List<ScheduleDtoForCalendar> ScheduleDtosList = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> ScheduleDtosList1 = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> ScheduleDtosList2 = new ArrayList<ScheduleDtoForCalendar>();
		String[] values = searchScheduleDto.getLocalDate().split("T");
		String startDt = values[0];

		String[] values1 = searchScheduleDto.getEnddate().split("T");
		String endDt = values1[0];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate stdate = LocalDate.parse(startDt, formatter);
		LocalDate enddate = LocalDate.parse(endDt, formatter);
		System.out.println("stdate" + stdate);
		System.out.println("enddate" + enddate);
		UserAccount dbUserAccount = userAccountRepository.findByUsername(searchScheduleDto.getUsername());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}

		if (stdate.isBefore(LocalDate.now())) {
			throw new RuntimeException(ErrorMessageHandler.pleaseUpdateTheStatusOfPatientAppointments);

		}
		/*
		 * if (stdate.equals(LocalDate.now())) { if
		 * (LocalTime.parse(searchScheduleDto.getFromTime()).isBefore(LocalTime.
		 * now())) { throw new RuntimeException(
		 * "Your Selected Time Should be Greater Than The Present System Time");
		 * } }
		 */
		if (enddate.isBefore(stdate)) {
			throw new RuntimeException(ErrorMessageHandler.startdateShouldBeLessthanEnddate);
		}
		if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
			
			Doctor doctor = therapistRepository.findByEmail(dbUserAccount.getUsername());
			if (doctor == null) {
				throw new RuntimeException(ErrorMessageHandler.therapistDoesNotExists);
			}
			schedulesList=getSearchSchedules(stdate, enddate, doctor, searchScheduleDto);
			
		} else {// Admin and Others
			if (dbUserAccount.getCompany() != null) {
				System.out.println(searchScheduleDto.getDepartment().getId());
				List<Doctor> therapists = therapistRepository.findByCompany_IdAndDepartment_IdAndUserAccount_Active(dbUserAccount.getCompany().getId(),searchScheduleDto.getDepartment().getId(),true);
				for (Doctor doctor : therapists) {

					singleTherapistSchedulesList=getSearchSchedules(stdate, enddate, doctor, searchScheduleDto);
					schedulesList.addAll(singleTherapistSchedulesList);
				}

			}
		}
		if (schedulesList.size() > 0) {
			for (Schedule schedule : schedulesList) {
				ScheduleDtoForCalendar scheduleDtoForCalendar = new ScheduleDtoForCalendar();
				scheduleDtoForCalendar.setId(schedule.getId());
				scheduleDtoForCalendar.setAvailableDays(schedule.getAvailableDays());
				scheduleDtoForCalendar.setDoctor(schedule.getDoctor());
				scheduleDtoForCalendar.setFromTime(schedule.getFromTime().toString());
				scheduleDtoForCalendar.setToTime(schedule.getToTime().toString());
				if (schedule.getPresentDate() != null) {
					scheduleDtoForCalendar.setStartDate(schedule.getPresentDate().toString());
				}
				scheduleDtoForCalendar
						.setDoctorName(schedule.getDoctor().getFirstName() + " " + schedule.getDoctor().getLastName());
				scheduleDtoForCalendar.setScheduleStatus(schedule.getScheduleStatus());
				for (LocalDate startDate = stdate; startDate
						.isBefore(enddate.plusDays(1)); startDate = startDate.plusDays(1)) {
					if (startDate.getDayOfWeek().name().equals(schedule.getAvailableDays().get(0))) {
						scheduleDtoForCalendar.setScheduledDate(startDate.format(formatter));
					}

				}

				ScheduleDtosList.add(scheduleDtoForCalendar);
				
				
				
				if(ScheduleDtosList.equals(schedulesList)){
					
				}
				
				
			}
			///Nani writing
			//SearchScheduleDto searchScheduleDto2=new SearchScheduleDto();
			for(ScheduleDtoForCalendar searchSchedule:ScheduleDtosList){
				System.out.println(searchSchedule.getScheduledDate());
				System.out.println(searchSchedule.getDoctor().getFirstName());
				System.out.println(searchSchedule.getDoctor().getLastName());
				System.out.println(searchSchedule.getFromTime());
				System.out.println(searchSchedule.getToTime());
				System.out.println(searchSchedule.getAvailableDays());
				ScheduledHourDto scheduledHourDto=new ScheduledHourDto();
				scheduledHourDto.setScheduleDtoForCalendar(searchSchedule);
				scheduledHourDto.setStartTime(searchSchedule.getFromTime());
				scheduledHourDto.setEndtime(searchSchedule.getToTime());
				scheduledHourDto.setAppointmentStartedDate(searchSchedule.getScheduledDate());
				   List<String> scheduledTimings=	findScheduledHours(scheduledHourDto);
				   for(String oneHrSplittedcheduledTiming:scheduledTimings){
					   System.out.println(oneHrSplittedcheduledTiming);
					   if(oneHrSplittedcheduledTiming.equals(searchScheduleDto.getAccurateFromTime()+"-"+searchScheduleDto.getAccurateToTime())){
						   ScheduleDtosList1.add(searchSchedule);
					   }
				   }
			}
			///////////////
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat myFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			//String inputString1 =searchScheduleDto.getLocalDate();//             "23 01 1997";   2018-07-20
			//String inputString2 = "2018-08-17";//searchScheduleDto.getAccurateEndDate();//      "27 04 1997";   17-08-2018
			long noOfWeeks_Or_noOfAwaitingSubAppointments=0;
			try {
			  //  Date date1 = myFormat.parse(searchScheduleDto.getLocalDate());
			   // Date date2 = myFormat1.parse(searchScheduleDto.getAccurateEndDate());
				// long diff = date2.getTime() - date1.getTime();
			  //  long diff = myFormat1.parse(searchScheduleDto.getAccurateEndDate()).getTime() - myFormat.parse(searchScheduleDto.getLocalDate()).getTime();
			   // System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			    
			   // noOfWeeks_Or_noOfAwaitingSubAppointments=(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)/7)+1;
			    noOfWeeks_Or_noOfAwaitingSubAppointments=(TimeUnit.DAYS.convert(myFormat1.parse(searchScheduleDto.getAccurateEndDate()).getTime() - myFormat.parse(searchScheduleDto.getLocalDate()).getTime(), TimeUnit.MILLISECONDS)/7)+1;
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			
			/////
			List<LocalDate> appointementWeekDates = new ArrayList<LocalDate>();
			LocalDate date = LocalDate.parse(searchScheduleDto.getLocalDate());
			appointementWeekDates.add(date);
			for (int i = 1; i < noOfWeeks_Or_noOfAwaitingSubAppointments; i++) {
			   date = date.plusDays(7);
				appointementWeekDates.add(date);
			}
			//int occurances=LocalDate.parse(searchScheduleDto.getAccurateEndDate())-LocalDate.parse(searchScheduleDto.getLocalDate()) ;
			for(ScheduleDtoForCalendar scheduleDtoForCalendar:ScheduleDtosList1){
			System.out.println(ScheduleDtosList1.size());
			/*List<LocalDate> appointementWeekDates = new ArrayList<LocalDate>();
			LocalDate date = LocalDate.parse(searchScheduleDto.getLocalDate());
			appointementWeekDates.add(date);
			for (int i = 1; i < noOfWeeks_Or_noOfAwaitingSubAppointments; i++) {
			   date = date.plusDays(7);
				appointementWeekDates.add(date);
			}*/

			for (LocalDate appointedDate : appointementWeekDates) {
				SubAppointment subappointment = subAppointmentRepository
						.findByAppointmentStartTimeGreaterThanEqualAndAppointmentEndTimeLessThanEqualAndAppointMentStatusAndAppointmentStartedDateAndActiveAndDoctor_Id(
								 LocalTime.parse(searchScheduleDto.getAccurateFromTime()),
								LocalTime.parse(searchScheduleDto.getAccurateToTime()),
								AppointMentStatus.APPOINTEMENT, appointedDate, true,scheduleDtoForCalendar.getDoctor().getId());
				if (subappointment!=null) {
					//throw new RuntimeException("Sorry! Appointment already Scheduled! on " + appointedDate);
					//ScheduleDtosList1.remove(scheduleDtoForCalendar);
					ScheduleDtosList2.add(scheduleDtoForCalendar);
					System.out.println(ScheduleDtosList2.size());
					
				}
				
				
			}
			}
			ScheduleDtosList1.removeAll(ScheduleDtosList2);
			//////////////
		} else {
			throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
		}
		if(ScheduleDtosList1.size()<1){
			throw new RuntimeException(ErrorMessageHandler.noTherapistAvailableDuringTheseHours);
		}
		return ScheduleDtosList1;
	}
//For User mgmt
	@Override
	public List<ScheduleDtoForCalendar> searchSchedulesByDay(SearchScheduleDto searchScheduleDto) {
		if (searchScheduleDto.getLocalDate() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectStartDateAndTime);
		}
		if (searchScheduleDto.getFromTime() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectFromtime);
		}
		if (searchScheduleDto.getToTime() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectTotime);
		}
		if (searchScheduleDto.getEnddate() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectEndDate);
		}
		if (searchScheduleDto.getFromTime().equals(searchScheduleDto.getToTime())) {
			throw new RuntimeException(ErrorMessageHandler.endtimeShouldNotEqualToFromtime);
		}
		if (LocalTime.parse(searchScheduleDto.getFromTime()).isAfter(LocalTime.parse(searchScheduleDto.getToTime()))) {
			throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterthanFromtime);
		}
		List<Schedule> schedulesList = new ArrayList<Schedule>();
		List<Schedule> singleTherapistSchedulesList = new ArrayList<Schedule>();
		List<ScheduleDtoForCalendar> ScheduleDtosList = new ArrayList<ScheduleDtoForCalendar>();
		String[] values = searchScheduleDto.getLocalDate().split("T");
		String startDt = values[0];

		String[] values1 = searchScheduleDto.getEnddate().split("T");
		String endDt = values1[0];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate stdate = LocalDate.parse(startDt, formatter);
		LocalDate enddate = LocalDate.parse(endDt, formatter);
		System.out.println("stdate" + stdate);
		System.out.println("enddate" + enddate);
		UserAccount dbUserAccount = userAccountRepository.findByUsername(searchScheduleDto.getUsername());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}

		if (stdate.isBefore(LocalDate.now())) {
			throw new RuntimeException(ErrorMessageHandler.yourSelectedDateShouldBeGreaterthanPresentSystemDate);

		}
		/*
		 * if (stdate.equals(LocalDate.now())) { if
		 * (LocalTime.parse(searchScheduleDto.getFromTime()).isBefore(LocalTime.
		 * now())) { throw new RuntimeException(
		 * "Your Selected Time Should be Greater Than The Present System Time");
		 * } }
		 */
		if (enddate.isBefore(stdate)) {
			throw new RuntimeException(ErrorMessageHandler.startdateShouldBeLessthanEnddate);
		}
		if (dbUserAccount.getRole().getRoleName().equals("Individual")||dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			
			Doctor doctor = therapistRepository.findByEmail(dbUserAccount.getUsername());
			if (doctor == null) {
				throw new RuntimeException(ErrorMessageHandler.therapistDoesNotExists);
			}
			schedulesList=getSearchSchedules(stdate, enddate, doctor, searchScheduleDto);
			
		} else {// Admin and Others
			if (dbUserAccount.getCompany() != null) {
				System.out.println(searchScheduleDto.getDepartment().getId());
				List<Doctor> therapists = therapistRepository.findByCompany_IdAndDepartment_IdAndUserAccount_Active(dbUserAccount.getCompany().getId(),searchScheduleDto.getDepartment().getId(),true);
				for (Doctor doctor : therapists) {

					singleTherapistSchedulesList=getSearchSchedules(stdate, enddate, doctor, searchScheduleDto);
					schedulesList.addAll(singleTherapistSchedulesList);
				}

			}
		}
		if (schedulesList.size() > 0) {
			for (Schedule schedule : schedulesList) {
				ScheduleDtoForCalendar scheduleDtoForCalendar = new ScheduleDtoForCalendar();
				scheduleDtoForCalendar.setId(schedule.getId());
				scheduleDtoForCalendar.setAvailableDays(schedule.getAvailableDays());
				scheduleDtoForCalendar.setDoctor(schedule.getDoctor());
				scheduleDtoForCalendar.setFromTime(schedule.getFromTime().toString());
				scheduleDtoForCalendar.setToTime(schedule.getToTime().toString());
				if (schedule.getPresentDate() != null) {
					scheduleDtoForCalendar.setStartDate(schedule.getPresentDate().toString());
				}
				scheduleDtoForCalendar
						.setDoctorName(schedule.getDoctor().getFirstName() + " " + schedule.getDoctor().getLastName());
				scheduleDtoForCalendar.setScheduleStatus(schedule.getScheduleStatus());
				for (LocalDate startDate = stdate; startDate
						.isBefore(enddate.plusDays(1)); startDate = startDate.plusDays(1)) {
					if (startDate.getDayOfWeek().name().equals(schedule.getAvailableDays().get(0))) {
						scheduleDtoForCalendar.setScheduledDate(startDate.format(formatter));
					}

				}

				ScheduleDtosList.add(scheduleDtoForCalendar);
			}
		} else {
			throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
		}
		return ScheduleDtosList;
	}
	
	
	
	@Override
	public List<Schedule> getAllSchedulesByDoctorUsername(String doctorUsername) {
		Doctor doctor = therapistRepository.findByEmail(doctorUsername);
		List<Schedule> schedules = new ArrayList<Schedule>();
		if (doctor.getEmail() != null) {
			schedules = scheduleRepository.findByDoctor_Id(doctor.getId());
		} else {
			throw new RuntimeException(ErrorMessageHandler.doctorDoesNotExists);
		}
		return schedules;
	}

	@Override
	public List<String> findScheduledHours(ScheduledHourDto scheduledHourDto) {
		System.out.println(scheduledHourDto.getAppointmentStartedDate());
		if (scheduledHourDto.getStartTime() == null || scheduledHourDto.getEndtime() == null) {
			throw new RuntimeException(" "+ErrorMessageHandler.pleaseStarttimeAndEndtime);
		}
		Schedule schedule = scheduleRepository.findOne(scheduledHourDto.getScheduleDtoForCalendar().getId());
		if (schedule == null) {
			throw new RuntimeException(ErrorMessageHandler.scheduleDoesNotExists);
		}
		LocalTime stTime = LocalTime.parse(scheduledHourDto.getStartTime());
		LocalTime edTime = LocalTime.parse(scheduledHourDto.getEndtime());
		if (schedule.getFromTime().isAfter(edTime)) {
			throw new RuntimeException(ErrorMessageHandler.yourSelectedStarttimeShouldBeLessthanScheduledEndtime);
		}
		if (edTime.isAfter(schedule.getToTime())) {
			throw new RuntimeException(ErrorMessageHandler.yourSelectedEndtimeShouldBeLessthanScheduledEndtime);
		}
		if (stTime.isBefore(schedule.getFromTime())) {
			throw new RuntimeException(ErrorMessageHandler.yourSelectedStarttimeShouldBeGreaterthanScheduledStartime);
		}
		if (stTime.isAfter(edTime)) {
			throw new RuntimeException(ErrorMessageHandler.starttimeShouldbeLessthanTheEndtime);
		}
		if (stTime.equals(edTime)) {
			throw new RuntimeException(" " +ErrorMessageHandler.starttimeAndEndtimeShouldNotBeEqual);
		}
		String[] dateValues = scheduledHourDto.getAppointmentStartedDate().split("T");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(dateValues[0], formatter);
		System.out.println(startDate);
		List<String> scheduledTimings = getScheduledHours(schedule,
				scheduledHourDto.getScheduleDtoForCalendar().getDoctor(),startDate);
		return scheduledTimings;
	}
// convert 2pm-10pm  to (2,3,4,5,6,7,8,9)
	public List<String> findScheduledHoursFor(LocalTime fromTime, LocalTime toTime) {

		List<String> scheduledTimings = new ArrayList<String>();
		for (LocalTime startTime = fromTime; startTime.isBefore(toTime); startTime = startTime.plusHours(1)) {
			scheduledTimings.add(startTime+"-"+startTime.plusHours(1));
		}
		return scheduledTimings;
	}

	public List<Schedule> getCompletedSchedulesByHourywise(List<String> list, SubAppointment subAppointment,
			Doctor doctor, LocalDate startDate) {

		// sub appointment schedule 2pm-6pm i.e
		// 2-3,3-4,4-5,5-6 division of sub schedules
		List<Schedule> sheduleForHour = new ArrayList<Schedule>();
		List<Schedule> completedSchedules = new ArrayList<Schedule>();
		Schedule schedule2 = scheduleRepository
				.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndFromTimeLessThan(list,
						subAppointment.getDoctor().getId(), subAppointment.getAppointmentStartTime(),
						subAppointment.getAppointmentEndTime());
		if (schedule2 != null) {
			sheduleForHour.add(schedule2);
		}
		Schedule schedul3 = scheduleRepository
				.findByAvailableDaysInAndDoctor_IdAndToTimeGreaterThanAndToTimeLessThanEqual(list,
						subAppointment.getDoctor().getId(), subAppointment.getAppointmentStartTime(),
						subAppointment.getAppointmentEndTime());
		if (schedul3 != null) {
			sheduleForHour.add(schedul3);
		}
		Schedule schedul4 = scheduleRepository.findByAvailableDaysInAndDoctor_IdAndFromTimeLessThanAndToTimeGreaterThan(
				list, subAppointment.getDoctor().getId(), subAppointment.getAppointmentStartTime(),
				subAppointment.getAppointmentEndTime());
		if (schedul4 != null) {
			sheduleForHour.add(schedul4);
		}

		for (Schedule schedul : sheduleForHour) {
			if (schedul != null) {
				List<String> subAppointedTimingHours = new ArrayList<String>();
				List<String> matchedTimingHours = new ArrayList<String>();
				List<String> toTalSubScheduleTimes = findScheduledHoursFor(schedul.getFromTime(),
						schedul.getToTime());
				System.out.println("time" + toTalSubScheduleTimes);

				List<SubAppointment> subAppointMentsList = subAppointmentRepository.findSubAppointmentsByScheduledTimes(
						doctor.getId(), AppointMentStatus.APPOINTEMENT, startDate, true, schedul.getFromTime(),
						schedul.getToTime());

				// AndAndAppointmentEndTime
				for (SubAppointment subAppointment2 : subAppointMentsList) {
					if (subAppointment2 != null) {
						List<String> subScheduleTimes = findScheduledHoursFor(
								subAppointment2.getAppointmentStartTime(), subAppointment2.getAppointmentEndTime());
						subAppointedTimingHours.addAll(subScheduleTimes);
					}
				}
				if (subAppointedTimingHours.size() > 0 && toTalSubScheduleTimes.size() > 0) {
					for (String singleSubScheduleTime : toTalSubScheduleTimes) {
						if (subAppointedTimingHours.size() > 0) {
							for (String subAppointTime : subAppointedTimingHours) {
								if (singleSubScheduleTime.equals(subAppointTime)) {
									matchedTimingHours.add(subAppointTime);
								}
							}
						}
					}
				}
				if (toTalSubScheduleTimes.size() > 0) {
					toTalSubScheduleTimes.removeAll(matchedTimingHours);
				}
				if (toTalSubScheduleTimes.isEmpty()) {
					completedSchedules.add(sheduleForHour.get(0));
				}

			}
		}
		return completedSchedules;
	}
    //Remaining Sub Schedules From Complete Schedule i.e. 2pm-6pm(Complete Schedule) 3-4,4-5(Sub Appointments) Result=2-3,5-6(Remaining Sub Schedules)  
	public List<String> getScheduledHours(Schedule schedule, Doctor doctor, LocalDate startDate) {
		List<String> totalSubScheduleTimings = findScheduledHoursFor(schedule.getFromTime(), schedule.getToTime());
		List<String> matchedTimingHours = new ArrayList<String>();
		List<String> scheduledHours = new ArrayList<String>();
		List<SubAppointment> subAppointMentsList = subAppointmentRepository.findSubAppointmentsByScheduledTimes(
				doctor.getId(), AppointMentStatus.APPOINTEMENT, startDate, true, schedule.getFromTime(),
				schedule.getToTime());
		List<String> subAppointedTimingHours = new ArrayList<String>();
		if (subAppointMentsList.size() > 0) {
			for (SubAppointment subAppointment2 : subAppointMentsList) {
				if (subAppointment2 != null) {
					List<String> subScheduleTimes = findScheduledHoursFor(subAppointment2.getAppointmentStartTime(),
							subAppointment2.getAppointmentEndTime());
					subAppointedTimingHours.addAll(subScheduleTimes);
				}
			}
		}
		if (subAppointedTimingHours.size() > 0 && totalSubScheduleTimings.size() > 0) {
			for (String singleSubScheduleTime : totalSubScheduleTimings) {
				if (subAppointedTimingHours.size() > 0) {
					for (String subAppointTime : subAppointedTimingHours) {
						if (singleSubScheduleTime.equals(subAppointTime)) {
							matchedTimingHours.add(subAppointTime);
						}
					}
				}
			}
		}
		if (totalSubScheduleTimings.size() > 0) {
			totalSubScheduleTimings.removeAll(matchedTimingHours);
		}
		if (totalSubScheduleTimings.size() > 0) {
			for (String scheduletime : totalSubScheduleTimings) {
				scheduledHours.add(scheduletime.toString());
			}
		}
		return scheduledHours;
	}
	public List<ScheduleDtoForCalendar> getDailySchedules(LocalDate presentDate, Doctor doctor, List<String> list,DateTimeFormatter formatter) {

		List<ScheduleDtoForCalendar> ListOfschedulesDtos = new ArrayList<ScheduleDtoForCalendar>();
		List<Schedule> schedulesList = scheduleRepository.findByAvailableDaysInAndDoctor_Id(list,
				doctor.getId());
		List<Holiday> holidays = holidaysRepository.findByDoctor_IdAndFromDate(doctor.getId(), presentDate);
		List<Schedule> holidaysSchedules = new ArrayList<Schedule>();
		for (Holiday holiday : holidays) {
			List<Schedule> holidaySchedules = scheduleRepository
					.findByDoctor_IdAndAvailableDaysInAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
							doctor.getId(), list, holiday.getFromLocalTime(), holiday.getToLocalTime());
			holidaysSchedules.addAll(holidaySchedules);
		}
		List<SubAppointment> subAppointmentsList = subAppointmentRepository
				.findByDoctor_IdAndAppointmentStartedDateAndActive(doctor.getId(), presentDate, true);
		for (SubAppointment subAppointment : subAppointmentsList) {
			Schedule schedule = scheduleRepository
					.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
							list, doctor.getId(), subAppointment.getAppointmentStartTime(),
							subAppointment.getAppointmentEndTime());
			// appointed schedules and holidayschedules are removed
			// from total schedules
			holidaysSchedules.add(schedule);

			List<Schedule> completedScheduleHour = getCompletedSchedulesByHourywise(list, subAppointment,
					doctor, presentDate);
			holidaysSchedules.addAll(completedScheduleHour);

		}

		List<Schedule> matchedschedules = new ArrayList<Schedule>();
		for (Schedule schedule1 : schedulesList) {
			for (Schedule hschedule : holidaysSchedules) {
				if (schedule1 != null && hschedule != null) {
					if (schedule1.getId().equals(hschedule.getId())) {
						matchedschedules.add(schedule1);
					}
				}

			}
		}
		schedulesList.removeAll(matchedschedules);
		for (Schedule schedule : schedulesList) {
			ScheduleDtoForCalendar scheduleDtoForCalendar = new ScheduleDtoForCalendar();
			scheduleDtoForCalendar.setDoctorName(schedule.getDoctor().getFirstName());
			scheduleDtoForCalendar.setAvailableDays(schedule.getAvailableDays());
			scheduleDtoForCalendar.setColor(schedule.getColor());
			scheduleDtoForCalendar.setFromTime((schedule.getFromTime()).toString());
			scheduleDtoForCalendar.setToTime(schedule.getToTime().toString());
			scheduleDtoForCalendar.setStartDate(
					presentDate.format(formatter) + "T" + (schedule.getFromTime()).toString() + ":00");
			scheduleDtoForCalendar.setEndDate(
					presentDate.format(formatter) + "T" + (schedule.getToTime()).toString() + ":00");
			scheduleDtoForCalendar.setDoctor(schedule.getDoctor());
			scheduleDtoForCalendar.setScheduleStatus(schedule.getScheduleStatus());
			ListOfschedulesDtos.add(scheduleDtoForCalendar);

		}
		return ListOfschedulesDtos;
	
	}
	
	public Map<String, List<ScheduleDtoForCalendar>> getWeeklySchedules(LocalDate startDate, Doctor doctor,
			List<String> list, DateTimeFormatter formatter) {

		Map<String, List<ScheduleDtoForCalendar>> schedulesMap = new HashMap<String, List<ScheduleDtoForCalendar>>();
		List<ScheduleDtoForCalendar> sundaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> mondaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> tuesdaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> wednesdaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> thursdaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> fridaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<ScheduleDtoForCalendar> saturedaySchedules = new ArrayList<ScheduleDtoForCalendar>();
		List<Schedule> schedulesList = scheduleRepository.findByAvailableDaysInAndDoctor_Id(list, doctor.getId());
		List<Holiday> holidays = holidaysRepository.findByDoctor_IdAndFromDate(doctor.getId(), startDate);
		List<Schedule> holidaysSchedules = new ArrayList<Schedule>();
		for (Holiday holiday : holidays) {
			List<Schedule> holidaySchedules = scheduleRepository
					.findByDoctor_IdAndAvailableDaysInAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(doctor.getId(),
							list, holiday.getFromLocalTime(), holiday.getToLocalTime());
			holidaysSchedules.addAll(holidaySchedules);
		}
		List<SubAppointment> subAppointmentsList = subAppointmentRepository
				.findByDoctor_IdAndAppointmentStartedDateAndActive(doctor.getId(), startDate, true);
		for (SubAppointment subAppointment : subAppointmentsList) {
			Schedule schedule = scheduleRepository
					.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(list,
							doctor.getId(), subAppointment.getAppointmentStartTime(),
							subAppointment.getAppointmentEndTime());
			// appointed schedules and holidayschedules are
			// removed from total schedules
			holidaysSchedules.add(schedule);

			// code starts
			List<Schedule> completedSchedules = getCompletedSchedulesByHourywise(list, subAppointment, doctor,
					startDate);

			// End
			holidaysSchedules.addAll(completedSchedules);
		}
		List<Schedule> matchedschedules = new ArrayList<Schedule>();
		for (Schedule schedule1 : schedulesList) {
			for (Schedule hschedule : holidaysSchedules) {
				if (schedule1 != null && hschedule != null) {
					if (schedule1.getId().equals(hschedule.getId())) {
						matchedschedules.add(schedule1);
					}
				}

			}
		}
		schedulesList.removeAll(matchedschedules);
		for (Schedule schedule : schedulesList) {
			ScheduleDtoForCalendar scheduleDtoForCalendar = convertScheduleToScheduleDtoForCalender(schedule, startDate,
					formatter);
			if (startDate.getDayOfWeek().name().equalsIgnoreCase("SUNDAY")) {
				sundaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(startDate.getDayOfWeek().name(), sundaySchedules);
			}
			if (startDate.getDayOfWeek().name().equalsIgnoreCase("MONDAY")) {
				mondaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(startDate.getDayOfWeek().name(), mondaySchedules);
			}
			if (startDate.getDayOfWeek().name().equalsIgnoreCase("TUESDAY")) {
				tuesdaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(startDate.getDayOfWeek().name(), tuesdaySchedules);
			}
			if (startDate.getDayOfWeek().name().equalsIgnoreCase("WEDNESDAY")) {
				wednesdaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(startDate.getDayOfWeek().name(), wednesdaySchedules);
			}
			if (startDate.getDayOfWeek().name().equalsIgnoreCase("THURSDAY")) {
				thursdaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(startDate.getDayOfWeek().name(), thursdaySchedules);
			}
			if (startDate.getDayOfWeek().name().equalsIgnoreCase("FRIDAY")) {
				fridaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(startDate.getDayOfWeek().name(), fridaySchedules);
			}
			if (startDate.getDayOfWeek().name().equalsIgnoreCase("SATURDAY")) {
				saturedaySchedules.add(scheduleDtoForCalendar);
				schedulesMap.put(startDate.getDayOfWeek().name(), saturedaySchedules);
			}

		}
		return schedulesMap;

	}
	public ScheduleDtoForCalendar convertScheduleToScheduleDtoForCalender(Schedule schedule, LocalDate presentDate,
			DateTimeFormatter formatter) {
		ScheduleDtoForCalendar scheduleDtoForCalendar = new ScheduleDtoForCalendar();
		scheduleDtoForCalendar.setId(schedule.getId());
		scheduleDtoForCalendar.setDoctorName(schedule.getDoctor().getFirstName());
		scheduleDtoForCalendar.setAvailableDays(schedule.getAvailableDays());
		scheduleDtoForCalendar.setColor(schedule.getColor());
		scheduleDtoForCalendar.setFromTime((schedule.getFromTime()).toString());
		scheduleDtoForCalendar.setToTime(schedule.getToTime().toString());
		scheduleDtoForCalendar
				.setStartDate(presentDate.format(formatter) + "T" + (schedule.getFromTime()).toString() + ":00");
		scheduleDtoForCalendar
				.setEndDate(presentDate.format(formatter) + "T" + (schedule.getToTime()).toString() + ":00");
		scheduleDtoForCalendar.setDoctor(schedule.getDoctor());
		scheduleDtoForCalendar.setScheduleStatus(schedule.getScheduleStatus());
		return scheduleDtoForCalendar;
	}
	//For Check Availability
	public List<Schedule> getSearchSchedules(LocalDate stdate,LocalDate enddate, Doctor doctor,SearchScheduleDto searchScheduleDto){
		List<Schedule> schedulesList = new ArrayList<Schedule>();
		Set<String> appointmentdays = new HashSet<String>();
		List<SubAppointment> subappointments = new ArrayList<SubAppointment>();

		// iterating Active therapists
		
		for (LocalDate startDate = stdate; startDate
				.isBefore(enddate.plusDays(1)); startDate = startDate.plusDays(1)) {

			appointmentdays.add(startDate.getDayOfWeek().name());
			// Checking How many (Sub)Appointments are Exists
			// Between Given Dates by Days(In Schedule)
			List<SubAppointment> scheduledSubappointments = subAppointmentRepository
					.findByDoctor_IdAndAppointmentStartedDateAndAppointmentEndedDateAndAppointMentStatusAndActive(
							doctor.getId(), startDate, startDate, AppointMentStatus.APPOINTEMENT, true);
			subappointments.addAll(scheduledSubappointments);// subappointments=1
																// Bec,28-12-2017
																// Appointment
																// Exist
			// Search From 27-12-2017 to 29-12-2017
		}
		List<Holiday> holidayss = holidaysRepository
				.findByDoctor_IdAndFromDateGreaterThanEqualAndToDateLessThanEqual(doctor.getId(), stdate,
						enddate.plusDays(1));
		List<Schedule> holidayScheduleList = new ArrayList<Schedule>();
		/*
		 * if (holidayss.size() > 0) { for(Holiday
		 * holiday:holidayss){ List<String> list = new
		 * ArrayList<String>();
		 * list.add(holiday.getFromDate().getDayOfWeek().name());
		 * List<Schedule> holidaySchedules = scheduleRepository
		 * .findByDoctor_IdAndAvailableDaysInAndFromTimeGreaterThanEqualAndToTimeLessThanEqual
		 * (doctor.getId(),list, holiday.getFromLocalTime(),
		 * holiday.getToLocalTime()); for (Schedule schedule :
		 * holidaySchedules) {
		 * schedule.setScheduleStatus(ScheduleStatus.HOLIDAY);
		 * scheduleRepository.save(schedule);
		 * holidayScheduleList.add(schedule); } } }
		 */
		////////////////
		if (holidayss.size() > 0) {
			for (Holiday holiday : holidayss) {
				List<Schedule> scheduleList = scheduleRepository.findByDoctor_Id(doctor.getId());
				for (Schedule sc : scheduleList) {

					for (String dbday : sc.getAvailableDays()) {

						if (dbday.equals(holiday.getFromDate().getDayOfWeek().name())) {

							if (sc.getFromTime().equals(holiday.getFromLocalTime())) {
								sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
								scheduleRepository.save(sc);
								holidayScheduleList.add(sc);
							}
							if (sc.getToTime().equals(holiday.getToLocalTime())) {
								sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
								scheduleRepository.save(sc);
								holidayScheduleList.add(sc);
							}

							if (holiday.getFromLocalTime().isAfter(sc.getFromTime())
									&& holiday.getFromLocalTime().isBefore(sc.getToTime())) {
								sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
								scheduleRepository.save(sc);
								holidayScheduleList.add(sc);
							}
							if (holiday.getFromLocalTime().isBefore(sc.getFromTime())
									&& holiday.getToLocalTime().isAfter(sc.getToTime())) {
								sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
								scheduleRepository.save(sc);
								holidayScheduleList.add(sc);
							}
							if (holiday.getToLocalTime().isAfter(sc.getFromTime())
									&& holiday.getToLocalTime().isBefore(sc.getToTime())) {
								sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
								scheduleRepository.save(sc);
								holidayScheduleList.add(sc);
							}

						}
					}

				}
			}
		}
		//////////////////

		List<Schedule> scheduleList = new ArrayList<Schedule>();
		if (subappointments.size() < 1) { // If Appointment Doesn't Exist?
			for (String apDay : appointmentdays) {
				List<String> list = new ArrayList<String>();
				list.add(apDay);
				//Her it is imp..bec, here finded schedules only we are getting in search schedule page(For Single doctor)
				//06062020 718
				/*
				 * List<Schedule> schedules = scheduleRepository
				 * .findByDoctor_IdAndAvailableDaysInAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
				 * doctor.getId(), list, LocalTime.parse(searchScheduleDto.getFromTime()),
				 * LocalTime.parse(searchScheduleDto.getToTime()));
				 */
				List<Schedule> schedules = scheduleRepository
						.findByDoctor_IdAndAvailableDaysInAndFromTimeLessThanEqualAndToTimeGreaterThanEqual(
								doctor.getId(), list, LocalTime.parse(searchScheduleDto.getFromTime()),
								LocalTime.parse(searchScheduleDto.getToTime()));
				scheduleList.addAll(schedules);
				list.remove(apDay);
			}
			List<Schedule> matchedSchedules = new ArrayList<Schedule>();
			for (Schedule schedule : scheduleList) {
				if (holidayScheduleList.size() > 0) {
					schedule.setScheduleStatus(ScheduleStatus.AVAILABILITY);
					scheduleRepository.save(schedule);
					schedulesList.add(schedule);
					if (holidayScheduleList.size() > 0) {
						for (Schedule holidayschedule1 : holidayScheduleList) {
							if (schedule.getId().equals(holidayschedule1.getId())) {
								matchedSchedules.add(schedule);
								schedulesList.remove(schedule);
							}
						}
					}
				} else {
					schedule.setScheduleStatus(ScheduleStatus.AVAILABILITY);
					scheduleRepository.save(schedule);
					schedulesList.add(schedule);
				}
			}
		} else {// If Appointment  Exist?
				
			List<Schedule> schedules = new ArrayList<Schedule>();
			List<String> listOfDays = new ArrayList<String>();
			for (SubAppointment subAppointment : subappointments) {
				for (LocalDate startDate = stdate; startDate
						.isBefore(enddate.plusDays(1)); startDate = startDate.plusDays(1)) {
					if (subAppointment.getAppointmentStartedDate().equals(startDate)
							&& subAppointment.isActive() == true) {
						List<String> list = new ArrayList<String>();
						list.add(subAppointment.getAppointmentStartedDate().getDayOfWeek().name());
						listOfDays.add(subAppointment.getAppointmentStartedDate().getDayOfWeek().name());
						Schedule schedule1 = scheduleRepository
								.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
										list, subAppointment.getDoctor().getId(),
										subAppointment.getAppointmentStartTime(),
										subAppointment.getAppointmentEndTime());
						if (schedule1 != null) {
							schedules.add(schedule1);
						}

						// sub appointment schedule 2pm-6pm i.e
						// 2-3,3-4,4-5,5-6 division of sub schedules
						List<Schedule> sheduleForHour = new ArrayList<Schedule>();
						Schedule schedule2 = scheduleRepository
								.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndFromTimeLessThan(
										list, subAppointment.getDoctor().getId(),
										subAppointment.getAppointmentStartTime(),
										subAppointment.getAppointmentEndTime());
						if (schedule2 != null) {
							sheduleForHour.add(schedule2);
						}
						Schedule schedul3 = scheduleRepository
								.findByAvailableDaysInAndDoctor_IdAndToTimeGreaterThanAndToTimeLessThanEqual(
										list, subAppointment.getDoctor().getId(),
										subAppointment.getAppointmentStartTime(),
										subAppointment.getAppointmentEndTime());
						if (schedul3 != null) {
							sheduleForHour.add(schedul3);
						}
						Schedule schedul4 = scheduleRepository
								.findByAvailableDaysInAndDoctor_IdAndFromTimeLessThanAndToTimeGreaterThan(
										list, subAppointment.getDoctor().getId(),
										subAppointment.getAppointmentStartTime(),
										subAppointment.getAppointmentEndTime());
						if (schedul4 != null) {
							sheduleForHour.add(schedul4);
						}

						for (Schedule schedul : sheduleForHour) {
							if (schedul != null) {
								List<String> subAppointedTimingHours = new ArrayList<String>();
								List<String> matchedTimingHours = new ArrayList<String>();
								List<String> toTalSubScheduleTimes = findScheduledHoursFor(
										schedul.getFromTime(), schedul.getToTime());
								System.out.println("time" + toTalSubScheduleTimes);

								List<SubAppointment> subAppointMentsList = subAppointmentRepository
										.findSubAppointmentsByScheduledTimes(doctor.getId(),
												AppointMentStatus.APPOINTEMENT, startDate, true,
												schedul.getFromTime(), schedul.getToTime());

								// AndAndAppointmentEndTime
								for (SubAppointment subAppointment2 : subAppointMentsList) {
									if (subAppointment2 != null) {
										List<String> subScheduleTimes = findScheduledHoursFor(
												subAppointment2.getAppointmentStartTime(),
												subAppointment2.getAppointmentEndTime());
										subAppointedTimingHours.addAll(subScheduleTimes);
									}
								}
								if (subAppointedTimingHours.size() > 0
										&& toTalSubScheduleTimes.size() > 0) {
									for (String singleSubScheduleTime : toTalSubScheduleTimes) {
										if (subAppointedTimingHours.size() > 0) {
											for (String subAppointTime : subAppointedTimingHours) {
												if (singleSubScheduleTime.equals(subAppointTime)) {
													matchedTimingHours.add(subAppointTime);
												}
											}
										}
									}
								}
								if (toTalSubScheduleTimes.size() > 0) {
									toTalSubScheduleTimes.removeAll(matchedTimingHours);
								}
								if (toTalSubScheduleTimes.isEmpty()) {
									schedules.add(sheduleForHour.get(0));
								}

							}
						}

						if (schedules.size() > 0) {
							for (Schedule schedule : schedules) {
								if (schedule != null) {
									schedule.setScheduleStatus(ScheduleStatus.APPOINTED);
									scheduleRepository.save(schedule);
								}
							}
						}
						//

						list.remove(subAppointment.getAppointmentStartedDate().getDayOfWeek().name());
					}

				}
			}
			List<Schedule> availableScheduleList = new ArrayList<Schedule>();
			for (String day : appointmentdays) {
				List<String> list = new ArrayList<String>();
				list.add(day);
				List<Schedule> totalSchedules = scheduleRepository
						.findByDoctor_IdAndAvailableDaysInAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
								doctor.getId(), list, LocalTime.parse(searchScheduleDto.getFromTime()),
								LocalTime.parse(searchScheduleDto.getToTime()));
				availableScheduleList.addAll(totalSchedules);

			}

			List<Schedule> list1 = new ArrayList<Schedule>();
			if (schedules.size() > 0) {
				for (Schedule appointedSchedule : schedules) {
					if (availableScheduleList.size() > 0) {
						for (Schedule availableschedule : availableScheduleList) {
							if (appointedSchedule != null && availableschedule != null) {
								if (appointedSchedule.getId().equals(availableschedule.getId())) {
									list1.add(availableschedule);
								}
							}
						}
					}
				}
			}
			if (availableScheduleList.size() > 0) {
				availableScheduleList.removeAll(list1);
			}
			List<Schedule> holidayScs = new ArrayList<Schedule>();
			if (availableScheduleList.size() > 0) {
				for (Schedule availableSchedule : availableScheduleList) {
					if (holidayScheduleList.size() > 0) {
						for (Schedule schedule1 : holidayScheduleList) {
							if (schedule1 != null && availableSchedule != null) {
								if (availableSchedule.getId().equals(schedule1.getId())) {
									holidayScs.add(availableSchedule);
								}
							}
						}
					}
				}
			}
			if (availableScheduleList.size() > 0) {
				availableScheduleList.removeAll(holidayScs);
			}

			schedulesList.addAll(availableScheduleList);
		}
		subappointments.removeAll(subappointments);
		holidayScheduleList.removeAll(holidayScheduleList);
	
		return schedulesList;
	}
}
