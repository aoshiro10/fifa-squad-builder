import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Scrapper {

    private static HashMap<Position, List<Player>> playerMap;

    public static void init() throws FileNotFoundException {
        if (playerMap != null) {
            return;
        }
        playerMap = new HashMap<>();

        File file = new File("src/main/resources/players.txt");
        Scanner scanner = new Scanner(file);
        int numAttributes = 6;

        int nameIndex = 0;
        int positionIndex = 1;
        int ratingIndex = 2;
        int clubIndex = 3;
        int leagueIndex = 4;
        int countryIndex = 5;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] attributes = line.split(",");
            if (attributes.length != numAttributes) {
                continue;
            }
            String name = attributes[nameIndex];
            Position[] positions = Position.getPosition(attributes[positionIndex]);
            int rating = Integer.valueOf(attributes[ratingIndex]);
            String club = attributes[clubIndex];
            String league = attributes[leagueIndex];
            String country = attributes[countryIndex];
            for (Position position : positions) {
                Player player = new Player(name, position, rating, league, club, country);
                List<Player> playerList;
                if (!playerMap.containsKey(position)) {
                    playerList = new ArrayList<>();
                } else {
                    playerList = playerMap.get(position);
                }
                playerList.add(player);
                playerMap.put(position, playerList);
            }
        }

    }

    public static Player getPlayer(Position position) {
        if (playerMap == null) {
            try {
                init();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        List<Player> players = playerMap.get(position);
        int index = (int) (Math.random() * players.size());
        return players.get(index);
    }

}
