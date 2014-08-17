package au.edu.unimelb.benjamin.socialeventplannerv1.view;

import java.util.ArrayList;
import java.util.Collections;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.ListCellAdapter;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.event.Events;
import au.edu.unimelb.benjamin.socialeventplannerv1.util.DataUtil;

public class ViewEvents extends ListActivity {

	private ArrayList<Events> eventsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(android.R.layout.list_content);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(this, EditEventsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("event", eventsList.get(position));
		intent.putExtra("event", bundle);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		eventsList = (ArrayList<Events>) DataUtil.getData(this);
		Collections.sort(eventsList);
		
		setListAdapter(new ListCellAdapter(this, eventsList));
	}
}
