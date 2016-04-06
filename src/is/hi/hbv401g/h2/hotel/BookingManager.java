package is.hi.hbv401g.h2.hotel;

import java.util.Calendar;

public class BookingManager {
	private BookingDBController bookingDBController = new BookingDBController();
	private Booking[] bookings;
	
	// NOT STARTED
	public Booking book(Room[] rooms, Traveler traveler, Calendar dateFrom, Calendar dateTo) {
		Booking booking = new Booking(traveler, room, from, to);
		
		
		
		return new Booking();
	}
	
	// NOT STARTED
	Booking[] getBookings() {
		
		Booking[] bookings = new Booking[0];
		return bookings;
	}
	
	// NOT STARTED
	public Booking[] getBookingsByTraveler(Traveler traveler) {
		
		Booking[] bookings = new Booking[0];
		return bookings;
	}
	
	// NOT STARTED
	public void cancelBooking(Booking booking) {
		
	}
	
	// NOT STARTED
	public boolean editBooking(Booking booking, Calendar dateTo, Calendar dateFrom) {
		
		return false;
	}
}
