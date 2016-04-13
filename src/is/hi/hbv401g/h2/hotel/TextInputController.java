package is.hi.hbv401g.h2.hotel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TextInputController {
	
//	private TextView textView;
	private static HotelManager hotelManager;
	private static TextView textView;
	private static List<Room> lastResult;
	private static BookingManager bookingManager;
	
	// Expect to get the input in one of the two following ways:
	// searchWIthAddres: minPrice, maxPrice, minStars, maxStars, street, city, zipCode
	// searchWithCoords: minPrice, minStars, maxStars, radius coordinates
	public static void main(String[] args) {
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTimeInMillis(0);
		calFrom.set(2016, 4, 9, 16, 0, 0);
		Date dateFrom = calFrom.getTime();
		System.out.print("dateFrom: ");
		//System.out.println(calFrom.getTime());
		System.out.println(dateFrom.toString());
		//dateFrom.
		
		Calendar calTo = Calendar.getInstance();
		calTo.setTimeInMillis(0);
		calTo.set(2016, 4, 12, 16, 0, 0);
		Date dateTo = calTo.getTime();
		System.out.print("dateTo: ");
		//System.out.println(calTo.getTime());
		System.out.println(dateTo.toString());
		
		textView = new TextView();
		bookingManager = new BookingManager();
		hotelManager = new HotelManager();
		
		textView.greet();
		int indexToBook = searchForRooms(dateFrom,dateTo);
		
		
		List<Room> roomToBook = new ArrayList<Room>();
		roomToBook.add(lastResult.get(indexToBook));
		
		
		
		bookingManager.book(new Traveler("arnar",0), roomToBook, dateFrom, dateTo);
		
		textView.farewell();
	}
	public static int searchForRooms(Date dateFrom,Date dateTo){
		List<Room> searchResults;
		String searchMethod = textView.askForSearchMethod();
		if(searchMethod.equalsIgnoreCase("1")) {
			String[] searchParameters = textView.askForSearchParametersAddress();
			searchResults = hotelManager.searchWithAddress(
					Integer.parseInt(searchParameters[0]), 
					Integer.parseInt(searchParameters[1]), 
					Integer.parseInt(searchParameters[2]), 
					searchParameters[4], 
					searchParameters[5], 
					searchParameters[6],
					1,
					dateFrom,
					dateTo
			);
		} else {
			String[] searchParameters = textView.askForSearchParametersCoords();
			searchResults = hotelManager.searchWithCoords(
					Integer.parseInt(searchParameters[0]), 
					Integer.parseInt(searchParameters[1]), 
					Integer.parseInt(searchParameters[2]), 
					Double.parseDouble(searchParameters[4]), 
					new Coordinates(Double.parseDouble(searchParameters[5]), Double.parseDouble(searchParameters[6])),
							1,
							dateFrom,
							dateTo
							
			);
		}
		lastResult = searchResults;
		textView.printSearchResults(searchResults);
		if(searchResults.size()==0) return searchForRooms(dateFrom,dateTo);
		else return textView.askForIndexToBook();
		
	}
}