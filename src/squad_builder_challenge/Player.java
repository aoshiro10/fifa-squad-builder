package squad_builder_challenge;

public class Player {

	public int rating;
	public String country;
	public String position;
	public String league;
	
	public Player(String country, String position, String league, int rating) {
		
		this.country = country;
		this.position = position;
		this.league = league;
		this.rating = rating;
		
	}
	
	public String toString() {
		
		String playerInformation = "country: " + this.country +
								" position: " + this.position +
								" league: " + this.league + " rating: " 
								+ Integer.toString(this.rating);
		
		return playerInformation;
		
	}
	
}
