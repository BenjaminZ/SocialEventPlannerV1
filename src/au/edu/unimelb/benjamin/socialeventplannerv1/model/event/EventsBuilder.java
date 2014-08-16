package au.edu.unimelb.benjamin.socialeventplannerv1.model.event;

import android.text.TextUtils;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.TimeAndDate;

public class EventsBuilder {
	
	private String title;
	private int minute, hour, day, month, year;
	private double latitude, longitude;
	private String venue;
	private String note;
	private String[] attendees;
	
	

	public EventsBuilder(String title, int minute, int hour, int day,
			int month, int year, String venue, String note, String[] attendees) {
		this.title = title;
		this.minute = minute;
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
		this.venue = venue;
		this.note = note;
		this.attendees = attendees;
	}

	public Events getEvent(){
		if (TextUtils.isEmpty(title)) {
			title = "Event";
		}
		if (minute == 0) {
			minute = TimeAndDate.getCurrentMinute();
		}
		if (hour == 0) {
			hour = TimeAndDate.getCurrentHour();
		}
		if (day == 0) {
			day = TimeAndDate.getCurrentDay();
		}
		if (month == 0) {
			month = TimeAndDate.getCurrentMonth();
		}
		if (year == 0) {
			year = TimeAndDate.getCurrentYear();
		}
		if (TextUtils.isEmpty(venue)) {
			latitude = 0.0;
			longitude = 0.0;
		} else {
			getLocation();
		}
		
		return new Events(title, minute, hour, day, month, year, venue, longitude, latitude, note, attendees);
	}
	
	private void getLocation() {
		//TODO
		
		latitude = 0.0;
		longitude = 0.0;
	}
}
