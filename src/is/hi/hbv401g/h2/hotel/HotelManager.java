package is.hi.hbv401g.h2.hotel;

import java.util.List;

import metaSearchEngine.program.HotelAbstract;

import java.util.Date;

public class HotelManager {
//	private HotelDBController hotelDBController;

//	public HotelManager(){
//		this.hotelDBController = new HotelDBController();
//	};

//	public static List<Room> searchWithAddress(int[] priceRange,int minStars, String street, String city, String zipCode, int count, Date fromDate, Date toDate){
//		return HotelDBController.searchWithAddress(priceRange[0],priceRange[1],minStars,street,city,zipCode,count, fromDate, toDate);
//	};
	
	public static List<HotelAbstract> searchWithAddress(int[] priceRange,int minStars, String street, String city, String zipCode, int count, Date fromDate, Date toDate){
		List<Room> rooms = HotelDBController.searchWithAddress(priceRange[0],priceRange[1],minStars,street,city,zipCode,count, fromDate, toDate);
		List<SearchResult> searchResults;
		
//		Room room, Date startTime, Date endTime, int numSingle, int numDouble, int numMulti, int priceSingle,
//		int priceDouble, int priceMulti, String Loc, String[] dealerInfo
		
//		public HotelAbstract(Date startTime, Date endTime, int[] priceRange, String location, String[] dealerInfo) {

		
		
		for(int i=0;i<rooms.size();i++) {
			Room room = rooms.get(i);
			String[] dealerInfo = {room.getHotel().getName(), "555-5555", "hotel@hotel.com"};
			SearchResult searchResult = new SearchResult(room, fromDate, toDate, 0, 0, 0, 0, 0, 0, room.getHotel().getCity(), dealerInfo );
			searchResults.add(searchResult);
			return searchResults;
		}
	};

	public List<Room> searchWithCoords(int minPrice,int maxPrice,int minStars, double radius, Coordinates coords,int count, Date fromDate, Date toDate){
		return HotelDBController.searchWithCoords(minPrice,maxPrice,minStars,radius,coords,count, fromDate, toDate);
	};
}