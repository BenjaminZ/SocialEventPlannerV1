package au.edu.unimelb.benjamin.socialeventplannerv1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAndDate {

	public static int getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("hhmm");
		Date currentTime = new Date(System.currentTimeMillis());
		int time = Integer.parseInt(formatter.format(currentTime));
		
		return time;
	}
	
	public static int getCurrentHour() {
		SimpleDateFormat formatter = new SimpleDateFormat("hh");
		Date currentHour = new Date(System.currentTimeMillis());
		
		return Integer.parseInt(formatter.format(currentHour));
	}
	
	public static int getCurrentMinute() {
		SimpleDateFormat formatter = new SimpleDateFormat("ss");
		Date currentMinute = new Date(System.currentTimeMillis());
		
		return Integer.parseInt(formatter.format(currentMinute));
	}
	
	public static long getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
		Date currentDate = new Date(System.currentTimeMillis());
		long date = Integer.parseInt(formatter.format(currentDate));
		
		return date;
	}
}
