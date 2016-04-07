package is.hi.hbv401g.h2.hotel;
import java.sql.*;

public class HotelDBController {


	
	// NOT FINISHED
	public Room[] searchWithAddress(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, String street, String city, String zipCode){
		Room[] room = new Room[0];
		ResultSet rs = Database.executeQuery(1,"a");
//		while ( rs.next() ) {
		// Here is a loop where we want to add to the room object
//			minPrice = rs.getInt("minprice");
//			city = rs.getString("city");
//		}
			
		
		return room;
		
	};

	// NOT FINISHED
	public Room[] searchWithCoords(int minPrice,int maxPrice,int minStars,int maxStars,String[] amenities, float radius, Coordinates coords){
		ResultSet rs = Database.executeQuery(1,"a");
		Room[] room = new Room[0];
		return room;
		};
}
