package au.edu.rmit.daniel.socialeventplannerv1.view;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import au.edu.rmit.daniel.socialeventplannerv1.R;

public class CalendarNewEventActivity extends NewEventActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		
		findEditTexts();
		findButtons();
		findEditEmail();
		
		
		getButtonEditDate().setVisibility(android.view.View.INVISIBLE);
		calendarNewEvent();
	}
	
	public void calendarNewEvent() {
		Intent intentReceived = getIntent();
		Bundle bundleReceived = (Bundle) intentReceived.getExtras().get("date");
		Calendar date = (Calendar) bundleReceived.get("date");
		setDay(date.get(Calendar.DATE));
		setMonth(date.get(Calendar.MONTH));
		setYear(date.get(Calendar.YEAR));
	}
}
