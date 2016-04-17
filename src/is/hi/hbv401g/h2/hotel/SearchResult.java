package is.hi.hbv401g.h2.hotel;

import java.util.Date;

import metaSearchEngine.program.HotelAbstract;

public class SearchResult extends HotelAbstract{
	
	private Room room;

	public SearchResult(Room room, Date startTime, Date endTime, int numSingle, int numDouble, int numMulti, int priceSingle,
			int priceDouble, int priceMulti, String Loc, String[] dealerInfo) {
		super(startTime, endTime, numSingle, numDouble, numMulti, priceSingle, priceDouble, priceMulti, Loc, dealerInfo);
		this.room = room;
	}

	public Room getRoom() {
		return this.room;
	}
}
