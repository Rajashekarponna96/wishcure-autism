package com.openspace.HospitalMgnt.Therapist.Holidays;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Holiday;
import com.openspace.Model.DoctorManagement.Schedule;
import com.openspace.Model.DoctorManagement.ScheduleStatus;
import com.openspace.Model.PatientMgnt.Repositories.HolidaysRepository;
import com.openspace.Model.PatientMgnt.Repositories.ScheduleRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class HolidaysServiceImpl implements HolidaysService {
	@Autowired
	private HolidaysRepository holidaysRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private TherapistRepository therapistRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public List<Holiday> getAllHolidays() {
		// TODO Auto-generated method stub
		return (List<Holiday>) holidaysRepository.findAll();
	}

	@Override
	public void updateHolidays(Holiday holiday) {
		// TODO Auto-generated method stub
		holidaysRepository.save(holiday);

	}

	@Override
	public void deleteHolidays(Long id) {
		Holiday dbHoliday = holidaysRepository.findOne(id);
		if (dbHoliday == null) {
			throw new RuntimeException(ErrorMessageHandler.holidayDoesNotExists);
		}
		/*
		 * List<String> availableDays = new ArrayList<String>();
		 * availableDays.add(dbHoliday.getFromDate().getDayOfWeek().name());
		 * List<Schedule> schedules = scheduleRepository
		 * .findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqualAndScheduleStatus(
		 * availableDays, dbHoliday.getDoctor().getId(),
		 * dbHoliday.getFromLocalTime(), dbHoliday.getToLocalTime(),
		 * ScheduleStatus.HOLIDAY); if (schedules.size() > 0) { for (Schedule
		 * schedule : schedules) {
		 * schedule.setScheduleStatus(ScheduleStatus.AVAILABILITY);
		 * scheduleRepository.save(schedule); } }
		 */
		List<Schedule> scheduleList = scheduleRepository.findByDoctor_Id(dbHoliday.getDoctor().getId());
		for (Schedule sc : scheduleList) {

			for (String dbday : sc.getAvailableDays()) {

				if (dbday.equals(dbHoliday.getFromDate().getDayOfWeek().name())) {

					if (sc.getFromTime().equals(dbHoliday.getFromLocalTime())) {
						sc.setScheduleStatus(ScheduleStatus.AVAILABILITY);
						scheduleRepository.save(sc);
					}
					if (sc.getToTime().equals(dbHoliday.getToLocalTime())) {
						sc.setScheduleStatus(ScheduleStatus.AVAILABILITY);
						scheduleRepository.save(sc);
					}

					if (dbHoliday.getFromLocalTime().isAfter(sc.getFromTime())
							&& dbHoliday.getFromLocalTime().isBefore(sc.getToTime())) {
						sc.setScheduleStatus(ScheduleStatus.AVAILABILITY);
						scheduleRepository.save(sc);
					}
					if (dbHoliday.getFromLocalTime().isBefore(sc.getFromTime())
							&& dbHoliday.getToLocalTime().isAfter(sc.getToTime())) {
						sc.setScheduleStatus(ScheduleStatus.AVAILABILITY);
						scheduleRepository.save(sc);
					}
					if (dbHoliday.getToLocalTime().isAfter(sc.getFromTime())
							&& dbHoliday.getToLocalTime().isBefore(sc.getToTime())) {
						sc.setScheduleStatus(ScheduleStatus.AVAILABILITY);
						scheduleRepository.save(sc);
					}

				}
			}
		}
		holidaysRepository.delete(id);

	}

	@Override
	public void saveHolidaysService(HolidayDto holidayDto) {
		// TODO Auto-generated method stub
		String color = "red";
		Doctor doctor = therapistRepository.findByEmail(holidayDto.getDoctorUsername());

		if (doctor == null) {
			throw new RuntimeException(ErrorMessageHandler.doctorDoesNotExists);
		}
		LocalDate startDate = holidayDto.convertToLocaldate(holidayDto.getFromTime());
		LocalDate endDate = holidayDto.convertToLocaldate(holidayDto.getToTime());
		LocalTime startTime = holidayDto.convertToLocalTime(holidayDto.getFromTime());
		LocalTime endTime = holidayDto.convertToLocalTime(holidayDto.getToTime());
		List<Holiday> holidayList = holidaysRepository.findByDoctor_Id(doctor.getId());

		/*for (Holiday holiday : holidayList) {
			if (holiday.getFromDate().equals(startDate)) {
				throw new RuntimeException("Holiday Already Existed! At This Date");
			}
			if (holiday.getToDate().equals(endDate)) {
				throw new RuntimeException("Holiday Already Existed! At This Date");
			}

			
			 * if (sc.getFromTime().equals(holidayDto.getFromTime())) { throw
			 * new RuntimeException("At this Time holiday Already Existed!1"); }
			 * if (sc.getToTime().equals(holidayDto.getToTime())) { throw new
			 * RuntimeException("At this Time holiday Already Exist!2"); }
			 
			if (startDate.isAfter(holiday.getFromDate()) && startDate.isBefore(holiday.getToDate())) {
				// checkes whether the current time is between 14:49:00 and
				// 20:11:13.
				throw new RuntimeException("At this Time holiday Already Existed!3");
			}
			if (endDate.isAfter(holiday.getFromDate()) && endDate.isBefore(holiday.getToDate())) {
				// checkes whether the current time is between 14:49:00 and
				// 20:11:13.
				throw new RuntimeException("At this Time holiday Already Existed!3");
			}

		}*/
		

		if (holidayDto.getFromTime() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectFromtime);
		}
		if (holidayDto.getToTime() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectTotime);
		}
		if (holidayDto.getReason() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseSelectTitle);
		}
		if (holidayDto.getFromTime().equals(holidayDto.getToTime())) {
			throw new RuntimeException(ErrorMessageHandler.endtimeAndStartTimeShouldNotBeEqual);
		}

		if (holidayDto.getFromTime().after(holidayDto.getToTime())) {
			throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterThanStarttime);
		}
		/*if (holidayDto.getFromTime().before(new Timestamp(System.currentTimeMillis()))) {
			throw new RuntimeException("Please Choose above Current Date And Time ");

		}*/
         List<Holiday> holidaysList=new ArrayList<Holiday>();
		for (LocalDate stDate = startDate; stDate.isBefore(endDate.plusDays(1)); stDate = stDate.plusDays(1)) {
			Holiday holiday = new Holiday();
			for (Holiday holidayValidation : holidayList) {
				if (holidayValidation.getFromDate().equals(stDate)) {
					if(holidayValidation.getFromLocalTime().equals(startTime))
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
				if (holidayValidation.getFromDate().equals(stDate)) {
					if(holidayValidation.getToLocalTime().equals(endTime))
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
				if (holidayValidation.getFromDate().equals(stDate)) {
					if(startTime.isAfter(holidayValidation.getFromLocalTime())&&startTime.isBefore(holidayValidation.getToLocalTime()))
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
				if (holidayValidation.getFromDate().equals(stDate)) {
					if(startTime.isBefore(holidayValidation.getFromLocalTime())&&endTime.isAfter(holidayValidation.getToLocalTime()))
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
				if (holidayValidation.getFromDate().equals(stDate)) {
					if(endTime.isAfter(holidayValidation.getFromLocalTime())&&endTime.isBefore(holidayValidation.getToLocalTime()))
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			
				
			}
			//holiday.setDoctor(doctor);
			if (startDate.isBefore(LocalDate.now())) {
				throw new RuntimeException(ErrorMessageHandler.yourSelectedDateShouldBeGreaterthanPresentSystemDate);
			}
			if (startDate.equals(stDate)) {
				holiday.setFromDate(stDate);
				holiday.setToDate(stDate);
				holiday.setFromLocalTime(startTime);
				holiday.setToLocalTime(LocalTime.parse("23:30:00"));
			}
			if (endDate.equals(stDate) && !startDate.equals(stDate)) {
				holiday.setFromDate(stDate);
				holiday.setToDate(stDate);
				holiday.setFromLocalTime(LocalTime.parse("00:00:00"));
				// holiday.setFromLocalTime(startTime);
				holiday.setToLocalTime(endTime);
			}
			if (startDate.equals(stDate) && endDate.equals(stDate)) {
				holiday.setFromDate(stDate);
				holiday.setToDate(stDate);
				// holiday.setFromLocalTime(LocalTime.parse("00:00:00"));
				holiday.setFromLocalTime(startTime);
				holiday.setToLocalTime(endTime);
			}
			if (!startDate.equals(stDate) && !endDate.equals(stDate)) {
				holiday.setFromDate(stDate);
				holiday.setToDate(stDate);
				holiday.setFromLocalTime(LocalTime.parse("00:00:00"));
				holiday.setToLocalTime(LocalTime.parse("23:30:00"));
			}
			holiday.setReason(holidayDto.getReason());
			holiday.setColor(color);
			holiday.setDoctor(doctor);
			holiday.setActive(true);
			holidaysList.add(holiday);

			/*
			 * String day = holiday.getFromDate().getDayOfWeek().name();
			 * List<String> list = new ArrayList<String>(); list.add(day);
			 * List<Schedule> schedules = scheduleRepository.
			 * findByDoctor_IdAndAvailableDaysInAndFromTimeGreaterThanEqualAndToTimeLessThanEqual
			 * (doctor.getId(),list, holiday.getFromLocalTime(),
			 * holiday.getToLocalTime()); if (schedules != null) { for (Schedule
			 * schedule : schedules) {
			 * schedule.setScheduleStatus(ScheduleStatus.HOLIDAY); //
			 * schedules.add(schedule); scheduleRepository.save(schedule);
			 * 
			 * } }
			 */
			/*
			 * List<Schedule> scheduleList =
			 * scheduleRepository.findByDoctor_Id(doctor.getId());
			 * scheduleList) {
			 * 
			 * for (String dbday : sc.getAvailableDays()) {
			 * 
			 * if (dbday.equals(holiday.getFromDate().getDayOfWeek().name())) {
			 * 
			 * if (sc.getFromTime().equals(holiday.getFromLocalTime())) {
			 * sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
			 * scheduleRepository.save(sc); } if
			 * (sc.getToTime().equals(holiday.getToLocalTime())) {
			 * sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
			 * scheduleRepository.save(sc); }
			 * 
			 * if (holiday.getFromLocalTime().isAfter(sc.getFromTime()) &&
			 * holiday.getFromLocalTime().isBefore(sc.getToTime())) {
			 * sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
			 * scheduleRepository.save(sc); } if
			 * (holiday.getFromLocalTime().isBefore(sc.getFromTime()) &&
			 * holiday.getToLocalTime().isAfter(sc.getToTime())) {
			 * sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
			 * scheduleRepository.save(sc); } if
			 * (holiday.getToLocalTime().isAfter(sc.getFromTime()) &&
			 * holiday.getToLocalTime().isBefore(sc.getToTime())) {
			 * sc.setScheduleStatus(ScheduleStatus.HOLIDAY);
			 * scheduleRepository.save(sc); }
			 * 
			 * } } }
			 */
		}
		holidaysRepository.save(holidaysList);
	}

	@Override
	public Page<Holiday> getAllHolidaysByDoctorUsername(String doctorUsername, int page, int size) {
		// TODO Auto-generated method stub
		Doctor doctor = therapistRepository.findByEmail(doctorUsername);
		Page<Holiday> holidays = null;
		LocalDate date = LocalDate.now();
		StringBuilder sb = new StringBuilder();
		sb.append(date);
		sb.append(" 00:00:00");
		if (doctor.getEmail() != null) {
			holidays = holidaysRepository.findByDoctor_Id(doctor.getId(),
					new PageRequest(page, size, Sort.Direction.DESC, "id"));
		} else {
			throw new RuntimeException(ErrorMessageHandler.doctorDoesNotExists);
		}
		return holidays;
	}

	@Override
	public List<Holiday> getAllTherapistHolidaysByAdminUsername(String adminUsername) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		List<Doctor> therapists = new ArrayList<Doctor>();
		List<Holiday> holidaysList = new ArrayList<Holiday>();
		if (dbUserAccount.getRole().getRoleName().equals("Admin")) {
			therapists = therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			List<Holiday> holidays = new ArrayList<Holiday>();
			for (Doctor doctor : therapists) {
				holidays = holidaysRepository.findByDoctor_Id(doctor.getId());
				holidaysList.addAll(holidays);
			}

		}

		return holidaysList;

	}

	/*
	 * @Override public Page<Holiday>
	 * getAllTherapistHolidaysByAdminUsername(String adminUsername, int page,
	 * int size) { UserAccount
	 * dbUserAccount=userAccountRepository.findByUsername(adminUsername);
	 * if(dbUserAccount==null){ throw new RuntimeException("User Doesnot  Exist"
	 * ); } List<Doctor> therapists = new ArrayList<Doctor>(); List<Holiday>
	 * schedulsList = new ArrayList<Holiday>(); if
	 * (dbUserAccount.getRole().getRoleName().equals("Admin")) { therapists =
	 * therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
	 * List<Holiday> scheduls = new ArrayList<Holiday>(); for (Doctor doctor :
	 * therapists) { scheduls =
	 * holidaysRepository.findByDoctor_Id(doctor.getId(), new PageRequest(page,
	 * size)); schedulsList.addAll(scheduls); }
	 * 
	 * } Doctor doctor=null; return (Page<Holiday>)
	 * holidaysRepository.findByDoctor_Id(doctor.getId(), new PageRequest(page,
	 * size));
	 * 
	 * }
	 */
	@Override
	public Page<Holiday> getAllTherapistHolidaysByAdminUsername(String adminUsername, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		List<Doctor> therapists = new ArrayList<Doctor>();
		if (dbUserAccount.getRole().getRoleName().equals("Admin")) {
			therapists = therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			Page<Holiday> holidays = null;
			for (Doctor doctor : therapists) {
				holidays = holidaysRepository.findByDoctor_Id(doctor.getId(),
						new PageRequest(page, size, Sort.Direction.DESC, "id"));
			}

		}
		Doctor doctor = null;
		return (Page<Holiday>) holidaysRepository.findByDoctor_Id(doctor.getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));

	}

	@Override
	public List<Holiday> getAllHolidaysByRole(String adminUsername) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUsername);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		/*
		 * LocalDate date = LocalDate.now(); StringBuilder sb=new
		 * StringBuilder(); sb.append(date); sb.append(" 00:00:00");
		 * "Timestamp ___________________"+Timestamp.valueOf(sb.toString()));
		 */
		List<Holiday> holidaysList = new ArrayList<Holiday>();
		if (dbUserAccount.getRole().getRoleName().equals("Admin")
				|| dbUserAccount.getRole().getRoleName().equals("Super Admin")) {
			List<Doctor> therapists = therapistRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			List<Holiday> holiday = new ArrayList<Holiday>();
			for (Doctor doctor : therapists) {

				holiday = holidaysRepository.findByDoctor_Id(doctor.getId());
				holidaysList.addAll(holiday);
			}
		}
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")|| dbUserAccount.getRole().getRoleName().equals("Individual")) {
			Doctor therapist = therapistRepository.findByUserAccount_Id(dbUserAccount.getId());
			holidaysList = holidaysRepository.findByDoctor_Id(therapist.getId());
		}
		return holidaysList;
	}

	@Override
	public List<Holiday> getAllHolidaysByDoctorUsername(String doctorUsername) {
		// TODO Auto-generated method stub
		Doctor doctor = therapistRepository.findByEmail(doctorUsername);
		List<Holiday> holidays = null;
		LocalDate date = LocalDate.now();
		StringBuilder sb = new StringBuilder();
		sb.append(date);
		sb.append(" 00:00:00");
		if (doctor.getEmail() != null) {
			holidays = holidaysRepository.findByDoctor_Id(doctor.getId());
		} else {
			throw new RuntimeException(ErrorMessageHandler.doctorDoesNotExists);
		}
		return holidays;
	}

}
