package is.hi.hbv401g.h2.hotel;


public class HotelManager {
	private HotelDBController hotelDBController;

	public HotelManager(){
		this.hotelDBController = new HotelDBController();
	};

	public Room[] searchWithAddress(int minPrice,int maxPrice,int minStars,int maxStars, String street, String city, String zipCode){
		return hotelDBController.searchWithAddress(minPrice,maxPrice,minStars,maxStars,street,city,zipCode);
	};

	public Room[] searchWithCoords(int minPrice,int maxPrice,int minStars,int maxStars, double radius, Coordinates coords){
		return hotelDBController.searchWithCoords(minPrice,maxPrice,minStars,maxStars,radius,coords);
	};
}