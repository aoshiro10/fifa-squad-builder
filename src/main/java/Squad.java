import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Squad {

    private Map<Position, Player> players;
    private int chemistry = -1;

    public Squad(List<Player> playerList) {
        players = buildMap(playerList);
    }

    private Map<Position, Player> buildMap(List<Player> playerList) {
        HashMap<Position, Player> map = new HashMap<>();
        for (Player player : playerList) {
            Position position = player.getPosition();
            map.put(position, player);
        }
        return map;
    }

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

    public Player getPlayer(Position position) {
        return players.get(position).copy();
    }

    public List<Player> getPlayers() {
        List<Player> playerList = new ArrayList<>();
        for (Position position : this.players.keySet()) {
            Player player = this.players.get(position);
            playerList.add(player.copy());
        }
        return playerList;
    }

    public Squad copy() {
        return new Squad(getPlayers());
    }

    public Squad substitute(List<Player> newPlayers) {
        Squad newSquad = copy();
        for (Player player : newPlayers) {
            newSquad.players.put(player.getPosition(), player.copy());
        }
        return newSquad;
    }

    @Override
    public String toString() {
        List<Player> playerList = getPlayers();
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : playerList) {
            stringBuilder.append(player + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Player player : getPlayers()) {
            hashCode += player.hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Squad)) {
            return false;
        }
        Squad squad = (Squad) obj;
        for (Position position : this.players.keySet()) {
            Player player = this.players.get(position);
            Player other = squad.getPlayer(position);
            if (!player.equals(other)) {
                return false;
            }
        }
        return true;
    }

}
