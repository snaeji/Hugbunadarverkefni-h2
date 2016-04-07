package is.hi.hbv401g.h2.hotel;

import java.util.Date;
//import java.util.Calendar;

public class Booking {
	
	private Date fromDate;
	private Date toDate;
	private Date dateCreated;
	private boolean hasReserved;
	private Room[] rooms;

	public Booking(Traveler traveler, Room [] room, Date fromDate, Date toDate) {
		long now = System.currentTimeMillis();
		this.dateCreated = new Date(now);
		this.rooms = room;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.hasReserved = false;
		//if(!room.reserve(this)) { // ÞARF AÐ GERA LYKKJU HÉR ÞAR SEM ÞETTA ER FYLKI AF ROOM (IMPORTANT!?)
		//	return null;
		//}
		for(Room roomy : rooms) roomy.reserve(this);
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
		for(Room room : this.rooms)room.cancelReserve(this);
	}
	
	boolean setDates(Date nextToDate, Date nextFromDate) {
		Date prevToDate = this.toDate;
		Date prevFromDate = this.fromDate;
		cancelReserve();
		this.toDate = nextToDate;
		this.fromDate = nextFromDate;
		boolean worked = true;
		for(Room room : this.rooms){
			if(!room.reserve(this)) worked = false;
		}
		if(worked) return true;
		//if(room.reserve(this)) {
		//	return true;
		//}
		this.toDate = prevToDate;
		this.fromDate = prevFromDate;
		worked = true;
		for(Room room : this.rooms){
			if(!room.reserve(this)) worked = false;
		}
		//if(worked) return false;
		return false;
		//if(room.reserve(this)){
		//	return false;
		//}
	}
}

