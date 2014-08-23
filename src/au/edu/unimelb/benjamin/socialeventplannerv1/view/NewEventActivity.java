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
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.event.Events;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.event.EventsBuilder;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.DataUtil;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.TimeAndDateUtil;

public class NewEventActivity extends Activity {
	
	private String title;
	private int minute, hour, day, month, year;
	private String venue;
	private String note;
	private String[] attendees;
	
	public EditText getEditTitle() {
		return editTitle;
	}

	public EditText getEditVenue() {
		return editVenue;
	}

	public EditText getEditNote() {
		return editNote;
	}

	public MultiAutoCompleteTextView getEditEmail() {
		return editEmail;
	}

	public int getMinute() {
		return minute;
	}

	public int getHour() {
		return hour;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public DatePickerDialog getDateDialog() {
		return dateDialog;
	}
	
	public TimePickerDialog getTimeDialog() {
		return timeDialog;
	}
	public void setDay(int day) {
		this.day = day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Button getButtonEditDate() {
		return buttonEditDate;
	}
	private EditText editTitle, editVenue, editNote;
	private Button buttonEditTime, buttonEditDate, buttonDone;
	private MultiAutoCompleteTextView editEmail;
	private DatePickerDialog dateDialog;
	private TimePickerDialog timeDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		
		findEditTexts();
		findButtons();
		findEditEmail();
		
	}
	
	public void findEditTexts() {
		
		editTitle = (EditText) findViewById(R.id.editTextTitle);
		editVenue = (EditText) findViewById(R.id.editTextVenue);
		editNote = (EditText) findViewById(R.id.editTextNote);
	}
	
	public void findButtons() {
		
		buttonEditTime = (Button) findViewById(R.id.buttonNewEditTime);
		buttonEditDate = (Button) findViewById(R.id.buttonNewEditDate);
		ButtonClickListener buttonClickListener = new ButtonClickListener();
		buttonEditTime.setOnClickListener(buttonClickListener);
		buttonEditDate.setOnClickListener(buttonClickListener);
		buttonDone = (Button) findViewById(R.id.buttonNewDone);
		buttonDone.setOnClickListener(buttonClickListener);
	}
	
	public void findEditEmail() {
		
		EditEmailListener editEmailListener = new EditEmailListener();
		editEmail = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextViewEmail);
		editEmail.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		editEmail.setOnEditorActionListener(editEmailListener);
		editEmail.setOnFocusChangeListener(editEmailListener);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, setAdapter());
		editEmail.setAdapter(adapter);
	}
	
	public void setParameters() {
		title = editTitle.getText().toString();
		venue = editVenue.getText().toString();
		note = editNote.getText().toString();
		attendees = editEmail.getText().toString().split(", ");
	}
	
	class EditEmailListener implements OnEditorActionListener, OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			
			tokenEraser();
		}

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			
			tokenEraser();
			return false;
		}
		
		
	}
	
	public void tokenEraser() {
		String msg = editEmail.getText().toString();
		Log.v("edit", msg);
		if (!TextUtils.isEmpty(editEmail.getText())) {
			
			String text = editEmail.getText().toString();
			if (text.charAt(text.length() - 1) == ' ') {
				
				editEmail.setText(text.substring(0, text.length() - 2));
			}
		}
	}
	
	public String[] setAdapter() {
		
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
			
			switch (v.getId()) {
			case R.id.buttonNewEditTime:
				
				timePicker();
				break;
				
			case R.id.buttonNewEditDate:
				
				datePicker();
				break;
				
			case R.id.buttonNewDone:
				setParameters();
				setEvent();
				finish();
				break;
				
			default:
				break;
			}
			
		}
		
	}
	
	public void setEvent() {
		EventsBuilder eventsBuilder = new EventsBuilder(title, minute, hour, day, month, year, venue, note, attendees);
		Events event = eventsBuilder.getEvent();
		DataUtil.saveData(NewEventActivity.this, event);
		
	}
	
	public void datePicker() {
//		int currentYear = TimeAndDate.getCurrentYear();
//		int currentMonth = TimeAndDate.getCurrentMonth() - 1;
//		int currentDay = TimeAndDate.getCurrentDay();
//		
//		Log.v("date", currentYear + "year");
//		Log.v("date", currentMonth + "month");
//		Log.v("date", currentDay + "day");
		
		dateDialog = new DatePickerDialog(NewEventActivity.this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				
				day = dayOfMonth;
				month = monthOfYear + 1;
				NewEventActivity.this.year = year;
			}
		}, TimeAndDateUtil.getCurrentYear(), TimeAndDateUtil.getCurrentMonth() - 1, TimeAndDateUtil.getCurrentDay());
//		}, currentYear, currentMonth, currentDay);
		dateDialog.show();
	}
	
	public void timePicker() {
		
		timeDialog = new TimePickerDialog(NewEventActivity.this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				
				hour = hourOfDay;
				NewEventActivity.this.minute = minute;
			}
		}, TimeAndDateUtil.getCurrentHour(), TimeAndDateUtil.getCurrentMinute(), true);
		
		timeDialog.show();
	}

	

}
