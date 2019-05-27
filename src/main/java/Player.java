public class Player {

    private String name;
    private int rating;
    private String country;
    private Position position;
    private String league;
    private String club;

    public Player(String name, Position position, int rating, String league, String club, String country ) {
        this.name = name;
        this.country = country;
        this.position = position;
        this.league = league;
        this.rating = rating;
        this.club = club;
    }


    public int getRating() {
        return rating;
    }

    public Position getPosition() {
        return position;
    }

    public String getCountry() {
        return country;
    }

    public String getClub() {
        return club;
    }

    public String getLeague() {
        return league;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("player: %s country: %s position: %s league: %s rating: %d club: %s",
                this.name, this.country, this.position, this.league, this.rating, this.club);
    }
}
