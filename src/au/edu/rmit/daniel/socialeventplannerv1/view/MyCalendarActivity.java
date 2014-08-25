package au.edu.rmit.daniel.socialeventplannerv1.view;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import au.edu.rmit.daniel.socialeventplannerv1.util.TimeAndDateUtil;
import au.edu.rmit.daniel.socialeventplannerv1.R;

public class MyCalendarActivity extends Activity {
	
	private int month, year, day, startDayOfWeek, totalDayOfMonth;
	private int startDate = 1;
	private TextView textViewMonth, textViewYear;
	private GridView calendar;
	private ArrayAdapter<String> adapter;
	private Button buttonLeft, buttonRight;
	private Calendar calendarUtil;

	public int getTotalDayOfMonth() {
		return totalDayOfMonth;
	}
	
	public void setTotalDayOfMonth() {
		totalDayOfMonth = calendarUtil.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
//		calendarUtil.set(Calendar.DATE, day);
//		setTotalDayOfMonth();
//		setStartDayOfWeek();
//		initializeAdapter();
	}

	public int getDayOfWeek() {
		return startDayOfWeek;
	}

	public void setStartDayOfWeek() {
		calendarUtil.set(Calendar.DATE, startDate);
		startDayOfWeek = calendarUtil.get(Calendar.DAY_OF_WEEK);
		
//		int year = calendarUtil.get(Calendar.YEAR);
//		int month = calendarUtil.get(Calendar.MONTH);
//		int day = calendarUtil.get(Calendar.DATE);
//		System.out.println();
		
	}

	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
		calendarUtil.set(Calendar.MONTH, month - 1);
		textViewMonth.setText(month + "");
		setTotalDayOfMonth();
		setStartDayOfWeek();
		initializeAdapter();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
		calendarUtil.set(Calendar.YEAR, year);
		textViewYear.setText(year + "");
		setTotalDayOfMonth();
		setStartDayOfWeek();
		initializeAdapter();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_calendar);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		initializeView();
		initializeData();
		calendar.setAdapter(adapter);
		setListeners();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initializeAdapter();
	}
	
	public void initializeData() {
		calendarUtil = Calendar.getInstance();
		setDay(TimeAndDateUtil.getCurrentDay());
		setMonth(TimeAndDateUtil.getCurrentMonth());
		setYear(TimeAndDateUtil.getCurrentYear());
	}
	
	public void initializeAdapter() {
		adapter.clear();
		for (int i = 1; i < startDayOfWeek; i++) {
			adapter.add("");
		}
		for (int i = 1; i <= totalDayOfMonth; i++) {
			adapter.add(((Integer)i).toString());
		}
		adapter.notifyDataSetChanged();
	}
	
	public void initializeView() {
		textViewMonth = (TextView) findViewById(R.id.textViewCalendarMonth);
		textViewMonth.setText(month + "");
		textViewYear = (TextView) findViewById(R.id.textViewCalendarYear);
		textViewYear.setText(year + "");
		calendar = (GridView) findViewById(R.id.gridViewCalendar);
		buttonLeft = (Button) findViewById(R.id.buttonCalendarLeft);
		buttonRight = (Button) findViewById(R.id.buttonCalendarRight);
	}
	
	public void setListeners() {
		MyOnClickListener textViewListener = new MyOnClickListener();
		calendar.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if ((position + 1) >= startDayOfWeek) {
					int pickDay = ((position + 1) - startDayOfWeek) + 1;
					Calendar pickDate = Calendar.getInstance();
					pickDate.set(Calendar.DATE, pickDay);
					pickDate.set(Calendar.MONTH, getMonth());
					pickDate.set(Calendar.YEAR, getYear());
					Intent intent = new Intent(MyCalendarActivity.this, ViewEventsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("date", pickDate);
					intent.putExtra("date", bundle);
					

					
					startActivity(intent);
				}
			}
		});
		
		buttonLeft.setOnClickListener(textViewListener);
		buttonRight.setOnClickListener(textViewListener);
	}
	
	
	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.buttonCalendarLeft:
				reduceMonth();
				break;
				
			case R.id.buttonCalendarRight:
				addMonth();
				break;
				
			default:
				break;
			}
		}
		
	}
	
	public void addMonth() {
		if (getMonth() < 12) {
			setMonth(getMonth() + 1);
		} else {
			setMonth(1);
			setYear(getYear() + 1);
		}
	
	}
	
	public void reduceMonth() {
		if (getMonth() > 1) {
			setMonth(getMonth() - 1);
		} else {
			setMonth(12);
			setYear(getYear() - 1);
		}
	}
	
//	public void test() {
//		int year = calendarUtil.get(Calendar.YEAR);
//		int month = calendarUtil.get(Calendar.MONTH);
//		int day = calendarUtil.get(Calendar.DATE);
//		System.out.println();
//	}

}
