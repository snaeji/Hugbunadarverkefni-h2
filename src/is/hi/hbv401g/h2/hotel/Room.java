package is.hi.hbv401g.h2.hotel;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Room {

	private int price;
	private int area;
	private int beds;
	private int bedrooms;
	private int roomCount;
	private List<Date> reservedDates = new ArrayList<Date>();
	private List<Integer> reservedCounter = new ArrayList<Integer>();
	private Hotel hotel;

	public Room(int price, int area, int beds, int bedrooms, int roomCount, List<Date> reservedDates, List<Integer> reservedCounter, Hotel hotel){
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

	@SuppressWarnings("deprecation")
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
		for (Date systemDate : this.reservedDates) {
				
			Calendar calSys = Calendar.getInstance();
			calSys.setTimeInMillis(0);
			calSys.set(systemDate.getYear(), systemDate.getMonth(), systemDate.getDate(), 16, 00, 00);
			systemDate = calSys.getTime(); // get back a Date object
			
			if(dateFrom.before(systemDate)&&systemDate.before(dateTo)){
				if(this.reservedCounter.get(counter)<amountOfRooms)return false;
			}
			counter++;
		    
		}
		
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean reserve(Booking booking){
		
		/* MISSING LOGIC */
		
		Date dateFrom = new Date(booking.getFromDate().getTime());
		Date dateTo = new Date(booking.getToDate().getTime());
		
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTimeInMillis(0);
		calFrom.set(dateFrom.getYear(), dateFrom.getMonth(), dateFrom.getDate(), 16, 0, 0);
		dateFrom = calFrom.getTime();
		
		Calendar calTo = Calendar.getInstance();
		calTo.setTimeInMillis(0);
		calTo.set(dateTo.getYear(), dateTo.getMonth(), dateTo.getDate(), 16, 0, 0);
		dateTo = calTo.getTime();
		
		long oneDay = TimeUnit.DAYS.toMillis(1);
		
		while(!dateFrom.after(dateTo)){
			boolean b = false;
			int counter = 0;
			for (Date systemDate : this.reservedDates) {
				// Check if dateFrom is in reservedDates
				Calendar calSys = Calendar.getInstance();
				calSys.setTimeInMillis(0);
				calSys.set(systemDate.getYear(), systemDate.getMonth(), systemDate.getDate(), 16, 0, 0);
				systemDate = calSys.getTime();
				
			    if(dateFrom.compareTo(systemDate)==0){
			    	this.reservedCounter.set(counter, this.reservedCounter.get(counter)-1);
			    	b = true;
			    	break;
			    }
			    counter++;
			}
			if(b==false){
				// insert new date, add dateFrom to List
				reservedDates.add(dateFrom);
				// add reservedCounter
				reservedCounter.add(this.roomCount-1);
			}
			//increase dateFrom
			dateFrom = new Date(dateFrom.getTime()+oneDay);
		}
		
		//Change reservedCounter and reservedRooms in Database... ?
		// Change to void function? 
		
		return true;
	}

	@SuppressWarnings("deprecation")
	public void cancelReserve(Booking booking){
		/* MISSING LOGIC */
		
		Date dateFrom = new Date(booking.getFromDate().getTime());
		Date dateTo = new Date(booking.getToDate().getTime());
		
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTimeInMillis(0);
		calFrom.set(dateFrom.getYear(), dateFrom.getMonth(), dateFrom.getDate(), 16, 0, 0);
		dateFrom = calFrom.getTime(); 
		
		Calendar calTo = Calendar.getInstance();
		calTo.setTimeInMillis(0);
		calTo.set(dateTo.getYear(), dateTo.getMonth(), dateTo.getDate(), 16, 0, 0);
		dateTo = calTo.getTime(); 
		
		long oneDay = TimeUnit.DAYS.toMillis(1);
		
		while(!dateFrom.after(dateTo)){
			int counter = 0;
			for (Date systemDate : this.reservedDates) {
				// Check if dateFrom is in reservedDates
				Calendar calSys = Calendar.getInstance();
				calSys.setTimeInMillis(0);
				calSys.set(systemDate.getYear(), systemDate.getMonth(), systemDate.getDate(), 16, 0, 0);
				systemDate = calSys.getTime();
				
			    if(dateFrom.compareTo(systemDate)==0){
			    	this.reservedCounter.set(counter, this.reservedCounter.get(counter)+1);
			    	break;
			    }
			    counter++;
			}
			dateFrom = new Date(dateFrom.getTime()+oneDay);
		}
		
		//Change reservedCounter and reservedRooms in Database... ?
	}
}
