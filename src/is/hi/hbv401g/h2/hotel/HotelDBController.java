package is.hi.hbv401g.h2.hotel;
import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelDBController {

	public HotelDBController(){
		createDB();
		createTableHotels();
		createTableRooms();
		createTableReservations();
		insertIntoDBStuff();
	}

	public List<Room> searchWithAddress(int minPrice,int maxPrice,int minStars, String street, String city, String zipCode,int count, Date fromDate, Date toDate){
		List<Room> rooms = executeQueryRooms();
		for(int i = rooms.size()-1;i>=0;i--){
			if(		  !(rooms.get(i).getPrice()<=maxPrice
					&&rooms.get(i).getPrice()>=minPrice
					&&rooms.get(i).getHotel().getStars()>=minStars
					&&rooms.get(i).isAvailable(fromDate, toDate, count)
					)
			)
			{
				rooms.remove(i);
			};
		}
		if(street!=null){
			for(int i = rooms.size()-1;i>=0;i--){
				if(rooms.get(i).getHotel().getStreet().compareTo(street)!=0&&street.compareTo("")!=0)rooms.remove(i);
			}
		}
		if(city!=null){
			for(int i = rooms.size()-1;i>=0;i--){
				if(rooms.get(i).getHotel().getCity().compareTo(city)!=0&&street.compareTo("")!=0)rooms.remove(i);
			}
		}
		if(zipCode!=null){
			for(int i = rooms.size()-1;i>=0;i--){
				if(rooms.get(i).getHotel().getZipCode().compareTo(zipCode)!=0&&street.compareTo("")!=0)rooms.remove(i);
			}
		}
		return rooms;
	};

	public List<Room> searchWithCoords(int minPrice,int maxPrice,int minStars, double radius, Coordinates coords,int count, Date fromDate, Date toDate){
		List<Room> rooms = executeQueryRooms();
		List <Room> filteredRooms = new ArrayList<Room>();
		for(int i = rooms.size()-1;i>=0;i--){
			if(		  !(rooms.get(i).getPrice()<=maxPrice
					&&rooms.get(i).getPrice()>=minPrice
					&&rooms.get(i).getHotel().getStars()>=minStars
					&&rooms.get(i).getHotel().getCoordinates().distanceTo(coords)<=radius
					&&rooms.get(i).isAvailable(fromDate, toDate, count)
					)
			  )
			{
				rooms.remove(i);
			};
		}
		
		return rooms;
	};
	
	void changeReservations(int id,List <Date> reservedDates,List <Integer> reservedCounter){
		System.out.println("Updating room reservations");
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			System.out.println("changeReservations: ...");

			statement = connection.createStatement();
			String sql = "DELETE FROM Reservations WHERE id="+id+";"; 
			statement.executeUpdate(sql);
			
			
			
			statement = connection.createStatement();
			sql = "INSERT INTO Reservations (id,date,count) VALUES ";
			if(reservedDates.size()>0)sql = sql + " ("+id+",'"+reservedDates.get(0).getTime()+"',"+reservedCounter.get(0)+")";
			System.out.println("inside test"+reservedDates.get(0).toString());
			for(int i = 1;i<reservedDates.size();i++){
				sql = sql + " ,("+id+",'"+reservedDates.get(i).getTime()+"',"+reservedCounter.get(i)+")";
			}
			sql = sql+";";
			statement.executeUpdate(sql);
			
			
			
			statement.close();
			connection.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("changeReservations: success");
		
	}
	
	@SuppressWarnings("deprecation")
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
			System.out.println("executeQueryRooms: begin");
			
			preparedStatement = connection.prepareStatement("SELECT * FROM Reservations");
			rs = preparedStatement.executeQuery(); //Queery
			while(rs.next()){
				id.add(rs.getInt("id"));
				reserveDates.add(new Date(rs.getLong("date")));
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
			System.out.println("executeQueryRooms: next");
			
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
				Hotel hotel = new Hotel(rs.getInt("stars"),rs.getString("name"),rs.getString("type"),rs.getString("street"),rs.getString("streetNumber"),rs.getString("city"),rs.getString("zipCode"),new Coordinates(rs.getDouble("latitude"),rs.getDouble("longtitude")));
				//Room(int price, int area, int beds, int bedrooms, int roomCount, List<Date> reservedDates, List<Integer> reservedCounter, Hotel hotel, int id)
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
		System.out.println("executeQueryRooms: success");
		return rooms;
	}
	
	 private void createDB(){
		@SuppressWarnings("unused")
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("createDB: success");
		return;
	}
	 
	// Here we make the Hotels table
	private void createTableHotels()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			System.out.println("createTableHotels: ...");

			stmt = c.createStatement();
//Hotel(int stars, String name, String type, String street, String streetNumber, String city, String zipCode, Coordinates coordinates)
			String sql = "CREATE TABLE IF NOT EXISTS Hotels " +
					"(name           TEXT    PRIMARY KEY, " + 
					" stars          INT	 NOT NULL, " +
					" type           TEXT    NOT NULL, " + 
					" street         TEXT    NOT NULL, " + 
					" streetnumber   TEXT    NOT NULL, " +
					" city           TEXT    NOT NULL, " + 
					" zipCode        TEXT    NOT NULL, " + 
					" latitude       REAL	 NOT NULL, " +
					" longtitude	 REAL	 NOT NULL )"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("createTableHotels: success");
	}
	// Here we make the Rooms table
	private void createTableRooms()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			System.out.println("createTableRooms: ...");

			stmt = c.createStatement();
