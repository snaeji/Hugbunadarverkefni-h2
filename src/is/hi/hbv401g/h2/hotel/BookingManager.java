package is.hi.hbv401g.h2.hotel;

import java.util.Calendar;

public class BookingManager {
	private BookingDBController bookingDBController = new BookingDBController();
//	private Booking[] bookings;
	
	
	// FINISHED
	public Booking book(Traveler traveler, Room[] rooms, Calendar fromDate, Calendar toDate) {
		Booking booking = new Booking(traveler, rooms, fromDate, toDate);
		bookingDBController.book(booking);
		return booking;
	}
	
	// FINISHED
	Booking[] getBookings() {
		return bookingDBController.getBookings();
	}
	
	// FINISHED
	public Booking[] getBookingsByTraveler(Traveler traveler) {
		Booking[] bookings = bookingDBController.getBookingsByTraveler(traveler);
		return bookings;
	}
	
	// FINISHED
	public void cancelBooking(Booking booking) {
		bookingDBController.cancelBooking(booking);
		booking.cancelReserve();
	}
	
	// FINISHED
	public boolean editBooking(Booking booking, Calendar fromDate, Calendar toDate) {
		if(booking.setDates(fromDate, toDate)) return true;
		return false;
	}
}
