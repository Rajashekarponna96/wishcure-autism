package com.openspace.Model.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarUtil {

	public LocalDate convertweekAndYearToStartDateOfWeek(int week, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(Calendar.YEAR, year);

		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy"); // PST`
		java.util.Date startDate = calendar.getTime();
		String startDateInStr = formatter.format(startDate);
		System.out.println("...date..." + startDateInStr);
		String[] values = startDateInStr.split(" ");
		String dateofStartDateWeek = values[0];
		String monthofStartDateWeek = values[1];
		String yearofStartDateWeek = values[2];
		LocalDate weekStartdate = LocalDate.of(Integer.parseInt(yearofStartDateWeek.trim()),
				Integer.parseInt(monthofStartDateWeek.trim()), Integer.parseInt(dateofStartDateWeek.trim()));
		return weekStartdate;
	}

	public LocalDate convertweekAndYearToLastDateOfWeek(int week, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(Calendar.YEAR, year);

		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy"); // PST`
		java.util.Date startDate = calendar.getTime();
		String startDateInStr = formatter.format(startDate);
		calendar.add(Calendar.DATE, 6);
		Date enddate = calendar.getTime();
		String endDaString = formatter.format(enddate);
		String[] endDatevalues = endDaString.split(" ");
		String dateofEndWeek = endDatevalues[0];
		String monthofEndWeek = endDatevalues[1];
		String yearofEndWeek = endDatevalues[2];
		LocalDate weekEnddate = LocalDate.of(Integer.parseInt(yearofEndWeek.trim()),
				Integer.parseInt(monthofEndWeek.trim()), Integer.parseInt(dateofEndWeek.trim()));

		return weekEnddate;
	}

	public LocalDate convertMonthToLocalDate(int month, int year) {
		return LocalDate.of(year, month, 1);
	}

	public LocalDate convertYeartoDate(int year) {
		return LocalDate.of(year, 1, 1);
	}

	public LocalDate convertYeartoEndDate(int year) {
		return LocalDate.of(year, 12, 31);
	}

	public static Integer monthsList(String monthName) {
		Map<String, Integer> months = new HashMap<String, Integer>();
		months.put("January", 1);
		months.put("February", 2);
		months.put("March", 3);
		months.put("April", 4);
		months.put("May", 5);
		months.put("June", 6);
		months.put("July", 7);
		months.put("August", 8);
		months.put("September", 9);
		months.put("October", 10);
		months.put("November", 11);
		months.put("December", 12);
		months.put("JANUARY", 1);
		months.put("FEBRUARY", 2);
		months.put("MARCH", 3);
		months.put("APRIL", 4);
		months.put("MAY", 5);
		months.put("JUNE", 6);
		months.put("JULY", 7);
		months.put("AUGUST", 8);
		months.put("SEPTEMBER", 9);
		months.put("OCTOBER", 10);
		months.put("NOVEMBER", 11);
		months.put("DECEMBER", 12);
		return months.get(monthName);
	}

	public LocalDate convertDayToLocalDate(String currentDay) {
		// String currentDay = "Monday 13 November, 2017";
		String year = currentDay.substring(currentDay.lastIndexOf(","));
		String currentYear = year.substring(1, year.length());
		String[] values = currentDay.split(" ");
		String dayOfDate = values[1];
		String month = values[2];
		String actualmonth = month.substring(0, month.length() - 1);
		int monthOfDay = CalendarUtil.monthsList(actualmonth);
		System.out.println("dddddd "
				+ LocalDate.of(Integer.parseInt(currentYear.trim()), monthOfDay, Integer.parseInt(dayOfDate)));
		LocalDate localDate = LocalDate.of(Integer.parseInt(currentYear.trim()), monthOfDay,
				Integer.parseInt(dayOfDate));
		System.out.println("localDate1111" + localDate);
		return localDate;

	}

	public LocalDate convertMonthNameToStartDate(String monthName, int year) {
		int month = CalendarUtil.monthsList(monthName);
		System.out.println("month number " + month);
		return LocalDate.of(year, month, 1);

	}

	public LocalDate convertMonthNameToEndDate(String monthName, int year) {
		int month = CalendarUtil.monthsList(monthName);
		Calendar calendar = Calendar.getInstance();
		// passing month-1 because 0-->jan, 1-->feb... 11-->dec
		calendar.set(year, month - 1, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date date = calendar.getTime();
		DateFormat formatter = new SimpleDateFormat("dd MM yyyy");
		String startDateInStr = formatter.format(date);
		System.out.println("startDateInStr" + startDateInStr);
		String[] values = startDateInStr.split(" ");
		String dateOfMonth = values[0];
		String monthofMonth = values[1];
		String yearofMonth = values[2];
		System.out.println("startDateInStr2" + values[2]);
		System.out.println("startDateInStr1" + values[1]);
		System.out.println("startDateInStr0" + values[0]);
		LocalDate monthEnddate = LocalDate.of(Integer.parseInt(yearofMonth.trim()),
				Integer.parseInt(monthofMonth.trim()), Integer.parseInt(dateOfMonth.trim()));
		System.out.println("monthEnddate " + monthEnddate);

		return monthEnddate;

	}

	public LocalDate splitDateFormat(String date) {
		String[] splitDate = date.split("T");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate presentDate = LocalDate.parse(splitDate[0], formatter);
		return presentDate;
	}

}
