package au.edu.unimelb.benjamin.socialeventplannerv1.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import au.edu.unimelb.benjamin.socialeventplannerv1.testdrive.EventTester;

public class MainActivity extends Activity implements OnClickListener {

	private Button buttonNewEvent, buttonEditEvent, buttonViewCalendar, buttonViewAllEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttonNewEvent = (Button) findViewById(R.id.buttonScheduleEvent);
        buttonEditEvent = (Button) findViewById(R.id.buttonEditEvent);
        buttonViewCalendar = (Button) findViewById(R.id.buttonViewCalendar);
        buttonViewAllEvents = (Button) findViewById(R.id.buttonViewAllEvents);
        
        buttonNewEvent.setOnClickListener(this);
        buttonEditEvent.setOnClickListener(this);
        buttonViewCalendar.setOnClickListener(this);
        buttonViewAllEvents.setOnClickListener(this);
        
        //Test
        findViewById(R.id.buttonTest).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent testIntent = new Intent(MainActivity.this, EventTester.class);
				startActivity(testIntent);
			}
		});
    }

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();

		switch (v.getId()) {
		case R.id.buttonScheduleEvent:
			intent.setClass(MainActivity.this, NewEventActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
