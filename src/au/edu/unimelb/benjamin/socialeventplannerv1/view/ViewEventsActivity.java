package au.edu.unimelb.benjamin.socialeventplannerv1.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.ListCellAdapter;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.event.Events;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.DataUtil;

public class ViewEventsActivity extends ListActivity {

	private ArrayList<Events> eventsList, listToDisplay;
	private Button newEvent;
	private Calendar date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_events);
		
		newEvent = (Button) findViewById(R.id.buttonCalendarNewEvent);
		newEvent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ViewEventsActivity.this, CalendarNewEventActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("date", date);
				intent.putExtra("date", bundle);
//				int day = date.get(Calendar.DATE);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intentToSend = new Intent(this, EditEventsActivity.class);
		Bundle bundleToSend = new Bundle();
		bundleToSend.putSerializable("event", listToDisplay.get(position));
		intentToSend.putExtra("event", bundleToSend);
		startActivity(intentToSend);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Intent intentReceived = getIntent();
		eventsList = (ArrayList<Events>) DataUtil.getData(this);
		if (intentReceived.hasExtra("date")) {
			newEvent.setVisibility(android.view.View.VISIBLE);
			
			Bundle bundleReceived = (Bundle) intentReceived.getExtras().get("date");
			date = (Calendar) bundleReceived.get("date");
			listToDisplay = new ArrayList<Events>();
			for (Events eventToCheck : eventsList) {
//				int year = date.get(Calendar.YEAR);
//				int month = date.get(Calendar.MONTH);
//				int day = date.get(Calendar.DATE);
				if ((eventToCheck.getDay() == date.get(Calendar.DATE)) && (eventToCheck.getMonth() == date.get(Calendar.MONTH)) && (eventToCheck.getYear() == date.get(Calendar.YEAR))) {
					listToDisplay.add(eventToCheck);
				}
			}
		} else {
			listToDisplay = eventsList;
		}
		Collections.sort(listToDisplay);
		
		setListAdapter(new ListCellAdapter(this, listToDisplay));
	}
}
