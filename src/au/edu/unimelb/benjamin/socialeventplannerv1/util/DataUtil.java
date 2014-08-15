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

import au.edu.unimelb.benjamin.socialeventplannerv1.R;
import android.content.Context;
import au.edu.unimelb.benjamin.socialeventplannerv1.event.Events;

public class DataUtil {
	//TODO Android IO
	
	private static ArrayList<Events> eventsList;

	@SuppressWarnings("unchecked")
	public static ArrayList<Events> getData(Context context) {
		try {
			ObjectInputStream inputFile = 
					new ObjectInputStream(
							new FileInputStream(context.getFilesDir() + File.separator + R.string.data_file_name));
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
		return new ArrayList<Events>();
	}
	
	public static void saveData(Context context, Events event) {
		
		try {
			
			getData(context);
			if (eventsList == null) {
				eventsList = new ArrayList<Events>();
			}
			eventsList.add(event);
			ObjectOutputStream outputFile = 
					new ObjectOutputStream(
							new FileOutputStream(context.getFilesDir() + File.separator + R.string.data_file_name));
			outputFile.writeObject(eventsList);
			outputFile.flush();
			outputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
