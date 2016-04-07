package is.hi.hbv401g.h2.hotel;
import java.sql.*;

public class Database {
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
	                   " area           INT     NOT NULL, " + 
	                   " beds           INT     NOT NULL, " + 
	                   " bedrooms       INT     NOT NULL, " + 
	                   " roomCount      INT     NOT NULL, " + 
	                   " reserveID      INT     NOT NULL, " +  
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
	  
	  
	public static ResultSet executeQuery(int a, String b){
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			rs = stmt.executeQuery( "SELECT * FROM Hotels;" );
			rs.close();
			stmt.close();
			c.close();
			return rs;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			return rs;
		}
		
	}
}
