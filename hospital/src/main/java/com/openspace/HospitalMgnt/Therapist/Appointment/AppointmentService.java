package com.openspace.HospitalMgnt.Therapist.Appointment;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.AppointmentInvoice;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.DoctorManagement.SubAppointmentStatus;
import com.openspace.Model.Dto.AppointmentDto;
import com.openspace.Model.Dto.AppointmentInvoiceDto;
import com.openspace.Model.Dto.AppointmentsDateDto;
import com.openspace.Model.Dto.InvoiceSubAppointmentDto;
import com.openspace.Model.Dto.ItemDto;
import com.openspace.Model.Dto.PatientInvoiceDto;
import com.openspace.Model.Dto.ScheduleDtoForCalendar;
import com.openspace.Model.Dto.SubAppointmentDto;

public interface AppointmentService {
	void saveAppointment(AppointmentDto appoointmentDto);

	Page<SubAppointment> getAppointmentssByRole(String adminUserName, int page, int size);

	List<Appointment> getAppointmentssByRole(String adminUserName);

	int getAllAppointmentsCountByRole(String adminUserName);

	// void updateAppointment(AppointmentDto appoointmentDto);

	void updateAppointment(Appointment appoointmentDto);

	void deleteAppointment(Long id);

	Page<SubAppointment> getAppointlistByDates(AppointmentsDateDto appointmentsDateDto, int page, int size);

	List<SubAppointmentDto> getSubAppointmentssByRole(String adminUsername);

	List<SubAppointmentDto> getSubAppointmentssByRoleForCalendarView(String adminUserName);

	void updateSubappointmentStatus(Long id, SubAppointmentStatus status);

	List<SubAppointmentDto> getTodayAppointments(String adminUserName);
	
	List<PatientInvoiceDto> getSubAppointmentsByInvoice(InvoiceSubAppointmentDto appointmentDto );

	List<PatientInvoiceDto> getAllSubAppointmentsByInvoice(String username);

	Page<PatientInvoiceDto> getAllSubAppointmentsByInvoicePage(String username, int page, int size);

	Page<Appointment> getAppointmentsByRole(String adminUserName, int page, int size);

	List<SubAppointment> getAllSubAppointmentsByAppointment(Long id);

	List<ItemDto> getSubAppointmentsByInvoiceNo(AppointmentInvoiceDto invoiceNo);

	Page<Appointment> findAllTherapistAppointments(Long id, int page, int size);

	void updateAppointmentWithAssignedTherapist(ScheduleDtoForCalendar scheduleDtoForCalendar);

	Page<PatientInvoiceDto> getSubAppointmentsByInvoicePage(InvoiceSubAppointmentDto appointmentDto, int page,int size);
			
}
