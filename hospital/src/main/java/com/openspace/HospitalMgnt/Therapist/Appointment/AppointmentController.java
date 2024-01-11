package com.openspace.HospitalMgnt.Therapist.Appointment;

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
import com.openspace.Model.DoctorManagement.Appointment;
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
@RestController
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(value = RestURIConstants.SAVE_APPOINTMENT, method = RequestMethod.POST)
	public @ResponseBody void saveAppointment(@RequestBody AppointmentDto appoointmentDto) {
		appointmentService.saveAppointment(appoointmentDto);
	}

	@RequestMapping(value = RestURIConstants.GET_APPOINTS_BY_ROLE_PAGE, method = RequestMethod.GET )
	public @ResponseBody Page<SubAppointment> getAppointmentssByRole(@PathVariable String adminUserName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return appointmentService.getAppointmentssByRole(adminUserName,page,size);
	}
	@RequestMapping(value = RestURIConstants.GET_APPOINTSS_BY_ROLE_PAGE, method = RequestMethod.GET )
	public @ResponseBody Page<Appointment> getAppointmentsByRole(@PathVariable String adminUserName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return appointmentService.getAppointmentsByRole(adminUserName,page,size);
	}
	@RequestMapping(value = RestURIConstants.GET_APPOINTS_BY_ROLE, method = RequestMethod.GET )
	public @ResponseBody List<Appointment> getAppointmentssByRole(@PathVariable String adminUserName) {
		return appointmentService.getAppointmentssByRole(adminUserName);
	}
	
	@RequestMapping(value = RestURIConstants.GET_APPOINTS_COUNT_BY_ROLE, method = RequestMethod.GET)
	public @ResponseBody int getAllAppointmentsCountByRole(@PathVariable String adminUserName) {
		return appointmentService.getAllAppointmentsCountByRole(adminUserName);
	}
	
	@RequestMapping(value = RestURIConstants.UPDATE_APPOINTMENT, method = RequestMethod.PUT)
	public @ResponseBody void updateAppointment(@RequestBody Appointment appoointmentDto) {
		appointmentService.updateAppointment(appoointmentDto);
	}
	
	@RequestMapping(value = RestURIConstants.GET_APPOINTMENT_LIST_BY_BETWEEN_DATES, method = RequestMethod.POST)
	public @ResponseBody Page<SubAppointment> getAppointlistByDates(@RequestBody AppointmentsDateDto appointmentsDateDto,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return appointmentService.getAppointlistByDates(appointmentsDateDto,page,size);
	}
	
	
	@RequestMapping(value = RestURIConstants.DELETE_APPOINTMENT, method = RequestMethod.DELETE)
	public @ResponseBody void deleteAppointment(@PathVariable("id") Long id) {
		 appointmentService.deleteAppointment(id);
	}
	
	@RequestMapping(value = RestURIConstants.GET_SUB_APPOINTS_BY_ROLE, method = RequestMethod.GET )
	public @ResponseBody List<SubAppointmentDto> getSubAppointmentssByRole(@PathVariable String adminUserName) {
		return appointmentService.getSubAppointmentssByRole(adminUserName);
	}
	
	@RequestMapping(value = RestURIConstants.GET_SUB_APPOINTS_BY_ROLE_FOR_CALENDARVIEW, method = RequestMethod.GET )
	public @ResponseBody List<SubAppointmentDto> getSubAppointmentssByRoleForCalendarView(@PathVariable String adminUserName) {
		return appointmentService.getSubAppointmentssByRoleForCalendarView(adminUserName);
	}
	@RequestMapping(value = RestURIConstants.GET_TODAY_SUB_APPOINTS, method = RequestMethod.GET )
	public @ResponseBody List<SubAppointmentDto> getTodayAppointments(@PathVariable String adminUserName) {
		return appointmentService.getTodayAppointments(adminUserName);
	}
	@RequestMapping(value = RestURIConstants.UPDATE_SUBAPPOINTMENT_STATUS, method = RequestMethod.POST)
	public @ResponseBody void updateSubappointmentStatus(@PathVariable	Long id,@PathVariable SubAppointmentStatus status) {
		appointmentService.updateSubappointmentStatus(id,status);
	}
	@RequestMapping(value=RestURIConstants.GET_SUBAPPOINTMENTS_BY_INVOICE,method=RequestMethod.POST)
	public List<PatientInvoiceDto> getSubAppointmentsByInvoice(@RequestBody InvoiceSubAppointmentDto dto){
		return appointmentService.getSubAppointmentsByInvoice(dto);
	}
	
	@RequestMapping(value=RestURIConstants.GET_SUBAPPOINTMENTS_BY_INVOICE_PAGE,method=RequestMethod.POST)
	public Page<PatientInvoiceDto> getSubAppointmentsByInvoicePage(@RequestBody InvoiceSubAppointmentDto dto,@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size){
		return appointmentService.getSubAppointmentsByInvoicePage(dto, page, size);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_SUBAPPOINTMENTS_BY_INVOICE, method = RequestMethod.GET )
	public @ResponseBody List<PatientInvoiceDto> getAllSubAppointmentsByInvoice(@PathVariable String adminUserName) {
		return appointmentService.getAllSubAppointmentsByInvoice(adminUserName);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_SUBAPPOINTMENTS_BY_INVOICE_PAGE, method = RequestMethod.GET )
	public @ResponseBody Page<PatientInvoiceDto> getAllSubAppointmentsByInvoicePage(@PathVariable String adminUserName,@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return appointmentService.getAllSubAppointmentsByInvoicePage(adminUserName, page, size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_SUBAPPOINTMENTS_BY_APPOINTMENT, method = RequestMethod.GET )
	public @ResponseBody List<SubAppointment> getAllSubAppointmentsByAppointment(@PathVariable Long id) {
		return appointmentService.getAllSubAppointmentsByAppointment(id);
	}
	@RequestMapping(value = RestURIConstants.GET_SUBAPPOINTMENTS_BY_INVOICENO, method = RequestMethod.POST )
	public @ResponseBody List<ItemDto> getSubAppointmentsByInvoiceNo(@RequestBody AppointmentInvoiceDto appointmentInvoiceDto) {
		return appointmentService.getSubAppointmentsByInvoiceNo(appointmentInvoiceDto);
	}
	
	@RequestMapping(value = RestURIConstants.FIND_ALL_THERAPIST_APPOINTMENTS, method = RequestMethod.GET )
	public @ResponseBody Page<Appointment> findAllTherapistAppointments(@PathVariable Long id,@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return appointmentService.findAllTherapistAppointments(id, page, size);
	}
	
	@RequestMapping(value = RestURIConstants.UPDATE_APPOINTMENT_WITH_ASSIGNEDTHERAPIST, method = RequestMethod.POST)
	public @ResponseBody void updateAppointmentWithAssignedTherapist(@RequestBody ScheduleDtoForCalendar scheduleDtoForCalendar) {
		appointmentService.updateAppointmentWithAssignedTherapist(scheduleDtoForCalendar);
	}
}
