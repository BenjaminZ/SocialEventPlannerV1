package au.edu.rmit.daniel.socialeventplannerv1.model;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import au.edu.rmit.daniel.socialeventplannerv1.model.event.Events;
import au.edu.rmit.daniel.socialeventplannerv1.R;

public class ListCellAdapter extends BaseAdapter {
	
	private ArrayList<Events>eventsList;
	private Context context;
	private LinearLayout outerLL;
	
	public ListCellAdapter(Context context, ArrayList<Events>eventsList) {
		super();
		this.context = context;
		this.eventsList = eventsList;
	}
	
	

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}



	@Override
	public int getCount() {
		return eventsList.size();
	}

	@Override
	public Events getItem(int position) {
		return eventsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return eventsList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView != null) {
			outerLL = (LinearLayout) convertView;
		} else {
			outerLL = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.adapter_events_list_cell, parent, false);
		}
		
		TextView textViewTitle, textViewDate, textViewNumOfInvits;
		textViewTitle = (TextView) outerLL.findViewById(R.id.textViewTitle);
		textViewDate = (TextView) outerLL.findViewById(R.id.textViewDate);
		textViewNumOfInvits = (TextView) outerLL.findViewById(R.id.textViewNumOfInvits);
		
		Events event = getItem(position);
		textViewTitle.setText(event.getTitle());
		textViewDate.setText(event.getDay() + File.separator + event.getMonth());
		
		String[] attendees = event.getAttendees();
		if (attendees[0].equals("")) {
			textViewNumOfInvits.setText("0 attendees");
		} else {
			textViewNumOfInvits.setText(event.getAttendees().length + " attendees");
		}
		
		return outerLL;
	}

}
