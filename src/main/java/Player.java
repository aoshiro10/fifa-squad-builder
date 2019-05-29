import static java.util.Objects.hash;

/**
 * Object representing a player.
 */
public final class Player {

    private final String name;
    private final int rating;
    private final String country;
    private final Position position;
    private final String league;
    private final String club;

    /**
     * Constructor for a new player.
     * @param name player's name
     * @param position player's position
     * @param rating player's rating
     * @param league player's league
     * @param club player's club
     * @param country player's country
     */
    public Player(String name, Position position, int rating, String league, String club, String country ) {
        this.name = name;
        this.country = country;
        this.position = position;
        this.league = league;
        this.rating = rating;
        this.club = club;
    }

    /**
     * Getter for player's position.
     * @return player's position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getter for player's country.
     * @return player's country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter for player's club.
     * @return player's club
     */
    public String getClub() {
        return club;
    }

    /**
     * Getter for player's league.
     * @return player's league
     */
    public String getLeague() {
        return league;
    }

    /**
     * Hash player.
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return hash(name, country, club, league);
    }

    /**
     * Checks for equality between this player and passed param.
     * @param obj objects being tested for equality
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player player = (Player) obj;
        return (name.equals(player.name)) && (country.equals(player.country))
                && (club.equals(player.club)) && (league.equals(player.league));
    }

    /**
     * String representation of the player.
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("player: %s country: %s position: %s league: %s rating: %d club: %s",
                this.name, this.country, this.position, this.league, this.rating, this.club);
    }
}
