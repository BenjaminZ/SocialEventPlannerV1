package au.edu.rmit.daniel.socialeventplannerv1.util;

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
import android.util.Log;
import au.edu.rmit.daniel.socialeventplannerv1.model.event.Events;
import au.edu.rmit.daniel.socialeventplannerv1.R;

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
			Log.d("Exeptions", e.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
		}
		if (eventsList == null) {
			eventsList = new ArrayList<Events>();
		}
		return eventsList;
	}
	
	public static void saveData(Context context, Events event) {
		getData(context);
		eventsList.add(event);
		saveData(context);
	}
	
	private static void saveData(Context context) {
		try {
			
			ObjectOutputStream outputFile = 
					new ObjectOutputStream(
							new FileOutputStream(context.getFilesDir() + File.separator + context.getResources().getString(R.string.data_file_name)));
			outputFile.writeObject(eventsList);
			outputFile.flush();
			outputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
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
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
		} catch (NotFoundException e) {
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("Exeptions", e.getMessage());
		}
	}
	
	public static void deleteData(Context context, Events event) {
		getData(context);
		for (Events toDelete : eventsList) {
			if (toDelete.getId() == event.getId()) {
				eventsList.remove(toDelete);
				break;
			}
		}
		saveData(context);
	}
}
