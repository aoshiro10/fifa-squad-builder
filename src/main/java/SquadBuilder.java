/**
 * Program to find a Fifa ultimate team squad
 * using genetic algorithm to find a team with
 * 100 chemistry.
 */
public class SquadBuilder {

    public static void main(String[] args) {
        //hyperparameters
        float mutationRate = 0.25f;
        int populationSize = 10000;
        int maxGenerations = 100;
        GeneticAlgorithm.init(mutationRate, populationSize, maxGenerations);
    }

}
