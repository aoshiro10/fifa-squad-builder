import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Object representing a Fifa squad
 */
public final class Squad implements Comparable<Squad> {

    private Map<Position, Player> playersMap;
    private List<Player> players;
    private int chemistry = -1;
    private String country;
    private String league;

    /**
     * Constructor for a new squad using a playerList
     * with one player per position.
     * @param playerList list of players in the squad.
     * @requires playerList.size() == 11
     */
    public Squad(List<Player> playerList) {
        this.players = playerList;
        this.playersMap = buildMap(playerList);
    }

    /**
     * Constructs the data structure mapping from position
     * to player in the squad
     * @param playerList list of players in the squad
     * @return map
     */
    private Map<Position, Player> buildMap(List<Player> playerList) {

        Map<String, Integer> leagueCount = new HashMap<>();
        Map<String, Integer> countryCount = new HashMap<>();

        HashMap<Position, Player> map = new HashMap<>();
        for (Player player : playerList) {
            Position position = player.getPosition();
            map.put(position, player);

            String league = player.getLeague();
            String country = player.getCountry();

            leagueCount.put(league, leagueCount.getOrDefault(league, 0) + 1);
            countryCount.put(country, countryCount.getOrDefault(country, 0) + 1);
        }

        //Setting the country and squad of the current squad
        this.country = getTopKey(countryCount);
        this.league = getTopKey(leagueCount);

        return map;
    }

    /**
     * Getter for the dominant league in the squad
     * @return dominant league
     */
    public String getLeague() {
        return league;
    }

    /**
     * Getter for the dominant country in the squad
     * @return dominant country
     */
    public String getCountry() {
        return country;
    }

    private String getTopKey(Map<String, Integer> countMap) {
        String topKey = null;
        int topCount = -1;

        for (String key : countMap.keySet()) {
            int count = countMap.get(key);
            if ((topKey == null) || (topCount < count)) {
                topKey = key;
                topCount = count;
            }
        }
        return topKey;
    }

    /**
     * Getter for squad's chemistry
     * Chemistry is used as the fitness function in the
     * genetic algorithm
     * @return squad's chemistry
     */
    public int getChemistry() {
        if (this.chemistry == -1) {
            this.chemistry = calculateChemistry();
        }
        return chemistry;
    }

    private int calculateChemistry(){

        int chemistry = 0;

        Link[] links = Link.links;

        for (Link link : links) {
            Player player1 = getPlayer(link.getPosition1());
            Player player2 = getPlayer(link.getPosition2());
            chemistry += getLinkChemistry(player1, player2);
        }

        return chemistry;
    }

    /**
     * Checks if a squad can mate with the current squad
     * @param other possible mate
     * @return true if possible; false otherwise
     */
    public boolean compatible(Squad other) {
        return ((other.getCountry().equals(this.getCountry()))
                || (other.getLeague().equals(this.getLeague())));
    }

    //TODO: calculations are estimates of the real chemistry between two players.
    private int getLinkChemistry(Player player1, Player player2) {

        int strong = 6;
        int normal = 4;
        int weak = 2;

        if (player1.getCountry().equals(player2.getCountry())) {
            if (player1.getLeague().equals(player2.getLeague())) {
                return strong;
            }
            return  normal;
        }
        if (player1.getLeague().equals(player2.getLeague())) {
            if (player1.getClub().equals(player2.getClub())) {
                return strong;
            }
            return normal;
        }
        return weak;
    }

    /**
     * Getter for the player at given position
     * @param position position in squad
     * @return player in given position
     */
    public Player getPlayer(Position position) {
        return playersMap.get(position);
    }

    /**
     * Creates a string representation of the squad
     * @return string representation
     */
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : this.players) {
            stringBuilder.append(player + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Hashes the squad
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Player player : players) {
            hashCode += player.hashCode();
        }
        return hashCode;
    }

    /**
     * Checks current squad with input for equality
     * @param obj object being compared
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Squad)) {
            return false;
        }
        Squad squad = (Squad) obj;
        for (Position position : this.playersMap.keySet()) {
            Player player = this.playersMap.get(position);
            Player other = squad.getPlayer(position);
            if (!player.equals(other)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compare the squad to another one in terms of chemistry.
     * @param o other squad
     * @return -1 if less, 0 if equal, and +1 if greater
     */
    @Override
    public int compareTo(Squad o) {
        return Integer.compare(this.getChemistry(), o.chemistry);
    }
}
