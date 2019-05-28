import java.util.ArrayList;
import java.util.List;

public class GenericAlgorithm {

    public static Squad init(float mutationRate, int populationSize, int maxGenerations) {

        List<Squad> squads = getInitialSquads(populationSize);
        int generation = 0;
        Squad bestSquad = null;

        while (generation < maxGenerations) {

            Squad tempBest = evaluate(squads);

            if ((bestSquad == null) || (bestSquad.getChemistry() < tempBest.getChemistry())) {
                bestSquad = tempBest;
                System.out.println("Generation " + generation + ": chemistry: " + bestSquad.getChemistry());
            }

            if (bestSquad.getChemistry() >= 100) {
                break;
            }
            squads = getNextGeneration(mutationRate, squads);
            generation += 1;
        }

        return bestSquad;

    }

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


    private static List<Squad> getNextGeneration(float mutationRate, List<Squad> squads) {

        List<Squad> newSquads = new ArrayList<Squad>();
        int populationSize = squads.size();
        List<Squad> squadPool = getSquadPool(squads);

        for (int squadIndex = 0; squadIndex < populationSize; squadIndex++) {

            Squad currentSquad = squadPool.get((int) (Math.random() * squadPool.size()));
            newSquads.add(currentSquad);
            //squads.add(mutate(currentSquad, mutationRate));

        }
        return newSquads;

    }


    private static List<Squad> getSquadPool(List<Squad> squads) {

        List<Squad> squadPool = new ArrayList<>();

        //sort squads by highest chemistry in descending order
        mergeSort(squads, 0, squads.size() - 1);

        //percentage representation of the top 20 squads in future squad list
        double[] sizeRatio = {.20, .15, .10, .10, .05, .05, .05, .05, .05, .05, .05,
                .025, .025, .025, .025, .025, .025, .025, .025, .025, .025, .025 };

        for (int j = 0 ; j< sizeRatio.length; j++) {
            double size = sizeRatio[j];
            for (int i = 0; i < size; i++) {
                Squad squad = squads.get(j);
                squadPool.add(squad);
            }
        }

        return squadPool;
    }

    private static void mergeSort(List<Squad> squads, int l, int h) {
        if (l < h) {
            int m = l+(h-l)/2;
            mergeSort(squads, l, m);
            mergeSort(squads, m+1, h);
            merge(squads, l, m, h);
        }
    }

    private static void merge(List<Squad> squads, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 =  r - m;
        List<Squad> leftSquads = new ArrayList<>();
        List<Squad> rightSquads = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftSquads.add(squads.get(l + i));
        }

        for (int j = 0; j < n2; j++) {
            rightSquads.add(squads.get(m + 1+ j));
        }

        int i = 0;
        int j = 0;
        int k = l;
        while ((i < n1) && (j < n2)) {
            if (leftSquads.get(i).getChemistry() < rightSquads.get(j).getChemistry())  {
                squads.set(k, leftSquads.get(i));
                i++;
            } else {
                squads.set(k, rightSquads.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            squads.set(k, leftSquads.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            squads.set(k, rightSquads.get(j));
            j++;
            k++;
        }
    }

}
