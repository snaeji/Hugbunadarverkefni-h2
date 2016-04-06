package is.hi.hbv401g.h2.hotel;


public class Room {

	private int price;
	private int area;
	private int beds;
	private int bedrooms;
	private int roomCount;
	private List<Date> theseDates;
	private List<int> reservedCounter;
	private Hotel hotel;

	public Room(int price, int area, int beds, int bedrooms, int roomCount, List<Date> theseDates, List<int> reservedCounter, Hotel hotel){
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

	public boolean isAvailable(Date date){
		/* MISSING LOGIC */

	}

	public boolean reserve(Date dateTo, Date dateFrom, Booking booking){
		/* MISSING LOGIC */
	}

	public void cancelReserve(Date dateTo, Date dateFrom, Booking booking){
		/* MISSING LOGIC */
	}
}
