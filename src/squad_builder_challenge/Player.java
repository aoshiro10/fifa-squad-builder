package squad_builder_challenge;

public class Player {

	public int rating;
	public String country;
	public String position;
	public String league;
	public int club;
	
	public Player(String country, String position, String league, int rating, int club) {
		
		this.country = country;
		this.position = position;
		this.league = league;
		this.rating = rating;
		this.club = club;
		
	}
	
	public String toString() {
		
		String playerInformation = "country: " + this.country +
								" position: " + this.position +
								" league: " + this.league + " rating: " 
								+ Integer.toString(this.rating) + " club: " + Integer.toString(this.club);
		
		return playerInformation;
		
	}
	
}
