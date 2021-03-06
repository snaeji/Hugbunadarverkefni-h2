package is.hi.hbv401g.h2.hotel;

import java.util.List;

import metaSearchEngine.program.HotelAbstract;

import java.util.ArrayList;
import java.util.Date;

public class HotelManager {
	
	public static List<Room> searchWithAddress(int[] priceRange,int minStars, String street, String city, String zipCode, int count, Date fromDate, Date toDate){
		HotelDBController.init();
		return HotelDBController.searchWithAddress(priceRange[0],priceRange[1],minStars,street,city,zipCode,count, fromDate, toDate);
	};
	
	// a�fer� svo a� GUI gr�ppu lausnin virki
	public static List<HotelAbstract> searchWithAddress(int[] priceRange,int minStars, String city, Date fromDate, Date toDate){
		HotelDBController.init();
		
		List<Room> rooms = HotelDBController.searchWithAddress(priceRange[0],priceRange[1],minStars,null,city,null,1, fromDate, toDate);
		List<HotelAbstract> searchResults = new ArrayList<HotelAbstract>();
		
		for(int i=0;i<rooms.size();i++) {
			Room room = rooms.get(i);
			String[] dealerInfo = {room.getHotel().getName(), "555-5555", "hotel@hotel.com"};
			SearchResult searchResult = new SearchResult(room, fromDate, toDate, priceRange, room.getHotel().getCity(), dealerInfo, room.getPrice(), room.getArea(), room.getBeds(), room.getBedrooms());
			searchResults.add(searchResult);
		}
		return searchResults;
	};
	
	

	public List<Room> searchWithCoords(int minPrice,int maxPrice,int minStars, double radius, Coordinates coords,int count, Date fromDate, Date toDate){
		return HotelDBController.searchWithCoords(minPrice,maxPrice,minStars,radius,coords,count, fromDate, toDate);
	};
}