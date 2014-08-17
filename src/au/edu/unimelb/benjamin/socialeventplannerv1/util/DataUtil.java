package au.edu.unimelb.benjamin.socialeventplannerv1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import au.edu.unimelb.benjamin.socialeventplannerv1.model.event.Events;

public class DataUtil {
	
	private static ArrayList<Events> eventsList;

	@SuppressWarnings("unchecked")
	public static ArrayList<Events> getData(Context context) {
		try {
			ObjectInputStream inputFile = 
					new ObjectInputStream(
							new FileInputStream(context.getFilesDir() + File.separator + context.getResources().getString(R.string.data_file_name)));
			eventsList = ((ArrayList<Events>) inputFile.readObject());
			inputFile.close();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (eventsList == null) {
			eventsList = new ArrayList<Events>();
		}
		return eventsList;
	}
	
	public static void saveData(Context context, Events event) {
		
		try {
			
			getData(context);
			eventsList.add(event);
			ObjectOutputStream outputFile = 
					new ObjectOutputStream(
							new FileOutputStream(context.getFilesDir() + File.separator + context.getResources().getString(R.string.data_file_name)));
			outputFile.writeObject(eventsList);
			outputFile.flush();
			outputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void editData(Context context, Events event) {
		getData(context);
		for (Events toFind : eventsList) {
			if (event.getId() == toFind.getId()) {
				eventsList.remove(toFind);
				break;
			}
		}
		eventsList.add(event);
		ObjectOutputStream outputFile;
		try {
			outputFile = new ObjectOutputStream(
					new FileOutputStream(context.getFilesDir() + File.separator + context.getResources().getString(R.string.data_file_name)));
			outputFile.writeObject(eventsList);
			outputFile.flush();
			outputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
