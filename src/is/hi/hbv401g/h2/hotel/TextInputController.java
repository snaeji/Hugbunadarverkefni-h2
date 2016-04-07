package is.hi.hbv401g.h2.hotel;

public class TextInputController {
	
//	private TextView textView;
	private HotelManager hotelManager;
	private BookingManager bookingManager;
	
	// Expect to get the input in one of the two following ways:
	// searchWIthAddres: minPrice, maxPrice, minStars, maxStars, street, city, zipCode
	// searchWithCoords: minPrice, minStars, maxStars, radius coordinates
	public static void main(String[] args) {
		TextView textView = new TextView();
		String searchMethod = textView.askForSearchMethod();
		if(searchMethod.equalsIgnoreCase("searchWithAddress")) {
			String[] searchParameters = textView.askForSearchParametersAddress();
			HotelManager.searchWithAddress(
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
			HotelManager.searchWithAddress(
					Integer.parseInt(searchParameters[0]), 
					Integer.parseInt(searchParameters[1]), 
					Integer.parseInt(searchParameters[2]), 
					Integer.parseInt(searchParameters[2]), 
					Double.toString(searchParameters[4]), 
					searchParameters[5]
			);
		}
		String[] searchParameters = getSearchParameters();
		
	}
	
	private static String[] getSearchParameters() {
		TextView textView = new TextView();
		String[] searchParameters;
		String searchMethod = textView.askForSearchMethod();
		if(searchMethod.compareToIgnoreCase("searchWithAddress") == 0) {
			searchParameters = textView.askForSearchParametersAddress();
		} else
		if(searchMethod.compareToIgnoreCase("searchWithCoords") == 0) {
			searchParameters = textView.askForSearchParametersCoords();
		} else {
			System.out.println("Invalid search method, try again.");
			return getSearchParameters();
		}
		return searchParameters;
	}
}