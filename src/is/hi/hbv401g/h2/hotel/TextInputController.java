package is.hi.hbv401g.h2.hotel;

public class TextInputController {
	
//	private TextView textView;
	private static HotelManager hotelManager;
	private static TextView textView;
//	private static BookingManager bookingManager;
	
	// Expect to get the input in one of the two following ways:
	// searchWIthAddres: minPrice, maxPrice, minStars, maxStars, street, city, zipCode
	// searchWithCoords: minPrice, minStars, maxStars, radius coordinates
	public static void main(String[] args) {
		Room[] searchResults;
		textView.greet();
		String searchMethod = textView.askForSearchMethod();
		if(searchMethod.equalsIgnoreCase("searchWithAddress")) {
			String[] searchParameters = textView.askForSearchParametersAddress();
			searchResults = hotelManager.searchWithAddress(
					Integer.parseInt(searchParameters[0]), 
					Integer.parseInt(searchParameters[1]), 
					Integer.parseInt(searchParameters[2]), 
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
					Integer.parseInt(searchParameters[2]), 
					Double.parseDouble(searchParameters[4]), 
					new Coordinates(Double.parseDouble(searchParameters[5]), Double.parseDouble(searchParameters[6]))
			);
		}
		textView.printSearchResults(searchResults);
	}
}