package au.edu.unimelb.benjamin.socialeventplannerv1.testdrive;

import android.app.Activity;
import android.os.Bundle;
import au.edu.unimelb.benjamin.socialeventplannerv1.module.Event;

public class EventTester extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//TODO
		super.onCreate(savedInstanceState);
		setContentView(au.edu.unimelb.benjamin.socialeventplannerv1.R.layout.tester_event);
		
		String[] attendees = new String[] {"benjaminzhang1130@gmail.com"};
		
//		Event event = new Event("Test Title", 950, 12082014, "Australia", "Test Note", attendees);
	}
	
}
