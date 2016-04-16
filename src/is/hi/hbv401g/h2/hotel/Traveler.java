package is.hi.hbv401g.h2.hotel;

import metaSearchEngine.program.UserClass;

public class Traveler implements UserClass {
	
	private String username;
	private final String name;
	private String email;
	private int id;
	
	public Traveler(String name, int id){
		this.name = name;
		this.id = id;
	};
	
	 public String getName(){
		return this.name;
	}
	 
	public int getId() {
		return this.id;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	@Override
	public void setUserName(String newUsername) {
		this.username = newUsername;
	}

}
