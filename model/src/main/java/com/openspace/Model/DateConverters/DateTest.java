package com.openspace.Model.DateConverters;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class DateTest {

	public static void main(String[] args) {
		
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = date1.plusDays(1);
		
		System.out.println("date 1   "+date1);
		System.out.println("date 2   "+date2);
		
		Period period1 = Period.between(date1,date2);
		System.out.println("period1  "+period1);
		
		System.out.println("period1 in days "+period1.getDays());
		System.out.println("period1 in years "+period1.getYears());
		System.out.println("period1 in months "+period1.getMonths());
		
		Duration dutation1 = Duration.between(date1.atStartOfDay(),date2.atStartOfDay());
		
		System.out.println("period1 in days "+dutation1.toDays());
		System.out.println("period1 in hours "+dutation1.toHours());
		System.out.println("period1 in millisec "+dutation1.toMillis());
		System.out.println("period1 in mimits "+dutation1.toMinutes());
		System.out.println("period1 in nanosec "+dutation1.toNanos());
		System.out.println("period1 in sec"+dutation1.getSeconds());
		System.out.println("period1 in getNonos"+dutation1.getNano());
		
		
		//--------------------------------------------

		LocalTime time1 = LocalTime.now();
		LocalTime time2 = time1.plusHours(1);
		
		System.out.println("time1 "+time1);
		System.out.println("time2 "+time2);
		
		//Period timeperiod1 = Period.between(time1,time2);
		Duration.between(time1,time2);
				
		LocalDateTime localTimedate1 = LocalDateTime.now();
		LocalDateTime localTimedate2 = LocalDateTime.now().plusDays(1);
		
		System.out.println("localTimedate1 "+localTimedate1);
		System.out.println("localTimedate2 "+localTimedate2);
		
		
		Duration datetimeduration1= Duration.between(localTimedate1,localTimedate2);
		System.out.println("datetimeduration1 get nons "+datetimeduration1.getNano());
		System.out.println("datetimeduration1 to nonos "+datetimeduration1.toNanos());
		System.out.println("datetimeduration1 to String "+datetimeduration1.toHours());
		
		System.out.println("LocalDate.now().plusDays(2) "+LocalDate.now().plusDays(2));
		System.out.println("LocalDate.now().plusDays(10) "+LocalDate.now().plusDays(10));
		
		System.out.println("LocalDate.now().plusDays(3) "+LocalDate.now().plusDays(3));
		System.out.println("LocalDate.now().plusDays(19) "+LocalDate.now().plusDays(19));
		
		System.out.println("Local Date   "+LocalDate.of(2015, 07, 01));
		

	}

}
