import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.hash;

public final class Squad {

    private Map<Position, Player> players;

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
