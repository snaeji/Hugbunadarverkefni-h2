package is.hi.hbv401g.h2.hotel;

import java.io.Console;
import java.util.List;

public class TextView {
	private String searchHelpMessage = "Press 9 for English, press 12 for Spanish, press 15 if you don't know what I'm talking about.";
	Console console = System.console();
	
	TextView() {
		greet();
	}
	
	void greet() {
		System.out.println("Sup Mister Smith");
	}
	
	String askForSearchMethod() {
		//System.out.println("Please insert your search method, either searchWithAddress or searchWithCoords");
		System.out.println("Enter '1' for searchWithAddress or '2' for searchWithCoords");
		String searchMethod = console.readLine("Search method: ");
		
		if((searchMethod.compareToIgnoreCase("1") == 0) || (searchMethod.compareToIgnoreCase("2") == 0)) {
			return searchMethod;
		}
		System.out.println("Invalid search method, try again.");
		return askForSearchMethod();
	}
	
	String[] askForSearchParametersAddress() {
		String[] searchParameters = new String[7];
		System.out.println("Please insert your search parameters:");
		searchParameters[0] = console.readLine("Minimum price: ");
		searchParameters[1] = console.readLine("Maximum price: ");
		searchParameters[2] = console.readLine("Minimum stars: ");
		searchParameters[3] = console.readLine("Maximum stars: ");
		searchParameters[4] = console.readLine("Street: ");
		searchParameters[5] = console.readLine("City: ");
		searchParameters[6] = console.readLine("Zip code: ");
		return searchParameters;
	}
	
	String[] askForSearchParametersCoords() {
		String[] searchParameters = new String[7];
		System.out.println("Please insert your search parameters:");
		searchParameters[0] = console.readLine("Minimum price: ");
		searchParameters[1] = console.readLine("Maximum price: ");
		searchParameters[2] = console.readLine("Minimum stars: ");
		searchParameters[3] = console.readLine("Maximum stars: ");
		searchParameters[4] = console.readLine("Search radius: ");
		System.out.println("Coordinates:");
		searchParameters[5] = console.readLine("Longitude: ");
		searchParameters[6] = console.readLine("Latitude: ");
		return searchParameters;
	}
	
	void printSearchHelp() {
		System.out.println(searchHelpMessage);
	}
	
	void printSearchResults(List<Room> searchResults) {
		System.out.println();
		System.out.println("Search results:");
		int counter = 0;
		for(Room room : searchResults) {
			System.out.println("Result: "+counter++);
			System.out.print("Price: ");
			System.out.println(room.getPrice());
			System.out.print("Area: ");
			System.out.println(room.getArea());
			System.out.print("Number of beds: ");
			System.out.println(room.getBeds());
			System.out.print("Number of bedrooms: ");
			System.out.println(room.getBedrooms());
			System.out.print("Hotel name: ");
			System.out.println(room.getHotel());
			System.out.println();
		}
		System.out.println();
	}
	
	int askForIndexToBook(){
		String result = console.readLine("Which result would you like to book? ");
		return Integer.parseInt(result);
	}
	
	void farewell(){
		System.out.print("Congratulations, you have now booked a room.");
	}
	
	
}
