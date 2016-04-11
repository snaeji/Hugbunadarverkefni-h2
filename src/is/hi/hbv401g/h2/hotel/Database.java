package is.hi.hbv401g.h2.hotel;
import java.sql.*;
import java.util.ArrayList;
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
				String sql = "CREATE TABLE IF NOT EXISTS Rooms " +
						"(ID INT PRIMARY KEY     NOT NULL," +
						" price          INT     NOT NULL, " + 
						" beds           INT     NOT NULL, " + 
						" bedrooms       INT     NOT NULL, " + 
						" roomCount      INT     NOT NULL, " + 
						" reserveID      INT     NOT NULL, " + 
						" hotelID        INT     NOT NULL, " +  
						" UNIQUE (reserveID))"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Table created successfully");
		}
		// Here we make the Reserved table
		// Here we have to loop to create 365 tables?
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
						" reserveID      INT     NOT NULL, " + 
						" countAvail     INT     NOT NULL, " + 
						" date           TEXT    NOT NULL, " + 
						" FOREIGN KEY(reserveID) REFERENCES Rooms(reserveID))"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Table created successfully");
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
			int id,price,beds,bedrooms,roomCount,reserveID,hotelID;
			List <Room> roomList = new ArrayList<Room>();
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
				//				pstmt.setInt(2,a);
				rs = pstmt.executeQuery();
				while ( rs.next() ) {
					
					id = rs.getInt("id");
					price = rs.getInt("price");
					beds = rs.getInt("beds");
					bedrooms = rs.getInt("bedrooms");
					roomCount = rs.getInt("roomCount");
					reserveID = rs.getInt("reserveID");
					hotelID = rs.getInt("hotelID");
					Hotel hotel = new Hotel(1,2,3,4);
					roomList.add(new Room(id,price,beds,bedrooms,roomCount,reserveID,hotel) );
					System.out.println();
				}
				//				return rs;
				//				rs.close();
				pstmt.close();
				c.close();
				//				return rs;
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
				return roomList;
			}
			return roomList;

		}
		


		
}
