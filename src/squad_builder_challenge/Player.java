package squad_builder_challenge;

public class Player {

	public String name;
	public int rating;
	public String country;
	public String position;
	public String league;
	public String club;
	
	public Player(String name, String position, int rating, String league,  String club, String country ) {
		
		this.name = name;
		this.country = country;
		this.position = position;
		this.league = league;
		this.rating = rating;
		this.club = club;
		
	}
	
	public String toString() {
		
		String playerInformation = "player: " + this.name + " country: " + this.country +
								" position: " + this.position +
								" league: " + this.league + " rating: " 
								+ Integer.toString(this.rating) + " club: " + this.club;
		
		return playerInformation;
		
	}
	
}
