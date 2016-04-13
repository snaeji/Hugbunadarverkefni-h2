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
		textView.greet();
		int indexToBook = searchForRooms();
		
		
		List<Room> roomToBook = new ArrayList<Room>();
		roomToBook.add(lastResult.get(indexToBook));
		
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTimeInMillis(0);
		calFrom.set(2016, 4, 9, 16, 0, 0);
		Date dateFrom = calFrom.getTime();
		
		Calendar calTo = Calendar.getInstance();
		calTo.setTimeInMillis(0);
		calTo.set(2016, 4, 12, 16, 0, 0);
		Date dateTo = calFrom.getTime();
		
		
		bookingManager.book(new Traveler("arnar",1), roomToBook, dateFrom, dateTo);
		
		textView.farewell();
	}
	public static int searchForRooms(){
		List<Room> searchResults;
		String searchMethod = textView.askForSearchMethod();
		if(searchMethod.equalsIgnoreCase("searchWithAddress")) {
			String[] searchParameters = textView.askForSearchParametersAddress();
			searchResults = hotelManager.searchWithAddress(
					Integer.parseInt(searchParameters[0]), 
					Integer.parseInt(searchParameters[1]), 
					Integer.parseInt(searchParameters[2]), 
					searchParameters[4], 
					searchParameters[5], 
					searchParameters[6]
			);
		} else {
			String[] searchParameters = textView.askForSearchParametersCoords();
			searchResults = hotelManager.searchWithCoords(
					Integer.parseInt(searchParameters[0]), 
					Integer.parseInt(searchParameters[1]), 
					Integer.parseInt(searchParameters[2]), 
					Double.parseDouble(searchParameters[4]), 
					new Coordinates(Double.parseDouble(searchParameters[5]), Double.parseDouble(searchParameters[6]))
			);
		}
		lastResult = searchResults;
		textView.printSearchResults(searchResults);
		if(searchResults.size()==0) return searchForRooms();
		else return textView.askForIndexToBook();
		
	}
}