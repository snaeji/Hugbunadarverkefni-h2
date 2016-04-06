package is.hi.hbv401g.h2.hotel;

public class TextView {
	private String searchHelpMessage = "Press 9 for English, press 12 for Spanish, press 15 if you don't know what I'm talking about.";
	
	TextView() {
		greet();
	}
	
	private void greet() {
		System.out.println("Sup Mister Smith");
	}
	
	private void printSearchHelp() {
		System.out.println(searchHelpMessage);
	}
	
	private void printSearchResults(Room[] searchResults) {
		for(int i=0;i<searchResults;i++) {
			System.out.println(x);
		}
	}
	
	
	
}
