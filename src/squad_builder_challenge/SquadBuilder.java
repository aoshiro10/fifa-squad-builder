package squad_builder_challenge;

import java.util.ArrayList;

public class SquadBuilder {
	
	public static int populationSize = 1000;
	
	public static float mutationRate = .10f;
	
	public static int generation = 1;
	
	public static ArrayList<Squad> squads;
	
	public static int bestSoFar = 0;
	

	public static void main(String[] args) {
		
		squads = PlayerScrapper.generateSquads();
		
		while (true) {
			
			Squad currentBest = GeneticAlgorithm.evaluate();
			
			if (bestSoFar < currentBest.chemistry) {
				System.out.println("Generation " + String.valueOf(generation) + ": " + String.valueOf(currentBest.chemistry));
				bestSoFar = currentBest.chemistry;
			}
			
			Squad copy = currentBest.copy();
			
			squads = GeneticAlgorithm.generateNextGeneration();
			
			squads.set(0, copy);
			
			if (currentBest.chemistry >= 100) {
				
				currentBest.print();
				break;
				
			} 
			
			generation++;
			
		}

	}

}
