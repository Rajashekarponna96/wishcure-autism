package com.openspace.HospitalMgnt;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.openspace.HospitalMgnt.common.CORSSecurityFilter;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.PatientMgnt.Repositories.SubAppointmentRepository;

@SpringBootApplication
@EntityScan(basePackages = { "com.openspace.Model.*" })
@ComponentScan(basePackages = { "com.openspace.*" }, basePackageClasses = { CORSSecurityFilter.class })
 /*@EnableWebSecurity */
@Configuration
@EnableScheduling
public class HospitalMgntApplication {
	public static void main(String[] args) {
		SpringApplication.run(HospitalMgntApplication.class, args);
	}

	@Autowired
	private SubAppointmentRepository subAppointmentRepository;

	@Scheduled(cron = "0 1 12 * * ?")
	public void subAppointmentRemainderScheduler() {
		List<SubAppointment> subAppointments = subAppointmentRepository.getActiveSubAppointments(true, LocalDate.now());
		for (SubAppointment subAppointment : subAppointments) {
			if (subAppointment.getAppointmentEndedDate().minusDays(1).equals(LocalDate.now())) {
				/*Mail mail = new Mail();
				mail.postMail(subAppointment.getDoctor().getEmail(), "Appointment Schedule Alert",
						" Tommarow you have  Appointment  with patient " + subAppointment.getPatient().getFirstName()
								+ " " + subAppointment.getPatient().getLastName() + " has been scheduled at "
								+ subAppointment.getAppointmentStartTime());
				if (subAppointment.getPatient().getEmail() != null) {
					mail.postMail(subAppointment.getPatient().getEmail(), "Appointment Saved Successfully !",
							" Tommarow you have  Appointment with doctor " + subAppointment.getDoctor().getFirstName()
									+ " " + subAppointment.getDoctor().getLastName() + " has been scheduled at "
									+ subAppointment.getAppointmentStartTime());*/
				
				Mail mail = new Mail();
				mail.postMail(subAppointment.getDoctor().getEmail(), "Appointment Schedule Alert",
						" This is a reminder that patient " + subAppointment.getPatient().getFirstName()
								+ " " + subAppointment.getPatient().getLastName() + " has been scheduled at "
								+ subAppointment.getAppointmentStartedDate() + "  "	+ subAppointment.getAppointmentStartTime()+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
				if (subAppointment.getPatient().getEmail() != null) {
					mail.postMail(subAppointment.getPatient().getEmail(), "Appointment Saved Successfully !",
					" This is a reminder that Therapist " + subAppointment.getDoctor().getFirstName()
					+ " " + subAppointment.getDoctor().getLastName() + " has been scheduled at "
					+ subAppointment.getAppointmentStartedDate() + "  "	+ subAppointment.getAppointmentStartTime()+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
	
				}
			}
		}
	}

	@Scheduled(cron = "0 1 12 * * ?")
	public void subAppointmentRemainderSchedulerBeforeTwoHour() {
		List<SubAppointment> subAppointments = subAppointmentRepository.getActiveSubAppointments(true, LocalDate.now());
		for (SubAppointment subAppointment : subAppointments) {
			if (subAppointment.getAppointmentEndedDate().minusDays(1).equals(LocalDate.now())) {
				if (subAppointment.getAppointmentStartTime().minusHours(2)
						.equals(LocalTime.parse(LocalTime.now().toString().substring(0, 5)))) {
					/*Mail mail = new Mail();
					mail.postMail(subAppointment.getDoctor().getEmail(), "Appointment Schedule Alert",
							" Today you have  Appointment  with patient " + subAppointment.getPatient().getFirstName()
									+ " " + subAppointment.getPatient().getLastName() + " has been scheduled at "
									+ subAppointment.getAppointmentStartTime());
					if (subAppointment.getPatient().getEmail() != null) {
						mail.postMail(subAppointment.getPatient().getEmail(), "Appointment Saved Successfully !",
								" Today you have  Appointment with doctor " + subAppointment.getDoctor().getFirstName()
										+ " " + subAppointment.getDoctor().getLastName() + " has been scheduled at "
										+ subAppointment.getAppointmentStartTime());*/
					
					Mail mail = new Mail();
					mail.postMail(subAppointment.getDoctor().getEmail(), "Appointment Schedule Alert",
							" This is a reminder that patient " + subAppointment.getPatient().getFirstName()
									+ " " + subAppointment.getPatient().getLastName() + " has been scheduled at "
									+ subAppointment.getAppointmentStartedDate() + "  "	+ subAppointment.getAppointmentStartTime()+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
					if (subAppointment.getPatient().getEmail() != null) {
						mail.postMail(subAppointment.getPatient().getEmail(), "Appointment Saved Successfully !",
						" This is a reminder that Therapist " + subAppointment.getDoctor().getFirstName()
						+ " " + subAppointment.getDoctor().getLastName() + " has been scheduled at "
						+ subAppointment.getAppointmentStartedDate() + "  "	+ subAppointment.getAppointmentStartTime()+"<br><br><br><br>Cheers, <br>ezClinic Team. ");
		
					}
				}
			}
		}
	}

	public void subAppointmentRemainderzcsdfdSchedulerBeforeTwoHour() {
		String body=""+"<div class=\"col-sm-12\">"
	+"<div class=\"col-sm-2\">"
				+"&nbsp;"+"</div>"
	+"<div class=\"col-sm-8 col-sm-offset-2 border\">"
				+"<h1 align=\"center\">"
	+"Your booking is confirmed</h1>"+"<label>Dear,Pathi</label><p>Your booking is confirmed. See below for more information.If the event is not already in your calendar,please use the calendar links provided below to add it.</p>"
				+"<hr><h2>Booking details</h2><h3>Subject</h3><p>FREE Live Demo with Matt at ClinicSource.com</p><h3>Time</h3>"
	+"<p>Tue, Jan 30, 2018, 02:30am - 03:15am India; New Delhi,Bangalore, Kolkata, Chennai, Mumbai (GMT+5:30)</p></div></div>";
	}
}
