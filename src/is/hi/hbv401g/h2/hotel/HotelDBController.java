package is.hi.hbv401g.h2.hotel;

public class HotelDBController {

	
	// NOT FINISHED
	public Room[] searchWithAddress(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, String street, String city, String zipCode){
		
		Room[] room = new Room[0];
		return room;
		
	};

	// NOT FINISHED
	public Room[] searchWithCoords(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, float radius, Coordinates coords){
		return hotelDBController.searchWithCoords(minPrice,maxPrice,minStars,maxStars,amenities,radius,coords);
	};
}
