package com.openspace.HospitalMgnt.Therapist.Holidays;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Holiday;


public interface HolidaysService {
	
	void saveHolidaysService(HolidayDto holidayDto);

	List<Holiday> getAllHolidays();

	void updateHolidays(Holiday holiday);

	void deleteHolidays(Long id);
	
	Page<Holiday> getAllHolidaysByDoctorUsername(String doctorUsername,int page,int size);
	
	List<Holiday> getAllHolidaysByDoctorUsername(String doctorUsername);

	List<Holiday> getAllTherapistHolidaysByAdminUsername(String adminUsername);

	Page<Holiday> getAllTherapistHolidaysByAdminUsername(String adminUsername, int page, int size);

	List<Holiday> getAllHolidaysByRole(String adminUsername);
	

}
