package is.hi.hbv401g.h2.hotel;

public class Hotel {
	private int stars;
	private String name;
	private String type;
	private String street;
	private String streetNumber;
	private String city;
	private String zipCode;
	//private Room[] rooms;
	private Coordinates coordinates;


	//public Hotel(int stars, String name, String type, String street, String streetNumber, String city, String zipCode, Room[] rooms, Coordinates coordinates) {
	public Hotel(int stars, String name, String type, String street, String streetNumber, String city, String zipCode, Coordinates coordinates) {
		this.stars = stars;
		this.name = name;
		this.type = type;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.zipCode = zipCode;
		//this.rooms = rooms;
		this.coordinates = coordinates;
	}
	
	public int getStars() {
		return this.stars;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	/*public Room[] getRooms() {
		return this.rooms;
	}*/
	
	public String getStreet() {
		return this.street;
	}
	
	public String getStreetNumber() {
		return this.streetNumber;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getZipCode() {
		return this.zipCode;
	}

	public Coordinates getCoordinates(){
		return this.coordinates;
	}
}
