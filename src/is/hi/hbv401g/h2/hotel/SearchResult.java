package is.hi.hbv401g.h2.hotel;

import java.util.Date;

import metaSearchEngine.program.HotelAbstract;

public class SearchResult extends HotelAbstract{
	private Room room;
	
	public SearchResult(Room room, Date startTime, Date endTime, int[] priceRange, String location, String[] dealerInfo, int price,
			int area, int numOfBeds, int numOfBedrooms) {
		super(startTime, endTime, priceRange, location, dealerInfo, price, area, numOfBeds, numOfBedrooms);
		this.room = room;
	}	
	
	public Room getRoom() {
		return this.room;
	}
}