//Room(int price, int area, int beds, int bedrooms, int roomCount, List<Date> reservedDates, List<Integer> reservedCounter, Hotel hotel, int id)
			String sql = "CREATE TABLE IF NOT EXISTS Rooms " +
				"(id 			 INT 	 PRIMARY KEY," +
				" price          INT     NOT NULL, " + 
				" area           INT     NOT NULL, " + 
				" beds         	 INT     NOT NULL, " + 
				" bedrooms       INT     NOT NULL, " +
				" roomCount      INT     NOT NULL, " + 
				" hotel          TEXT    NOT NULL, " + 
				" stars          INT	 NOT NULL, " +
				" FOREIGN KEY(hotel) REFERENCES Hotels(name))";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("createTableRooms: success");
	}
	private void createTableReservations()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			System.out.println("createTableReservations: ...");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Reservations" +
					"(id         	INT     NOT NULL, " + 
					" count     	INT     NOT NULL, " + 
					" date          TEXT    NOT NULL, " + 
					" FOREIGN KEY(id) REFERENCES Rooms(id))"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("createTableReservations: success");
	}
	// Here we insert some junk test data
	private void insertIntoDBStuff()
	{
		List<Room> rooms = executeQueryRooms();
		if(rooms.size()>0)return;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			c.setAutoCommit(false);
			System.out.println("insertIntoDBStuff: ...");

			stmt = c.createStatement();
			String sql = "INSERT INTO Hotels (name,stars,type,street,streetnumber,city,zipCode,latitude,longtitude) " +
					"VALUES ('hotel1', 3,'hostel', 'gata1', '1',  'rvk', '101', 1.0,1.0),  "+
						   "('hotel2', 4,'hotel', 'gata2', '2',  'rvk', '102', 5.0,5.0), " +
						   "('hotel3', 5,'dorm', 'gata3', '3',  'rvk', '103', 10.0,10.0)"; 
			stmt.executeUpdate(sql);
			
			stmt = c.createStatement();
			sql = "INSERT INTO Rooms (id,price,area,beds,bedrooms,roomCount,hotel,stars) " +
					"VALUES (1, 1,15, 1, 1,  5, 'hotel1',3),  "+
						   "(2, 2,20, 2, 2,  5, 'hotel2',4), " +
						   "(3, 3,30, '3', 3,  5, 'hotel3',5)"; 
			stmt.executeUpdate(sql);
			
			//stmt = c.createStatement();
			//sql = "INSERT INTO Reservations (id,date,count) " +
			//		"VALUES ('1', 3000,15, 1, 1,  5, 'hotel1'),  "+
			//			   "('2', 5000,20, 2, 2,  5, 'hotel2'), " +
			//			   "('3', 10000,30, 3', 3,  5, 'hotel3')"; 
			//stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("insertIntoDBStuff: success");
	}
	
	//update Room for cancelreserve & reserve
}
