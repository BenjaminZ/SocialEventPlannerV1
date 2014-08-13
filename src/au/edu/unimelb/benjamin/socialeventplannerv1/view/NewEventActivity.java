package au.edu.unimelb.benjamin.socialeventplannerv1.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.TimeAndDate;

public class NewEventActivity extends Activity implements OnClickListener {
	
	private String title;
	private int minute, hour, day, month, year;
	private String venue;
	private double[] location;
	private String note;
	private String[] attendees;
	private EditText editTitle;
	private Button buttonEditTime, buttonEditDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		
		editTitle = (EditText) findViewById(R.id.editTextTitle);
		buttonEditTime = (Button) findViewById(R.id.buttonNewEditTime);
		buttonEditDate = (Button) findViewById(R.id.buttonNewEditDate);
		buttonEditTime.setOnClickListener(this);
		buttonEditDate.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.buttonNewEditTime:
			new TimePickerDialog(NewEventActivity.this, new OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

					hour = hourOfDay;
					NewEventActivity.this.minute = minute;
				}
			}, TimeAndDate.getCurrentHour(), TimeAndDate.getCurrentMinute(), true).show();
			break;
			
		case R.id.buttonNewEditDate:
			new DatePickerDialog(NewEventActivity.this, new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					
					day = dayOfMonth;
					month = monthOfYear;
					NewEventActivity.this.year = year;
				}
			}, 1970, 0, 30).show();
			
			break;

		default:
			break;
		}
	}
	


}
