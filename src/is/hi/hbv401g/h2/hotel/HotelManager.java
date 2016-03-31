package is.hi.hbv401g.h2.hotel;


public class HotelManager {
	private HotelDBController hotelDBController;

	public HotelManager(){
		hotelDBController = new HotelDBController();
	}

	public Room[] searchWithAddress(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, String street, String city, String zipCode){
		/* 
		 	MISSING
		*/
	}
	public Room[] searchWithCoords(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, float radius, Coordinates coords){
		/* 
		 	MISSING
		*/
	}
}
