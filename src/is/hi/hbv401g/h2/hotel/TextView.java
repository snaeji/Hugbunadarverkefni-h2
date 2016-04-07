package is.hi.hbv401g.h2.hotel;

public class TextView {
	private String searchHelpMessage = "Press 9 for English, press 12 for Spanish, press 15 if you don't know what I'm talking about.";
	
	TextView() {
		greet();
	}
	
	private void greet() {
		System.out.println("Sup Mister Smith");
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
