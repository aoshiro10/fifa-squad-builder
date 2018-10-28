package squad_builder_challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class PlayerScrapper {
	
	
	
	static String[] countries = {"Spain", "France", "Brazil", "Germany",
							"Argentina", "Italy", "England",
							"Portugal", "Netherlands", "Belgium", "Colombia",
							"Uruguay", "Croatia", "Turkey", "Serbia" };
	
	static String[] positions = {"GK", "ST", "CAM", "LW", "RW", "LB", "RB", "CB"};
	
	static String[] leagues = {"England Premier League", "Spain Primera Division", 
						"Italy Serie A", "Germany 1. Bundesliga", "France Ligue 1"};
	
	
	public static ArrayList<Player> goalKeepers = new ArrayList<Player>();
	public static ArrayList<Player> strikers = new ArrayList<Player>();
	public static ArrayList<Player> midfielders = new ArrayList<Player>();
	public static ArrayList<Player> leftWings = new ArrayList<Player>();
	public static ArrayList<Player> rightWings = new ArrayList<Player>();
	public static ArrayList<Player> leftBacks = new ArrayList<Player>();
	public static ArrayList<Player> rightBacks = new ArrayList<Player>();
	public static ArrayList<Player> centerBacks = new ArrayList<Player>();
	
	
	public static Player generatePlayer() {
		
		int countryIndex = (int) (Math.random() * countries.length);
		int positionIndex = (int) (Math.random() * positions.length);
		int leagueIndex = (int) (Math.random() * leagues.length);
		
		String country = countries[countryIndex];
		String position = positions[positionIndex];
		String league = leagues[leagueIndex];
		int rating = getRandomRating();
		int club = (int) (Math.random() * 20);
		
		return new Player(country, position, league, rating, club);
		
	}
	
	public static void generateAllPlayers() {
		
		int totalPlayers = 10000;
		
		for (int playerIndex = 0; playerIndex < totalPlayers; playerIndex++) {
			
			Player player = generatePlayer();
			
			addPlayerToArrayList(player);
			
		}
		
	}
	
	public static Squad generateSquad() {
		
		int gkIndex = (int) (Math.random() * goalKeepers.size());
		int rbIndex = (int) (Math.random() * rightBacks.size());
		int rcbIndex = (int) (Math.random() * centerBacks.size());
		int lcbIndex = rcbIndex;
		
		while (lcbIndex == rcbIndex) {
			
			lcbIndex = (int) (Math.random() * centerBacks.size());
		}
		
		int lbIndex = (int) (Math.random() * leftBacks.size());
		int cdmIndex = (int) (Math.random() * midfielders.size());
		int rmIndex = (int) (Math.random() * rightWings.size());
		int lmIndex = (int) (Math.random() * leftWings.size());
		int camIndex = cdmIndex;
		while (camIndex == cdmIndex) {
			camIndex = (int) (Math.random() * midfielders.size());
		}
		int lfIndex = (int) (Math.random() * strikers.size());
		int rfindex = lfIndex;
		while (rfindex == lfIndex) {
			rfindex = (int) (Math.random() * strikers.size());
		}
		
		Player gk = goalKeepers.get(gkIndex);
		Player rb = rightBacks.get(rbIndex);
		Player rcb = centerBacks.get(rcbIndex);
		Player lcb = centerBacks.get(lcbIndex);
		Player lb = leftBacks.get(lbIndex);
		Player cdm = midfielders.get(cdmIndex);
		Player rm = rightWings.get(rmIndex);
		Player cam = midfielders.get(camIndex);
		Player lm = leftWings.get(lmIndex);
		Player lf = strikers.get(lfIndex);
		Player rf = strikers.get(rfindex);
		
		return new Squad(gk, rb, rcb, lcb, lb, cdm, rm, cam, lm,  lf,  rf);
		
	}
	
	public static ArrayList<Squad> generateSquads() {
		
		generateAllPlayers();
		
		ArrayList<Squad> squads = new ArrayList<Squad>();
		
		for (int squadIndex = 0; squadIndex < SquadBuilder.populationSize; squadIndex++) {
			
			Squad squad = generateSquad();
			
			squads.add(squad);
			
		}
		
		return squads;
		
	}
	
	private static void addPlayerToArrayList(Player player) {
		
		if (player.position.equals("GK")) {
			goalKeepers.add(player);
		} else if (player.position.equals("ST")) {
			strikers.add(player);
		} else if (player.position.equals("CAM")) {
			midfielders.add(player);
		} else if (player.position.equals("LW")) {
			leftWings.add(player);
		} else if (player.position.equals("RW")) {
			rightWings.add(player);
		} else if (player.position.equals("LB")) {
			leftBacks.add(player);
		} else if (player.position.equals("RB")) {
			rightBacks.add(player);
		} else {
			centerBacks.add(player);
		}
		
	}

	private static int getRandomRating() {

		int min = 54;
		int max = 99;

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	


}
