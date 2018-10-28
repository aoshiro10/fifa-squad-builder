package squad_builder_challenge;

import java.util.ArrayList;

public class GeneticAlgorithm {
	
	
	public static ArrayList<Squad> getMatingPool() {
		
		ArrayList<Squad> matingPool = new ArrayList<Squad>();
		
		for (int squadIndex = 0; squadIndex < SquadBuilder.squads.size(); squadIndex++) {
				
			Squad squad = SquadBuilder.squads.get(squadIndex);
			
			for (int index = 0; index < squad.chemistry; index++) {
				
				matingPool.add(squad);
				
			}
		}
		
		return matingPool;
		
	}
	
	public static ArrayList<Squad> generateNextGeneration() {
		
		ArrayList<Squad> squads = new ArrayList<Squad>();
		
		ArrayList<Squad> matingPool = getMatingPool();
		
		
		for (int squadIndex = 0; squadIndex < SquadBuilder.populationSize; squadIndex++) {
			
			Squad parent1 = matingPool.get((int) (Math.random() * matingPool.size()));
			Squad parent2 = matingPool.get((int) (Math.random() * matingPool.size()));
	
			Squad crossOverSquad = crossOver(parent1, parent2);
			Squad mutatedSquad = mutate(crossOverSquad);
			
			squads.add(mutatedSquad);
			
		}
		
		return squads;
		
	}
	
	
	public static Squad crossOver(Squad squad1, Squad squad2) {
		
		Player gk = squad1.gk;
		Player rb = squad1.rb;
		Player rcb = squad1.rcb;
		Player lcb = squad1.lcb;
		Player lb = squad1.lb;
		Player cdm = squad1.cdm;
		Player rm = squad2.rm;
		Player cam = squad2.cam;
		Player lm = squad2.lm;
		Player lf = squad2.lf;
		Player rf = squad2.rf;
		
		return new Squad(gk, rb, rcb, lcb, lb, cdm, rm, cam, lm, lf, rf);
		
	}
	
	public static Squad mutate(Squad squad) {
		
		for (int positionIndex = 0; positionIndex < 11; positionIndex ++) {
			
			if (Math.random() < SquadBuilder.mutationRate) {
				
				if (positionIndex == 0) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.goalKeepers.size());
					
					squad.gk = PlayerScrapper.goalKeepers.get(newPlayerIndex);
					
				} else if (positionIndex == 1) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.rightBacks.size());
					
					squad.rb = PlayerScrapper.rightBacks.get(newPlayerIndex);
					
				} else if (positionIndex == 2) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.centerBacks.size());
					
					squad.rcb = PlayerScrapper.centerBacks.get(newPlayerIndex);
					
				} else if (positionIndex == 3) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.centerBacks.size());
					
					squad.lcb = PlayerScrapper.centerBacks.get(newPlayerIndex);
					
				} else if (positionIndex == 4) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.leftBacks.size());
					
					squad.lb = PlayerScrapper.leftBacks.get(newPlayerIndex);
					
				} else if (positionIndex == 5) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.midfielders.size());
					
					squad.cdm = PlayerScrapper.midfielders.get(newPlayerIndex);
				
				} else if (positionIndex == 6) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.midfielders.size());
					
					squad.cam = PlayerScrapper.midfielders.get(newPlayerIndex);
					
				} else if (positionIndex == 7) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.rightWings.size());
					
					squad.rm = PlayerScrapper.rightWings.get(newPlayerIndex);
					
				} else if (positionIndex == 8) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.leftWings.size());
					
					squad.lm = PlayerScrapper.leftWings.get(newPlayerIndex);
					
				} else if (positionIndex == 9) {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.strikers.size());
					
					squad.lf = PlayerScrapper.strikers.get(newPlayerIndex);
					
				} else {
					
					int newPlayerIndex = (int) (Math.random() * PlayerScrapper.strikers.size());
					
					squad.rf = PlayerScrapper.strikers.get(newPlayerIndex);
					
				}
				
			}
			
		}
		
		squad.chemistry = squad.calculateSquadChemistry();
		
		return squad;
		
	}
	
	public static Squad evaluate() {
		
		int bestChemistry = 0;
		int bestIndex = 0;
		
		for (int squadIndex = 0; squadIndex < SquadBuilder.squads.size(); squadIndex++) {
			
			Squad squad = SquadBuilder.squads.get(squadIndex);
			
			if (bestChemistry < squad.chemistry) {
				bestChemistry = squad.chemistry;
				bestIndex = squadIndex;
				
			}
			
		}
		
		return SquadBuilder.squads.get(bestIndex);
	}
	
	
}
