package is.hi.hbv401g.h2.hotel;

import java.sql.Date;
import java.util.Calendar;

public class Booking {
	
	private Calendar fromDate;
	private Calendar toDate;
	private Calendar dateCreated;
	private boolean hasReserved;
	private Room room;

	public Booking() {
		this.dateCreated = Calendar.getInstance();
	}

	public Calendar getDateCreated() {
		return dateCreated;
	}
	
	public Calendar getToDate() {
		return toDate;
	}
	
	public Calendar getFromDate() {
		return fromDate;
	}
	
	package boolean containsRoom(Room room) {
		return this.room == room;
	}
	
	package void reserve(void) {
		// this can fail
		room.reserve(fromDate, toDate, this);
	}

	
}
