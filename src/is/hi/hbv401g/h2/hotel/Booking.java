package is.hi.hbv401g.h2.hotel;

import java.sql.Date;
import java.util.Calendar;

public class Booking {
	
	private Calendar fromDate;
	private Calendar toDate;
	private Calendar dateCreated;
	private boolean hasReserved;
	private Room room;

	public Booking(Room room) {
		this.dateCreated = Calendar.getInstance();
		this.room = room;
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
	
	boolean containsRoom(Room room) {
		if (this.room.equals((Room.room)) return true;
		return this.room == room;
	}
	
	package void reserve(void) {
		// this can fail
		room.reserve(fromDate, toDate, this);
	}

	
}
