package mats;

import java.sql.*;

public class Kontroll {
	private String databasenavn = "";
	private String databasedriver = "com.mysql.jdbc.Driver";
	private Connection forbindelse;
	private ResultSet resultat;
	private Statement stmt;
	
	private static Kontroll single_instance = null;
	
	public static Kontroll getInstance(){
        if( single_instance == null){
            single_instance = new Kontroll();
        }
        return single_instance;
    }
	
	public void lagForbindelse(){
		try {
			forbindelse = DriverManager.getConnection(databasenavn, "", "");
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void lukkForbindelse() throws Exception {
		try {
			if(forbindelse != null) forbindelse.close();
		} catch (Exception e) {throw new Exception("Kan ikke lukke databasen");}
	}
	
	public void regVare(Vare v) throws Exception {
		String sql = "INSERT INTO Vare VALUES('" + v.getVareNr() + "','" + v.getBetegnelse() + "','" + v.getPris() + "','" + v.getKatNr() + "','" + v.getAntall() + "','" + v.getHylle() + "');";
		try {
			lagForbindelse();
			stmt = forbindelse.createStatement();
			stmt.executeUpdate(sql);
			lukkForbindelse();
		}catch(Exception ex) {throw new Exception ("Kan ikke oppdatere Vare tabell");}
	}
	
	public ResultSet hentVarer() throws Exception {
		resultat = null;
		try {
			lagForbindelse();
			String sql = "SELECT v.VNr, v.Betegnelse, v.Pris, k.Navn, v.KatNr, v.Antall, v.Hylle FROM Vare as v, Kategori as k WHERE v.KatNr = k.KatNr;";
			stmt = forbindelse.createStatement();
			resultat = stmt.executeQuery(sql);
		}catch (Exception e) {throw new Exception ("Kan ikke hente varer fra tabellen");}
		return resultat;
	}
	
	public ResultSet hentOrdre(int n) throws Exception {
		resultat = null;
		try {
			lagForbindelse();
			if(n == 0) {
				String sql = "SELECT * FROM Ordrelinje;";
				stmt = forbindelse.createStatement();
				resultat = stmt.executeQuery(sql);
			}
			else {
				String sql = "SELECT * FROM Ordrelinje WHERE OrdreNr = " + n + ";";
				stmt = forbindelse.createStatement();
				resultat = stmt.executeQuery(sql);
			}
			
		}catch (Exception e) {throw new Exception ("Kan ikke hente varer fra tabellen");}
		return resultat;
	}	
	
	public ResultSet hentKundeInfo(int ordreNummer) throws SQLException {
		resultat = null;
		lagForbindelse();
		stmt = forbindelse.createStatement();
		String sql = "SELECT o.OrdreDato, o.KNr, k.Fornavn, k.Etternavn FROM Ordre as o, Kunde as k WHERE o.OrdreNr = " + ordreNummer + " AND o.KNr = k.KNr;" ;
		resultat = stmt.executeQuery(sql);
		
		return resultat;
	}
	
	public ResultSet hentKunde(int kundeNummer) throws SQLException {
		resultat = null;
		lagForbindelse();
		stmt = forbindelse.createStatement();
		String sql = "SELECT Fornavn, Etternavn, Adresse FROM Kunde WHERE KNr = "+ kundeNummer + ";";
		resultat = stmt.executeQuery(sql);
		
		return resultat;
	}
	
	public void slettKunde(int kundeNummer) throws SQLException {
		lagForbindelse();
		stmt = forbindelse.createStatement();
		String sql = "UPDATE Kunde SET Fornavn = null, Etternavn = null, Adresse = null, PostNr = null, Kjønn = null WHERE KNr = " + kundeNummer + ";";
		stmt.executeUpdate(sql);
	}
	
	

}
