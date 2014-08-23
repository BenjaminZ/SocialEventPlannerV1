package au.edu.unimelb.benjamin.socialeventplannerv1.model.event;

import java.io.Serializable;
import java.util.Calendar;



public class Events implements Serializable,Comparable<Events>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5338929727662897733L;
	private String title;
	private int minute, hour, day, month, year;
	private String venue;
	private double longitude, latitude;
	private String note;
	private String[] attendees;
	private long id;
	
	protected Events(String title, int minute, int hour, int day, int month, int year, String venue, double longitude, double latitude, String note, String[] attendees) {
		this.title = title;
		this.minute = minute;
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
		this.venue = venue;
		this.longitude = longitude;
		this.latitude = latitude;
		this.note = note;
		this.attendees = attendees;
		id = System.currentTimeMillis();
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getMinute() {
		return minute;
	}


	public void setMinute(int minute) {
		this.minute = minute;
	}


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String[] getAttendees() {
		return attendees;
	}

	public void setAttendees(String[] attendees) {
		this.attendees = attendees;
	}

	public long getId() {
		return id;
	}


	@Override
	public int compareTo(Events another) {
		
//		if (year > another.getYear()) {
//			return -1;
//		} else if (year < another.getYear()) {
//			return 1;
//		} else if (month > another.getMonth()) {
//			return -1;
//		} else if (month < another.getMonth()) {
//			return 1;
//		} else if (day > another.getDay()) {
//			return -1;
//		} else if (day < another.getDay()) {
//			return 1;
//		} else if (hour > another.getHour()) {
//			return -1;
//		} else if (hour < another.getHour()) {
//			return 1;
//		} else if (minute > another.getMinute()) {
//			return -1;
//		} else if (minute < another.minute) {
//			return 1;
//		} else {
//			return 0;
//		}
		
		Calendar date = Calendar.getInstance();
		date.set(year, month, day, hour, minute);
		Calendar dateAnother = Calendar.getInstance();
		dateAnother.set(another.getYear(), another.getMonth(), another.getDay(), another.getHour(), another.getMinute());
		return date.compareTo(dateAnother);
	}
	
	
	
}
