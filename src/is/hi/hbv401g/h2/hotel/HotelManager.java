package is.hi.hbv401g.h2.hotel;


public class HotelManager {
	private HotelDBController hotelDBController;

	public HotelManager(){
		this.hotelDBController = new HotelDBController();
	};

	public Room[] searchWithAddress(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, String street, String city, String zipCode){
		return hotelDBcontroller.searchWithAddress(minPrice,maxPrice,minStars,maxStars,amenities,street,city,zipCode);
	};

	public Room[] searchWithCoords(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, float radius, Coordinates coords){
		return hotelDBcontroller.searchWithCoords(minPrice,maxPrice,minStars,maxStars,amenities,radius,coords);
	};
}
