package is.hi.hbv401g.h2.hotel;

import java.sql.Date;
import java.util.Calendar;

public class Booking {
	
	private Date fromDate;
	private Date toDate;
	private Date dateCreated;
	private boolean hasReserved;
	private Room[] rooms;

	public Booking(Traveler traveler, Room[] room, Date fromDate, Date toDate) {
		long now = System.currentTimeMillis();
		this.dateCreated = new Date(now);
		this.rooms = room;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.hasReserved = false;
		if(!room.reserve(this)) { // ÞARF AÐ GERA LYKKJU HÉR ÞAR SEM ÞETTA ER FYLKI AF ROOM (IMPORTANT!?)
			return null;
		}
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	
	public Date getToDate() {
		return toDate;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	
	boolean containsRoom(Room room) {
		for(Room aRoom: rooms) {
			if(aRoom == room) return true;
		}
		return false;
	}
	
	void reserve() {
		hasReserved = true;
	}
	
	boolean hasReserved() {
		return hasReserved;
	}
	
	void cancelReserve() {
		this.hasReserved = false;
		room.cancelReserve(this);
	}
	
	boolean setDates(Date nextToDate, Date nextFromDate) {
		Date prevToDate = this.toDate;
		Date prevFromDate = this.fromDate;
		cancelReserve();
		this.toDate = nextToDate;
		this.fromDate = nextFromDate;
		if(room.reserve(this)) {
			return true;
		}
		this.toDate = prevToDate;
		this.fromDate = prevFromDate;
			if(room.reserve(this)){
				return false;
			}
		}
	}
}
