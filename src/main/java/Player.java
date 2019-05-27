import static java.util.Objects.hash;

public final class Player {

    private final String name;
    private final int rating;
    private final String country;
    private final Position position;
    private final String league;
    private final String club;

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

    public Player copy() {
        return new Player(this.name, this.position, this.rating, this.league, this.club, this.country);
    }

    @Override
    public int hashCode() {
        return hash(name, country, rating, position, club, league);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player player = (Player) obj;
        return (name.equals(player.name)) && (country.equals(player.country))
                && (rating == player.rating) && (position.equals(player.position))
                && (club.equals(player.club)) && (league.equals(player.league));
    }

    @Override
    public String toString() {
        return String.format("player: %s country: %s position: %s league: %s rating: %d club: %s",
                this.name, this.country, this.position, this.league, this.rating, this.club);
    }
}
