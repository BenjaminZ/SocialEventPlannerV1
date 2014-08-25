package au.edu.rmit.daniel.socialeventplannerv1.model;

public class CalendarDate {

	private int day, month, year;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		if ((month < 12) && (month >= 0)) {
			this.month = month;
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if ((day > 0) || (day <= 31)) {
			this.day = day;
		}
	}
	
	
}
