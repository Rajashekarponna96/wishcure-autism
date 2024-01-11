package com.openspace.HospitalMgnt.Therapist.Holidays;

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
import com.openspace.Model.DoctorManagement.Holiday;


@RestController
public class HolidaysController {

	@Autowired
	private HolidaysService holidaysService;
	
	@RequestMapping(value=RestURIConstants.SAVE_HOLIDAYS, method=RequestMethod.POST)
	public @ResponseBody void saveHolidaysService(@RequestBody HolidayDto holidayDto ){
		holidaysService.saveHolidaysService(holidayDto);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_HOLIDAYS, method=RequestMethod.GET)
	public @ResponseBody List<Holiday> getAllHolidays(){
		return holidaysService.getAllHolidays();
	}
	
	@RequestMapping(value=RestURIConstants.UPDATE_HOLIDAYS, method=RequestMethod.PUT)
	public @ResponseBody void updateHolidaysService(@RequestBody Holiday holiday){
		holidaysService.updateHolidays(holiday);
	}
	@RequestMapping(value=RestURIConstants.DELETE_HOLIDAYS,method=RequestMethod.DELETE)
	public @ResponseBody void deleteHolidaysService(@PathVariable Long id){
		holidaysService.deleteHolidays(id);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_HOLIDAYS_BY_DOCTOR_USERNAME_PAGE,method=RequestMethod.GET)
	public @ResponseBody Page<Holiday> getAllHolidaysByDoctorUsername(@PathVariable String doctorUsername,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size){
		return holidaysService.getAllHolidaysByDoctorUsername(doctorUsername,page,size);
	}	
	
	@RequestMapping(value=RestURIConstants.GET_ALL_HOLIDAYS_BY_DOCTOR_USERNAME,method=RequestMethod.GET)
	public @ResponseBody List<Holiday> getAllHolidaysByDoctorUsername(@PathVariable String doctorUsername){
		return holidaysService.getAllHolidaysByDoctorUsername(doctorUsername);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_HOLIDAYS_BY_ADMIN_USERNAME,method=RequestMethod.GET)
	public @ResponseBody List<Holiday> getAllTherapistHolidaysByAdminUsername(@PathVariable String adminUsername){
		return holidaysService.getAllTherapistHolidaysByAdminUsername(adminUsername);
	}	
	@RequestMapping(value=RestURIConstants.GET_ALL_HOLIDAYS_BY_ADMIN_USERNAME_PAGE,method=RequestMethod.GET)
	public @ResponseBody Page<Holiday> getAllTherapistHolidaysByAdminUsername(@PathVariable String adminUsername,@RequestParam("page")int page,@RequestParam("size")int size){
		return holidaysService.getAllTherapistHolidaysByAdminUsername(adminUsername,page,size);
	}	
	
	@RequestMapping(value=RestURIConstants.GET_ALL_HOLIDAYS_BY_ROLE,method=RequestMethod.GET)
	public @ResponseBody List<Holiday> getAllTherapistHolidaysByRole(@PathVariable String adminUsername){
		return holidaysService.getAllHolidaysByRole(adminUsername);
	}
	
	
}
