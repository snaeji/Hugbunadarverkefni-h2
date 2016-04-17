package is.hi.hbv401g.h2.hotel;

import java.util.List;
import java.util.Date;

public class HotelManager {
//	private HotelDBController hotelDBController;

//	public HotelManager(){
//		this.hotelDBController = new HotelDBController();
//	};

	public static List<Room> searchWithAddress(int minPrice,int maxPrice,int minStars, String street, String city, String zipCode, int count, Date fromDate, Date toDate){
		return HotelDBController.searchWithAddress(minPrice,maxPrice,minStars,street,city,zipCode,count, fromDate, toDate);
	};

	public List<Room> searchWithCoords(int minPrice,int maxPrice,int minStars, double radius, Coordinates coords,int count, Date fromDate, Date toDate){
		return HotelDBController.searchWithCoords(minPrice,maxPrice,minStars,radius,coords,count, fromDate, toDate);
	};
}