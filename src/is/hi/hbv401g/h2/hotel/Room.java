package is.hi.hbv401g.h2.hotel;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Room {

	private int price;
	private int area;
	private int beds;
	private int bedrooms;
	private int roomCount;
	private List<Date> theseDates = new ArrayList<Date>();
	private List<Integer> reservedCounter = new ArrayList<Integer>();
	private Hotel hotel;

	public Room(int price, int area, int beds, int bedrooms, int roomCount, List<Date> theseDates, List<Integer> reservedCounter, Hotel hotel){
		this.price = price;
		this.area = area;
		this.beds = beds;
		this.bedrooms = bedrooms;
		this.roomCount = roomCount;
		this.theseDates = theseDates;
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

	public boolean isAvailable(Date dateFrom, Date dateTo){
		/* MISSING LOGIC 
		
			List<Date> theseDates;
			List<int> reservedCounter;

		*/
		return true;
	}

	public boolean reserve(Booking booking){
		/* MISSING LOGIC */
		return true;
	}

	public void cancelReserve(Booking booking){
		/* MISSING LOGIC */
	}
}
