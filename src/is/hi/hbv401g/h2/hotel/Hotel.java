package is.hi.hbv401g.h2.hotel;

public class Hotel {
	int stars;
	String name;
	String type;
	String street;
	String streetNumber;
	String city;
	String zipCode;
	Room[] rooms;
	
	public Hotel(int stars, String name, String type, String street, String streetNumber, String city, String zipCode) {
		this.stars = stars;
		this.name = name;
		this.type = type;
		this.street = street;
		this.streetNumber = streetNumber;
		this.city = city;
		this.zipCode = zipCode;
	}
	
	public int getStars() {
		return this.stars;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public Room[] getRooms() {
		return rooms;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getZipCode() {
		return zipCode;
	}
}
