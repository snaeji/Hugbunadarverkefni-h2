package is.hi.hbv401g.h2.hotel;

import java.util.Date;

public class BookingManager {
	private BookingDBController bookingDBController = new BookingDBController();
//	private Booking[] bookings;
	
	
	// FINISHED
	public Booking book(Traveler traveler, Room[] rooms, Date fromDate, Date toDate) {
		Booking booking = new Booking(traveler, rooms, fromDate, toDate);
		bookingDBController.book(booking);
		return booking;
	}
	
	// NOT USED - SHOULD BE REMOVED?
//	Booking[] getBookings() {
//		return bookingDBController.getBookings();
//	}
	
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
	public boolean editBooking(Booking booking, Date fromDate, Date toDate) {
		if(booking.setDates(fromDate, toDate)) return true;
		return false;
	}
}
