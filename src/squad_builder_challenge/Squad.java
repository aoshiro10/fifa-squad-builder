package squad_builder_challenge;

public class Squad {
	
	public Player gk;
	public Player rb;
	public Player rcb;
	public Player lcb;
	public Player lb;
	public Player cdm;
	public Player rm;
	public Player cam;
	public Player lm;
	public Player lf;
	public Player rf;
	
	public int chemistry;
	
	public Squad(Player gk, Player rb, Player rcb, Player lcb, 
				Player lb, Player cdm, Player rm, Player cam,
				Player lm, Player lf, Player rf) {
		
		this.gk = gk;
		this.rb = rb;
		this.rcb = rcb;
		this.lcb = lcb;
		this.lb = lb;
		this.cdm = cdm;
		this.rm = rm;
		this.cam = cam;
		this.lm = lm;
		this.lf = lf;
		this.rf = rf;
		
		this.chemistry = this.calculateSquadChemistry();
		
	}
	
	public int calculateSquadChemistry() {
		
		int totalChemistry = 0;
		
		totalChemistry += chemistry(this.lf, this.rf); 
		totalChemistry += chemistry(this.lf, this.cam);
		totalChemistry += chemistry(this.rf, this.cam);
		totalChemistry += chemistry(this.lm, this.lf);
		totalChemistry += chemistry(this.lm, this.cdm);
		totalChemistry += chemistry(this.lm, this.lb);
		totalChemistry += chemistry(this.lb, this.lcb);
		totalChemistry += chemistry(this.lcb, this.gk);
		totalChemistry += chemistry(this.lcb, this.rcb);
		totalChemistry += chemistry(this.lcb, this.cdm);
		totalChemistry += chemistry(this.gk, this.rcb);
		totalChemistry += chemistry(this.rcb, this.cdm);
		totalChemistry += chemistry(this.rcb, this.rb);
		totalChemistry += chemistry(this.rb, this.rm);
		totalChemistry += chemistry(this.rm, this.cdm);
		totalChemistry += chemistry(this.rm, this.cam);
		totalChemistry += chemistry(this.rm, this.rf);
		
//		Links: 		
//		LF - RF
//		LF - CAM
//		RF - CAM
//		LM - LF
//		LM - CDM
//		LM - LB
//		LB - LCB
//		LCB - GK
//		LCB - RCB
//		LCB - CDM
//		GK - RCB
//		RCB - CDM
//		RCB - RB
//		RB - RM
//		RM - CDM
//		RM - CAM
//		RM - RF
		
		return totalChemistry;
		
	}
	
	
	
//		STRONG = 6 (SAME Country and (LEAGUE))  or (SAME CLUB)
//		NORMAL = 5 (SAME LEAGUE or CLUB) OR (SAME COUNTRY DIFFERENT CLUB AND LEAGUE)
//		WEAK = 3 OTHERWISE
	private static int chemistry(Player player1, Player player2) {
		
		
		if (player1.country.equals(player2.country)) {
			
			if (player1.league.equals(player2.league)) {
				
				return 6;
				
			}
			
			return 5;
	
		}
		
		if (player1.league.equals(player2.league)) {
			
			if (player1.club == player2.club) {
				
				return 6;
				
			}
			
			
			return 5;
		}
		
		return 3;
	}

	
	public Squad copy() {
		
		return new Squad(this.gk, this.rb, this.rcb, this.lcb, this.lb, 
				this.cdm, this.rm, this.cam, this.lm, this.lf, this.rf);
	}
	
	public void print() {
		
		System.out.println(this.gk.toString());
		System.out.println(this.rb.toString());
		System.out.println(this.rcb.toString());
		System.out.println(this.lcb.toString());
		System.out.println(this.lb.toString());
		System.out.println(this.cdm.toString());
		System.out.println(this.rm.toString());
		System.out.println(this.lm.toString());
		System.out.println(this.cam.toString());
		System.out.println(this.rf.toString());
		System.out.println(this.lf.toString());

	}
}
