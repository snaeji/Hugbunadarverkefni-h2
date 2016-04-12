package is.hi.hbv401g.h2.hotel;

import java.util.List;

public class HotelManager {
	private HotelDBController hotelDBController;

	public HotelManager(){
		this.hotelDBController = new HotelDBController();
	};

	public List<Room> searchWithAddress(int minPrice,int maxPrice,int minStars, String street, String city, String zipCode){
		return hotelDBController.searchWithAddress(minPrice,maxPrice,minStars,street,city,zipCode);
	};

	public List<Room> searchWithCoords(int minPrice,int maxPrice,int minStars, double radius, Coordinates coords){
		return hotelDBController.searchWithCoords(minPrice,maxPrice,minStars,radius,coords);
	};
}