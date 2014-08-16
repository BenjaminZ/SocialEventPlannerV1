package au.edu.unimelb.benjamin.socialeventplannerv1.view;

import java.util.ArrayList;
import java.util.Collections;

import android.app.ListActivity;
import android.os.Bundle;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.ListCellAdapter;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.event.Events;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.DataUtil;

public class ViewEvents extends ListActivity {

	private ArrayList<Events> eventsList;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(android.R.layout.list_content);
		
		eventsList = (ArrayList<Events>) DataUtil.getData(this);
		Collections.sort(eventsList);
		
		setListAdapter(new ListCellAdapter(this, eventsList));
	}
}
