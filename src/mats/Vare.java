package mats;

public class Vare {
	private String vareNr;
	private String betegnelse;
	private double pris;
	private int katNr;
	private int antall;
	private String hylle;
	
	public Vare(String vareNr, String betegnelse, double pris, int katNr, int antall, String hylle) {
		this.vareNr = vareNr;
		this.betegnelse = betegnelse;
		this.pris = pris;
		this.katNr = katNr;
		this.antall = antall;
		this.hylle = hylle;
	}

	public String getVareNr() {
		return vareNr;
	}

	public String getBetegnelse() {
		return betegnelse;
	}

	public double getPris() {
		return pris;
	}

	public int getKatNr() {
		return katNr;
	}

	public int getAntall() {
		return antall;
	}

	public String getHylle() {
		return hylle;
	}
	
	

}
