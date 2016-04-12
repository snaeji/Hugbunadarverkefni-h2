package is.hi.hbv401g.h2.hotel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Database {
	// Here we create the hotels.db database
		public static void initDB(){
			@SuppressWarnings("unused")
			Connection c = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Opened database successfully");
			return;
		}
		// Here we make the Hotels table
		public static void createTableHotels()
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sql = "CREATE TABLE IF NOT EXISTS Hotels " +
						"(ID INT PRIMARY KEY              ," +
						" name           TEXT    NOT NULL, " + 
						" type           TEXT    NOT NULL, " + 
						" street         TEXT    NOT NULL, " + 
						" streetnumber   TEXT    NOT NULL, " +
						" city           TEXT    NOT NULL, " + 
						" zipCode        TEXT    NOT NULL, " + 
						" stars          INT		NOT NULL )"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Table created successfully");
		}
		// Here we make the Rooms table
		public static void createTableRooms()
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sql = "CREATE TABLE IF NOT EXISTS Hotels " +
					"(ID INT PRIMARY KEY              ," +
					" name           TEXT    NOT NULL, " + 
					" type           TEXT    NOT NULL, " + 
					" street         TEXT    NOT NULL, " + 
					" streetnumber   TEXT    NOT NULL, " +
					" city           TEXT    NOT NULL, " + 
					" zipCode        TEXT    NOT NULL, " + 
					" stars          INT	 NOT NULL )"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Table created successfully");
		}
		
		public static void createRoomsInHotel() {
			Connection c = null;
			Statement stmt = null;
			try {
				System.out.println("Opened database successfully");
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
				stmt = c.createStatement();
				String sql = "CREATE TABLE IF NOT EXISTS roomsInHotel" +
					"(ID INT PRIMARY KEY             ," + 
					" roomID         INT     NOT NULL," + 
					" hotelID        INT     NOT NULL," +
					" FOREIGN KEY(roomID)  REFERENCES Rooms(ID))," +
					" FOREIGN KEY(hotelID) REFERENCES hotels(ID))"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Table roomsInHotel created successfully");
		}
		
		public static void createTableReserved()
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sql = "CREATE TABLE IF NOT EXISTS Reserved" +
						"(ID INT PRIMARY KEY     NOT NULL," +
						" roomID         INT     NOT NULL, " + 
						" countAvail     INT     NOT NULL, " + 
						" date           TEXT    NOT NULL, " + 
						" FOREIGN KEY(roomID) REFERENCES Rooms(ID))"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Table Reserved created successfully");
		}

		// Here we insert some junk test data
		public static void insertIntoDBStuff()
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sql = "INSERT INTO HOTELS (name,type,street,streetnumber,city,zipCode,stars) " +
						"VALUES ('hotelnumber1', 'hostel', 'torgabraut', '29',  'rvk', '90210', 3);"; 
				stmt.executeUpdate(sql);

				stmt.close();
				c.commit();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Records created successfully");
		}
		// Here is a test select statement that outputs to give confirmation
		public static void select()
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotels;" );
				while ( rs.next() ) {
					int id = rs.getInt("id");
					String  name = rs.getString("name");
					String  street = rs.getString("street");
					System.out.println( "ID = " + id );
					System.out.println( "NAME = " + name );
					System.out.println( "street = " + street );
					System.out.println();
				}
				rs.close();
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Operation done successfully");
		}
		
		public static List<Room> executeQueryRooms(int a, String b){
			List <Room> roomList = null;
			Connection c = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotels.db");
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");
				pstmt = c.prepareStatement("SELECT * from Hotels WHERE name = ?");
				pstmt.setString(1,b);
				rs = pstmt.executeQuery();
				
				roomList = resultSetToRoom(rs);
				
				pstmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
				return roomList;
			}
			return roomList;
		}
		
		public static List<Room> resultSetToRoom(ResultSet rs) {
			List <Room> roomList = new ArrayList<Room>();
			int id,area,price,beds,bedrooms,roomCount,reserveID,hotelID;
			while ( rs.next() ) {				
				id = rs.getInt("id");
				area = rs.getInt("area");
				price = rs.getInt("price");
				beds = rs.getInt("beds");
				bedrooms = rs.getInt("bedrooms");
				roomCount = rs.getInt("roomCount");
				hotelID = rs.getInt("hotelID");
				// how to handle hotel creation and room creation and link correctly
				Hotel hotel = null;
				roomList.add(new Room(price,area,beds,bedrooms,roomCount,reserveID,hotel,id) );
//                               Room(price,area,beds,bedrooms,roomCount,reservedDates,reservedCounter,hotel,id){
				System.out.println();
			}
			return roomList;
		}
		
		public static List<Hotel> resultSetToHotel(ResultSet rs) {
			int stars;
			String name, type, street, streetNumber, city, zipCode, rooms;
			Coordinates coords;
			List <Hotel> hotelList = new ArrayList<Hotel>();
			while ( rs.next() ) {				
				name = rs.getString("name");
				type = rs.getString("type");
				stars = rs.getInt("stars");
				street = rs.getString("street");
				streetNumber = rs.getString("streetnumber");
				city = rs.getString("roomCount");
				zipCode = rs.getString("hotelID");
				//rooms = ?
				// how to handle hotel creation and room creation and link correctly
				// how to handle coords?
				hotelList.add(new Hotel(stars, name, type, street, streetNumber, city, zipCode, ????, coords, ))
				System.out.println();
			}
			return hotelList;
		}
		


		
}
