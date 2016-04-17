package is.hi.hbv401g.h2.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import metaSearchEngine.program.HotelAbstract;
import metaSearchEngine.program.UserClass;

public class BookingManager {
	
// uselesss comment for Haukur
	public static Booking book(UserClass traveler, List<Room> rooms, Date fromDate, Date toDate) {
		Integer id = null;
		Booking booking = new Booking(traveler, rooms, fromDate, toDate, id);
		BookingDBController.book(booking);
		return booking;
	}
	
	// spes aðferð svo að GUI grúppu lausnin virki
	public static Booking book(UserClass traveler, List<HotelAbstract> sadThing) {
		List<Room> rooms = new ArrayList<Room>();
		SearchResult searchResult;
		Date fromDate = sadThing.get(0).getStartTime();
		Date toDate = sadThing.get(0).getEndTime();
		
		for(int i=0;i<sadThing.size();i++) {
			searchResult = (SearchResult)sadThing.get(i);
			rooms.add(searchResult.getRoom());
		}
		
		Integer id = null;
		Booking booking = new Booking(traveler, rooms, fromDate, toDate, id);
		BookingDBController.book(booking);
		return booking;
	}	

	/*
	public Booking[] getBookingsByTraveler(Traveler traveler) {
		Booking[] bookings = bookingDBController.getBookingsByTraveler(traveler);
		return bookings;
	}*/
	
	public void cancelBooking(Booking booking) {
		BookingDBController.cancelBooking(booking);
		booking.cancelReserve();
	}
	
	public boolean editBooking(Booking booking, Date fromDate, Date toDate) {
		if(booking.setDates(fromDate, toDate)) return true;
		return false;
	}
}
