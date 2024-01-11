package com.openspace.PatientManagement.dashboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping(value="/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService reportService;
	
	@RequestMapping(value="/ActiveDoctorsPerPresentYear/{adminUserName:.+}/{year}", method = RequestMethod.GET)
	public DashboardDto getActiveDoctorsChartDataPerPresentYear(@PathVariable(value="adminUserName")String adminUserName,@PathVariable("year")int year){
					
		return reportService.getActiveDoctorsChartDataPerPresentYear(adminUserName,year);
	}
	@RequestMapping(value="/ActiveUsersPerPresentYear/{adminUserName:.+}/{year}", method = RequestMethod.GET)
	public DashboardDto getActiveUsersChartDataPerPresentYear(@PathVariable(value="adminUserName")String adminUserName,@PathVariable("year")int year){
					
		return reportService.getActiveUsersChartDataPerPresentYear(adminUserName,year);
	}
	
	@RequestMapping(value="/TotalPatientsRegisteredPerPresentYear/{adminUserName:.+}/{year}", method = RequestMethod.GET)
	public DashboardDto getTotalPatientsRegisteredChartDataPerPresentYear(@PathVariable(value="adminUserName")String adminUserName,@PathVariable("year")int year){
					
		return reportService.getTotalPatientsRegisteredChartDataPerPresentYear(adminUserName,year);
	}
	
	@RequestMapping(value="/TodayPatients/{adminUserName:.+}", method = RequestMethod.GET)
	public DashboardDto getTodayPatientsChartData(@PathVariable(value="adminUserName")String adminUserName){
		return reportService.findTodayPatientsChartDataByPresentWeek(adminUserName);
	}

}
