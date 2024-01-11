package com.openspace.HospitalMgnt.Therapist.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.Config.Mail;
import com.openspace.Model.DoctorManagement.AppointMentStatus;
import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Holiday;
import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.Schedule;
import com.openspace.Model.DoctorManagement.ScheduleStatus;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.Dto.AppointmentDto;
import com.openspace.Model.Dto.AppointmentInvoiceDto;
import com.openspace.Model.Dto.AppointmentsDateDto;
import com.openspace.Model.Dto.InvoiceSubAppointmentDto;
import com.openspace.Model.Dto.ItemDto;
import com.openspace.Model.Dto.PaidStatus;
import com.openspace.Model.Dto.PatientInvoiceDto;
import com.openspace.Model.Dto.ScheduleDtoForCalendar;
import com.openspace.Model.Dto.SubAppointmentDto;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.PatientMgnt.Repositories.AppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.CompanyRepository;
import com.openspace.Model.PatientMgnt.Repositories.HolidaysRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PaymentRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.ScheduleRepository;
import com.openspace.Model.PatientMgnt.Repositories.SubAppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Payment.ModeOfPaymentType;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private TherapistRepository therapistRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private HolidaysRepository holidaysRepository;

	@Autowired
	private SubAppointmentRepository subAppointmentRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	
	@Override
	public void saveAppointment(AppointmentDto appoointmentDto) {
		Doctor doctor = therapistRepository.findByEmail(appoointmentDto.getDoctor().getEmail());
		if (doctor == null) {
			throw new RuntimeException(ErrorMessageHandler.therapistDoesNotExists);
		}
		String startdate = appoointmentDto.getAppointmentStartedDate();
		System.out.println("startdate" + startdate);
		String endDate = appoointmentDto.getAppointmentEndedDate();
		System.out.println("endDate" + endDate);
		String moodifiedStartDate = startdate.substring(0, 10);
		String moodifiedEndDate = endDate.substring(0, 10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate StartlocalDate = LocalDate.parse(moodifiedStartDate, formatter);
		LocalDate EndlocalDate = LocalDate.parse(moodifiedEndDate, formatter);

		LocalTime startTime = LocalTime.parse(appoointmentDto.getAppointmentStartTime());
		LocalTime endTime = LocalTime.parse(appoointmentDto.getAppointmentEndTime());
		// LocalTime durTime = LocalTime.parse(appoointmentDto.getDuration());

		Patient dbPatient = patientRepository.findOne(appoointmentDto.getPatient().getId());
		dbPatient.setAppointmentCreated(true);
		UserAccount userAccount = userAccountRepository.findByUsername(appoointmentDto.getAdminUserName());
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		Company company = null;
		if (!userAccount.getRole().getRoleName().equals("Individual")) {
			company = companyRepository.findOne(person.getUserAccount().getCompany().getId());
		}
		List<Appointment> appointmentList = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
		for (Appointment sc : appointmentList) {
			// checking subappointments start
			for (SubAppointment subAppointment : sc.getSubAppointments()) {

				if (StartlocalDate.isBefore(LocalDate.now()) || EndlocalDate.isBefore(LocalDate.now())) {
					throw new RuntimeException(ErrorMessageHandler.yourSelectedDateShouldBeGreaterthanPresentSystemDate);
				}

				if (StartlocalDate.equals(LocalDate.now())) {
					if (startTime.isBefore(LocalTime.now())) {
						throw new RuntimeException(ErrorMessageHandler.yourSelectedTimeShouldBeGreaterthanPresentSystemTime);
					}
				}
				if (StartlocalDate.equals(EndlocalDate)) {
					if (startTime.equals(endTime)) {
						throw new RuntimeException(ErrorMessageHandler.endtimeAndStartTimeShouldNotBeEqual);
					}
				}
				if (StartlocalDate.equals(EndlocalDate)) {
					if (startTime.isAfter(endTime)) {
						throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterthanStarttime);
					}
				}
				if (StartlocalDate.isAfter(EndlocalDate.plusDays(1))) {
					throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterThanStarttime);
				}
				if (subAppointment.getAppointmentStartedDate().equals(StartlocalDate)) {
					if (subAppointment.getAppointmentStartTime().equals(startTime)) {
						throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
					}
				}
				if (subAppointment.getAppointmentEndedDate().equals(EndlocalDate)) {
					if (subAppointment.getAppointmentEndTime().equals(endTime)) {
						throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
					}
				}
				if (subAppointment.getAppointmentStartedDate().equals(StartlocalDate)) {
					if (startTime.isAfter(subAppointment.getAppointmentStartTime())
							&& startTime.isBefore(subAppointment.getAppointmentEndTime())) {
						// checkes whether the current time is between
						// 14:49:00 and
						// 20:11:13.
						throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
					}
				}

				if (subAppointment.getAppointmentStartedDate().equals(StartlocalDate)) {
					if (startTime.isBefore(subAppointment.getAppointmentStartTime())
							&& endTime.isAfter(subAppointment.getAppointmentEndTime())) {
						throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
					}
				}
				if (subAppointment.getAppointmentStartedDate().equals(StartlocalDate)) {
					if (endTime.isAfter(subAppointment.getAppointmentStartTime())
							&& endTime.isBefore(subAppointment.getAppointmentEndTime())) {
						// checkes whether the current time is between
						// 14:49:00 and
						// 20:11:13.
						throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
					}
				}
			}
			// checking subappointments ends

			if (StartlocalDate.equals(EndlocalDate)) {
				if (startTime.equals(endTime)) {
					throw new RuntimeException(ErrorMessageHandler.endtimeAndStartTimeShouldNotBeEqual);
				}
			}
			if (StartlocalDate.equals(EndlocalDate)) {
				if (startTime.isAfter(endTime)) {
					throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterThanStarttime);
				}
			}
			if (StartlocalDate.isAfter(EndlocalDate.plusDays(1))) {
				throw new RuntimeException(ErrorMessageHandler.endtimeShouldBeGreaterThanStarttime);
			}

			if (sc.getAppointmentStartedDate().equals(StartlocalDate)) {
				if (sc.getAppointmentStartTime().equals(startTime)) {
					throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
				}
			}

			if (sc.getAppointmentEndedDate().equals(EndlocalDate)) {
				if (sc.getAppointmentEndTime().equals(endTime)) {
					throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
				}
			}
			if (sc.getAppointmentStartedDate().equals(StartlocalDate)) {
				if (startTime.isAfter(sc.getAppointmentStartTime()) && startTime.isBefore(sc.getAppointmentEndTime())) {
					// checkes whether the current time is between
					// 14:49:00 and
					// 20:11:13.
					throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
				}
			}
			if (sc.getAppointmentStartedDate().equals(StartlocalDate)) {
				if (startTime.isBefore(sc.getAppointmentStartTime()) && endTime.isAfter(sc.getAppointmentEndTime())) {
					throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
				}
			}
			if (sc.getAppointmentStartedDate().equals(StartlocalDate)) {
				if (endTime.isAfter(sc.getAppointmentStartTime()) && endTime.isBefore(sc.getAppointmentEndTime())) {
					// checkes whether the current time is between
					// 14:49:00 and
					// 20:11:13.
					throw new RuntimeException(ErrorMessageHandler.schedulesAreNotAvailableOnThisTimings);
				}
			}
		}
		List<Holiday> holidaysList = holidaysRepository.findByDoctor_Id(doctor.getId());

		for (Holiday holiday : holidaysList) {

			if (StartlocalDate.equals(holiday.getFromDate())) {
				if (startTime.equals(holiday.getFromLocalTime())) {
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			}

			if (StartlocalDate.equals(holiday.getFromDate())) {
				if (endTime.equals(holiday.getToLocalTime())) {
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			}

			if (StartlocalDate.equals(holiday.getFromDate())) {
				if (startTime.isAfter(holiday.getFromLocalTime()) && startTime.isBefore(holiday.getToLocalTime())) {
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			}
			if (holiday.getFromDate().equals(holiday.getToDate())) {
				if (StartlocalDate.equals(holiday.getFromDate())) {
					if (startTime.isBefore(holiday.getFromLocalTime()) && endTime.isAfter(holiday.getToLocalTime())) {
						throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
					}
				}
			}
			if (StartlocalDate.equals(holiday.getFromDate())) {
				if (endTime.isAfter(holiday.getFromLocalTime()) && endTime.isBefore(holiday.getToLocalTime())) {
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			}
			if (StartlocalDate.isAfter(holiday.getFromDate()) && StartlocalDate.isBefore(holiday.getToDate())) {
				if (startTime.isAfter(holiday.getFromLocalTime()) && startTime.isBefore(holiday.getToLocalTime())) {
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			}
			if (StartlocalDate.equals(holiday.getFromDate()) && StartlocalDate.isBefore(holiday.getToDate())) {
				if (startTime.isAfter(holiday.getFromLocalTime()) || endTime.isBefore(holiday.getToLocalTime())) {
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			}

			if (StartlocalDate.isAfter(holiday.getFromDate()) && StartlocalDate.equals(holiday.getToDate())) {
				if (endTime.isBefore(holiday.getToLocalTime())) {
					throw new RuntimeException(ErrorMessageHandler.itsAHolidayOnThisDate);
				}
			}
			/*
			 * if(StartlocalDate.isAfter(holiday.getFromDate())&&StartlocalDate.
			 * isBefore(holiday.getToDate())){
			 * 
			 * throw new RuntimeException("At this Time Holiday  Existed8888!");
			 * }
			 */
		}
		List<LocalDate> appointementWeekDates = new ArrayList<LocalDate>();
		if (appoointmentDto.getOccurance().name().equals("WEEKLY")) {
			Appointment appointment = new Appointment();
			List<Appointment> appointmentsList = new ArrayList<Appointment>();
			List<SubAppointment> subAppointmentsList = new ArrayList<SubAppointment>();
			if (appoointmentDto.getAfterOccurrence() != null) {
				LocalDate date = StartlocalDate;
				appointementWeekDates.add(date);
				for (int i = 1; i < appoointmentDto.getAfterOccurrence(); i++) {
					if (appoointmentDto.getWeeks() == null) {
						date = date.plusDays(7);
					} else {
						date = date.plusDays(7 * appoointmentDto.getWeeks());
					}
					appointementWeekDates.add(date);
				}

				for (LocalDate appointedDate : appointementWeekDates) {
					List<SubAppointment> subappointments = subAppointmentRepository
							.findByDoctor_IdAndAppointmentStartTimeGreaterThanEqualAndAppointmentEndTimeLessThanEqualAndAppointMentStatusAndAppointmentStartedDateAndActive(
									doctor.getId(), LocalTime.parse(appoointmentDto.getAppointmentStartTime()),
									LocalTime.parse(appoointmentDto.getAppointmentEndTime()),
									AppointMentStatus.APPOINTEMENT, appointedDate, true);
					if (subappointments.size() > 0) {
						throw new RuntimeException(ErrorMessageHandler.sorryAppointmentAlreadyScheduledOnThisDate);
					} else {
						SubAppointment subAppointment = new SubAppointment();
						// appointment.setDoctor(appoointmentDto.getDoctor());
						subAppointment.setDoctor(doctor);
						subAppointment.setPatient(appoointmentDto.getPatient());
						appointment.setDoctor(doctor);
						appointment.setPatient(appoointmentDto.getPatient());
						List<String> availableDays = new ArrayList<String>();
						availableDays.add(appointedDate.getDayOfWeek().name());
						subAppointment.setPerson(person);
						appointment.setPerson(person);
						if (!userAccount.getRole().getRoleName().equals("Individual")) {
							subAppointment.setCompany(company);
							appointment.setCompany(company);
						}
						Schedule dbSchedule = scheduleRepository.findOne(appoointmentDto.getScheduleId());
						for (String dbday : dbSchedule.getAvailableDays()) {
							if (dbday.equals(StartlocalDate.getDayOfWeek().name())) {
								String fromTime = dbSchedule.getFromTime() + ":00";
								String toTime = dbSchedule.getToTime() + ":00";
								LocalTime dbFromTime = LocalTime.parse(fromTime);
								LocalTime dbToTime = LocalTime.parse(toTime);
								LocalTime uiFromTime = LocalTime.parse(appoointmentDto.getAppointmentStartTime());
								LocalTime uiToTime = LocalTime.parse(appoointmentDto.getAppointmentEndTime());
								if (uiFromTime.isAfter(dbFromTime) || uiFromTime.equals(dbFromTime)) {
									if (uiToTime.isBefore(dbToTime) || uiToTime.equals(dbToTime)) {
										appointment.setAppointmentStartTime(startTime);
										appointment.setAppointmentEndTime(endTime);
										subAppointment.setAppointmentStartTime(startTime);
										subAppointment.setAppointmentEndTime(endTime);
									} else {
										throw new RuntimeException(ErrorMessageHandler.yourSelectedEndtimeShouldBeLessthanScheduledEndtime);
									}

								} else {
									throw new RuntimeException(
											ErrorMessageHandler.yourSelectedStarttimeShouldBeGreaterthanScheduledStartime);
								}
							}
						}

						/*
						 * List<Schedule> availableSchedules =
						 * scheduleRepository
						 * .findByAvailableDaysInAndDoctor_Id(availableDays,
						 * doctor.getId()); for (Schedule sc :
						 * availableSchedules) { for (String dbday :
						 * sc.getAvailableDays()) { if
						 * (dbday.equals(appointedDate.getDayOfWeek().name())) {
						 * String fromTime = sc.getFromTime() + ":00"; String
						 * toTime = sc.getToTime() + ":00"; if
						 * (fromTime.equals(appoointmentDto.
						 * getAppointmentStartTime())) { if
						 * (toTime.equals(appoointmentDto.getAppointmentEndTime(
						 * ))) {
						 * subAppointment.setAppointmentStartTime(startTime);
						 * subAppointment.setAppointmentEndTime(endTime);
						 * appointment.setAppointmentStartTime(startTime);
						 * appointment.setAppointmentEndTime(endTime); } else {
						 * throw new RuntimeException(
						 * "Please Select Valid Available EndTime!!"); }
						 * 
						 * } } } }
						 */
						if (subAppointment.getAppointmentStartTime() == null) {
							throw new RuntimeException(ErrorMessageHandler.pleaseSelectAValidAvailableStarttime);
						}
						if (appointment.getAppointmentStartTime() == null) {
							throw new RuntimeException(ErrorMessageHandler.pleaseSelectAValidAvailableStarttime);
						}
						subAppointment.setAppointmentStartedDate(appointedDate);
						subAppointment.setAppointmentEndedDate(appointedDate);
						appointment.setAppointmentStartedDate(appointedDate);
						appointment.setAppointmentEndedDate(appointedDate);
						appointment.setOccurance(appoointmentDto.getOccurance());
						appointment.setWeeks(appoointmentDto.getWeeks());
						appointment.setWeekDays(appointedDate.getDayOfWeek().name());
						appointment.setDescription(appoointmentDto.getDescription());
						appointment.setAfterOccurrence(appoointmentDto.getAfterOccurrence());
						appointment.setRepeatOnDay(appoointmentDto.getRepeatOnDay());
						appointment.setRepeatOnWhichWeek(appoointmentDto.getRepeatOnWhichWeek());
						appointment.setRepeatOnWeek(appoointmentDto.getRepeatOnWeek());
						appointment.setEnd(appoointmentDto.getEnd());
						appointment.setRepeateOn(appoointmentDto.getRepeateOn());
						// appointment.setDuration(durTime);
						appointment.setAppointmentStatus(AppointMentStatus.APPOINTEMENT);
						subAppointment.setAppointMentStatus(AppointMentStatus.APPOINTEMENT);
						subAppointment.setCreatedDate(LocalDate.now());
						appointment.setCreatedDate(LocalDate.now());

						/////////////
						List<Holiday> holidaysList1 = holidaysRepository.findByDoctor_Id(doctor.getId());

						for (Holiday holiday : holidaysList1) {

							if (appointedDate.equals(holiday.getFromDate())) {
								if (startTime.equals(holiday.getFromLocalTime())) {
									throw new RuntimeException("At" + holiday.getFromDate() + "  Holiday  Existed1!");
								}
							}

							if (appointedDate.equals(holiday.getFromDate())) {
								if (endTime.equals(holiday.getToLocalTime())) {
									throw new RuntimeException("At" + holiday.getFromDate() + "  Holiday  Existed2!");
								}
							}

							if (appointedDate.equals(holiday.getFromDate())) {
								if (startTime.isAfter(holiday.getFromLocalTime())
										&& startTime.isBefore(holiday.getToLocalTime())) {
									throw new RuntimeException("At" + holiday.getFromDate() + "  Holiday  Existed3!");
								}
							}
							if (holiday.getFromDate().equals(holiday.getToDate())) {
								if (StartlocalDate.equals(holiday.getFromDate())) {
									if (startTime.isBefore(holiday.getFromLocalTime())
											&& endTime.isAfter(holiday.getToLocalTime())) {
										throw new RuntimeException(
												"At" + holiday.getFromDate() + "  Holiday  Existed4!");
									}
								}
							}
							if (appointedDate.equals(holiday.getFromDate())) {
								if (endTime.isAfter(holiday.getFromLocalTime())
										&& endTime.isBefore(holiday.getToLocalTime())) {
									throw new RuntimeException("At" + holiday.getFromDate() + " Holiday  Existed5!");
								}
							}
							if (appointedDate.isAfter(holiday.getFromDate())
									&& appointedDate.isBefore(holiday.getToDate())) {
								if (startTime.isAfter(holiday.getFromLocalTime())
										&& startTime.isBefore(holiday.getToLocalTime())) {
									throw new RuntimeException("At" + holiday.getFromDate() + "  Holiday  Existed6!");
								}
							}
							if (appointedDate.equals(holiday.getFromDate())
									&& appointedDate.isBefore(holiday.getToDate())) {
								if (startTime.isAfter(holiday.getFromLocalTime())
										|| endTime.isBefore(holiday.getToLocalTime())) {
									throw new RuntimeException("At" + holiday.getFromDate() + "  Holiday  Existed7!");
								}
							}

							if (appointedDate.isAfter(holiday.getFromDate())
									&& appointedDate.equals(holiday.getToDate())) {
								if (endTime.isBefore(holiday.getToLocalTime())) {
									throw new RuntimeException("At" + holiday.getFromDate() + " Holiday  Existed8!");
								}
							}
							/*
							 * if(StartlocalDate.isAfter(holiday.getFromDate())&
							 * &StartlocalDate.isBefore(holiday.getToDate())){
							 * 
							 * throw new RuntimeException(
							 * "At this Time Holiday  Existed8888!"); }
							 */
						}
						//////////////////
						appointment.setActive(true);
						subAppointment.setActive(true);
						subAppointment.setStatus(SubAppointmentStatus.AWAITING);
						subAppointment.setMonth(subAppointment.getAppointmentStartedDate().getMonth());
						subAppointment.setYear(subAppointment.getAppointmentStartedDate().getYear());
						System.out.println(subAppointment.getAppointmentStartedDate().getMonth());
						subAppointment.setOccurance(appointment.getOccurance());
						subAppointmentsList.add(subAppointment);
						appointment.setSubAppointments(subAppointmentsList);
						subAppointment.setAppointment(appointment);

					}
					List<Payment> dbPaymentsList=paymentRepository.findByPatient_Id(dbPatient.getId());
					for(Payment dbPayment:dbPaymentsList){
						if(dbPayment.getDoctor()==null){
							dbPayment.setDoctor(doctor);
							dbPayment.setOccurance(Occurance.WEEKLY);
							paymentRepository.save(dbPayment);
						}
					}
					appointmentsList.add(appointment);
				}
				appointment.setAppointmentStartedDate(StartlocalDate);
				appointment.setAppointmentEndedDate(EndlocalDate);
				appointmentRepository.save(appointment);
				patientRepository.save(dbPatient);
				/*
				 * for (SubAppointment dbsubAppointment : subAppointmentsList) {
				 * dbsubAppointment.setAppointment(appointment);
				 * subAppointmentRepository.save(dbsubAppointment);
				 * 
				 * 
				 * List<String> availableDay = new ArrayList<String>();
				 * availableDay.add(appointment.getAppointmentStartedDate().
				 * getDayOfWeek().name()); Schedule schedule =
				 * scheduleRepository
				 * .findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(
				 * availableDay, appointment.getDoctor().getId(),
				 * appointment.getAppointmentStartTime(),
				 * appointment.getAppointmentEndTime()); if (schedule != null) {
				 * schedule.setScheduleStatus(ScheduleStatus.APPOINTED);
				 * scheduleRepository.save(schedule); }
				 * availableDay.remove(availableDay);
				 * 
				 * }
				 */

			}

		} else {

			Appointment appointment = new Appointment();
			SubAppointment subappointment = new SubAppointment();
			subappointment.setCreatedDate(LocalDate.now());
			appointment.setCreatedDate(LocalDate.now());
			// appointment.setDoctor(appoointmentDto.getDoctor());
			appointment.setDoctor(doctor);
			appointment.setPatient(appoointmentDto.getPatient());
			subappointment.setDoctor(doctor);
			subappointment.setPatient(appoointmentDto.getPatient());
			List<String> availableDays = new ArrayList<String>();
			availableDays.add(StartlocalDate.getDayOfWeek().name());
			appointment.setPerson(person);
			subappointment.setPerson(person);
			if (!userAccount.getRole().getRoleName().equals("Individual")) {
				appointment.setCompany(company);
				subappointment.setCompany(company);
			}
			Schedule dbSchedule = scheduleRepository.findOne(appoointmentDto.getScheduleId());
			for (String dbday : dbSchedule.getAvailableDays()) {
				if (dbday.equals(StartlocalDate.getDayOfWeek().name())) {
					String fromTime = dbSchedule.getFromTime() + ":00";
					String toTime = dbSchedule.getToTime() + ":00";
					LocalTime dbFromTime = LocalTime.parse(fromTime);
					LocalTime dbToTime = LocalTime.parse(toTime);
					LocalTime uiFromTime = LocalTime.parse(appoointmentDto.getAppointmentStartTime());
					LocalTime uiToTime = LocalTime.parse(appoointmentDto.getAppointmentEndTime());
					if (uiFromTime.isAfter(dbFromTime) || uiFromTime.equals(dbFromTime)) {
						if (uiToTime.isBefore(dbToTime) || uiToTime.equals(dbToTime)) {
							appointment.setAppointmentStartTime(startTime);
							appointment.setAppointmentEndTime(endTime);
							subappointment.setAppointmentStartTime(startTime);
							subappointment.setAppointmentEndTime(endTime);
						} else {
							throw new RuntimeException(
									ErrorMessageHandler.yourSelectedEndtimeShouldBeLessthanScheduledEndtime);
						}

					} else {
						throw new RuntimeException(
								ErrorMessageHandler.yourSelectedStarttimeShouldBeGreaterthanScheduledStartime);
					}
				}
			}

			/*
			 * List<Schedule> availableSchedules =
			 * scheduleRepository.findByAvailableDaysInAndDoctor_Id(
			 * availableDays, doctor.getId()); for (Schedule sc :
			 * availableSchedules) { for (String dbday : sc.getAvailableDays())
			 * { if (dbday.equals(StartlocalDate.getDayOfWeek().name())) {
			 * String fromTime = sc.getFromTime() + ":00"; String toTime =
			 * sc.getToTime() + ":00"; LocalTime dbFromTime =
			 * LocalTime.parse(fromTime); LocalTime dbToTime =
			 * LocalTime.parse(toTime); LocalTime uiFromTime =
			 * LocalTime.parse(appoointmentDto.getAppointmentStartTime());
			 * LocalTime uiToTime =
			 * LocalTime.parse(appoointmentDto.getAppointmentEndTime()); if
			 * (uiFromTime.isAfter(dbFromTime) ) { if
			 * (uiToTime.isBefore(dbToTime)) {
			 * appointment.setAppointmentStartTime(startTime);
			 * appointment.setAppointmentEndTime(endTime);
			 * subappointment.setAppointmentStartTime(startTime);
			 * subappointment.setAppointmentEndTime(endTime); } else { throw new
			 * RuntimeException(
			 * "Please Select EndTime Should Be Less Than The Scheduled EndTime"
			 * ); }
			 * 
			 * }else{ throw new RuntimeException(
			 * "Please Select StartTime Should Be Greater Than The Scheduled Start Time!"
			 * ); } } } }
			 */
			/*
			 * if (appointment.getAppointmentStartTime() == null) { throw new
			 * RuntimeException("Please Select Valid Available StartTime!!"); }
			 */
			subappointment.setAppointmentStartTime(startTime);
			subappointment.setAppointmentEndTime(endTime);
			subappointment.setAppointmentStartedDate(StartlocalDate);
			subappointment.setAppointmentEndedDate(EndlocalDate);
			appointment.setAppointmentStartTime(startTime);
			appointment.setAppointmentEndTime(endTime);
			appointment.setAppointmentStartedDate(StartlocalDate);
			appointment.setAppointmentEndedDate(EndlocalDate);
			appointment.setOccurance(appoointmentDto.getOccurance());
			appointment.setWeeks(appoointmentDto.getWeeks());
			appointment.setWeekDays(StartlocalDate.getDayOfWeek().name());
			appointment.setDescription(appoointmentDto.getDescription());
			appointment.setAfterOccurrence(appoointmentDto.getAfterOccurrence());
			appointment.setRepeatOnDay(appoointmentDto.getRepeatOnDay());
			appointment.setRepeatOnWhichWeek(appoointmentDto.getRepeatOnWhichWeek());
			appointment.setRepeatOnWeek(appoointmentDto.getRepeatOnWeek());
			appointment.setEnd(appoointmentDto.getEnd());
			appointment.setRepeateOn(appoointmentDto.getRepeateOn());
			// appointment.setDuration(durTime);
			appointment.setAppointmentStatus(AppointMentStatus.APPOINTEMENT);
			subappointment.setAppointMentStatus(AppointMentStatus.APPOINTEMENT);

			patientRepository.save(dbPatient);
			appointment.setActive(true);
			subappointment.setActive(true);
			List<SubAppointment> list = new ArrayList<SubAppointment>();
			list.add(subappointment);
			appointment.setSubAppointments(list);
			appointmentRepository.save(appointment);
			subappointment.setAppointment(appointment);
			subappointment.setStatus(SubAppointmentStatus.AWAITING);
			subappointment.setMonth(subappointment.getAppointmentStartedDate().getMonth());
			subappointment.setYear(subappointment.getAppointmentStartedDate().getYear());
			List<Payment> dbPaymentsList=paymentRepository.findByPatient_Id(dbPatient.getId());
			for(Payment dbPayment:dbPaymentsList){
				if(dbPayment.getDoctor()==null){
					dbPayment.setDoctor(doctor);
					dbPayment.setOccurance(Occurance.NEVER);
					paymentRepository.save(dbPayment);
				}
			}
			subappointment.setOccurance(appointment.getOccurance());
			subAppointmentRepository.save(subappointment);
			List<String> availableDay = new ArrayList<String>();
			availableDay.add(appointment.getAppointmentStartedDate().getDayOfWeek().name());

			Schedule schedule = scheduleRepository
					.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqual(availableDay,
							appointment.getDoctor().getId(), appointment.getAppointmentStartTime(),
							appointment.getAppointmentEndTime());
			if (schedule != null) {
				schedule.setScheduleStatus(ScheduleStatus.APPOINTED);
				scheduleRepository.save(schedule);
			}
			availableDay.remove(availableDay);

		}
		if (appoointmentDto.getScheduleId() != null) {
			Schedule schedule = scheduleRepository.findOne(appoointmentDto.getScheduleId());
			if (schedule != null) {
				schedule.setScheduleStatus(ScheduleStatus.APPOINTED);
			}
			scheduleRepository.save(schedule);
		}
		String body = ""
				+ "<div class=\"col-sm-10 col-sm-offset-2\" style=\"background-color:gray;  border: 2px solid pink;  padding: 5px;  margin: 5px;\">"
				+ "<div class=\"col-sm-2\">" + "&nbsp;" + "</div>" + "<div class=\"col-sm-8 col-sm-offset-2 border\">"
				+ "<h1 align=\"center\">" + "Your booking is confirmed</h1>"
				+ "<label>Dear,"+userAccount.getUsername()+"</label><p>Your booking is confirmed. See below for more information.If the event is not already in your calendar,please use the calendar links provided below to add it.</p>"
				+ "<hr><h2>Booking details</h2><h3>Subject</h3><p>FREE Live Demo with Matt at ClinicSource.com</p><h3>Time</h3>"
				+ "<p>Tue, Jan 30, 2018, 02:30am - 03:15am India; New Delhi,Bangalore, Kolkata, Chennai, Mumbai (GMT+5:30)</p></div></div>";

		Mail mail = new Mail();
		//mail.postMail(appoointmentDto.getDoctor().getEmail(), "Appointment Saved Successfully !", body);
		mail.postMail(appoointmentDto.getDoctor().getEmail(), "Appointment Saved Successfully !",
				" This email is to confirm that we have an recurring appointment scheduled for  " + appoointmentDto.getPatient().getFirstName()+appoointmentDto.getPatient().getLastName()
						+ " on  " + StartlocalDate + "  "+ appoointmentDto.getAppointmentStartTime()+
	" Reminder emails or SMS will be sent 24 hours prior to the appointment. If you need to cancel or change any appointment, please contact us at least 48 hours before the appointment.  "
+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
		if (appoointmentDto.getPatient().getEmail() != null) {
			/*mail.postMail(appoointmentDto.getPatient().getEmail(), "Appointment Saved Successfully !",
					" your Appointment is created with doctor " + appoointmentDto.getDoctor().getFirstName()
							+ " has been scheduled at " + StartlocalDate + "  "
							+ appoointmentDto.getAppointmentStartTime());*/
			
			mail.postMail(appoointmentDto.getPatient().getEmail(), "Appointment Saved Successfully !",
					" This email is to confirm that we have an recurring appointment scheduled for  " + appoointmentDto.getDoctor().getFirstName()+appoointmentDto.getDoctor().getLastName()
							+ " on  " + StartlocalDate + "  "+ appoointmentDto.getAppointmentStartTime()+
		" Reminder emails or SMS will be sent 24 hours prior to the appointment. If you need to cancel or change any appointment, please contact us at least 48 hours before the appointment.  "
	+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
		}
	}

	@Override
	public Page<SubAppointment> getAppointmentssByRole(String adminUserName, int page, int size) {
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		if (person == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		//Appointment appointment = new Appointment();
		Page<SubAppointment> appointmentList = null;
		if (userAccount.getRole().getRoleName().equals("Therapist")) {
			Doctor doctor = therapistRepository.findOne(person.getId());
			appointmentList = subAppointmentRepository.findByDoctor_IdAndAppointmentStartedDateAndActive(doctor.getId(),
					LocalDate.now(), true, new PageRequest(page, size, Sort.Direction.DESC, "id"));
		} else {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (userAccount.getRole().getRoleName().equals("Individual")) {
				appointmentList = subAppointmentRepository.findByPerson_IdAndAppointmentStartedDateAndActive(doctor.getId(),LocalDate.now(), true,
						new PageRequest(page, size, Sort.Direction.DESC, "id"));
			}/* else {
				appointmentList = subAppointmentRepository.findByPerson_IdAndActive(person.getId(), true,
						new PageRequest(page, size, Sort.Direction.DESC, "id"));
			}*/
			 else {
					appointmentList = subAppointmentRepository.findByPerson_IdAndAppointmentStartedDateAndActive(person.getId(),LocalDate.now(), true,
							new PageRequest(page, size, Sort.Direction.DESC, "id"));
				}
		}
		return appointmentList;
	}
	
	@Override
	public Page<Appointment> getAppointmentsByRole(String adminUserName, int page, int size) {
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		if (person == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		//Appointment appointment = new Appointment();
		Page<Appointment> appointmentList = null;
		if (userAccount.getRole().getRoleName().equals("Therapist")) {
			Doctor doctor = therapistRepository.findOne(person.getId());
			appointmentList = appointmentRepository.findByDoctor_IdAndActive(doctor.getId(),
					true, new PageRequest(page, size, Sort.Direction.DESC, "id"));
		} else {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (userAccount.getRole().getRoleName().equals("Individual")) {
				appointmentList = appointmentRepository.findByPerson_IdAndActive(doctor.getId(), true,
						new PageRequest(page, size, Sort.Direction.DESC, "id"));
			} else {
				appointmentList = appointmentRepository.findByPerson_IdAndActive(person.getId(), true,
						new PageRequest(page, size, Sort.Direction.DESC, "id"));
			}
		}
		System.out.println(appointmentList.getSize());
				/*appointmentList = appointmentRepository.findByPerson_IdAndActive(person.getId(), true,
						new PageRequest(page, size, Sort.Direction.DESC, "id"));*/
		return appointmentList;
	}

	@Override
	public int getAllAppointmentsCountByRole(String adminUserName) {
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		if (person == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		Appointment appointment = new Appointment();
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		if (userAccount.getRole().getRoleName().equals("Therapist")) {
			Doctor doctor = therapistRepository.findOne(person.getId());
			appointmentList = appointmentRepository.findByDoctor_IdAndAppointmentStartedDateAndActive(doctor.getId(),
					LocalDate.now(), true);
		} else {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (userAccount.getRole().getRoleName().equals("Individual")) {
				appointmentList = appointmentRepository.findByPerson_IdAndActive(doctor.getId(), true);
			} else {

				appointmentList = appointmentRepository.findByPerson_IdAndActive(person.getId(), true);
			}
		}
		return appointmentList.size();
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		Appointment dbAppointment = appointmentRepository.findOne(appointment.getId());
		if (dbAppointment == null) {
			throw new RuntimeException(ErrorMessageHandler.appointmentDoesNotExists);
		}
		dbAppointment.setDoctor(appointment.getDoctor());
		dbAppointment.setPatient(appointment.getPatient());
		dbAppointment.setOccurance(appointment.getOccurance());
		dbAppointment.setWeeks(appointment.getWeeks());
		dbAppointment.setWeekDays(appointment.getWeekDays());
		dbAppointment.setDescription(appointment.getDescription());
		appointmentRepository.save(dbAppointment);
	}
	@Override
	public Page<SubAppointment> getAppointlistByDates(AppointmentsDateDto appointmentsDateDto, int page, int size){
		List<SubAppointment> appointmentList=new ArrayList<>();
		
		if(appointmentsDateDto.getAppointmentEndedDate()!=null){
			appointmentList = getAppointlistByDatesList(appointmentsDateDto);
			System.out.println(appointmentList.size());
			

		}
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > appointmentList.size() ? appointmentList.size()
				: (start + new PageRequest(page, size).getPageSize());
		return new PageImpl<SubAppointment>(appointmentList.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), appointmentList.size());
		
	}
	
	public List<SubAppointment> getAppointlistByDatesList(AppointmentsDateDto appointmentsDateDto) {
		String startdate = appointmentsDateDto.getAppointmentStartedDate();
		String endDate = appointmentsDateDto.getAppointmentEndedDate();
		String moodifiedStartDate = startdate.substring(0, 10);
		String moodifiedEndDate = endDate.substring(0, 10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate StartlocalDate = LocalDate.parse(moodifiedStartDate, formatter);
		LocalDate StartDate = LocalDate.parse(moodifiedStartDate, formatter).plusDays(1);
		LocalDate EndlocalDate = LocalDate.parse(moodifiedEndDate, formatter);
		LocalDate EndDate = LocalDate.parse(moodifiedEndDate, formatter).plusDays(1);
		UserAccount userAccount = userAccountRepository.findByUsername(appointmentsDateDto.getAdminUserName());
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<SubAppointment> appointmentList = null;
		List<SubAppointment> appointmentList1 = new ArrayList<>();
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		Doctor doctor = therapistRepository.findOne(person.getId());
		if (userAccount.getRole().getRoleName().equals("Therapist")) {
			appointmentList = subAppointmentRepository
					.findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
							doctor.getId(), StartDate, EndDate, true);
		} else {
			// List<Doctor> therapists =
			// therapistRepository.findByCompany_Id(userAccount.getCompany().getId());
			// for (Doctor therapist : therapists) {
			appointmentList = subAppointmentRepository
					.findByPerson_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqualAndActive(
							person.getId(), StartDate, EndDate, true);
			
			// }
			// return new PageImpl<Appointment>(appointmens, new
			// PageRequest(page, size), appointmens.size());
		}
		for(SubAppointment appointment:appointmentList){
			if(appointment.getAppointment().getAppointmentStartedDate().isAfter(LocalDate.now())){
				
				appointment.setDateFlag(true);
				
			}
			appointmentList1.add(appointment);
		}
		return appointmentList1;

	}

	@Override
	public void deleteAppointment(Long id) {
		Appointment dbAppointment = appointmentRepository.findOne(id);
		if (dbAppointment == null) {
			throw new RuntimeException(ErrorMessageHandler.appointmentDoesNotExists);
		}
		/*if (dbAppointment.getAppointmentEndedDate().isAfter(LocalDate.now())) {
			throw new RuntimeException("AppointMent is in Progress.. Can't Delete");
		}
		if (dbAppointment.getAppointmentEndedDate().isEqual(LocalDate.now())) {
			if (dbAppointment.getAppointmentEndTime().isAfter(LocalTime.now()))
				throw new RuntimeException("AppointMent is in Progress.. Can't Delete");
		}*/

		List<String> availableDays = new ArrayList<String>();
		availableDays.add(dbAppointment.getAppointmentStartedDate().getDayOfWeek().name());
		List<Schedule> schedules = scheduleRepository
				.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqualAndScheduleStatus(
						availableDays, dbAppointment.getDoctor().getId(), dbAppointment.getAppointmentStartTime(),
						dbAppointment.getAppointmentEndTime(), ScheduleStatus.APPOINTED);
		if (schedules.size() > 0) {
			for (Schedule schedule : schedules) {
				schedule.setScheduleStatus(ScheduleStatus.AVAILABILITY);
				scheduleRepository.save(schedule);
			}
		}
		List<Schedule> subAppointmentSchedulesList = new ArrayList<Schedule>();
		for (SubAppointment subAppointment : dbAppointment.getSubAppointments()) {
			List<Schedule> subAppointmentSchedule = scheduleRepository
					.findByAvailableDaysInAndDoctor_IdAndFromTimeGreaterThanEqualAndToTimeLessThanEqualAndScheduleStatus(
							availableDays, subAppointment.getDoctor().getId(), subAppointment.getAppointmentStartTime(),
							subAppointment.getAppointmentEndTime(), ScheduleStatus.APPOINTED);
			subAppointmentSchedulesList.addAll(subAppointmentSchedule);
		}
		if (subAppointmentSchedulesList.size() > 0) {
			for (Schedule schedule : subAppointmentSchedulesList) {
				schedule.setScheduleStatus(ScheduleStatus.AVAILABILITY);
				scheduleRepository.save(schedule);
			}
		}
		Patient dbPatient = patientRepository.findOne(dbAppointment.getPatient().getId());
		if (dbPatient != null) {
			dbPatient.setAppointmentCreated(false);
			patientRepository.save(dbPatient);
		}

		dbAppointment.setActive(false);
		dbAppointment.setAppointmentStatus(AppointMentStatus.APPOINTMENTAVAILABLE);
		for (SubAppointment subAppointment : dbAppointment.getSubAppointments()) {
			subAppointment.setActive(false);
		}
		appointmentRepository.save(dbAppointment);
	}

	@Override
	public List<Appointment> getAppointmentssByRole(String adminUserName) {
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		if (person == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		Appointment appointment = new Appointment();
		List<Appointment> appointmentList = null;
		if (userAccount.getRole().getRoleName().equals("Therapist")) {

			Doctor doctor = therapistRepository.findOne(person.getId());
			appointmentList = appointmentRepository.findByDoctor_IdAndAppointmentStartedDateAndActive(doctor.getId(),
					LocalDate.now(), true);
		} else {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (userAccount.getRole().getRoleName().equals("Individual")) {
				appointmentList = appointmentRepository.findByPerson_IdAndActive(doctor.getId(), true);
			} else {
				appointmentList = appointmentRepository.findByPerson_IdAndActive(person.getId(), true);
			}
		}
		return appointmentList;
	}

	@Override
	public List<SubAppointmentDto> getSubAppointmentssByRole(String adminUserName) {
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		if (person == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		List<SubAppointment> subAppointmentList = null;
		if (userAccount.getRole().getRoleName().equals("Therapist")) {

			Doctor doctor = therapistRepository.findOne(person.getId());

			subAppointmentList = subAppointmentRepository
					.findByDoctor_IdAndAppointmentStartedDateAndActive(doctor.getId(), LocalDate.now(), true);
		} else {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (userAccount.getRole().getRoleName().equals("Individual")) {
				subAppointmentList = subAppointmentRepository.findByPerson_IdAndActive(doctor.getId(), true);
			} else {
				subAppointmentList = subAppointmentRepository.findByPerson_IdAndActive(person.getId(), true);
			}
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<SubAppointmentDto> subAppointmentslist = new ArrayList<SubAppointmentDto>();
		for (SubAppointment subAppointment2 : subAppointmentList) {
			SubAppointmentDto subAppointmentDto = convertSubAppointmentToSubAppointmentDto(subAppointment2, formatter);
			subAppointmentslist.add(subAppointmentDto);
		}
		return subAppointmentslist;
	}

	@Override
	public List<SubAppointmentDto> getSubAppointmentssByRoleForCalendarView(String adminUserName) {
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		if (person == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		List<SubAppointment> subAppointmentList = null;
		if (userAccount.getRole().getRoleName().equals("Therapist")) {

			Doctor doctor = therapistRepository.findOne(person.getId());

			subAppointmentList = subAppointmentRepository.findByDoctor_IdAndActive(doctor.getId(), true);
		} else {
			Doctor doctor = therapistRepository.findOne(person.getId());
			if (userAccount.getRole().getRoleName().equals("Individual")) {
				subAppointmentList = subAppointmentRepository.findByPerson_IdAndActive(doctor.getId(), true);
			} else {
				subAppointmentList = subAppointmentRepository.findByPerson_IdAndActive(person.getId(), true);
			}
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<SubAppointmentDto> subAppointmentslist = new ArrayList<SubAppointmentDto>();
		for (SubAppointment subAppointment2 : subAppointmentList) {
			SubAppointmentDto subAppointmentDto = convertSubAppointmentToSubAppointmentDto(subAppointment2, formatter);
			subAppointmentslist.add(subAppointmentDto);
		}
		return subAppointmentslist;
	}

	public SubAppointmentDto convertSubAppointmentToSubAppointmentDto(SubAppointment subAppointment,
			DateTimeFormatter formatter) {
		SubAppointmentDto subAppointmentDto = new SubAppointmentDto();
		subAppointmentDto.setActive(true);
		subAppointmentDto.setAppointment(subAppointment.getAppointment());
		subAppointmentDto.setAppointmentEndedDate(subAppointment.getAppointmentEndedDate().format(formatter));
		subAppointmentDto.setAppointmentStartedDate(subAppointment.getAppointmentStartedDate().format(formatter));
		subAppointmentDto.setPatient(subAppointment.getPatient());
		subAppointmentDto.setDoctor(subAppointment.getDoctor());
		subAppointmentDto.setAppointmentStartTime(subAppointment.getAppointmentStartTime().toString());
		subAppointmentDto.setAppointmentEndTime(subAppointment.getAppointmentEndTime().toString());
		return subAppointmentDto;

	}

	@Override
	public List<SubAppointmentDto> getTodayAppointments(String adminUserName){
		UserAccount userAccount = userAccountRepository.findByUsername(adminUserName);
		if (userAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Person person = personRepository.findByUserAccount_Id(userAccount.getId());
		if (person == null) {
			throw new RuntimeException(ErrorMessageHandler.personDoesNotExists);
		}
		List<SubAppointment> subAppointmentList;
		if(!userAccount.getRole().getRoleName().equals("Therapist")){
		 subAppointmentList = subAppointmentRepository.findByPerson_IdAndAppointmentStartedDateAndActive(person.getId(),LocalDate.now(), true);
		}else{
			 subAppointmentList=subAppointmentRepository.findByDoctor_IdAndAppointmentStartedDateAndActive(person.getId(),LocalDate.now(), true);
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<SubAppointmentDto> subAppointmentslist = new ArrayList<SubAppointmentDto>();
		for (SubAppointment subAppointment2 : subAppointmentList) {
			SubAppointmentDto subAppointmentDto = new SubAppointmentDto();
			subAppointmentDto.setId(subAppointment2.getId());
			subAppointmentDto.setActive(true);
			subAppointmentDto.setStatus(subAppointment2.getStatus());
			subAppointmentDto.setAppointment(subAppointment2.getAppointment());
			subAppointmentDto.setAppointmentEndedDate(subAppointment2.getAppointmentEndedDate().format(formatter));
			subAppointmentDto.setAppointmentStartedDate(subAppointment2.getAppointmentStartedDate().format(formatter));
			subAppointmentDto.setPatient(subAppointment2.getPatient());
			subAppointmentDto.setDoctor(subAppointment2.getDoctor());
			subAppointmentDto.setAppointmentStartTime(subAppointment2.getAppointmentStartTime().toString()+":00");
			subAppointmentDto.setAppointmentEndTime(subAppointment2.getAppointmentEndTime().toString()+":00");
			subAppointmentslist.add(subAppointmentDto);
		}
		return subAppointmentslist;
		
	}

	/*@Override
	public void completeSubappointments(List<Long> idsList) {
		// TODO Auto-generated method stub
		System.out.println(idsList.size());
		for(Long id:idsList){
			if(id!=null){
			SubAppointment dbSubAppointment=subAppointmentRepository.findOne(id);
			dbSubAppointment.setApprove(true);
			subAppointmentRepository.save(dbSubAppointment);
			dbSubAppointment.setApprove(false);
			subAppointmentRepository.save(dbSubAppointment);
			}
		}
	}*/

	@Override
	public void updateSubappointmentStatus(Long id, SubAppointmentStatus status) {
		boolean ismonthFlagHasInvoiceNo=false;
		boolean isweekFlagHasInvoiceNo=false;
		boolean flag=false;
		System.out.println("id 999"+id);
		System.out.println("status 1000"+status);
		SubAppointment dbSubAppointment=subAppointmentRepository.findOne(id);
		dbSubAppointment.setStatus(status);
		
		//subAppointmentRepository.save(dbSubAppointment);
		
		if(status.equals(SubAppointmentStatus.CANCEL)){
			dbSubAppointment.setPaidStatus(null);
			subAppointmentRepository.save(dbSubAppointment);
			System.out.println(status.equals(SubAppointmentStatus.CANCEL));
			Mail mail = new Mail();
			mail.postMail(dbSubAppointment.getDoctor().getEmail(), "Appointment was cancelled !",
					" your Appointment  was cancelled  with patient " + dbSubAppointment.getPatient().getFirstName()+ dbSubAppointment.getPatient().getLastName()
							+ " has been scheduled at " + dbSubAppointment.getAppointmentStartedDate() + "  "
							+ dbSubAppointment.getAppointmentStartTime());
			if (dbSubAppointment.getPatient().getEmail() != null) {
				mail.postMail(dbSubAppointment.getPatient().getEmail(), "Appointment  was cancelled  !",
						" your Appointment  was cancelled  with doctor " + dbSubAppointment.getDoctor().getFirstName()+ dbSubAppointment.getDoctor().getLastName()
								+ " has been scheduled at " + dbSubAppointment.getAppointmentStartedDate() + "  "
								+ dbSubAppointment.getAppointmentStartTime());
			}
		}
		if(status.equals(SubAppointmentStatus.COMPLETE)){
			if(dbSubAppointment.getInvoiceNo()==null){
			dbSubAppointment.setPaidStatus(PaidStatus.DUE);
			//subAppointmentRepository.save(dbSubAppointment);
			
			List<Payment> dbPayments=paymentRepository.findByPatient_Id(dbSubAppointment.getPatient().getId());
			ModeOfPaymentType mode = null;
			 
			for(Payment payment:dbPayments){ 
			 mode=	payment.getModeOfPaymentTypes();
			System.out.println("1032:"+mode);
			}
			int y=0,b=0;
			if(mode==null){
				throw new RuntimeException(ErrorMessageHandler.patientDoesNotHaveAModeOfPayment+" ");
			}
			if(mode.equals(ModeOfPaymentType.WEEKLY)||mode.equals(ModeOfPaymentType.ONCE)){
				 /////////////////
				List<SubAppointment> subAppointments=subAppointmentRepository.findByMonthAndYear(dbSubAppointment.getMonth(),dbSubAppointment.getYear());
				SubAppointment subAppointment2=null;
				SubAppointment subAppointment3=null;
				for(SubAppointment subAppointment:subAppointments){
					
						subAppointment2=subAppointment;
						if(subAppointment2.getInvoiceNo()==null){
							
						}else{
							isweekFlagHasInvoiceNo=true;
							int x =Integer.parseInt(subAppointment.getInvoiceNo().substring(4, 8));
							if(x>y){
								subAppointment3=subAppointment;
								y=x;
							}
							 
						}
				}
				if(isweekFlagHasInvoiceNo==false){
					String year=Integer.toString(dbSubAppointment.getYear()).substring(2);
					String invoiceNo=dbSubAppointment.getMonth().toString().substring(0, 3)+"/"+"0001"+"/"+year;
					System.out.println("invoiceNo2:"+invoiceNo);
					dbSubAppointment.setInvoiceNo(invoiceNo);
				}else{
					System.out.println("1050:"+subAppointment3.getInvoiceNo());
					System.out.println("1088:"+subAppointment3.getInvoiceNo().substring(4, 8));
					System.out.println("1089:"+subAppointment3.getInvoiceNo().substring(4, 8)+1);
					int a=Integer.parseInt(subAppointment3.getInvoiceNo().substring(4, 8))+1;
					String number=String.format("%04d", a);
					String year=Integer.toString(dbSubAppointment.getYear()).substring(2);
					String invoiceNo=dbSubAppointment.getMonth().toString().substring(0, 3)+"/"+number+"/"+year;
					System.out.println("invoiceNo 1056:"+invoiceNo);
					dbSubAppointment.setInvoiceNo(invoiceNo);
				}
				///////////////////////
			}else if(mode.equals(ModeOfPaymentType.MONTHLY)){
				System.out.println("monthly enter");
				// appointments in month
				List<SubAppointment> subAppointments=subAppointmentRepository.findByMonthAndYear(dbSubAppointment.getMonth(),dbSubAppointment.getYear());
				SubAppointment subAppointment2=null;
				SubAppointment subAppointment3=null;
				for(SubAppointment subAppointment:subAppointments){
					
						subAppointment2=subAppointment;
						if(subAppointment2.getInvoiceNo()==null){
							
						}else{
							ismonthFlagHasInvoiceNo=true;
							int x =Integer.parseInt(subAppointment.getInvoiceNo().substring(4, 8));
							if(x>y){
								System.out.println("1075:"+subAppointment.getInvoiceNo());
								subAppointment3=subAppointment;
								y=x;
							}
						
						}
				}
				if(ismonthFlagHasInvoiceNo==false){
					String year=Integer.toString(dbSubAppointment.getYear()).substring(2);
					String invoiceNo=dbSubAppointment.getMonth().toString().substring(0, 3)+"/"+"0001"+"/"+year;
					System.out.println("invoiceNo2:"+invoiceNo);
					dbSubAppointment.setInvoiceNo(invoiceNo);
				}else{
					// appointments in month for patient
					List<SubAppointment> subAppointments1=subAppointmentRepository.findByMonthAndYearAndPatientId(dbSubAppointment.getMonth(),dbSubAppointment.getYear(),dbSubAppointment.getPatient().getId());
					SubAppointment subAppointment4=null;
					for(SubAppointment subAppointment:subAppointments1){
						System.out.println("1093:"+subAppointment.getId());
						System.out.println("1094:"+dbSubAppointment.getId());

						if(subAppointment.getInvoiceNo()==null){
							System.out.println("invoice is null");
						}else{
							/*flag=true;
							subAppointment4=subAppointment;*/
							/////
							flag=true;
							int a =Integer.parseInt(subAppointment.getInvoiceNo().substring(4, 8));
							if(a>b){
								System.out.println("1075:"+subAppointment.getInvoiceNo());
								subAppointment4=subAppointment;
							}
							 b=a;
						/////////
						
						}
						//
					}
					if(flag==true){
						dbSubAppointment.setInvoiceNo(subAppointment4.getInvoiceNo());
						System.out.println("1118:"+subAppointment4.getInvoiceNo());
					}else{
						System.out.println("1050:"+subAppointment3.getInvoiceNo());
						System.out.println("1088:"+subAppointment3.getInvoiceNo().substring(4, 8));
						int a=Integer.parseInt(subAppointment3.getInvoiceNo().substring(4, 8))+1;
						String number=String.format("%04d", a);
						String year=Integer.toString(dbSubAppointment.getYear()).substring(2);
						String invoiceNo=dbSubAppointment.getMonth().toString().substring(0, 3)+"/"+number+"/"+year;
						System.out.println("invoiceNo 1056:"+invoiceNo);
						dbSubAppointment.setInvoiceNo(invoiceNo);
						}
				}
			}
			subAppointmentRepository.save(dbSubAppointment);
			
			/*Mail mail = new Mail();
			mail.postMail(dbSubAppointment.getDoctor().getEmail(), "Appointment  was completed !",
					" your Appointment  was completed with patient " + dbSubAppointment.getPatient().getFirstName()
							+ " has been done at " + dbSubAppointment.getAppointmentStartedDate() + "  "
							+ dbSubAppointment.getAppointmentStartTime());
			if (dbSubAppointment.getPatient().getEmail() != null) {
				mail.postMail(dbSubAppointment.getPatient().getEmail(), "Appointment  was completed !",
						" your Appointment  was completed with doctor " + dbSubAppointment.getDoctor().getFirstName()
								+ " has been done at " + dbSubAppointment.getAppointmentStartedDate() + "  "
								+ dbSubAppointment.getAppointmentStartTime());*/
			Mail mail = new Mail();
			mail.postMail(dbSubAppointment.getDoctor().getEmail(), "Appointment  was completed !",
					" Today, You saw patient " + dbSubAppointment.getPatient().getFirstName()+ dbSubAppointment.getPatient().getLastName()
					+ " at " + dbSubAppointment.getAppointmentStartedDate() + "  "
					+ dbSubAppointment.getAppointmentStartTime()+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
			if (dbSubAppointment.getPatient().getEmail() != null) {
				mail.postMail(dbSubAppointment.getPatient().getEmail(), "Appointment  was completed !",
						" Today, we saw patient " + dbSubAppointment.getPatient().getFirstName()+ dbSubAppointment.getPatient().getLastName()
								+ " at " + dbSubAppointment.getAppointmentStartedDate() + "  "
								+ dbSubAppointment.getAppointmentStartTime()+" If you have any questions regarding the session, feel free to contact the therapist @"
								+dbSubAppointment.getDoctor().getEmail()+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
			}
			}
		}
		subAppointmentRepository.save(dbSubAppointment);
	}
	/*@Override
	public List<PatientInvoiceDto> getSubAppointmentsByInvoice(InvoiceSubAppointmentDto appointmentDto ){
		List<SubAppointment> subAppointments=null;
		List<PatientInvoiceDto> invoiceDtos=new ArrayList<>();
		
		Set<Patient> patients=new LinkedHashSet<>();
		if(appointmentDto.getDoctor()!=null&&appointmentDto.getStartDate()!=null){
		 subAppointments=subAppointmentRepository.findByDoctor_IdAndActiveAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
				appointmentDto.getDoctor().getId(),true,appointmentDto.getStartDate(),appointmentDto.getEndDate());
		}else if(appointmentDto.getDoctor()!=null){
			 subAppointments=subAppointmentRepository.findByDoctor_IdAndActive(appointmentDto.getDoctor().getId(), true);
		}else{
			 subAppointments=subAppointmentRepository.findByActiveAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
						true,appointmentDto.getStartDate(),appointmentDto.getEndDate());
		}
		for(SubAppointment subAppointment:subAppointments){
			patients.add(subAppointment.getPatient());
		}
		for(Patient patient:patients){
			PatientInvoiceDto invoiceDto=new PatientInvoiceDto();
			invoiceDto.setPatient(patient);
		List<Payment> payments=paymentRepository.findByPatientId(patient.getId());
		for(Payment payment:payments){
			invoiceDto.setTotalAmount(payment.getTotalAmount());
			invoiceDto.setRemainingAmount(payment.getRemainingAmount());
		}
		List<SubAppointment> appointments=subAppointmentRepository.findByPatient_IdAndActive(patient.getId(), true);
		for(SubAppointment subAppointment:appointments){
			if(subAppointment.getInvoiceNo()!=null){
			invoiceDto.setInvoice(subAppointment.getInvoiceNo());
			}
			invoiceDto.setDoctor(subAppointment.getDoctor());
		}
		invoiceDtos.add(invoiceDto);
		}
		
		return invoiceDtos;
	}*/
	@Override
	public List<PatientInvoiceDto> getSubAppointmentsByInvoice(InvoiceSubAppointmentDto appointmentDto ){
		Person person=personRepository.findByEmail(appointmentDto.getAdminUsername());
		
		List<SubAppointment> subAppointments=null;
		List<PatientInvoiceDto> invoiceDtos=new ArrayList<>();
		if(appointmentDto.getDoctor()!=null&&appointmentDto.getStartDate()!=null){
		 subAppointments=subAppointmentRepository.findByDoctor_IdAndActiveAndStatusAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
				appointmentDto.getDoctor().getId(),true,SubAppointmentStatus.COMPLETE,appointmentDto.getStartDate(),appointmentDto.getEndDate());
		}else if(appointmentDto.getDoctor()!=null){
			 subAppointments=subAppointmentRepository.findByDoctor_IdAndActiveAndStatus(appointmentDto.getDoctor().getId(), true,SubAppointmentStatus.COMPLETE);
		}else if(appointmentDto.getStartDate()!=null&&!person.getUserAccount().getRole().getRoleName().equals("Therapist")){
			 subAppointments=subAppointmentRepository.findByActiveAndStatusAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
						true,SubAppointmentStatus.COMPLETE,appointmentDto.getStartDate(),appointmentDto.getEndDate());
		}else if(person.getUserAccount().getRole().getRoleName().equals("Therapist")&&appointmentDto.getStartDate()!=null){
			subAppointments=subAppointmentRepository.findByDoctor_IdAndActiveAndStatusAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
					person.getId(),true,SubAppointmentStatus.COMPLETE,appointmentDto.getStartDate(),appointmentDto.getEndDate());
		}
			else{
			System.out.println(appointmentDto.getAdminUsername());
			
			if(person.getUserAccount().getRole().getRoleName().equals("Therapist")){
				 subAppointments=subAppointmentRepository.findByDoctor_IdAndActiveAndStatus(person.getId(), true,SubAppointmentStatus.COMPLETE);
			}else{
				subAppointments=subAppointmentRepository.findByPerson_IdAndActiveAndStatus(person.getId(),true,SubAppointmentStatus.COMPLETE);	
			}
			
		}
		String invoice=null;
		
		
		
		for(SubAppointment subAppointment:subAppointments){
			if(subAppointment.getPatient().getRegionalCenter()==null){
			double totalPaidAmount = 0;
			System.out.println("Id::"+subAppointment.getId());
			System.out.println(subAppointment.getPatient().getEmail());
			//List<Payment> payments=paymentRepository.findByPatientIdAndMonthAndYear(subAppointment.getPatient().getId(),subAppointment.getAppointmentStartedDate().getMonth(),subAppointment.getAppointmentStartedDate().getYear());
			List<Payment> payments=paymentRepository.findByPatient_Id(subAppointment.getPatient().getId());
			ModeOfPaymentType modeOfPaymentType=null;
			Paymethod paymentType=null;
			double assgnAmt= 0;
			for(Payment payment:payments){
				System.out.println(payment.getPatient().getEmail());
				 modeOfPaymentType=payment.getModeOfPaymentTypes();
				 System.out.println(modeOfPaymentType);
				 assgnAmt= payment.getAssignedAmount();
				/* if(payment.getPaidAmount()!=null){
				totalPaidAmount = totalPaidAmount + payment.getPaidAmount();
				 }*/
				 paymentType=payment.getPaymentTypes();
			}
			List<SubAppointment> singleSubAppointment=null;
			if(modeOfPaymentType!=null){
			if(modeOfPaymentType.equals(ModeOfPaymentType.WEEKLY)){
				PatientInvoiceDto invoiceDto=new PatientInvoiceDto();
				Appointment appointment=appointmentRepository.findOne(subAppointment.getAppointment().getId());
				invoiceDto.setAppointment(appointment);
				invoiceDto.setCreatedDate(subAppointment.getCreatedDate());
				invoiceDto.setPaidDate(subAppointment.getPaidDate());
				invoiceDto.setPatient(subAppointment.getPatient());
				singleSubAppointment=new ArrayList<>();
				singleSubAppointment.add(subAppointment);
				invoiceDto.setSubAppointments(singleSubAppointment);
				invoiceDto.setDoctor(subAppointment.getDoctor());
				invoiceDto.setInvoice(subAppointment.getInvoiceNo());
				invoiceDto.setStarDate(subAppointment.getAppointmentStartedDate());
				invoiceDto.setDueDate(invoiceDto.getStarDate().plusDays(7));
				invoiceDto.setPaymentTypes(paymentType);
				int count=0;
				int counter=0;
				double totalPaidAmount1 = 0;
				if(subAppointment.getPaidStatus()!=null){
				if(subAppointment.getPaidStatus().equals(PaidStatus.DUE)){
					count++;
				}else if(subAppointment.getPaidStatus().equals(PaidStatus.PAID)){
					counter++;
					if(subAppointment.getAssignAmount()!=null){
					totalPaidAmount1 = totalPaidAmount + subAppointment.getAssignAmount();	
					}
				}
				
				invoiceDto.setPaidAmount(totalPaidAmount1);
				invoiceDto.setRemainingAmount(count*assgnAmt);
				invoiceDto.setTotalAmount(invoiceDto.getPaidAmount()+invoiceDto.getRemainingAmount());
				
				
				if(invoiceDto!=null){
				invoiceDtos.add(invoiceDto);
				}
				}
			}else{
				List<SubAppointment> appointments=subAppointmentRepository.findByPatient_IdAndActiveAndStatusAndMonthAndYear(subAppointment.getPatient().getId(),
						true, SubAppointmentStatus.COMPLETE, subAppointment.getAppointmentStartedDate().getMonth(),subAppointment.getAppointmentStartedDate().getYear());
				PatientInvoiceDto invoiceDto=new PatientInvoiceDto();
				
				int count=0;
				int counter=0;
				//invoiceDto.setSubAppointments(appointments);
				for(SubAppointment appointmentSub:appointments){
					Appointment appointment=appointmentRepository.findOne(appointmentSub.getAppointment().getId());
					invoiceDto.setAppointment(appointment);
					invoiceDto.setCreatedDate(appointmentSub.getCreatedDate());
					if(appointmentSub.getPaidDate()!=null){
					invoiceDto.setPaidDate(appointmentSub.getPaidDate());
					}
					System.out.println(appointmentSub.getPatient().getEmail());
					System.out.println(appointmentSub.getId());
					invoiceDto.setSubAppointments(appointments);
					invoiceDto.setPatient(appointmentSub.getPatient());
					System.out.println(appointmentSub.getDoctor().getEmail());
					System.out.println(appointmentSub.getPatient().getEmail());
					invoiceDto.setDoctor(appointmentSub.getDoctor());
					System.out.println("1238::"+appointmentSub.getInvoiceNo());
					invoiceDto.setInvoice(appointmentSub.getInvoiceNo());
					invoiceDto.setStarDate(appointmentSub.getAppointmentStartedDate());
					invoiceDto.setDueDate(invoiceDto.getStarDate().plusDays(7));
					invoiceDto.setPaymentTypes(paymentType);
					//invoiceDto.setPaidAmount(totalPaidAmount);
					if(appointmentSub.getPaidStatus()!=null){
						
					
					if(appointmentSub.getPaidStatus().equals(PaidStatus.DUE)){
						count++;
					}else if(appointmentSub.getPaidStatus().equals(PaidStatus.PAID)){
						counter++;
						if(appointmentSub.getAssignAmount()!=null){
						totalPaidAmount = totalPaidAmount + appointmentSub.getAssignAmount();	
						}
					}
					}
			}
				System.out.println(totalPaidAmount);
				invoiceDto.setPaidAmount(totalPaidAmount);
				invoiceDto.setRemainingAmount(count*assgnAmt);
				invoiceDto.setTotalAmount(invoiceDto.getPaidAmount()+invoiceDto.getRemainingAmount());
				if(invoiceDto.getPatient()!=null){
				if(invoiceDto.getInvoice()!=null){
					System.out.println(invoiceDto.getInvoice());
				if(invoice!=invoiceDto.getInvoice()){
				invoiceDtos.add(invoiceDto);
				}}}
				 invoice=invoiceDto.getInvoice();
			}
			}
		}	
		}
		System.out.println(invoiceDtos.size());
		
		return invoiceDtos;
	}
	@Override
	public Page<PatientInvoiceDto> getSubAppointmentsByInvoicePage(InvoiceSubAppointmentDto appointmentDto, int page, int size ){
		List<PatientInvoiceDto> patientsList =getSubAppointmentsByInvoice(appointmentDto);
		/*Set<Patient> list=searchPatient(patient);
		patientsList.addAll(list);*/
		int start =  new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > patientsList.size() ? patientsList.size() : (start + new PageRequest(page, size).getPageSize());
		
		return new PageImpl<PatientInvoiceDto>(patientsList.subList(start, end), new PageRequest(page, size,Sort.Direction.DESC,"id"), patientsList.size());
	}
	
	public List<PatientInvoiceDto> getSubAppointmentsByInvoice(String usrname ){
		
		
		return null;
	}
	@Override
	public List<PatientInvoiceDto> getAllSubAppointmentsByInvoice(String username){
       List<PatientInvoiceDto> invoiceDtos=new ArrayList<>();
		Set<Patient> patients=new LinkedHashSet<>();
		UserAccount userAccount=userAccountRepository.findByUsername(username);
		if(userAccount==null){
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		Person person=personRepository.findByEmail(username);
		if(person==null){
			throw new RuntimeException(ErrorMessageHandler.adminDoesNotExists);
		}
		List<SubAppointment> subAppointments=subAppointmentRepository.findByPerson_IdAndActive(person.getId(), true);
		System.out.println("subAppointments size:"+subAppointments.size());
		for(SubAppointment subAppointment:subAppointments){
			patients.add(subAppointment.getPatient());
		}
		System.out.println("patients size:"+patients.size());
		for(Patient patient:patients){
			PatientInvoiceDto invoiceDto=new PatientInvoiceDto();
			invoiceDto.setPatient(patient);
		List<Payment> payments=paymentRepository.findByPatient_Id(patient.getId());
		for(Payment payment:payments){
			invoiceDto.setTotalAmount(payment.getTotalAmount());
			invoiceDto.setRemainingAmount(payment.getRemainingAmount());
		}
		List<SubAppointment> appointments=subAppointmentRepository.findByPatient_IdAndActive(patient.getId(), true);
		for(SubAppointment subAppointment:appointments){
			if(subAppointment.getInvoiceNo()!=null){
			invoiceDto.setInvoice(subAppointment.getInvoiceNo());
			}
			invoiceDto.setDoctor(subAppointment.getDoctor());
		}
		invoiceDtos.add(invoiceDto);
		}
		
		return invoiceDtos;
	}
	@Override
	public Page<PatientInvoiceDto> getAllSubAppointmentsByInvoicePage(String username, int page, int size){
		List<PatientInvoiceDto> patientsList =getAllSubAppointmentsByInvoice(username);
		/*Set<Patient> list=searchPatient(patient);
		patientsList.addAll(list);*/
		int start =  new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > patientsList.size() ? patientsList.size() : (start + new PageRequest(page, size).getPageSize());
		
		return new PageImpl<PatientInvoiceDto>(patientsList.subList(start, end), new PageRequest(page, size,Sort.Direction.DESC,"id"), patientsList.size());
	}
	@Override
	public List<SubAppointment> getAllSubAppointmentsByAppointment(Long id){
		List<SubAppointment> subAppointments=subAppointmentRepository.findByAppointment_Id(id);
		return subAppointments;
	}
	@Override
	public List<ItemDto> getSubAppointmentsByInvoiceNo(AppointmentInvoiceDto appointmentInvoiceDto){
		List<SubAppointment> subAppointments=subAppointmentRepository.findByActiveAndInvoiceNoAndPatient_Id(true, appointmentInvoiceDto.getInvoiceNumber(),appointmentInvoiceDto.getPatientId());
		boolean flag=false;
		double assgnAmt = 0;
		List<ItemDto> itemDtos=new ArrayList<>();
		for(SubAppointment subAppointment:subAppointments){
			ItemDto itemDto=new ItemDto();
			if(flag==false){
				List<Payment> payments=paymentRepository.findByPatient_Id(subAppointment.getPatient().getId());
				for(Payment payment:payments){
					 assgnAmt= payment.getAssignedAmount();
				}
			}
			itemDto.setAppointmentStartedDate(subAppointment.getAppointmentStartedDate());
			itemDto.setItemName("Speech Therapy");
			if(subAppointment.getAssignAmount()!=null){
				itemDto.setPrice(subAppointment.getAssignAmount());	
			}else{
				itemDto.setPrice(assgnAmt);
			}
			itemDtos.add(itemDto);
		}
		/*double totalAmount=0;
		for(ItemDto itemDto:itemDtos){
			totalAmount=totalAmount+itemDto.getPrice();
		}
		for(ItemDto itemDto:itemDtos){
			itemDto.setTotalAmount(totalAmount);
		}*/
		return itemDtos;
	}
	@Override
	public Page<Appointment> findAllTherapistAppointments(Long id, int page, int size){
		Page<Appointment> appointments=appointmentRepository.findByDoctor_IdAndAppointmentEndedDateGreaterThanEqualAndActive
				(id, LocalDate.now(), true, new PageRequest(page, size,Sort.Direction.DESC,"id"));
				
		return appointments;
	}
	
	@Override
	public void updateAppointmentWithAssignedTherapist(ScheduleDtoForCalendar scheduleDtoForCalendar ){
		//List<Appointment> appointments=appointmentRepository.findByDoctor_IdAndActive(oldDoctor.getId(),true);
  		//for(Appointment appointment:appointments){
		Appointment appointment=appointmentRepository.findOne(scheduleDtoForCalendar.getAppointmentId());
		/*if(1>0){
			throw new RuntimeException("test");
		}*///check cheyali Subs kaliga unnayo levo?
		List<LocalDate> dates=new ArrayList<>();
  			List<SubAppointment> subAppointments=subAppointmentRepository.findByAppointment_Id(appointment.getId());
  			for(SubAppointment subAppointment:subAppointments){
  				if(subAppointment.getStatus().equals(SubAppointmentStatus.AWAITING)&&subAppointment.getAppointmentStartedDate().isAfter(LocalDate.now())){
  				LocalDate date=subAppointment.getAppointmentEndedDate();
  				dates.add(date);
  				}
  			}
  			/////////
  			for (LocalDate appointedDate : dates) {
				SubAppointment subappointment = subAppointmentRepository
						.findByAppointmentStartTimeGreaterThanEqualAndAppointmentEndTimeLessThanEqualAndAppointMentStatusAndAppointmentStartedDateAndActiveAndDoctor_Id(
								 appointment.getAppointmentStartTime(),
								appointment.getAppointmentEndTime(),
								AppointMentStatus.APPOINTEMENT, appointedDate, true,scheduleDtoForCalendar.getDoctor().getId());
				if (subappointment!=null) {
					throw new RuntimeException(ErrorMessageHandler.sorryAppointmentAlreadyScheduledOnThisDate+" Just Now");
				}
			}
  			//////
  			
  			boolean isPatientHasAwaitApps=false;
  			for(SubAppointment subAppointment:subAppointments){
  				System.out.println(subAppointment.getPatient().getEmail());
  				System.out.println(subAppointment.getAppointmentStartedDate());
  				System.out.println(subAppointment.getStatus());
  				
  				if(subAppointment.getStatus().equals(SubAppointmentStatus.AWAITING)&&subAppointment.getAppointmentStartedDate().isAfter(LocalDate.now())){
  					subAppointment.setDoctor(scheduleDtoForCalendar.getDoctor());
  					subAppointmentRepository.save(subAppointment);
  					isPatientHasAwaitApps=true;
  				}
  			}
  			if(isPatientHasAwaitApps==true){
  				appointment.setDoctor(scheduleDtoForCalendar.getDoctor());
  				appointmentRepository.save(appointment);
  				List<Payment> dbPaymentsList=paymentRepository.findByPatient_Id(appointment.getPatient().getId());
  	      		Payment payment = null;
  				for(Payment dbPayment:dbPaymentsList){
  					
  						payment=dbPayment;
  						System.out.println(payment);
  				}
  				//error
  				payment.setDoctor(scheduleDtoForCalendar.getDoctor());
  				paymentRepository.save(payment);
  		}
	}
}
