package au.edu.unimelb.benjamin.socialeventplannerv1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAndDate {

	public static int getCurrentDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		Date currentDay = new Date(System.currentTimeMillis());
		int day = Integer.parseInt(formatter.format(currentDay));
		
		return day;
	}
	
	public static int getCurrentHour() {
		SimpleDateFormat formatter = new SimpleDateFormat("hh");
		Date currentHour = new Date(System.currentTimeMillis());
		
		return Integer.parseInt(formatter.format(currentHour));
	}
	
	public static int getCurrentMinute() {
		SimpleDateFormat formatter = new SimpleDateFormat("mm");
		Date currentMinute = new Date(System.currentTimeMillis());
		
		return Integer.parseInt(formatter.format(currentMinute));
	}
	
	public static int getCurrentMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		Date currentMonth = new Date(System.currentTimeMillis());
		int month = Integer.parseInt(formatter.format(currentMonth));
		
		return month;
	}
	
	public static int getCurrentYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date currentYear = new Date(System.currentTimeMillis());
		int year = Integer.parseInt(formatter.format(currentYear));
		
		return year;
	}
}
