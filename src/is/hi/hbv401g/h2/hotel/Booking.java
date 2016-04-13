package is.hi.hbv401g.h2.hotel;

import java.util.List;
import java.util.Date;
//import java.util.Calendar;

public class Booking {

	private Date fromDate;
	private Date toDate;
	private boolean hasReserved;
	private List<Room> rooms;
	private Traveler traveler;
	private Integer id;
	
	public Booking(Traveler traveler, List<Room> room, Date fromDate, Date toDate, Integer id) {
		this.rooms = room;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.hasReserved = false;
		this.traveler = traveler;
		this.id = id;
		//if(!room.reserve(this)) { // ÞARF AÐ GERA LYKKJU HÉR ÞAR SEM ÞETTA ER FYLKI AF ROOM (IMPORTANT!?)
		//	return null;
		//}
		for(Room roomy : rooms) roomy.reserve(this);
	}

	public Traveler getTraveler() {
		return traveler;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public Date getToDate() {
		return toDate;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	
	public List<Room> getRooms() {
		return this.rooms;
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

