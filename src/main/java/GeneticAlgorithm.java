import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class with the genetic algorithm to build Fifa ultimate team squads
 */
public class GeneticAlgorithm {

    /**
     * Initializes the genetic algorithm with the inputted hyperparams
     * @param mutationRate probability of a mutation occurring to a given position
     * @param populationSize number of squads per generation
     * @param maxGenerations max number of generations evaluated
     * @return best squad found under the max generations.
     */
    public static Squad init(float mutationRate, int populationSize, int maxGenerations) {

        List<Squad> squads = getInitialSquads(populationSize);
        int generation = 0;
        Squad bestSquad = null;
        while (generation < maxGenerations) {
            Squad tempBest = evaluate(squads);

            if ((bestSquad == null) || (bestSquad.getChemistry() < tempBest.getChemistry())) {
                bestSquad = tempBest;
                System.out.println("Generation " + generation + ": chemistry: " + bestSquad.getChemistry());
                System.out.println(bestSquad.getLeague());
                System.out.println(bestSquad.getCountry());
            }
            if (bestSquad.getChemistry() >= 100) {
                break;
            }
            squads = getNextGeneration(mutationRate, squads);
            squads.add(0, bestSquad);
            generation += 1;
        }
        System.out.println(bestSquad);
        return bestSquad;

    }

    /**
     * Gets initial population
     * @param populationSize number of squads in each generation
     * @return initial population of randomly created squads
     */
    private static List<Squad> getInitialSquads(int populationSize) {
        List<Squad> squads = new ArrayList<>();
        Position[] positions = Position.getPositions();
        for (int i = 0; i < populationSize; i++) {
            List<Player> playerList = new ArrayList<>();
            for (Position position : positions) {
                Player player = Scrapper.getPlayer(position);
                playerList.add(player);
            }
            Squad squad = new Squad(playerList);
            squads.add(squad);
        }
        return squads;
    }

    /**
     * Gets the best squad in the current generation
     * @param squads list of squads in current generation
     * @return best squad determined by chemistry
     */
    private static Squad evaluate(List<Squad> squads) {
        Squad bestSquad = null;
        for (int squadIndex = 0; squadIndex < squads.size(); squadIndex++) {
            Squad squad = squads.get(squadIndex);
            int chemistry = squad.getChemistry();
            if ((bestSquad == null) || (bestSquad.getChemistry() < chemistry)) {
                bestSquad = squad;
            }
        }
        return bestSquad;
    }

    /**
     * Mutates the given squad with probability of the mutationRate passed
     * @param mutationRate probability of mutation on each position
     * @param squad current squad being mutated
     * @return mutated squad
     */
    private static Squad mutate(float mutationRate, Squad squad) {
        List<Player> players = new ArrayList<>();
        Position[] positions = Position.getPositions();
        for (Position position : positions) {
            Player player;
            if (Math.random() < mutationRate) {
                player = Scrapper.getPlayer(position);
            } else {
                player = squad.getPlayer(position);
            }
            players.add(player);
        }
        return new Squad(players);
    }

    /**
     * Generates a new generation of squads given the current one
     * @param mutationRate probability of mutation
     * @param squads previous generation
     * @return new generation of squads
     */
    private static List<Squad> getNextGeneration(float mutationRate, List<Squad> squads) {
        List<Squad> newSquads = new ArrayList<>();
        int populationSize = squads.size();
        List<Squad> squadPool = getSquadPool(squads);
        for (int squadIndex = 0; squadIndex < populationSize; squadIndex++) {
            Squad parent1 = squadPool.get((int) (Math.random() * squadPool.size()));
            Squad parent2 = findMate(squadPool, parent1);
            Squad child = mate(parent1, parent2);
            Squad mutation = mutate(mutationRate, child);
            newSquads.add(mutation);
        }
        return newSquads;
    }

    /**
     * Mate two squads together
     * @param squad1 squad1
     * @param squad2 squad2
     * @return offspring
     */
    private static Squad mate(Squad squad1, Squad squad2) {
        List<Player> players = new ArrayList<>();
        Position[] positions = Position.getPositions();
        for (Position position : positions) {
            Player player;
            if (Math.random()>=0.5) {
                player = squad1.getPlayer(position);
            } else {
                player = squad2.getPlayer(position);
            }
            players.add(player);
        }
        return new Squad(players);
    }

    /**
     * Finds an compatible mate in the squadpool for the current squad
     * @param squadPool pool of squads for mating
     * @param mate current squad looking for a mate
     * @return compatible mate
     */
    private static Squad findMate(List<Squad> squadPool, Squad mate) {
        int populationSize = squadPool.size();
        int maxIterations = 200;
        int iteration = 0;
        while (iteration < maxIterations) {
            int index = (int) (populationSize * Math.random());
            Squad possibleMate = squadPool.get(index);
            if (mate.compatible(possibleMate)) {
                return possibleMate;
            }
            iteration += 1;
        }
        int index = (int) (populationSize * Math.random());
        Squad randomMate = squadPool.get(index);
        return randomMate;
    }

    /**
     * Generates a squad pool for mating
     * @param squads current list of squads
     * @return squad pool
     */
    private static List<Squad> getSquadPool(List<Squad> squads) {
        List<Squad> squadPool = new ArrayList<>();
        int populationSize = squads.size();

        //sort squads by highest chemistry in descending order
        squads.sort(Collections.reverseOrder());

        //percentage representation of the top 45 squads in future squad list
        //TODO: Make the number of top squad an input
        double[] sizeRatio = {.10, .10, .0725, .0725, .05, .05, .05, .05, .025, .025,
                             .025, .025, .025, .025, .02, .02, .02, .02, .015, .015,
                             .015, .015, .01, .01, .01, .01, .01, .01, .01, .01,
                              .01, .01, .005, .005, .005, .005, .005, .005, .005, .005,
                             .005, .005, .005, .005, 0.05};

        for (int i = 0 ; i< sizeRatio.length; i++) {
            int size = (int)(populationSize * sizeRatio[i]);
            for (int j = 0; j < size; j++) {
                Squad squad = squads.get(i);
                squadPool.add(squad);
            }
        }
        return squadPool;
    }

}
