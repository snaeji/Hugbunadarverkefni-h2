package is.hi.hbv401g.h2.hotel;

import java.io.Console;

public class TextView {
	private String searchHelpMessage = "Press 9 for English, press 12 for Spanish, press 15 if you don't know what I'm talking about.";
	Console console = System.console();
	
	TextView() {
		greet();
	}
	
	private void greet() {
		System.out.println("Sup Mister Smith");
	}
	
	String askForSearchMethod() {
		System.out.println("Please insert your search method, either searchWithAddress or searchWithCoords");
		String searchMethod = console.readLine("Search method: ");
		
		if((searchMethod.compareToIgnoreCase("searchWithAddress") == 0) || (searchMethod.compareToIgnoreCase("searchWithCoords") == 0)) {
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
		String[] searchParameters = new String[6];
		System.out.println("Please insert your search parameters:");
		searchParameters[0] = console.readLine("Minimum price: ");
		searchParameters[1] = console.readLine("Maximum price: ");
		searchParameters[2] = console.readLine("Minimum stars: ");
		searchParameters[3] = console.readLine("Maximum stars: ");
		searchParameters[4] = console.readLine("Search radius: ");
		searchParameters[5] = console.readLine("Search coordinates: ");
		return searchParameters;
	}
	
	void printSearchHelp() {
		System.out.println(searchHelpMessage);
	}
	
	void printSearchResults(Room[] searchResults) {
		System.out.println();
		System.out.println("Search results:");
		for(int i=0;i<searchResults.length;i++) {
			System.out.print("Price: ");
			System.out.println(searchResults[i].getPrice());
			System.out.print("Area: ");
			System.out.println(searchResults[i].getArea());
			System.out.print("Number of beds: ");
			System.out.println(searchResults[i].getBeds());
			System.out.print("Number of bedrooms: ");
			System.out.println(searchResults[i].getBedrooms());
			System.out.print("Hotel name: ");
			System.out.println(searchResults[i].getHotel());
			System.out.println();
		}
		System.out.println();
	}
	
	
	
}
