package is.hi.hbv401g.h2.hotel;

public class Traveler {
	
	private String name;
	private int Id;
	
	public Traveler(String name, int id){
		this.name = name;
		this.Id = id;
	};
	
	 public String getName(){
		return this.name;
	}
	 
	public int getId() {
		return this.Id;
	}

}
