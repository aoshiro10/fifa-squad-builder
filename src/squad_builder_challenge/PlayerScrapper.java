package squad_builder_challenge;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator; 
import java.util.Map; 
  
public class PlayerScrapper {
	
	
	private static void getPlayers() {
		
		File file = new File("src/players.txt");
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			String str = br.readLine();
			
			while (str != null) {
				
				String[] info = str.split(",");
				
				String name = info[0];
				String position = info[1];
				int rating = Integer.parseInt(info[2]);
				String club = info[3];
				String league = info[4];
				String country = info[5];
				
				Player player = new Player(name, position, rating, club, league, country);
				
				addPlayer(player);
				
				str = br.readLine();

			}	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void addPlayer(Player player) {
		
		String position = player.position;
		
		if (position.equals("CM") || position.equals("CAM") || 
			position.equals("CDM") || position.equals("CF") || position.equals("ST")) {
			midfielders.add(player);
		} else if (position.equals("GK")) {
			goalKeepers.add(player);
		} else if (position.equals("RW") || position.equals("RF") || position.equals("RM")) {
			rightWings.add(player);
		} else if (position.equals("LW") || position.equals("LF") || position.equals("LM")) {
			leftWings.add(player);
		} else if (position.equals("RB") || position.equals("RWB")) {
			rightBacks.add(player);
		} else if (position.equals("LB") || position.equals("LWB")) {
			leftBacks.add(player);
		} else if (position.equals("CB")) {
			centerBacks.add(player);
		} else {
			System.out.println(position + " not found");
		}
		
	}

	static String[] countries = {"Spain", "France", "Brazil", "Germany",
							"Argentina", "Italy", "England",
							"Portugal", "Netherlands", "Belgium", "Colombia",
							"Uruguay", "Croatia", "Turkey", "Serbia" };
	
	static String[] positions = {"GK", "ST", "LW", "RW", "LB", "RB", "CB"};
	
	static String[] leagues = {"England Premier League", "Spain Primera Division", 
						"Italy Serie A", "Germany 1. Bundesliga", "France Ligue 1"};
	
	
	public static ArrayList<Player> goalKeepers = new ArrayList<Player>();
	public static ArrayList<Player> midfielders = new ArrayList<Player>();
	public static ArrayList<Player> leftWings = new ArrayList<Player>();
	public static ArrayList<Player> rightWings = new ArrayList<Player>();
	public static ArrayList<Player> leftBacks = new ArrayList<Player>();
	public static ArrayList<Player> rightBacks = new ArrayList<Player>();
	public static ArrayList<Player> centerBacks = new ArrayList<Player>();
	
	
//	public static Player generatePlayer() {
//		
//		int countryIndex = (int) (Math.random() * countries.length);
//		int positionIndex = (int) (Math.random() * positions.length);
//		int leagueIndex = (int) (Math.random() * leagues.length);
//		
//		String country = countries[countryIndex];
//		String position = positions[positionIndex];
//		String league = leagues[leagueIndex];
//		int rating = getRandomRating();
//		int club = (int) (Math.random() * 20);
//		
//		return new Player(country, position, league, rating, club);
//		
//	}
//	
//	public static void generateAllPlayers() {
//		
//		int totalPlayers = 10000;
//		
//		for (int playerIndex = 0; playerIndex < totalPlayers; playerIndex++) {
//			
//			Player player = generatePlayer();
//			
//			addPlayerToArrayList(player);
//			
//		}
//		
//	}
	
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
		int lfIndex = (int) (Math.random() * midfielders.size());
		int rfindex = lfIndex;
		while (rfindex == lfIndex) {
			rfindex = (int) (Math.random() * midfielders.size());
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
		Player lf = midfielders.get(lfIndex);
		Player rf = midfielders.get(rfindex);
		
		return new Squad(gk, rb, rcb, lcb, lb, cdm, rm, cam, lm,  lf,  rf);
		
	}
	
	public static ArrayList<Squad> generateSquads() {
		
		getPlayers();
		
		System.out.print(centerBacks.size());
		
		ArrayList<Squad> squads = new ArrayList<Squad>();
		
		for (int squadIndex = 0; squadIndex < SquadBuilder.populationSize; squadIndex++) {
			
			Squad squad = generateSquad();
			
			squads.add(squad);
			
		}
		
		return squads;
		
	}
	
	

	


}
