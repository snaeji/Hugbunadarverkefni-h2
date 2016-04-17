package is.hi.hbv401g.h2.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import metaSearchEngine.program.HotelAbstract;
import metaSearchEngine.program.UserClass;

public class BookingManager {
	
//	private BookingDBController bookingDBC;
//	
//	public BookingManager() {
//		bookingDBC = new BookingDBController();
//	}
	
	// FINISHED
	public static Booking book(UserClass traveler, List<Room> rooms, Date fromDate, Date toDate) {
		Integer id = null;
		Booking booking = new Booking(traveler, rooms, fromDate, toDate, id);
		BookingDBController.book(booking);
		return booking;
	}
	
	public static Booking book(List<HotelAbstract> sadThing) {
		List<Room> rooms = new ArrayList<Room>();
		SearchResult searchResult;
		
		for(int i=0;i<sadThing.size();i++) )
			 = (SearchResult)sadThing;
		Room room = searchResult.getRoom();
		Integer id = null;
		Booking booking = new Booking(traveler, rooms, fromDate, toDate, id);
		BookingDBController.book(booking);
		return booking;
	}
	
	// NOT USED - SHOULD BE REMOVED?
//	Booking[] getBookings() {
//		return bookingDBController.getBookings();
//	}
	
	// FINISHED
	/*
	public Booking[] getBookingsByTraveler(Traveler traveler) {
		Booking[] bookings = bookingDBController.getBookingsByTraveler(traveler);
		return bookings;
	}*/
	
	// FINISHED
	public void cancelBooking(Booking booking) {
		BookingDBController.cancelBooking(booking);
		booking.cancelReserve();
	}
	
	// FINISHED
	public boolean editBooking(Booking booking, Date fromDate, Date toDate) {
		if(booking.setDates(fromDate, toDate)) return true;
		return false;
	}
}
