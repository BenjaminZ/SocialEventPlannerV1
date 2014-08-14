package au.edu.unimelb.benjamin.socialeventplannerv1.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TimePicker;
import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.TimeAndDate;

public class NewEventActivity extends Activity {
	
	private String title;
	private int minute, hour, day, month, year;
	private String venue;
	private double[] location;
	private String note;
	private String[] attendees;
	private EditText editTitle, editVenue, editNote;
	private Button buttonEditTime, buttonEditDate;
	private MultiAutoCompleteTextView editEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		
		editTitle = (EditText) findViewById(R.id.editTextTitle);
		
		buttonEditTime = (Button) findViewById(R.id.buttonNewEditTime);
		buttonEditDate = (Button) findViewById(R.id.buttonNewEditDate);
		ButtonClickListener buttonClickListener = new ButtonClickListener();
		buttonEditTime.setOnClickListener(buttonClickListener);
		buttonEditDate.setOnClickListener(buttonClickListener);
		
		editVenue = (EditText) findViewById(R.id.editTextVenue);
		
		editNote = (EditText) findViewById(R.id.editTextNote);
		
		editEmail = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextViewEmail);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	             android.R.layout.simple_dropdown_item_1line, setAdapter());
		editEmail.setAdapter(adapter);
	}
	
	private String[] setAdapter() {
		
		ArrayList<String> emailAddressCollection = new ArrayList<String>();
		ContentResolver cr = getContentResolver();
		
		Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, null, null, null);
		
		while (emailCur.moveToNext())
		{
			String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
			emailAddressCollection.add(email);
		}
		emailCur.close();
		
		String[] emailAddresses = new String[emailAddressCollection.size()];
		emailAddressCollection.toArray(emailAddresses);
		
		return emailAddresses;
		
	}
	

	class ButtonClickListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch (v.getId()) {
			case R.id.buttonNewEditTime:
				
				timePicker();
				break;
				
			case R.id.buttonNewEditDate:
				
				datePicker();
				break;
				
			default:
				break;
			}
			
		}
		
	}
	
	private void datePicker() {
		
		new TimePickerDialog(NewEventActivity.this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				
				hour = hourOfDay;
				NewEventActivity.this.minute = minute;
			}
		}, TimeAndDate.getCurrentHour(), TimeAndDate.getCurrentMinute(), true).show();
	}
	
	private void timePicker() {
		
		new DatePickerDialog(NewEventActivity.this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				
				day = dayOfMonth;
				month = monthOfYear;
				NewEventActivity.this.year = year;
			}
		}, 1970, 0, 30).show();
	}
	

}
