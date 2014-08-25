package au.edu.rmit.daniel.socialeventplannerv1.model.event;

import android.text.TextUtils;
import au.edu.rmit.daniel.socialeventplannerv1.model.GeoResult.Location;
import au.edu.rmit.daniel.socialeventplannerv1.util.GeocodingTask;
import au.edu.rmit.daniel.socialeventplannerv1.util.TimeAndDateUtil;
import au.edu.rmit.daniel.socialeventplannerv1.util.GeocodingTask.OnResponseListener;

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
			minute = TimeAndDateUtil.getCurrentMinute();
		}
		if (hour == 0) {
			hour = TimeAndDateUtil.getCurrentHour();
		}
		if (day == 0) {
			day = TimeAndDateUtil.getCurrentDay();
		}
		if (month == 0) {
			month = TimeAndDateUtil.getCurrentMonth();
		}
		if (year == 0) {
			year = TimeAndDateUtil.getCurrentYear();
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
		
		latitude = 0.0;
		longitude = 0.0;
		
		GeocodingTask geoTask = new GeocodingTask();
		geoTask.execute(venue);
		geoTask.setOnResponseListener(new OnResponseListener() {
			
			@Override
			public void onResponse(Location location) {

				latitude = location.getLat();
				longitude = location.getLng();
			}
		});
	}
}
