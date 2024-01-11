package com.openspace.PatientManagement.dashboard;

import java.time.LocalDate;

public interface DashboardService {

	int findActiveDoctorBydates(String adminUserName, LocalDate startDatelocaldate, LocalDate endDatelocaldate);

	int findRegistrationsBydates(String adminUserName, LocalDate startDatelocaldate, LocalDate endDatelocaldate);
	
	int totalPatientsRegistered(String adminUserName, LocalDate startDatelocaldate, LocalDate endDatelocaldate);
	
	DashboardDto getTotalPatientsRegisteredChartDataPerPresentYear(String adminUserName, int year);
	
	DashboardDto getActiveDoctorsChartDataPerPresentYear(String adminUserName, int year);
	
	DashboardDto getActiveUsersChartDataPerPresentYear(String adminUserName, int year);
	
	DashboardDto findTodayPatientsChartDataByPresentWeek(String adminUserName);
	
	int findTodayPatientsByPresentDate(String adminUserName, LocalDate presentDate);

	
	

}
