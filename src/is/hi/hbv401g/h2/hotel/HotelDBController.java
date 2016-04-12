package is.hi.hbv401g.h2.hotel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelDBController {

	public HotelDBController(){
		
	}

	public List<Room> searchWithAddress(int minPrice,int maxPrice,int minStars, String street, String city, String zipCode){
		List<Room> rooms = executeQueryRooms();
		List <Room> filteredRooms = new ArrayList<Room>();
		for(int i = 0;i<rooms.size();i++){
			if(		  rooms.get(i).getPrice()<=maxPrice
					&&rooms.get(i).getPrice()>=minPrice
					&&rooms.get(i).getHotel().getStars()>=minStars
					)
			{
				filteredRooms.add(rooms.get(i));
			};
		}
		rooms = filteredRooms;
		if(street!=null){
			for(int i = 0;i<rooms.size();i++){
				if(rooms.get(i).getHotel().getStreet().compareTo(street)==0)filteredRooms.add(rooms.get(i));
			}
			rooms = filteredRooms;
		}
		if(city!=null){
			for(int i = 0;i<rooms.size();i++){
				if(rooms.get(i).getHotel().getCity().compareTo(city)==0)filteredRooms.add(rooms.get(i));
			}
			rooms = filteredRooms;
		}
		if(zipCode!=null){
			for(int i = 0;i<rooms.size();i++){
				if(rooms.get(i).getHotel().getZipCode().compareTo(zipCode)==0)filteredRooms.add(rooms.get(i));
			}
			rooms = filteredRooms;
		}
		
		
		return rooms;
	};

	public List<Room> searchWithCoords(int minPrice,int maxPrice,int minStars, double radius, Coordinates coords){
		List<Room> rooms = executeQueryRooms();
		List <Room> filteredRooms = new ArrayList<Room>();
		for(int i = 0;i<rooms.size();i++){
			if(		  rooms.get(i).getPrice()<=maxPrice
					&&rooms.get(i).getPrice()>=minPrice
					&&rooms.get(i).getHotel().getStars()>=minStars
					&&rooms.get(i).getHotel().getCoordinates().distanceTo(coords)<=radius
			  )
			{
				filteredRooms.add(rooms.get(i));
			};
		}
		rooms = filteredRooms;
		
		return filteredRooms;
	};
	
	private List<Room> executeQueryRooms(){
		List <Room> rooms = new ArrayList<Room>();
		List <Integer> id = new ArrayList<Integer>();
		List <Date> reserveDates = new ArrayList<Date>();
		List <Integer> reserveCount = new ArrayList<Integer>();
		
		//Get reservedDates
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			preparedStatement = connection.prepareStatement("SELECT * from RoomReservations");
			rs = preparedStatement.executeQuery(); //Queery
			while(rs.next()){
				id.add(rs.getInt("id"));
				reserveDates.add(new Date(rs.getInt("date")));
				reserveCount.add(rs.getInt("count"));
				
			}
			preparedStatement.close();
			connection.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			return rooms;
		}
		
		//Get Rooms & Hotels.... and put reserveDates & reserveCount & Hotels etc into Rooms
		
		connection = null;
		preparedStatement = null;
		rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			connection.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			preparedStatement = connection.prepareStatement("SELECT * from Rooms,Hotels WHERE Hotels.name = Rooms.hotel");
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				List <Date> tempReserveDates = new ArrayList<Date>();
				List <Integer> tempReserveCount = new ArrayList<Integer>();
				
				//Create the reserveDates and reserveCount lists for this specific room
				for(int i = 0;i<id.size();i++){
					if(id.get(i)==rs.getInt("id"))
					{
						tempReserveDates.add(reserveDates.get(i));
						tempReserveCount.add(reserveCount.get(i));
					}
				}
				//Hotel(int stars, String name, String type, String street, String streetNumber, String city, String zipCode, Coordinates coordinates)
				//Room(int price, int area, int beds, int bedrooms, int roomCount, List<Date> reservedDates, List<Integer> reservedCounter, Hotel hotel, int id)
				Hotel hotel = new Hotel(rs.getInt("stars"),rs.getString("name"),rs.getString("type"),rs.getString("street"),rs.getString("name"),rs.getString("city"),rs.getString("zipCode"),new Coordinates(rs.getDouble("latitude"),rs.getDouble("longtitude")));
				Room room = new Room(rs.getInt("price"),rs.getInt("area"),rs.getInt("beds"),rs.getInt("bedrooms"),rs.getInt("roomCount"),tempReserveDates,tempReserveCount,hotel,rs.getInt("id"));
				rooms.add(room);
			}
			
			preparedStatement.close();
			connection.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			return rooms;
		}
		
		return rooms;
	}
	
	//update Room for cancelreserve & reserve
}
