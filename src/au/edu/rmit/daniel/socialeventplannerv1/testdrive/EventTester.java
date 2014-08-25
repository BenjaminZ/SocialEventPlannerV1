package au.edu.rmit.daniel.socialeventplannerv1.testdrive;

import android.app.Activity;
import android.os.Bundle;
import au.edu.rmit.daniel.socialeventplannerv1.model.event.Events;

public class EventTester extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//TODO
		super.onCreate(savedInstanceState);
		setContentView(au.edu.rmit.daniel.socialeventplannerv1.R.layout.tester_event);
		
		String[] attendees = new String[] {"benjaminzhang1130@gmail.com"};
		
//		Event event = new Event("Test Title", 950, 12082014, "Australia", "Test Note", attendees);
	}
	
}
