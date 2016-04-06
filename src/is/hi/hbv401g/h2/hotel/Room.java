package is.hi.hbv401g.h2.hotel;

import java.util.Date;
import java.util.List;

public class Room {

	private int price;
	private int area;
	private int beds;
	private int bedrooms;
	private int roomCount;
	private List<Date> reservedDates;
	private List<Integer> reservedCounter;
	private Hotel hotel;

	public Room(int price, int area, int beds, int bedrooms, int roomCount, List<Date> reservedDates, List<Integer> reservedCounter, Hotel hotel) {
		this.price = price;
		this.area = area;
		this.beds = beds;
		this.bedrooms = bedrooms;
		this.roomCount = roomCount;
		this.reservedDates = reservedDates;
		this.reservedCounter = reservedCounter;
		this.hotel = hotel;
	};

	public int getPrice (){
		return this.price;
	}

	public int getArea(){
		return this.area;
	}

	public int getBeds(){
		return this.beds;
	}

	public int getBedrooms(){
		return this.bedrooms;
	}

	public Hotel getHotel(){
		return this.hotel;
	}

	// NOT FINISHED
	public boolean isAvailable(Date dateFrom, Date dateTo){
		/* MISSING LOGIC 
		
			List<Date> theseDates;
			List<int> reservedCounter;

		*/
		return false;
	}

	// NOT FINISHED
	public boolean reserve(Date dateFrom, Date dateTo, Booking booking){
		/* MISSING LOGIC */
		return false;
	}

	public void cancelReserve(Date dateFrom, Date dateTo, Booking booking){
		/* MISSING LOGIC */
	}
}
