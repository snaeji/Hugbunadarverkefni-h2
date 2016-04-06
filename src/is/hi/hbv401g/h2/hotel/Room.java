package is.hi.hbv401g.h2.hotel;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;


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

	public boolean isAvailable(Date dateFrom, Date dateTo, int amountOfRooms){
		if(amountOfRooms>this.roomCount) return false;
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTimeInMillis(0);
		calFrom.set(dateFrom.getYear(), dateFrom.getMonth(), dateFrom.getDate(), 0, 0, 0);
		dateFrom = calFrom.getTime(); // get back a Date object
		
		Calendar calTo = Calendar.getInstance();
		calTo.setTimeInMillis(0);
		calTo.set(dateTo.getYear(), dateTo.getMonth(), dateTo.getDate(), 23, 59, 59);
		dateTo = calTo.getTime(); // get back a Date object
		
		int counter = 0;
		for (Date systemDate : this.theseDates) {
			counter++;
			Calendar calSys = Calendar.getInstance();
			calSys.setTimeInMillis(0);
			calSys.set(systemDate.getYear(), systemDate.getMonth(), systemDate.getDate(), 16, 00, 00);
			systemDate = calSys.getTime(); // get back a Date object
			
			if(dateFrom.before(systemDate)&&systemDate.before(dateTo)){
				if(this.reservedCounter.get(counter)<amountOfRooms)return false;
			}
		    
		}
		
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
