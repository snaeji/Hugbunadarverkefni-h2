package is.hi.hbv401g.h2.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDBController {
	
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String DB = "jdbc:sqlite:hotels.db";
	private static final DateFormat DF = new SimpleDateFormat("dd-MM-yyyy"); 
	
	/*
	Booking[] getBookings() {
		return null;
	}
	*/
	
	/*
	Booking[] getBookingsByTraveler(Traveler traveler) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establishConnection(DRIVER, DB, true);
			pstmt = con.prepareStatement(SELECT_BOOKING_WITH_TRAVELER);
			pstmt.setInt(0, traveler.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				????
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}   System.out.println("Canceled booking");
		return null;
	}
	*/
	
	public BookingDBController() {
		createTableBooking();
	}
	
	static void cancelBooking(Booking booking) {
		System.out.println("cancelBooking: ...");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establishConnection(DRIVER, DB, false);
			pstmt = con.prepareStatement(DELETE_BOOKING_WITH_ID);
			pstmt.setInt(0, booking.getId());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}   System.out.println("cancelBooking: success");
	}
	
	static Booking editBooking(Booking booking, Date toDate, Date fromDate) {
		System.out.println("editBooking: ...");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establishConnection(DRIVER, DB, false);
			pstmt = con.prepareStatement(UPDATE_BOOKING_DATES);
			pstmt.setString(1, DF.format(booking.getFromDate()));
			pstmt.setString(2, DF.format(booking.getToDate()));
			pstmt.setInt(3, booking.getId());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}   System.out.println("editBooking: success");
		return booking;
		// 
	}
	
	static void book(Booking booking) {
		System.out.println("book: ...");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establishConnection(DRIVER, DB, false);
			pstmt = con.prepareStatement(INSERT_BOOKING);
			for(Room room : booking.getRooms()) {
				pstmt.setInt(1, booking.getTraveler().getId());
				pstmt.setInt(2, room.getId());
				pstmt.setString(3, DF.format(booking.getFromDate()));
				pstmt.setString(4, DF.format(booking.getToDate()));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			con.commit();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}   System.out.println("Inserted booking");
	}

	private static Connection establishConnection(String driver, String db, boolean autoCommit) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(db);
		con.setAutoCommit(autoCommit);
		return con;
	}

	private static void createTableBooking()
	{
		System.out.println("createTableBooking: ...");
		Connection c = null;
		Statement stmt = null;
		try {
			c = establishConnection(DRIVER, DB, false);
			stmt = c.createStatement();
			stmt.executeUpdate(CREATE_BOOKING_TABLE);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}   System.out.println("createTableBooking: success");
	}
	
	private static final String CREATE_BOOKING_TABLE 
		= "CREATE TABLE IF NOT EXISTS Booking ("
		+ "ID          INTEGER    PRIMARY KEY,"
		+ "travelerID  INT     NOT NULL,"
		+ "roomID      INT     NOT NULL,"
		+ "fromDate    TEXT    NOT NULL,"
		+ "toDate      TEXT    NOT NULL)";
	
	private static final String SELECT_BOOKING_WITH_TRAVELER 
		= "SELECT * FROM Booking WHERE travelerID = *";
	
	private static final String INSERT_BOOKING 
		= "INSERT INTO Booking (travelerID, roomID, fromDate, toDate) "
		+ "VALUES (?, ?, ?, ?)";
	
	private static final String UPDATE_BOOKING_DATES
		= "UPDATE Booking "
		+ "SET fromDate=?, toDate=? "
		+ "WHERE ID=?";
	
	private static final String SELECT_ALL_BOOKING
		= "SELECT * FROM Booking";
	
	private static final String SELECT_BOOKING_WITH_ID
		= "SELECT * FROM Booking WHERE ID=?";
		
	private static final String DELETE_BOOKING_WITH_ID
		= "DELETE * FROM Booking WHERE ID=?";
}
