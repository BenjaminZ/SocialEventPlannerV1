package au.edu.unimelb.benjamin.socialeventplannerv1.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.event.Events;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.DataUtil;


public class EditEventsActivity extends NewEventActivity {

	private Button buttonDeleteEvent;
	private Intent intent;
	private Events event;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		
		findEditTexts();
		findButtons();
		findEditEmail();
		findDeleteButton();
		
		setTexts();
	}
	
	public void findDeleteButton() {
		buttonDeleteEvent = (Button) findViewById(R.id.buttonDeleteEvent);
		buttonDeleteEvent.setVisibility(android.view.View.VISIBLE);
		buttonDeleteEvent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DataUtil.deleteData(EditEventsActivity.this, event);
				finish();
			}
		});
	}
	
	@Override
	public void setEvent() {
		event.setTitle(getEditTitle().getText().toString());
		event.setVenue(getEditVenue().getText().toString());
		event.setNote(getEditNote().getText().toString());
		event.setAttendees(getEditEmail().getText().toString().split(", "));
		DataUtil.editData(this, event);
	}
	
	public void setTexts() {
		intent = getIntent();
		Bundle bundle = (Bundle) intent.getExtras().get("event");
		event = (Events) bundle.get("event");
		getEditTitle().setText(event.getTitle());
		getEditVenue().setText(event.getVenue());
		getEditNote().setText(event.getNote());
		
		String attendees = "";
		for (String attendee : event.getAttendees()) {
			attendees = attendees + attendee + ", ";
		}
		getEditEmail().setText(attendees);
		tokenEraser();
	}
}
